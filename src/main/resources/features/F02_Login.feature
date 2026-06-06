@Regression @Smoke
  Feature: Login: User could log in with valid email and password
    Scenario: Login with valid data
      When user clicks on <Log in> hyperlink
      And user enters a valid email in <Email> textField
      And user enters a valid password in <Password> textField
      And user clicks on <Log in> button
      Then Login is done successfully