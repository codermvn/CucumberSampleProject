package com.lh.test.framework.handlers;

import com.lh.test.framework.configuration.Configuration;
import com.lh.test.framework.exceptions.EndTestException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.logging.Logger;

public class BrowserHandler {

	static Logger LOG = Logger.getLogger(BrowserHandler.class.getName());
	private static WebDriver webDriver;

	public static WebDriver loadBrowser(String browser) {
		if ("chrome".equalsIgnoreCase(browser)) {
			return getChromeWebDriver();
		}

		if ("firefox".equalsIgnoreCase(browser)) {
			return getFireFoxDriver();
		}

		if ("ie".equalsIgnoreCase(browser) || "iexplore".equalsIgnoreCase(browser)
				|| "internet explorer".equalsIgnoreCase(browser)) {
			return getIEWebDriver();
		}

		throw new EndTestException("Driver for selected browser not defined.");
	}

	public static WebDriver loadRemoteBrowser(String browser, URL hubUrl) {

		if ("chrome".equalsIgnoreCase(browser)) {
			return new RemoteWebDriver(hubUrl, DesiredCapabilities.chrome());
		}

		if ("firefox".equalsIgnoreCase(browser)) {
			return new RemoteWebDriver(hubUrl, DesiredCapabilities.firefox());
		}

		if ("ie".equalsIgnoreCase(browser) || "iexplore".equalsIgnoreCase(browser)
				|| "internet explorer".equalsIgnoreCase(browser)) {
			DesiredCapabilities capabilities = new DesiredCapabilities().internetExplorer();
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			return new RemoteWebDriver(hubUrl, capabilities);
		}

		throw new EndTestException("Driver for selected browser not defined.");
	}

	/**
	 * create driver if not instantiated
	 * 
	 * @return WebDriver
	 */
	public static WebDriver getDriver() {

		try {
			webDriver.getTitle();
		} catch (WebDriverException e) {
			LOG.severe(e.getMessage());
			webDriver = getFireFoxDriver();
		}
		try {
			return webDriver;
		} finally {
			webDriver = null;
		}
	}

	/**
	 * Gets the current driver. Returns error if driver not instantiated earlier
	 * 
	 * @return WebDriver
	 */
	public static WebDriver getCurrentDriver() {
		try {
			webDriver.getTitle();
			try {
				return webDriver;
			} finally {
				webDriver = null;
			}
		} catch (WebDriverException e) {
			throw new EndTestException("Unable to getCurrentDriver()");
		}

	}

	private static WebDriver getChromeWebDriver() {

		if (webDriver != null && !(webDriver instanceof ChromeDriver)) {
			try {
				webDriver.quit();
			} catch (WebDriverException e) {
				// Ignore
			}
		}

		if (webDriver != null) {
			webDriver.getTitle();
		} else {
//add proxy desired capability if required. Check after implementation
			String chromeDriverPath = System.getProperty("user.dir")+Configuration
		            .getConfiguration().getWebdriverForChrome();
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);
			webDriver = new ChromeDriver();
			webDriver.manage().deleteAllCookies();
		}

		try {
			return webDriver;
		} finally {
			webDriver = null;
		}
	}

	private static WebDriver getIEWebDriver() {

		if (webDriver != null && !(webDriver instanceof InternetExplorerDriver)) {
			try {
				webDriver.quit();
			} catch (WebDriverException e) {
				// Ignore
			}
		}

		if (webDriver != null) {
			webDriver.getTitle();
		} else {

			System.setProperty("webdriver.ie.driver", Configuration.getConfiguration().getWebdriverForIE());
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			// capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION,
			// true);
			webDriver = new InternetExplorerDriver(capabilities);
			webDriver.manage().deleteAllCookies();
		}

		try {
			return webDriver;
		} finally {
			webDriver = null;
		}
	}

	private static WebDriver getFireFoxDriver() {

		// Check to see if firefox is the current driver quit if not
		if (webDriver != null && !(webDriver instanceof FirefoxDriver)) {
			try {
				webDriver.quit();
			} catch (WebDriverException e) {
				// Ignore
			}
		}

		if (webDriver != null) {
			webDriver.getTitle();
		} else {
			String geckoDriverPath = System.getProperty("user.dir")+Configuration
                    .getConfiguration().getWebdriverForFirefox();
            System.setProperty("webdriver.gecko.driver", geckoDriverPath);
            FirefoxProfile profile = new FirefoxProfile();
//            profile.setPreference("browser.download.panel.shown", false);
//            profile.setPreference("browser.helperApps.neverAsk.saveToDisk","application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/x-excel, application/x-msexcel, application/excel, application/vnd.ms-excel");
//            profile.setPreference("browser.download.folderList", 1); 
            System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"/dev/null");
            webDriver = new FirefoxDriver(profile);
           

			webDriver.manage().deleteAllCookies();
		}
		try {
			return webDriver;
		} finally {
			webDriver = null;
		}
	}

	public static void maximizeBrowser(WebDriver driver) {
		driver.manage().window().maximize();
	}
}
