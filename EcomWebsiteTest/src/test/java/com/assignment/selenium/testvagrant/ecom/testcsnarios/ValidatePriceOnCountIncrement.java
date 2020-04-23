package com.assignment.selenium.testvagrant.ecom.testcsnarios;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.assignment.selenium.testvagrant.ecom.pages.CartPage;
import com.assignment.selenium.testvagrant.ecom.pages.HomePage;
import com.assignment.selenium.testvagrant.ecom.pages.LoginPage;
import com.assignment.selenium.testvagrant.ecom.pages.ProductPage;
import com.assignment.selenium.testvagrant.ecom.templates.EcomTestTemplates;
import com.assignment.selenium.testvagrant.lib.report.ExtentTestNGReportBuilder;
import com.assignment.selenium.testvagrant.lib.utils.DriverUtils;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

public class ValidatePriceOnCountIncrement extends EcomTestTemplates {
	private ExtentTest testInfo;
	private String testName;
	@BeforeMethod
	public void setup(Method method) throws IOException {
		testName = "ValidateProductPrice";
		testInfo = ExtentTestNGReportBuilder.extent.createTest(testName);
		
		testInfo.log(Status.INFO,"Test Started");
		getDriver().get(getUrl());		
		testInfo.log(Status.INFO,"Reached Landing Page", MediaEntityBuilder.createScreenCaptureFromPath(DriverUtils.captureScreenShots(getDriver(), testName)).build());
	}
	
	@Test
	public void validatePrice() throws InterruptedException, IOException
	{
		testInfo.log(Status.INFO, "Performing Login");
		LoginPage loginPage = new LoginPage(getDriver(),testName, testInfo);
		loginPage.performLogin();
		
		
		testInfo.log(Status.INFO, "Searching and Clikcing Product");
		HomePage homePage = new HomePage(getDriver(),testName, testInfo);
		Assert.assertEquals(homePage.verifyHomePage(), true,"Failed to Perform Login");
		homePage.searchItem("RoundNeck Shirt");
		homePage.clickProduct();
		
		testInfo.log(Status.INFO, "Adding Product to Cart");
		ProductPage productPage = new ProductPage(getDriver(),testName, testInfo);
		Assert.assertEquals(productPage.verifyProduct("RoundNeck Shirt"),true,"Failed To click on Product");
		productPage.addToCart();
		productPage.viewCart();
		
		testInfo.log(Status.INFO, "Validating and Checking out from Cart");
		CartPage cartPage = new CartPage(getDriver(),testName, testInfo);
		Assert.assertEquals(cartPage.verifyCartPage(),true,"Failed to navigate to cart");
		double initialPrice = cartPage.getPrice();
		testInfo.log(Status.INFO, "Intial price of Product:"+initialPrice);
		testInfo.log(Status.INFO, "Increasing Product Quantity to 2");
		cartPage.increaseQuantity("2");
		double expectedPrice = initialPrice*2;
		testInfo.log(Status.INFO, "Expected New price of Product:"+expectedPrice);
		double incrementedPrice = cartPage.getPrice();
		testInfo.log(Status.INFO, "Incremented price of Product:"+incrementedPrice);
		Assert.assertEquals(incrementedPrice, expectedPrice, "Incremented Price didn't match with expected price");
		testInfo.log(Status.PASS, "Price Comparision Successful.Checking out from Cart");
		cartPage.checkoutCart();
	}
	
	@AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE)
        {
        	testInfo.log(Status.PASS,"The Test Methos named as :"+result.getName()+" is FAILED");
        	testInfo.log(Status.FAIL,"Test Failre :"+result.getThrowable());
        }
        else if(result.getStatus() == ITestResult.SKIP)
        {
           testInfo.log(Status.PASS,"The Test Methos named as :"+result.getName()+" is SKIPPED");
        }
        else if(result.getStatus() == ITestResult.SUCCESS)
        {
        	testInfo.log(Status.PASS,"The Test Methos named as :"+result.getName()+" is PASSED");
        }
        extent.flush();
    }
}
