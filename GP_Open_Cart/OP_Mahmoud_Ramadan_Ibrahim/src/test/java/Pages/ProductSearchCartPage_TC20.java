package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.util.List;
import java.util.ArrayList;

public class ProductSearchCartPage_TC20 {

    WebDriver driver;

    public ProductSearchCartPage_TC20(WebDriver driver) {
        this.driver = driver;
    }

    // --- Locators (عناوين العناصر) ---

    // الأزرار العامة
    By products_btn = By.xpath("//a[contains(text(), ' Products')]");
    By cart_btn = By.xpath("//a[contains(text(), ' Cart')]");
    By login_btn = By.xpath("//a[contains(text(), ' Signup / Login')]");

    // محددات صفحة المنتجات والبحث
    By allProducts_title = By.xpath("//h2[contains(text(), 'All Products')]");
    By search_input = By.id("search_product");
    By search_btn = By.id("submit_search");
    By searchedProducts_title = By.xpath("//h2[contains(text(), 'Searched Products')]");

    // محددات المنتجات في صفحة البحث
    By product_overlay_xpath = By.xpath("//div[@class='features_items']//div[@class='product-overlay']");
    By product_names_xpath = By.xpath("//div[@class='features_items']//div[@class='productinfo text-center']/p[1]");

    // محددات سلة التسوق
    By cart_product_rows = By.xpath("//table[@id='cart_info_table']/tbody/tr");
    By login_email_input = By.xpath("//input[@data-qa='login-email']");
    By login_password_input = By.xpath("//input[@data-qa='login-password']");
    By login_submit_btn = By.xpath("//button[@data-qa='login-button']");
    By logged_in_username = By.xpath("//a/b");

    // --- Actions & Assertions (الأفعال والتحققات) ---

    // Step 3 & 4
    public void navigateToProductsPage() {
        driver.findElement(products_btn).click();
        Assert.assertTrue(driver.findElement(allProducts_title).isDisplayed(),
                "[ERROR] Failed to navigate to ALL PRODUCTS page.");
    }

    // Step 5, 6, 7
    public List<String> searchAndVerify(String productName) {
        driver.findElement(search_input).sendKeys(productName);
        driver.findElement(search_btn).click();

        // Step 6: Verify 'SEARCHED PRODUCTS' is visible
        Assert.assertTrue(driver.findElement(searchedProducts_title).isDisplayed(),
                "[ERROR] 'SEARCHED PRODUCTS' title is not visible.");

        // Step 7: Verify products are visible and collect their names
        List<WebElement> productElements = driver.findElements(product_names_xpath);
        Assert.assertTrue(productElements.size() > 0, "[ERROR] No products found for the search: " + productName);

        List<String> foundProductNames = new ArrayList<>();
        for (WebElement product : productElements) {
            String name = product.getText().trim();
            // يجب التأكد من أن اسم المنتج يحتوي على كلمة البحث (للتأكد من دقة البحث)
            Assert.assertTrue(name.toLowerCase().contains(productName.toLowerCase()), "[ERROR] Product name mismatch.");
            foundProductNames.add(name);
        }
        return foundProductNames;
    }

    // Step 8: Add all searched products to the cart
    public void addAllFoundProductsToCart() {
        List<WebElement> productContainers = driver.findElements(By.xpath("//div[@class='single-products']"));

        System.out.println("counter started");
        for (int i = 0; i < productContainers.size(); i++) {
            // نبدأ العد من 1 لأن محددات الـ XPath للقوائم تبدأ من 1
            String productXPath = String.format("(//div[@class='single-products'])[%d]//a[contains(text(),'Add to cart')]", i + 1);
            WebElement addToCartButton = driver.findElement(By.xpath(productXPath));

            System.out.println(i);
            // النقر على زر الإضافة للسلة
            addToCartButton.click();

            // انتظار ظهور مودال "Product added" ثم النقر على "Continue Shopping"
            WebElement continueShoppingButton = driver.findElement(By.xpath("//button[contains(text(), 'Continue Shopping')]"));
            continueShoppingButton.click();
        }
    }

    // Step 9: Verify products in cart (before login)
    public void verifyProductsInCart(List<String> expectedProducts) {
        driver.findElement(cart_btn).click();

        List<WebElement> cartRows = driver.findElements(cart_product_rows);
        Assert.assertEquals(cartRows.size(), expectedProducts.size(),
                "[ERROR] Mismatch in number of items in cart.");

        List<String> actualCartNames = new ArrayList<>();
        for (WebElement row : cartRows) {
            // محدد اسم المنتج في جدول السلة
            String productName = row.findElement(By.xpath(".//td[@class='cart_description']/h4/a")).getText().trim();
            actualCartNames.add(productName);
        }

        // التحقق من أن جميع المنتجات المتوقعة موجودة في السلة
        Assert.assertTrue(actualCartNames.containsAll(expectedProducts), "[ERROR] Not all searched products are present in the cart.");
    }

    // Step 10: Click login and submit details
    public void loginUser(String email, String password) {
        driver.findElement(login_btn).click();
        driver.findElement(login_email_input).sendKeys(email);
        driver.findElement(login_password_input).sendKeys(password);
        driver.findElement(login_submit_btn).click();

        // التحقق من أن تسجيل الدخول نجح
        Assert.assertTrue(driver.findElement(logged_in_username).isDisplayed(),
                "[ERROR] Login failed. Username not displayed.");
    }
}