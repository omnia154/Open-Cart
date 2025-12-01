package Pages;

import Tests.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignUpPage extends TestBase {

    WebDriver driver;

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
    }

    //locators

    By titleMr = By.id("id_gender1");
    By password = By.id("password");

    By firstName = By.id("first_name");
    By lastName = By.id("last_name");
    By address1 = By.id("address1");
    By country = By.id("country");
    By state = By.id("state");
    By city = By.id("city");
    By zipcode = By.id("zipcode");
    By mobile = By.id("mobile_number");

    By createAccountBtn = By.xpath("//button[@data-qa='create-account']");

    public void fillAccountDetails(String pass, String fname, String lname, String addr, String st,
                                   String cty, String zip, String mob) {

        driver.findElement(password).sendKeys(pass);
        driver.findElement(firstName).sendKeys(fname);
        driver.findElement(lastName).sendKeys(lname);
        driver.findElement(address1).sendKeys(addr);
        driver.findElement(state).sendKeys(st);
        driver.findElement(city).sendKeys(cty);
        driver.findElement(zipcode).sendKeys(zip);
        driver.findElement(mobile).sendKeys(mob);

    }

    public void clickCreateAccount() {
        driver.findElement(createAccountBtn).click();
    }


}
