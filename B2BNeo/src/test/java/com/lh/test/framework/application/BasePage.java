package com.lh.test.framework.application;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

import org.hamcrest.core.IsInstanceOf;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.lh.test.framework.annotations.IdentifyBy;
import com.lh.test.framework.application.helpers.BaseOperations;
import com.lh.test.framework.application.helpers.ExpectedConditionExtension;
import com.lh.test.framework.application.helpers.LocatorTypes;
import com.lh.test.framework.exceptions.EndTestException;

/**
 * Parent class for all pages Move all common page level functions here
 *
 * 
 */
public abstract class BasePage extends BaseOperations {

	private static final int DEFAULT_LOAD_TIMEOUT_MSEC = 180000;
	private static final int DEFAULT_LOAD_TIMEOUT_SEC = 180;
	public int sleep = 1000;

	public void waitForPage() {
		waitForPage(DEFAULT_LOAD_TIMEOUT_MSEC);
	}

	private By getIdentifierFromAnnotation() {
		IdentifyBy annotation = this.getClass().getAnnotation(IdentifyBy.class);
		return LocatorTypes.getLocator(annotation.locator(), annotation.identifier());
	}

	private String getIdentifierStringFromAnnotation() {
		IdentifyBy annotation = this.getClass().getAnnotation(IdentifyBy.class);
		return annotation.identifier();
	}

	private String getLocatorTypeFromAnnotation() {
		return this.getClass().getAnnotation(IdentifyBy.class).locator().getLocatorType();
	}

	private String getAttributeTypeFromAnnotation() {
		String attribute = this.getClass().getAnnotation(IdentifyBy.class).attribute();
		if (attribute.isEmpty()) {
			return getLocatorTypeFromAnnotation();
		}
		return attribute;
	}

	private String getExpectedValueFromAnnotation() {
		String value = this.getClass().getAnnotation(IdentifyBy.class).value();
		if ("---".equals(value)) {
			return getIdentifierStringFromAnnotation();
		}
		return value;
	}

