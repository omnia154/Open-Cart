package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class ProductPage {
    WebDriver driver;
    JavascriptExecutor js;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
    }

    // --- Locators
    By productsLink = By.partialLinkText("Products");
    By addFirstProductBtn = By.xpath("(//a[@data-product-id='1' and contains(@class,'add-to-cart')])[2]");
    By continueBtn = By.xpath("//button[text()='Continue Shopping']");
    By addSecondProductBtn = By.xpath("(//a[@data-product-id='2' and contains(@class,'add-to-cart')])[2]");
    By viewCartLink = By.partialLinkText("View Cart");
    By viewProductBtn = By.xpath("(//a[contains(text(),'View Product')])[1]");
    By quantityInput = By.id("quantity");
    By addToCartBtnDetails = By.cssSelector("button.cart");
    By cartQuantityCell = By.cssSelector("td.cart_quantity button");
    By removeBtn = By.className("cart_quantity_delete");
    By emptyCartMsg = By.id("empty_cart");
    By cartRows = By.xpath("//tbody/tr");
    By categoryTitle = By.xpath("//h2[normalize-space()='Category']");
    By womenCategory = By.xpath("//a[normalize-space()='Women']");
    By dressLink = By.xpath("//div[@id='Women']//a[contains(text(),'Dress')]");
    By menCategory = By.xpath("//a[normalize-space()='Men']");
    By tshirtsLink = By.xpath("//a[normalize-space()='Tshirts']");
    By pageTitleText = By.xpath("//h2[@class='title text-center']");
    By reviewHeader = By.xpath("//a[normalize-space()='Write Your Review']");
    By nameInput = By.id("name");
    By emailInput = By.id("email");
    By reviewInput = By.id("review");
    By submitReviewBtn = By.id("button-review");
    By successReviewMsg = By.xpath("//span[contains(text(),'Thank you for your review')]");
    By allProductsList = By.className("features_items");
    By productName = By.xpath("//div[@class='product-information']//h2");
    By productCategory = By.xpath("//div[@class='product-information']//p[contains(text(),'Category')]");
    By productPrice = By.xpath("//div[@class='product-information']//span[contains(text(),'Rs.')]");
    By productAvailability = By.xpath("//div[@class='product-information']//b[contains(text(),'Availability')]");
    By productCondition = By.xpath("//div[@class='product-information']//b[contains(text(),'Condition')]");
    By productBrand = By.xpath("//div[@class='product-information']//b[contains(text(),'Brand')]");


    By recommendedTitle = By.xpath("//h2[contains(text(),'recommended items')]");
    By addToCartRecommendedBtn = By.xpath("(//div[@id='recommended-item-carousel']//a[contains(@class,'add-to-cart')])[1]");

    // --- Actions
    public void clickProductsMenu() {
        WebElement btn = driver.findElement(productsLink);
        js.executeScript("arguments[0].click();", btn);
    }

    public void addFirstProduct() {
        js.executeScript("window.scrollBy(0, 400)");
        WebElement btn = driver.findElement(addFirstProductBtn);
        js.executeScript("arguments[0].click();", btn);
    }

    public void clickContinueShopping() {
        try { Thread.sleep(1000); } catch (InterruptedException e) {}
        WebElement btn = driver.findElement(continueBtn);
        js.executeScript("arguments[0].click();", btn);
    }

    public void addSecondProduct() {
        WebElement btn = driver.findElement(addSecondProductBtn);
        js.executeScript("arguments[0].click();", btn);
    }

    public void clickViewCart() {
        try { Thread.sleep(1000); } catch (InterruptedException e) {}
        WebElement btn = driver.findElement(viewCartLink);
        js.executeScript("arguments[0].click();", btn);
    }

    public void clickViewFirstProduct() {
        js.executeScript("window.scrollBy(0, 400)");
        WebElement btn = driver.findElement(viewProductBtn);
        js.executeScript("arguments[0].click();", btn);
    }

    public void setQuantity(String num) {
        WebElement input = driver.findElement(quantityInput);
        input.clear();
        input.sendKeys(num);
    }

    public void addToCartFromDetails() {
        WebElement btn = driver.findElement(addToCartBtnDetails);
        js.executeScript("arguments[0].click();", btn);
    }

    public String getCartQuantity() {
        return driver.findElement(cartQuantityCell).getText();
    }

    public void clickRemoveProduct() {
        WebElement btn = driver.findElement(removeBtn);
        js.executeScript("arguments[0].click();", btn);
    }

    public boolean isCartEmpty() {
        try {
            Thread.sleep(1000);
            WebElement msg = driver.findElement(emptyCartMsg);
            return msg.isDisplayed();
        } catch (Exception e) {
            List<WebElement> rows = driver.findElements(cartRows);
            return rows.isEmpty();
        }
    }

    public boolean isCategoryVisible() {
        return driver.findElement(categoryTitle).isDisplayed();
    }

    public void clickWomenCategory() {
        js.executeScript("window.scrollBy(0, 500)");
        WebElement btn = driver.findElement(womenCategory);
        js.executeScript("arguments[0].click();", btn);
    }

    public void clickDressLink() {
        try { Thread.sleep(1000); } catch (InterruptedException e) {}
        WebElement btn = driver.findElement(dressLink);
        js.executeScript("arguments[0].click();", btn);
    }

    public String getPageTitle() {
        return driver.findElement(pageTitleText).getText();
    }

    public void clickMenCategory() {
        try { Thread.sleep(2000); } catch (InterruptedException e) {}
        WebElement btn = driver.findElement(menCategory);
        js.executeScript("arguments[0].scrollIntoView(true);", btn);
        js.executeScript("arguments[0].click();", btn);
    }

    public void clickTshirtsLink() {
        try { Thread.sleep(1000); } catch (InterruptedException e) {}
        WebElement btn = driver.findElement(tshirtsLink);
        js.executeScript("arguments[0].click();", btn);
    }

    public boolean isReviewHeaderVisible() {
        return driver.findElement(reviewHeader).isDisplayed();
    }

    public void fillReviewForm(String name, String email, String msg) {
        WebElement header = driver.findElement(reviewHeader);
        js.executeScript("arguments[0].scrollIntoView(true);", header);
        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(reviewInput).sendKeys(msg);
    }

    public void clickSubmitReview() {
        WebElement btn = driver.findElement(submitReviewBtn);
        js.executeScript("arguments[0].click();", btn);
    }

    public boolean isSuccessReviewMsgVisible() {
        try {
            Thread.sleep(1000);
            return driver.findElement(successReviewMsg).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isProductListVisible() {
        return driver.findElement(allProductsList).isDisplayed();
    }

    public boolean isProductInfoVisible() {
        return driver.findElement(productName).isDisplayed() &&
                driver.findElement(productCategory).isDisplayed() &&
                driver.findElement(productPrice).isDisplayed() &&
                driver.findElement(productAvailability).isDisplayed() &&
                driver.findElement(productCondition).isDisplayed() &&
                driver.findElement(productBrand).isDisplayed();
    }


    public void scrollToBottom() {
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public boolean isRecommendedVisible() {
        WebElement title = driver.findElement(recommendedTitle);

        js.executeScript("arguments[0].scrollIntoView(true);", title);
        return title.isDisplayed();
    }

    public void addRecommendedProductToCart() {
        try { Thread.sleep(1000); } catch (InterruptedException e) {}
        WebElement btn = driver.findElement(addToCartRecommendedBtn);
        js.executeScript("arguments[0].click();", btn);
    }
}