import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxDriverManager extends DriverManager {

    protected void startService() {
        WebDriverManager.firefoxdriver().setup();

    }

    protected void createDriver() {
        driver = new FirefoxDriver();

    }
}
