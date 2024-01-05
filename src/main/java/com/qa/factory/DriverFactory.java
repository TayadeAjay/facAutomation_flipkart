package com.qa.factory;

import java.io.File;

import org.aspectj.util.FileUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.exception.FrameworkException;

public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionsManager;
	public static String highlight;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public WebDriver initDriver(Properties prop) {
		optionsManager = new OptionsManager(prop);
		highlight = prop.getProperty("highlight").trim();
		String browserName = prop.getProperty("browser").toLowerCase().trim();
		System.out.println("Browser name is:" + browserName);
		if (browserName.equalsIgnoreCase("chrome")) {
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		} else if (browserName.trim().equalsIgnoreCase("firefox")) {
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		} else if (browserName.trim().equalsIgnoreCase("safari")) {
			tlDriver.set(new SafariDriver());
		} else if (browserName.trim().equalsIgnoreCase("edge")) {
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
		} else {
			System.out.println("Please pass the right browser" + browserName);
			throw new FrameworkException("NO BROWSER FOUND EXCEPTION...");
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}

	public synchronized static WebDriver getDriver() {
		return tlDriver.get();
	}

	public Properties initProp() {

		prop = new Properties();
		FileInputStream ip = null;
		String envName = System.getProperty("env");
		System.out.println("Running test cases on Env: " + envName);

		try {
			if (envName == null) {
				System.out.println("no env is passed....Running tests on QA env...");
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
			} else {
				switch (envName.toLowerCase().trim()) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;

				default:
					System.out.println("....Wrong env is passed....No need to run the test cases....");
					throw new FrameworkException("WRONG ENV IS PASSED...");

				}

			}
		} catch (FileNotFoundException e) {

		}

		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}

	public static String getScreenshot() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtil.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

}
