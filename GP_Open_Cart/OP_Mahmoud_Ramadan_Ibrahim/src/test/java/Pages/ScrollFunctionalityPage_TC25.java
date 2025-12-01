package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class ScrollFunctionalityPage_TC25 {

    WebDriver driver;
    JavascriptExecutor js;

    // Constructor
    public ScrollFunctionalityPage_TC25(WebDriver driver) {
        this.driver = driver;
        // تهيئة محرك JavaScriptExecutor لتنفيذ أوامر التمرير
        this.js = (JavascriptExecutor) driver;
    }

    // --- Locators (عناوين العناصر) ---

    // Step 5: نص 'SUBSCRIPTION' في أسفل الصفحة
    By subscription_text = By.xpath("//h2[text()='Subscription']");

    // Step 6: زر السهم للعودة إلى الأعلى (Arrow button)
    By scroll_up_arrow = By.id("scrollUp");

    // Step 7: النص المرئي في أعلى الصفحة بعد التمرير للأعلى
    //By full_fledged_text = By.xpath("//h2/b[text()='Full-Fledged practice website for Automation Engineers']");
    // مُحدِّد جديد يستهدف أي عنصر <h2> يحتوي على جزء من النص الرئيسي
    By full_fledged_text = By.xpath("//h2[contains(., 'Full-Fledged practice website')]");

// بديل أكثر دقة: (قد يكون النص داخل <b>)
// By full_fledged_text = By.xpath("//b[contains(text(), 'Full-Fledged')]");


    // --- Actions & Assertions (الأفعال والتحققات) ---

    // Step 4: Scroll down page to bottom
    public void scrollPageToBottom() {
        // تنفيذ أمر JavaScript للتمرير إلى نهاية الصفحة (0, document.body.scrollHeight)
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    // Step 5: Verify 'SUBSCRIPTION' is visible
    public void verifySubscriptionTextIsVisible() {
        // الانتظار قليلاً لضمان اكتمال التمرير والتحميل (اختياري، لكن مفيد)
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        WebElement subscriptionElement = driver.findElement(subscription_text);
        Assert.assertTrue(subscriptionElement.isDisplayed(),
                "[ERROR] Subscription text is not visible at the bottom of the page.");
    }

    // Step 6: Click on arrow at bottom right side to move upward
    public void clickScrollUpArrow() {
        // نتحقق من أن زر السهم نفسه مرئي ومتاح للنقر قبل النقر عليه
        WebElement arrowElement = driver.findElement(scroll_up_arrow);
        Assert.assertTrue(arrowElement.isDisplayed(),
                "[ERROR] Scroll Up Arrow button is not visible or clickable.");
        arrowElement.click();
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