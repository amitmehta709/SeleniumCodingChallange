package com.assignment.selenium.testvagrant.ecom.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.assignment.selenium.testvagrant.lib.pages.PageObject;
import com.assignment.selenium.testvagrant.lib.utils.DriverUtils;

public class CartPage extends PageObject {

	public CartPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(how = How.XPATH, using = "//h1[@class='cart-header__title']")
	public WebElement cartTitle;
	
	@FindBy(how = How.XPATH, using = "//div[@class='cart__qty']/input[@class='cart__qty-input']")
	public WebElement quantityField;
	
	@FindBy(how = How.XPATH, using = "//td[@class='cart__final-price text-right small--hide']/div/span")
	public WebElement priceLbl;

	@FindBy(how = How.XPATH, using = "//input[@name='checkout']")
	public WebElement checkoutBtn;
	
	public boolean verifyCartPage()
	{
		DriverUtils.waitForPageLoad(getDriver());
		if(DriverUtils.checkElement(cartTitle))
		{
			return true;
		}
		return false;
	}
	
	public void increaseQuantity(String count)
	{
		DriverUtils.waitForElement(getDriver(), quantityField);
		quantityField.clear();
		quantityField.click();
		quantityField.sendKeys(count);			
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
	
	public void checkoutCart()
	{
		DriverUtils.scrollDown(getDriver(), checkoutBtn);
		checkoutBtn.click();
	}
	
	

}
