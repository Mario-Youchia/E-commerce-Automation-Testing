@Regression @Smoke9
  Feature: Add products to shopping cart: Logged user could add different products to Shopping cart
    Background: Login and Select any category
      When user login successfully to the website
      And user selects a random category
    Scenario: user adds a first product to shopping cart
      And user adds a first product to shopping cart
    Scenario: user adds a second product to shopping cart
      And user adds a second product to shopping cart
    Scenario: user adds a third product to shopping cart and go to shopping cart
      And user adds a third product to shopping cart
      And user navigates to shopping cart
      And user increases the quantity of the first product by 1
      And user increases the quantity of the third product by 2
      And user clicks on <Update shopping cart> button
      Then Add products to shopping cart is done successfully