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

public class Topic6_Element {

    WebDriver driver;

    @BeforeMethod
    public void openBrowser() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();

    }

    @Test
    public void TC1_Displayed() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        WebElement emailBasicInfo = driver.findElement(By.xpath("//input[@id='mail']"));
        Assert.assertTrue(emailBasicInfo.isDisplayed());
        if (emailBasicInfo.isDisplayed() == true) {
            emailBasicInfo.sendKeys("Automation Testing");
            System.out.println("Element is displayed");
        }
        WebElement emailSubmitOrReset = driver.findElement(By.xpath("//input[@id='email']"));
        Assert.assertTrue(emailSubmitOrReset.isDisplayed());
        if (emailSubmitOrReset.isDisplayed() == true) {
            emailSubmitOrReset.sendKeys("Automation Testing");
            System.out.println("Element is displayed");
        }
        WebElement education = driver.findElement(By.xpath("//textarea[@id='edu']"));
        Assert.assertTrue(education.isDisplayed());
        if (education.isDisplayed() == true) {
            education.sendKeys("Automation Testing");
            System.out.println("Element is displayed");
        }
        WebElement under18btn = driver.findElement(By.xpath("//input[@id='under_18']"));
        Assert.assertTrue(under18btn.isDisplayed());
        if (under18btn.isDisplayed() == true) {
            under18btn.click();
            System.out.println("Element is displayed");
        }
        WebElement user5 = driver.findElement(By.xpath("//h5[text()='Name: User5']"));
        Assert.assertFalse(user5.isDisplayed());
        if (user5.isDisplayed() == false) {
            System.out.println("Element is not displayed");
        }
    }

    @Test
    public void TC2_Enabled() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        WebElement emailBasicInfo = driver.findElement(By.xpath("//input[@id='mail']"));
        Assert.assertTrue(emailBasicInfo.isEnabled());
        if (emailBasicInfo.isEnabled() == true) {
            System.out.println("Element is enabled");
        }
        WebElement emailSubmitOrReset = driver.findElement(By.xpath("//input[@id='email']"));
        Assert.assertTrue(emailSubmitOrReset.isEnabled());
        if (emailSubmitOrReset.isEnabled() == true) {
            System.out.println("Element is enabled");
        }
        WebElement education = driver.findElement(By.xpath("//textarea[@id='edu']"));
        Assert.assertTrue(education.isEnabled());
        if (education.isEnabled() == true) {
            System.out.println("Element is enabled");
        }
        WebElement jobRole1 = driver.findElement(By.xpath("//select[@id='job1']"));
        Assert.assertTrue(jobRole1.isEnabled());
        if (jobRole1.isDisplayed() == true) {
            System.out.println("Element is enabled");
        }
        WebElement jobRole2 = driver.findElement(By.xpath("//select[@id='job2']"));
        Assert.assertTrue(jobRole2.isEnabled());
        if (jobRole2.isDisplayed() == true) {
            System.out.println("Element is enabled");
        }
        WebElement developmentInterest = driver.findElement(By.xpath("//input[@id='development']"));
        Assert.assertTrue(developmentInterest.isEnabled());
        if (developmentInterest.isDisplayed() == true) {
            System.out.println("Element is enabled");
        }
        WebElement slider1 = driver.findElement(By.xpath("//input[@id='slider-1']"));
        Assert.assertTrue(slider1.isEnabled());
        if (slider1.isDisplayed() == true) {
            System.out.println("Element is enabled");
        }

        WebElement password = driver.findElement(By.xpath("//input[@id='disable_password']"));
        Assert.assertFalse(password.isEnabled());
        if (password.isDisplayed() == false) {
            System.out.println("Element is not enabled");
        }
        WebElement disableRadioBtn = driver.findElement(By.xpath("//input[@id='radio-disabled']"));
        Assert.assertFalse(disableRadioBtn.isEnabled());
        if (disableRadioBtn.isDisplayed() == false) {
            System.out.println("Element is not enabled");
        }
        WebElement biography = driver.findElement(By.xpath("//textarea[@id='bio']"));
        Assert.assertFalse(biography.isEnabled());
        if (biography.isDisplayed() == false) {
            System.out.println("Element is not enabled");
        }
        WebElement jobRole3 = driver.findElement(By.xpath("//select[@id='job3']"));
        Assert.assertFalse(jobRole3.isEnabled());
        if (jobRole3.isDisplayed() == false) {
            System.out.println("Element is not enabled");
        }
        WebElement disableInterestBtn = driver.findElement(By.xpath("//input[@id='check-disbaled']"));
        Assert.assertFalse(disableInterestBtn.isEnabled());
        if (disableInterestBtn.isDisplayed() == false) {
            System.out.println("Element is not enabled");
        }
        WebElement slider2 = driver.findElement(By.xpath("//input[@id='slider-2']"));
        Assert.assertFalse(slider2.isEnabled());
        if (slider2.isDisplayed() == false) {
            System.out.println("Element is not enabled");
        }
    }

    @Test
    public void TC3_Selected() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        WebElement under18btn = driver.findElement(By.xpath("//input[@id='under_18']"));
        under18btn.click();
        Assert.assertTrue(under18btn.isSelected());
        if (under18btn.isSelected() == true) {
            System.out.println("Element is selected");
        }

        WebElement javaLanguague = driver.findElement(By.xpath("//input[@id='java']"));
        javaLanguague.click();
        Assert.assertTrue(javaLanguague.isSelected());
        if (javaLanguague.isSelected() == true) {
            System.out.println("Element is selected");
        }
        javaLanguague.click();
        Assert.assertFalse(javaLanguague.isSelected());
        if (javaLanguague.isSelected() == false) {
            System.out.println("Element is de-selected");
        }
    }
    @Test
    public void TC4_RegisterMailChimp() {
        driver.get("https://login.mailchimp.com/signup/");
        WebElement emailTextBox = driver.findElement(By.xpath("//input[@id='email']"));
        Assert.assertTrue(emailTextBox.isDisplayed());
        Assert.assertTrue(emailTextBox.isEnabled());
        emailTextBox.sendKeys("Abcd1234@gmail.com");

        WebElement password = driver.findElement(By.xpath("//input[@id='new_password']"));
        Assert.assertTrue(password.isDisplayed());
        Assert.assertTrue(password.isEnabled());

        password.sendKeys("a");
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
        password.clear();

        password.sendKeys("A");
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
        password.clear();

        password.sendKeys("1");
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
        password.clear();

        password.sendKeys("@");
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
        password.clear();

        password.sendKeys("abcd1234");
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
        password.clear();

        password.sendKeys(randomText());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='plus-50 error not-completed']")).isDisplayed());
        password.clear();
    }

    public String randomText() {
        String passrandom = RandomStringUtils.randomAlphabetic(51);
        return passrandom;
        }
    @AfterMethod
    public void closeBrowser() {
        driver.close();
    }
}
