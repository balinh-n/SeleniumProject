package exercise;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic14_Uploadfile {
    WebDriver driver;

    @BeforeMethod
    public void OpenBrowser() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void TC1_UploadFile() throws InterruptedException {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
        String path = System.getProperty("user.dir");
        System.out.println(path);
        String loading = path + "\\image\\" + "loading.jpg";
        String question = path + "\\image\\" + "question.jpg";
        String suprise = path + "\\image\\" + "suprise.jpg";
        driver.findElement(By.xpath("//input[@name='files[]']")).sendKeys(loading);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@name='files[]']")).sendKeys(question);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@name='files[]']")).sendKeys(suprise);
        Thread.sleep(2000);

        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='loading.jpg']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='question.jpg']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='suprise.jpg']")).isDisplayed());

        
        List<WebElement> startbtn = driver.findElements(By.cssSelector("td>button.start"));
        for (WebElement start : startbtn) {
                start.click();
                Thread.sleep(2000);
        }
    
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']//a[@title='loading.jpg']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']//a[@title='question.jpg']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']//a[@title='suprise.jpg']")).isDisplayed());
    }

    @AfterMethod
    public void CloseBrowser() {
        driver.close();
    }
}
