package exercise;

import java.io.IOException;
import java.time.Duration;

import java.util.List;


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
    public void TC3_Material_Angular() throws InterruptedException {
        driver.get("https://material.angular.io/components/radio/examples");
        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebElement summer_radio = driver.findElement(By.xpath("//label[text()='Summer']/preceding-sibling::div/input"));
        js.executeScript("arguments[0].scrollIntoView();", summer_radio);
        Assert.assertFalse(summer_radio.isSelected());
        if (!summer_radio.isSelected()) {
            summer_radio.click();
        }
        Assert.assertTrue(summer_radio.isSelected());

        driver.get("https://material.angular.io/components/checkbox/examples");
        WebElement checkedBtn = driver.findElement(By.xpath("//label[text()='Checked']/parent::div//input"));
        WebElement indeteminate = driver.findElement(By.xpath("//label[text()='Indeterminate']/parent::div//input"));
        checkedBtn.click();
        indeteminate.click();
        Thread.sleep(5000);
        Assert.assertTrue(checkedBtn.isSelected());
        Assert.assertTrue(indeteminate.isSelected());
        if (checkedBtn.isSelected()) {
            checkedBtn.click();

        }
        if (indeteminate.isSelected()) {
            indeteminate.click();

        }
    }

    @Test
    public void TC4_CheckBox() {
        driver.get("https://automationfc.github.io/multiple-fields/");

        List<WebElement> checkboxs = driver.findElements(By.xpath("//div[@id='cid_52']//input[@type='checkbox']"));
        for (WebElement checkbox : checkboxs) {
            if (!checkbox.isSelected()) {
                checkbox.click();
                Assert.assertTrue(checkbox.isSelected());
            }
        }
        driver.navigate().refresh();
        WebElement heart_attack = driver.findElement(By.xpath("//input[@value='Heart Attack']"));
        heart_attack.click();
        for (WebElement checkbox : checkboxs) {
            if (!checkbox.isSelected()) {
                checkbox.isSelected();
                continue;
            }
        }
        Assert.assertTrue(heart_attack.isSelected());
    }

    @Test
    public void TC5_Custom_Checkbox_Radio() {
        driver.get("https://login.ubuntu.com/");
        driver.findElement(By.cssSelector("label.new-user")).click();
        driver.findElement(By.xpath("//label[@for='id_accept_tos']")).click();
    }

    @Test
    public void TC6_Custom_Checkbox_Radio() throws InterruptedException {
        driver.get(
                "https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
        WebElement canthobtn = driver
                .findElement(By.xpath("//span[text()='Cần Thơ']/ancestor::label//div[@id='i14']"));

        Assert.assertEquals(canthobtn.getAttribute("aria-checked"), "false");
        if (!canthobtn.isSelected()) {
            canthobtn.click();
        }
        Thread.sleep(2000);
        Assert.assertEquals(canthobtn.getAttribute("aria-checked"), "true");
    }

    @Test
    public void TC7_AcceptAlert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[@onclick='jsAlert()']")).click();
        driver.switchTo().alert().accept();
    }

    @Test
    public void TC8_ConfirmAlert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[@onclick='jsConfirm()']")).click();
        String alert = driver.switchTo().alert().getText();
        Assert.assertEquals(alert, "I am a JS Confirm");
        driver.switchTo().alert().dismiss();
        String alert_text = driver.findElement(By.cssSelector("p#result")).getText();
        Assert.assertEquals(alert_text, "You clicked: Cancel");
    }

    @Test
    public void TC9_PromptAlert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[@onclick='jsPrompt()']")).click();
        String alert = driver.switchTo().alert().getText();
        Assert.assertEquals(alert, "I am a JS prompt");

        driver.switchTo().alert().sendKeys("automation");
        driver.switchTo().alert().accept();
        String alert_text = driver.findElement(By.cssSelector("p#result")).getText();
        Assert.assertEquals(alert_text, "You entered: automation");
    }

    @Test
    public void TC11_AuthenticateAlert() {
        driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
        boolean displayed = driver
                .findElement(
                        By.xpath("//p[contains(text(), 'Congratulations! You must have the proper credentials.')]"))
                .isDisplayed();
        Assert.assertTrue(displayed);
    }

    @AfterMethod
    public void closeBrowser() {
        driver.close();
    }
}
