package com.pages;

import org.openqa.selenium.support.PageFactory;

import com.base.BaseClass;
import com.utility.Gestures;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LoginPage extends BaseClass{
	@AndroidFindBy(accessibility  = "test-Username")
	AndroidElement userNameTxtBox;
	
	@AndroidFindBy(accessibility = "test-Password")
	AndroidElement passwordTxtBox;
	
	@AndroidFindBy(accessibility = "test-LOGIN")
	AndroidElement loginBtn;
	
	@AndroidFindBy(accessibility = "test-Error message")
	AndroidElement loginErrorMessage;
	
	public LoginPage() {
	/*
	 * Decorator is a structural pattern that allows adding new behaviors to objects 
	 * dynamically by placing them inside special wrapper objects, called decorators.
	 * Using decorators you can wrap objects countless number of times since both 
	 * target objects and decorators follow the same interface
	 */
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	ProductPage products=new ProductPage();
	public String logIntoApplication(String user, String pass,String scenario) {
		String validUserOrNot;
		userNameTxtBox.sendKeys(user);
		passwordTxtBox.sendKeys(pass);
		Gestures.simpleTapAction(loginBtn);
		if(scenario.equalsIgnoreCase("negative")) {
			//validUser=loginErrorMessage.getText();
			userNameTxtBox.clear();
			passwordTxtBox.clear();
			validUserOrNot="Not A Valid User, Please check the credentials";
		}
		else {
			validUserOrNot="Entered data is a valid one";
			products.logoutWithoutGestures();
			
		}
		return validUserOrNot;
	}
	
	public ProductPage simpleLogin(String user, String pass) {
		userNameTxtBox.sendKeys(user);
		passwordTxtBox.sendKeys(pass);
		loginBtn.click();
		return new ProductPage();
	}
	
	public void scrollingDownToElement() {
		Gestures.scrollingVertically("secret_sauce");
	}
}
