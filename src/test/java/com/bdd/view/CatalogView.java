package com.bdd.view;

import com.mobile.MobileBase;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;


/**
 * @author Karla Ccallo
 */
public class CatalogView extends MobileBase {

    @AndroidFindBy(accessibility = "Add To Cart button")
    @iOSXCUITFindBy(accessibility = "Add To Cart button")
    private MobileElement btnAgregarCarrito;


    public boolean validarPantalla(String sTitulo) {
        String sXPath = "//android.widget.TextView[@text='%s']";
        return isObjectVisibleBySeconds(getMobileElementByXPath(String.format(sXPath, sTitulo)), 2);
    }

}
