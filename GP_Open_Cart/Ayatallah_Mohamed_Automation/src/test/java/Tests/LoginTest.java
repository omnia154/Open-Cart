package Tests;

import Pages.DeleteAccountPage;
import Pages.HomePage;
import Pages.LoginPage;
import jdk.jfr.Description;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {
    HomePage homePage;
    LoginPage loginPage;
    DeleteAccountPage deleteAccountPage;
    String validEmail = "validemail@example.com";
    String validPassword = "Valid123";
    String username = "NewUser";
    String invalidEmail = "invalid@email.com";
    String invalidPassword = "Invalid";

    @Description("Test Case 2: Login User with correct email and password")
    @Test
    public void verifyLoginWithValidCredentials() {

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
    public void loginWithInvalidCredentials() {

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
}

