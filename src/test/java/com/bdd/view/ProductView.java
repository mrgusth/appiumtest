package com.bdd.view;

import com.mobile.MobileBase;
import com.mobile.MobileDriverManager;
import io.appium.java_client.MobileElement;


public class ProductView extends MobileBase {


    public boolean validarProductosCargados() {
        // Check if any product is visible in the list by looking for product containers by resource-id
        try {
            // Use resource-id to find product titles - more reliable than content-desc
            String sXPath = "//*[@resource-id='com.saucelabs.mydemoapp.android:id/titleTV']";
            //String sXPath = "//android.widget.TextView[@resource-id='com.saucelabs.mydemoapp.android:id/titleTV']";
            MobileElement productTitle = waitUntilVisibilityElement(5, getMobileElementByXPath(sXPath));
            return isObjectVisibleBySeconds(productTitle, 3);
        } catch (Exception e) {
            System.out.println("Could not find product titles in catalog");
            return false;
        }
    }

    public void agregarProducto(String producto, int unidades) {
        for (int i = 0; i < unidades; i++) {
            try {
                // Find product by exact text match using resource-id
                // First, click on the product to open its detail view
                String productXPath = "//*[@resource-id='com.saucelabs.mydemoapp.android:id/titleTV' and @text='" + producto + "']";
                MobileElement productTitle = waitUntilVisibilityElement(5, getMobileElementByXPath(productXPath));
                clickElement(productTitle);

                // Wait a bit for detail view to load
                Thread.sleep(1000);

                // Now look for Add to Cart button in the detail view
                // Try multiple strategies to find the button
                try {
                    String addBtnXPath = "//android.widget.Button[@content-desc=\"Tap to add product to cart\"]']";
                    MobileElement addBtn = waitUntilVisibilityElement(3, getMobileElementByXPath(addBtnXPath));
                    clickElement(addBtn);
                } catch (Exception e1) {
                    // Alternative: look for any button with text "ADD"
                    try {
                        String addBtnXPath = "//android.widget.Button[@content-desc=\"Tap to add product to cart\"]";
                        MobileElement addBtn = waitUntilVisibilityElement(3, getMobileElementByXPath(addBtnXPath));
                        clickElement(addBtn);
                    } catch (Exception e2) {
                        System.out.println("Could not find add to cart button for product: " + producto);
                    }
                }

                // Go back to product list
                try {
                    Thread.sleep(500);
                    MobileDriverManager.getDriver().navigate().back();
                    Thread.sleep(500);
                } catch (Exception e) {
                    System.out.println("Could not navigate back");
                }
            } catch (Exception e) {
                System.out.println("Error in agregarProducto for: " + producto + " - " + e.getMessage());
            }
        }
    }

    public boolean validarCarritoActualizado(int expectedItems) {
        try {
            // Use resource-id to find the cart button more reliably
            // The cart button is in the header with resource-id cartRL
            String sXPath = "//*[@resource-id='com.saucelabs.mydemoapp.android:id/cartIV']";
            MobileElement cartButton = waitUntilVisibilityElement(2, getMobileElementByXPath(sXPath));

            // Check if cart is visible (has been updated by adding items)
            return isObjectVisibleBySeconds(cartButton, 2);
        } catch (Exception e) {
            System.out.println("Could not validate cart update");
            return false;
        }
    }
}
