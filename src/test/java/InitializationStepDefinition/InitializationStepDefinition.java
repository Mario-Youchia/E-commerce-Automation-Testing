package InitializationStepDefinition;

import Pages.P06_SelectCategory;
import Utilities.Util;
import Utilities.finishStepDefinition;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import stepDefinitions.Hooks;

import java.util.concurrent.TimeUnit;

public class InitializationStepDefinition {
    @Before
    public void before() {
        System.out.println("BEFORE InitializationStepDefinition");

        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        Hooks.driver = new ChromeDriver();
        Hooks.driver.manage().window().maximize();
        Hooks.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Hooks.driver.navigate().to(Hooks.mainPageUrl);
        Hooks.category = new P06_SelectCategory();
        Hooks.actions = new Actions(Hooks.driver);
        //finishStepDefinition.numberOfScenarios++;

        //Hooks.category = new P06_SelectCategory();
        //Hooks.ListOfAllCategories = Util.ListOfMainCategories();
        //Hooks.ListOfAllSubCategories = Util.ListOfSubCategories();
        //Hooks.ListOfAllCategories = Hooks.category.ListOfMainCategories();
        //Hooks.ListOfAllSubCategories = Hooks.category.ListOfSubCategories();
        //Hooks.actions = new Actions(Hooks.driver);
    }
    @When("Get all possible search keywords")
    public void getAllPossibleSearchKeywords() {

    }

    @And("Get all categories and subcategories in the website")
    public void getAllCategoriesAndSubcategoriesInTheWebsite() throws InterruptedException {
        //Hooks.ListOfAllCategories = Hooks.category.ListOfMainCategories();
        //Hooks.ListOfAllSubCategories = Hooks.category.ListOfSubCategories();
        Util.hoverToRandomCategory();
    }

    @After
    public void after() {
        Hooks.driver.quit();
    }
}
