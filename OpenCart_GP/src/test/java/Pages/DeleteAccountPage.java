package Pages;

import Tests.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class DeleteAccountPage extends TestBase {

    WebDriver driver;

    public DeleteAccountPage(WebDriver driver) {
        this.driver = driver;
    }


    //Locators
    By deleteAccount_title = By.xpath("//h2[@data-qa='account-deleted']");

    //Actions

    //Assertion
    public void assertAccountDeletedMsg()
    {
        Assert.assertTrue(driver.findElement(deleteAccount_title).isDisplayed());
        Assert.assertEquals(driver.findElement(deleteAccount_title).getText(), "ACCOUNT DELETED!");
    }

}
