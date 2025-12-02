package Tests;

import Pages.HomePage;
import jdk.jfr.Description;
import org.testng.annotations.Test;

public class SubscriptionHomePageTest extends TestBase {
    HomePage homePage;
    String validEmail = "validemail@example.com";

    @Description("Test Case 10: Verify Subscription in home page")
    @Test
    public void verifySubscriptionInHomePage() {

        homePage = new HomePage(driver);

        homePage.navigate();
        homePage.assertNavigationToHomePage();
        homePage.assertSubscriptionTitle();

        homePage.enterSubscriptionEmail(validEmail);
        homePage.assertSuccessfulSubscription();
    }
}
