package com.assignment.selenium.testvagrant.lib.utils;

import java.io.IOException;
import java.util.Properties;

public class AutomationProperties {

	private static Properties p = null;

	private synchronized static void init() {
		try {
			p = new Properties();
			p.load(AutomationProperties.class.getClassLoader().getResourceAsStream("init.properties"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static String getProperty(String key) {
		if (p == null) {
			init();
		}

		return p.getProperty(key);

	}
}
