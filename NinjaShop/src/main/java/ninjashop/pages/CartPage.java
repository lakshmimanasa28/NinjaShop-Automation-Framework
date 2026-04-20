package ninjashop.pages;

import ninjashop.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

    private By shoppingCartLink = By.linkText("Shopping Cart");
    private By checkoutButton = By.linkText("Checkout");
    private By emptyCartMessage = By.xpath("//p[contains(text(),'Your shopping cart is empty!')]");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void goToCart() {
        click(shoppingCartLink);
    }

    

    public void clickCheckout() {
        click(checkoutButton);

        wait.until(driver -> driver.getCurrentUrl().contains("route=checkout/checkout"));

        if (!driver.getCurrentUrl().contains("checkout/checkout")) {
            driver.get("https://tutorialsninja.com/demo/index.php?route=checkout/checkout");
        }
    }
    public void updateQuantity(String productName, int qty) {
        By qtyField = By.xpath("//a[text()='" + productName + "']/ancestor::tr//input");
        driver.findElement(qtyField).clear();
        driver.findElement(qtyField).sendKeys(String.valueOf(qty));

        By updateBtn = By.xpath("//a[text()='" + productName + "']/ancestor::tr//button[@data-original-title='Update']");
        click(updateBtn);
    }

    public void removeProduct(String productName) {
        By removeBtn = By.xpath("//a[text()='" + productName + "']/ancestor::tr//button[@data-original-title='Remove']");
        click(removeBtn);

        // WAIT until product disappears
        By productRow = By.xpath("//a[text()='" + productName + "']");
        wait.until(driver -> driver.findElements(productRow).isEmpty());
    }

    public boolean isProductInCart(String productName) {
        By locator = By.xpath("//a[text()='" + productName + "']");
        return driver.findElements(locator).size() > 0;
    }

    public boolean isCartEmpty() {
        return driver.findElements(emptyCartMessage).size() > 0;
    }
}