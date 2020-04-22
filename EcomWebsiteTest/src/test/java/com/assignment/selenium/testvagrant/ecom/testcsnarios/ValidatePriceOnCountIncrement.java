package com.assignment.selenium.testvagrant.ecom.testcsnarios;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.assignment.selenium.testvagrant.ecom.pages.CartPage;
import com.assignment.selenium.testvagrant.ecom.pages.HomePage;
import com.assignment.selenium.testvagrant.ecom.pages.LoginPage;
import com.assignment.selenium.testvagrant.ecom.pages.ProductPage;
import com.assignment.selenium.testvagrant.ecom.templates.EcomTestTemplates;

public class ValidatePriceOnCountIncrement extends EcomTestTemplates {
	
	@BeforeMethod
	public void setup() {
		getDriver().get(getUrl());
	}
	
	@Test
	public void validatePrice() throws InterruptedException
	{
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.performLogin();
		
		HomePage homePage = new HomePage(getDriver());
		Assert.assertEquals(homePage.verifyHomePage(), true);
		homePage.searchItem("RoundNeck Shirt");
		homePage.clickProduct();
		
		ProductPage productPage = new ProductPage(getDriver());
		Assert.assertEquals(productPage.verifyProduct("RoundNeck Shirt"),true);
		productPage.addToCart();
		productPage.viewCart();
		
		CartPage cartPage = new CartPage(getDriver());
		Assert.assertEquals(cartPage.verifyCartPage(),true);
		double initialPrice = cartPage.getPrice();
		System.out.println(initialPrice);
		cartPage.increaseQuantity("2");
		double incrementedPrice = cartPage.getPrice();
		System.out.println(incrementedPrice);
	}
	
	@AfterMethod
	public void signout()
	{}
}
