package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import Utilities.finishStepDefinition;

public class S01_Registration {

    @When("user clicks on <Register> hyperlink")
    public void userClicksOnRegisterHyperlink() {
        Hooks.registerPage.registerHL().click();
    }

    @And("user enters valid first name")
    public void userEntersValidFirstName() {
        Hooks.registerPage.FirstNameTF().sendKeys(Hooks.firstName);
    }

    @And("user enters valid last name")
    public void userEntersValidLastName() {
        Hooks.registerPage.LastNameTF().sendKeys(Hooks.lastName);
    }

    @And("user enters a valid email")
    public void userEntersAValidEmail() {
        Hooks.registerPage.EmailTF().sendKeys(Hooks.email);
    }

    @And("user enters a valid password")
    public void userEntersAValidPassword() {
        Hooks.registerPage.passwordTF().sendKeys(Hooks.password);
    }

    @And("user enters the valid password <Confirm password> textField")
    public void userEntersTheValidPasswordConfirmPasswordTextField() {
        Hooks.registerPage.confirmPasswordTF().sendKeys(Hooks.password);
    }

    @And("user clicks on <Register> button")
    public void userClicksOnRegisterButton() {
        Hooks.registerPage.registerBtn().click();
    }

    @Then("registration is done successfully")
    public void registrationIsDoneSuccessfully() {
        // 1) There is a registration success message
        String expectedResult = "Your registration completed";
        String actualResult = Hooks.registerPage.registerMsg().getText();
        Assert.assertTrue(actualResult.contains(expectedResult));
        // 2) Continue button is displayed
        Assert.assertTrue(Hooks.registerPage.continueBtn().isDisplayed());
        // Increase the number of passed scenarios by one
        finishStepDefinition.numberOfPassedScenarios++;
    }
}
