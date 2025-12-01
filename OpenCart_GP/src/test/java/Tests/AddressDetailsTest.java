package Tests;

import Pages.*;
import jdk.jfr.Description;
import org.testng.annotations.Test;

public class AddressDetailsTest extends TestBase{

    HomePage homePage;
    LoginPage loginPage;
    SignUpPage signUpPage;
    CartPage cartPage;
    DeleteAccountPage deleteAccountPage;
    CheckoutPage checkoutPage;
    PaymentPage paymentPage;

    String validEmail = "validemail@example.com";
    String validPassword = "Valid123";
    String username = "NewUser";

    @Description("Test Case 23: Verify address details in checkout page")
    @Test
    public void verifyLoginWithValidCredentials()
    {

        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        signUpPage = new SignUpPage(driver);
        cartPage = new CartPage(driver);
        deleteAccountPage = new DeleteAccountPage(driver);
        checkoutPage = new CheckoutPage(driver);
        paymentPage = new PaymentPage(driver);


        homePage.navigate();
        homePage.assertNavigationToHomePage();

        homePage.clickOnLoginBtn();
        loginPage.signup(username, validEmail);

        signUpPage.fillAccountDetails(validPassword, "New", "User", "Alex", "Alx", "Egy", "12345", "01234567");
        homePage.clickOnLoginBtn();
        homePage.assertUserIsLoggedIN(username);


        homePage.addProduct();
        homePage.clickOnCartBtn();
        cartPage.assertNavigationToCartPage();

        cartPage.clickProceedToCheckout();
        checkoutPage.verifyAddressDetails();
        checkoutPage.verifyReviewYourOrder();

        paymentPage.deleteAccount();
        deleteAccountPage.assertAccountDeletedMsg();

    }
}
