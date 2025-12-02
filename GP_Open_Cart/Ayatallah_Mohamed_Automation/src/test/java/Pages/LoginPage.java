
package Pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class LoginPage {
    WebDriver driver;
    WebDriverWait wait;
    String baseURL = "https://automationexercise.com/login";
    By login_title = By.xpath("//h2[text()= 'Login to your account']");
    By email_field = By.xpath("//input[@data-qa='login-email']");
    By password_field = By.xpath("//input[@data-qa='login-password']");
    By login_btn = By.xpath("//button[@data-qa='login-button']");
    By login_error_msg = By.xpath("//p[contains(text(),'Your email or password')]");
    By nameField = By.name("name");
    By emailField = By.xpath("//input[@data-qa='signup-email']");
    By signupBtn = By.xpath("//button[@data-qa='signup-button']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5L));
    }

    public void login(String email, String password) {
        this.driver.findElement(this.email_field).sendKeys(new CharSequence[]{email});
        this.driver.findElement(this.password_field).sendKeys(new CharSequence[]{password});
        this.driver.findElement(this.login_btn).click();
    }

    public void signup(String name, String email) {
        this.driver.findElement(this.nameField).sendKeys(new CharSequence[]{name});
        this.driver.findElement(this.emailField).sendKeys(new CharSequence[]{email});
        this.driver.findElement(this.signupBtn).click();
    }

    public void assertLoginTitle() {
        Assert.assertTrue(this.driver.findElement(this.login_title).isDisplayed());
        Assert.assertEquals(this.driver.findElement(this.login_title).getText(), "Login to your account");
    }

    public void verifyErrorMsg() {
        Assert.assertTrue(this.driver.findElement(this.login_error_msg).isDisplayed());
        Assert.assertEquals(this.driver.findElement(this.login_error_msg).getText(), "Your email or password is incorrect!");
    }

    public void assertNavigationToLogin() {
        this.wait.until(ExpectedConditions.urlToBe(this.baseURL));
        Assert.assertEquals(this.driver.getCurrentUrl(), this.baseURL);
    }
}
