package com.cucumber.Pages;

import com.cucumber.Base.MainBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPage extends MainBase {

    private final By checkOutPageLabel = By.xpath("//*[@id='header_container']//span[@class='title']");
    private final By firstNameTextbox = By.id("first-name");
    private final By lastNameTextbox = By.id("last-name");
    private final By postalCodeTextbox = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By cartQuantity = By.className("cart_quantity");
    private final By cartItemNames = By.className("inventory_item_name");
    private final By cartPrice = By.className("inventory_item_price");
    private final By totalItemsPrice = By.className("summary_subtotal_label");
    private final By taxPrice = By.className("summary_tax_label");
    private final By totalPrice = By.className("summary_total_label");
    private final By finishButton = By.id("finish");
    private final By thanksLabel = By.xpath("//*[@id='checkout_complete_container']/h2");
    private final By hamburgerButton = By.id("react-burger-menu-btn");
    private final By logoutButton = By.id("logout_sidebar_link");
    private final By displayLoginButton = By.id("login-button");

    WebDriver driver;

    public CheckoutPage(WebDriver driver){
        this.driver = driver;
    }

    public boolean verifyCheckOutInfoPage(){
        boolean isCheckoutPageIsShowing = false;
        for(WebElement ele: returnNumberOfElements(driver, checkOutPageLabel)){
            String text = ele.getText();
            isCheckoutPageIsShowing = text.contains("Checkout");
        }
        return isCheckoutPageIsShowing;
    }

    public void addUserInfo(String firstName, String lastName, String postal){
        enterText(driver, firstNameTextbox, firstName);
        enterText(driver, lastNameTextbox, lastName);
        enterText(driver, postalCodeTextbox, postal);
    }

    public void clickContinue(){
        singleClick(driver, continueButton);
    }

    public boolean verifyCompleteCheckoutPage(){
        return isWebElementEmpty(driver, finishButton);
    }

    public boolean isQtysCorrect(String qty){
        boolean isQtyCorrect = false;
        for(WebElement qtty: returnNumberOfElements(driver, cartQuantity)){
            String quantityIs = qtty.getText();
            if(quantityIs.equalsIgnoreCase(qty)){
                isQtyCorrect = true;
                break;
            }
        }
        return isQtyCorrect;
    }

    public boolean isItemsCorrect(String prod){
        boolean isItemCorrect = false;
        for(WebElement item: returnNumberOfElements(driver, cartItemNames)){
            String itemName = item.getText();
            if(itemName.equalsIgnoreCase(prod)){
                isItemCorrect = true;
                break;
            }
        }
        return isItemCorrect;
    }

    public boolean isPricesCorrect(String price){
        boolean isPriceCorrect = false;
        for(WebElement prc: returnNumberOfElements(driver, cartPrice)){
            String priceIs = prc.getText();
            System.out.println("Price Is: "+priceIs);
            if(priceIs.equalsIgnoreCase(price)){
                isPriceCorrect = true;
                break;
            }
        }
        return isPriceCorrect;
    }

    public double itemTotal(){
        String ttl = getNumbersFromStingWithDecimalValue(getTextFromWebElement(driver, totalItemsPrice));
        return Double.parseDouble(ttl);
    }

    public double itemTax(){
        String tx = getNumbersFromStingWithDecimalValue(getTextFromWebElement(driver, taxPrice));
        return Double.parseDouble(tx);
    }

    public double itemTotalPrice(){
        String ttlPrc = getNumbersFromStingWithDecimalValue(getTextFromWebElement(driver, totalPrice));
        return Double.parseDouble(ttlPrc);
    }

    public boolean priceWithTax(){
        double priceWithTax = itemTotal() + itemTax();
        boolean isGrandTotalMatched = false;
        isGrandTotalMatched = priceWithTax == itemTotalPrice();
        System.out.println("Tax is: "+itemTax());
        System.out.println("Total Price is: "+priceWithTax);
        return isGrandTotalMatched;
    }

    public void clickFinish(){
        singleClick(driver, finishButton);
    }

    public boolean verifyThanksPage(String msg){
        boolean isThanksTextShowing = false;
        for(WebElement ele: returnNumberOfElements(driver, thanksLabel)){
            String lbl = ele.getText();
            System.out.println("Thank you page: "+lbl);
            isThanksTextShowing = lbl.equalsIgnoreCase(msg);
        }
        return isThanksTextShowing;
    }

    public void scrollToHamburgerMenu(){
        WebElement ele = driver.findElement(hamburgerButton);
        jsScrollElementInToView(ele);
    }

    public void clickHamburgerMenu(){
        singleClick(driver, hamburgerButton);
    }

    public void clickLogout(){
        singleClick(driver, logoutButton);
    }

    public boolean showLoginScreen(){
        return !isWebElementEmpty(driver, displayLoginButton);
    }

}
