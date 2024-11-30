package com.cucumber.Pages;

import com.cucumber.Base.MainBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends MainBase {

    private final By yourCartLabel = By.xpath("//span[normalize-space()='Your Cart']");
    private final By itemNames = By.xpath("//div[@class='inventory_item_name']");
    private final By quantities = By.xpath("//div[@class='cart_item']/div[@class='cart_quantity']");
    private final By checkoutButton = By.id("checkout");

    WebDriver driver;

    public CartPage(WebDriver driver){
        this.driver = driver;
    }

    public boolean verifyCartPage(){
        boolean isCartPage = false;
        for(WebElement ele: returnNumberOfElements(driver,yourCartLabel)){
            String cartText = ele.getText();
            isCartPage = cartText.equalsIgnoreCase("Your Cart");
        }
        return isCartPage;
    }

    public boolean verifyTheProductsInCartPage(){
        boolean isProdsShowing = false;
        List<String> addedProjectsAre = productCounter;
        List<WebElement> productsOnCart = returnNumberOfElements(driver,itemNames);
        for(String prod: addedProjectsAre){
            for(WebElement ele: productsOnCart){
                if(prod.equalsIgnoreCase(ele.getText())){
                    isProdsShowing = true;
                }
            }
        }
        return isProdsShowing;
    }

    public boolean verifyCorrectProductsShowing(String prod1, String prod2){
        boolean isProdsSame = false;
        for(WebElement prod: returnNumberOfElements(driver,itemNames)){
            String product = prod.getText();
            System.out.println("Product name on Cart page is: "+product);
            isProdsSame = product.equalsIgnoreCase(prod1) || product.equalsIgnoreCase(prod2);
        }
        return isProdsSame;
    }

    public void clickCheckout(){
        singleClick(driver,checkoutButton);
    }
}
