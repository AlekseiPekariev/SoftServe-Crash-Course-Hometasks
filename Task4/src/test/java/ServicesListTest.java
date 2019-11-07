import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ServicesListTest {
    private WebDriver driver;
    private String homePageLink = "https://www.softserveinc.com/uk-ua/";
    private List<String> referenceServicesList;
    private List<WebElement> foundServicesList;
    private List<String> formattedServicesList;

    // Open Browser
    @BeforeSuite
    public void openBrowser() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        referenceServicesList = new ArrayList<String>
                (Arrays.asList("програмне забезпечення",
                        "хмарні технології", "великі дані",
                        "штучний інтелект",
                        "інтернет речей (iot)",
                        "сервісний дизайн",
                        "кібербезпека",
                        "цифрові платформи",
                        "розширена реальність (xr)"));
    }

    // Open Home Page
    @BeforeClass
    public void goToHomePage() {
        driver.get(homePageLink);
    }

    // Switch On the full screen mode(just for test)
    @BeforeTest
    public void fullScreen() {
        driver.manage().window().fullscreen();
    }

    // Filling the list of services
    @Test(priority = 0,
            enabled = true,
            groups = {"services_list_test"},
            timeOut = 2000)
    public void servicesListFilling() {
        foundServicesList = driver.findElements(By.className("services-nav-link"));
    }

    // Checking the services list size
    @Test(priority = 1,
            enabled = true,
            groups = {"services_list_test"},
            timeOut = 2000,
            dependsOnMethods = "servicesListFilling")
    public void sizeCheck() {
        Assert.assertEquals(foundServicesList.size(), referenceServicesList.size());
    }

    // Checking Services content
    @Test(priority = 1,
            enabled = true,
            groups = {"services_list_test"},
            timeOut = 2000,
            dependsOnMethods = "sizeCheck")
    public void serviceListsComparison() {
        formattedServicesList = new ArrayList<String>();

        for (WebElement service : foundServicesList) {
            formattedServicesList.add(service.getText().toLowerCase());
        }

        Assert.assertEquals(formattedServicesList, referenceServicesList);
    }

    // Closing the driver
    @AfterClass
    public void closingBrowser() {
        driver.quit();
    }
}