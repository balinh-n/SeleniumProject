package exercise;

import java.time.Duration;
import java.util.List;
import java.util.Set;
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

public class Topic11_12_PopUp_Iframe_Window {

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
    // Mặc định mở trang đăng nhập khi chạy auto
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
        js.executeScript("arguments[0].scrollIntoView();", formview);
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

        List<WebElement> residenceList = driver.findElements(By.cssSelector("select#RESULT_RadioButton-3 option"));
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

    @Test
    public void TC12_Frame() throws InterruptedException {
        driver.get("https://netbanking.hdfcbank.com/netbanking/");
        driver.switchTo().frame("login_page");
        driver.findElement(By.xpath("//input[@name ='fldLoginUserId']")).sendKeys("12345");
        driver.findElement(By.xpath("//a[@onclick='return fLogon();']")).click();
        Thread.sleep(3000);
        Assert.assertTrue(driver.findElement(By.xpath("//input[@name='fldPassword']")).isDisplayed());
    }

    @Test
    public void TC13_Window_Tab() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        String defaultwindow = "Selenium WebDriver";

        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
        Thread.sleep(3000);
        switchWindow("Google");

        switchWindow(defaultwindow);

        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
        Thread.sleep(3000);
        switchWindow("Facebook - Đăng nhập hoặc đăng ký");

        switchWindow(defaultwindow);

        driver.findElement(By.xpath("//a[text()='TIKI']")).click();
        Thread.sleep(3000);
        switchWindow("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
    }

    public void switchWindow(String title) {
        Set<String> allwindow = driver.getWindowHandles();
        for (String window : allwindow) {

            driver.switchTo().window(window);
            String actualTitle = driver.getTitle();
            if (actualTitle.equals(title)) {
                break;
            }
        }
    }

    @Test
    public void TC14_WindowTab() throws InterruptedException {
        driver.get("http://live.techpanda.org/");

        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[@title='Xperia']//following-sibling::div//a[text()='Add to Compare']")).click();
        Thread.sleep(2000);
        String xperiasuccess = driver.findElement(By.cssSelector("li.success-msg span")).getText();
        Assert.assertEquals(xperiasuccess, "The product Sony Xperia has been added to comparison list.");

        //a[@title='Samsung Galaxy']//following-sibling::div//a[text()='Add to Compare']
        driver.findElement(By.xpath("//a[@title='Samsung Galaxy']//following-sibling::div//a[text()='Add to Compare']")).click();
        Thread.sleep(2000);
        String galaxysuccess = driver.findElement(By.cssSelector("li.success-msg span")).getText();
        Assert.assertEquals(galaxysuccess, "The product Samsung Galaxy has been added to comparison list.");
        Thread.sleep(2000);

        driver.findElement(By.xpath("//button[@title='Compare']")).click();

        switchWindow("Products Comparison List - Magento Commerce");
        Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
        driver.close();

        switchWindow("Mobile");

        driver.findElement(By.xpath("//a[text()='Clear All']")).click();
        driver.switchTo().alert().accept();

        String clearAll = driver.findElement(By.cssSelector("li.success-msg span")).getText();
        Assert.assertEquals(clearAll, "The comparison list was cleared.");
    
    }

    @Test
    public void TC15_WindowTab() throws InterruptedException{
        driver.get("https://dictionary.cambridge.org/vi/");
        
        driver.findElement(By.xpath("//span/span[text()='Đăng nhập']")).click();

        switchWindow("Login");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//form[@id='gigya-login-form']//input[@type='submit']")).click();
        String usernameError = driver.findElement(By.xpath("//input[@name='username']//following-sibling::span[contains(@class,'gigya-error-code-400027')]")).getText();
        String passwordError =driver.findElement(By.xpath("//input[@name='password']//following-sibling::span[contains(@class,'gigya-error-code-400027')]")).getText();
        Assert.assertEquals(usernameError, "This field is required");
        Assert.assertEquals(passwordError, "This field is required");

        driver.close();
        switchWindow("Cambridge Dictionary | Từ điển tiếng Anh, Bản dịch & Từ điển từ đồng nghĩa");
        driver.findElement(By.cssSelector("input#searchword")).sendKeys("automation");
        driver.findElement(By.xpath("//button[@title='Tìm kiếm']")).click();
        Thread.sleep(2000);

        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='dataset_cald4']//following-sibling::div[@class='link']//span[@class='hw dhw']")).isDisplayed());   
    }

    @Test
    public void TC16_WindowTab() throws InterruptedException {
        driver.get("https://courses.dce.harvard.edu/");
        driver.findElement(By.cssSelector("a.anon-only")).click();

        Thread.sleep(5000);
        switchWindow("Harvard Division of Continuing Education Login Portal");
        driver.findElement(By.cssSelector("div.cd7be4319.c2de32735")).isDisplayed();
        driver.close();

        Thread.sleep(3000);
        switchWindow("DCE Course Search");
        Assert.assertTrue(driver.findElement(By.cssSelector("div#sam-wait")).isDisplayed());
        driver.findElement(By.cssSelector("button.fa.fa-times.sam-wait__close")).click();

        driver.findElement(By.cssSelector("select#crit-summer_school")).click();
        List<WebElement> options = driver.findElements(By.cssSelector("select#crit-summer_school option"));
        for (WebElement option : options) {
            if(option.getText().equals("Adult, Extension, & Visiting College")) {
                option.click();
                break;
            }
        }

        driver.findElement(By.cssSelector("select#crit-session")).click();
        List<WebElement> courses = driver.findElements(By.cssSelector("select#crit-session option"));
        for (WebElement course : courses) {
            if(course.getText().equals("3-week session II")) {
                course.click();
                break;
            }
        }

        driver.findElement(By.cssSelector("button#search-button")).click();
        List<WebElement> courseLists = driver.findElements(By.cssSelector("div.panel.panel--kind-results.panel--visible div.result.result--group-start"));

        Assert.assertEquals(courseLists.size(), 21);
    }
    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }
}
