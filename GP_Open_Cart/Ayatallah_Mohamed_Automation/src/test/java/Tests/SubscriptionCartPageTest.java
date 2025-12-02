package Tests;

import Pages.CartPage;
import Pages.HomePage;
import jdk.jfr.Description;
import org.testng.annotations.Test;

public class SubscriptionCartPageTest extends TestBase {
    HomePage homePage;
    CartPage cartPage;
    String validEmail = "validemail@example.com";

    @Description("Test Case 11: Verify Subscription in Cart page")
    @Test
    public void verifySubscriptionInCartPage() {

        homePage = new HomePage(driver);
        cartPage = new CartPage(driver);

        homePage.navigate();
        homePage.assertNavigationToHomePage();
        homePage.clickOnCartBtn();

        cartPage.assertNavigationToCartPage();
        cartPage.enterSubscriptionEmail(validEmail);
        cartPage.assertSuccessfulSubscription();
    }
}