package com.cucumber.Pages;

import com.cucumber.Base.MainBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends MainBase {

    private final By userNameTextbox = By.id("user-name");
    private final By passwordTextbox = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By productsLabel = By.xpath("//*[@id='header_container']//span[@class='title']");


    WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    public void enterUserName() {
        enterText(driver, userNameTextbox, readPropertiesFile("userName"));
    }

    public void enterPassword(){
        enterText(driver, passwordTextbox, readPropertiesFile("password"));
    }

    public void clickLoginButton(){
        singleClick(driver, loginButton);
    }

    public boolean verifyProductsPage(){
        return !isWebElementEmpty(driver, productsLabel);
    }
}
