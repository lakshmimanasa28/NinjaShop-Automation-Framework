package ninjashop.pages;

import ninjashop.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage extends BasePage {

    private By firstProductLink = By.cssSelector(".product-layout .caption h4 a");
    private By productNameHeader = By.tagName("h1");
    
    private By addToCartBtn = By.id("button-cart");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void clickFirstProduct() {
        click(firstProductLink);
    }

    public void addToCart() {
        click(addToCartBtn);
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.cssSelector(".alert-success")
        ));
    }

    public boolean isProductNameDisplayed() {
        return isDisplayed(productNameHeader);
    }

    
    public boolean isProductPriceDisplayed() {
       
        By[] locators = {
            By.cssSelector(".price"),
            By.cssSelector(".price-tax"),
            By.xpath("//div[contains(@class,'price')]"),
            By.xpath("//li[contains(text(),'Price')]"),
            By.xpath("//h2[contains(text(),'$')]")   
        };

        for (By locator : locators) {
            if (isDisplayed(locator)) {
                return true;
            }
        }
        return false;
    }

    public boolean isProductDisplayed(String productNameText) {
        By locator = By.xpath("//h4/a[contains(text(),'" + productNameText + "')]");
        return isDisplayed(locator);
    }

    public boolean isNoResultsMessageDisplayed() {
        By noResult = By.xpath("//p[contains(text(),'There is no product that matches the search criteria')]");
        return isDisplayed(noResult);
    }
}