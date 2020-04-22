package com.assignment.selenium.testvagrant.ecom.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

import com.assignment.selenium.testvagrant.lib.pages.PageObject;
import com.assignment.selenium.testvagrant.lib.utils.DriverUtils;

public class ProductPage extends PageObject {

	public ProductPage(WebDriver driver) {
		super(driver);
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
	
	public boolean verifyProduct(String product)
	{
		DriverUtils.waitForPageLoad(getDriver());
		if(DriverUtils.checkElement(productTitleLabel))
		{
			String productTitle = productTitleLabel.getText();
			if(product.equalsIgnoreCase(productTitle))
			{
				return true;
			}
		}
		return false;
	}
	
	public void addToCart()
	{
		DriverUtils.waitForElement(getDriver(), addCartButton);
		addCartButton.click();
	}
	
	public void viewCart()
	{
		DriverUtils.waitForPageLoad(getDriver());
		DriverUtils.waitForElement(getDriver(), viewCartButton);
		viewCartButton.click();
	}
	
	public void continueShopping()
	{
		DriverUtils.waitForPageLoad(getDriver());
		DriverUtils.waitForElement(getDriver(), cntShoppingBtn);
		cntShoppingBtn.click();
	}
	
	public void selectSize(String value)
	{
		DriverUtils.waitForPageLoad(getDriver());
		DriverUtils.waitForElement(getDriver(), sizeDrpDown);
		
		Select drpDownElement = new Select(sizeDrpDown);
		drpDownElement.selectByValue(value);
	}
}
