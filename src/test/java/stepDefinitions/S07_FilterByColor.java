package stepDefinitions;

import Utilities.finishStepDefinition;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;


public class S07_FilterByColor {
    @Given("user hovers on <Apparel> category")
    public void userHoversOnApparelCategory() {
        Hooks.indexOfCategory = 2;
        WebElement category = Hooks.driver.findElement(By.cssSelector(Hooks.category.getMainCategoryCSS()));
        Hooks.actions.moveToElement(category);
    }

    @And("user chooses <shoes> sub-category")
    public void userChoosesShoesSubCategory() {
        Hooks.indexOfSubCategory = 0;
        WebElement subCategory = Hooks.driver.findElement(By.cssSelector(Hooks.category.getSubCategoryCSS()));
        Hooks.CategoryURL = subCategory.getAttribute("href");
        Hooks.actions.moveToElement(subCategory);
        Hooks.actions.click().build().perform();
    }

    @And("user chooses one of the three available colors")
    public void userChoosesOneOfTheThreeAvailableColors() {
        Hooks.filter.getCheckboxElement().click();
    }

    @Then("Filter by color is done successfully")
    public void filterByColorIsDoneSuccessfully() {
        // 1) Check if the checkbox chosen is selected or not
        boolean actualResult = Hooks.filter.getSelectedCheckbox().isSelected();
        Assert.assertTrue(actualResult);
        // 2) Check if the current url is the same as expected
        String expectedResult = Hooks.CategoryURL + "?viewmode=grid&orderby=0&pagesize=6&specs=" + Hooks.filter.getDataOptionID();
        String ActualResult = Hooks.driver.getCurrentUrl();
        Assert.assertEquals(ActualResult,expectedResult);
        // Increase the number of passed scenarios by one
        finishStepDefinition.numberOfPassedScenarios++;
    }
}
