import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

public class ChromeDriverManager extends DriverManager {


    @Override
    public void startService() {
        WebDriverManager.chromedriver().setup();
    }

    @Override
    public void createDriver() {
        driver = new ChromeDriver();
    }


}
