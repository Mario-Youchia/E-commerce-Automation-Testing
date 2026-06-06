@Regression @Smoke6
  Feature: Filter by color: Logged user could filter with color
    Scenario: user filters shoes by color
      When user login successfully to the website
      Given user hovers on <Apparel> category
      And user chooses <shoes> sub-category
      And user chooses one of the three available colors
      Then Filter by color is done successfully