package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class HomePage_TC7 {

    WebDriver driver;

    // Constructor: همزة الوصل بين ملف الاختبار وهذا الكلاس
    public HomePage_TC7(WebDriver driver) {
        this.driver = driver;
    }

    String homePageURL = "http://automationexercise.com";
    String testCasesPageURL = "https://automationexercise.com/test_cases";

    // --- 1. Locators (العناوين) ---

    // اخترنا اللوجو كدليل على أن الصفحة الرئيسية ظهرت
    By homePageLogo = By.xpath("//img[@alt='Website for automation practice']");

    // زر "Test Cases" الموجود في الشريط العلوي
    By testCases_btn = By.xpath("//div[@class='shop-menu pull-right']//a[contains(text(),'Test Cases')]");

    // عنوان صفحة Test Cases للتحقق من الوصول إليها
    By testCasesPage_title = By.xpath("//h2/b[text()='Test Cases']");


    // --- 2. Actions (الأفعال) ---

    // Step 2: Navigate to url
    public void navigateToHome() {
        driver.get(homePageURL);
    }

    // Step 4: Click on 'Test Cases' button
    public void clickTestCasesButton() {
        driver.findElement(testCases_btn).click();
    }


    // --- 3. Assertions (التحققات) ---

    // Step 3: Verify that home page is visible successfully
    public void assertHomePageVisible() {
        // نتحقق من أمرين: العنوان صحيح، واللوجو ظاهر
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, "Automation Exercise");
        Assert.assertTrue(driver.findElement(homePageLogo).isDisplayed(), "[ERROR] Home Page Logo is not displayed!");
    }

    // Step 5: Verify user is navigated to test cases page successfully
    public void assertNavigationToTestCasesPage() {
        // التحقق من الرابط الحالي
        String currentURL = driver.getCurrentUrl();
        Assert.assertTrue(currentURL.contains("test_cases"), "[ERROR] URL does not contain 'test_cases'");

        // التحقق من وجود العنوان العريض في الصفحة
        Assert.assertTrue(driver.findElement(testCasesPage_title).isDisplayed(), "[ERROR] Test Cases Page Title is not visible");
    }
}
