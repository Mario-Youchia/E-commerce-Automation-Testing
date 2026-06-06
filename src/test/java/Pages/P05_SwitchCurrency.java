package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import stepDefinitions.Hooks;

public class P05_SwitchCurrency {
    final String CurrencyDropDownMenuId = "customerCurrency";
    final String SelectedCurrencyCSS = "option[selected][value^=\"https://\"]";

    public WebElement CurrencyDropdown() {
        return Hooks.driver.findElement(By.id(CurrencyDropDownMenuId));
    }
    public WebElement SelectedCurrency() {
        return Hooks.driver.findElement(By.cssSelector(SelectedCurrencyCSS));
    }
}
