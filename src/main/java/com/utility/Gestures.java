package com.utility;

import java.time.Duration;

import org.openqa.selenium.Dimension;

import com.base.BaseClass;

import io.appium.java_client.MultiTouchAction;
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
	
	public static void scrollIntoViewMethod(String elementText) {
		
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+elementText+"\"))").click();;
		String val="\"" +elementText + "\"";
		System.out.println(val);
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
	
	public static void scrollUpUsingDimension() {
		Dimension dimension=driver.manage().window().getSize();
		Double scrollStartHeightDouble=dimension.getHeight()*0.1;
		int scrollStartHeight=scrollStartHeightDouble.intValue();
		Double scrollEndHeightDouble=dimension.getHeight()*0.8;
		int scrollEndHeight=scrollEndHeightDouble.intValue();
		
		new TouchAction(driver)
		.press(PointOption.point(0,scrollStartHeight))
		.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
		.moveTo(PointOption.point(0,scrollEndHeight))
		.release().perform();
	}
	
	public static void scrollUpAndDown(String exactText) {
		driver.findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\""+exactText+"\").instance(0))");
	}
	
	public static void dualTouchGesture() {
		Dimension dimension=driver.manage().window().getSize();
		int scrollStartHeight=(int) (dimension.getHeight()*0.4);
		int scrollEndHeight=(int)(dimension.getHeight()*0.7);
		int scrollWidthAnchor=(int)(dimension.getWidth()*0.3);
		TouchAction touch1=new TouchAction(driver)
		.press(PointOption.point(scrollWidthAnchor,scrollStartHeight))
		.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
		.moveTo(PointOption.point(scrollWidthAnchor,scrollEndHeight))
		.release().perform()
		;
		
		
		Double swipeToRightEnds=dimension.getWidth()*0.7;
		int swipeToRight=swipeToRightEnds.intValue();
		TouchAction touch2=new TouchAction(driver)
		.press(PointOption.point(scrollWidthAnchor, scrollEndHeight))
		.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
		.moveTo(PointOption.point(swipeToRight, scrollEndHeight))
		.release().perform();
		
	}
}
