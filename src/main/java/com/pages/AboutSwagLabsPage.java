package com.pages;

import org.openqa.selenium.support.PageFactory;

import com.base.BaseClass;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AboutSwagLabsPage extends BaseClass{
	final static String chromePackageName="com.android.chrome:id/";
	@AndroidFindBy(id = ""+chromePackageName+"terms_accept")
	AndroidElement acceptTerms;
	@AndroidFindBy(id = ""+chromePackageName+"negative_button")
	AndroidElement noThanksBtn;
	
	public AboutSwagLabsPage() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public boolean swagLabsParentPage() {
		//acceptTerms.click();
		//noThanksBtn.click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(driver.getTitle());
		return driver.getPageSource().contains("Sauce Labs");
	}
}
