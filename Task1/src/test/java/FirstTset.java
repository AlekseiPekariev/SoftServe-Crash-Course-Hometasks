import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class FirstTset {
    @Test
    public void FirstTest() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "E:\\Java\\CrashCourse\\src\\test\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.softserveinc.com/en-us/");
        driver.manage().window().maximize();
        driver.findElement(By.name("q")).sendKeys("softserve it academy");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
        String nameOfFirstlink = driver.findElement(By.className("LC20lb")).getText();
        Assert.assertEquals(nameOfFirstlink, "Школа програмування : SoftServe провідна IT-компанія");
        driver.findElement(By.className("LC20lb")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://career.softserveinc.com/uk-ua/technology");
        driver.findElement(By.name("search")).sendKeys("TEST AUTOMATION");
        driver.findElement(By.name("search")).sendKeys(Keys.ENTER);
        Thread.sleep(5000);
        driver.manage().timeouts().setScriptTimeout(3, TimeUnit.SECONDS);
        String nameOfCourse = driver.findElement(By.id("vacancy-content")).getText();
        //System.out.println(nameOfCourse);
        Assert.assertEquals(nameOfCourse, "Test Automation with Java, C#, Python Development");
        driver.quit();

    }
}