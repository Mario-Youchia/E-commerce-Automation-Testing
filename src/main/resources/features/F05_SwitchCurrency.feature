@Regression @Smoke2 @Smoke3
  Feature: Switch Currency: Logged User could switch between currencies US-Euro
    Scenario: Switch currency
      When user login successfully to the website
      Given user selects the other currency from <currency> dropdown menu
      Then Switch Currency is done successfully