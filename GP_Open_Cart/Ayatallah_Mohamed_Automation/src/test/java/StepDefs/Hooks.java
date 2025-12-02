
package StepDefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class Hooks {
    public static WebDriver driver;

    @Before
    public void preconditions() {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5L));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}