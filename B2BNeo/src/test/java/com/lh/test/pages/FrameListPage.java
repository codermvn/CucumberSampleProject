package com.lh.test.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.lh.test.OBR.Helper;
import com.lh.test.framework.annotations.IdentifyBy;
import com.lh.test.framework.application.BasePage;
import com.lh.test.framework.application.helpers.LocatorTypes;
import com.lh.test.framework.handlers.DBConnectionHandler;
import com.lh.test.framework.handlers.EnvironmentHandler;

@IdentifyBy(locator = LocatorTypes.CSS, identifier = "div.MuiToolbar-root.MuiToolbar-regular.jss106.MuiToolbar-gutters > span.jss107 > strong > span", attribute = "text", value = "Frame List")
public class FrameListPage extends BasePage {

	Logger Log = Logger.getLogger(FrameListPage.class.getName());

	@FindBy(css = "#frameType")
	private WebElement frameType;

	@FindBy(css = "#frameType>option[value='LION']")
	private WebElement optionFrameTypeLION;

	@FindBy(css = "#frameType>option[value='PANDA']")
	private WebElement optionFrameTypePANDA;

	@FindBy(css = "#filingMethod")
	private WebElement filingMeth;

	@FindBy(css = "#filingMethod>option[value='CAT25']")
	private WebElement filingMethCat25;

	@FindBy(css = "#filingMethod>option[value='CAT25 abs']")
	private WebElement filingMethCat25abs;

	@FindBy(css = "#filingMethod>option[value='CAT35 fix']")
	private WebElement filingMethCat35fix;

	@FindBy(css = "#filingMethod>option[value='CAT35 float']")
	private WebElement filingMethCat35float;

	@FindBy(css = "#subcategory")
	private WebElement SubCategory;

	@FindBy(css = "#subcategory>option[value='A']")
	private WebElement SubCategoryA;

	@FindBy(css = "input[qa-id='frames-basicfilter-pos-location-input']")
	private WebElement POC;

	@FindBy(css = "input[qa-id='frames-basicfilter-basecarrier-input']")
	private WebElement baseCarrier;

	@FindBy(css = "input[qa-id='frames-basicfilter-origin-location-input']")
	private WebElement origin;

	@FindBy(css = "input[qa-id='frames-basicfilter-destination-location-input']")
	private WebElement destination;

	@FindBy(css = "#compartment")
	private WebElement cmp;

	@FindBy(css = "#compartment>option[value='F']")
	private WebElement cmpFirst;

	@FindBy(css = "#compartment>option[value='C']")
	private WebElement cmpBusiness;

	@FindBy(css = "#compartment>option[value='W']")
	private WebElement cmpPremiumEco;

	@FindBy(css = "#compartment>option[value='Y']")
	private WebElement cmpEconomy;

	@FindBy(css = "input[qa-id='frames-basicfilter-wildcard-fcc-input']")
	private WebElement FCCcontains;

	@FindBy(css = "input[qa-id='frames-basicfilter-resulting-rbd-input']")
	private WebElement resultingBC;

	@FindBy(css = "button[qa-id='frames-basicfilter-search-button']")
	private WebElement searchButton;

	@FindBy(css = "button[qa-id='frames-basicfilter-toggle-btn']")
	private WebElement frameListFilter;

	@FindBy(css = "button[qa-id='frames-basicfilter-pos-location-selection-DE'")
	private WebElement pocSelection;

	@FindBy(css = "button[qa-id='frames-excel-import-btn']")
	private WebElement frameImportBtn;

	@FindBy(xpath = "/html/body/div[1]/div/div/div/div[2]/div/div[3]/div/span/strong")
	private WebElement returnedFrameCnt;

	@FindBy(css = "button[qa-id='frames-delete-btn']")
	private WebElement deleteButton;

	@FindBy(css = "button[qa-id='frames-excel-export-btn']")
	private WebElement frameExportBtn;

	@FindBy(css = "button[qa-id='frames-bulkedit-btn']")
	private WebElement frameEditBtn;

	@FindBy(css = "button[qa-id='frames-close-bulk-edit-btn']")
	private WebElement Editframe;
	
	@FindBy(css = "button[qa-id='frames-from-fares-btn']")
	private WebElement frameOutOfFareBtn;

	public FrameListPage() {
		PageFactory.initElements(driver, this);
	}

	/**
	 * @return: Returns whether delete button was clicked or not. If there are not
	 *          elements on frame list search then delete would not happen and hence
	 *          would return false.
	 */
	public boolean clickDeleteButton() {
		waitForElement(deleteButton);
		if (deleteButton.getAttribute("qa-status") != null && !this.returnedFrameCnt.getText().equalsIgnoreCase("0")) {
			Log.info("Delete Frame Button Clicked");
			deleteButton.click();
			return true;
		} else {
			Log.fatal("Delete Element not present");
			return false;
		}

	}

