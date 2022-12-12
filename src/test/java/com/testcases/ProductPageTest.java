package com.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.pages.AboutSwagLabsPage;
import com.pages.CheckOutPage;
import com.pages.DrawingPage;
import com.pages.GeoLocationPage;
import com.pages.LoginPage;
import com.pages.ProductPage;
import com.pages.WebViewPage;
import com.utility.RandomData;

import junit.framework.Assert;


public class ProductPageTest extends BaseClass{
	
	LoginPage loginFunction;
	ProductPage products;
	@BeforeMethod
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
	
	@Test
	public void filterOutProductsPriceTest() {
		loginFunction.simpleLogin(prop.getProperty("username"), prop.getProperty("password"));
		int actValue=products.filterByPrice();
		System.out.println(actValue);
	}
	
	@Test
	public void filterOutProductsNameTest() {
		loginFunction.simpleLogin(prop.getProperty("username"), prop.getProperty("password"));
		String actValue=products.filterByName();
		System.out.println(actValue);
		Assert.assertEquals("Both the named filters are working as expected", actValue);
	}
	
	@Test
	public void addingProductsToCartTest() {
		String confirmationText=loginFunction.loginMethodChaining(prop.getProperty("username"), prop.getProperty("password"))
		.navigateToProductPage(new ProductPage())
		.addToCart().navigateToCheckoutAndPaymentPage(new CheckOutPage())
		.checkoutProcess(new RandomData());
		System.out.println(confirmationText);
		Assert.assertEquals("\"FINISH\"\r\n"
				+ "THANK YOU FOR YOU ORDER", confirmationText);
	}
	
	@Test
	public void webViewFunctionalityTest() {
		String confirmationText=loginFunction.loginMethodChaining(prop.getProperty("username"), prop.getProperty("password"))
											.navigateToProductPage(new ProductPage())
											.webViewFunctionality()
											.navigateToWebViewPage(new WebViewPage())
											.navigateToAWebsite(prop.getProperty("url"));
		System.out.println(confirmationText);
		Assert.assertEquals("Google", confirmationText);
	}
	
	@Test
	public void geoLocationFunctionalityTest() {
		String confirmationText=loginFunction.loginMethodChaining(prop.getProperty("username"), prop.getProperty("password"))
				.navigateToProductPage(new ProductPage())
				.geoLocationFunctionality()
				.navigateToGeoLocationPage(new GeoLocationPage())
				.geoLocationLatAndLong();
		System.out.println(confirmationText);
		Assert.assertEquals("Latitude and longtitude values are generated", confirmationText);
	}
	
	@Test
	public void drawingFunctionalityTest() {
		String confirmationText=loginFunction.loginMethodChaining(prop.getProperty("username"), prop.getProperty("password"))
		.navigateToProductPage(new ProductPage())
		.drawingFunctionality()
		.navigateToDrawingPage(new DrawingPage())
		.drawingSomethingInTheBoard();
		Assert.assertEquals("The image is erased", confirmationText);
	}
	
	@Test
	public void aboutSwagLabsFunctionalityTest() {
		boolean textPresentOrNot=loginFunction.loginMethodChaining(prop.getProperty("username"), prop.getProperty("password"))
		.navigateToProductPage(new ProductPage())
		.aboutSwagLabsFunctionality()
		.navigateToAboutSwagLabsPage(new AboutSwagLabsPage())
		.swagLabsParentPage();
		System.out.println(textPresentOrNot);
		Assert.assertEquals(true, textPresentOrNot);
	}
	
	@Test
	public void resetAppFunctionalityTest() {
		boolean resetAppStatus=loginFunction.loginMethodChaining(prop.getProperty("username"), prop.getProperty("password"))
		.navigateToProductPage(new ProductPage())
		.resetAppStatus();
		System.out.println(resetAppStatus);
		Assert.assertEquals(true, resetAppStatus);
	}
	
	@AfterMethod
	public void close() {
		tearDown();
	}
	
}
