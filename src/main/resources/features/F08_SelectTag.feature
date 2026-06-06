@Regression @Smoke7 @Smoke8
  Feature: Select Tag: Logged user could select different tags
    Background: Select any category
      When user login successfully to the website
      And user selects a random category
    Scenario: user selects different tag
      Given user chooses a tag from <Popular tags> section
      Then Select Tag is done successfully