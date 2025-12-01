package Tests;

import Pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC18_ViewCategoryTest extends TestBase {

    @Test
    public void verifyCategoryFunctionality() {
        ProductPage page = new ProductPage(driver);

        try { Thread.sleep(2000); } catch (InterruptedException e) {}

        boolean isVisible = page.isCategoryVisible();
        Assert.assertTrue(isVisible, "Categories sidebar is NOT visible");
        page.clickWomenCategory();
        page.clickDressLink();

        String title1 = page.getPageTitle();
        System.out.println("Page Title 1: " + title1);
        Assert.assertTrue(title1.contains("WOMEN - DRESS PRODUCTS"), "Wrong Page: " + title1);

        page.clickMenCategory();
        page.clickTshirtsLink();

        String title2 = page.getPageTitle();
        System.out.println("Page Title 2: " + title2);
        Assert.assertTrue(title2.contains("MEN - TSHIRTS PRODUCTS"), "Wrong Page: " + title2);

        System.out.println("Test Case 18 Passed!");
    }
}