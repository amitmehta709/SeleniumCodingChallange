package com.assignment.selenium.testvagrant.ecom.pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.assignment.selenium.testvagrant.lib.pages.PageObject;
import com.assignment.selenium.testvagrant.lib.utils.DriverUtils;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

public class HomePage extends PageObject {
	private ExtentTest testInfo;
	private String testName;
	public HomePage(WebDriver driver, String testName, ExtentTest extentTest ) {
		super(driver);
		this.testName = testName;
		this.testInfo = extentTest;
	}
	
	@FindBy(how = How.XPATH, using = "//a[@class='site-nav__link site-nav__link--main site-nav__link--active']/span")
	public WebElement homeBtn;
	
	@FindBy(how = How.XPATH, using = "//button[@class='btn--link site-header__icon site-header__search-toggle js-drawer-open-top']")
	public WebElement searchBtn;
	
	@FindBy(how = How.XPATH, using = "//input[@class='search-form__input search-bar__input']")
	public WebElement searchField;
	
	@FindBy(how = How.XPATH, using = "//button[@class='search-bar__submit search-form__submit']")
	public WebElement searchSubmitBtn;
	
	@FindBy(how = How.XPATH, using = "//ul[@class='grid grid--uniform grid--view-items']")
	public WebElement featuredCollectionGrid;
	
	public boolean verifyHomePage() throws IOException
	{
		DriverUtils.waitForPageLoad(getDriver());
		if(DriverUtils.checkElement(homeBtn))
		{
			testInfo.log(Status.INFO,"Reached Home Page", MediaEntityBuilder.createScreenCaptureFromPath(DriverUtils.captureScreenShots(getDriver(), testName)).build());
			return true;
		}
		return false;
	}
	
	public void searchItem(String item) throws IOException
	{
		DriverUtils.waitForElement(getDriver(), searchBtn);
		DriverUtils.click(getDriver(), searchBtn);
		testInfo.log(Status.INFO,"Clicked on Search Button", MediaEntityBuilder.createScreenCaptureFromPath(DriverUtils.captureScreenShots(getDriver(), testName)).build());
		
		DriverUtils.waitForElement(getDriver(), searchField);
		searchField.click();
		testInfo.log(Status.INFO,"Clicked in Search Field", MediaEntityBuilder.createScreenCaptureFromPath(DriverUtils.captureScreenShots(getDriver(), testName)).build());
		searchField.sendKeys(item);
		testInfo.log(Status.INFO,"Entered data in search Fiels", MediaEntityBuilder.createScreenCaptureFromPath(DriverUtils.captureScreenShots(getDriver(), testName)).build());
		
		DriverUtils.waitForElement(getDriver(), searchSubmitBtn);
		DriverUtils.click(getDriver(), searchSubmitBtn);
		testInfo.log(Status.INFO,"Clicked on Search Submit Button", MediaEntityBuilder.createScreenCaptureFromPath(DriverUtils.captureScreenShots(getDriver(), testName)).build());
		
	}
	
	public void clickProduct() throws IOException
	{
		DriverUtils.waitForPageLoad(getDriver());
		testInfo.log(Status.INFO,"Got Searched Products List", MediaEntityBuilder.createScreenCaptureFromPath(DriverUtils.captureScreenShots(getDriver(), testName)).build());
		
		List<WebElement> productList = getDriver().findElements(By.xpath("//div[@class='product-card product-card--list']/a"));
		
		WebElement product  = productList.get(0);
		
		product.click();
		testInfo.log(Status.INFO,"Clicked on Best Matched Product", MediaEntityBuilder.createScreenCaptureFromPath(DriverUtils.captureScreenShots(getDriver(), testName)).build());
		
	}
	
	public String addFeaturedCollectionProduct() throws IOException
	{
		DriverUtils.scrollDown(getDriver(), featuredCollectionGrid);
		testInfo.log(Status.INFO,"Scrolled Down to Featured Collection Product", MediaEntityBuilder.createScreenCaptureFromPath(DriverUtils.captureScreenShots(getDriver(), testName)).build());
		
		List<WebElement> featuredProductDescList= getDriver().findElements(By.xpath("//div[@class='grid-view-item product-card']/div[@class='h4 grid-view-item__title product-card__title']"));
		
		WebElement featuredproductDesc  = featuredProductDescList.get(0);
		
		String featuredProductName = featuredproductDesc.getText();
		
		List<WebElement> featuredProductList = getDriver().findElements(By.xpath("//a[@class='grid-view-item__link grid-view-item__image-container full-width-link']"));
		WebElement featuredproduct  = featuredProductList.get(0);
		featuredproduct.click();
		testInfo.log(Status.INFO,"Clicked on First Featured Product", MediaEntityBuilder.createScreenCaptureFromPath(DriverUtils.captureScreenShots(getDriver(), testName)).build());
		
		return featuredProductName;
	}

}
