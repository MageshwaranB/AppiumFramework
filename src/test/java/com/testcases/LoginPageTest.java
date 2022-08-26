package com.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.pages.LoginPage;

public class LoginPageTest extends BaseClass
{
	LoginPage loginFunction;
	@BeforeMethod
	public void setup() {
		initialization();
		loginFunction=new LoginPage();
	}
	
	@Test
	public void loginTest() {
		loginFunction.logIntoApplication(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@AfterMethod
	public void close() {
		tearDown();
	}
}
