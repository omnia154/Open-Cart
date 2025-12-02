
package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignUpPage {
    WebDriver driver;
    By titleMr = By.id("id_gender1");
    By password = By.id("password");
    By firstName = By.id("first_name");
    By lastName = By.id("last_name");
    By address1 = By.id("address1");
    By state = By.id("state");
    By city = By.id("city");
    By zipcode = By.id("zipcode");
    By mobile = By.id("mobile_number");
    By createAccountBtn = By.xpath("//button[@data-qa='create-account']");

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillAccountDetails(String pass, String fname, String lname, String addr, String st, String cty, String zip, String mob) {
        this.driver.findElement(this.titleMr).click();
        this.driver.findElement(this.password).sendKeys(new CharSequence[]{pass});
        this.driver.findElement(this.firstName).sendKeys(new CharSequence[]{fname});
        this.driver.findElement(this.lastName).sendKeys(new CharSequence[]{lname});
        this.driver.findElement(this.address1).sendKeys(new CharSequence[]{addr});
        this.driver.findElement(this.state).sendKeys(new CharSequence[]{st});
        this.driver.findElement(this.city).sendKeys(new CharSequence[]{cty});
        this.driver.findElement(this.zipcode).sendKeys(new CharSequence[]{zip});
        this.driver.findElement(this.mobile).sendKeys(new CharSequence[]{mob});
    }

    public void clickCreateAccount() {
        this.driver.findElement(this.createAccountBtn).click();
    }
}

