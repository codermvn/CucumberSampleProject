package com.lh.test.framework.application;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import com.lh.test.framework.configuration.Configuration;
import com.lh.test.framework.exceptions.EndTestException;
import com.lh.test.framework.handlers.BrowserHandler;
import com.lh.test.framework.handlers.EnvironmentHandler;
import com.lh.test.framework.handlers.ScenarioHandler;
import com.lh.test.framework.handlers.ScreenshotHandler;
import com.lh.test.framework.handlers.TagsHandler;
import com.lh.test.framework.handlers.TeardownHandler;
import com.lh.test.framework.handlers.TestNameHandler;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

/**
 * Basic setup performed before and after running a cucumber scenario
 *
 * 
 */
public class SetupTest {

	public static Logger LOG = Logger.getLogger(SetupTest.class.getName());
	private static WebDriver driver;
	private Configuration configuration = Configuration.getConfiguration();

	public static WebDriver getDriver() {
		return driver;
	}

	@Before
	public void initializeTest(Scenario scenario) {

		LOG.info("Starting Scenario Execution:   " + scenario.getName());

		ScenarioHandler.setScenario(scenario);
		List<String> tags = TagsHandler.getAllTags(scenario);
		String testCaseName = TestNameHandler.identifyTestCaseName(tags);
		TestNameHandler.setTestCaseName(testCaseName);

		// TODO: 10/23/2017 Initiate log4j configuration after setting up logging
		// ;setupLogging();//optional

		if ("true".equalsIgnoreCase(configuration.getUiTestingProperty())) {
			String browser = configuration.getBrowserProperty();
			String environment = configuration.getEnvironmentProperty();
			String remoteRun = configuration.getRemoteRunProperty();
			try {
				URL hub;
				if ("true".equalsIgnoreCase(remoteRun)) {
					driver = BrowserHandler.loadBrowser(browser);
				} else {
					hub = EnvironmentHandler.getHubUrl(configuration.getGridHubProperty(),
							configuration.getGridPortProperty());
					driver = BrowserHandler.loadRemoteBrowser(browser, hub);
				}
				BrowserHandler.maximizeBrowser(driver);
				driver.navigate().to(EnvironmentHandler.getURL(environment));
				BrowserHandler.maximizeBrowser(driver);
			} catch (MalformedURLException e) {
				// TODO: 11/3/2017 remove java util logger. Add log4j logger
				LOG.fatal("Exception thrown in loading the environment URL. " + e.getMessage());
				throw new EndTestException();
			} catch (WebDriverException e) {
				LOG.fatal("Exception thrown in trying to navigate to the environment " + e.getMessage());
				throw new EndTestException();
			}
		}
	}

	@After
	public void teardownTest(Scenario scenario) throws SQLException {
		if ("true".equalsIgnoreCase(configuration.getUiTestingProperty())) {
			ScreenshotHandler.takeScreenshotIfFail(scenario, driver);
			TeardownHandler.tearDown(driver);
			driver = null;
		}
		LOG.info("Exiting scenario   :" + scenario.getName());
	}

}
