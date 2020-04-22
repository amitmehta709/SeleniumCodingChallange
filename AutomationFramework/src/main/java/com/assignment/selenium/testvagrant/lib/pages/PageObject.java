package com.assignment.selenium.testvagrant.lib.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.assignment.selenium.testvagrant.lib.utils.AutomationProperties;

public abstract class PageObject {

	private final WebDriver driver;

	public PageObject(WebDriver driver) {
		this.driver = driver;
		init();
	}
	
	public void init()
	{
		int wait = Integer
				.parseInt(AutomationProperties.getProperty("tesvagrant.assignment.selenium.page.wait"));
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, wait), this);
	}
	
	/**
	 * @return the driver
	 */
	public WebDriver getDriver() {
		return driver;
	}
}
