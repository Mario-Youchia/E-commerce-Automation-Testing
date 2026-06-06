package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import stepDefinitions.Hooks;

import java.util.Random;

public class P08_SelectTag {
    final String tagCSS = "div[class*=\"tags\"]>ul>li:nth-of-type(1)>a";
    final int numberOfTagsInWebPage = 15;
    final String tagAttributeCSS = "href";
    final String pageTitleCSS = "div[class*=\"page-title\"]";
    public WebElement getATagElement() {
        return Hooks.driver.findElement(By.cssSelector(tagCSS.replace("1",Integer.toString(new Random().nextInt(numberOfTagsInWebPage) + 1))));
    }
    public String getTagAttributeCSS() {
        return tagAttributeCSS;
    }
    public WebElement getPageTitle() {
        return Hooks.driver.findElement(By.cssSelector(pageTitleCSS));
    }
}
