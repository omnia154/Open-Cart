
package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountCreatedPage {
    WebDriver driver;
    By accountCreated_title = By.cssSelector("[data-qa='account-created']");
    By continue_btn = By.xpath("//a[@data-qa='continue-button']");

    public AccountCreatedPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickContinue() {
        this.driver.findElement(this.continue_btn).click();
    }

    public void assertAccountCreatedTitle() {
        this.driver.findElement(this.accountCreated_title).isDisplayed();
    }
}
