package StepDefs;

import Pages.DeleteAccountPage;
import Pages.HomePage;
import Pages.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStepDef {

    HomePage homePage = new HomePage(Hooks.driver);
    LoginPage loginPage = new LoginPage(Hooks.driver);
    DeleteAccountPage deleteAccountPage = new DeleteAccountPage(Hooks.driver);

    String validEmail = "validemail@example.com";
    String validPassword = "Valid123";
    String username = "NewUser";

    @Given("the user navigates to homepage")
    public void userNavigatesToHomepage()
    {
        homePage.navigate();
    }

    @And("the homepage is displayed successfully")
    public void homepageDisplayedSuccessfully()
    {
        homePage.assertNavigationToHomePage();
    }

    @When("the user clicks on 'Signup-Login'")
    public void userClicksOnLogin()
    {
        homePage.clickOnLoginBtn();
    }

    @Then("\"Login to your account\" should be visible")
    public void verifyLoginPageTitle()
    {
        loginPage.assertLoginTitle();
    }

    @When("the user enters email {string} and password {string}")
    public void login(String email, String password)
    {
        loginPage.login(email, password);
    }

    @Then("\"Logged in as username\" should be visible")
    public void verifyLogin()
    {
        homePage.assertUserIsLoggedIN(username);
    }

    @When("the user clicks \"Delete Account\"")
    public void userClicksDeleteAccountBtn()
    {
        homePage.deleteAccount();
    }

    @Then("\"ACCOUNT DELETED!\" should be visible")
    public void verifyDeletingAccount()
    {
        deleteAccountPage.assertAccountDeletedMsg();
    }

    @Then("the error message should be visible")
    public void verifyErrorMsgForInvalidLogin()
    {
        loginPage.verifyErrorMsg();
    }


}
