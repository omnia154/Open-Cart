package Tests;

import Pages.HomePage_TC7; // استيراد صفحتك التي أنشأتها
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;

public class VerifyTestCase7 {

    WebDriver driver;
    HomePage_TC7 homePage; // تعريف متغير لصفحتك

    // 1. الإعدادات قبل كل اختبار (Setup)
    @BeforeMethod
    public void setup() {
        // تشغيل المتصفح (تأكد أنك قمت بتثبيت ChromeDriver أو تستخدم Selenium Manager)
        driver = new EdgeDriver();

        // تكبير الشاشة
        driver.manage().window().maximize();

        // إعطاء مهلة ذكية للمتصفح (Implicit Wait)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    // 2. الاختبار الفعلي (The Test)
    @Test
    public void testVerifyTestCase7() {
        // إنشاء نسخة من صفحتك وإعطائها الـ driver
        homePage = new HomePage_TC7(driver);

        // تنفيذ الخطوات التي كتبناها في ملف الصفحة

        // Step 2: Navigate to url
        homePage.navigateToHome();

        // Step 3: Verify Home Page
        homePage.assertHomePageVisible();

        // Step 4: Click 'Test Cases'
        homePage.clickTestCasesButton();

        // Step 5: Verify Test Cases Page
        homePage.assertNavigationToTestCasesPage();
    }

    // 3. التنظيف بعد الاختبار (Teardown)
    @AfterMethod
    public void tearDown() {
        // إغلاق المتصفح تماماً
        if (driver != null) {
            driver.quit();
        }
    }
}
