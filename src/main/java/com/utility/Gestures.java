package com.utility;

import java.time.Duration;

import org.openqa.selenium.Dimension;

import com.base.BaseClass;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;

public class Gestures extends BaseClass{
	static TouchAction touch=new TouchAction(driver);
	
	public static void simpleTapAction(AndroidElement androidElement) {
		touch.tap(ElementOption.element(androidElement)).perform();
	}
	
	public static void scrollingVertically(String androidElement) {
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+androidElement+"\" ))");
	}
	
	
	public static void scrollUsingDimension() {
		Dimension dimension=driver.manage().window().getSize();
		Double scrollStartHeightDouble=dimension.getHeight()*0.7;
		int scrollStartHeight=scrollStartHeightDouble.intValue();
		Double scrollEndHeightDouble=dimension.getHeight()*0.1;
		int scrollEndHeight=scrollEndHeightDouble.intValue();
		
		new TouchAction(driver)
		.press(PointOption.point(0,scrollStartHeight))
		.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
		.moveTo(PointOption.point(0,scrollEndHeight))
		.release().perform();
	}
}
