package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import stepDefinitions.Hooks;

public class P02_Login {

    public P02_Login() {
        PageFactory.initElements(Hooks.driver, this);
    }

    @FindBy(className = loginHLClassName)
    WebElement loginHL;

    final String loginHLClassName = "ico-login";
    final String EmailId = "Email";
    final String PasswordId = "Password";
    final String loginButtonClassName = "login-button";
    final String PasswordClassName = "ico-account";

    public WebElement loginHL() {
        return loginHL;
    }
    public WebElement emailTF() {
        return Hooks.driver.findElement(By.id(EmailId));
    }
    public WebElement passwordTF() {
        return Hooks.driver.findElement(By.id(PasswordId));
    }
    public WebElement loginBtn() {
        return Hooks.driver.findElement(By.className(loginButtonClassName));
    }
    public WebElement MyAccountHL() {
        return Hooks.driver.findElement(By.className(PasswordClassName));
    }
}
