package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CartPage {
    WebDriver driver;
    String baseURL = "https://automationexercise.com/view_cart";
    By subscription_title = By.xpath("//h2[text()='Subscription']");
    By subscriptionEmail_txtbox = By.id("susbscribe_email");
    By subscriptionEmail_btn = By.id("subscribe");
    By successSubscription_msg = By.xpath("//div[@class=\"alert-success alert\"]");
    By product1 = By.xpath("//a[text()='Proceed To Checkout']");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterSubscriptionEmail(String email) {
        this.driver.findElement(this.subscriptionEmail_txtbox).sendKeys(new CharSequence[]{email});
        this.driver.findElement(this.subscriptionEmail_btn).click();
    }

    public void clickProceedToCheckout() {
        this.driver.findElement(this.product1).click();
    }

    public void assertNavigationToCartPage() {
        Assert.assertEquals(this.driver.getCurrentUrl(), this.baseURL);
    }

    public void assertSubscriptionTitle() {
        Assert.assertTrue(this.driver.findElement(this.subscription_title).isDisplayed());
        Assert.assertEquals(this.driver.findElement(this.subscription_title).getText(), "SUBSCRIPTION");
    }

    public void assertSuccessfulSubscription() {
        Assert.assertTrue(this.driver.findElement(this.successSubscription_msg).isDisplayed());
        Assert.assertEquals(this.driver.findElement(this.successSubscription_msg).getText(), "You have been successfully subscribed!", "[ERROR] Displayed message is incorrect");
    }
}
