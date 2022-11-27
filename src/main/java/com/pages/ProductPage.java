package com.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.BaseClass;
import com.utility.Gestures;

import io.appium.java_client.MobileElement;
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
	
	@AndroidFindBy(accessibility = "test-Modal Selector Button")
	AndroidElement filterButton;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'Name')]")
	List<AndroidElement> namesFilter;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'Price')]")
	List<AndroidElement> priceFilter;
	
	@AndroidFindBy(xpath = "(//android.widget.TextView[@content-desc=\"test-Price\"])")
	List<AndroidElement> productPrice;
	
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
	
	
	public int filterByPrice() {
		int value=0;
		for(int i=0;i<2;i++) {
			filterButton.click();
		try {
			priceFilter.get(i).click();
			switch (i) {
			case 0:
				System.out.println("Price ranging from low to high");
				System.out.println();
				value=filteringProductsByPrice(7.99,i);
				System.out.println("----------------");
				continue;
			case 1:
				System.out.println("Price ranging from high to low");
				System.out.println();
				Gestures.scrollUpAndDown("$49.99");
				value=filteringProductsByPrice(49.99,i);
				break;
			}
			//break;
		}
		catch(Exception e) {
			priceFilter= driver.findElements(By.xpath("//android.widget.TextView[contains(@text, 'Price')]"))	;
			}
	}
		return value;
	}


	private int filteringProductsByPrice(Double thresholdLimit,int i) {
		int count=0, value=0;
		try {
		List<AndroidElement> lowerPricedProducts=driver.findElements(By.xpath("//android.widget.ScrollView[@content-desc=\"test-PRODUCTS\"]/android.view.ViewGroup/android.view.ViewGroup[1]"));	
		for(AndroidElement allProducts:lowerPricedProducts) {
			List<MobileElement> allProductsPrice=allProducts.findElementsByXPath("(//android.widget.TextView[@content-desc=\"test-Price\"])");
			System.out.println(allProductsPrice.size());
			value=lowToHighFilter(allProductsPrice, thresholdLimit,i);
			System.out.println("Value "+value);
			Gestures.scrollUsingDimension();
			value=value + lowToHighFilter(allProductsPrice, thresholdLimit,i);
			}		
		}
		catch(StaleElementReferenceException e) {
			List<AndroidElement> lowerPricedProducts=driver.findElements(By.xpath("//android.widget.ScrollView[@content-desc=\"test-PRODUCTS\"]/android.view.ViewGroup/android.view.ViewGroup[1]"));
			for(AndroidElement allProducts:lowerPricedProducts) {
				List<MobileElement> allProductsPrice=allProducts.findElementsByXPath("(//android.widget.TextView[@content-desc=\"test-Price\"])");
				value=value + lowToHighFilter(allProductsPrice, thresholdLimit,i);
				System.out.println("Value "+value);
				Gestures.scrollUsingDimension();
				try {
				value=value + lowToHighFilter(allProductsPrice, thresholdLimit,i);
				}
				catch(Exception e1) {
					lowerPricedProducts=driver.findElements(By.xpath("//android.widget.ScrollView[@content-desc=\"test-PRODUCTS\"]/android.view.ViewGroup/android.view.ViewGroup[1]"));
					for(AndroidElement allProducts1:lowerPricedProducts) {
						List<MobileElement> allProductsPrice1=allProducts1.findElementsByXPath("(//android.widget.TextView[@content-desc=\"test-Price\"])");
						value=value + lowToHighFilter(allProductsPrice1, thresholdLimit,i);
						System.out.println("Value "+value);
					}
				System.out.println("Value "+value);
				count=count+value;
				System.out.println("count "+count);
				}
			}
		}
		return count;
	}
	
	public Double splitTheString(String priceText) {
		String[] splitValues=priceText.split("\\$");
		return Double.parseDouble(splitValues[1]);
	}
	
	public int lowToHighFilter(List<MobileElement> allProductsPrice, Double threshold, int choice) {
		int count=0;
		for(MobileElement productPrice:allProductsPrice) {
			System.out.println(productPrice.getText());
			Double productValue=splitTheString(productPrice.getText());
			switch(choice) {
			case 0:
				if(productValue>=threshold)
					count++;
				break;
			case 1: 
				if(threshold>=productValue)
					count++;
				break;
			}
		}
		return count;
	}
	
	public List<String> getProductsText(int choice) {
		List<String> availableProductTitles=new ArrayList<String>();
		List<AndroidElement> allProducts=driver.findElementsByXPath("(//android.widget.TextView[@content-desc=\"test-Item title\"])");
		productsLoop(allProducts, availableProductTitles);
		Gestures.scrollUsingDimension();
		allProducts=driver.findElementsByXPath("(//android.widget.TextView[@content-desc=\"test-Item title\"])");
		productsLoop(allProducts, availableProductTitles);
		if(choice==0) {
			availableProductTitles.forEach(System.out::println);
			return availableProductTitles;
		}
		else {
			Collections.sort(availableProductTitles, Collections.reverseOrder());
			availableProductTitles.forEach(System.out::println);
			return availableProductTitles;
		}
		
	}
	
	public void productsLoop(List<AndroidElement> allProducts, List<String> availableProductTitles) {
		for(AndroidElement individualProduct:allProducts)
		{
			availableProductTitles.add(individualProduct.getText());
		}
	}
	
	List<String> expectedAtoZProducts=new ArrayList<>();
	List<String> expectedZtoAProducts=new ArrayList<>();
	public String filterByName() {
		int value=0;
		for(int i=0;i<2;i++) {
			if(i==0) {
				expectedAtoZProducts=getProductsText(i);
				Gestures.scrollUpAndDown("Sauce Labs Backpack");
			}
			else {
				Gestures.scrollUpAndDown("Sauce Labs Backpack");
				expectedZtoAProducts=getProductsText(i);
				
			}
			filterButton.click();
		try {
			namesFilter.get(i).click();
			switch (i) {
			case 0:
				System.out.println("Name from A to Z");
				System.out.println();
				value=filteringProductsByNames("Sauce Labs Backpack",i);
				ascendingProducts.forEach(System.out::println);
				System.out.println("----------------");
				continue;
			case 1:
				System.out.println("Name from Z to A");
				System.out.println();
				Gestures.scrollUpAndDown("Test.allTheThings() T-Shirt (Red)");
				value=filteringProductsByNames("Test.allTheThings() T-Shirt (Red)",i);
				descendingProducts.forEach(System.out::println);
				break;
			}
			
		}
		catch(Exception e) {
			namesFilter=driver.findElements(By.xpath("//android.widget.TextView[contains(@text, 'Name')]"));
			}
	}
		if(expectedAtoZProducts.equals(ascendingProducts) && expectedZtoAProducts.equals(descendingProducts)) {
			System.out.println("Ascending list");
			ascendingProducts.forEach(x->System.out.println(x));
			System.out.println("ExpectedAtoZProducts");
			expectedAtoZProducts.forEach(System.out::println);
			System.out.println("Descending list");
			descendingProducts.forEach(x->System.out.println(x));
			System.out.println("ExpectedZtoAProducts");
			expectedZtoAProducts.forEach(System.out::println);

			return "Both the named filters are working as expected";
		}
		else {
			return "Validation failed check the code";
		}
		
	}
	List<String> filteredProductList=new ArrayList<>();
	public int filteringProductsByNames(String productName, int i) {
		int count=0;	
		try {
		List<AndroidElement> lowerPricedProducts=driver.findElements(By.xpath("//android.widget.ScrollView[@content-desc=\"test-PRODUCTS\"]/android.view.ViewGroup/android.view.ViewGroup[1]"));	
		for(AndroidElement allProducts:lowerPricedProducts) {
			List<MobileElement> allProductsName=allProducts.findElementsByXPath("(//android.widget.TextView[@content-desc=\"test-Item title\"])");
			System.out.println(allProductsName.size());
			Thread.sleep(4000);
			addElementsToAList(alphabeticalFilter(allProductsName, productName,i),i);
			//System.out.println("Value "+value);
			
			Gestures.scrollUsingDimension();
			Thread.sleep(3000);
			addElementsToAList(alphabeticalFilter(allProductsName, productName,i),i);
			}		
		}
		catch(Exception e) {
			List<AndroidElement> lowerPricedProducts=driver.findElements(By.xpath("//android.widget.ScrollView[@content-desc=\"test-PRODUCTS\"]/android.view.ViewGroup/android.view.ViewGroup[1]"));
			for(AndroidElement allProducts:lowerPricedProducts) {
				List<MobileElement> allProductsName=allProducts.findElementsByXPath("(//android.widget.TextView[@content-desc=\"test-Item title\"])");
				addElementsToAList(alphabeticalFilter(allProductsName, productName,i),i);
				//System.out.println("Value "+value);
				Gestures.scrollUsingDimension();
				
				try {
					Thread.sleep(3000);
					addElementsToAList(alphabeticalFilter(allProductsName, productName,i),i);
				}
				catch(Exception e1) {
					List<AndroidElement> lowerPricedProducts1=driver.findElements(By.xpath("//android.widget.ScrollView[@content-desc=\"test-PRODUCTS\"]/android.view.ViewGroup/android.view.ViewGroup[1]"));
					for(AndroidElement allProducts1:lowerPricedProducts1) {
						List<MobileElement> allProductsName1=allProducts1.findElementsByXPath("(//android.widget.TextView[@content-desc=\"test-Item title\"])");
						addElementsToAList(alphabeticalFilter(allProductsName1, productName,i),i);

				}
				}
			}
		}
		return count;
	}

	List<String> ascendingProducts=new ArrayList<>();
	List<String> descendingProducts=new ArrayList<>();
	private List<String> alphabeticalFilter(List<MobileElement> allProductsName, String productName, int choice) {
		int count=0;
		for(MobileElement individualProductName:allProductsName) {
			System.out.println(individualProductName.getText());
			String singleProduct=individualProductName.getText();
			//Double productValue=splitTheString(productPrice.getText());
			switch(choice) {
			case 0:
//				if(productValue>=threshold)
				if(singleProduct.compareToIgnoreCase(productName)>=0)
					ascendingProducts.add(singleProduct);
					ascendingProducts.forEach(System.out::println);
					System.out.println(ascendingProducts.size());
					count++;
				break;
			case 1: 
				//if(threshold>=productValue)
				if(singleProduct.compareToIgnoreCase(productName)<=0)
					descendingProducts.add(singleProduct);
					descendingProducts.forEach(System.out::println);	
					count++;
				break;
			}
		}
		if(choice==0) {
			return ascendingProducts;
		}
		else {
			return descendingProducts;
		}
		
	}
	public void addElementsToAList(List<String> mainProductLine, int choice) {
		if(choice==0) {
			for(String product:mainProductLine) {
				filteredProductList.add(product);
			}
		}
		else {
			for(String product:mainProductLine) {
				filteredProductList.add(product);
			}
		}
	}
}
