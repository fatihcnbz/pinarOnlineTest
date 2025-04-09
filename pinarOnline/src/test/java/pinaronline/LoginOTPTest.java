package pinaronline;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;
import java.time.Duration;

public class LoginOTPTest {
    public static void main(String[] args) throws InterruptedException {
        // WebDriver setup (Windows için tam yol ve .exe uzantısı)
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\USER\\Projeler\\pinarOnline\\drivers\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        // Siteyi aç
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
        // List<WebElement> otpInputs = driver.findElements(By.xpath("/html/body/div[1]/div[5]/div[1]/div/div/div[2]/div/div[1]/div/input[1]"));
        //String otp = "1414";
        List<WebElement> otpInputs = driver.findElements(By.cssSelector("input[type='number']"));
        String otp = "1414";

       // for (int i = 0; i < otp.length(); i++) {
         //   otpInputs.get(i).sendKeys(Character.toString(otp.charAt(i)));
        //}
        for (int i = 0; i < otp.length(); i++) {
            otpInputs.get(i).sendKeys(Character.toString(otp.charAt(i)));
        }

        Thread.sleep(4000);

        // "otpOnaylama " butonuna tıkla
        WebElement otpRegister = driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[1]/div/div/div[2]/div/button"));
        otpRegister.click();
        Thread.sleep(2000);


        // Girişin başarılı olup olmadığını kontrol et
        try {
            WebElement account = driver.findElement(By.xpath("//*[@id='Profil']"));
            System.out.println("✅ Login successful!");
        } catch (Exception e) {
            System.out.println("❌ Login failed!");
        }

        // Tarayıcıyı kapat
        driver.quit();
    }
}
