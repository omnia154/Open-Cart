import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class TC18_ViewCategory {
    public static void main(String[] args) throws InterruptedException {
        // --- Test Case 18: View Category Products ---

        // 1. Setup
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        // 2. Open URL
        driver.get("https://automationexercise.com/");
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // 3. Verify that categories are visible on left side bar
        WebElement categoryTitle = driver.findElement(By.xpath("//h2[normalize-space()='Category']"));
        if(categoryTitle.isDisplayed()) {
            System.out.println("✅ Step 1: Categories are visible");
        }

        // Scroll down to see categories
        js.executeScript("window.scrollBy(0, 500)");

        // 4. Click on 'Women' category
        WebElement womenCategory = driver.findElement(By.xpath("//a[normalize-space()='Women']"));
        js.executeScript("arguments[0].click();", womenCategory);

        Thread.sleep(1000); // استنى القائمة تفتح

        // 5. Click on 'Dress'
        WebElement dressLink = driver.findElement(By.xpath("//div[@id='Women']//a[contains(text(),'Dress')]"));
        js.executeScript("arguments[0].click();", dressLink);

        // 6. Verify 'WOMEN - DRESS PRODUCTS'
        WebElement headerText = driver.findElement(By.xpath("//h2[@class='title text-center']"));
        if(headerText.getText().contains("WOMEN - DRESS PRODUCTS")) {
            System.out.println("✅ Step 2: Women - Dress page verified");
        } else {
            System.out.println("❌ Failed: Wrong page opened -> " + headerText.getText());
        }

        // --- التعديل الجذري هنا ---
        // الصفحة بتعمل Reload كامل، لازم نستنى وقت كافي عشان القوائم تحمل تاني
        System.out.println("⏳ Waiting for page stability...");
        Thread.sleep(5000);

        // 7. Click on 'Men' category
        WebElement menCategory = driver.findElement(By.xpath("//a[normalize-space()='Men']"));
        // نتأكد إنه ظاهر قدامنا
        js.executeScript("arguments[0].scrollIntoView(true);", menCategory);
        Thread.sleep(1000);
        js.executeScript("arguments[0].click();", menCategory);

        Thread.sleep(2000); // استنى القائمة بتاعت Men تفتح

        // 8. Click on 'Tshirts' (لاحظي الفرق: Tshirts مش T-Shirts)
        // الموقع كاتبه Tshirts من غير شرطة
        WebElement tshirtsLink = driver.findElement(By.xpath("//a[contains(text(),'Tshirts')]"));
        js.executeScript("arguments[0].click();", tshirtsLink);

        // 9. Verify 'MEN - TSHIRTS PRODUCTS'
        Thread.sleep(3000);
        WebElement headerTextMen = driver.findElement(By.xpath("//h2[@class='title text-center']"));
        if(headerTextMen.getText().contains("MEN - TSHIRTS PRODUCTS")) {
            System.out.println("✅ Step 3: Men - T-Shirts page verified");
        } else {
            System.out.println("❌ Failed: Men page did not open correctly.");
        }

        Thread.sleep(3000);
        driver.quit();
    }
}