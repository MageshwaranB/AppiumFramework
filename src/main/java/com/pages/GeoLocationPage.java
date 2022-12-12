package com.pages;

import org.openqa.selenium.support.PageFactory;

import com.base.BaseClass;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class GeoLocationPage extends BaseClass{
	@AndroidFindBy(xpath = "//android.widget.Button[@text='ALLOW']")
	AndroidElement allowBtn;
	@AndroidFindBy(accessibility = "test-longitude")
	AndroidElement longitudeValue;
	@AndroidFindBy(accessibility = "test-latitude")
	AndroidElement latitudeValue;
	
	public GeoLocationPage() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public String geoLocationLatAndLong() {
		allowBtn.click();
		String latValue=latitudeValue.getText();
		String longValue=longitudeValue.getText();
		System.out.println(latValue+ ", "+longValue);
		return "Latitude and longtitude values are generated";
	}
}
