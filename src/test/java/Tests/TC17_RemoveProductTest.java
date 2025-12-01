package Tests;

import Pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC17_RemoveProductTest extends TestBase {

    @Test
    public void verifyRemoveProductFromCart() {
        ProductPage page = new ProductPage(driver);
        page.addFirstProduct();
        page.clickViewCart();
        page.clickRemoveProduct();

        boolean isEmpty = page.isCartEmpty();
        System.out.println("Is cart empty? " + isEmpty);

        Assert.assertTrue(isEmpty, "Cart is NOT empty!");

        System.out.println("Test Case 17 Passed: Product removed.");
    }
}