package com.HelperUtil;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.annotations.Listeners;

public class BrowserUtil {

	static String browserName;
	static String url;

	static WebDriver driver;

	public  BrowserUtil(String browserName ) {
		this.browserName = browserName;	
		
	}
	public BrowserUtil(String browserName, String url) {

		this.browserName = browserName;
		this.url = url;

	}

	public static WebDriver loadBrowser() {
		if (browserName.equalsIgnoreCase("Firefox")) {

			driver = new FirefoxDriver();

		} else if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "E:\\selenium\\chromedriver_win32\\chromedriver.exe");

			driver = new ChromeDriver();

		} else {
			System.out.println("Enter name of the browser");
		}

		driver.get(url);
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
		return driver;

	}

	public static WebDriver remoteBrowser() {
		

		driver = new HtmlUnitDriver(true);
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
		return driver;

	}
}
