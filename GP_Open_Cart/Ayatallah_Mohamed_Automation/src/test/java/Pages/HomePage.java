package Pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class HomePage {
    WebDriver driver;
    WebDriverWait wait;
    String baseURL = "https://automationexercise.com/";
    By subscription_title = By.xpath("//h2[text()='Subscription']");
    By subscriptionEmail_txtbox = By.id("susbscribe_email");
    By subscriptionEmail_btn = By.id("subscribe");
    By successSubscription_msg = By.xpath("//div[@class=\"alert-success alert\"]");
    By cart_btn = By.xpath("(//a[@href=\"/view_cart\"] )[1]");
    By signup_login_btn = By.xpath("//a[@href=\"/login\"] ");
    By loggedInText = By.xpath("//a[contains(text(),'Logged in as')]");
    By deleteAccount_btn = By.xpath("//a[@href=\"/delete_account\"]");
    By logout_btn = By.xpath("//a[@href=\"/logout\"]");
    By product1 = By.xpath("(//a[@data-product-id=\"1\"])[2]");
    By product_img = By.xpath("//img[@src=\"/get_product_picture/1\"]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5L));
    }

    public void navigate() {
        this.driver.get(this.baseURL);
    }

    public void enterSubscriptionEmail(String email) {
        this.driver.findElement(this.subscriptionEmail_txtbox).sendKeys(new CharSequence[]{email});
        this.driver.findElement(this.subscriptionEmail_btn).click();
    }

    public void clickOnCartBtn() {
        this.driver.findElement(this.cart_btn).click();
    }

    public void clickOnLoginBtn() {
        this.driver.findElement(this.signup_login_btn).click();
    }

    public void deleteAccount() {
        this.driver.findElement(this.deleteAccount_btn).click();
    }

    public void logout() {
        this.driver.findElement(this.logout_btn).click();
    }

    public void addProduct() {
        Actions actions = new Actions(this.driver);
        actions.moveToElement(this.driver.findElement(this.product_img)).perform();
        this.wait.until(ExpectedConditions.elementToBeClickable(this.driver.findElement(this.product1)));
        this.driver.findElement(this.product1).click();
    }

    public void assertNavigationToHomePage() {
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

    public void assertUserIsLoggedIN(String username) {
        Assert.assertTrue(this.driver.findElement(this.loggedInText).isDisplayed());
        Assert.assertEquals(this.driver.findElement(this.loggedInText).getText(), "Logged in as " + username);
    }
}
