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

public class LoginPage extends PageObject {

	private String testName;
	private ExtentTest testInfo;
	public LoginPage(WebDriver driver, String testName, ExtentTest extentTest ) {
		super(driver);
		this.testName =testName;
		this.testInfo = extentTest;
	}
	
	@FindBy(how = How.XPATH, using = "//div[@class='password-login']/a")
	public WebElement enterPasswordBtn;
	
	@FindBy(how = How.CSS, using = "input#Password")
	public WebElement passwordField;
	
	@FindBy(how = How.XPATH, using = "//button[@class='btn btn--narrow']")
	public WebElement enterBtn;
	
	@FindBy(how = How.XPATH, using = "//a[@class='site-nav__link site-nav__link--main site-nav__link--active']/span")
	public WebElement homeBtn;

	public boolean performLogin() throws IOException
	{
		DriverUtils.waitForPageLoad(getDriver());
		
		if(DriverUtils.checkElement(enterPasswordBtn))
		{
			enterPasswordBtn.click();
			testInfo.log(Status.INFO,"Clicked on Enter Password Button", MediaEntityBuilder.createScreenCaptureFromPath(DriverUtils.captureScreenShots(getDriver(), testName)).build());
			
			DriverUtils.waitForElement(getDriver(), passwordField);
			passwordField.sendKeys("idgad");
			testInfo.log(Status.INFO,"Entered Password", MediaEntityBuilder.createScreenCaptureFromPath(DriverUtils.captureScreenShots(getDriver(), testName)).build());
			
			DriverUtils.waitForElement(getDriver(), enterBtn);
			DriverUtils.click(getDriver(), enterBtn);
			testInfo.log(Status.INFO,"Clicked on Enter Button", MediaEntityBuilder.createScreenCaptureFromPath(DriverUtils.captureScreenShots(getDriver(), testName)).build());
			
			return true;
		}
		else if(DriverUtils.checkElement(homeBtn))
		{
			testInfo.log(Status.INFO,"Already Logged In", MediaEntityBuilder.createScreenCaptureFromPath(DriverUtils.captureScreenShots(getDriver(), testName)).build());
			return true;
		}
		return false;
	}
}
