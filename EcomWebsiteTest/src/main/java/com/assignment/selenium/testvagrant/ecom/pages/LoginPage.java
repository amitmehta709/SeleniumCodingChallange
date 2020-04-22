package com.assignment.selenium.testvagrant.ecom.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.assignment.selenium.testvagrant.lib.pages.PageObject;
import com.assignment.selenium.testvagrant.lib.utils.DriverUtils;

public class LoginPage extends PageObject {

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(how = How.XPATH, using = "//div[@class='password-login']/a")
	public WebElement enterPasswordBtn;
	
	@FindBy(how = How.CSS, using = "input#Password")
	public WebElement passwordField;
	
	@FindBy(how = How.XPATH, using = "//button[@class='btn btn--narrow']")
	public WebElement enterBtn;
	
	@FindBy(how = How.XPATH, using = "//a[@class='site-nav__link site-nav__link--main site-nav__link--active']/span")
	public WebElement homeBtn;

	public boolean performLogin()
	{
		DriverUtils.waitForPageLoad(getDriver());
		
		if(DriverUtils.checkElement(enterPasswordBtn))
		{
			System.out.println("Logging in");
			enterPasswordBtn.click();
			
			DriverUtils.waitForElement(getDriver(), passwordField);
			passwordField.sendKeys("idgad");
			
			DriverUtils.waitForElement(getDriver(), enterBtn);
			DriverUtils.click(getDriver(), enterBtn);
			
			return true;
		}
		else if(DriverUtils.checkElement(homeBtn))
		{
			System.out.println("Already Logged in");
			return true;
		}
		return false;
	}
}
