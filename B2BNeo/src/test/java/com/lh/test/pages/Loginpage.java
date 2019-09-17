package com.lh.test.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.lh.test.framework.annotations.IdentifyBy;
import com.lh.test.framework.application.BasePage;
import com.lh.test.framework.application.helpers.LocatorTypes;
import com.lh.test.framework.configuration.Configuration;

@IdentifyBy(locator = LocatorTypes.CSS, identifier = "div.jss216", attribute = "text", value = "ProfitLine/Price")
public class Loginpage extends BasePage {

	Logger Log = Logger.getLogger(Loginpage.class.getName());

	// private Configuration configuration = Configuration.getConfiguration();

	@FindBy(css = "input.jss214:nth-child(5)")
	private WebElement username;

	@FindBy(css = "input.jss214:nth-child(7)")
	private WebElement password;

	@FindBy(css = "body > div.MuiDialog-root > div.MuiDialog-container.MuiDialog-scrollPaper > div > div > button[qa-id='authentication-login-btn']")
	private WebElement loginButton;

	public Loginpage() {
		PageFactory.initElements(driver, this);
	}

	public void clickLoginButton() {

	}

	public void enterPLPCredentialsAndLogin(String usernameStr, String passwordStr) {
		Log.info("Entering Login Credentials");
		waitForElement(loginButton);
		username.sendKeys(usernameStr);
		password.sendKeys(passwordStr);
		loginButton.click();
	}

}
