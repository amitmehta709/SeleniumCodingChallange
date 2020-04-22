package com.assignment.selenium.testvagrant.ecom.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.assignment.selenium.testvagrant.lib.pages.PageObject;
import com.assignment.selenium.testvagrant.lib.utils.DriverUtils;

public class HomePage extends PageObject {

	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(how = How.XPATH, using = "//a[@class='site-nav__link site-nav__link--main site-nav__link--active']/span")
	public WebElement homeBtn;
	
	@FindBy(how = How.XPATH, using = "//button[@class='btn--link site-header__icon site-header__search-toggle js-drawer-open-top']")
	public WebElement searchBtn;
	
	@FindBy(how = How.XPATH, using = "//input[@class='search-form__input search-bar__input']")
	public WebElement searchField;
	
	@FindBy(how = How.XPATH, using = "//button[@class='search-bar__submit search-form__submit']")
	public WebElement searchSubmitBtn;
	
	public boolean verifyHomePage()
	{
		DriverUtils.waitForPageLoad(getDriver());
		if(DriverUtils.checkElement(homeBtn))
		{
			return true;
		}
		return false;
	}
	
	public void searchItem(String item)
	{
		DriverUtils.waitForElement(getDriver(), searchBtn);
		DriverUtils.click(getDriver(), searchBtn);
		
		DriverUtils.waitForElement(getDriver(), searchField);
		searchField.click();
		searchField.sendKeys(item);
		
		DriverUtils.waitForElement(getDriver(), searchSubmitBtn);
		DriverUtils.click(getDriver(), searchSubmitBtn);
		
	}
	
	public void clickProduct()
	{
		DriverUtils.waitForPageLoad(getDriver());
		
		List<WebElement> productList = getDriver().findElements(By.xpath("//div[@class='product-card product-card--list']/a"));
		
		WebElement product  = productList.get(0);
		
		product.click();
		
	}

}
