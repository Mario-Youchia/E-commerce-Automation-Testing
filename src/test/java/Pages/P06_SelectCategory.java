package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.Random;

import stepDefinitions.Hooks;

public class P06_SelectCategory {
    final String productSelectorsCSS = "div[class*=\"product-selectors\"]";
    final String mainCategoriesCSS = "ul[class*=\"top-menu notmobile\"]>li:nth-of-type(%)>a";
    final String subCategoriesCSS = "ul[class*=\"top-menu notmobile\"]>li:nth-of-type(@)>ul[class*=\"sublist first-level\"]>li:nth-of-type(#)>a";

    public WebElement productSelectors() {
        return Hooks.driver.findElement(By.cssSelector(productSelectorsCSS));
    }

    public String getMainCategoryCSS() {
        return mainCategoriesCSS.replace("%",Integer.toString(Hooks.indicesOfCategory[Hooks.indexOfCategory]));
    }

    public String getSubCategoryCSS() {
        return subCategoriesCSS.replace("@",Integer.toString(Hooks.indicesOfCategory[Hooks.indexOfCategory])).replace("#",Integer.toString(Hooks.indicesOfSubCategory[Hooks.indexOfCategory][Hooks.indexOfSubCategory]));
    }
}
