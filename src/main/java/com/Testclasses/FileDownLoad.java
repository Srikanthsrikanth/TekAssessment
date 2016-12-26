package com.Testclasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.HelperUtil.BrowserUtil;

/**
 * @author Srikanth Sarikonda
 *Performs RemoteBrowser Testind and downloads the specified file.
 */
public class FileDownLoad {
	private Properties prop;
	private DesiredCapabilities capabilities;
	private String propertyFile = "E:\\selenium\\Tek-Assignment\\Input.properties";
    private HtmlUnitDriver driver;
	private FileInputStream fileInput;
	private String nyisUrl;
	private String downloadFilepath = "c:\\download";

	@Test
	public void getText() throws InterruptedException, IOException {
		
		File file = new File(propertyFile);

		try {
			fileInput = new FileInputStream(file);
			 prop = new Properties();
			prop.load(fileInput);
			nyisUrl = prop.getProperty("NyisoUrl");
			
            
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		System.setProperty("webdriver.chrome.driver", "E:\\selenium\\chromedriver_win32\\chromedriver.exe");
		

		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.prompt_for_download", "false");
		chromePrefs.put("download.default_directory", downloadFilepath);
		chromePrefs.put("plugins.plugins_disabled", new String[]{
			    "Adobe Flash Player", "Chrome PDF Viewer"});
		ChromeOptions options = new ChromeOptions();
		HashMap<String, Object> chromeOptionsMap = new HashMap<String, Object>();
		options.setExperimentalOption("prefs", chromePrefs);
		options.addArguments("--test-type");

		capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);

		capabilities.setCapability("chrome.binary", "E:\\selenium\\chromedriver_win32\\chromedriver.exe");
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);

		System.setProperty("webdriver.ie.driver", "E:\\selenium\\chromedriver_win32\\chromedriver.exe");
	}
	@Test
		public void loadSite() throws InterruptedException
		{
		driver = new HtmlUnitDriver(capabilities);
		driver.get(nyisUrl);
		Thread.sleep(4000);
		driver.switchTo().frame("MENU");
		driver.findElement(By.xpath(prop.getProperty("zonal_xpath"))).click();	
		Thread.sleep(4000);
		driver.switchTo().defaultContent();
		Thread.sleep(4000);
		driver.switchTo().frame("BODY");
		WebElement recentFile=driver.findElement(By.xpath(prop.getProperty("CvsFile_Xpath")));
		recentFile.click();
	}
	
	@AfterTest
	public void closeDriver()
	{
		driver.close();
	}
}
