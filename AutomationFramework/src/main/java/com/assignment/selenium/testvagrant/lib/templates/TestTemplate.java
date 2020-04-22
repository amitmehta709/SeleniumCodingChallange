package com.assignment.selenium.testvagrant.lib.templates;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;

public abstract class TestTemplate {

	private Map<String, String> testParameters;
	
	@BeforeClass(alwaysRun = true)
	public void setup(ITestContext context) {
		// Handle environment variable
		this.testParameters = context.getCurrentXmlTest().getAllParameters();

		if (this.testParameters == null) {
			this.testParameters = new HashMap<>();
		}

		// Override parameters through JVM
		Properties pros = System.getProperties();
		Iterator<Object> keys = pros.keySet().iterator();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			this.testParameters.put(key, pros.getProperty(key));
		}

	}
	
	/**
	 * @return the testParameters
	 */
	public Map<String, String> getTestParameters() {
		return testParameters;
	}
}
