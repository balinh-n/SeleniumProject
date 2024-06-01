package exercise;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic9_Button_Radio_Checkbox_Alert {

    WebDriver driver;

    @BeforeMethod
    public void openBrowser() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();

    }

    @Test
    public void TC2_CheckBox_Radio() throws InterruptedException {

        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebElement dualzone = driver
                .findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input"));
        WebElement dual_zone = driver.findElement(By.xpath("//input[@id='eq5']/parent::span"));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        js.executeScript("window.scrollBy(0,250)", "");
        if (!dualzone.isSelected()) {
            // wait.until(ExpectedConditions.elementToBeClickable(heated));
            dual_zone.click();
        }
        Assert.assertTrue(dualzone.isSelected());
        Thread.sleep(5000);
        if (dualzone.isSelected()) {
            dual_zone.click();
        }
        Assert.assertFalse(dualzone.isSelected());

        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
        WebElement fuel = driver.findElement(By.xpath("//input[@id='engine3']/parent::span"));
        WebElement fuel_radio = driver
                .findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::span/input"));
        js.executeScript("window.scrollBy(0,250)", "");
        if (!fuel_radio.isSelected()) {
            wait.until(ExpectedConditions.elementToBeClickable(fuel));
            fuel.click();
        }
        Assert.assertTrue(fuel_radio.isSelected());
    }

    @Test
    public void TC3_Material_Angular() {
        driver.get("https://material.angular.io/components/radio/examples");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        
        WebElement summer_radio = driver.findElement(By.xpath("//label[text()='Summer']/preceding-sibling::div/input"));
        js.executeScript("arguments[0].scrollIntoView();", summer_radio);
        Assert.assertFalse(summer_radio.isSelected());
        if (!summer_radio.isSelected()) {
            
            summer_radio.click();
        }
        Assert.assertTrue(summer_radio.isSelected());
    }

    @AfterMethod
    public void closeBrowser() {
        driver.close();
    }
}
