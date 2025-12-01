import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class TC13_ProductQuantity {
    public static void main(String[] args) throws InterruptedException {
        // --- Test Case 13: Verify Product quantity in Cart ---

        // 1. Setup
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        // 2. Open URL
        driver.get("https://automationexercise.com/");

        // 3. Verify that home page is visible successfully
        String title = driver.getTitle();
        if(title.equals("Automation Exercise")) {
            System.out.println("✅ Step 1: Home page visible");
        }

        // 4. Click 'View Product' for any product on home page (e.g., the first one)
        // بنختار أول منتج ونضغط على تفاصيله
        driver.findElement(By.xpath("(//a[contains(text(),'View Product')])[1]")).click();

        // 5. Verify product detail is opened
        if(driver.getCurrentUrl().contains("product_details")) {
            System.out.println("✅ Step 2: Product details page opened");
        }

        // 6. Increase quantity to 4
        // فيه حقل input اسمه quantity بنكتب فيه الرقم
        WebElement quantityInput = driver.findElement(By.id("quantity"));
        quantityInput.clear(); // نمسح الرقم القديم (1)
        quantityInput.sendKeys("4"); // نكتب 4
        System.out.println("ℹ️ Quantity set to 4...");

        // 7. Click 'Add to cart' button
        driver.findElement(By.cssSelector("button.cart")).click();

        // 8. Click 'View Cart' button
        // بنستنى المودال يظهر وندوس عليه
        driver.findElement(By.xpath("//u[text()='View Cart']")).click();

        // 9. Verify that product is displayed in cart page with exact quantity '4'
        // بنروح لصفحة الكارت وندور على الزرار اللي فيه الكمية
        WebElement cartQuantity = driver.findElement(By.cssSelector("td.cart_quantity button"));
        String quantityText = cartQuantity.getText();

        if(quantityText.equals("4")) {
            System.out.println("✅ Test Passed! Product quantity is correct: " + quantityText);
        } else {
            System.out.println("❌ Test Failed! Expected 4 but found: " + quantityText);
        }

        Thread.sleep(3000);
        driver.quit();
    }
}
