package Tests;

import Pages.*;
import jdk.jfr.Description;
import org.testng.annotations.Test;
import Pages.ProductPage;
public class LoginTest extends TestBase{

    HomePage homePage;
    LoginPage loginPage;
    DeleteAccountPage deleteAccountPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    PaymentPage paymentPage;

    //Test Data
    String validEmail = "validemail@example.com";
    String validPassword = "Valid123";
    String username = "NewUser";
    String invalidEmail = "invalid@email.com";
    String invalidPassword = "Invalid";

    @Description("Test Case 2: Login User with correct email and password")
    @Test
    public void verifyLoginWithValidCredentials()
    {

        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        deleteAccountPage = new DeleteAccountPage(driver);

        homePage.navigate();
        homePage.assertNavigationToHomePage();

        homePage.clickOnLoginBtn();
        loginPage.assertLoginTitle();

        loginPage.login(validEmail, validPassword);
        homePage.assertUserIsLoggedIN(username);

        homePage.deleteAccount();
        deleteAccountPage.assertAccountDeletedMsg();
    }


    @Description("Test Case 3: Login User with incorrect email and password")
    @Test
    public void loginWithInvalidCredentials()
    {

        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        deleteAccountPage = new DeleteAccountPage(driver);

        homePage.navigate();
        homePage.assertNavigationToHomePage();

        homePage.clickOnLoginBtn();
        loginPage.assertLoginTitle();

        loginPage.login(invalidEmail, invalidPassword);
        loginPage.verifyErrorMsg();
    }

    @Description("Test Case 4: Logout User")
    @Test
    public void verifyLogoutAfterLogin()
    {

        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);

        homePage.navigate();
        homePage.assertNavigationToHomePage();

        homePage.clickOnLoginBtn();
        loginPage.assertLoginTitle();

        loginPage.login(validEmail, validPassword);
        homePage.assertUserIsLoggedIN(username);

        loginPage.assertNavigationToLogin();

    }

    @Description("Test Case 16: Place Order: Login before Checkout")
    @Test
    public void loginBeforeCheckOut()
    {
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        deleteAccountPage = new DeleteAccountPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        paymentPage = new PaymentPage(driver);

        homePage.navigate();
        homePage.assertNavigationToHomePage();

        homePage.clickOnLoginBtn();
        loginPage.assertLoginTitle();

        loginPage.login(validEmail, validPassword);
        homePage.assertUserIsLoggedIN(username);

        homePage.addProduct();
        homePage.clickOnCartBtn();
        cartPage.assertNavigationToCartPage();

        cartPage.clickProceedToCheckout();
        checkoutPage.verifyAddressDetails();
        checkoutPage.verifyReviewYourOrder();

        checkoutPage.writeDescriptionComment();
        checkoutPage.clickPlaceOrder();

        paymentPage.cardDetails("Aya", "123456", "123", "09", "2030");
        paymentPage.assertOrderMsg();

        paymentPage.deleteAccount();
        deleteAccountPage.assertAccountDeletedMsg();
    }


}
