package com.inetbanking.utilities;
//listener class

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
public class Reporting extends TestListenerAdapter{
	 ExtentHtmlReporter htmlReporter;
	 ExtentReports extent;
	 ExtentTest logger;
	 

	 public void onStart(ITestContext testContext){
		 String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		 String repName="Test-Report-"+timeStamp+".html";
	 
	 htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/" + repName);
	 htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"/extent-config.xml");
	 
	 extent = new ExtentReports ();
	 extent.attachReporter(htmlReporter);
	 extent.setSystemInfo("Host Name", "localhost");
	 extent.setSystemInfo("Environment", "QA");
	 extent.setSystemInfo("user", "pavan");
	 
	 htmlReporter.config().setDocumentTitle("InetBabking Test Project");
	 htmlReporter.config().setReportName("Functional Test Automation Report");
	 htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
	 htmlReporter.config().setTheme(Theme.STANDARD);
	 }
	 
	
	 public void onTestSuccess(ITestResult tr){
	 logger = extent.createTest(tr.getName());
	
	 logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
	 }
	 
	
	 public void onTestFailure(ITestResult tr){
	 logger = extent.createTest(tr.getName());
	 logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));
	 String screenshotPath=System.getProperty("user.dir")+"\\ScreenShorts\\"+tr.getName()+".png";
	 File f=new File( screenshotPath);
	 if(f.exists()) {
		 try {
			logger.fail("ScreenShot is below:" +logger.addScreenCaptureFromPath(screenshotPath));
		} catch (Exception e) {
			e.printStackTrace();
		
		}
		 
	 }
	 }
	 
	
	 public void onTestSkipped(ITestResult tr){
	 logger = extent.createTest(tr.getName());
	 logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
	 }

	 public void endReport(ITestContext testcontext){
	 extent.flush();
	    }

}
