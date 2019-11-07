import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class IEDriverManager extends DriverManager {
    protected void startService() {
        WebDriverManager.iedriver().setup();

    }


    protected void createDriver() {
        driver = new InternetExplorerDriver();

    }
}
