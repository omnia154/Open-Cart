package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class DeleteAccountPage {
    WebDriver driver;
    By deleteAccount_title = By.xpath("//h2[@data-qa='account-deleted']");

    public DeleteAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public void assertAccountDeletedMsg() {
        Assert.assertTrue(this.driver.findElement(this.deleteAccount_title).isDisplayed());
        Assert.assertEquals(this.driver.findElement(this.deleteAccount_title).getText(), "ACCOUNT DELETED!");
    }
}
