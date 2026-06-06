package stepDefinitions;

import Utilities.Util;
import Utilities.finishStepDefinition;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class S04_Search {

    String searchKeyword;

    @When("user login successfully to the website")
    public void userLoginSuccessfullyToTheWebsite() {
        Hooks.login.userClicksOnLogInHyperlink();
        Hooks.login.userEntersAValidEmailInEmailTextField();
        Hooks.login.userEntersAValidPasswordInPasswordTextField();
        Hooks.login.userClicksOnLogInButton();
        Hooks.login.loginIsDoneSuccessfully();
        // Decreasing the number of passed scenarios by one to compensate the increasing in the previous line.
        finishStepDefinition.numberOfPassedScenarios--;
    }

    @When("user enters a product name in the <Search> testField")
    public void userEntersAProductNameInTheSearchTestField() {
        searchKeyword = Util.getARandomSearchKeyword();
        Hooks.searchPage.getSearchTF().sendKeys(searchKeyword);
    }

    @And("user clicks on <Search> button")
    public void userClicksOnSearchButton() {
        Hooks.searchPage.searchBtn().click();
    }

    @Then("Search is done successfully")
    public void searchIsDoneSuccessfully() {
        // 1) Url is changed and contains the search keyword
        String expectedResult = Hooks.mainPageUrl + "search?q=" + searchKeyword;
        String actualResult = Hooks.driver.getCurrentUrl();
        Assert.assertEquals(actualResult,expectedResult);
        // 2) "Search" title is shown
        expectedResult = "Search";
        actualResult = Hooks.searchPage.searchHeaderLocator().getText();
        Assert.assertTrue(actualResult.contains(expectedResult));
        // Increase the number of passed scenarios by one
        finishStepDefinition.numberOfPassedScenarios++;
    }
}
