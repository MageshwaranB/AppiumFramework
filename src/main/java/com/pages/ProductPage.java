package com.pages;

import org.openqa.selenium.support.PageFactory;

import com.base.BaseClass;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductPage extends BaseClass{
	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Menu\"]/android.view.ViewGroup/android.widget.ImageView")
	AndroidElement burgerButton;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"LOGOUT\")")
	AndroidElement logoutButton;
	
	public ProductPage() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public void logoutWithoutGestures() {
		burgerButton.click();
		logoutButton.click();
	}
}
