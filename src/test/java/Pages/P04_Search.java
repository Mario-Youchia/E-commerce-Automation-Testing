package Pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import stepDefinitions.Hooks;

public class P04_Search {

    final String rootOfMenuCategoriesXPath = "//div[6]/div[2]/ul[1]";
    final int numberOfMainCategories = 7;
    final int numberOfSubcategories = 3;
    final String attributeOfURLOfCategories = "href";
    final String tagContainingURLOfCategory = "/a";
    final String productTitleClassCSS = "h2[class=\"product-title\"]";
    final int minimumNumberOfCharForSearch = 3;
    final String searchTFid = "small-searchterms";
    final String searchButtonCSS = "button[type=\"submit\"]";
    final String searchHeaderCSS = "div[class=\"page-title\"]";

    public String getRootOfMenuCategoriesXPath() {
        return rootOfMenuCategoriesXPath;
    }

    public int getNumberOfMainCategories() {
        return numberOfMainCategories;
    }

    public int getNumberOfSubcategories() {
        return numberOfSubcategories;
    }

    public String getAttributeOfURLOfCategories() {
        return attributeOfURLOfCategories;
    }

    public String getTagContainingURLOfCategory() {
        return tagContainingURLOfCategory;
    }

    public String getProductTitleClassCSS() {
        return productTitleClassCSS;
    }

    public int getMinimumNumberOfCharForSearch() {
        return minimumNumberOfCharForSearch;
    }

    public WebElement getSearchTF() {
        return Hooks.driver.findElement(By.id(searchTFid));
    }

    public WebElement searchBtn() {
        return Hooks.driver.findElement(By.cssSelector(searchButtonCSS));
    }

    public WebElement searchHeaderLocator() {
        return Hooks.driver.findElement(By.cssSelector(searchHeaderCSS));
    }
}
