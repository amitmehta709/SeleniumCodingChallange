package com.assignment.selenium.testvagrant.lib.utils;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverUtils {

	private static final long SLEEP = 5000;
	
	private static long getWait() {
		return Long.parseLong(AutomationProperties.getProperty("tesvagrant.assignment.selenium.page.wait"));
	}
	public static void scrollDown(WebDriver driver, WebElement element) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;

		jse.executeScript("arguments[0].scrollIntoView();", element);
	}
	
	public static void click(WebDriver driver, WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	public static void sleep(long sleep) {
		try {
			Thread.sleep(sleep);
		} catch (InterruptedException e) {
			// Ignore
		}
	}

	public static void sleep() {
		sleep(SLEEP);
	}
	
	public static boolean checkElement(WebDriver driver, WebElement element) {
		long wait = getWait();

		return checkElement(driver, element, wait);
	}
	
	public static boolean checkElement(WebElement element)
	{
		try 
		{
			element.isDisplayed();
			return true;
		}
		catch (NoSuchElementException e)
		{
			return false;
		}
	}
	
	public static void waitForElement(WebDriver driver, WebElement element) {
		long wait = getWait();

		waitForElement(driver, element, wait);
	}
	
	public static boolean checkElement(WebDriver driver, WebElement element, long wait) {
		try {
			waitForElement(driver, element, wait);
			return true;
		} catch (TimeoutException e) {
			return false;
		}
	}
	
	public static void waitForElement(WebDriver driver, WebElement element, long wait) {
		new WebDriverWait(driver, wait, 1000).until(ExpectedConditions.and(ExpectedConditions.visibilityOf(element),
				ExpectedConditions.elementToBeClickable(element)));
	}
	
	public static void waitForPageLoad(WebDriver driver) {
		long wait = getWait();

		waitForPageLoad(driver, wait);
	}
	
	public static void waitForPageLoad(WebDriver driver, long wait) {

		new WebDriverWait(driver, wait).until(webDriver -> ((JavascriptExecutor) webDriver)
				.executeScript("return document.readyState").equals("complete"));
	}
	
	public static String captureScreenShots(WebDriver driver, String scenario) {
		try {
			File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			// Setting file name
			String folderPath = System.getProperty("user.dir");
			
			String fileName = scenario
					+ TestParameters.UNDERSCORE 
					+ System.currentTimeMillis()
					+ ".jpg";
			String filePath = folderPath 
					+ File.separator
					+ TestParameters.Screenshots 
					+ File.separator
					+ fileName;

			// copy screenshot file into screenshot folder.
			FileUtils.copyFile(f, new File(filePath));
			return filePath;
			
		} catch (Exception e) {
			throw new RuntimeException("Failed to Capture Screenshot (scenario:"
					+ scenario + ")due to " + e);
		}
	}
}
