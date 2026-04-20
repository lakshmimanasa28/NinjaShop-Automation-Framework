package ninjashop.tests;

import ninjashop.base.BaseTest;
import ninjashop.pages.CartPage;
import ninjashop.pages.HomePage;
import ninjashop.pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    @Test
    public void verifyAddToCart() {
        HomePage home = new HomePage(driver);
        home.searchProduct("iMac");

        ProductPage product = new ProductPage(driver);
        product.clickFirstProduct();
        product.addToCart();

        CartPage cart = new CartPage(driver);
        cart.goToCart();

        Assert.assertTrue(cart.isProductInCart("iMac"), "Product should be added to cart");
    }

    @Test
    public void verifyUpdateCartQuantity() {
        HomePage home = new HomePage(driver);
        home.searchProduct("iMac");

        ProductPage product = new ProductPage(driver);
        product.clickFirstProduct();
        product.addToCart();

        CartPage cart = new CartPage(driver);
        cart.goToCart();
        cart.updateQuantity("iMac", 1);

        Assert.assertTrue(cart.isProductInCart("iMac"), "Product should remain in cart after update");
    }

    @Test
    public void verifyRemoveFromCart() {
        HomePage home = new HomePage(driver);
        home.searchProduct("iMac");

        ProductPage product = new ProductPage(driver);
        product.clickFirstProduct();
        product.addToCart();

        CartPage cart = new CartPage(driver);
        cart.goToCart();
        cart.removeProduct("iMac");

        Assert.assertTrue(cart.isCartEmpty(), "Cart should be empty after removal");
    }
}