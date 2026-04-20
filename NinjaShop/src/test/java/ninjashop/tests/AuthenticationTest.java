package ninjashop.tests;

import ninjashop.base.BaseTest;
import ninjashop.dataprovider.LoginDataProvider;
import ninjashop.pages.HomePage;
import ninjashop.pages.LoginPage;
import ninjashop.pages.RegisterPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthenticationTest extends BaseTest {

    private String testEmail;   

    @Test(priority = 1)
    public void verifySuccessfulRegistration() {
        HomePage homePage = new HomePage(driver);
        homePage.clickMyAccountLink();
        homePage.clickRegisterLink();

        RegisterPage registerPage = new RegisterPage(driver);

       
        testEmail = "manasa" + System.currentTimeMillis() + "@test.com";

        registerPage.register("Manasa", "Lakshmi", testEmail, "9876543210", "Test@123");

        Assert.assertTrue(registerPage.isRegistrationSuccessful(),
                "Registration should be successful with new account");
    }

    @Test(priority = 2, dependsOnMethods = "verifySuccessfulRegistration")
    public void verifySuccessfulLogin() {
        HomePage homePage = new HomePage(driver);
        homePage.clickMyAccountLink();
        homePage.clickLoginLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(testEmail, "Test@123");

        Assert.assertTrue(loginPage.isLoggedInSuccessfully(),
                "Login should be successful with newly registered account");
    }

    @Test(priority = 3, dataProvider = "invalidLoginData", dataProviderClass = LoginDataProvider.class)
    public void verifyInvalidLoginScenarios(String email, String password, String expectedMessage) {
        HomePage homePage = new HomePage(driver);
        homePage.clickMyAccountLink();
        homePage.clickLoginLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(email, password);

        String actualMessage = loginPage.getWarningMessage();

        Assert.assertTrue(actualMessage.contains(expectedMessage),
                "Expected warning message not displayed. Actual: " + actualMessage);
    }

    @Test(priority = 4, dependsOnMethods = "verifySuccessfulLogin")
    public void verifyLogout() {
        HomePage homePage = new HomePage(driver);
        homePage.clickLogout();                      

        
        Assert.assertTrue(homePage.isHomePageDisplayed() || 
                          driver.getCurrentUrl().contains("account/login"),
                "Should redirect to home or login page after logout");
    }
}