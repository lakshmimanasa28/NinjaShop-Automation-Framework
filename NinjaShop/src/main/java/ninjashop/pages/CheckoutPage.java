package ninjashop.pages;

import ninjashop.base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class CheckoutPage extends BasePage {

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }
    private By guestRadio = By.cssSelector("input[value='guest']");
    private By continueAccount = By.id("button-account");
    private By firstName = By.id("input-payment-firstname");
    private By lastName = By.id("input-payment-lastname");
    private By email = By.id("input-payment-email");
    private By telephone = By.id("input-payment-telephone");
    private By address1 = By.id("input-payment-address-1");
    private By city = By.id("input-payment-city");
    private By postcode = By.id("input-payment-postcode");
    private By country = By.id("input-payment-country");
    private By zone = By.id("input-payment-zone");

    private By sameAddressCheckbox = By.name("shipping_address");
    private By continueBilling = By.id("button-guest");
    private By continueDeliveryDetails = By.id("button-shipping-address");
    private By continueDeliveryMethod = By.id("button-shipping-method");
    private By termsCheckbox = By.cssSelector("#collapse-payment-method input[name='agree']");
    private By continuePayment = By.id("button-payment-method");
    private By confirmOrder = By.id("button-confirm");
    private By successMsg = By.xpath("//h1[contains(text(),'Your order has been placed!')]");


    // step 1
    public void selectGuestCheckout() {
        waitForElementVisible(guestRadio);

        WebElement guest = driver.findElement(guestRadio);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", guest);

        click(continueAccount);
    }

    public void fillBillingDetails() {

        waitForElementVisible(firstName);

        sendKeys(firstName, "Manasa");
        sendKeys(lastName, "Lakshmi");
        sendKeys(email, "test" + System.currentTimeMillis() + "@mail.com");
        sendKeys(telephone, "9876543210");
        sendKeys(address1, "Guntur");
        sendKeys(city, "Guntur");
        sendKeys(postcode, "522007");

        new Select(waitForElementVisible(country)).selectByVisibleText("India");
        wait.until(driver -> new Select(driver.findElement(zone)).getOptions().size() > 1);

        new Select(driver.findElement(zone)).selectByVisibleText("Andhra Pradesh");

        wait.until(ExpectedConditions.textToBePresentInElementLocated(zone, "Telangana"));

        WebElement same = driver.findElement(sameAddressCheckbox);
        if (!same.isSelected()) {
            same.click();
        }

        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.cssSelector(".fa-spinner, .loading")
        ));

        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(continueBilling));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", btn);

        try { Thread.sleep(500); } catch (Exception e) {}

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", btn);
        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.id("collapse-shipping-address")),
                ExpectedConditions.visibilityOfElementLocated(By.id("collapse-shipping-method"))
        
        ));
    }

    // step 3
    public void continueDeliveryDetails() {

      
        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.id("collapse-shipping-address")),
                ExpectedConditions.visibilityOfElementLocated(By.id("collapse-shipping-method"))
        ));

    
        if (driver.findElements(By.cssSelector("#collapse-shipping-address.in")).size() > 0) {

            By btnBy = By.id("button-shipping-address");

            WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(btnBy));

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block:'center'});", btn);

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();", btn);
        }

      
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("collapse-shipping-method")
        ));
    }
    //step 4
    public void continueDeliveryMethod() {

        By step4Panel = By.id("collapse-shipping-method");
        By btnBy = By.id("button-shipping-method");
        wait.until(ExpectedConditions.visibilityOfElementLocated(step4Panel));
        wait.until(ExpectedConditions.attributeContains(step4Panel, "class", "in"));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.cssSelector(".loading, .fa-spinner")
        ));
        WebElement btn = wait.until(driver -> {
            WebElement el = driver.findElement(btnBy);
            return (el.isDisplayed() && el.isEnabled()) ? el : null;
        });
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", btn
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", btn
        );
        wait.until(ExpectedConditions.attributeContains(
                By.id("collapse-payment-method"), "class", "in"
        ));
    }
    //step 5
    public void continuePaymentMethod() {
        By step5Panel = By.id("collapse-payment-method");
        By agreeBy = By.cssSelector("#collapse-payment-method input[name='agree']");
        By btnBy = By.id("button-payment-method");

        wait.until(ExpectedConditions.visibilityOfElementLocated(step5Panel));
        wait.until(ExpectedConditions.attributeContains(step5Panel, "class", "in"));

        WebElement agree = wait.until(ExpectedConditions.presenceOfElementLocated(agreeBy));
        if (!agree.isSelected()) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", agree);
        }

        WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(btnBy));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", btn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);

        wait.until(ExpectedConditions.attributeContains(By.id("collapse-checkout-confirm"), "class", "in"));
    }
    // step 6
    public void confirmOrder() {

        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(confirmOrder));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(true);", btn);

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", btn);
    }

    public boolean isOrderSuccess() {
        return isDisplayed(successMsg);
    }
}