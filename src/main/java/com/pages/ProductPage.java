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
	
	@AndroidFindBy(accessibility = "test-Toggle")
	AndroidElement toggleToViewModeButton;
	
	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Item\"]")
	List<AndroidElement> allVerticalElements;
	
	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Description\"]/android.widget.TextView[1]")
	AndroidElement productTitle;
	
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
		//Gestures.scrollIntoViewMethod("Test.allTheThings() T-Shirt (Red)");
		for(int i=0;i<allAvailableProducts.size()-1;) {
		//for(int i=0;i<allAvailableProducts.size();) {
			for(int j=0;j<2;j++) {
				allAvailableProducts.get(i+1).findElements(By.xpath("//android.view.ViewGroup[@content-desc=\"test-Item\"]")).get(j).click();
				//allAvailableProducts.get(i).findElements(By.xpath("//android.view.ViewGroup[@content-desc=\"test-Item\"]")).get(j).click();
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
	
	public String verticalProductsView() {
		toggleToViewModeButton.click();
		int count=0;
		try {
		for(int i=0;i<allVerticalElements.size();i++) {
			System.out.println(allVerticalElements.size());
			if(i<4) {
				allVerticalElements.get(i).click();
				productTitle.isDisplayed();
				count++;
				backToProductsButton.click();
				
			}
			else {
				Thread.sleep(3000);
				Gestures.scrollUsingDimension();
				Thread.sleep(3000);
				System.out.println(allVerticalElements.size());
				for(int j=0;j<allVerticalElements.size();j++) {
					if(j!=allVerticalElements.size()) {
						allVerticalElements.get(j).click();
						productTitle.isDisplayed();
						count++;
						backToProductsButton.click();
					}
					else {
						break;
					}
				}
				
				break;
			}
		}
	}
		catch(Exception e) {
			System.out.println(e.getMessage());
			allVerticalElements=driver.findElements(By.xpath("//android.view.ViewGroup[@content-desc=\"test-Item\"]"));
		}
		toggleToViewModeButton.click();
		System.out.println("Total no of elements present: "+count);
		String validationMessage=null;
		if(count==6) {
			validationMessage="All the elements are present and clickable";
		}
		else {
			validationMessage="Only "+count +" elements were clicked";
		}
		return validationMessage;
	}
	
	
}
