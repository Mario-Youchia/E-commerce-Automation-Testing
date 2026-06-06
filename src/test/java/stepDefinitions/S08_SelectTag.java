package stepDefinitions;

import Utilities.finishStepDefinition;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class S08_SelectTag {
    String urlOfTag;

    @And("user selects a random category")
    public void userSelectsARandomCategory() {
        Hooks.categoryStepDef.userHoversOnACategoryInHomepage();
        Hooks.categoryStepDef.userSelectsOneOfTheSubCategoriesOrTheCategoryItselfIfThereAreNoSubCategories();
        Hooks.categoryStepDef.selectCategoryIsDoneSuccessfully();
        // Decreasing the number of passed scenarios by one to compensate the increasing in the previous line.
        finishStepDefinition.numberOfPassedScenarios--;
    }

    @Given("user chooses a tag from <Popular tags> section")
    public void userChoosesATagFromPopularTagsSection() {
        WebElement tagElement = Hooks.tag.getATagElement();
        urlOfTag = tagElement.getAttribute(Hooks.tag.getTagAttributeCSS());
        tagElement.click();
    }

    @Then("Select Tag is done successfully")
    public void selectTagIsDoneSuccessfully() {
        // 1) Check if the current url is the same as expected
        String expectedResult = urlOfTag;
        String actualResult = Hooks.driver.getCurrentUrl();
        Assert.assertEquals(actualResult,expectedResult);
        // 2) Check if the page title contains "Products tagged with"
        Assert.assertTrue(Hooks.tag.getPageTitle().getText().contains("Products tagged with '"));
        // Increase the number of passed scenarios by one
        finishStepDefinition.numberOfPassedScenarios++;
    }
}
