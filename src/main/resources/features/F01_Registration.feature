@Regression @Smoke @Smoke2 @Smoke3 @Smoke4 @Smoke5 @Smoke6 @Smoke7 @Smoke9
  Feature: Registration: User could register with valid data
    Scenario: Registration with valid data
      When user clicks on <Register> hyperlink
      And user enters valid first name
      And user enters valid last name
      And user enters a valid email
      And user enters a valid password
      And user enters the valid password <Confirm password> textField
      And user clicks on <Register> button
      Then registration is done successfully