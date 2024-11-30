Feature: Purchase a Product

  @Demo
  Scenario: Verify the integration of product checkout flow
    When user logged in with valid login details
    Then verify that it should redirect to product page
    And take a screenshot
    When user add "Sauce Labs Bolt T-Shirt" product to the cart
    Then verify that selected product is added to cart or not
    When user add "Sauce Labs Fleece Jacket" product to the cart
    Then verify that selected product is added to cart or not
#    When user add "Sauce Labs Backpack" product to the cart
#    Then verify that selected product is added to cart or not
    And take a screenshot
    When user click on cart button
    Then validate that added products into that cart are showing correct
    And take a screenshot
    When user click on checkout button
    Then verify that user is redirected to checkout page
    And take a screenshot
    When user add "FirstName", "LastName", "12345" postal code and click on Continue button
#    Then validate that quantity "1", price "$29.99" and total price for product "Sauce Labs Backpack" is showing correct
    And validate that quantity "1", price "$49.99" and total price for product "Sauce Labs Fleece Jacket" is showing correct
    And validate that quantity "1", price "$15.99" and total price for product "Sauce Labs Bolt T-Shirt" is showing correct
    And take a screenshot
    When user click on finish button
    Then verify that user is on checkout complete page and see message "Thank you for your order!"
    And take a screenshot
    When user click on log out button from hamburger menu
    Then verify that user is redirected to login page again
    And take a screenshot