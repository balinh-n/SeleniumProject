package exercise;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic10_Interaction {

    WebDriver driver;

    @BeforeMethod
    public void openBrowser() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();

    }

    @Test
    public void TC1_HoverToElemntToolTips() {
        driver.get("https://automationfc.github.io/jquery-tooltip/");
        Actions action = new Actions(driver);
        WebElement yourage = driver.findElement(By.xpath("//input[@id='age']"));
        action.moveToElement(yourage).build().perform();
        Assert.assertTrue(driver.findElement(By.cssSelector("div.ui-helper-hidden-accessible")).isDisplayed());
    }

    //@Test
    // public void TC2_HoverToElement() {
    //     driver.get("http://www.myntra.com/");

    //     WebElement kids = driver.findElement(By.xpath("//div[@class='desktop-navLink']/a[text()='Kids']"));
    //     WebElement homeandbath = driver.findElement(By.xpath("//a[text()='Home & Bath']"));
    //     Actions actions = new Actions(driver);
    //     actions.moveToElement(kids).moveToElement(homeandbath).click().build().perform();
    //     Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Kids Home Bath']")).isDisplayed());
    // }

    @Test
    public void TC3_Fahasa() throws InterruptedException {
        driver.get("https://www.fahasa.com/");
        WebElement menuIcon = driver.findElement(By.cssSelector("span.icon_menu"));
        // WebElement productList = driver.findElement(By.xpath("//span[text()='Sách
        // Trong Nước']"));
        WebElement bookType = driver
                .findElement(By.xpath("//div[@class='fhs_menu_content fhs_column_left']//a[text()='Kỹ Năng Sống']"));
        Actions actions = new Actions(driver);

        actions.moveToElement(menuIcon).perform();
        bookType.click();
        Assert.assertTrue(
                driver.findElement(By.xpath("//ol[@class='breadcrumb']//strong[text()='Kỹ năng sống']")).isDisplayed());

    }

    @Test
    public void TC4_Click_Hold() {
        driver.get("https://automationfc.github.io/jquery-selectable/");
        Actions actions = new Actions(driver);
        List<WebElement> numberlist = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
        actions.clickAndHold(numberlist.get(0)).moveToElement(numberlist.get(3)).release().perform();

        List<WebElement> numberselected = driver
                .findElements(By.xpath("//ol[@id='selectable']/li[@class='ui-state-default ui-selectee ui-selected']"));
        Assert.assertEquals(numberselected.size(), 4);
    }

    @Test
    public void TC5_Click_Select() {
        driver.get("https://automationfc.github.io/jquery-selectable/");
        Actions actions = new Actions(driver);
        List<WebElement> numberlist = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
        actions.keyDown(Keys.CONTROL).perform();
        actions.click(numberlist.get(0)).click(numberlist.get(2)).click(numberlist.get(5)).click(numberlist.get(10))
                .perform();
        actions.release().perform();
        Assert.assertEquals(
                driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']")).size(), 4);
    }

    @Test
    public void TC6_DoubleClick() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        WebElement db = driver.findElement(By.xpath("//button[@ondblclick='doubleClickMe()']"));
        Actions actions = new Actions(driver);
        actions.scrollToElement(db).perform();
        actions.doubleClick(db).perform();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(), "Hello Automation Guys!");
    }

    @Test
    public void TC7_RightClick() throws InterruptedException {
        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
        Actions actions = new Actions(driver);
        actions.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
        WebElement quitBtn = driver.findElement(By.cssSelector("li.context-menu-icon-quit"));
        actions.moveToElement(quitBtn).perform();
        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-visible.context-menu-hover")).isDisplayed());

        actions.click(quitBtn).perform();
        driver.switchTo().alert().accept();
        Thread.sleep(2000);
        Assert.assertFalse(quitBtn.isDisplayed());
    }
    
    @Test
    public void TC8_DraganDrop() throws InterruptedException {
        driver.get("https://automationfc.github.io/kendo-drag-drop/");
        Actions actions = new Actions(driver);
        WebElement source = driver.findElement(By.cssSelector("div#draggable"));
        WebElement target = driver.findElement(By.cssSelector("div#droptarget"));
        actions.dragAndDrop(source, target).perform();
        Thread.sleep(2000);
        Assert.assertEquals(target.getText(), "You did great!");
        String color = driver.findElement(By.cssSelector("div#draggable")).getCssValue("background-color");
        String hex = Color.fromString(color).asHex();
        Assert.assertEquals(hex, "#03a9f4");
    }
    @AfterMethod
    public void closeBrowser() {
        driver.close();
    }
}