
package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CheckoutPage {
    WebDriver driver;
    By addressDetails = By.xpath("(//div[@class=\"step-one\"])[1]");
    By reviewYourOrder = By.xpath("(//div[@class=\"step-one\"])[2]");
    By description_textarea = By.name("message");
    By placeOrder_btn = By.xpath("//a[@href=\"/payment\"]");
    By deliveryUsername = By.xpath("(//li[@class=\"address_firstname address_lastname\"])[1]");
    By deliveryAddress = By.xpath("(//li[@class=\"address_address1 address_address2\"])[2]");
    By deliveryState = By.xpath("(//li[@class=\"address_city address_state_name address_postcode\"])[1]");
    By deliveryCity = By.xpath("(//li[@class=\"address_country_name\"])[1]");
    By deliveryMobile = By.xpath("(//li[@class=\"address_phone\"])[1]");
    By billingUsername = By.xpath("(//li[@class=\"address_firstname address_lastname\"])[2]");
    By billingaddress = By.xpath("(//li[@class=\"address_address1 address_address2\"])[5]");
    By billingstate = By.xpath("(//li[@class=\"address_city address_state_name address_postcode\"])[2]");
    By billingcity = By.xpath("(//li[@class=\"address_country_name\"])[2]");
    By billingmobile = By.xpath("(//li[@class=\"address_phone\"])[2]");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void writeDescriptionComment() {
        this.driver.findElement(this.description_textarea).sendKeys(new CharSequence[]{"description"});
    }

    public void clickPlaceOrder() {
        this.driver.findElement(this.placeOrder_btn).click();
    }

    public void verifyAddressDetails() {
        Assert.assertTrue(this.driver.findElement(this.addressDetails).isDisplayed());
    }

    public void verifyReviewYourOrder() {
        Assert.assertTrue(this.driver.findElement(this.reviewYourOrder).isDisplayed());
    }

    public void assertDeliveryAddress(String username, String add, String st, String cty, String mob) {
        Assert.assertEquals(this.driver.findElement(this.deliveryUsername).getText(), username);
        Assert.assertEquals(this.driver.findElement(this.deliveryAddress).getText(), add);
        Assert.assertEquals(this.driver.findElement(this.deliveryState).getText(), st);
        Assert.assertEquals(this.driver.findElement(this.deliveryCity).getText(), cty);
        Assert.assertEquals(this.driver.findElement(this.deliveryMobile).getText(), mob);
    }

    public void assertBillingAddress(String username, String add, String st, String cty, String mob) {
        Assert.assertEquals(this.driver.findElement(this.billingUsername).getText(), username);
        Assert.assertEquals(this.driver.findElement(this.billingaddress).getText(), add);
        Assert.assertEquals(this.driver.findElement(this.billingstate).getText(), st);
        Assert.assertEquals(this.driver.findElement(this.billingcity).getText(), cty);
        Assert.assertEquals(this.driver.findElement(this.billingmobile).getText(), mob);
    }
}

