@Regression @Smoke
  Feature: Reset Password: User could reset his/her password successfully
    Scenario: Reset password using valid email
      When user clicks on <Log in> hyperlink
      And user clicks on <Forgot Password?> hyperlink
      And user enters a valid email
      And user clicks on <Recover> button
      Then Reset Password is done successfully