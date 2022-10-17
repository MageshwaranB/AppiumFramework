package com.testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.pages.LoginPage;
import com.pages.ProductPage;

import junit.framework.Assert;


public class ProductPageTest extends BaseClass{
	
	LoginPage loginFunction;
	ProductPage products;
	@BeforeClass
	public void setup() {
		initialization();
		loginFunction=new LoginPage();
		//products=loginFunction.simpleLogin(prop.getProperty("username"), prop.getProperty("password"));
		products=new ProductPage();
		
	}
	
	@Test
	public void validatingAllAvailableProductsTest() {
		loginFunction.simpleLogin(prop.getProperty("username"), prop.getProperty("password"));
		String actText=products.validatingTheExistenceOfAllProducts();
		Assert.assertEquals("All the elements are present and clickable", actText);
	}
	
	@Test
	public void validatingAllAvailableInVerticalListTest() {
		loginFunction.simpleLogin(prop.getProperty("username"), prop.getProperty("password"));
		String actText=products.verticalProductsView();
		Assert.assertEquals("All the elements are present and clickable", actText);
	}
	
	@AfterClass
	public void close() {
		tearDown();
	}
	
}
