package server.control;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class seleniumWhatsApp {
	public seleniumWhatsApp(String numero, String messaggio) throws InterruptedException {
		Map prefs = new HashMap();
		prefs.put("binary", "C:(Program Files (x86)/Google/Chrome/Application/chrome.exe");
		System.setProperty("webdriver.chrome.driver", "C:/chromeDriver/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		options.addArguments("user-data-dir=C:\\Users\\ke_vi\\AppData\\Local\\Google\\Chrome\\User Data\\Default");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);

		WebDriver driver = new ChromeDriver(capabilities);

		// WebDriver driver = new ChromeDriver(options);
		String http = "https://api.whatsapp.com/send?phone=" + numero + "&text=" + messaggio;
		driver.get(http);
		// driver.get("https://web.telegram.org");

		driver.manage().window().maximize();
		try {
			Thread.sleep(2500);
			driver.findElement(By.xpath("//*[@id=\"action-button\"]")).click();
			Thread.sleep(5000);
		} catch (InterruptedException t) {
			Thread.sleep(2500);
			driver.findElement(By.xpath("//*[@id=\"action-button\"]")).click();
		}
		try {
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id=\"main\"]/footer/div[1]/button")).click();
		} catch (Exception e) {
			Thread.sleep(10000);
			driver.findElement(By.xpath("//*[@id=\"main\"]/footer/div[1]/button")).click();
		}

		Thread.sleep(500);
		driver.close();
	}

}
