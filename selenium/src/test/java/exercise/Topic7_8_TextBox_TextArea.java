package exercise;

import java.time.Duration;
import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
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
        // Register account
        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys(fName.trim());
        driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys(lName.trim());
        driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("abcd1234@");
        driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys("abcd1234@");
        driver.findElement(By.xpath("//button[@title='Register']")).click();

        // Validate infor user
        Exception();
        WebElement registerSuccessMessage = driver.findElement(By.xpath("//li[@class='success-msg']//span"));
        Assert.assertTrue(registerSuccessMessage.isDisplayed());
        Assert.assertEquals(registerSuccessMessage.getText(), "Thank you for registering with Main Website Store.");

        Exception();
        WebElement userInfor = driver.findElement(By.xpath("//div[@class='col-1']//p"));
        Assert.assertTrue(userInfor.isDisplayed());
        Assert.assertTrue(userInfor.getText().trim().contains(registerInfo));
        Assert.assertTrue(userInfor.getText().trim().contains(email));

        // Review item
        driver.findElement(By.xpath("//li[@class='level0 nav-1 first']/a")).click();
        driver.findElement(By.xpath("//h2[@class='product-name']/a[@title='Samsung Galaxy']")).click();
        driver.findElement(By.xpath("//a[text()='Add Your Review']")).click();

        driver.findElement(By.xpath("//input[@id='Quality 1_5']")).click();
        driver.findElement(By.xpath("//textarea[@id='review_field']")).sendKeys(randomText());
        driver.findElement(By.xpath("//input[@id='summary_field']")).sendKeys(randomText());
        driver.findElement(By.xpath("//input[@id='nickname_field']")).clear();
        driver.findElement(By.xpath("//input[@id='nickname_field']")).sendKeys(fName);
        driver.findElement(By.xpath("//button[@title='Submit Review']")).click();

        // Review information
        WebElement submitReviewSuccessText = driver.findElement(By.xpath("//li[@class='success-msg']//span"));
        Assert.assertTrue(submitReviewSuccessText.isDisplayed());
        Assert.assertEquals(submitReviewSuccessText.getText(), "Your review has been accepted for moderation.");

        // Logout
        driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
        driver.findElement(By.xpath("//a[@title='Log Out']")).click();

        Exception();
        Assert.assertEquals(driver.getTitle(), "Home page");
    }

    @Test
    public void TC4_Dropdown_List() {
        driver.get("https://demo.nopcommerce.com/register");

        driver.findElement(By.xpath("//a[text()='Register']")).click();
        driver.findElement(By.xpath("//input[@id='gender-male']")).click();
        driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys(randomText());
        driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys(randomText());

        WebElement dateOfBirth = driver.findElement(By.xpath("//select[@name='DateOfBirthDay']"));
        Select day = new Select(dateOfBirth);
        day.selectByValue("1");
        String dayRegister = dateOfBirth.getAttribute("value");
        List<WebElement> dayOptions = day.getOptions();
        Assert.assertEquals(dayOptions.size(), 32);

        WebElement monthOfBirth = driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']"));
        Select month = new Select(monthOfBirth);
        month.selectByValue("5");
        String monthRegister = monthOfBirth.getAttribute("value");
        List<WebElement> monthOptions = month.getOptions();
        Assert.assertEquals(monthOptions.size(), 13);

        WebElement yearOfBirth = driver.findElement(By.xpath("//select[@name='DateOfBirthYear']"));
        Select year = new Select(yearOfBirth);
        year.selectByValue("1980");
        String yearRegister = yearOfBirth.getAttribute("value");
        List<WebElement> yearOptions = year.getOptions();
        Assert.assertEquals(yearOptions.size(), 112);

        driver.findElement(By.xpath("//input[@name='Email']")).sendKeys(randomText() + "@gmail.com");
        String pass = randomText();
        driver.findElement(By.xpath("//input[@name='Password']")).sendKeys(pass);
        driver.findElement(By.xpath("//input[@name='ConfirmPassword']")).sendKeys(pass);
        driver.findElement(By.xpath("//button[@name='register-button']")).click();

        Exception();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(),
                "Your registration completed");

        driver.findElement(By.xpath("//a[@class='ico-account']")).click();
        WebElement dateOfBirthDetail = driver.findElement(By.xpath("//select[@name='DateOfBirthDay']"));
        Assert.assertEquals(dayRegister, dateOfBirthDetail.getAttribute("value"));
        WebElement monthOfBirthDetail = driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']"));
        Assert.assertEquals(monthRegister, monthOfBirthDetail.getAttribute("value"));
        WebElement yearOfBirthDetail = driver.findElement(By.xpath("//select[@name='DateOfBirthYear']"));
        Assert.assertEquals(yearRegister, yearOfBirthDetail.getAttribute("value"));
    }

    @Test
    public void TC5_Rode() {
        driver.get("https://www.rode.com/wheretobuy");
        WebElement country = driver.findElement(By.xpath("//select[@name='country']"));
        Select select = new Select(country);
        select.selectByValue("Vietnam");
        Assert.assertFalse(select.isMultiple());

        driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys("Ho Chi Minh");
        driver.findElement(By.xpath("//button[@class='btn btn-default']")).click();

        List<WebElement> dealer = driver.findElements(By.xpath("//div[@class='col-lg-6 p-1 overflow-hidden']"));
        Assert.assertEquals(dealer.size(), 16);
        List<WebElement> dealerfriends = driver
                .findElements(By.xpath("//div[@class='col-lg-6 p-1 overflow-hidden']//h4"));
        for (WebElement dealerName : dealerfriends) {
            System.out.println(dealerName.getText());
        }
    }

    @Test
    public void TC6_Jquery() {
        driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");

        selectASpeed("//span[@id='speed-button']", "//ul[@id='speed-menu']/li", "Medium",
                "//span[@id='speed-button']/span[@class='ui-selectmenu-text']");

        selectASpeed("//span[@id='speed-button']", "//ul[@id='speed-menu']/li", "Slower",
                "//span[@id='speed-button']/span[@class='ui-selectmenu-text']");

        selectASpeed("//span[@id='speed-button']", "//ul[@id='speed-menu']/li", "Faster",
                "//span[@id='speed-button']/span[@class='ui-selectmenu-text']");
    }

    public void selectASpeed(String speedBox, String speedData, String speedSelect, String getSpeedText) {

        driver.findElement(By.xpath(speedBox)).click();
        List<WebElement> speeds = driver.findElements(By.xpath(speedData));
        for (WebElement speed : speeds) {
            if (speed.getText().equals(speedSelect)) {
                speed.click();
                break;
            }
        }
        Assert.assertEquals(driver.findElement(By.xpath(getSpeedText)).getText(), speedSelect);
    }

    @Test
    public void TC7_ReactDropdown() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
        reactSelectName("//div[@role='listbox']", "//div[@class='visible menu transition']/div", "Stevie Feliciano");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Stevie Feliciano");
    }

    public void reactSelectName(String friendDropdown, String friendList, String friendName ) {
        driver.findElement(By.xpath(friendDropdown)).click();
        List<WebElement> friends = driver.findElements(By.xpath(friendList));
        for (WebElement friend : friends) {
            if (friend.getText().equals(friendName)) {
                friend.click();
                break;
            }
        }
    }

    @Test
    public void TC8_VueJS() {
        driver.get("https://mikerodham.github.io/vue-dropdowns/");

        driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).click();
        List<WebElement> options = driver.findElements(By.xpath("//ul[@class='dropdown-menu']/li"));
        for (WebElement option : options) {
            if (option.getText().equals("Second Option")) {
                option.click();
                break;
            }
        }
    }
    public void Exception() {
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void TC9_EditableDropdown() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

        driver.findElement(By.cssSelector("input.search")).sendKeys("b");
        
        List<WebElement> countryLists = driver.findElements(By.cssSelector("div.visible.menu.transition span"));
        for (WebElement country : countryLists) {
            if (country.getText().equals("Bahamas")) {
                country.click();
                break;
            }
        }
    }


    public String randomText() {
        String random = RandomStringUtils.randomAlphanumeric(7);
        return random;
    }

    @AfterMethod
    public void closeBrowser() {
    driver.close();
    }
}
