package com.inetbanking.testcases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageobject.LoginPage;



public class TC_LoginTest_001 extends BaseClass{
	
	@Test
	public void LoginTest() throws IOException {
		
		logger.info("URL is opened");
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(username);
		logger.info("Entered password");
		lp.setPassword(password);
		logger.info("Entered password");
		lp.clickSubmit();
		
		if(driver.getTitle().equals("Guru99 Bank Manager HomePage123")) {
			Assert.assertTrue(true);
			logger.info("login test is passed");
			
		}else {
			captureScreen(driver,"LoginTest");
			Assert.assertFalse(false);
			logger.info("login test is failed");
		}
		
		
		
		
		
	}
	

}
