package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class HomePage {

    WebDriver driver;

    String baseURL = "https://automationexercise.com/";

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    //Locators

    By subscription_title = By.xpath("//h2[text()='Subscription']");
    By subscriptionEmail_txtbox = By.id("susbscribe_email");
    By subscriptionEmail_btn = By.id("subscribe");
    By successSubscription_msg = By.xpath("//div[@class=\"alert-success alert\"]");
    By cart_btn = By.xpath("(//a[@href=\"/view_cart\"] )[1]");
    By signup_login_btn = By.xpath("//a[@href=\"/login\"] ");
    By loggedInText = By.xpath("//a[contains(text(),'Logged in as')]");
    By deleteAccount_btn = By.xpath("//a[@href=\"/delete_account\"]");
    By logout_btn = By.xpath("//a[@href=\"/logout\"]");
    By product1 = By.xpath("(//a[@data-product-id='1'])[1]");

    //Actions

    public void navigate()
    {
        driver.get(baseURL);
    }

    public void enterSubscriptionEmail(String email)
    {
        driver.findElement(subscriptionEmail_txtbox).sendKeys(email);
        driver.findElement(subscriptionEmail_btn).click();
    }

    public void clickOnCartBtn()
    {
        driver.findElement(cart_btn).click();
    }

    public void clickOnLoginBtn()
    {
        driver.findElement(signup_login_btn).click();
    }

    public void deleteAccount()
    {
        driver.findElement(deleteAccount_btn).click();
    }

    public void logout(){
        driver.findElement(logout_btn).click();
    }

    public void addProduct(){
        driver.findElement(product1).click();
    }


    //Assertion

    public void assertNavigationToHomePage()
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

    public void assertUserIsLoggedIN(String username)
    {
        Assert.assertTrue(driver.findElement(loggedInText).isDisplayed());
        Assert.assertEquals(driver.findElement(loggedInText).getText(), "Logged in as " + username);
    }

}
