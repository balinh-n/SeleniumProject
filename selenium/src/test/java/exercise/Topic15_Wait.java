package exercise;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic15_Wait {
    WebDriver driver;
    WebDriverWait webDriverWait;
    FluentWait<WebDriver> fluentDriver;
    FluentWait<WebElement> fluentElement;
    @BeforeMethod
    public void OpenBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void TC2_ImplicitWait() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div#start button")).click();
        driver.findElement(By.cssSelector("div#finish>h4")).isDisplayed();

    }

    @Test
    public void TC3_StaticWait() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.cssSelector("div#start button")).click();
        sleep(5);

        Assert.assertTrue(driver.findElement(By.cssSelector("div#finish h4")).isDisplayed());
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish h4")).getText(), "Hello World!");
    }

    @Test
    public void TC4_ExplicitWait() {
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.cssSelector("div#start button")).click();
        webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish h4")));
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish h4")).getText(), "Hello World!");
    }

    @Test
    public void TC5_ExplicitWait() {
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.cssSelector("div#start button")).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish h4")));
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish h4")).getText(), "Hello World!");
    }

    @Test
    public void TC6_ExplicitWait() {

        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.get(
                "https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
        webDriverWait
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.RadAjaxPanel.inlinePanel")));

        Assert.assertEquals(driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")).getText(),
                "No Selected Dates to display.");
        List<WebElement> dates = driver.findElements(By.cssSelector("tbody tr.rcRow td a"));
        for (WebElement date : dates) {
            if (date.getText().equals("8")) {
                date.click();
            }
        }

        webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.raDiv")));
        webDriverWait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='rcSelected']//a[text()='8']")));

        Assert.assertEquals(driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")).getText(),
                "Monday, July 8, 2024");

    }

    @Test
    public void TC7_ExplicitWait() {
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.get("https://gofile.io/welcome");
        webDriverWait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.btn.btn-secondary.btn-lg")));
        driver.findElement(By.cssSelector("button.btn.btn-secondary.btn-lg")).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//button[@class='btn btn-primary btn-lg mb-1 filesUploadButton']")));

        String path = System.getProperty("user.dir") + "\\image\\loading.jpg";
        driver.findElement(By.cssSelector("input#filesUploadInput")).sendKeys(path);

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.col-6.text-center a")));
        driver.findElement(By.cssSelector("div.col-6.text-center a")).click();
        webDriverWait
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span#filesContentFolderName")));
        driver.findElement(By.xpath("//span[text()='Close']//parent::button")).click();
        webDriverWait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.contentLink button.text-white")));
        webDriverWait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Play']//parent::button")));
        Assert.assertTrue(driver.findElement(By.cssSelector("a.contentLink button.text-white")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Play']//parent::button")).isDisplayed());

    }

    @Test
    public void TC8_FluentWait() {
        driver.get("https://automationfc.github.io/fluent-wait/");

        WebElement countDown = driver.findElement(By.cssSelector("div#javascript_countdown_time"));
        fluentElement = new FluentWait<WebElement>(countDown);

        fluentElement.withTimeout(Duration.ofSeconds(15))
        .pollingEvery(Duration.ofSeconds(1))
        .ignoring(NoSuchElementException.class);

        fluentElement.until(new Function<WebElement,Boolean>() {

            @Override
            public Boolean apply(WebElement t) {
                String text = t.getText();
                return text.endsWith("00"); 
            }
            
        });
    }

    @Test
    public void TC9_FluentWait() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.xpath("//button[text()='Start']")).click();

        fluentDriver = new FluentWait<WebDriver>(driver);

        fluentDriver.withTimeout(Duration.ofSeconds(30))
        .pollingEvery(Duration.ofMillis(100))
        .ignoring(NoSuchElementException.class);

        fluentDriver.until(new Function<WebDriver,Boolean>() {

            @Override
            public Boolean apply(WebDriver webDriver) {
                return webDriver.findElement(By.xpath("//h4")).isDisplayed();
            }
            
        });
    }


    public void sleep(int num) {
        try {
            Thread.sleep(num * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void CloseBrowser() {
        driver.close();
    }
}
