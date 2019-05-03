package com.inetbanking.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver Idriver;
	
	public LoginPage(WebDriver rdriver){
		
		Idriver=rdriver;
		PageFactory.initElements(rdriver, this);
	}
	@FindBy(name="uid")
	@CacheLookup
	WebElement textUserName;
	
	@FindBy(name="password")
	@CacheLookup
	WebElement textPassword;
	
	@FindBy(name="btnLogin")
	@CacheLookup
	WebElement btnLogin;
	
	@FindBy(xpath="/html/body/div[3]/div/ul/li[15]/a")
	@CacheLookup
	WebElement lnkLogout;
	
	public void setUserName(String uname) {
		textUserName.sendKeys(uname);
	}
	public void setPassword(String pwd) {
	textPassword.sendKeys(pwd);
	}
	public void clickSubmit() {
		btnLogin.click();
	}
	public void clicklogout() {
		lnkLogout.click();
	}
}
