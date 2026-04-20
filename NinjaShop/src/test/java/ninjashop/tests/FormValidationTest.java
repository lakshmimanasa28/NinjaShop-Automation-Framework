package ninjashop.tests;

import ninjashop.base.BaseTest;
import ninjashop.pages.RegisterPage;
import ninjashop.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FormValidationTest extends BaseTest {

    @Test
    public void verifyRegistrationWithEmptyFields() {
        HomePage homePage = new HomePage(driver);
        homePage.clickMyAccountLink();
        homePage.clickRegisterLink();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.register("", "", "", "", "");   

        Assert.assertTrue(registerPage.isErrorMessageDisplayed(), "Error messages should appear for empty fields");
    }
}