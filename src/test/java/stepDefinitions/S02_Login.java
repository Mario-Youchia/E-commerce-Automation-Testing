package stepDefinitions;

import Utilities.finishStepDefinition;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class S02_Login {

    @When("user clicks on <Log in> hyperlink")
    public void userClicksOnLogInHyperlink() {
        Hooks.loginPage.loginHL().click();
    }

    @And("user enters a valid email in <Email> textField")
    public void userEntersAValidEmailInEmailTextField() {
        Hooks.loginPage.emailTF().sendKeys(Hooks.email);
    }

    @And("user enters a valid password in <Password> textField")
    public void userEntersAValidPasswordInPasswordTextField() {
        Hooks.loginPage.passwordTF().sendKeys(Hooks.password);
    }

    @And("user clicks on <Log in> button")
    public void userClicksOnLogInButton() {
        Hooks.loginPage.loginBtn().click();
    }

    @Then("Login is done successfully")
    public void loginIsDoneSuccessfully() {
        // 1) Redirected to homepage
        String expectedResult = Hooks.mainPageUrl;
        String actualResult = Hooks.driver.getCurrentUrl();
        Assert.assertEquals(actualResult,expectedResult);
        // 2) MY ACCOUNT hyperlink is displayed
        Assert.assertTrue(Hooks.loginPage.MyAccountHL().isDisplayed());
        // Increase the number of passed scenarios by one
        finishStepDefinition.numberOfPassedScenarios++;
    }
}
