package exercise;

import java.time.Duration;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic13_JavaScriptExercutor {
    WebDriver driver;
    JavascriptExecutor js;
    
    @BeforeMethod
    public void OpenBrowser() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC1_JSExecutor() {
        driver.get("http://live.techpanda.org/");
        js = (JavascriptExecutor)driver;
        String domain = (String) js.executeScript("return document.domain");
        Assert.assertEquals(domain,"live.techpanda.org");
        String title = (String) js.executeScript("return document.URL");
        Assert.assertEquals(title, "http://live.techpanda.org/");
        
        WebElement mobileCLick = driver.findElement(By.xpath("//a[text()='Mobile']"));
        js.executeScript("arguments[0].click();", mobileCLick);

        WebElement samsung = driver.findElement(By.xpath("//a[text()='Samsung Galaxy']//parent::h2//following-sibling::div/button"));
        js.executeScript("arguments[0].click();", samsung);
        
        String ms =js.executeScript("return document.documentElement.innerText;").toString();
        Assert.assertTrue(ms.contains("Samsung Galaxy was added to your shopping cart."));

        WebElement customerService = driver.findElement(By.xpath("//a[text()='Customer Service']"));
        js.executeScript("arguments[0].click();", customerService);
        String customerTitle = (String) js.executeScript("return document.title");
        Assert.assertEquals(customerTitle, "Customer Service");

        WebElement newletter = driver.findElement(By.cssSelector("input#newsletter"));
        js.executeScript("arguments[0].scrollIntoView();", newletter);
        String email = RandomStringUtils.randomAlphanumeric(5);
        newletter.sendKeys(email + "@gmail.com");
        driver.findElement(By.xpath("//button[@title='Subscribe']")).click();
        String subcribe =js.executeScript("return document.documentElement.innerText;").toString();
        Assert.assertTrue(subcribe.contains("Thank you for your subscription."));

        js.executeScript("window.location = arguments[0]", "https://www.facebook.com/");
        String newdomain = (String) js.executeScript("return document.domain;");
        Assert.assertEquals(newdomain, "facebook.com");
    }


    @Test
    public void TC2_HTML5Validation() throws InterruptedException{
        String Url = "http://live.techpanda.org/";
        js = (JavascriptExecutor)driver;
        js.executeScript("window.location = arguments[0]", Url);

        WebElement accountbtn = driver.findElement(By.xpath("//span[@class='icon']//following-sibling::span[text()='Account']"));
        clickElement(accountbtn);

        WebElement myaccountbtn = driver.findElement(By.xpath("//div[@id='header-account']//a[@title='My Account']"));
        clickElement(myaccountbtn);

        WebElement createaccount = driver.findElement(By.xpath("//a[@title='Create an Account']"));
        clickElement(createaccount);
        
        WebElement fname = driver.findElement(By.cssSelector("input#firstname"));
        sendKeys(fname,random());

        WebElement lname = driver.findElement(By.cssSelector("input#lastname"));
        sendKeys(lname, random());

        WebElement email = driver.findElement(By.cssSelector("input#email_address"));
        sendKeys(email, random()+"@gmail.com");

        String password = random();
        WebElement pass = driver.findElement(By.cssSelector("input#password"));
        sendKeys(pass, password);

        WebElement cfmpass = driver.findElement(By.cssSelector("input#confirmation"));
        sendKeys(cfmpass, password);

        WebElement registerbtn = driver.findElement(By.xpath("//button[@title='Register']"));
        clickElement(registerbtn);

        Assert.assertTrue(getTextbyJSE().contains("Thank you for registering with Main Website Store."));

        WebElement account = driver.findElement(By.xpath("//div[@class = 'account-cart-wrapper']//span[text()='Account']"));
        clickElement(account);

        WebElement logout = driver.findElement(By.xpath("//a[@title = 'Log Out']"));
        clickElement(logout);
        Thread.sleep(5000);
        Assert.assertEquals(driver.getTitle(), "Home page");
    }
    public String getTextbyJSE() {
        String getText =js.executeScript("return document.documentElement.innerText;").toString();
        return getText;
    }
    public void sendKeys (WebElement element, String key) {
        js.executeScript("arguments[0].value=arguments[1];",element,key);
    }
    public void clickElement(WebElement element) {
        js.executeScript("arguments[0].click();", element);
    }

    public String random() {
        String randomString = RandomStringUtils.randomAlphabetic(6);
        return randomString;
    }

    @AfterMethod
    public void CloseBrowser() {
        driver.close();
    }
}
