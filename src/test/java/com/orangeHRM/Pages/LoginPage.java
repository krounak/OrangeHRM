package com.orangeHRM.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;
	
	public LoginPage(WebDriver driver) {	
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//input[@name='username']")
	WebElement username;
	
	@FindBy(xpath = "//input[@name='password']")
	WebElement password;
	
	@FindBy(xpath = "//button[@type='submit']")
	WebElement login; 
	
	
	private boolean userNameFunctionality() {
		 return username.isEnabled()?true:false;
	}
	private boolean passwordFunctionality() {
		return password.isEnabled()?true:false;
	}
	private boolean loginBtnFunctionality() {
		return login.isEnabled()?true:false;
	}
	private boolean isPasswordMasked() {
		String attribute = password.getAttribute("type");
		return attribute.equalsIgnoreCase("password");
	}
	private void setUsername(String userName) {
		username.sendKeys(userName);
	}
	private void setPassword(String pwd) {
		password.sendKeys(pwd);
	}
	private boolean checkHover() {
		String cssValue = driver.findElement(By.xpath("//p[text()='Forgot your password? ']")).getCssValue("cursor");
		if(cssValue.equalsIgnoreCase("pointer")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private void waitForAlert() throws InterruptedException {
		int i=0;
		while(i++<5) {
			try {
				driver.switchTo().alert().accept();
			} catch (NoAlertPresentException e) {
				 Thread.sleep(2000);
				 continue;
			}
		}
	}
	public void login(String userName,String pwd) {
		   
		 if(userNameFunctionality() && passwordFunctionality() && loginBtnFunctionality() && isPasswordMasked() && checkHover()) {
			 System.out.println("Functionality is enabled");
		 }
		 else {
			 System.out.println("Functionality is disabled");
		 }
		 
		 setUsername(userName);
		 setPassword(pwd);
		 login.click();
		 try {
			waitForAlert();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
