package exercise;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class Topic7_8_TextBox_TextArea {
    WebDriver driver;
  
    @BeforeMethod
    public void openBrowser() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }
    @Test
    public void TC1_LiveTechPanda() {
        
        driver.get("http://live.techpanda.org/");
        String fName = "Jack";
        String lName = "Sparrow";
        String email = randomText() + "@gmail.com";
        String registerInfo = fName.concat(" ").concat(lName);
        //Register account
        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys(fName.trim());
        driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys(lName.trim());
        driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("abcd1234@");
        driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys("abcd1234@");
        driver.findElement(By.xpath("//button[@title='Register']")).click();

        //Validate infor user
        Exception();
        WebElement registerSuccessMessage = driver.findElement(By.xpath("//li[@class='success-msg']//span"));
        Assert.assertTrue(registerSuccessMessage.isDisplayed());
        Assert.assertEquals(registerSuccessMessage.getText(), "Thank you for registering with Main Website Store.");

        Exception();
        WebElement userInfor = driver.findElement(By.xpath("//div[@class='col-1']//p"));
        Assert.assertTrue(userInfor.isDisplayed());
        Assert.assertTrue(userInfor.getText().trim().contains(registerInfo));
        Assert.assertTrue(userInfor.getText().trim().contains(email));

        //Review item
        driver.findElement(By.xpath("//li[@class='level0 nav-1 first']/a")).click();
        driver.findElement(By.xpath("//h2[@class='product-name']/a[@title='Samsung Galaxy']")).click();
        driver.findElement(By.xpath("//a[text()='Add Your Review']")).click();

        driver.findElement(By.xpath("//input[@id='Quality 1_5']")).click();
        driver.findElement(By.xpath("//textarea[@id='review_field']")).sendKeys(randomText());
        driver.findElement(By.xpath("//input[@id='summary_field']")).sendKeys(randomText());
        driver.findElement(By.xpath("//input[@id='nickname_field']")).clear();
        driver.findElement(By.xpath("//input[@id='nickname_field']")).sendKeys(fName);
        driver.findElement(By.xpath("//button[@title='Submit Review']")).click();

        //Review information
        WebElement submitReviewSuccessText = driver.findElement(By.xpath("//li[@class='success-msg']//span"));
        Assert.assertTrue(submitReviewSuccessText.isDisplayed());
        Assert.assertEquals(submitReviewSuccessText.getText(), "Your review has been accepted for moderation.");

        //Logout
        driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
        driver.findElement(By.xpath("//a[@title='Log Out']")).click();

        Exception();
        Assert.assertEquals(driver.getTitle(), "Home page");
    }

    @Test
    public void TC2_OrangeHrm() {
        //Log in OrangeHrm
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        //Access Add employee tab
        Exception();
        driver.findElement(By.xpath("//ul[@class='oxd-main-menu']//span[text()='PIM']")).click();
        driver.findElement(By.xpath("//a[text()='Add Employee']")).click();

        //Create employee
        Exception();
        driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys(randomText());
        String employeeFName = driver.findElement(By.xpath("//input[@name='firstName']")).getText();
        driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys(randomText());
        String employeeLName = driver.findElement(By.xpath("//input[@name='lastName']")).getText();
        String employeeID = driver.findElement(By.cssSelector("div[class*='oxd-grid-2'] input[class*='oxd-input']")).getText();
        driver.findElement(By.xpath("span[class*='oxd-switch-input ']")).click();
        //Username
        driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div")).sendKeys(randomText());
        String userName = driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div")).getText();
        //Password
        String pass = randomText();
        driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div")).sendKeys(pass);
        String password = driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div")).getText();
        driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div")).sendKeys(pass);
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        //User details
        Exception();
        String fnameDetail = driver.findElement(By.xpath("//input[@name='firstName']")).getText();
        String lnameDetail = driver.findElement(By.xpath("//input[@name='lastName']")).getText();
        String employeeIdDetail = driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div")).getText();
        Assert.assertEquals(employeeFName, fnameDetail);
        Assert.assertEquals(employeeLName, lnameDetail);
        Assert.assertEquals(employeeID, employeeIdDetail);

        //Immigration
        driver.findElement(By.xpath("//a[text()='Immigration']")).click();
        Exception();
        driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']/following-sibling::button")).click();
        driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div")).sendKeys(randomNumber());
        String passportID = driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div")).getText();
        driver.findElement(By.xpath("//textarea[@placeholder='Type Comments here']")).sendKeys("This is comment");
        String comment = driver.findElement(By.xpath("//textarea[@placeholder='Type Comments here']")).getText();
        driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();
        //Edit Immigration
        driver.findElement(By.xpath("//button[@type='button']/i[@class='oxd-icon bi-pencil-fill']")).click();
        String editPassportID = driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div")).getText();
        String editComment =driver.findElement(By.xpath("//textarea[@placeholder='Type Comments here']")).getText();
        Assert.assertEquals(passportID, editPassportID);
        Assert.assertEquals(comment, editComment);

        //Logout
        driver.findElement(By.xpath("//li[@class='--active oxd-userdropdown']")).click();
        driver.findElement(By.xpath("//a[text()='Logout']")).click();

        //Sign in
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys(userName);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        //My Info
        Exception();
        driver.findElement(By.xpath("//a[@class='oxd-main-menu-item']/span[text()='My Info']")).click();
        String editFNameEmployee = driver.findElement(By.xpath("//input[@name='firstName']")).getText();
        String editLNameEmployee = driver.findElement(By.xpath("//input[@name='lastName']")).getText();
        String editEmployeeID = driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div")).getText();
        Assert.assertEquals(employeeFName, editFNameEmployee);
        Assert.assertEquals(employeeLName, editLNameEmployee);
        Assert.assertEquals(employeeID, editEmployeeID);

        driver.findElement(By.xpath("//a[text()='Immigration']")).click();
        Exception();
        driver.findElement(By.xpath("//button[@type='button']/i[@class='oxd-icon bi-pencil-fill']")).click();
        String editPassportID1 = driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div")).getText();
        String editComment1 =driver.findElement(By.xpath("//textarea[@placeholder='Type Comments here']")).getText();
        Assert.assertEquals(passportID, editPassportID1);
        Assert.assertEquals(comment, editComment1);
    }

    public void Exception() {
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            
        }
    }
    public String randomText() {
        String random = RandomStringUtils.randomAlphanumeric(7);
        return random;
    }
    public String randomNumber() {
        String number = RandomStringUtils.randomNumeric(13);
        return number;
    }
    @AfterMethod
    public void closeBrowser() {
        driver.close();
    }
}
