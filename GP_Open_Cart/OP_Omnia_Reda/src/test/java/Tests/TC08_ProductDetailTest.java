package Tests;

import Pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC08_ProductDetailTest extends TestBase {

    @Test
    public void verifyAllProductsAndDetail() {
        ProductPage page = new ProductPage(driver);

        String homeTitle = driver.getTitle();
        if(homeTitle.equals("Automation Exercise")) {
            System.out.println("Home page is visible successfully");
        }
        page.clickProductsMenu();
        boolean isListVisible = page.isProductListVisible();
        Assert.assertTrue(isListVisible, "All Products list is NOT visible");
        System.out.println("All Products page verified");

        page.clickViewFirstProduct();

        boolean isDetailsVisible = page.isProductInfoVisible();
        Assert.assertTrue(isDetailsVisible, "Some product details are missing!");

        System.out.println("Test Case 8 Passed: All product details are visible.");
    }
}