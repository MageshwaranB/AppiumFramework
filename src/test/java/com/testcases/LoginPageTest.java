package com.testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.pages.LoginPage;
import com.pages.ProductPage;
import com.utility.ExcelUtils;

import junit.framework.Assert;

public class LoginPageTest extends BaseClass
{
	LoginPage loginFunction;
	ExcelUtils utils;
	String path=".\\src\\main\\java\\com\\testdata\\LoginData.xlsx";
	String sheetName="credentials";
	ProductPage products;
	@BeforeClass
	public void setup() {
		initialization();
		loginFunction=new LoginPage();
		products=new ProductPage();
	}
	
	@DataProvider
	public String[][] provideData()
	{
		utils=new ExcelUtils(path);
		String loginData[][]=utils.getCellDataValues(sheetName,path);
		return loginData;
	}
	
	@Test(dataProvider = "provideData")
	public void loginTest(String user, String pass, String scenarios) {
		String actualRes=loginFunction.logIntoApplication(user,pass,scenarios);
		Assert.assertEquals("Not A Valid User, Please check the credentials", actualRes);
		//Assert.assertEquals("Entered data is a valid one", actualRes);

	}
	
	@Test
	public void scrollToElement() {
		loginFunction.scrollingDownToElement();
		
	}
	
	@AfterClass
	public void close() {
		tearDown();
	}
}
