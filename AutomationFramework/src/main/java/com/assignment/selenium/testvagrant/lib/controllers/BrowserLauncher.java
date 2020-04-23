package com.assignment.selenium.testvagrant.lib.controllers;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.remote.CapabilityType;

import com.assignment.selenium.testvagrant.lib.utils.AutomationProperties;
import com.assignment.selenium.testvagrant.lib.utils.TestParameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserLauncher {

	public static WebDriver launch(String browser) {
		WebDriver driver;
		switch (browser) {
		case TestParameters.FIREFOX:
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver(constructFirefoxOptions());

			break;
		default:
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(constructChromeOptions());

			break;
		}

		// maximize the window
		driver.manage().window().maximize();
		// add implicit wait
		driver.manage().timeouts().implicitlyWait(
				Long.parseLong(AutomationProperties.getProperty("tesvagrant.assignment.selenium.page.wait.implict")),
				TimeUnit.SECONDS);
		// clean cookies
		driver.manage().deleteAllCookies();

		return driver;

	}
	
	private static ChromeOptions constructChromeOptions()
	{ 
		ChromeOptions options = new ChromeOptions();
		options.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, TestParameters.TRUE);
		options.addArguments("--disable-web-security");
        options.addArguments("--allow-running-insecure-content");
		options.addArguments("no-sandbox");
		//options.addArguments("headless");
		return options;
		
	}
	
	private static FirefoxOptions constructFirefoxOptions()
	{
		FirefoxOptions options = new FirefoxOptions();
		ProfilesIni profile = new ProfilesIni();
		FirefoxProfile fxProfile = profile.getProfile("default");
		options.setProfile(fxProfile);
		options.addArguments("--disable-web-security");
        options.addArguments("--allow-running-insecure-content");
		options.addArguments("no-sandbox");
		options.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, TestParameters.TRUE);
	
		return options;
	}
	
}
