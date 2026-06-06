@Regression @Smoke4 @Smoke5
  Feature: Select Category: Logged user could select different Categories

    Scenario: Select different category
      When user login successfully to the website
      Given user hovers on a category in homepage
      And user selects one of the sub-categories or the category itself if there are no sub-categories
      Then Select Category is done successfully