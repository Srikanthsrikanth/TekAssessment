package com.Testclasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.HelperUtil.BrowserUtil;


/**
 * @author Srikanth Sarikonda
 *Tests the Most recent date in the 'http://mis.nyiso.com/public/'
 */
public class NyisoTest {
	private WebDriver driver;
	private Properties prop;
	private String filePath = "E:\\selenium\\chromedriver_win32\\Test.xlsx";
	private String propertyFile = "E:\\selenium\\Tek-Assignment\\Input.properties";

	private FileInputStream fileInput;
	private BrowserUtil browser;
	private String nyisUrl;
	private String email;

	@BeforeTest
	public void loadSite() throws IOException, InterruptedException {
		File file = new File(propertyFile);

		try {
			fileInput = new FileInputStream(file);
			prop = new Properties();
			prop.load(fileInput);
			nyisUrl = prop.getProperty("NyisoUrl");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		browser = new BrowserUtil("chrome", nyisUrl);
		driver = browser.loadBrowser();
	   driver.manage().timeouts().implicitlyWait(4000, TimeUnit.SECONDS);

	}

	@Test
	public void getText() throws InterruptedException {
		driver.switchTo().frame("MENU");
		WebElement zonal=driver.findElement(By.xpath(prop.getProperty("zonal_xpath")));
		zonal.click();
        Thread.sleep(4000);
		driver.switchTo().defaultContent();
		Thread.sleep(4000);
		driver.switchTo().frame("BODY");
		String lastUpdatedText = driver.findElement(By.xpath(prop.getProperty("LastUploadEelement_Xpath"))).getText();
		System.out.println("LastUpdatedText: " + lastUpdatedText);

	}

	@AfterTest
	public void closeDriver() {

		driver.close();

	}

}
