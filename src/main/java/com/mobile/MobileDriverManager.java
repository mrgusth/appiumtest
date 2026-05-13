package com.mobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import lombok.Getter;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Karla Ccallo
 */
public class MobileDriverManager {
    @Getter
    private static AppiumDriver<MobileElement> driver;

    public static DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
    public static String appiumHub = "";

    public static void setMobileDriver() {

        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "17");
        desiredCapabilities.setCapability("automationName", "UIAutomator2");
        desiredCapabilities.setCapability("deviceName", "GOOGLE");
        desiredCapabilities.setCapability("udid", "emulator-5554");
        desiredCapabilities.setCapability("app", "C:/Users/Nox/Documents/vscode-workspace/capacitacion-mobile-appium/src/test/resources/app/mda-2.0.2-23.apk");
        desiredCapabilities.setCapability("noReset", true);
        appiumHub = "http://127.0.0.1:4723";

//        desiredCapabilities.setCapability("platformName", "Android");
//        desiredCapabilities.setCapability("automationName", "UIAutomator2");
//        desiredCapabilities.setCapability("noReset", false);
//        desiredCapabilities.setCapability("app", "bs://6541d74ccbabfc8db32c671c3106804a5cb87ec1");
//        desiredCapabilities.setCapability("browserstack.user", "user_borwser");
//        desiredCapabilities.setCapability("browserstack.key", "pass_browser");
//        desiredCapabilities.setCapability("device", "Google Pixel 3");
//        desiredCapabilities.setCapability("os_version", "9.0");
//        desiredCapabilities.setCapability("browserstack.debug", true);
//        desiredCapabilities.setCapability("browserstack.networksLogs", true);
//        desiredCapabilities.setCapability("name", "sesionViernes");
//        appiumHub = "https://hub-cloud.browserstack.com/wd/hub";

        System.out.println("url server:"+appiumHub);

        try {
            String os = desiredCapabilities.getCapability("platformName").toString();

            switch (os.toUpperCase(Locale.ROOT)) {
                case "ANDROID":
                    desiredCapabilities.setCapability("appium:appPackage", "com.saucelabs.mydemoapp.android");
                    desiredCapabilities.setCapability("appium:appActivity", "com.saucelabs.mydemoapp.android.view.activities.SplashActivity");
                    driver = new AndroidDriver<>(new URL(appiumHub), desiredCapabilities);
                    break;
                case "IOS":
                    desiredCapabilities.setCapability("appium:bundleId", "com.saucelabs.mydemoapp.rn");
                    driver = new IOSDriver<>(new URL(appiumHub), desiredCapabilities);
                    break;
                default:
                    Logger.getLogger(MobileDriverManager.class.getName()).log(Level.WARNING, "Sistema operativo mobile no soportado >>> {0}", os);
            }
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            setDriver(driver);
        } catch (MalformedURLException malformedURLException) {
            Logger.getLogger(MobileDriverManager.class.getName()).log(Level.WARNING, "Ocurrio un error con la URL del servidor de Appium");
        }
    }

    public static void setDriver(AppiumDriver<MobileElement> driver) {
        MobileDriverManager.driver = driver;
    }

    public static void quitDriver() {
         if (driver != null) {
             Logger.getLogger(MobileDriverManager.class.getName()).log(Level.WARNING, "Deteniendo el Driver.");
             MobileDriverManager.getDriver().terminateApp("com.saucelabs.mydemoapp.android");
             if(MobileDriverManager.getDriver()!= null){
                 driver.quit();
             }
        }
    }
}