	/**
	 * @param searchFrameType
	 * @param searchFilingMet
	 * @param searchSubCat
	 * @param searchPOC
	 * @param searchCarrier
	 * @param searchOrigin
	 * @param searchDest
	 * @param searchCMP
	 * @param searchFCC
	 * @param searchresulBC
	 * @throws InterruptedException
	 */
	public void setUISearchParameters(String searchFrameType, String searchFilingMet, String searchSubCat,
			String searchPOC, String searchCarrier, String searchOrigin, String searchDest, String searchCMP,
			String searchFCC, String searchresulBC) throws InterruptedException {

		Log.info("Setting Frame List Search options");
		waitForElement(frameType);

		// frameType
		frameType.click();
		WebElement frameType = searchFrameType.equalsIgnoreCase("LION") ? optionFrameTypeLION
				: searchFrameType.equalsIgnoreCase("PANDA") ? optionFrameTypePANDA : null;
		if (frameType != null)
			frameType.click();
		// filingMethod
		filingMeth.click();
		searchFilingMet = searchFilingMet.replaceAll("\\s", "");
		WebElement filingMet = searchFilingMet.equalsIgnoreCase("CAT25") ? filingMethCat25
				: searchFilingMet.equalsIgnoreCase("CAT25abs") ? filingMethCat25
						: searchFilingMet.equalsIgnoreCase("CAT35fix") ? filingMethCat35fix
								: searchFilingMet.equalsIgnoreCase("CAT35float") ? filingMethCat35float : null;
		if (filingMet != null)
			filingMet.click();

		// subCategory
		if (searchSubCat != null) {
			SubCategory.click();
			//SubCategoryA.click(); // TODO: This needs to be as per input
		}

		// POC
		if (searchPOC != null) {
			POC.click();
			POC.sendKeys(searchPOC);
			Thread.sleep(sleep);
			frameListFilter.click();
			frameListFilter.click();
		}

		// Base Carrier
		if (searchCarrier != null) {
			baseCarrier.click();
			baseCarrier.sendKeys(searchCarrier);
			Thread.sleep(sleep);
			frameListFilter.click();
			frameListFilter.click();
		}

		// Origin
		if (searchOrigin != null) {
			origin.click();
			origin.sendKeys(searchOrigin);
			Thread.sleep(sleep);
			frameListFilter.click();
			frameListFilter.click();
		}

		// Destination
		if (searchDest != null) {
			destination.click();
			destination.sendKeys(searchDest);
			Thread.sleep(sleep);
			frameListFilter.click();
			frameListFilter.click();
		}

		// Compartment selection
		if (searchCMP != null) {
			WebElement comp = searchCMP.replaceAll("\\s", "").equalsIgnoreCase("First") ? cmpFirst
					: searchCMP.replaceAll("\\s", "").equalsIgnoreCase("Business") ? cmpBusiness
							: searchCMP.replaceAll("\\s", "").equalsIgnoreCase("PremiumEconomy") ? cmpPremiumEco
									: searchCMP.replaceAll("\\s", "").equalsIgnoreCase("Economy") ? cmpEconomy : null;
			if (comp != null) {
				cmp.click();
				comp.click();
			}
		}
		// Wild card FCC
		if (searchFCC != null)
			FCCcontains.sendKeys(searchFCC);
		// Resulting BC
		if (searchresulBC != null)
			resultingBC.sendKeys(searchresulBC);
		Log.info("Search Criterion Saved");
	}

	public boolean isFrameListPageDispplayed() {
		Log.info("Checking if Frame List Page option is Displayed ");
		waitForElement(frameListFilter);
		if (null != frameListFilter && frameListFilter.isDisplayed())
			return true;
		return false;
	}

	public void searchFrames() throws InterruptedException {

		Thread.sleep(sleep);
		searchButton.click();
		Log.info("Clicked Frame Search");
	}

	public void importFrame(String path) throws InterruptedException, AWTException {
		Log.info("Importing File: " + path);
		waitForElement(frameImportBtn);
		frameImportBtn.click();
		Thread.sleep(sleep * 2); // wait for the window to appear
		StringSelection ss = new StringSelection(path);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

		// hit enter
		Robot robot = new Robot();

		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		// switch back

	}

	public int getSearchedFrameCount() {
		waitForElement(returnedFrameCnt);
		Log.info("Frame Search Response Record Count(Screen): " + returnedFrameCnt.getText());
		return Integer.parseInt(returnedFrameCnt.getText());
	}

	public void actionAlertBox() {
		driver.switchTo().alert().getText();
	}

	public void exportFrame() {
		waitUntilElementClickable(frameExportBtn);
		if (frameExportBtn.getAttribute("qa-status").equals("active"))
			frameExportBtn.click();
	}

	public void openOutlookPortal() throws MalformedURLException {
		driver.navigate().to(EnvironmentHandler.getURL("http://outlook.com/owa/hawker.com"));
	}

	public int checkDBFramesAirportAirport(String Origin, String Destination, String filingMethod)

