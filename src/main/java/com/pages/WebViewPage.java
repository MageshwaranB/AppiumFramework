package com.pages;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.base.BaseClass;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class WebViewPage extends BaseClass{
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='WEBVIEW SELECTION']")
	AndroidElement webViewBanner;
	@AndroidFindBy(accessibility = "test-enter a https url here...")
	AndroidElement searchTxtBox;
	@AndroidFindBy(accessibility = "test-GO TO SITE")
	AndroidElement goToSiteBtn;
	@AndroidFindBy(xpath = "//android.widget.Image[@text='Google']")
	AndroidElement confirmationTextElement;
	
	public WebViewPage() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	WebDriverWait wait=new WebDriverWait(driver, 10);
	public String navigateToAWebsite(String url) {
		webViewBanner.isDisplayed();
		searchTxtBox.sendKeys(url);
		goToSiteBtn.click();
		wait.until(ExpectedConditions.visibilityOf(confirmationTextElement));
		return confirmationTextElement.getText();
	}
}
