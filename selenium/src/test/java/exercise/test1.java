package exercise;

import java.time.Duration;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * test1
 */
public class test1 {
    WebDriver driver;
    FluentWait<WebDriver> wFluentWait;
    
    @BeforeMethod
    public void OpenBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
       
    }

    @Test
    public void TC8() {
        wFluentWait = new FluentWait<WebDriver>(driver);
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.cssSelector("div#start button")).click();

        wFluentWait.withTimeout(Duration.ofSeconds(30))
        .pollingEvery(Duration.ofSeconds(1))
        .ignoring(NoSuchElementException.class);

        wFluentWait.until(new Function<WebDriver,Boolean>() {

            @Override
            public Boolean apply(WebDriver t) {
              return t.findElement(By.cssSelector("div#finish h4")).isDisplayed();

            }
            
        });
    }
    
    @AfterMethod
    public void CloseBrowser() {
        driver.close();
    }
}