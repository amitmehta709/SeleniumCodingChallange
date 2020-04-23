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

public class AddFeaturedCollectionProduct extends EcomTestTemplates {

	private ExtentTest testInfo;
	String testName;
	@BeforeMethod
	public void setup(Method method) throws IOException {
		testName = "AddFeaturedCollectionProduct";
		testInfo = ExtentTestNGReportBuilder.extent.createTest(testName);
		
		testInfo.log(Status.INFO,"Test Started");
		getDriver().get(getUrl());		
		testInfo.log(Status.INFO,"Reached Landing Page", MediaEntityBuilder.createScreenCaptureFromPath(DriverUtils.captureScreenShots(getDriver(), testName)).build());
	}
	
	@Test
	public void addFeaturedProduct() throws IOException
	{
		testInfo.log(Status.INFO, "Perforing Login");
		LoginPage loginPage = new LoginPage(getDriver(),testName, testInfo);
		loginPage.performLogin();
		
		testInfo.log(Status.INFO, "Searching and Clicking on Product from Featured Collection");		
		HomePage homePage = new HomePage(getDriver(),testName, testInfo);
		Assert.assertEquals(homePage.verifyHomePage(), true,"Failed to Perform Login");
		String productTitle = homePage.addFeaturedCollectionProduct();
		
		testInfo.log(Status.INFO, "Adding Product to Cart");
		ProductPage productPage = new ProductPage(getDriver(),testName, testInfo);
		Assert.assertEquals(productPage.verifyProduct(productTitle),true,"Failed To click on Product");
		productPage.addToCart();
		productPage.viewCart();	
		
		testInfo.log(Status.INFO, "Validating and Checking out from Cart");
		CartPage cartPage = new CartPage(getDriver(),testName, testInfo);
		Assert.assertEquals(cartPage.verifyCartPage(),true,"Failed to navigate to cart");
		
		testInfo.log(Status.PASS, "Successfully Added Featured Product.Checking out from Cart");
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
