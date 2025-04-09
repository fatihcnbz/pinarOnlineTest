import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import java.time.Duration;
import java.util.List;

public class katagoriAltkatagoriUrundetaylari {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();


        try {
            // 1. Siteye git
            driver.get("https://testrio.pinaronline.com");

            // 2. (Varsa) giriş popup'ı veya reklam kapat
            try {
                WebElement closePopup = driver.findElement(By.cssSelector("button[aria-label='Close']"));
                closePopup.click();
            } catch (Exception e) {
                // popup yoksa geç
            }        Thread.sleep(2000);


            // 3. Kategori menüsünü bul (örnek: “Ürünler” menüsü)
            WebElement kategoriElement = driver.findElement(By.xpath("//span[text()='Kategoriler']"));
            //WebElement kategoriMenu = driver.findElement(By.xpath("<span class="text-[0.875rem] font-semi-bold">Kategoriler</span>"));
            Actions action = new Actions(driver);
            action.moveToElement(kategoriElement).perform();
            Thread.sleep(2000);



            // 4. Alt kategoriye tıkla (örnek: “Süt Ürünleri”)
            WebElement altKategori = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[12]/div[2]/div[2]/div[1]/div[1]/a[3]/div[1]/span[1]"));
            altKategori.click();
            Thread.sleep(2000);


            // 2. (Varsa) giriş popup'ı veya reklam kapat
            try {
                WebElement closePopup = driver.findElement(By.cssSelector("button[aria-label='Close']"));
                closePopup.click();
            } catch (Exception e) {
                // popup yoksa geç
            }        Thread.sleep(2000);


            // 5. Ürün listesinden ilk ürünü seç
            List<WebElement> urunler = driver.findElements(By.xpath("/html[1]/body[1]/div[1]/main[1]/div[1]/div[1]/section[1]/div[3]/div[2]/div[1]/a[1]/div[1]/div[2]/div[3]/div[1]/p[1]"));
            if (!urunler.isEmpty()) {
                urunler.get(0).click();
            } else {
                System.out.println("Ürün bulunamadı.");
            }

            // 6. Ürün detay sayfasına ulaşıldı mı kontrol et
            WebElement urunAdi = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/main[1]/section[1]/div[3]/div[1]/div[1]/span[1]"));
            if (urunAdi.isDisplayed()) {
                System.out.println("Test Başarılı: Ürün detay sayfasına ulaşıldı → " + urunAdi.getText());
            } else {
                System.out.println("Test Başarısız: Ürün adı bulunamadı.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Test tamamlandığında tarayıcıyı kapat
            driver.quit();
        }
    }
}
