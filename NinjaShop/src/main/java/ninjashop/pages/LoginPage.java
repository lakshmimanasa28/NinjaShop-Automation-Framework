package ninjashop.pages;

import ninjashop.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private By emailInput = By.id("input-email");
    private By passwordInput = By.id("input-password");
    private By loginButton = By.cssSelector("input[value='Login']");
    private By warningMessage = By.cssSelector(".alert.alert-danger");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String email, String password) {
        sendKeys(emailInput, email);
        sendKeys(passwordInput, password);
        click(loginButton);
    }

    public String getWarningMessage() {
        return getText(warningMessage);
    }

    
    public boolean isLoggedInSuccessfully() {
        try {
          
            click(By.xpath("//a[@title='My Account' or contains(@class,'dropdown-toggle')]"));

            
            boolean logoutVisible = isDisplayed(By.linkText("Logout"));
            boolean editAccountVisible = isDisplayed(By.linkText("Edit Account"));

           
            click(By.xpath("//a[@title='My Account' or contains(@class,'dropdown-toggle')]"));

            return logoutVisible || editAccountVisible;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLoginPageDisplayed() {
        return isDisplayed(emailInput);
    }
}