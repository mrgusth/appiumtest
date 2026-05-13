package com.mobile;

import io.appium.java_client.MobileElement;
import io.appium.java_client.functions.ExpectedCondition;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * @author Karla Ccallo
 */
public class MobileBase {
    public MobileBase(){
        PageFactory.initElements(new AppiumFieldDecorator(MobileDriverManager.getDriver()),this);
    }

    public MobileElement waitUntilVisibilityElement(int timeOnSeconds, MobileElement element) {
        WebDriverWait webDriverWait = new WebDriverWait(MobileDriverManager.getDriver(), (long)timeOnSeconds);
        return (MobileElement)webDriverWait.until(ExpectedConditions.visibilityOf(element));
    }

    public boolean isObjectVisibleBySeconds(MobileElement element, long segundos) {
        MobileDriverManager.getDriver().manage().timeouts().implicitlyWait(segundos, TimeUnit.SECONDS);
        boolean esVisible = false;
        try {
            esVisible = element.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException var) {
            System.out.println("Error: "+ var);
        }
        return esVisible;
    }

    public MobileElement getMobileElementByXPath(String xPath) {
        return MobileDriverManager.getDriver().findElement(By.xpath(xPath));
    }
    public void clickElement(MobileElement element) {
        element.click();
    }
    public void sendKey(MobileElement element, String input) {
        element.sendKeys(input);
    }

    public String getText(MobileElement element) {
        return element.getText();
    }

    public void explicitWaiting(int ms, ExpectedCondition<?> expectedCondition) {
        new WebDriverWait(MobileDriverManager.getDriver(),ms).until(ExpectedConditions.refreshed(expectedCondition));
    }
}
