package pages;

import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class EbebekTest_Pages {

    public AndroidDriver driver;
    public WebDriverWait wait;

    @Before
    public void setup() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "Pixel XL API 29");
        caps.setCapability("udid", "emulator-5554"); //DeviceId from "adb devices" command
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "11.0");
        //caps.setCapability("skipUnlock", "true");
        caps.setCapability("appPackage", "com.solidict.ebebek");
        caps.setCapability("appActivity", "com.ebebek.android.view.MainActivity");
        //caps.setCapability("noReset", "false");
        //caps.setCapability("autoGrantPermissions", "true");

        try {
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        wait = new WebDriverWait(driver, Duration.ofSeconds(30L));
    }

    By category = By.id("com.solidict.ebebek:id/navCategories");//Kategoriler///
    By subCategory1 = By.xpath("(//*[@resource-id='com.solidict.ebebek:id/iv_icon'])[1]"); //Giyim & Tekstil Anasayfa
    By subCategory2 = By.xpath("(//*[@resource-id='com.solidict.ebebek:id/iv_arrow'])[1]"); //Bebek Giyim
    By filterButton = By.xpath("//*[@text='Filtrele']"); //Filtre
    By filterInterval = By.xpath("(//*[@class='android.widget.LinearLayout'])[10]"); //Filtre Aralığı
    By filterInterval_50_100 = By.xpath("(//*[@class='android.widget.LinearLayout'])[6]"); //50-100
    By filterApply = By.id("com.solidict.ebebek:id/btApplyFilter");
    By displayProducts = By.xpath("//*[contains(@text, \"Ürünleri Gör\")]");
    By scoreInterval = By.xpath("(//*[@class='android.widget.LinearLayout'])[12]"); //Değerlendirme Aralığı");
    By scoredMost = By.xpath("(//*[@resource-id='com.solidict.ebebek:id/tvFilterName'])[2]"); /////En çok değerlendirilenler
    By addFirstProductToBasket = By.xpath("(//*[@resource-id='com.solidict.ebebek:id/btAddToCart_'])[1]");

    By addToBasket = By.id("com.solidict.ebebek:id/textView_ButtonAddToCart");

    //By SepeteGit = By.xpath("//*[contains(@text, \"Sepetim\")]");
    By goToBasket = By.xpath("(//*[@resource-id='com.solidict.ebebek:id/navigation_bar_item_icon_view'])[3]");
    By increaseButton = By.xpath("(//*[@class='android.widget.ImageView'])[3]");
    By price = By.xpath("(//*[@resource-id='com.solidict.ebebek:id/textView_CartProduct_Price'])");

    By completeOrderButton = By.xpath("//*[contains(@text, \"Alışverişi Tamamla\")]");


    public void selectCategories() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(category)).click();
        Thread.sleep(600);
        wait.until(ExpectedConditions.visibilityOfElementLocated(subCategory1)).click();
        Thread.sleep(600);
        wait.until(ExpectedConditions.visibilityOfElementLocated(subCategory2)).click();
    }

    public void filterPrices50_100() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(filterButton)).click();
        Thread.sleep(600);
        wait.until(ExpectedConditions.visibilityOfElementLocated(filterInterval)).click();
        Thread.sleep(600);
        wait.until(ExpectedConditions.visibilityOfElementLocated(filterInterval_50_100)).click();
        Thread.sleep(600);
        wait.until(ExpectedConditions.visibilityOfElementLocated(filterApply)).click();
    }

    public void filterBestSellers() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(scoreInterval)).click();
        Thread.sleep(600);
        wait.until(ExpectedConditions.visibilityOfElementLocated(scoredMost)).click();
        Thread.sleep(600);
        wait.until(ExpectedConditions.visibilityOfElementLocated(filterApply)).click(); //Filtre uygula
        Thread.sleep(1200);
        wait.until(ExpectedConditions.visibilityOfElementLocated(displayProducts)).click(); //Ürünleri gör
    }

    public void addFirstProductToBasket() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(addFirstProductToBasket)).click(); //Ürünü sepete ekle
        Thread.sleep(600);
        wait.until(ExpectedConditions.visibilityOfElementLocated(addToBasket)).click(); //Sepete devam et
    }

    public void increaseProductQuantityAndCheck() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(goToBasket)).click();
        Thread.sleep(600);
        String[] str = wait.until(ExpectedConditions.visibilityOfElementLocated(price)).getText().split(" ",2);
        Long priceSingle = Long.parseLong(str[0].replace(",","").replace(".",""));
        Thread.sleep(600);
        wait.until(ExpectedConditions.visibilityOfElementLocated(increaseButton)).click();
        Thread.sleep(600);
        String[] str2 = wait.until(ExpectedConditions.visibilityOfElementLocated(price)).getText().split(" ",2);
        Long priceDouble = Long.parseLong(str2[0].replace(",","").replace(".",""));
        Long expectedPrice = priceSingle * 2;
        Thread.sleep(600);
        Assert.assertEquals(expectedPrice,priceDouble);
    }



    public void completeOrder() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(completeOrderButton)).click();

    }



    public void teardown() {
        driver.quit();
    }

}