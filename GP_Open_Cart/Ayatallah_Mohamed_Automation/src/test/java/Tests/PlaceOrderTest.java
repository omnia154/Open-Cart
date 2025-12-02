
package Tests;

import Pages.CartPage;
import Pages.CheckoutPage;
import Pages.DeleteAccountPage;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.PaymentPage;
import jdk.jfr.Description;
import org.testng.annotations.Test;

public class PlaceOrderTest extends TestBase {
    HomePage homePage;
    LoginPage loginPage;
    DeleteAccountPage deleteAccountPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    PaymentPage paymentPage;
    String validEmail = "validemail@example.com";
    String validPassword = "Valid123";
    String username = "NewUser";

    @Description("Test Case 16: Place Order: Login before Checkout")
    @Test
    public void loginBeforeCheckOut() {

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
        loginPage.login(this.validEmail, this.validPassword);
        homePage.assertUserIsLoggedIN(this.username);

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
