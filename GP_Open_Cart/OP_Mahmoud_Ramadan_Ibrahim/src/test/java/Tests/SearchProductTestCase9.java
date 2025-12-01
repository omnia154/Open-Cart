package Tests;

// نفترض أنك استخدمت نفس الكلاس السابق لعمليات الصفحة الرئيسية
import Pages.HomePage_TC7;
import Pages.ProductsPage_TC9;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;

public class SearchProductTestCase9 {

    WebDriver driver;
    // تعريف كلمة البحث لاستخدامها في أكثر من خطوة
    final String SEARCH_TERM = "Men Tshirt";

    @BeforeMethod
    public void setup() {
        // Step 1: Launch browser
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        // إعداد انتظار ضمني (Implicit Wait) لمدة 10 ثواني لمساعدة Selenium في العثور على العناصر
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test(description = "Test Case 9: Verify Product Search Functionality")
    public void testProductSearch() {

        // إنشاء كائنات الصفحة (Home Page Object) لتنفيذ مهامها
        // نفترض أن هذا الكلاس موجود من الاختبار السابق
        HomePage_TC7 homePage = new HomePage_TC7(driver);

        // إنشاء كائن صفحة المنتجات الجديد (Products Page Object)
        ProductsPage_TC9 productsPage = new ProductsPage_TC9(driver);

        // Step 2 & 3: Navigate to url and Verify Home Page
        homePage.navigateToHome();
        homePage.assertHomePageVisible();

        // Step 4 & 5: Click 'Products' button and verify navigation
        productsPage.clickProductsButtonAndVerifyNavigation();

        // Step 6: Enter product name and click search
        productsPage.searchForProduct(SEARCH_TERM);

        // Step 7 & 8: Verify 'SEARCHED PRODUCTS' title and all related results
        productsPage.verifySearchResults(SEARCH_TERM);
    }

    @AfterMethod
    public void tearDown() {
        // إغلاق المتصفح بعد انتهاء الاختبار
        if (driver != null) {
            driver.quit();
        }
    }
}
