package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.util.List;

public class ProductsPage_TC9 {

    WebDriver driver;

    // Constructor
    public ProductsPage_TC9(WebDriver driver) {
        this.driver = driver;
    }

    // --- Locators (عناوين العناصر) ---

    // محدد زر 'Products' في شريط التنقل
    By products_btn = By.xpath("//a[contains(text(), ' Products')]");

    // عنوان صفحة 'ALL PRODUCTS'
    By allProducts_title = By.xpath("//h2[contains(text(), 'All Products')]");

    // حقل إدخال البحث
    By search_input = By.id("search_product");

    // زر البحث (عادة يكون أيقونة Magnifying Glass)
    By search_btn = By.id("submit_search");

    // عنوان 'SEARCHED PRODUCTS' الذي يظهر بعد البحث
    By searchedProducts_title = By.xpath("//h2[contains(text(), 'Searched Products')]");

    // محدد عام لجميع أسماء المنتجات الظاهرة في نتائج البحث (لتأكيد ظهورها)
    //By product_names = By.xpath("//div[@class='features_items']//p");
    By product_names = By.xpath("//div[@class='productinfo text-center']/p[1]");

    // --- Actions & Assertions (الأفعال والتحققات) ---

    // Step 4 & 5: النقر على زر المنتجات والتحقق من الوصول لصفحة جميع المنتجات
    public void clickProductsButtonAndVerifyNavigation() {
        driver.findElement(products_btn).click();

        // التحقق من الرابط (URL)
        String currentURL = driver.getCurrentUrl();
        Assert.assertTrue(currentURL.contains("products"), "[ERROR] URL does not contain 'products' segment.");

        // التحقق من ظهور عنوان 'ALL PRODUCTS'
        Assert.assertTrue(driver.findElement(allProducts_title).isDisplayed(), "[ERROR] 'ALL PRODUCTS' title is not visible.");
        Assert.assertEquals(driver.findElement(allProducts_title).getText().trim(), "ALL PRODUCTS", "[ERROR] Title text is incorrect.");
    }

    // Step 6: إدخال اسم المنتج والنقر على زر البحث
    public void searchForProduct(String productName) {
        driver.findElement(search_input).sendKeys(productName);
        driver.findElement(search_btn).click();
    }

    // Step 7 & 8: التحقق من ظهور العنوان والنتائج المتعلقة بالبحث
    public void verifySearchResults(String searchKeyword) {

        // Step 7: التحقق من ظهور عنوان 'SEARCHED PRODUCTS'
        Assert.assertTrue(driver.findElement(searchedProducts_title).isDisplayed(), "[ERROR] 'SEARCHED PRODUCTS' title is not visible after search.");

        // Step 8: التحقق من أن جميع المنتجات الظاهرة مرتبطة بالبحث

        // driver.findElements() ترجع قائمة (List) من جميع العناصر المطابقة للمحدد
        List<WebElement> foundProducts = driver.findElements(product_names);

        // التحقق أولاً من وجود نتائج على الأقل
        Assert.assertTrue(foundProducts.size() > 0, "[ERROR] No products found for the search keyword: " + searchKeyword);

        // التحقق من محتوى كل نتيجة
        for (WebElement product : foundProducts) {
            String productName = product.getText().toLowerCase();
            // التحقق من أن اسم المنتج يحتوي على كلمة البحث (بصرف النظر عن حالة الحروف)
            Assert.assertTrue(productName.contains(searchKeyword.toLowerCase()),
                    "[ERROR] Product '" + productName + "' does not contain the search keyword: " + searchKeyword);
        }
    }
}