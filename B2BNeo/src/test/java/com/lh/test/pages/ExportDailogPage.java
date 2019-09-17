package com.lh.test.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.lh.test.framework.annotations.IdentifyBy;
import com.lh.test.framework.application.BasePage;
import com.lh.test.framework.application.helpers.LocatorTypes;

@IdentifyBy(locator = LocatorTypes.CSS, identifier = "#alert-dialog-title > h6", attribute = "text", value = "Excel export")
public class ExportDailogPage extends BasePage {
	
	Logger Log = Logger.getLogger(ExportDailogPage.class);
	
	public ExportDailogPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "button[qa-id='confirmation-dialog-ok-btn']")
	private WebElement okButton;
	
	public void clickOK() {
		waitForElement(okButton);
		okButton.click();
	}

}
