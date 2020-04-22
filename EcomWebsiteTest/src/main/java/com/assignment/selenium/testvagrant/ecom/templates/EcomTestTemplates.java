package com.assignment.selenium.testvagrant.ecom.templates;

import org.testng.annotations.BeforeMethod;

import com.assignment.selenium.testvagrant.lib.templates.SeleniumTestTemplate;
import com.assignment.selenium.testvagrant.lib.utils.AutomationProperties;

public class EcomTestTemplates extends SeleniumTestTemplate {

	private String url;

	@BeforeMethod(alwaysRun = true)
	public void getInfo() {
		if (getDriver() == null) {
			//TestLog.log("Failed to launch browser!");
			return;
		}
		url = AutomationProperties.getProperty("tesvagrant.assignment.selenium.ecom.url");

	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
}
