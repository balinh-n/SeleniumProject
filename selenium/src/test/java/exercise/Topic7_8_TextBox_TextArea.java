package exercise;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class Topic7_8_TextBox_TextArea {
    WebDriver driver;
    @BeforeMethod
    public void openBrowser() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    public void TC1_LiveTechPanda() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("Jack");
        driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys("Sparrow");
        String email = randomText();
        driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(email);
        driver.findElement(By.xpath(email));
    }
    
    public String randomText() {
        String random = RandomStringUtils.randomAlphanumeric(7);
        return random.concat("@gmail.comm");
    }
    @AfterMethod
    public void closeBrowser() {
        driver.close();
    }
}
