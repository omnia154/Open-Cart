package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.util.List;

public class BrandProductsPage_TC19 {

    WebDriver driver;

    // Constructor
    public BrandProductsPage_TC19(WebDriver driver) {
        this.driver = driver;
    }

    // --- Locators (عناوين العناصر) ---

    // محدد عام لجميع العلامات التجارية في الشريط الجانبي
    By brands_sidebar = By.xpath("//div[@class='brands-name']");

    // محدد يستهدف جميع روابط العلامات التجارية
    By brand_links = By.xpath("//div[@class='brands-name']//ul/li/a");

    // محدد لعنوان الصفحة الذي يظهر بعد التصفية
    By brand_page_title = By.xpath("//h2[@class='title text-center']");

    // محدد لأسماء المنتجات المعروضة
    By product_names = By.xpath("//div[@class='features_items']//div[@class='productinfo text-center']/p[1]");


    // --- Actions & Assertions (الأفعال والتحققات) ---

    // Step 4: Verify that Brands are visible
    public void verifyBrandsAreVisible() {
        Assert.assertTrue(driver.findElement(brands_sidebar).isDisplayed(),
                "[ERROR] Brands list is not visible on the left sidebar.");
    }

    /**
     * ينقر على علامة تجارية محددة بواسطة فهرسها (Index)، وينظف اسمها، ثم يتحقق من الصفحة.
     * @param brandIndex فهرس العلامة التجارية في القائمة (0 هو العنصر الأول، 1 هو الثاني، إلخ)
     */
    public void clickAndVerifyBrandProducts(int brandIndex) {

        // 1. إعادة إيجاد قائمة العلامات التجارية (للتغلب على خطأ Stale Element)
        List<WebElement> brands = driver.findElements(brand_links);

        // التحقق من وجود العلامة التجارية المطلوبة في القائمة
        Assert.assertTrue(brands.size() > brandIndex,
                "[ERROR] Cannot find brand at index " + brandIndex + ". List size is " + brands.size());

        // 2. تنظيف اسم العلامة التجارية قبل النقر
        // استخدام Regex لإزالة الأرقام والأقواس (مثال: تحويل "Polo (6)" إلى "Polo")
        String brandNameWithCount = brands.get(brandIndex).getText().trim();
        String brandName = brandNameWithCount.replaceAll("\\s*\\(\\d+\\)", ""); // الاسم النظيف الذي سنقارن به

        // 3. النقر على العلامة التجارية المحددة (النقر يتم مرة واحدة فقط!)
        brands.get(brandIndex).click();

        // 4. التحقق من التصفية
        verifyBrandPageNavigation(brandName);
    }

    // دالة خاصة للتحقق من عنوان الصفحة وعرض المنتجات
    public void verifyBrandPageNavigation(String expectedBrandName) {


        // 3.1. التحقق من عنوان الصفحة
        WebElement titleElement = driver.findElement(brand_page_title);
        // تحويل العنوان الفعلي بالكامل إلى حروف كبيرة للمقارنة
        String actualTitle = titleElement.getText().trim().toUpperCase();

        // تحويل الاسم المتوقع (النظيف) إلى حروف كبيرة للمقارنة
        String expectedTitleSegment = expectedBrandName.trim().toUpperCase();

        Assert.assertTrue(titleElement.isDisplayed(), "[ERROR] Brand page title is not visible.");

        // التحقق من أن العنوان الفعلي يحتوي على الاسم النظيف المتوقع
        Assert.assertTrue(actualTitle.contains(expectedTitleSegment),
                "[ERROR] Title does not contain expected brand name: " + expectedTitleSegment + ". Actual title was: " + actualTitle);

        // 3.2. التحقق من عرض المنتجات
        List<WebElement> products = driver.findElements(product_names);

        // التحقق من أن عدد المنتجات الظاهرة أكبر من الصفر
        Assert.assertTrue(products.size() > 0,
                "[ERROR] No products are displayed for brand: " + expectedBrandName);
    }
}