
package Tests;

import Pages.AccountCreatedPage;
import Pages.CartPage;
import Pages.CheckoutPage;
import Pages.DeleteAccountPage;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.PaymentPage;
import Pages.SignUpPage;
import jdk.jfr.Description;
import org.testng.annotations.Test;

public class AddressDetailsTest extends TestBase {


    HomePage homePage;
    LoginPage loginPage;
    SignUpPage signUpPage;
    CartPage cartPage;
    DeleteAccountPage deleteAccountPage;
    CheckoutPage checkoutPage;
    PaymentPage paymentPage;
    AccountCreatedPage accountCreatedPage;


    String validEmail = "validemail@example.com";
    String validPassword = "Valid123";
    String username = "NewUser";
    String firstname = "New";
    String lastname = "User";
    String address = "Address";
    String state = "State";
    String city = "City";
    String zipcode = "12345";
    String mobile = "012345678";
    String country = "India";


    @Description("Test Case 23: Verify address details in checkout page")
    @Test
    public void verifyLoginWithValidCredentials() {

        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        signUpPage = new SignUpPage(driver);
        cartPage = new CartPage(driver);
        deleteAccountPage = new DeleteAccountPage(driver);
        checkoutPage = new CheckoutPage(driver);
        paymentPage = new PaymentPage(driver);
        accountCreatedPage = new AccountCreatedPage(driver);


        homePage.navigate();
        homePage.assertNavigationToHomePage();

        homePage.clickOnLoginBtn();
        loginPage.signup(this.username, this.validEmail);

        signUpPage.fillAccountDetails(validPassword, firstname, lastname, address, state, city, zipcode, mobile);
        signUpPage.clickCreateAccount();
        accountCreatedPage.assertAccountCreatedTitle();
        accountCreatedPage.clickContinue();

        homePage.assertUserIsLoggedIN(username);
        homePage.addProduct();
        homePage.clickOnCartBtn();

        cartPage.assertNavigationToCartPage();
        cartPage.clickProceedToCheckout();

        checkoutPage.assertDeliveryAddress("Mr. " + firstname + " " + lastname, address, city + " " + state + " " + zipcode, country, mobile);
        checkoutPage.assertBillingAddress("Mr. " + firstname + " " + lastname, address, city + " " + state + " " + zipcode, country, mobile);

        paymentPage.deleteAccount();
        deleteAccountPage.assertAccountDeletedMsg();
    }
}
