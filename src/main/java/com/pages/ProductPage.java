package com.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.BaseClass;
import com.utility.Gestures;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductPage extends BaseClass
{
	@AndroidFindBy(xpath = "//android.widget.ScrollView[@content-desc=\"test-PRODUCTS\"]/android.view.ViewGroup/android.view.ViewGroup")
	List<AndroidElement> allAvailableProducts;
	@AndroidFindBy(accessibility = "test-Menu")
	AndroidElement burgerButton;
	
	@AndroidFindBy(accessibility = "test-LOGOUT")
	AndroidElement logoutButton;
	
	@AndroidFindBy(accessibility = "test-BACK TO PRODUCTS")
	AndroidElement backToProductsButton;
	
	public ProductPage() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	public void logoutWithoutGestures() {
		burgerButton.click();
		logoutButton.click();
	}

	public String validatingTheExistenceOfAllProducts() {
		int count=0;
		try {
		for(int i=0;i<allAvailableProducts.size();i++) {
			for(int j=0;j<2;j++) {
				allAvailableProducts.get(i).findElements(By.xpath("(//android.view.ViewGroup[@content-desc=\"test-Item\"])")).get(j) .click();
				backToProductsButton.click();
				count++;
			}
//			Thread.sleep(3000);
//			Gestures.scrollUsingDimension();
		}
		Thread.sleep(5000);
		Gestures.scrollUsingDimension();
		for(int i=0;i<allAvailableProducts.size()-1;) {
			for(int j=0;j<2;j++) {
				allAvailableProducts.get(i+1).findElements(By.xpath("//android.view.ViewGroup[@content-desc=\"test-Item\"]")).get(j).click();
				backToProductsButton.click();
				count++;
			}
			break;
		}
		}
		catch(Exception e) {
			allAvailableProducts=driver.findElements(By.xpath("//android.widget.ScrollView[@content-desc=\\\"test-PRODUCTS\\\"]/android.view.ViewGroup/android.view.ViewGroup"));
		}
		System.out.println(count);
		String validationMessage=null;
		if(count>=6) {
			validationMessage="All the elements are present and clickable";
		}
		else {
			validationMessage="Only "+count +" elements were clicked";
		}
		return validationMessage;
	}
	
}
