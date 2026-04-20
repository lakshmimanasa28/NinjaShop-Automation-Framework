package ninjashop.tests;

import ninjashop.base.BaseTest;
import ninjashop.pages.HomePage;
import ninjashop.pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductTest extends BaseTest {

    @Test
    public void verifyProductSearch() {
        HomePage homePage = new HomePage(driver);
        homePage.searchProduct("iPhone");

        ProductPage productPage = new ProductPage(driver);
        Assert.assertTrue(productPage.isProductDisplayed("iPhone"), "iPhone should appear in search results");
    }

    @Test
    public void verifyNonExistentProductSearch() {
        HomePage homePage = new HomePage(driver);
        homePage.searchProduct("xyzabc123nonexistent");

        ProductPage productPage = new ProductPage(driver);
        Assert.assertTrue(productPage.isNoResultsMessageDisplayed(),
                "No results message should be shown for nonexistent product");
    }

    @Test
    public void verifyProductDetails() {
        HomePage homePage = new HomePage(driver);
        homePage.searchProduct("MacBook");

        ProductPage productPage = new ProductPage(driver);
        productPage.clickFirstProduct();

        Assert.assertTrue(productPage.isProductNameDisplayed(), "Product name should be displayed");
        Assert.assertTrue(productPage.isProductPriceDisplayed(), "Product price should be displayed");
    }
}