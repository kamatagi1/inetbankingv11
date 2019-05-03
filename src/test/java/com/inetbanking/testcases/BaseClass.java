package com.inetbanking.testcases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.inetbanking.utilities.ReadConfig;

public class BaseClass {
	ReadConfig readconfig=new ReadConfig();
	public String baseURL=readconfig.getApplicationURL();
	public String username=readconfig.getUsername();
	public String password=readconfig.getPassword(); 
	public static WebDriver driver;
	public static Logger logger;
	
	@Parameters("browser")
	@BeforeClass
	public void setup(String br) {
		
		logger=Logger.getLogger("ebanking");
		PropertyConfigurator.configure("Log4j.properties");
		if(br.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",readconfig.getChromePath());
			driver=new ChromeDriver();
			
		}
		else if(br.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver",readconfig.getfirefoxPath());
			driver=new FirefoxDriver();
			
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(baseURL);
		
		}
		
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	//to generate scrrenshot
	public void captureScreen(WebDriver driver,String tname) throws IOException {
		TakesScreenshot ts= (TakesScreenshot) driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File target=new File(System.getProperty("user.dir") + "/ScreenShorts/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screen shot taken");
	}
	
	//to genetate random email id
			public String randomeString() {
				String generatedstring=RandomStringUtils.randomAlphabetic(8);
				return generatedstring;
			}
			
			public static String randomeNUm() {
				String generatedstring2=RandomStringUtils.randomNumeric(4);
				return generatedstring2;
			}


}
