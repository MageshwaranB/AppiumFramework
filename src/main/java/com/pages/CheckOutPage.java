package com.pages;

import org.openqa.selenium.support.PageFactory;

import com.base.BaseClass;
import com.github.javafaker.Faker;
import com.utility.Gestures;
import com.utility.RandomData;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CheckOutPage extends BaseClass{
	@AndroidFindBy(accessibility = "test-CHECKOUT")
	AndroidElement checkoutBtn;
		
	@AndroidFindBy(accessibility = "test-First Name")
	AndroidElement firstNameTxtBox;
	
	@AndroidFindBy(accessibility = "test-Last Name")
	AndroidElement lastNameTxtBox;
	
	@AndroidFindBy(accessibility = "test-Zip/Postal Code")
	AndroidElement zipCodeTxtBox;
	@AndroidFindBy(accessibility = "test-CONTINUE")
	AndroidElement checkoutContinueBtn;
	
	@AndroidFindBy(accessibility = "test-FINISH")
	AndroidElement finishBtn;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'THANK YOU')]")
	AndroidElement confirmationText;
	
	public CheckOutPage() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public String checkoutProcess(RandomData data) {
		checkoutBtn.click();
		firstNameTxtBox.sendKeys(data.provideFirstName());
		lastNameTxtBox.sendKeys(data.provideLastName());
		zipCodeTxtBox.sendKeys(data.provideZipCode());
		checkoutContinueBtn.click();
		Gestures.scrollIntoViewMethod("FINISH");
		finishBtn.click();
		return confirmationText.getText();
		
		
		
	}
}
