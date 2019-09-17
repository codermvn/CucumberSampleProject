package com.lh.test.pages;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.lh.test.OBR.Helper;
import com.lh.test.framework.annotations.IdentifyBy;
import com.lh.test.framework.application.BasePage;
import com.lh.test.framework.application.SetupTest;
import com.lh.test.framework.application.helpers.LocatorTypes;
import com.lh.test.framework.handlers.DBConnectionHandler;
import com.lh.test.model.Market;

@IdentifyBy(locator = LocatorTypes.CSS, identifier = "div.jss4 h1", attribute = "text", value = "OBRs")
public class PLPOBRListpage extends BasePage {

	public PLPOBRListpage() {
		PageFactory.initElements(driver, this);
	}

	public static Logger Log = Logger.getLogger(PLPOBRListpage.class.getName());

	@FindBy(css = "#status")
	private WebElement status;

	@FindBy(css = "div > button[qa-id='OBR-basicfilter-search-button']")
	private WebElement searchButton;
	
	@FindBy(css = "#status>option[value = '']")
	private WebElement statusOptionBlank;

	@FindBy(css = "#status>option[value = 'Open']")
	private WebElement statusOptionOpen;

	@FindBy(css = "#status>option[value = 'Rejected']")
	private WebElement statusOptionRejected;

	@FindBy(css = "#status>option[value = 'Approved']")
	private WebElement statusOptionApproved;

	@FindBy(css = "div > input[qa-id ='OBR-basicfilter-tigerId-input']")
	private WebElement tigerID;

	@FindBy(css = "button.jss160:nth-child(6)")
	private WebElement logoutButton;

	@FindBy(css = "div > input[qa-id ='OBR-basicfilter-customerName-input']")
	private WebElement CustomerName;

	@FindBy(css = "div.ReactVirtualized__Grid:nth-child(2)")
	private WebElement scroll;

	@FindBy(css = "#receivedDateType")
	private WebElement receivedDateType;

	@FindBy(css = "#receivedDateType>option[value = 'on']")
	private WebElement receivedDateOn;

	@FindBy(css = "#receivedDateType>option[value = 'on/before']")
	private WebElement receivedDateOnOrBefore;

	@FindBy(css = "#receivedDateType>option[value = 'on/after']")
	private WebElement receivedDateOnOrAfter;

	@FindBy(css = "input[qa-id = 'OBR-basicfilter-received-date-input']")
	private WebElement receivedDate;

	public Set<Integer> setOpenOBR = new HashSet<Integer>();

	public Set<Integer> getListOpenOBRs() {
		return setOpenOBR;
	}

	public void setListOpenOBRs(Set<Integer> listOpenOBRs) {
		this.setOpenOBR = listOpenOBRs;
	}

	public boolean verifyOBRListpageDisplayed() throws InterruptedException {
		Thread.sleep(sleep);
		Log.info("Verifying if OBR page is visible");
		WebElement webElement = new WebDriverWait(driver, 10).until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div/div[2]/div/div[1]/h1"))));
		if (webElement.getText().equals("OBRs")) {
			return true;
		} else
			return false;
	}

	public void selectStatus(String statusStr) {
		Log.info("Entering search criterion OBR status for OBR search");
		waitForElement(status);		
		status.click();	
		WebElement webElement = statusStr.equalsIgnoreCase("Open") ? statusOptionOpen
				: statusStr.equalsIgnoreCase("Rejected") ? statusOptionRejected
						: statusStr.equalsIgnoreCase("Approved") ? statusOptionApproved : null;
		if (webElement != null)
			webElement.click();
		else
			statusOptionBlank.click();
	}

	public void clickSearchButton() throws InterruptedException {
		Log.info("Clicking OBR Search Button");
		waitForElement(searchButton);
		searchButton.click();
		Thread.sleep(sleep);
	}

	public void enterTigerID(String tigerIDStr) {
		Log.info("Entering Tiger ID for OBR Search");
		waitForElement(tigerID);
		tigerID.sendKeys(tigerIDStr);
		receivedDateType.click();
		waitForElement(receivedDateOn);
		receivedDateOn.click();
	}


	public boolean verifyOBRDisplayed(Map<Integer, Market> OBR_IDAndMarketMap) {
		Log.info("Verifying if OBR is displayed");

		for (Map.Entry<Integer, Market> oBR_IDAndMarketEntry : OBR_IDAndMarketMap.entrySet()) {
			boolean result = false;
			int index = 0;
			for (WebElement element : driver
					.findElements(By.cssSelector("div.ReactVirtualized__Grid__innerScrollContainer > a"))) {
				if (element.getAttribute("qa-id")
						.equals("obrlist-row-index_" + index + "-id_" + oBR_IDAndMarketEntry.getKey())) {
					result = true;
				}
				index++;
			}

			if (!result)
				return false;

		}

		return true;

	}

	public void openOBRByOBRID(Integer OBR_ID) {
		Log.info("Clicking OBR ID");
		int index = 0;
		for (WebElement element : driver
				.findElements(By.cssSelector("div.ReactVirtualized__Grid__innerScrollContainer > a"))) {
			if (element.getAttribute("qa-id").equals("obrlist-row-index_" + index + "-id_" + OBR_ID)) {
				WebElement webElement = driver.findElement(By
						.cssSelector("div.ReactVirtualized__Grid__innerScrollContainer > a[qa-id = 'obrlist-row-index_"
								+ index + "-id_" + OBR_ID + "'] div[qa-id ='obrlist-status-cell-" + index + "']"));
				webElement.click();
				break;
			}
			index++;
		}
	}

	public void openOBRfromURL(String obrID) {
		Log.info("Opening OBR Directly from URL");
		driver.get("https://airlapi.isb.lsy.fra.dlh.de/plp/webui/#/obr/" + obrID);
	}

	public void clickOnLogoutButton() {
		Log.info("Clicking LOGOUT Button");
		waitForElement(logoutButton);
		logoutButton.click();
		WebElement webElement = new WebDriverWait(driver, 10).until(ExpectedConditions
				.visibilityOf(driver.findElement(By.cssSelector("li[qa-id = 'header-user-menu-menuitem-logout']"))));
		webElement.click();
	}

	public void clickOnAllOpenRequests(String custName, String date, String status) {
		selectStatus(status);
		waitForElement(CustomerName);
		CustomerName.sendKeys(custName);
		waitForElement(receivedDateOnOrBefore);
		receivedDateOnOrBefore.click();
		waitForElement(receivedDate);
		receivedDate.clear();
		receivedDate.sendKeys(date);
	}

	public void scrollPage() {
		Log.info("Clicking Scroll Down Button");
		waitForElement(scroll);
		scroll.sendKeys(Keys.PAGE_DOWN);
		scroll.sendKeys(Keys.PAGE_DOWN);
	}

	public int getDisplayedOBRIDs() {
		Log.info("Capturing all the OBRs which are in Open Status");
		try {
			for (WebElement element : driver
					.findElements(By.cssSelector("div.ReactVirtualized__Grid__innerScrollContainer > a"))) {
				setOpenOBR.add(Integer.parseInt(element.getAttribute("href").substring(51)));
			}
		} catch (StaleElementReferenceException elementHasDisappeared) {
			Log.info("Got Stale State Exception");
			getDisplayedOBRIDs();
		}
		return setOpenOBR.size();
	}

	public void getOBRScrollable() {
		int originalCount=0;
		while(getDisplayedOBRIDs()>originalCount)
		{
			originalCount = getDisplayedOBRIDs();
			scrollPage();
		}	
	}
	
}
