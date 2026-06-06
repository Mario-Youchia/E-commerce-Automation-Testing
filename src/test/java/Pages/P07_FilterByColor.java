package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import stepDefinitions.Hooks;

import java.util.Random;

public class P07_FilterByColor {
    final String colorCheckboxCSS = "li[class*=\"item color-item\"]:nth-of-type(4)>input";
    final String dataOptionIdAttribute = "data-option-id";

    public WebElement getCheckboxElement() {
        Hooks.indexOfColorCheckbox = new Random().nextInt(3) + 2;
        String cssSelector = colorCheckboxCSS.replace("4",Integer.toString(Hooks.indexOfColorCheckbox));
        return Hooks.driver.findElement(By.cssSelector(cssSelector));
    }

    public WebElement getSelectedCheckbox() {
        return Hooks.driver.findElement(By.cssSelector(colorCheckboxCSS.replace("4",Integer.toString(Hooks.indexOfColorCheckbox))));
    }

    public String getDataOptionID() {
        return Hooks.driver.findElement(By.cssSelector(colorCheckboxCSS.replace("4",Integer.toString(Hooks.indexOfColorCheckbox)))).getAttribute(dataOptionIdAttribute);
    }
}
