package stepDefinitions;

import Utilities.Util;
import Utilities.finishStepDefinition;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class S03_ResetPassword {

    @And("user clicks on <Forgot Password?> hyperlink")
    public void userClicksOnForgotPasswordHyperlink() {
        Hooks.resetPage.forgotPasswordHL().click();
    }

    @And("user clicks on <Recover> button")
    public void userClicksOnRecoverButton() {
        Hooks.resetPage.recoveryBtn().click();
    }

    @Then("Reset Password is done successfully")
    public void resetPasswordIsDoneSuccessfully() {
        // 1) Confirmation message displayed and contains the expected result
        String expectedResult = "Email with instructions has been sent to you.";
        String actualResult = Hooks.resetPage.confirmationMsg().getText();
        Assert.assertTrue(actualResult.contains(expectedResult));
        // 2) Confirmation bar has the green color
        String expectedColor = "#4bb07a";
        String actualColor = Hooks.resetPage.confirmationBar().getCssValue("background-color");
        actualColor = Util.convertRGBaTohex(actualColor);
        Assert.assertEquals(actualColor,expectedColor);
        // Increase the number of passed scenarios by one
        finishStepDefinition.numberOfPassedScenarios++;
    }


}
