package Tests;

import Pages.HomePage_TC7;
import Pages.ScrollFunctionalityPage_TC25;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;

public class ScrollUpArrowTestCase25 {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        // Step 1: Launch browser
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test(description = "Test Case 25: Verify Scroll Up using 'Arrow' button and Scroll Down functionality")
    public void testScrollUpAndDown() {

        // تهيئة كائنات الصفحة
        HomePage_TC7 homePage = new HomePage_TC7(driver);
        ScrollFunctionalityPage_TC25 scrollPage = new ScrollFunctionalityPage_TC25(driver);

        // Step 2 & 3: Navigate to url and Verify Home Page
        homePage.navigateToHome();
        homePage.assertHomePageVisible();

        // Step 4: Scroll down page to bottom
        scrollPage.scrollPageToBottom();

        // Step 5: Verify 'SUBSCRIPTION' is visible
        scrollPage.verifySubscriptionTextIsVisible();

        // Step 6: Click on arrow at bottom right side to move upward
        scrollPage.clickScrollUpArrow();

        // Step 7: Verify that page is scrolled up and text is visible on screen
        scrollPage.verifyScrolledUpSuccessfully();

        System.out.println("✅ نجح الاختبار: تم التمرير للأسفل والتحقق، ثم العودة للأعلى والتحقق باستخدام زر السهم.");
    }

    @AfterMethod
    public void tearDown() {
        // إغلاق المتصفح
        if (driver != null) {
            driver.quit();
        }
    }
}