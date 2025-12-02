
package Pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PaymentPage {
    WebDriver driver;
    WebDriverWait wait;
    By nameOnCard = By.name("name_on_card");
    By cardNumber = By.name("card_number");
    By cvc = By.name("cvc");
    By expiryMonth = By.name("expiry_month");
    By expiryYear = By.name("expiry_year");
    By payAndConfirmBtn = By.id("submit");
    By successMessage = By.xpath("//div[text()='Your order has been placed successfully!']");
    By deleteAccount_btn = By.xpath("//a[@href=\"/delete_account\"]");

    public PaymentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10L));
    }

    public void cardDetails(String name, String card, String c_v_c, String expirationMonth, String expirationYear) {
        this.driver.findElement(this.nameOnCard).sendKeys(new CharSequence[]{name});
        this.driver.findElement(this.cardNumber).sendKeys(new CharSequence[]{card});
        this.driver.findElement(this.cvc).sendKeys(new CharSequence[]{c_v_c});
        this.driver.findElement(this.expiryMonth).sendKeys(new CharSequence[]{expirationMonth});
        this.driver.findElement(this.expiryYear).sendKeys(new CharSequence[]{expirationYear});
        this.driver.findElement(this.payAndConfirmBtn).click();
    }

    public void deleteAccount() {
        this.driver.findElement(this.deleteAccount_btn).click();
    }

    public void assertOrderMsg() {
        this.wait.until(ExpectedConditions.or(new ExpectedCondition[]{ExpectedConditions.visibilityOfElementLocated(this.successMessage), ExpectedConditions.urlContains("payment_done")}));
    }
}

