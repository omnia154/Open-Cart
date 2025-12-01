import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.List;

public class TC12_AddProducts {
    public static void main(String[] args) throws InterruptedException {
        // --- Test Case 12: Add Products in Cart ---

        // 1. تجهيز الدرايفر
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        // 2. فتح الموقع
        driver.get("https://automationexercise.com/");

        // 3. التأكد إننا في الصفحة الرئيسية
        String homeTitle = driver.getTitle();
        if(homeTitle.equals("Automation Exercise")) {
            System.out.println("✅ Step 1: Home page is visible successfully");
        }

        // 4. الضغط على 'Products'
        driver.findElement(By.partialLinkText("Products")).click();

        // 5. عمل Scroll بسيط
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 500)");

        // 6. إضافة المنتج الأول (Hover + Click)
        Actions actions = new Actions(driver);

        WebElement firstProduct = driver.findElement(By.xpath("(//div[@class='product-image-wrapper'])[1]"));
        actions.moveToElement(firstProduct).perform(); // Hover

        WebElement addToCart1 = driver.findElement(By.xpath("(//a[@data-product-id='1' and contains(@class,'add-to-cart')])[2]"));
        addToCart1.click();

        System.out.println("ℹ️ First product added...");

        // 7. التعامل مع الـ Popup (Continue Shopping)
        WebElement continueBtn = driver.findElement(By.xpath("//button[text()='Continue Shopping']"));
        continueBtn.click();

        // 8. إضافة المنتج الثاني
        WebElement secondProduct = driver.findElement(By.xpath("(//div[@class='product-image-wrapper'])[2]"));
        actions.moveToElement(secondProduct).perform(); // Hover

        WebElement addToCart2 = driver.findElement(By.xpath("(//a[@data-product-id='2' and contains(@class,'add-to-cart')])[2]"));
        addToCart2.click();

        System.out.println("ℹ️ Second product added...");

        // 9. الذهاب لصفحة الكارت
        WebElement viewCartBtn = driver.findElement(By.partialLinkText("View Cart"));
        viewCartBtn.click();

        // 10. التأكد إن الكارت فيها منتجين
        List<WebElement> cartItems = driver.findElements(By.xpath("//tbody/tr"));

        if (cartItems.size() == 2) {
            System.out.println("✅ Test Passed! Both products are added to Cart.");
        } else {
            System.out.println("❌ Test Failed! Cart has " + cartItems.size() + " items.");
        }

        Thread.sleep(3000);
        driver.quit();
    }
}