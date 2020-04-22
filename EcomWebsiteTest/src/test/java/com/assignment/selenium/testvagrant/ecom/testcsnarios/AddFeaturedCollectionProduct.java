package com.assignment.selenium.testvagrant.ecom.testcsnarios;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.assignment.selenium.testvagrant.ecom.pages.HomePage;
import com.assignment.selenium.testvagrant.ecom.pages.LoginPage;
import com.assignment.selenium.testvagrant.ecom.pages.ProductPage;
import com.assignment.selenium.testvagrant.ecom.templates.EcomTestTemplates;

public class AddFeaturedCollectionProduct extends EcomTestTemplates {

	@BeforeMethod
	public void setup() {
		getDriver().get(getUrl());
	}
	
	@Test
	public void addFeaturedProduct() throws InterruptedException
	{
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.performLogin();
		
		HomePage homePage = new HomePage(getDriver());
		Assert.assertEquals(homePage.verifyHomePage(), true);
		String productTitle = homePage.addFeaturedCollectionProduct();
		
		ProductPage productPage = new ProductPage(getDriver());
		Assert.assertEquals(productPage.verifyProduct(productTitle),true);
		productPage.addToCart();
		productPage.viewCart();
		
		Thread.sleep(10000);
		
	}
}
