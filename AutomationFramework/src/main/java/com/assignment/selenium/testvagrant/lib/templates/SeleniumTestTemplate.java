package com.assignment.selenium.testvagrant.lib.templates;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.assignment.selenium.testvagrant.lib.controllers.BrowserLauncher;
import com.assignment.selenium.testvagrant.lib.utils.TestParameters;

public abstract class SeleniumTestTemplate extends TestTemplate {

	private WebDriver driver;
	
	@BeforeClass(dependsOnMethods = "setup", alwaysRun=true)
	public void setupDriver(ITestContext context) {

		String browser = getTestParameters().get(TestParameters.BROWSER) == null ? TestParameters.CHROME
				: getTestParameters().get(TestParameters.BROWSER);

		driver = BrowserLauncher.launch(browser);
		
		if (driver == null)
		{
			throw new RuntimeException("Driver Not Provided");
		}		
	}

	@AfterClass(alwaysRun = true)
	public void teardown() {
		if (driver != null) {
			driver.quit();
		}
	}

	public WebDriver getDriver() {
		return this.driver;
	}
	
}
