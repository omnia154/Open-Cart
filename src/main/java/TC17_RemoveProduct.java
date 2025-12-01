import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class TC17_RemoveProduct {
    public static void main(String[] args) throws InterruptedException {
        // --- Test Case 17: Remove Products From Cart (Force Click Version) ---

        // 1. Setup
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        // 2. Open URL
        driver.get("https://automationexercise.com/");

        // 3. Verify home page
        if(driver.getTitle().equals("Automation Exercise")) {
            System.out.println("✅ Step 1: Home page visible");
        }

        // تجهيز أداة الجافاسكريبت (الجوكر) للضغط على أي عنصر
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // 4. Add product to cart
        System.out.println("🔄 Trying to click Products...");
        WebElement productsLink = driver.findElement(By.partialLinkText("Products"));
        js.executeScript("arguments[0].click();", productsLink); // ضغط إجباري

        System.out.println("✅ Products page clicked.");

        // Scroll عشان الصفحة تحمل
        js.executeScript("window.scrollBy(0, 500)");
        Thread.sleep(1000);

        // هنا بنضغط على زرار Add to Cart مباشرة بالجافاسكريبت من غير ما نحتاج Hover
        // ده بيحل مشكلة ElementNotInteractable نهائياً
        WebElement addToCartBtn = driver.findElement(By.xpath("(//a[@data-product-id='1' and contains(@class,'add-to-cart')])[2]"));
        js.executeScript("arguments[0].click();", addToCartBtn);
        System.out.println("ℹ️ First product added (Force Click)...");

        // 5. Click 'Continue Shopping' button
        Thread.sleep(1000); // استنى المودال يظهر
        WebElement continueBtn = driver.findElement(By.xpath("//button[text()='Continue Shopping']"));
        js.executeScript("arguments[0].click();", continueBtn);

        // 6. Go to Cart page
        WebElement cartLink = driver.findElement(By.partialLinkText("Cart"));
        js.executeScript("arguments[0].click();", cartLink);

        // 7. Verify that cart page is displayed
        if(driver.getCurrentUrl().contains("view_cart")) {
            System.out.println("✅ Step 2: Cart page opened");
        }

        // 8. Click 'X' button to remove product
        WebElement deleteBtn = driver.findElement(By.className("cart_quantity_delete"));
        js.executeScript("arguments[0].click();", deleteBtn);

        System.out.println("ℹ️ Remove button clicked...");

        // بنستنى ثانية عشان المنتج يختفي
        Thread.sleep(1000);

        // 9. Verify that product is removed (Check empty cart message)
        try {
            // بنحاول نلاقي رسالة "Cart is empty"
            // لو الصفحة فضيت، الرسالة دي بتظهر في العنصر #empty_cart
            WebElement emptyMsg = driver.findElement(By.id("empty_cart"));
            if(emptyMsg.isDisplayed()) {
                System.out.println("✅ Test Passed! Product removed and Cart is empty.");
            }
        } catch (Exception e) {
            // لو ملقناش الرسالة، ممكن نتأكد بطريقة تانية: عدد الصفوف في الجدول
            int rowCount = driver.findElements(By.xpath("//tbody/tr")).size();
            if(rowCount == 0) {
                System.out.println("✅ Test Passed! Cart table is empty.");
            } else {
                System.out.println("❌ Test Failed! Cart still has items.");
            }
        }

        Thread.sleep(3000);
        driver.quit();
    }
}