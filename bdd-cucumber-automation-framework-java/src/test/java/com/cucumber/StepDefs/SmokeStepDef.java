package com.cucumber.StepDefs;

import com.cucumber.Base.MainBase;
import com.cucumber.Pages.CartPage;
import com.cucumber.Pages.CheckoutPage;
import com.cucumber.Pages.LoginPage;
import com.cucumber.Pages.ProductsPage;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.asserts.SoftAssert;

import static com.cucumber.Base.Hooks.scenario;

public class SmokeStepDef extends MainBase {

    public static SoftAssert softAssert = new SoftAssert();
    LoginPage loginPage = new LoginPage(driver);
    ProductsPage productsPage = new ProductsPage(driver);
    CartPage cartPage = new CartPage(driver);
    CheckoutPage checkoutPage = new CheckoutPage(driver);


    @When("user logged in with valid login details")
    public void user_logged_in_with_valid_login_details() {
        loginPage.enterUserName();
        loginPage.enterPassword();
        loginPage.clickLoginButton();
    }

    @Then("verify that it should redirect to product page")
    public void verify_that_it_should_redirect_to_page() {
        waitForElement();
        softAssert.assertTrue(loginPage.verifyProductsPage(),"verify that it should redirect to product page - Failed");
    }

    @When("user add {string} product to the cart")
    public void user_add_product_to_the_cart(String prodName){
        waitForElement();
        productsPage.clickAddToCart(prodName);
    }

    @Then("verify that selected product is added to cart or not")
    public void verify_that_selected_product_is_added_to_cart_or_not(){
        softAssert.assertTrue(productsPage.verifyTheProductsAddedInCart(), "verify that selected product is added to cart or not - FAILED");
    }

    @When("user click on cart button")
    public void user_click_on_cart_button() {
        waitForElement();
        productsPage.clickViewCart();
    }

    @Then("validate that added products into that cart are showing correct")
    public void validate_that_added_products_into_that_cart_are_showing_correct(){
        waitForElement();
        softAssert.assertTrue(cartPage.verifyTheProductsInCartPage(), "validate that added products into that cart are showing correct - FAILED");
    }

    @When("user click on checkout button")
    public void user_click_on_checkout_button() {
        waitForElement();
        cartPage.clickCheckout();
    }

    @Then("verify that user is redirected to checkout page")
    public void verify_that_user_is_redirected_to_checkout_page() {
        waitForElement();
        softAssert.assertTrue(checkoutPage.verifyCheckOutInfoPage(), "verify that user is redirected to checkout page - FAILED");
    }

    @When("user add {string}, {string}, {string} postal code and click on Continue button")
    public void user_add_postal_code_and_click_on_continue_button(String fName, String lName, String postalCode) {
        waitForElement();
        checkoutPage.addUserInfo(fName,lName,postalCode);
        checkoutPage.clickContinue();
    }

    @Then("validate that quantity {string}, price {string} and total price for product {string} is showing correct")
    public void validate_that_and_for_product_is_showing_correct(String qty, String price, String prod) {
        waitForElement();
        softAssert.assertTrue(checkoutPage.isQtysCorrect(qty), "validate that quantity, price and total price for product is showing correct - Quantity - FAILED");
        softAssert.assertTrue(checkoutPage.isPricesCorrect(price), "validate that quantity, price and total price for product is showing correct - Price - FAILED");
        softAssert.assertTrue(checkoutPage.isItemsCorrect(prod), "validate that quantity, price and total price for product is showing correct - Product - FAILED");
        softAssert.assertTrue(checkoutPage.priceWithTax(), "validate that quantity, price and total price for product is showing correct - GrandPrice - FAILED");
    }

    @When("user click on finish button")
    public void user_click_on_finish_button() {
        waitForElement();
        checkoutPage.clickFinish();
    }

    @Then("verify that user is on checkout complete page and see message {string}")
    public void verify_that_user_is_on_checkout_complete_page_and_see_message(String msg) {
        waitForElement();
        softAssert.assertTrue(checkoutPage.verifyThanksPage(msg),"verify that user is on checkout complete page and see message - FAILED");
    }

    @When("user click on log out button from hamburger menu")
    public void user_click_on_log_out_button_from_hamburger_menu(){
        waitForElement();
        checkoutPage.scrollToHamburgerMenu();
        checkoutPage.clickHamburgerMenu();
        waitForElement();
        checkoutPage.clickLogout();
    }

    @Then("verify that user is redirected to login page again")
    public void verify_that_user_is_redirected_to_login_page_again() {
        waitForElement();
        softAssert.assertTrue(checkoutPage.showLoginScreen(), "verify that user is redirected to login page again - FAILED");
    }

    @And("take a screenshot")
    public void take_a_screenshot() {
        try {
            scenario.attach(getScreenshot(), "image/png", scenario.getName());
        } catch (Exception e) {
            ExtentCucumberAdapter.getCurrentStep().fail(e.getMessage());
        }
    }
}
