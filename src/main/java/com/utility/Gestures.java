package com.utility;

import com.base.BaseClass;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.ElementOption;

public class Gestures extends BaseClass{
	static TouchAction touch=new TouchAction(driver);
	
	public static void simpleTapAction(AndroidElement androidElement) {
		touch.tap(ElementOption.element(androidElement)).perform();
	}
	
	public static void scrollingVertically(String androidElement) {
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+androidElement+"\" ))");
	}
}
