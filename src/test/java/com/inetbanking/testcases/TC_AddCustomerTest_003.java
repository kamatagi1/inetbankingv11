package com.inetbanking.testcases;

import java.io.IOException;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageobject.AddCustomerPage;
import com.inetbanking.pageobject.LoginPage;

public class TC_AddCustomerTest_003 extends BaseClass{
	
	
	@Test
	public void addNewCustomer() throws InterruptedException, Exception {
		
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(username);
		logger.info("Use name is provided");
		lp.setPassword(password);
		logger.info("password is provided");
		lp.clickSubmit();
		
		Thread.sleep(3000);
		
		AddCustomerPage addcust=new AddCustomerPage(driver);
		addcust.clickAddNewCustomer();
		logger.info("providing customer details......");
		
		addcust.custName("pavan");
		addcust.custgender("male");
		addcust.custdob("10", "15", "1985");
		Thread.sleep(3000);
		addcust.custaddress("INDIA");
		addcust.custcity("Bang");
		addcust.custstate("KA");
		addcust.custpinno("500085");
		addcust.custtelphoneno("987890091");
		String email=randomeString()+"@gmail.com";
		addcust.custemailid(email);
		addcust.custpassword("abcdef");
		addcust.custsubmit();
		
		Thread.sleep(5000);
		
		logger.info("validation is started......");
		
		//validate
		boolean res=driver.getPageSource().contains("Customer Registered Successfully!!!");
				
		if(res==true) {
			Assert.assertTrue(true);
			logger.info("test case is passed......");
		}else {
			
			captureScreen(driver, "addNewCustomer");
			Assert.assertFalse(false);
			logger.info("test case is failed.......");
		}
		
		}
		

}
