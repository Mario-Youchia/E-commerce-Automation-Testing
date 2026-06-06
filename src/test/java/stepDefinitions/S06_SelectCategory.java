package stepDefinitions;

import Utilities.Util;
import Utilities.finishStepDefinition;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class S06_SelectCategory {
    @Given("user hovers on a category in homepage")
    public void userHoversOnACategoryInHomepage() {
        Util.hoverToRandomCategory();
    }
    @And("user selects one of the sub-categories or the category itself if there are no sub-categories")
    public void userSelectsOneOfTheSubCategoriesOrTheCategoryItselfIfThereAreNoSubCategories() {
        Hooks.actions.click().build().perform();
    }

    @Then("Select Category is done successfully")
    public void selectCategoryIsDoneSuccessfully() {
        // 1) Url is changed to the category
        String expectedResult = Hooks.CategoryURL;
        String actualResult = Hooks.driver.getCurrentUrl();
        Assert.assertEquals(actualResult,expectedResult);
        // 2) product selectors options are displayed
        Assert.assertTrue(Hooks.category.productSelectors().isDisplayed());
        // Increase the number of passed scenarios by one
        finishStepDefinition.numberOfPassedScenarios++;
    }
}
