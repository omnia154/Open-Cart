package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CartPage {

    WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    String baseURL = "https://automationexercise.com/view_cart";


    //Locators

    By subscription_title = By.xpath("//h2[text()='Subscription']");
    By subscriptionEmail_txtbox = By.id("susbscribe_email");
    By subscriptionEmail_btn = By.id("subscribe");
    By successSubscription_msg = By.xpath("//div[@class=\"alert-success alert\"]");
    By product1 = By.xpath("//a[text()='Proceed To Checkout']");


    //Actions

    public void enterSubscriptionEmail(String email)
    {
        driver.findElement(subscriptionEmail_txtbox).sendKeys(email);
        driver.findElement(subscriptionEmail_btn).click();
    }

    public void clickProceedToCheckout()
    {
        driver.findElement(product1).click();
    }


    //Assertion

    public void assertNavigationToCartPage()
    {
        Assert.assertEquals(driver.getCurrentUrl(), baseURL);
    }


    public void assertSubscriptionTitle()
    {
        Assert.assertTrue(driver.findElement(subscription_title).isDisplayed());
        Assert.assertEquals(driver.findElement(subscription_title).getText(), "SUBSCRIPTION");
    }

    public void assertSuccessfulSubscription()
    {
        Assert.assertTrue(driver.findElement(successSubscription_msg).isDisplayed());
        Assert.assertEquals(driver.findElement(successSubscription_msg).getText(), "You have been successfully subscribed!", "[ERROR] Displayed message is incorrect");
    }



}
