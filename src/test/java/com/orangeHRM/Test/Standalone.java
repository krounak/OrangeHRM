package com.orangeHRM.Test;

import org.testng.annotations.Test;

import com.orangeHRM.Pages.LoginPage;
import com.orangeHRM.base.BaseClass;

public class Standalone extends BaseClass{

	LoginPage login;
	
	@Test
	public void loginFunction() {
		login = new LoginPage(driver);
		login.login(userName, password);
		
		
	}
}
