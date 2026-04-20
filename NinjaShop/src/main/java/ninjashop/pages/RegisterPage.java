package ninjashop.pages;

import ninjashop.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage {

  
    private By firstName = By.id("input-firstname");
    private By lastName = By.id("input-lastname");
    private By email = By.id("input-email");
    private By telephone = By.id("input-telephone");
    private By password = By.id("input-password");
    private By confirmPassword = By.id("input-confirm");
    private By agreeCheckbox = By.name("agree");
    private By continueButton = By.cssSelector("input[type='submit']");
    private By successHeading = By.cssSelector("#content h1");
    private By errorMessages = By.cssSelector(".text-danger");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public void register(String fname, String lname, String mail, String phone, String pwd) {
        sendKeys(firstName, fname);
        sendKeys(lastName, lname);
        sendKeys(email, mail);
        sendKeys(telephone, phone);
        sendKeys(password, pwd);
        sendKeys(confirmPassword, pwd);
        click(agreeCheckbox);
        click(continueButton);
    }

    public String getSuccessMessage() {
        return getText(successHeading);
    }

    public boolean isRegistrationSuccessful() {
        return getSuccessMessage().contains("Your Account Has Been Created");
    }

    public boolean isErrorMessageDisplayed() {
        By errorLocator = By.cssSelector(".text-danger");
        return isDisplayed(errorLocator);
    }
}