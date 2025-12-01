package Tests;

import Pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC21_AddReviewTest extends TestBase {

    @Test
    public void verifyAddReviewOnProduct() {
        ProductPage page = new ProductPage(driver);

        page.clickProductsMenu();
        page.clickViewFirstProduct();

        boolean isReviewVisible = page.isReviewHeaderVisible();
        Assert.assertTrue(isReviewVisible, "Write Review header is not visible");
        page.fillReviewForm("Omnia", "omnia@test.com", "Amazing product! Testing with Selenium.");
        page.clickSubmitReview();


        boolean isSuccess = page.isSuccessReviewMsgVisible();
        Assert.assertTrue(isSuccess, "Success message not appeared!");

        System.out.println("Test Case 21 Passed: Review submitted successfully.");
    }
}