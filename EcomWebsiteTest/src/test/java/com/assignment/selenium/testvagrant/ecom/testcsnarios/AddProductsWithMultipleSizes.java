package com.assignment.selenium.testvagrant.ecom.testcsnarios;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.assignment.selenium.testvagrant.ecom.pages.HomePage;
import com.assignment.selenium.testvagrant.ecom.pages.LoginPage;
import com.assignment.selenium.testvagrant.ecom.pages.ProductPage;
import com.assignment.selenium.testvagrant.ecom.templates.EcomTestTemplates;
import com.assignment.selenium.testvagrant.lib.report.ExtentTestNGReportBuilder;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class AddProductsWithMultipleSizes extends EcomTestTemplates {
	private ExtentTest testInfo;
	@BeforeMethod
	public void setup(Method method) {
		getDriver().get(getUrl());
		String testName = method.getName();
		testInfo = ExtentTestNGReportBuilder.extent.createTest(testName);
	}
	
	@Test
	public void addMutipleProducts() throws InterruptedException
	{
		testInfo.log(Status.INFO, "Perforing Login");
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.performLogin();
		testInfo.log(Status.INFO, "Searching Product");
		HomePage homePage = new HomePage(getDriver());
		Assert.assertEquals(homePage.verifyHomePage(), true);
		homePage.searchItem("RoundNeck Shirt");
		homePage.clickProduct();
		
		ProductPage productPage = new ProductPage(getDriver());
		Assert.assertEquals(productPage.verifyProduct("RoundNeck Shirt"),true);
		productPage.addToCart();
		productPage.continueShopping();
		productPage.selectSize("XL");
		productPage.addToCart();
		productPage.viewCart();
		
		
	}
	@AfterMethod(alwaysRun = true)
    public synchronized void afterMethod(ITestResult result) {
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
