package com.inetbanking.testcases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetbanking.pageobject.LoginPage;
import com.inetbanking.utilities.XLUtils;

public class TC_LoginDDT_002 extends BaseClass {
	
	@Test(dataProvider="Logindata")
	public void loginDDT(String user,String pwd) throws InterruptedException {
		
		 LoginPage lp=new  LoginPage(driver);
		 lp.setUserName(user);
		 logger.info("user name provided");
		 lp.setPassword(pwd);
		 logger.info("password provided");
		 lp.clickSubmit();
		 Thread.sleep(3000);
		 
		 if(isAlertPresent()==true) {
			driver.switchTo().alert().accept(); // close alert
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
			logger.warning("login failed");
		 }else
		 {
			 Assert.assertTrue(true);
			 logger.warning("login passed");
			 lp.clicklogout();
			 Thread.sleep(3000);
			 driver.switchTo().alert().accept();//close logout alert
			 driver.switchTo().defaultContent();
		 }
		 
		
	}
	
	public boolean isAlertPresent() { //user defined method created to check alert is present are not
		try {
			
		driver.switchTo().alert();
		return true;
		
			}catch(NoAlertPresentException e)
		
			{
				return false;
			}
	}
	
	@DataProvider(name="Logindata")
	String[][] getData() throws Exception
	{
		String path=System.getProperty("user.dir")+ "/src/test/java/com/inetbanking/testdata/Logindata.xlsx";
		
		int rownum=XLUtils.gerRowCount(path, "Sheet1");
		int colcount=XLUtils.getCellCount(path, "Sheet1", 1);
		String logindata[][]=new String[rownum][colcount];
		for(int i=1;i<=rownum;i++)
		{
			for(int j=0;j<colcount;j++)
			{
				logindata[i-1][j]=XLUtils.getCellData(path, "Sheet1", i, j);
			}
		}
			
		return logindata;
		
	}
	
	

}
