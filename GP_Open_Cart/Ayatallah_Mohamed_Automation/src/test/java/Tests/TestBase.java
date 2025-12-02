package Tests;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {
    public WebDriver driver;

    @BeforeMethod
    public void preconditions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments(new String[]{"--force-device-scale-factor=0.75"});
        this.driver = new ChromeDriver(options);
        this.driver.manage().window().maximize();
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterMethod
    public void tearDown() {
        this.driver.quit();
    }
}
