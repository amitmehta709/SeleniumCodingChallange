package com.assignment.selenium.testvagrant.ecom.pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.assignment.selenium.testvagrant.lib.pages.PageObject;
import com.assignment.selenium.testvagrant.lib.utils.DriverUtils;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

public class CartPage extends PageObject {

	private ExtentTest testInfo;
	private String testName;
	public CartPage(WebDriver driver, String testName, ExtentTest extentTest ) {
		super(driver);
		this.testName = testName;
		this.testInfo = extentTest;
	}
	
	@FindBy(how = How.XPATH, using = "//h1[@class='cart-header__title']")
	public WebElement cartTitle;
	
	@FindBy(how = How.XPATH, using = "//div[@class='cart__qty']/input[@class='cart__qty-input']")
	public WebElement quantityField;
	
	@FindBy(how = How.XPATH, using = "//td[@class='cart__final-price text-right small--hide']/div/span")
	public WebElement priceLbl;

	@FindBy(how = How.XPATH, using = "//input[@name='checkout']")
	public WebElement checkoutBtn;
	
	public boolean verifyCartPage() throws IOException
	{
		DriverUtils.waitForPageLoad(getDriver());
		if(DriverUtils.checkElement(cartTitle))
		{
			testInfo.log(Status.INFO,"Verified Cart Page", MediaEntityBuilder.createScreenCaptureFromPath(DriverUtils.captureScreenShots(getDriver(), testName)).build());
			return true;
		}
		return false;
	}
	
	public void increaseQuantity(String count) throws IOException
	{
		DriverUtils.waitForElement(getDriver(), quantityField);
		quantityField.clear();
		quantityField.sendKeys(count);
		DriverUtils.sleep();
		testInfo.log(Status.INFO,"Entered New Quantity in Quantity Field", MediaEntityBuilder.createScreenCaptureFromPath(DriverUtils.captureScreenShots(getDriver(), testName)).build());
	}
	
	public double getPrice()
	{
		DriverUtils.sleep();
		DriverUtils.waitForElement(getDriver(), priceLbl);
		
		String price = priceLbl.getText();
		String priceNumber = price.substring(4).replaceAll(",", "");;
		
		double numericPrice = Double.valueOf(priceNumber);
		
		return numericPrice;
		
	}
	
	public void checkoutCart() throws IOException
	{
		DriverUtils.scrollDown(getDriver(), checkoutBtn);
		testInfo.log(Status.INFO,"Scrollled Down for Checkout Button", MediaEntityBuilder.createScreenCaptureFromPath(DriverUtils.captureScreenShots(getDriver(), testName)).build());
		
		checkoutBtn.click();
		testInfo.log(Status.INFO,"Clicked on Checkout Button", MediaEntityBuilder.createScreenCaptureFromPath(DriverUtils.captureScreenShots(getDriver(), testName)).build());
	}
	
	

}
