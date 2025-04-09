package pinaronline;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import java.util.List;

public class SepetMiktarDegistirmeTest {
    public static void main(String[] args) throws InterruptedException {
        // WebDriver setup
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        // Siteye git
        driver.get("https://testrio.pinaronline.com/");

        // "Giriş Yap" butonuna tıkla
        WebElement loginButton = driver.findElement(By.xpath("/html/body/div[1]/div[12]/div[1]/div/div/div[3]/button[1]/button"));
        loginButton.click();
        Thread.sleep(2000);

        // "Giriş Yap veya  Kaydol" butonuna tıkla
        WebElement loginButtonOrRegister = driver.findElement(By.xpath("/html/body/div[5]/div/div[1]/button/span"));
        loginButtonOrRegister.click();
        Thread.sleep(2000);

        // Telefon numarası gir
        WebElement phoneInput = driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[1]/div/div/div[2]/div/label/input"));
        phoneInput.sendKeys("397369423");

        // "TelefonNoOnaylama " butonuna tıkla
        WebElement PhoneRegister = driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[1]/div/div/div[2]/div/button/span"));
        PhoneRegister.click();
        Thread.sleep(2000);


        // OTP kodunu gir (1414)

        List<WebElement> otpInputs = driver.findElements(By.cssSelector("input[type='number']"));
        String otp = "1414";

        for (int i = 0; i < otp.length(); i++) {
            otpInputs.get(i).sendKeys(Character.toString(otp.charAt(i)));
        }

        Thread.sleep(4000);

        // "otpOnaylama " butonuna tıkla
        WebElement otpRegister = driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[1]/div/div/div[2]/div/button"));
        otpRegister.click();
        Thread.sleep(2000);

        // Kategori menüsüne git (örnek: "Süt Ürünleri")
        WebElement kategoriButton = driver.findElement(By.xpath("//span[text()='Kategoriler']"));
        kategoriButton.click();
        Thread.sleep(1000);

        // Alt kategoriye tıkla (örnek: "Süt")
        WebElement altKategori = driver.findElement(By.xpath("//span[contains(text(),'Süt')]"));
        altKategori.click();
        Thread.sleep(2000);


        //Ürün listesinde ilk ürünün içerisine gir
        WebElement UrunicerisineGir = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/main[1]/div[1]/div[1]/section[1]/div[3]/div[2]/div[1]/a[1]/div[1]/div[2]/div[1]/div[2]"));
        UrunicerisineGir.click();
        Thread.sleep(2000);

        // Ürünü sepete ekle
        WebElement sepeteEkleButonu = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/main[1]/section[1]/div[2]/div[5]/button[1]"));
        sepeteEkleButonu.click();
        Thread.sleep(10000);

        // Sepet ikonuna tıkla
        WebElement sepetIkonu = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[12]/div[1]/div[1]/div[1]/div[3]/button[2]/button[1]/span[1]"));
        sepetIkonu.click();
        Thread.sleep(2000);

        // Sepete git butonuna  tıkla
        WebElement sepeteGitButonu = driver.findElement(By.xpath("//span[normalize-space()='Sepete Git']"));
        sepeteGitButonu.click();
        Thread.sleep(2000);

        // Miktarı artır (+ butonuna bas)
        WebElement artiButonu = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/main[1]/section[1]/div[2]/div[3]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/button[2]/*[name()='svg'][1]/*[name()='path'][1]"));
        artiButonu.click();
        Thread.sleep(2000);

        // Yeni miktarı al ve kontrol et
        WebElement miktarElement = driver.findElement(By.xpath("//div[contains(@class,'relative') and contains(@class,'flex-row')]//span[not(contains(@class, 'hidden')) and text()='2']"));
        String miktar = miktarElement.getText();

        if (miktar.equals("2")) {
            System.out.println("✅ Miktar başarıyla artırıldı.");
        } else {
            System.out.println("❌ Miktar artırılamadı. Mevcut: " + miktar);
        }

        // Tarayıcıyı kapat
        driver.quit();
    }
}
