package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class ScrollFunctionalityPage_TC26 {

    WebDriver driver;
    JavascriptExecutor js;

    // Constructor
    public ScrollFunctionalityPage_TC26(WebDriver driver) {
        this.driver = driver;
        // تهيئة محرك JavaScriptExecutor لتنفيذ أوامر التمرير
        this.js = (JavascriptExecutor) driver;
    }

    // --- Locators (عناوين العناصر) ---

    // Step 5: نص 'SUBSCRIPTION' في أسفل الصفحة
    By subscription_text = By.xpath("//h2[text()='Subscription']");

    // Step 7: النص المرئي في أعلى الصفحة بعد التمرير للأعلى (تم تصحيح المحدد هنا)
    By full_fledged_text = By.xpath("//h2[contains(., 'Full-Fledged practice website')]");


    // --- Actions & Assertions (الأفعال والتحققات) ---

    // Step 4: Scroll down page to bottom (باستخدام JS)
    public void scrollPageToBottom() {
        // تنفيذ أمر JavaScript للتمرير إلى نهاية الصفحة
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    // Step 5: Verify 'SUBSCRIPTION' is visible
    public void verifySubscriptionTextIsVisible() {
        // الانتظار قليلاً لضمان اكتمال التمرير والتحميل
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        WebElement subscriptionElement = driver.findElement(subscription_text);
        Assert.assertTrue(subscriptionElement.isDisplayed(),
                "[ERROR] Subscription text is not visible at the bottom of the page.");
    }

    // Step 6: Scroll up page to top (باستخدام JS)
    public void scrollPageToTop() {
        // تنفيذ أمر JavaScript للتمرير إلى أعلى الصفحة (0, 0)
        js.executeScript("window.scrollTo(0, 0)");
    }

    // Step 7: Verify that page is scrolled up and text is visible
    public void verifyScrolledUpSuccessfully() {
        // الانتظار قليلاً لضمان اكتمال التمرير للأعلى
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        WebElement homePageText = driver.findElement(full_fledged_text);

        // التحقق من أن النص المطلوب مرئي على الشاشة
        Assert.assertTrue(homePageText.isDisplayed(),
                "[ERROR] Page failed to scroll up or 'Full-Fledged practice website...' text is not visible.");
    }
}