	private String getActualValue() {
		try {
			By by = getIdentifierFromAnnotation();
			new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(by));
			return driver.findElement(by).getAttribute(getAttributeTypeFromAnnotation());
		} catch (TimeoutException t) {
			return "";
		}
	}

	private boolean compareWithActualValueForText(String expectedValue) {
		try {
			By by = getIdentifierFromAnnotation();
			new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(by));
			new WebDriverWait(driver, 5).until(ExpectedConditions.textToBePresentInElementLocated(by, expectedValue));

			return true;
		} catch (TimeoutException t) {
			return false;
		}
	}

	private boolean compareWithActualValueForRegex(String expectedRegex) {
		try {
			By by = getIdentifierFromAnnotation();
			new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(by));
			new WebDriverWait(driver, 5)
					.until(ExpectedConditionExtension.textRegexToBePresentInElementLocated(by, expectedRegex));

			return true;
		} catch (TimeoutException t) {
			return false;
		}
	}

	/**
	 * To improve page stability and avoid element not clickable at given location
	 * error on page load/refresh
	 * 
	 * @param element WebElement - least stable element of the page
	 * @param timeout int - in milliseconds
	 */
	protected void waitForPageStability(WebElement element, int timeout) {
		int count = 0;
		long start = System.currentTimeMillis();
		Point point = element.getLocation();
		while (System.currentTimeMillis() < (start + timeout)) {
			if (point.getX() == element.getLocation().getX() && point.getY() == element.getLocation().getY()) {
				count++;
			} else {
				count = 0;
				point = element.getLocation();
			}
			if (count == 10) {
				return;
			}
			try {
				// sleep for 100ms to give page a little time
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO: 11/3/2017 add logger
			}
		}
	}

	/**
	 * Checks if the calling page is the displayed page on driver. Requires
	 * IdentifyBy annotation to be described for the calling page
	 * 
	 * @return boolean
	 */
	public boolean isDisplayed() {
		try {
			return compareExpectedAndActual();
		} catch (StaleElementReferenceException e) {
			return false;
		}
	}

	private boolean compareExpectedAndActual() {
		if ("text".equals(getAttributeTypeFromAnnotation())) {
			return compareWithActualValueForText(getExpectedValueFromAnnotation());
		}

		if ("regex".equals(getAttributeTypeFromAnnotation())) {
			return compareWithActualValueForRegex(getExpectedValueFromAnnotation());
		}
		return getExpectedValueFromAnnotation().equalsIgnoreCase(getActualValue());
	}

	/**
	 * Asserts the presence of the expected page on driver. Requires IdentifyBy
	 * annotation to be described for the calling page
	 */
	public void assertPage() {
		if (!compareExpectedAndActual()) {
			throw new EndTestException("Expected and actual page identification value are not same");
		}
	}

	/**
	 * Waits for the page to load and appear as the active on driver window.
	 * Requires IdentifyBy annotation to be described for the calling page
	 *
	 * @param timeout int - in milliseconds
	 */
	public void waitForPage(int timeout) {
		long start = System.currentTimeMillis();
		while (System.currentTimeMillis() < (start + timeout)) {
			if (!compareExpectedAndActual()) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO: 11/1/2017 add logger
				}
			} else {
				return;
			}
		}
		throw new TimeoutException("Timed out while waiting page to be identified on driver :: " + this.getClass());
	}

	/**
	 * Asserts the window title with the expected value
	 * 
	 * @param expectedPageTitle String
	 */
	protected void assertPageTitle(String expectedPageTitle) {
		if (!expectedPageTitle.equalsIgnoreCase(getPageTitle())) {
			throw new EndTestException("Expected and actual page title value are not same");
		}
	}

	/**
	 * Gets the page title
	 * 
	 * @return String
	 */
	protected String getPageTitle() {
		return driver.getTitle();
	}

	/**
	 * note: use this in case of nested frames instead of name/id
	 * 
	 * @param webElement WebElement
	 */
	protected void switchToFrame(WebElement webElement) {
		new WebDriverWait(driver, 180).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(webElement));
	}

	/**
	 * 'frame' variable should be the id/name of that FRAME
	 * 
	 * @param frame String
	 */
	protected void switchToFrame(String frame) {
		new WebDriverWait(driver, DEFAULT_LOAD_TIMEOUT_SEC)
				.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
	}

	/**
	 * Moves back to the main window
	 */
	protected void goToDefaultFrame() {
		driver.switchTo().defaultContent();
	}

	/**
	 * Moves to the parent frame of the current frame
	 */
	protected void goToParentFrame() {
		driver.switchTo().parentFrame();
	}

	/**
	 * Switches the driver focus to the last opened window Should be used when only
	 * 2 windows are displayed at a time
	 */
	protected void switchToNewWindow() {
		new WebDriverWait(driver, DEFAULT_LOAD_TIMEOUT_SEC)
				.until(ExpectedConditionExtension.moreThanOneWindowCondition());
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
	}

	/**
	 * Switches the driver focus to the last opened window Should be used when more
	 * than 2 windows are displayed at a time
	 * 
	 * @param expectedNumberOfWindows int - number of expected windows on driver
	 *                                screen
	 */
	protected void switchToNewWindow(int expectedNumberOfWindows) {
		new WebDriverWait(driver, DEFAULT_LOAD_TIMEOUT_SEC).until(numberOfWindowsToBe(expectedNumberOfWindows));
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
	}

	/**
	 * Used esp. in case of more than 2 windows on screen and switching is required
	 * Should be called before the input action that will cause the new window to
	 * open
	 * 
	 * @return String - window handle reference name
	 */
	protected String getCurrentWindow() {
		return driver.getWindowHandle();
	}

	/**
	 * Switches the driver focus to the input window String Input string will the
	 * result of getCurrentWindow() called before the input action that caused the
	 * new window to open
	 * 
	 * @param winHandle String - window handle reference name
	 */
	protected void switchToWindow(String winHandle) {
		new WebDriverWait(driver, DEFAULT_LOAD_TIMEOUT_SEC)
				.until(ExpectedConditionExtension.moreThanOneWindowCondition());
		driver.switchTo().window(winHandle);
	}

	public int getScreenHeight() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return ((Number) js.executeScript("return window.innerHeight")).intValue();
	}

	public int getScreenWidth() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return ((Number) js.executeScript("return document.body.clientWidth")).intValue();
	}

	public boolean nvl(Object value) {
		if (value instanceof String) {
			if (value != null)
				if (!value.toString().equalsIgnoreCase(""))
					return true;
		}

		if (value instanceof Double ) {
			if (value != null && (Double) value != 0)
				return true;
		}

		if (value instanceof Integer ) {
			if (value != null && (Integer) value != 0)
				return true;
		}
		return false;
	}
}
