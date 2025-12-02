package Tests;

import Pages.HomePage;
import Pages.LoginPage;
import jdk.jfr.Description;
import org.testng.annotations.Test;

public class LogoutTest extends TestBase {
    HomePage homePage;
    LoginPage loginPage;
    String validEmail = "validemail@example.com";
    String validPassword = "Valid123";
    String username = "NewUser";

    @Description("Test Case 4: Logout User")
    @Test
    public void verifyLogoutAfterLogin() {

        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);

        homePage.navigate();
        homePage.assertNavigationToHomePage();
        homePage.clickOnLoginBtn();

        loginPage.assertLoginTitle();
        loginPage.login(validEmail, validPassword);

        homePage.assertUserIsLoggedIN(username);
        homePage.logout();
        loginPage.assertNavigationToLogin();
    }
}