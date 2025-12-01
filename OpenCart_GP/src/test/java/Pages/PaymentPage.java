package Pages;

import Tests.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class PaymentPage extends TestBase {

    WebDriver driver;

    public PaymentPage(WebDriver driver) {
        this.driver = driver;
    }

    //locators
    By nameOnCard = By.name("name_on_card");
    By cardNumber = By.name("card_number");
    By cvc = By.name("cvc");
    By expiryMonth = By.name("expiry_month");
    By expiryYear = By.name("expiry_year");
    By payAndConfirmBtn = By.id("submit"); // fallback
    By successMessage = By.xpath("//div[text()='Your order has been placed successfully!']");
    By deleteAccount_btn = By.xpath("//a[@href=\"/delete_account\"]");

    //Actions
    public void cardDetails(String name, String card, String c_v_c, String expirationMonth, String expirationYear)
    {
        driver.findElement(nameOnCard).sendKeys(name);
        driver.findElement(cardNumber).sendKeys(card);
        driver.findElement(cvc).sendKeys(c_v_c);
        driver.findElement(expiryMonth).sendKeys(expirationMonth);
        driver.findElement(expiryYear).sendKeys(expirationYear);
        driver.findElement(payAndConfirmBtn).click();
    }

    public void deleteAccount()
    {
        driver.findElement(deleteAccount_btn).click();
    }


    //Assertion
    public void assertOrderMsg()
    {
        Assert.assertTrue(driver.findElement(successMessage).isDisplayed());
    }
}
