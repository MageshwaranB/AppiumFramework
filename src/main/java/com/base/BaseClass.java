package com.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class BaseClass {
	public static Properties prop;
	public static AndroidDriver<AndroidElement> driver;
	public static DesiredCapabilities desiredCap;

	public BaseClass() {
		try {
			FileInputStream inputStream = new FileInputStream(
					System.getProperty("user.dir") + "./src/main/java/com/config/config.properties");
			prop=new Properties();
			prop.load(inputStream);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void initialization() {
		try {
			desiredCap = new DesiredCapabilities();
			desiredCap.setCapability("platformName", "Android");
			desiredCap.setCapability("platformVersion", "8.0");
			desiredCap.setCapability("deviceName", "Android Emulator");
			desiredCap.setCapability("appPackage", "com.swaglabsmobileapp");
			desiredCap.setCapability("appActivity", "com.swaglabsmobileapp.MainActivity");
			URL url = new URL("http://127.0.0.1:4723/wd/hub");
			driver = new AndroidDriver<AndroidElement>(url, desiredCap);
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void tearDown() {
		driver.quit();
	}
}
