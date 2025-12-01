package Tests;

import Pages.HomePage_TC7;
import Pages.ProductSearchCartPage_TC20;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.List;

public class SearchCartLoginTestCase20 {

    WebDriver driver;
    // بيانات البحث
    final String SEARCH_PRODUCT = "Sleeves";

    // بيانات تسجيل الدخول
    final String LOGIN_EMAIL = "Medo8080@gmail.com";
    final String LOGIN_PASSWORD = "12345678";

    // متغير لتخزين أسماء المنتجات التي تم العثور عليها وإضافتها
    private List<String> productsInCartBeforeLogin;

    @BeforeMethod
    public void setup() {
        // Step 1: Launch browser
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15)); // زيادة الانتظار قليلاً للتعامل مع المودالات
    }

    @Test(description = "Test Case 20: Verify Cart Persistence After Login")
    public void testSearchAndVerifyCartAfterLogin() {

        // تهيئة كائنات الصفحة
        HomePage_TC7 homePage = new HomePage_TC7(driver);
        ProductSearchCartPage_TC20 cartPage = new ProductSearchCartPage_TC20(driver);

        // Step 2: Navigate and Verify Home Page
        homePage.navigateToHome();
        homePage.assertHomePageVisible();

        // Step 3 & 4: Click 'Products' and verify navigation
        cartPage.navigateToProductsPage();

        System.out.println("navigateToProductsPage is done");

        // Step 5, 6, 7: Search, verify results, and save names
        productsInCartBeforeLogin = cartPage.searchAndVerify(SEARCH_PRODUCT);

        // Step 8: Add those products to cart
        cartPage.addAllFoundProductsToCart();

        // Step 9: Click 'Cart' button and verify that products are visible in cart (قبل تسجيل الدخول)
        cartPage.verifyProductsInCart(productsInCartBeforeLogin);

        // Step 10: Click 'Signup / Login' and submit login details
        // *ملاحظة: يفترض وجود حساب (test_user@example.com) تم إنشاؤه مسبقاً في الموقع*
        cartPage.loginUser(LOGIN_EMAIL, LOGIN_PASSWORD);

        // Step 11: Again, go to Cart page
        driver.findElement(By.xpath("//a[contains(text(), ' Cart')]")).click();

        // Step 12: Verify that those products are visible in cart after login as well
        // نستخدم نفس الدالة للتحقق، مؤكدين أن المنتجات (productsInCartBeforeLogin) لم تضيع بعد تسجيل الدخول.
        cartPage.verifyProductsInCart(productsInCartBeforeLogin);

        System.out.println("✅ نجح الاختبار: تم حفظ المنتجات في سلة التسوق بعد تسجيل الدخول.");
    }

    @AfterMethod
    public void tearDown() {
        // إغلاق المتصفح
        if (driver != null) {
            driver.quit();
        }
    }
}