package pinaronline;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class GuestCheckoutTest {
    public static void main(String[] args) throws InterruptedException {
        // WebDriver setup
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        // Siteye git
        driver.get("https://testrio.pinaronline.com/");
        Thread.sleep(2000);

        // 3. Kategori menüsünü bul (örnek: “Ürünler” menüsü)
        WebElement kategoriElement = driver.findElement(By.xpath("//span[text()='Kategoriler']"));
        //WebElement kategoriMenu = driver.findElement(By.xpath("<span class="text-[0.875rem] font-semi-bold">Kategoriler</span>"));
        org.openqa.selenium.interactions.Actions action = new org.openqa.selenium.interactions.Actions(driver);
        action.moveToElement(kategoriElement).perform();
        Thread.sleep(2000);



        // 4. Alt kategoriye tıkla (örnek: “Süt Ürünleri”)
        WebElement altKategori = driver.findElement(By.xpath("/html/body/div[1]/div[12]/div[2]/div[2]/div/div[1]/a[6]/div/span"));
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

        // Ürünü sepete ekle
        WebElement sepeteEkleButonu = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/main[1]/section[1]/div[2]/div[5]/button[1]"));
        sepeteEkleButonu.click();
        System.out.println("✅ Ürün sepete eklendi");
        Thread.sleep(3000);

        // İl dropdown'a tıklanır
        WebElement ilDropdown = driver.findElement(By.xpath("/html/body/div[1]/div[5]/div/div/div/div/div[2]/button"));
        ilDropdown.click();
        Thread.sleep(2000); // açılma animasyonu için bekleme

        // Aşağıdan 'İstanbul' seçilir
        WebElement istanbulOption = driver.findElement(By.xpath("//span[text()='İstanbul']"));
        istanbulOption.click();
        Thread.sleep(2000);

        /*// İlce dropdown'a tıklanır
        WebElement ilceDropdown = driver.findElement(By.xpath("/html/body/div[1]/div[5]/div/div/div/div/div[3]/button"));
        ilceDropdown.click();
        Thread.sleep(2000); // açılma animasyonu için bekleme

        WebElement beylikduzuOption = driver.findElement(By.xpath("//div[contains(text(),'Arnavutköy')]"));
        beylikduzuOption.click();
        Thread.sleep(2000);
*/
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement comboboxButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[5]/div/div/div/div/div[3]/button")));
        comboboxButton.click();

       // WebElement beylikduzuOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Beylikdüzü')]")));
        //beylikduzuOption.click();

        // Aşağı ok tuşuyla gezinmek için Actions sınıfı
        Actions actions = new Actions(driver);

// 10 kere aşağı git (örneğin 10. sırada Beylikdüzü olduğunu varsayarsak)
        for (int i = 0; i < 12; i++) {
            actions.sendKeys(Keys.ARROW_DOWN).perform();
            Thread.sleep(200); // akıcı kaydırma için kısa bekleme
        }

// Enter ile seç
        actions.sendKeys(Keys.ENTER).perform();
        Thread.sleep(2000);

        // mahalle dropdown'a tıklanır
        WebElement mahalleDropdown = driver.findElement(By.xpath("/html/body/div[1]/div[5]/div/div/div/div/div[4]/button"));
        mahalleDropdown.click();
        Thread.sleep(2000); // açılma animasyonu için bekleme


       // Aşağı ok tuşuyla gezinmek için Actions sınıfı
        //Actions actions = new Actions(driver);

// 10 kere aşağı git (örneğin 10. sırada Beylikdüzü olduğunu varsayarsak)
        for (int i = 0; i < 1; i++) {
            actions.sendKeys(Keys.ARROW_DOWN).perform();
            Thread.sleep(200); // akıcı kaydırma için kısa bekleme
        }

// Enter ile seç
        actions.sendKeys(Keys.ENTER).perform();
        Thread.sleep(2000);

        WebElement adresOnayButton = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[5]/div[1]/div[1]/div[1]/div[1]/button[1]"));
                adresOnayButton.click();
        Thread.sleep(4000);


        //BURADAN DEVAM

        // Sepete git
        WebElement sepetButton = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/main[1]/section[1]/div[2]/div[5]/button[1]"));
        sepetButton.click();
        Thread.sleep(2000);

        // Siparişi tamamla butonuna tıkla
        WebElement tamamlaButton = driver.findElement(By.xpath("//button[contains(text(),'Siparişi Tamamla')]"));
        tamamlaButton.click();
        Thread.sleep(3000);

        // Misafir kullanıcı olarak telefon numarası girme sayfasına yönlendiriliyor mu?
        WebElement phoneInput = driver.findElement(By.name("phoneNumber"));
        if (phoneInput.isDisplayed()) {
            System.out.println("✅ Misafir kullanıcı ekranı açıldı.");
            phoneInput.sendKeys("5555555555");
            phoneInput.submit(); // Enter basılmış gibi
            System.out.println("📱 Telefon numarası gönderildi. OTP bekleniyor...");
        } else {
            System.out.println("❌ Misafir kullanıcı ekranı görüntülenemedi.");
        }

        Thread.sleep(5000);

        // Tarayıcıyı kapat
        driver.quit();
    }
}



