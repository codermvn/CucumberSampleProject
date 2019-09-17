package com.lh.test.pages;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.lh.test.framework.annotations.IdentifyBy;
import com.lh.test.framework.application.BasePage;
import com.lh.test.framework.application.helpers.LocatorTypes;

@IdentifyBy(locator = LocatorTypes.CSS, identifier = "div > h1", attribute = "regex", value = ".*Welcome.*")
public class PLPHomepage extends BasePage {
	Logger Log = Logger.getLogger(Loginpage.class.getName());

	@FindBy(css = "div > a > button[qa-id='homepage-frames-btn']")
	private WebElement frameListButton;

	@FindBy(css = "div > a > button[qa-id='homepage-obr-btn']")
	private WebElement oBRListButton;

	public PLPHomepage() {
		PageFactory.initElements(driver, this);
	}

	public void clickFrameListButton() {
		new WebDriverWait(driver, 30).until(elementToBeClickable(frameListButton));
		frameListButton.click();
		Log.info("Clicking Frame List Button");
	}

	public void clickOBRListButton() throws InterruptedException {
		new WebDriverWait(driver, 30).until(elementToBeClickable(oBRListButton));
		oBRListButton.click();
		Log.info("Clicking OBR List Button");

	}

}
