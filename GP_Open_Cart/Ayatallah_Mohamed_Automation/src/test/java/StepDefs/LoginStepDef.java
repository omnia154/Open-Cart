package StepDefs;

import Pages.DeleteAccountPage;
import Pages.HomePage;
import Pages.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStepDef {
    HomePage homePage;
    LoginPage loginPage;
    DeleteAccountPage deleteAccountPage;
    String validEmail;
    String validPassword;
    String username;

    public LoginStepDef() {
        this.homePage = new HomePage(Hooks.driver);
        this.loginPage = new LoginPage(Hooks.driver);
        this.deleteAccountPage = new DeleteAccountPage(Hooks.driver);
        this.validEmail = "validemail@example.com";
        this.validPassword = "Valid123";
        this.username = "NewUser";
    }

    @Given("the user navigates to homepage")
    public void userNavigatesToHomepage() {
        this.homePage.navigate();
    }

    @And("the homepage is displayed successfully")
    public void homepageDisplayedSuccessfully() {
        this.homePage.assertNavigationToHomePage();
    }

    @When("the user clicks on 'Signup-Login'")
    public void userClicksOnLogin() {
        this.homePage.clickOnLoginBtn();
    }

    @Then("\"Login to your account\" should be visible")
    public void verifyLoginPageTitle() {
        this.loginPage.assertLoginTitle();
    }

    @When("the user enters email {string} and password {string}")
    public void login(String email, String password) {
        this.loginPage.login(email, password);
    }

    @Then("\"Logged in as username\" should be visible")
    public void verifyLogin() {
        this.homePage.assertUserIsLoggedIN(this.username);
    }

    @When("the user clicks \"Delete Account\"")
    public void userClicksDeleteAccountBtn() {
        this.homePage.deleteAccount();
    }

    @Then("\"ACCOUNT DELETED!\" should be visible")
    public void verifyDeletingAccount() {
        this.deleteAccountPage.assertAccountDeletedMsg();
    }

    @Then("the error message should be visible")
    public void verifyErrorMsgForInvalidLogin() {
        this.loginPage.verifyErrorMsg();
    }
}