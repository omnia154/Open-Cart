package Tests;

import Pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC22_RecommendedItemsTest extends TestBase {

    @Test
    public void verifyRecommendedItems() {
        ProductPage page = new ProductPage(driver);

        page.scrollToBottom();

        boolean isVisible = page.isRecommendedVisible();
        Assert.assertTrue(isVisible, "Recommended Items are NOT visible!");
        System.out.println("Step 1: Recommended items visible");
        page.addRecommendedProductToCart();
        page.clickViewCart();

        boolean isEmpty = page.isCartEmpty();
        Assert.assertFalse(isEmpty, "Cart should NOT be empty!"); 
        System.out.println("Test Case 22 Passed: Recommended item added to cart.");
    }
}