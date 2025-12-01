package Pages;

import Tests.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CheckoutPage extends TestBase {

    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    //Locators
    By addressDetails = By.xpath("(//div[@class=\"step-one\"])[1]");
    By reviewYourOrder = By.xpath("(//div[@class=\"step-one\"])[2]");
    By description_textarea = By.name("message");
    By placeOrder_btn = By.xpath("//a[@href=\"/payment\"]");

    //Actions

    public void writeDescriptionComment()
    {
        driver.findElement(description_textarea).sendKeys("description");
    }

    public void clickPlaceOrder()
    {
        driver.findElement(placeOrder_btn).click();
    }


    //Assertion
    public void verifyAddressDetails()
    {
        Assert.assertTrue(driver.findElement(addressDetails).isDisplayed());
    }

    public void verifyReviewYourOrder()
    {
        Assert.assertTrue(driver.findElement(reviewYourOrder).isDisplayed());
    }




}
