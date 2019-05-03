package com.inetbanking.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class ReadConfig {
	Properties pro;
	public ReadConfig() {
		File src=new File("./configuration\\config.properties");
		try {
			FileInputStream fis =new FileInputStream(src);
			pro=new Properties();
			pro.load(fis);
		} catch (Exception e) {


		System.out.println("Exception is " + e.getMessage());
		}
	}
	public String getApplicationURL() {
		String url=pro.getProperty("baseURL");
		return url;
	}
	public String getUsername() {
		String Username=pro.getProperty("username");
		return Username;
		
	}
	public String getPassword() {
		String Password=pro.getProperty("password");
		return Password;
		
	}
	public String getChromePath() {
		String chromepath=pro.getProperty("chromepath");
		return chromepath;
		
	}
	public String getfirefoxPath() {
		String firefoxPath=pro.getProperty("firefoxpath");
		return firefoxPath;
		
	}
}
