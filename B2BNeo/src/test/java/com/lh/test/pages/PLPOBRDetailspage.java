package com.lh.test.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.lh.test.framework.annotations.IdentifyBy;
import com.lh.test.framework.application.BasePage;
import com.lh.test.framework.application.SetupTest;
import com.lh.test.framework.application.helpers.LocatorTypes;
import com.lh.test.model.Market;

@IdentifyBy(locator = LocatorTypes.CSS, identifier = "div .jss101 > strong > span", attribute = "text", value = "OBR Details")
public class PLPOBRDetailspage extends BasePage {

	Logger Log = Logger.getLogger(PLPOBRDetailspage.class.getName());
	@FindBy(css = "button[qa-id='header-back-btn']")
	private WebElement backButton;

	@FindBy(css = "button[qa-id = 'obr-details-reject-btn']")
	private WebElement rejectButton;

	@FindBy(css = "button[qa-id = 'obr-details-approve-btn']")
	private WebElement approveButton;

	@FindBy(css = "div .ReactVirtualized__Grid__innerScrollContainer > div")
	private List<WebElement> baseFareDataLst;

	@FindBy(css = ".jss996 > div:nth-child(1)")
	private WebElement disabledAppRejButton;

	@FindBy(css = "div > [qa-id ='obr-details-headers-obr-status-value']")
	private WebElement obrStatus;

	@FindBy(css = "div.MuiGrid-root.MuiGrid-container.MuiGrid-justify-xs-space-between")
	private WebElement currentOBR;

	@FindBy(css = "input[qa-id='obrframelist-approved_fare_amount-cell-0-input']")
	private WebElement approveDisCat35fix;

	@FindBy(css = "div > input[qa-id ='obrframelist-approved_relative_discount-cell-0-input']")
	private WebElement resMaxAbsDiscount;

	@FindBy(css = "div > input[qa-id ='obrframelist-rfd_booking_class-cell-0-input']")
	private WebElement resultingBC;

	@FindBy(css = "div > [qa-id='obrframelist-approved_fare_amount-cell-0'] > span ")
	private WebElement chgApproveDisCat35fix;

	@FindBy(css = "div > [qa-id ='obrframelist-approved_relative_discount-cell-0'] > span")
	private WebElement chgResMaxAbsDiscount;

	@FindBy(css = "div > [qa-id ='obrframelist-rfd_booking_class-cell-0'] > span ")
	private WebElement chgResultingBC;

	private String IS_OBR_ACTIONED = "OBR is neither Approved/Rejected";
	private String IS_FIELDS_EDITABLE = "Frame fields are editable after action";
	private String Is_VALUE_VISIBLE_AS_SAVED = "Updated values are not persisting";

	public PLPOBRDetailspage() {
		PageFactory.initElements(driver, this);
	}

	public boolean verifyMarketOrgnAndDstn(Market market) throws InterruptedException {

		Log.info("Verifying origin and destination");
		waitForElements(baseFareDataLst);
		for (int i = 0; i < baseFareDataLst.size(); i++) {
			WebElement elementOrgn = new WebDriverWait(driver, 10)
					.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(
							"div .ReactVirtualized__Grid__innerScrollContainer div[qa-id = 'obrframelist-bd_origin_code-cell-"
									+ i + "']"))));

			WebElement elementDstn = new WebDriverWait(driver, 10)
					.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(
							"div .ReactVirtualized__Grid__innerScrollContainer div[qa-id = 'obrframelist-bd_destination_code-cell-"
									+ i + "']"))));
			if (elementOrgn.getText().equals(market.getMarketOrgn())
					&& elementDstn.getText().equals(market.getMarketDstn())) {
				// Thread.sleep(sleep);
				return true;
			}
		}
		// Thread.sleep(sleep);
		return false;

	}

	public void updateOBRDetails(Integer enterDiscount, String enterResBC, String filingMethod)
			throws InterruptedException {
		Log.info("Trying to update OBR with Discount and Booking Class");
		if (filingMethod.equalsIgnoreCase("CAT35FIX")) {
			if (nvl(enterDiscount)) {
				waitForElement(approveDisCat35fix);
				approveDisCat35fix.clear();
				approveDisCat35fix.sendKeys(enterDiscount.toString());
				Thread.sleep(sleep);
			}

		} else { // For filing methods Cat25, Cat35 Float
			if (nvl(enterDiscount)) {
				waitForElement(resMaxAbsDiscount);
				resMaxAbsDiscount.clear();
				resMaxAbsDiscount.sendKeys(enterDiscount.toString());
				Thread.sleep(sleep);
			}

		}

		waitForElement(resultingBC);
		if (nvl(enterResBC)) {
			resultingBC.clear();
			resultingBC.sendKeys(enterResBC);
		}
	}

	public void clickBackButton() throws InterruptedException {
		Thread.sleep(sleep);
		waitUntilElementClickable(backButton);
		waitForElement(backButton);
		backButton.click();
		Log.info("Clicking Back Button From OBR Details Page");
	}

	public void clickRejectButton() throws InterruptedException {
		waitForElement(rejectButton);
		rejectButton.click();
		Log.info("Clicking OBR Reject button");
	}

	public void clickApproveButton() throws InterruptedException {
		waitForElement(approveButton);
		approveButton.click();
		Log.info("Clicking OBR Approve button");
	}

	public List<String> isObrActioned(String filingMethod, String discount, String rbd) {
		waitForElement(obrStatus);
		Log.info("OBR ID-->" + currentOBR.getAttribute("qa-id").substring(25));
		Log.info(obrStatus.getText());
		List<String> validationErrors = new ArrayList<String>();
		// Validation 1: Check the status of OBR
		if (!(obrStatus.getText().equalsIgnoreCase("Approved") || obrStatus.getText().equalsIgnoreCase("Rejected"))) {
			validationErrors.add(IS_OBR_ACTIONED);
		}

		if (nvl(discount) || nvl(rbd)) {
			// Validation 2: Check if Discount, Booking Class and Date fields are non
			// editable
			// Validation 3: Check if discounts and booking class are present as saved
			if (filingMethod.equalsIgnoreCase("CAT35FIX")) {
				if (checkIfElementIsInput(chgApproveDisCat35fix) || checkIfElementIsInput(chgResultingBC))
					validationErrors.add(IS_FIELDS_EDITABLE);
				// Validation 3: Check if discounts and booking class are present as saved
				if (!(chgApproveDisCat35fix.getText().replace("%", "").trim().equals(discount)
						&& chgResultingBC.getText().trim().equalsIgnoreCase(rbd))) {
					Log.info("Discount for Cat35Fix" + chgApproveDisCat35fix.getText());
					Log.info("Resulting Booking Class" + chgResultingBC.getText());
					validationErrors.add(Is_VALUE_VISIBLE_AS_SAVED);

				}
			} else {
				if (checkIfElementIsInput(chgResMaxAbsDiscount) || checkIfElementIsInput(chgResultingBC))
					validationErrors.add(IS_FIELDS_EDITABLE);
				if (!(chgResMaxAbsDiscount.getText().replace("%", "").trim().equals(discount)
						&& chgResultingBC.getText().trim().equalsIgnoreCase(rbd))) {
					Log.info("Discount for Cat35Float or Cat25: " + chgResMaxAbsDiscount.getText());
					Log.info("Resulting Booking Class" + chgResultingBC.getText());
					validationErrors.add(Is_VALUE_VISIBLE_AS_SAVED);
				}
			}
		}
		return validationErrors;
	}
}
