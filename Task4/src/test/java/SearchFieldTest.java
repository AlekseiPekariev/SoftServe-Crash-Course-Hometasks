import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SearchFieldTest {
    private WebDriver driver;
    private WebDriverWait waiter;
    private DriverManager driverManager;

    List<WebElement> searchResultsTitleList;
    List<WebElement> searchResultsDescriptionList;

    private String homePageLink = "https://www.softserveinc.com/uk-ua/";
    private String searchString = "security";

    // Open Browser
    //@Parameters("browser")
    @BeforeSuite
    public void openBrowser() {
        //if (browser.equalsIgnoreCase("chrome")) {
        //driverManager = DriverManagerFactory.getManager(DriverType.CHROME);
        //waiter = new WebDriverWait(driver, 1);
        //     }
        // else if (browser.equalsIgnoreCase("firefox")){
        driverManager = DriverManagerFactory.getManager(DriverType.FIREFOX);
        driver = driverManager.getDriver();
        waiter = new WebDriverWait(driver, 1);

        // }
        //System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        //WebDriverManager.chromedriver().setup();
        //driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }


    // Open Home Page
    @BeforeClass
    public void goToHomePage() {
        driver.get(homePageLink);
    }

    // Maximize window
    @BeforeTest
    public void fullScreen() {
        driver.manage().window().maximize();
    }

    // Finding the search field and filling with search string.
    @Test(priority = 0,
            enabled = true,
            groups = {"search_field_test"},
            timeOut = 200000)
    public void searchEntering() {
        driver.navigate().refresh();

        waiter.until(ExpectedConditions.invisibilityOfElementLocated(By.id("page-preloader")));
        WebElement searchIcon = driver.findElement(By.xpath("//a[@aria-label='Search']"));
        searchIcon.click();

        waiter.until(ExpectedConditions.invisibilityOfElementLocated(By.id("page-preloader")));
        WebElement searchInput = driver.findElement(By.xpath("//input[@type='text' and @maxlength=1000]"));
        searchInput.sendKeys(searchString);

        WebElement searchButton = driver.findElement(By.xpath("//input[@type='submit' and @value=' Пошук']"));
        searchButton.click();
    }

    // Checking if any results are shown
    @Test(priority = 1,
            enabled = true,
            groups = {"search_field_test"},
            dependsOnMethods = "searchEntering",
            timeOut = 2000)
    public void resultCountCheck() {
        searchResultsTitleList = driver.findElements(By.xpath("//h2[@class='search-result-title']"));

        Assert.assertEquals(searchResultsTitleList.size() > 0, true);
    }

    // Checking searching results content
    @Test(priority = 2,
            enabled = true,
            groups = {"search_field_test"},
            dependsOnMethods = "searchEntering",
            timeOut = 2000)
    public void searchContentCheck() {
        searchResultsTitleList = driver.findElements(By.xpath("//h2[@class='search-result-title']"));
        searchResultsDescriptionList = driver.findElements(By.xpath("//h2[@class='search-result-description']"));
        List<String> resultsTitleTextList = new ArrayList<String>();
        List<String> resultsDescriptionTextList = new ArrayList<String>();

        boolean booleanResult = true;

        for (int i = 0; i < searchResultsTitleList.size(); i++) {
            resultsTitleTextList.add(searchResultsTitleList.get(i).getText());
        }
        for (int i = 0; i < searchResultsDescriptionList.size(); i++) {
            resultsDescriptionTextList.add(searchResultsDescriptionList.get(i).getText());
        }

        for (int i = 0; i < searchResultsDescriptionList.size(); i++) {
            if (!(resultsTitleTextList.get(i).toLowerCase().contains(searchString.toLowerCase()))
                    || !(resultsDescriptionTextList.get(i).toLowerCase().contains(searchString.toLowerCase()))) {
                booleanResult = false;
                break;
            }
        }

        Assert.assertEquals(booleanResult, true);
    }

    // Closing the driver
    @AfterClass
    public final void closingBrowser() {
        driverManager.quitDriver();
    }
}