package Tests;

import Pages.HomePage_TC7;
import Pages.BrandProductsPage_TC19;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver; // استخدام متصفح Edge كما طلب في التشغيل الأخير
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;

public class BrandProductsTestCase19 {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        // Step 1: Launch browser
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test(description = "Test Case 19: Verify Brand Products Filtering")
    public void testViewAndCartBrandProducts() {

        // تهيئة كائنات الصفحة
        HomePage_TC7 homePage = new HomePage_TC7(driver);
        BrandProductsPage_TC19 brandPage = new BrandProductsPage_TC19(driver);

        // Step 2 & 3: Navigate to url and Verify Home Page
        homePage.navigateToHome();
        homePage.assertHomePageVisible();

        // Step 3 (تابع): Click on 'Products' button
        // النقر للانتقال من الصفحة الرئيسية إلى صفحة جميع المنتجات
        driver.findElement(By.xpath("//a[contains(text(), ' Products')]")).click();

        // Step 4: Verify that Brands are visible
        brandPage.verifyBrandsAreVisible();

        // Step 5 & 6: Click on FIRST brand (Index 0) and verify navigation
        brandPage.clickAndVerifyBrandProducts(0);

        // Step 7 & 8: Click on SECOND brand (Index 1) and verify navigation
        brandPage.clickAndVerifyBrandProducts(1);
    }

    @AfterMethod
    public void tearDown() {
        // إغلاق المتصفح
        if (driver != null) {
            driver.quit();
        }
    }
}