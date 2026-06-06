package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import stepDefinitions.Hooks;

public class P01_Registration {

    public P01_Registration() {
        PageFactory.initElements(Hooks.driver, this);
    }

    @FindBy(partialLinkText = registerHLPartialLT)
    WebElement registerHL;

    final String registerHLPartialLT = "Register";
    final String FirstNameId = "FirstName";
    final String LastNameId = "LastName";
    final String EmailId = "Email";
    final String PasswordId = "Password";
    final String ConfirmPasswordId = "ConfirmPassword";
    final String registerButtonId = "register-button";
    final String registrationMsgCSS = "div[class=\"result\"]";
    final String continueButtonClassName = "register-continue-button";

    public WebElement registerHL() {
        return registerHL;
    }
    public WebElement FirstNameTF() {
        return Hooks.driver.findElement(By.id(FirstNameId));
    }
    public WebElement LastNameTF() {
        return Hooks.driver.findElement(By.id(LastNameId));
    }
    public WebElement EmailTF() {
        return Hooks.driver.findElement(By.id(EmailId));
    }
    public WebElement passwordTF() {
        return Hooks.driver.findElement(By.id(PasswordId));
    }
    public WebElement confirmPasswordTF() {
        return Hooks.driver.findElement(By.id(ConfirmPasswordId));
    }
    public WebElement registerBtn() {
        return Hooks.driver.findElement(By.id(registerButtonId));
    }
    public WebElement registerMsg() {
        return Hooks.driver.findElement(By.cssSelector(registrationMsgCSS));
    }
    public WebElement continueBtn() {
        return Hooks.driver.findElement(By.className(continueButtonClassName));
    }
}