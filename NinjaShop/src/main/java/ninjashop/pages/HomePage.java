package ninjashop.pages;

import ninjashop.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    private By myAccountLink = By.xpath("//a[@title='My Account']");
    private By registerLink = By.linkText("Register");
    private By loginLink = By.linkText("Login");
    private By searchBox = By.name("search");
    private By searchButton = By.cssSelector("button.btn-default");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void clickMyAccountLink() {
        click(myAccountLink);
    }

    public void clickRegisterLink() {
        click(registerLink);
    }

    public void clickLoginLink() {
        click(loginLink);
    }

   
    public void clickLogout() {
        try {
            clickMyAccountLink();                   
            click(By.linkText("Logout"));            
        } catch (Exception e) {
            
            driver.get("https://tutorialsninja.com/demo/index.php?route=account/logout");
        }
    }

    public void searchProduct(String productName) {
        sendKeys(searchBox, productName);
        click(searchButton);
    }

    public boolean isHomePageDisplayed() {
        return isDisplayed(searchBox);
    }
}