			throws ClassNotFoundException, SQLException {
		DBConnectionHandler db = new DBConnectionHandler();
		Map<Integer, String> queryParameters = new HashMap<Integer, String>();
		Helper helper = new Helper();
		// Check Origin and Destination. If they are not at airport level then call
		// helper function to do so
		queryParameters.put(1, helper.checkAndNormalizeToAirportCode(Origin));
		queryParameters.put(2, helper.checkAndNormalizeToAirportCode(Destination));
		queryParameters.put(3, filingMethod);

		String query = "select count(*) from b2b_frame where origin_co in ? and destination_co in ? and filing_method=? and sysdate between valid_from and valid_to and discon_date > sysdate";

		int DBRecordCount = db.getDBRecordCount(query, queryParameters).intValue();
		Log.info("Database Query Response:" + DBRecordCount);
		return DBRecordCount;
	}

	/**
	 * @param frameType
	 * @param filingMeth
	 * @param SubCat
	 * @param POC
	 * @param baseCarrier
	 * @param Origin
	 * @param destination
	 * @param cmp
	 * @param fccContains
	 * @param resultBC
	 * @return number of records in DB
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public int checkDBFrameAirportAirport(String frameType, String filingMeth, String SubCat, String POC,
			String baseCarrier, String Origin, String destination, String cmp, String fccContains, String resultBC)
			throws SQLException, ClassNotFoundException {

		DBConnectionHandler db = new DBConnectionHandler();
		Map<Integer, String> queryParameters = new HashMap<Integer, String>();
		Helper helper = new Helper();

		String query = "select count(*) from b2b_frame where sysdate between valid_from and valid_to and discon_date > sysdate";
		if (nvl(Origin))
			query = query + " and origin_co in ?";
		if (nvl(destination))
			query = query + " and destination_co in ?";
		if (nvl(filingMeth))
			query = query + "and filing_method=?";
		if (nvl(frameType))
			query = query + " and frame_Type=?";
		if (nvl(SubCat))
			query += " and sub_category=?";
		if (nvl(POC))
			query += " and pos=?";
		if (nvl(baseCarrier))
			query += " and carrier_co=?";
		if (nvl(cmp))
			query += " and compartment=?";
		if (nvl(fccContains))
			query += " and FCC_WILDCARD like ?";
		if (nvl(resultBC))
			query += " and result_bcc=?";

		String compDBSearch = null;
		if (cmp != null && cmp.trim().length() > 0) {
			compDBSearch = cmp.replaceAll("\\s", "").equalsIgnoreCase("First") ? "F"
					: cmp.replaceAll("\\s", "").equalsIgnoreCase("Business") ? "C"
							: cmp.replaceAll("\\s", "").equalsIgnoreCase("PremiumEconomy") ? "W" : "Y";
		}

		// Check Origin and Destination. If they are not at airport level then call
		// helper function to do so

		int parameterCounter = 1;
		if (nvl(Origin)) {
			queryParameters.put(parameterCounter, helper.checkAndNormalizeToAirportCode(Origin));
			parameterCounter++;
		}

		if (nvl(destination)) {
			queryParameters.put(parameterCounter, helper.checkAndNormalizeToAirportCode(destination));
			parameterCounter++;
		}

		if (nvl(filingMeth)) {
			queryParameters.put(parameterCounter, filingMeth.trim().length() == 0 ? "%" : filingMeth);
			parameterCounter++;
		}

		if (nvl(frameType)) {
			queryParameters.put(parameterCounter, frameType.trim().length() == 0 ? "%" : frameType);
			parameterCounter++;
		}
		if (nvl(SubCat)) {
			queryParameters.put(parameterCounter, SubCat.trim().length() == 0 ? "%" : SubCat);
			parameterCounter++;
		}
		if (nvl(POC)) {
			queryParameters.put(parameterCounter, POC.trim().length() == 0 ? "%" : POC);
			parameterCounter++;
		}

		if (nvl(baseCarrier)) {
			queryParameters.put(parameterCounter, baseCarrier.trim().length() == 0 ? "%" : baseCarrier);
			parameterCounter++;
		}

		if (nvl(compDBSearch)) {
			queryParameters.put(parameterCounter, cmp.trim().length() == 0 ? "%" : compDBSearch);
			parameterCounter++;
		}

		if (nvl(fccContains)) {
			queryParameters.put(parameterCounter, fccContains.trim().length() == 0 ? "%" : fccContains + "%");
			parameterCounter++;
		}

		if (nvl(resultBC)) {
			queryParameters.put(parameterCounter, resultBC.trim().length() == 0 ? "%" : resultBC);
		}

		Log.info("Total Search Parameter Count: " + (parameterCounter - 1));

		int DBRecordCount = db.getDBRecordCount(query, queryParameters).intValue();
		Log.info("Database Query Response:" + DBRecordCount);
		return DBRecordCount;

	}

	public boolean nvl(String searchElement) {
		if (searchElement != null && searchElement.trim().length() > 0)
			return true;
		return false;

	}
	
	public void EditFrame() throws InterruptedException {
		waitForElement(frameEditBtn);
		frameEditBtn.click();
		//Thread.sleep(sleep);
		driver.switchTo()
        .activeElement().sendKeys();
		
	}	
	
	public boolean isEditFramePageDisplayed() {
		waitForElement(Editframe);
		if (null != Editframe && Editframe.isDisplayed())
			return true;
		return false;
	}
	
	public void clickFrameOutOfFareBtn()
	{
		clickElement(frameOutOfFareBtn);
	}


}
