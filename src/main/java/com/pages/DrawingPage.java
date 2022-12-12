package com.pages;

import org.openqa.selenium.support.PageFactory;

import com.base.BaseClass;
import com.utility.Gestures;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class DrawingPage extends BaseClass{
	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-DRAWING-SCREEN\"]/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.widget.Image")
	AndroidElement drawingPad;
	@AndroidFindBy(accessibility = "test-CLEAR")
	AndroidElement clearButton;
	@AndroidFindBy(accessibility = "test-SAVE")
	AndroidElement saveButton;
	
	public DrawingPage() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public String drawingSomethingInTheBoard() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Gestures.dualTouchGesture();
		clearButton.click();
		return "The image is erased";
	}
}
