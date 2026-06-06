@Regression @Smoke @Smoke2
  Feature: Search: Logged User could search for any product
    Scenario: Search for a product
      When user login successfully to the website
      When user enters a product name in the <Search> testField
      And user clicks on <Search> button
      Then Search is done successfully