package Tests;

import Pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC13_ProductQuantityTest extends TestBase {

    @Test
    public void verifyProductQuantityInCart() {
        ProductPage page = new ProductPage(driver);
        page.clickProductsMenu();
        page.clickViewFirstProduct();
        page.setQuantity("4");
        page.addToCartFromDetails();
        page.clickViewCart();

        String actualQty = page.getCartQuantity();
        System.out.println("Quantity in cart: " + actualQty);

        Assert.assertEquals(actualQty, "4", "Quantity mismatch!");

        System.out.println("Test Case 13 Passed: Quantity is correct.");
    }
}