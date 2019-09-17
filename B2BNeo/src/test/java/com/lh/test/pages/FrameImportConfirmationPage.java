package com.lh.test.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.lh.test.OBR.OBRService;
import com.lh.test.framework.annotations.IdentifyBy;
import com.lh.test.framework.application.BasePage;
import com.lh.test.framework.application.helpers.LocatorTypes;

@IdentifyBy(locator = LocatorTypes.CSS, identifier = "#alert-dialog-title > h6", attribute = "text", value = "Frame import")
public class FrameImportConfirmationPage extends BasePage {
	Logger Log = Logger.getLogger(FrameImportConfirmationPage.class.getName());

	public FrameImportConfirmationPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "button[qa-id='confirmation-dialog-ok-btn']")
	private WebElement okButton;

	public void clickOK() {

		waitForElement(okButton);
		okButton.click();
		Log.info(" Import Confirmation Page Clicked");
	}
}
