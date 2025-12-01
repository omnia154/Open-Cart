package Tests;

import Pages.ProductPage;
import org.testng.annotations.Test;

public class TC12_AddProductsTest extends TestBase {

    @Test
    public void testAddProductsToCart() {
        ProductPage page = new ProductPage(driver);

        page.clickProductsMenu();
        page.addFirstProduct();
        page.clickContinueShopping();
        page.addSecondProduct();
        page.clickViewCart();

        System.out.println("Test Case 12 Completed Successfully!");
        org.testng.Assert.fail("Failing to test screenshot");
    }
}