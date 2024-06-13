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

public class Topic11_PopUp_Iframe_Window {
    

WebDriver driver;

    @BeforeMethod
    public void openBrowser() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC1_Fixed_Popup() {
        driver.get("https://ngoaingu24h.vn/");
        driver.findElement(By.cssSelector("button.login_.icon-before"));
        WebElement signInPopup = driver.findElement(By.cssSelector("div.modal.fade.in div.modal-content"));
        Assert.assertTrue(signInPopup.isDisplayed());
        driver.findElement(By.cssSelector("input#account-input")).sendKeys("automation");
        driver.findElement(By.cssSelector("input#password-input")).sendKeys("automation");
        driver.findElement(By.cssSelector("button.btn-login-v1")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div.row error-login-panel")), "Tài khoản không tồn tại!");
        driver.findElement(By.cssSelector("button.close"));
        Assert.assertFalse(signInPopup.isDisplayed());
    }

    @Test
    public void TC2_FixedPopup() throws InterruptedException {
        driver.get("https://skills.kynaenglish.vn/dang-nhap");
        WebElement signIn = driver.findElement(By.cssSelector("div.modal-content div.k-popup-account-mb-content"));
        Assert.assertTrue(signIn.isDisplayed());

        driver.findElement(By.cssSelector("input#user-login")).sendKeys("automation@gmail.com");
        driver.findElement(By.cssSelector("input#user-password")).sendKeys("123456");
        driver.findElement(By.cssSelector("button#btn-submit-login")).click();
        WebElement errorMessage = driver.findElement(By.cssSelector("div#password-form-login-message"));
        Thread.sleep(3000);
        Assert.assertTrue(errorMessage.isDisplayed());
        Assert.assertEquals(errorMessage.getText(), "Sai tên đăng nhập hoặc mật khẩu");
    }

    @Test
    public void TC3_FixedPopupNotInDom() throws InterruptedException{
        driver.get("https://tiki.vn/");

        WebElement ads = driver.findElement(By.cssSelector("div.styles__PopupContent-sc-1e705dn-2"));
        if (ads.isDisplayed()) {
            driver.findElement(By.xpath("//picture[@class='webpimg-container']/img[@alt='close-icon']")).click();
        }
        
        driver.findElement(By.xpath("//span[text()='Tài khoản']")).click();
        WebElement signIn = driver.findElement(By.cssSelector("div.styles__Root-sc-2hr4xa-0.jyAQAr"));
        Assert.assertTrue(signIn.isDisplayed());
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("p.login-with-email")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Email không được để trống']")).getText(),"Email không được để trống");
        Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Mật khẩu không được để trống']")).getText(),"Mật khẩu không được để trống");
        driver.findElement(By.cssSelector("button.btn-close")).click();
        List<WebElement> sign1n = driver.findElements(By.cssSelector("div.styles__Root-sc-2hr4xa-0.jyAQAr"));
        Assert.assertEquals(sign1n.size(),0);
    }


    public void TC4_FixedPopupNotInDom() {
        driver.get("https://www.facebook.com/");

        driver.findElement(By.cssSelector("a._42ft._4jy0._6lti._4jy6._4jy2.selected._51sy")).click();
        driver.findElement(By.xpath(""));
    }
    @AfterMethod
    public void closeBrowser() {
        driver.close();
    }
}
