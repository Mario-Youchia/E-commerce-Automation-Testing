package stepDefinitions;

import Utilities.Util;
import Utilities.finishStepDefinition;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class S05_SwitchCurrency {
    String actualResult;
    @Given("user selects the other currency from <currency> dropdown menu")
    public void userSelectsTheOtherCurrencyFromCurrencyDropdownMenu() {
        actualResult = Hooks.currency.SelectedCurrency().getText();
        Util.switchCurrency(actualResult);
    }

    @Then("Switch Currency is done successfully")
    public void switchCurrencyIsDoneSuccessfully() {
        // Currency in expected result should be different than currency in actual result as switch currency method is
        // used.
        String expectedResult = Hooks.currency.SelectedCurrency().getText();
        Assert.assertNotEquals(actualResult, expectedResult);
        // Increase the number of passed scenarios by one
        finishStepDefinition.numberOfPassedScenarios++;
    }
}
