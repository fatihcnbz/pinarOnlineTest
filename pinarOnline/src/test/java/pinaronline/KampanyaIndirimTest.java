package pinaronline;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class KampanyaIndirimTest {
    public static void main(String[] args) throws InterruptedException {
        // WebDriver setup
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        // Siteye git
        driver.get("https://testrio.pinaronline.com/");

        // Kategori seç (örnek: Süt)
        WebElement kampanyaButton = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[12]/div[2]/div[1]/ul[1]/li[5]/a[1]"));
        kampanyaButton.click();
        Thread.sleep(1000);

        WebElement altKampanya = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/main[1]/div[2]/section[1]/div[1]/div[1]/a[1]/div[1]/div[1]/img[1]"));
        altKampanya.click();
        Thread.sleep(2000);

        // Sayfadaki ürünler içinde indirimli olanı bul
        List<WebElement> indirimliUrunler = driver.findElements(By.xpath("//del[contains(@class, 'line-through')]"));

        if (indirimliUrunler.size() > 0) {
            System.out.println("✅ En az bir indirimli ürün bulundu.");
            for (WebElement eskiFiyat : indirimliUrunler) {
                WebElement indirimliFiyat = eskiFiyat.findElement(By.xpath("./following-sibling::span"));
                System.out.println("🟡 Eski Fiyat: " + eskiFiyat.getText() + " | Yeni Fiyat: " + indirimliFiyat.getText());
            }
        } else {
            System.out.println("❌ İndirimli ürün bulunamadı.");
        }

        // Tarayıcıyı kapat
        driver.quit();
    }
}
