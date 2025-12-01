package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;


public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    String baseURL = "https://automationexercise.com/login";

    //Locators

    By login_title = By.xpath("//h2[text()= 'Login to your account']");
    By email_field = By.xpath("//input[@data-qa='login-email']");
    By password_field = By.xpath("//input[@data-qa='login-password']");
    By login_btn = By.xpath("//button[@data-qa='login-button']");
    By login_error_msg = By.xpath("//p[contains(text(),'Your email or password')]");

    By nameField = By.name("name");
    By emailField = By.xpath("//input[@data-qa='signup-email']");
    By signupBtn = By.xpath("//button[@data-qa='signup-button']");


    //Actions

    public void login(String email, String password)
    {
        driver.findElement(email_field).sendKeys(email);
        driver.findElement(password_field).sendKeys(password);
        driver.findElement(login_btn).click();
    }

    public void signup(String name, String email)
    {
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(signupBtn).click();
    }

    //Assertion
    public void assertLoginTitle()
    {
        Assert.assertTrue(driver.findElement(login_title).isDisplayed());
        Assert.assertEquals(driver.findElement(login_title).getText(), "Login to your account");
    }

    public void verifyErrorMsg()
    {
        Assert.assertTrue(driver.findElement(login_error_msg).isDisplayed());
        Assert.assertEquals(driver.findElement(login_error_msg).getText(), "Your email or password is incorrect!");
    }

    public void assertNavigationToLogin()
    {
        wait.until(ExpectedConditions.urlToBe(baseURL));
        Assert.assertEquals(driver.getCurrentUrl(), baseURL);
    }
}
