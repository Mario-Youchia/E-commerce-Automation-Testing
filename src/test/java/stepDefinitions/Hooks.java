package stepDefinitions;

import Pages.*;
import Utilities.Util;
import Utilities.finishStepDefinition;
import io.cucumber.java.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Hooks {
    public static WebDriver driver;
    public static final String mainPageUrl = "https://demo.nopcommerce.com/";
    private static final int lengthOfFirstName = 7;
    public static final String firstName = Util.generateName(lengthOfFirstName);
    private static final int lengthOfLastName = 6;
    public static final String lastName = Util.generateName(lengthOfLastName);
    public static final int lengthOfEmailUsername = 20;
    public static final int lengthOfEmailDomain = 15;
    public static final String email = Util.generateEmail(lengthOfEmailUsername, lengthOfEmailDomain);
    public static final int lengthOfPasswordWithoutSpecialChar = 20;
    public static final String passwordSpecialChar = "*/)(@#$%";
    public static final String password = Util.generateName(lengthOfPasswordWithoutSpecialChar) + passwordSpecialChar;
    public static P01_Registration registerPage;
    public static P02_Login loginPage;
    public static P03_ResetPassword resetPage;
    public static S02_Login login;
    public static P04_Search searchPage;
    public static List<String> allPossibleSearchKeywords;
    public static P05_SwitchCurrency currency;
    public static final String[] currencies = {"US Dollar", "Euro"};
    public static P06_SelectCategory category;
    public static final int[] indicesOfCategory = {1, 2, 3, 4, 5, 6, 7};
    public static final int[][] indicesOfSubCategory = {
            {1, 2, 3},
            {1, 2, 3},
            {1, 2, 3},
            {},
            {},
            {},
            {}
    };
    public static Actions actions;
    public static int indexOfCategory;
    public static int indexOfSubCategory = -1;
    public static String CategoryURL;
    public static P07_FilterByColor filter;
    public static int indexOfColorCheckbox = -1;
    public static S06_SelectCategory categoryStepDef;
    public static P08_SelectTag tag;
    public static P09_AddProductsToShoppingCart shoppingCart;
    public static String[] URLsOfAllCategoriesAndSubcategories;
    public static boolean isProductOutOfStock = false;

    @BeforeAll
    public static void getAllSearchKeywords() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(new ChromeOptions().addArguments("headless"));
        searchPage = new P04_Search();
        driver.navigate().to(mainPageUrl);
        allPossibleSearchKeywords = Util.getAllPossibleSearchKeywords();
        driver.quit();
    }


    @Before
    static public void userOpenChromeBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.navigate().to(mainPageUrl);
        finishStepDefinition.numberOfScenarios++;
        registerPage = new P01_Registration();
        loginPage = new P02_Login();
        resetPage = new P03_ResetPassword();
        login = new S02_Login();
        searchPage = new P04_Search();
        currency = new P05_SwitchCurrency();
        category = new P06_SelectCategory();
        actions = new Actions(driver);
        filter = new P07_FilterByColor();
        categoryStepDef = new S06_SelectCategory();
        tag = new P08_SelectTag();
        shoppingCart = new P09_AddProductsToShoppingCart();
    }

    @AfterStep
    public static void takeScreenshot(Scenario scenario) {
        final byte[] screenshot = ((TakesScreenshot) Hooks.driver).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", "image");
    }

    @After
    public void userCloseDriver() {
        driver.quit();
        finishStepDefinition.numberOfScenariosDone++;
    }
}
