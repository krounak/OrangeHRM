package com.orangeHRM.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	
	protected static WebDriver driver;
	
	public static final String PROPERTES_FILES = System.getProperty("user.dir") +"./Global Setting.properties";
	public static  String Browser = null;
	public static String URL = null;
	public static String userName = null;
	public static String password = null;
	static {
		Properties appProps = new Properties();
		try {
			appProps.load(new FileInputStream(PROPERTES_FILES));
			Browser = appProps.getProperty("browser");
			URL = appProps.getProperty("URL");
			userName = appProps.getProperty("username");
			password = appProps.getProperty("password");
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}
	
	@BeforeSuite
	public void beforeSetup() {
		
		try {
			if(Browser.equalsIgnoreCase("Chrome")){
				WebDriverManager.chromedriver().setup();
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-notifications");
				driver= new ChromeDriver();
			}
			else if (Browser.equalsIgnoreCase("Firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			}
			
			driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
			driver.manage().window().maximize();
			
			
		} catch (Exception e) {
			 throw new RuntimeException("Unsupported driver");
		}
	}
	
	@BeforeMethod
	public void setUpBrowser() {
		driver.get(URL);
		
	}
	
	 @AfterSuite
	    public void suiteTearDown() {
	     	driver.quit();
	    }
	 
	 
}
