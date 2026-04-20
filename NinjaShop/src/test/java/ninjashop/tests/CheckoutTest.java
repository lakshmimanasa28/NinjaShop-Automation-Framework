package ninjashop.tests;

import ninjashop.base.BaseTest;
import ninjashop.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {

    @Test
    public void verifyCheckoutFlow() {

        HomePage home = new HomePage(driver);
        home.searchProduct("HP");

        ProductPage product = new ProductPage(driver);
        product.clickFirstProduct();
        product.addToCart();

        CartPage cart = new CartPage(driver);
        cart.goToCart();
        cart.clickCheckout();

        CheckoutPage checkout = new CheckoutPage(driver);

        checkout.selectGuestCheckout();
        checkout.fillBillingDetails();
        checkout.continueDeliveryDetails();
        checkout.continueDeliveryMethod();
        checkout.continuePaymentMethod();
        checkout.confirmOrder();
        Assert.assertTrue(checkout.isOrderSuccess(),
                "Order should be placed successfully");
    }
}