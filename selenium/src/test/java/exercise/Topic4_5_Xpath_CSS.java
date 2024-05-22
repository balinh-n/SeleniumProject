package exercise;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic4_5_Xpath_CSS {
    WebDriver driver;
    By fullName = By.xpath("//input[@id='txtFirstname']");
    By email = By.xpath("//input[@id='txtEmail']");
    By cfmEmail = By.xpath("//input[@id='txtCEmail']");
    By pass = By.xpath("//input[@id='txtPassword']");
    By cfmPass = By.xpath("//input[@id='txtCPassword']");
    By phoneNumber = By.xpath("//input[@id='txtPhone']");
    By RegisterBtn = By.xpath("//button[@type='submit']");

    @BeforeMethod
    public void openBrowser() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
    }
    
    @Test
    public void TC1_EmptyData() {
        driver.findElement(RegisterBtn).click();
        
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtFirstname-error']")).getText(), "Vui lòng nhập họ tên");
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtEmail-error']")).getText(), "Vui lòng nhập email");
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText(), "Vui lòng nhập lại địa chỉ email");
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPassword-error']")).getText(), "Vui lòng nhập mật khẩu");
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(), "Vui lòng nhập lại mật khẩu");
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText(), "Vui lòng nhập số điện thoại.");
    }

    @Test
    public void TC2_InvalidEmail() {
        driver.findElement(fullName).sendKeys("Jack Sparrow");
        driver.findElement(email).sendKeys("a@gmail.com..");
        driver.findElement(cfmEmail).sendKeys("a@gmail.com..");
        driver.findElement(pass).sendKeys("abcd1234");
        driver.findElement(cfmPass).sendKeys("abcd1234");
        driver.findElement(phoneNumber).sendKeys("0351235355");
        driver.findElement(RegisterBtn).click();

        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtEmail-error']")).getText(), "Vui lòng nhập email hợp lệ");
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText(), "Email nhập lại không đúng");
    }
    @Test
    public void TC3_CfmEmailIncorrect() {
        driver.findElement(fullName).sendKeys("Jack Sparrow");
        driver.findElement(email).sendKeys("abcd1234@gmail.com");
        driver.findElement(cfmEmail).sendKeys("a@gmail.com");
        driver.findElement(pass).sendKeys("abcd1234");
        driver.findElement(cfmPass).sendKeys("abcd1234");
        driver.findElement(phoneNumber).sendKeys("0351235355");
        driver.findElement(RegisterBtn).click();
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText(), "Email nhập lại không đúng");
    }
    
    @Test
    public void TC4_InvalidPass() {
        driver.findElement(fullName).sendKeys("Jack Sparrow");
        driver.findElement(email).sendKeys("abcd1234@gmail.com");
        driver.findElement(cfmEmail).sendKeys("abcd1234@gmail.com");
        driver.findElement(pass).sendKeys("abcd");
        driver.findElement(cfmPass).sendKeys("abcd");
        driver.findElement(phoneNumber).sendKeys("0351235355");
        driver.findElement(RegisterBtn).click();

        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPassword-error']")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
    }

    @Test
    public void TC5_InvalidCfmPass() {
        driver.findElement(fullName).sendKeys("Jack Sparrow");
        driver.findElement(email).sendKeys("abcd1234@gmail.com");
        driver.findElement(cfmEmail).sendKeys("abcd1234@gmail.com");
        driver.findElement(pass).sendKeys("abcd123412");
        driver.findElement(cfmPass).sendKeys("abcd1234");
        driver.findElement(phoneNumber).sendKeys("0351235355");
        driver.findElement(RegisterBtn).click();
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(), "Mật khẩu bạn nhập không khớp");
    }

    @Test
    public void TC6_InvalidPhone() {
        driver.findElement(fullName).sendKeys("Jack Sparrow");
        driver.findElement(email).sendKeys("abcd1234@gmail.com");
        driver.findElement(cfmEmail).sendKeys("abcd1234@gmail.com");
        driver.findElement(pass).sendKeys("abcd123412");
        driver.findElement(cfmPass).sendKeys("abcd123412");
        driver.findElement(phoneNumber).sendKeys("095123");
        driver.findElement(RegisterBtn).click();
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText(), "Số điện thoại phải từ 10-11 số.");

        driver.findElement(phoneNumber).clear();
        driver.findElement(phoneNumber).sendKeys("015325");
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");
    }
    @AfterMethod
    public void closeBrowser() {
        driver.close();
    }

}
