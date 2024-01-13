package steps;

import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.EbebekTest_Pages;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class EbebekTest_Steps {

    EbebekTest_Pages page = new EbebekTest_Pages();


    @When("Testi baslat")
    public void baslat() {
        page.setup();
    }

    @And("Kategorilerden herhangi bir kategori ve alt kategori secilir")
    public void kategori_altKategoriSec() throws InterruptedException {
        page.selectCategories();
     }

    @And("Filtrelerden fiyat araligi 50-100 secilir")
    public void fiyatFiltresi50_100() throws InterruptedException {
        page.filterPrices50_100();
    }

    @And("Siralamadan Cok Degerlendirilenler secilip ilk urun sepete atilir")
    public void cokDegerlendirilenlerFiltresi_SepeteAt() throws InterruptedException {
        page.filterBestSellers();
        page.addFirstProductToBasket();
    }

    @And("Sepete gidilip urun arttirilir ve fiyat kontrolu yapilir")
    public void urunMiktariArttir() throws InterruptedException {
        page.increaseProductQuantityAndCheck();
    }


    @And("Alisverisi tamamla butonuna basilir")
    public void alisverisiTamamla(){
        page.completeOrder();

    }

    @When("Bekle")
    public void bekle() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }




}