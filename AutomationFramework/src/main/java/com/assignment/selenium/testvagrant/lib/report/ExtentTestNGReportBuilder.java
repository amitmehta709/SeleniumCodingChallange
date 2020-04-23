package com.assignment.selenium.testvagrant.lib.report;

import java.io.File;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentTestNGReportBuilder {

  	public static ExtentReports extent;
  	 
	@BeforeSuite(alwaysRun = true)
	public void beforeSuite() {
		 // start reporters
        //ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"\\AutomationReports.html"));
		ExtentHtmlReporter htmlReporter = createInstance(new File(System.getProperty("user.dir")+"\\AutomationReports.html"));
        // create ExtentReports and attach reporter(s)
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
	}
	
    
    public static ExtentHtmlReporter createInstance(File file) {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(file);
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setEncoding("utf-8");
        return htmlReporter;
    }
    
}

