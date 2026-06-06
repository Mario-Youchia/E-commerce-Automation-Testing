package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import stepDefinitions.Hooks;

public class P03_ResetPassword {
    final String forgotPasswordPartialLT = "Forgot password?";
    final String RecoveryButtonName = "send-email";
    final String confirmationMsgCSS = "p[class=\"content\"]";
    final String confirmationBarCSS = "div[class=\"bar-notification success\"]";


    public WebElement forgotPasswordHL() {
        return Hooks.driver.findElement(By.partialLinkText(forgotPasswordPartialLT));
    }
    public WebElement recoveryBtn() {
        return Hooks.driver.findElement(By.name(RecoveryButtonName));
    }
    public WebElement confirmationMsg() {
        return Hooks.driver.findElement(By.cssSelector(confirmationMsgCSS));
    }
    public WebElement confirmationBar() {
        return Hooks.driver.findElement(By.cssSelector(confirmationBarCSS));
    }
}