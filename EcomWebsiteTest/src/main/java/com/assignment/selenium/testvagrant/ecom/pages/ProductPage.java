package com.assignment.selenium.testvagrant.ecom.pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

import com.assignment.selenium.testvagrant.lib.pages.PageObject;
import com.assignment.selenium.testvagrant.lib.utils.DriverUtils;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

public class ProductPage extends PageObject {

	private ExtentTest testInfo;
	private String testName;
	public ProductPage(WebDriver driver, String testName, ExtentTest extentTest) {
		super(driver);
		this.testName = testName;
		this.testInfo = extentTest;
	}

	@FindBy(how = How.XPATH, using = "//h1[@class='product-single__title']")
	public WebElement productTitleLabel;
	
	@FindBy(how = How.XPATH, using = "//Select[@id='SingleOptionSelector-1']")
	public WebElement sizeDrpDown;
	
	@FindBy(how = How.XPATH, using = "//button[@aria-label='Add to cart']")
	public WebElement addCartButton;
	
	@FindBy(how = How.XPATH, using = "//a[@class='cart-popup__cta-link btn btn--secondary-accent']")
	public WebElement viewCartButton;
	
	@FindBy(how = How.XPATH, using = "//button[@class='cart-popup__dismiss-button text-link text-link--accent']")
	public WebElement cntShoppingBtn;
	
	public boolean verifyProduct(String product) throws IOException
	{
		DriverUtils.waitForPageLoad(getDriver());
		if(DriverUtils.checkElement(productTitleLabel))
		{
			String productTitle = productTitleLabel.getText();
			if(product.equalsIgnoreCase(productTitle))
			{
				testInfo.log(Status.INFO,"Product Label matched with Seacrched Product", MediaEntityBuilder.createScreenCaptureFromPath(DriverUtils.captureScreenShots(getDriver(), testName)).build());
				return true;
			}
		}
		return false;
	}
	
	public void addToCart() throws IOException
	{
		DriverUtils.waitForElement(getDriver(), addCartButton);
		addCartButton.click();
		testInfo.log(Status.INFO,"Clicked on Add to Cart", MediaEntityBuilder.createScreenCaptureFromPath(DriverUtils.captureScreenShots(getDriver(), testName)).build());
	}
	
	public void viewCart() throws IOException
	{
		DriverUtils.waitForPageLoad(getDriver());
		DriverUtils.waitForElement(getDriver(), viewCartButton);
		viewCartButton.click();
		testInfo.log(Status.INFO,"Clicked on View Cart", MediaEntityBuilder.createScreenCaptureFromPath(DriverUtils.captureScreenShots(getDriver(), testName)).build());
	}
	
	public void continueShopping() throws IOException
	{
		DriverUtils.waitForPageLoad(getDriver());
		DriverUtils.waitForElement(getDriver(), cntShoppingBtn);
		cntShoppingBtn.click();
		testInfo.log(Status.INFO,"Clicked on Continue Shooping", MediaEntityBuilder.createScreenCaptureFromPath(DriverUtils.captureScreenShots(getDriver(), testName)).build());
	}
	
	public void selectSize(String value) throws IOException
	{
		DriverUtils.waitForPageLoad(getDriver());
		DriverUtils.waitForElement(getDriver(), sizeDrpDown);
		
		Select drpDownElement = new Select(sizeDrpDown);
		drpDownElement.selectByValue(value);
		testInfo.log(Status.INFO,"Selected Different Size of Product", MediaEntityBuilder.createScreenCaptureFromPath(DriverUtils.captureScreenShots(getDriver(), testName)).build());
	}
}
