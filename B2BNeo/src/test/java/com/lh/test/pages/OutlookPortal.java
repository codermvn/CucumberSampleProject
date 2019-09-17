package com.lh.test.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.lh.test.framework.annotations.IdentifyBy;
import com.lh.test.framework.application.BasePage;
import com.lh.test.framework.application.helpers.BaseOperations;
import com.lh.test.framework.application.helpers.LocatorTypes;
import com.lh.test.framework.configuration.Configuration;
import com.lh.test.framework.handlers.EnvironmentHandler;

@IdentifyBy(locator = LocatorTypes.CSS, identifier = "#bySelection > div.groupMargin", attribute = "text", value = "Sign in with one of these accounts")

public class OutlookPortal extends BasePage {
	
	Logger Log = Logger.getLogger(OutlookPortal.class);
	Configuration config = Configuration.getConfiguration();
	
	public OutlookPortal() {
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css = "div.idp[tabindex = '1']")
	private WebElement lufthansaADFSServiceButton;
	
	@FindBy(css = "#userNameInput")
	private WebElement emailAddress;
	
	@FindBy(css = "#passwordInput")
	private WebElement emailPassword;
	
	@FindBy(css = "#submitButton")
	private WebElement signInButton;
	
	@FindBy(css = "#searchBoxId")
	private WebElement searchMailInput;
	
	@FindBy(css = "input._1Qs0_GHrFMawJzYAmLNL2x")
	private WebElement inputSearchTxt;
	
	@FindBy(css = "button[aria-label='Search']")
	private WebElement searchButton;
	
	@FindBy(css = "div.RKFl-TUsdXTE7ZZWxFGwX>div >div:nth-child(2)")
	private WebElement firstMail;
	
	@FindBy(css = "div.expanded-itempart div.item-header-actions >div:nth-child(2)>div")
	private WebElement firstMailTime;
	
	
	@FindBy(css = "button[name = 'Download']")
	private WebElement downloadButton;
	
	@FindBy(css = "div.expanded-itempart div._2mZE6q64kgwFv3zDOYIyAn")
	private WebElement downloadLink;
	
	
	public void login()
	{
		clickLufthansaADFSServiceButton();
		enterEmailAddress();
		enterEmailPassword();
		clickSignInButton();
	}
	
	
	public void clickLufthansaADFSServiceButton() {
		waitForElement(lufthansaADFSServiceButton);
		lufthansaADFSServiceButton.click();
	}
	
	public void enterEmailAddress(){
		waitForElement(emailAddress);
		emailAddress.sendKeys(config.getPLPUserMailID());
	}
	
	public void enterEmailPassword(){
		waitForElement(emailPassword);
		emailPassword.sendKeys(config.getPlPUserMailPWD());	
	}
	
	public void clickSignInButton(){
		waitForElement(signInButton);
		signInButton.click();
		}
	
	public int exportFileAndGetRecords(LocalDateTime timeOfExport) throws InterruptedException, IOException, URISyntaxException, AWTException {
		
		Log.info("Searching email having export file...");
		int excelRecordCount =0;
		clickElement(searchMailInput);
		clickElement(inputSearchTxt);
		inputSearchTxt.sendKeys("Requested frame excel export file");
		clickElement(searchButton);
		clickElement(firstMail);
		waitUntilElementVisible(firstMailTime, 180);
		LocalDateTime eMailDateTime = getDateTimeFromMail(firstMailTime);
		if(timeOfExport.minusHours(1).compareTo(eMailDateTime)<=0 && timeOfExport.plusMinutes(5).compareTo(eMailDateTime)>0)
		{
			Log.info("Email found");
			WebElement webElement = driver.findElement(By.cssSelector("div.BodyFragment span div"));
			String mailContent = webElement.getText();
			if(mailContent.contains("successfully processed"))
			{
				Log.info("Frame request successfully processed");
				 Robot robot = new Robot();  // Robot class throws AWT Exception	
				waitForElement(downloadLink);
				String fileName = downloadLink.getAttribute("aria-label");
				downloadLink.click();		
				Log.debug("Downloading export file...");
				clickElement(downloadButton);
			
				 actionByRobot(robot,"press",KeyEvent.VK_DOWN);
				 actionByRobot(robot,"release",KeyEvent.VK_DOWN);

				 int tabCount =1;
				 while(tabCount<=3)
				 {
					 actionByRobot(robot,"press",KeyEvent.VK_TAB);
					 actionByRobot(robot,"release",KeyEvent.VK_TAB);
					 tabCount++;
				 }
				 
				actionByRobot(robot,"press",KeyEvent.VK_ENTER);
				actionByRobot(robot,"release",KeyEvent.VK_ENTER);
				String home = System.getProperty("user.home");
				copyFileFromSource(home+"\\Downloads\\" + fileName.split(" ")[0]);
				excelRecordCount = readNumberOfRowInExcel(home+"\\Downloads\\" + fileName.split(" ")[0]);
				Log.info("Excel Record Count: " +excelRecordCount);
			}
			
		}
		return excelRecordCount;
			
		
	}
	
	public void actionByRobot(Robot robot, String eventName, int button) throws InterruptedException
	{
		if(eventName.equals("press"))
		{
			robot.keyPress(button);
		}
		else if(eventName.equals("release"))
		{
			robot.keyRelease(button);
		}
		
	}
	
	public LocalDateTime getDateTimeFromMail(WebElement firstMailTime)
	{
		String webElementMailStr = firstMailTime.getText();
		String[] strArray = webElementMailStr.split(" ");
		String str = strArray[strArray.length-1];
		String strDay = strArray[strArray.length-2];
		String[] strArrayDay = strDay.split("/");
		String strEmailDateTime = strArrayDay[2]+"-"+strArrayDay[1]+"-"+strArrayDay[0]+" "+str;	
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime formatDateTime = LocalDateTime.parse(strEmailDateTime, formatter);
		return formatDateTime;
	}
	
	
	

}
