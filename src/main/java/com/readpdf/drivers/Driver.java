package com.readpdf.drivers;

import java.util.concurrent.TimeUnit;
import java.util.List;
import java.util.Map;
import java.util.Arrays;
import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class Driver {
	static WebDriver driverWeb;

	private Driver() {
		System.setProperty("webdriver.edge.driver", "src\\test\\resources\\drivers\\msedgedriver.exe");
		EdgeOptions edgeOptions = new EdgeOptions();
		boolean incognito = false;
		boolean headlesMode = false;
		if (headlesMode) {
			List<String> args = Arrays.asList("--window-size=1280,800","--headless");
            Map<String, Object> map = new HashMap<>();
            map.put("args", args);
            edgeOptions.setCapability("ms:edgeOptions", map);
		} else if (incognito) {
            List<String> args = Arrays.asList("--inPrivate");
            Map<String, Object> map = new HashMap<>();
            map.put("args", args);
            edgeOptions.setCapability("ms:edgeOptions", map);
		} 
		edgeOptions.setCapability("acceptInsecureCerts",true);
		
		driverWeb = new EdgeDriver(edgeOptions);
		driverWeb.manage().window().maximize();
		driverWeb.manage().deleteAllCookies();
		driverWeb.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
	}
	
	public static Driver web() {
		return new Driver();
	}

	public static WebDriver inThePage(String url) {
		driverWeb.get(url);
		return driverWeb;
	}
	
	public static WebDriver inThePageUrl(String url) {
		driverWeb.get(url);
		return driverWeb;
	}

	public static WebDriver inTheStage() {
		return driverWeb;
	}
	
	public static WebDriver getDriver() {
		return driverWeb;
	}
}
		
			