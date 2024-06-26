package exercise;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
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
        Assert.assertEquals(driver.findElement(By.cssSelector("div.row error-login-panel")),
                "Tài khoản không tồn tại!");
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
    public void TC3_FixedPopupNotInDom() throws InterruptedException {
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
        Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Email không được để trống']")).getText(),
                "Email không được để trống");
        Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Mật khẩu không được để trống']")).getText(),
                "Mật khẩu không được để trống");
        driver.findElement(By.cssSelector("button.btn-close")).click();
        List<WebElement> signInW = driver.findElements(By.cssSelector("div.styles__Root-sc-2hr4xa-0.jyAQAr"));
        Assert.assertEquals(signInW.size(), 0);
    }

    @Test
    public void TC4_FixedPopupNotInDom() throws InterruptedException {
        driver.get("https://www.facebook.com/");

        driver.findElement(By.cssSelector("a._42ft._4jy0._6lti._4jy6._4jy2.selected._51sy")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("div._8ien")).isDisplayed());
        driver.findElement(By.cssSelector("img._8idr.img")).click();
        Thread.sleep(2000);
        List<WebElement> signin = driver.findElements(By.cssSelector("div._8ien"));
        Assert.assertEquals(signin.size(), 0);
    }

    @Test
    public void TC5_RandomPopupInDom() throws InterruptedException {
        driver.get("https://www.javacodegeeks.com/");
        if (driver.findElement(By.cssSelector("div#lepopup-popup-123 div.lepopup-element-2")).isDisplayed()) {
            driver.findElement(By.xpath("//div[contains(@class,'lepopup-element-23')]//a")).click();
            System.out.println("Popup is displayed");
        } else {
            System.out.println("Popup is not display");
        }
        driver.findElement(By.cssSelector("input#search-input")).sendKeys("Agile Testing Explained");
        driver.findElement(By.cssSelector("button#search-submit")).click();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.cssSelector("h2.post-title a")).getText(), "Agile Testing Explained");

    }

    @Test
    public void TC6_RandomPopupInDom() {
        driver.get("https://www.kmplayer.com/home");

        if (driver.findElement(By.cssSelector("div.pop-container")).isDisplayed()) {
            System.out.println("Popup is displayed");
            driver.findElement(By.cssSelector("div.close")).click();
        }
        Assert.assertTrue(driver.findElements(By.cssSelector("div.pop-container")).size() > 0);

    }

    @Test
    public void TC7_RandomPopupNotInDom() {
        driver.get("https://dehieu.vn/");

        By ads = By.cssSelector("div.modal-content.css-modal-bt");
        if (driver.findElement(ads).isDisplayed()) {
            driver.findElement(By.cssSelector("button.close")).click();
        }
        Assert.assertTrue(driver.findElements(ads).size() > 0);
    }

    @Test
    public void TC8_ShowdowRoot() {
        driver.get("https://automationfc.github.io/shadow-dom");
        WebElement shadowHost = driver.findElement(By.cssSelector("div#shadow_host"));
        SearchContext searchContext = shadowHost.getShadowRoot();
        String text = searchContext.findElement(By.cssSelector("span#shadow_content span")).getText();
        System.out.println(text);

        WebElement shadowRootNested = searchContext.findElement(By.cssSelector("div#nested_shadow_host"));
        SearchContext searchContext2 = shadowRootNested.getShadowRoot();
        String text2 = searchContext2.findElement(By.cssSelector("div#nested_shadow_content div")).getText();
        System.out.println(text2);
    }

    @Test
    public void TC9_ShadowRootInDom() throws InterruptedException {
        driver.manage().deleteAllCookies();
        driver.get("https://shopee.vn/");
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("a.Gaujs8")).click();
        WebElement homepage = driver.findElement(By.cssSelector("div.home-page"));
        SearchContext homePageShadowRoot = homepage.getShadowRoot();
        WebElement homePagePopUp = homePageShadowRoot.findElement(By.cssSelector("div.home-popup__content"));
        WebElement closePopUpbnt = homePageShadowRoot.findElement(By.cssSelector("div.home-popup__close-area"));
        SearchContext popUpShadowRoot = homePagePopUp.findElement(By.cssSelector("img.simple-banner.with-placeholder"));
        WebElement popUP = popUpShadowRoot.findElement(By.cssSelector("div.banner-image"));
        if (popUP.isDisplayed()) {
            closePopUpbnt.click();
        }
    }


    @Test
    public void TC10_IFrame() {
        driver.get("https://toidicodedao.com/");
       
        WebElement fbView = driver.findElement(By.cssSelector("div.fb-page.fb_iframe_widget"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", fbView);
        WebElement fbIframe = driver.findElement(By.cssSelector("div.fb-page.fb_iframe_widget iframe"));
        Assert.assertTrue(fbIframe.isDisplayed());
        driver.switchTo().frame(fbIframe);
        WebElement followerNumber = driver.findElement(By.cssSelector("div._1drq"));
        Assert.assertEquals(followerNumber.getText(), "406,410 followers");
    }

    @Test
    public void TC11_iframe() throws InterruptedException {
        driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");
        WebElement formview = driver.findElement(By.cssSelector("div#imageTemplateContainer"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();",formview);
        formview.click();
        Thread.sleep(5000);

        WebElement formFrame = driver.findElement(By.cssSelector("div.details__form-template iframe"));
        driver.switchTo().frame(formFrame);

        List<WebElement> yearList = driver.findElements(By.cssSelector("select#RESULT_RadioButton-2 option"));
        for (WebElement year : yearList) {
            if (year.getText().equals("Sophomore")) {
                year.click();
                break;
            }
        }
       
        List<WebElement> residenceList  = driver.findElements(By.cssSelector("select#RESULT_RadioButton-3 option"));
        for (WebElement residence : residenceList) {
            if (residence.getText().equals("North Dorm")) {
                residence.click();
                break;
            }
        }

        driver.findElement(By.xpath("//input[@id='RESULT_RadioButton-4_0']//parent::td")).click();
        Thread.sleep(3000);
        driver.navigate().refresh();
        driver.findElement(By.xpath("//nav[contains(@class,'header--desktop-floater')]//a[@title='Log in']")).click();
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("button.auth-form__submit")).click();
        WebElement errorMessage = driver.findElement(By.cssSelector("div#message-error"));
        Thread.sleep(2000);
        Assert.assertTrue(errorMessage.isDisplayed());
        Assert.assertEquals(errorMessage.getText(), "Username and password are both required.");
    }
    @AfterMethod
    public void closeBrowser() {
    driver.close();
    }
}
