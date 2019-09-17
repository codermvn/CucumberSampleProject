package com.lh.test.pages;

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


public class FrameOutOfFaresPage extends BasePage {
	
	@IdentifyBy(locator = LocatorTypes.CSS, identifier = "div h6", attribute = "text", value = "Frames out of Fares")
	Logger Log = Logger.getLogger(FrameOutOfFaresPage.class.getName());
	
	@FindBy(css = "div[qa-id='frames-from-fares-filter-dialog-title']")
	private WebElement frameOutOfFareDiv;

	@FindBy(css = "div.MuiGrid-root:nth-child(1) > div:nth-child(1) > div:nth-child(2) > input")
	private WebElement carrier;
	
	@FindBy(css = "div.MuiGrid-root:nth-child(2) > div:nth-child(1) > div:nth-child(2) > input")
	private WebElement origin;
	
	@FindBy(css = "div.MuiGrid-root:nth-child(3) > div:nth-child(1) > div:nth-child(2) > input")
	private WebElement destination;
	
	@FindBy(css = "div[qa-id='frames-from-fares-filter-compartment']>select.MuiInputBase-inputSelect")
	private WebElement cmp;

	@FindBy(css = "div[qa-id='frames-from-fares-filter-compartment']>select.MuiInputBase-inputSelect>option[value='F']")
	private WebElement cmpFirst;

	@FindBy(css = "div[qa-id='frames-from-fares-filter-compartment']>select.MuiInputBase-inputSelect>option[value='C']")
	private WebElement cmpBusiness;

	@FindBy(css = "div[qa-id='frames-from-fares-filter-compartment']>select.MuiInputBase-inputSelect>option[value='W']")
	private WebElement cmpPremiumEco;

	@FindBy(css = "div[qa-id='frames-from-fares-filter-compartment']>select.MuiInputBase-inputSelect>option[value='Y']")
	private WebElement cmpEconomy;

	@FindBy(css = "div.MuiGrid-root:nth-child(4) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > input")
	private WebElement FCC;

	@FindBy(css = "div.MuiGrid-root:nth-child(4) > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > input")
	private WebElement BC;
	
	@FindBy(css = "div[qa-id='frames-from-fares-filter-oneway-roundtrip'] input[value='3']")
	private WebElement oneWay;
	
	@FindBy(css = "div[qa-id='frames-from-fares-filter-oneway-roundtrip'] input[value='2']")
	private WebElement roundTrip;
	
	@FindBy(css = "div[qa-id='frames-from-fares-filter-oneway-roundtrip'] input[value='1']")
	private WebElement doubleOneWay;
	
	@FindBy(css = "div[qa-id='frames-from-fares-filter-fare-source'] input[value = 'Public']")
	private WebElement fareSource_Public;
	
	@FindBy(css = "div input[value = 'Private']")
	private WebElement fareSource_Private;
	
	@FindBy(css = "div input[value = 'Both']")
	private WebElement fareSource_Both;
	
	@FindBy(css = "div[qa-id='frames-from-fares-filter-fare-construction'] input[value = 'None']")
	private WebElement fareConstruction_None;
	
	@FindBy(css = "div[qa-id='frames-from-fares-filter-fare-construction'] input[value = 'Origin']")
	private WebElement fareConstruction_ByOrigin;
	
	@FindBy(css = "div[qa-id='frames-from-fares-filter-fare-construction'] input[value = 'Destination']")
	private WebElement fareConstruction_ByDestination;
	
	@FindBy(css = "div[qa-id='frames-from-fares-filter-fare-construction'] input[value = 'Both']")
	private WebElement fareConstruction_Both;
	
	@FindBy(css = "button[qa-id='frames-from-fares-filter-create-btn']")
	private WebElement createButton;
	
	@FindBy(css = "button[qa-id='frames-from-fares-filter-cancel-btn']")
	private WebElement cancelButton;
	
	public FrameOutOfFaresPage() {
		PageFactory.initElements(driver, this);
	}
	
	
	public boolean isFrameOutOfFarePageDisplayed() {
		Log.info("Checking if Frame List Page option is Displayed ");
		waitForElement(frameOutOfFareDiv);
		if (null != frameOutOfFareDiv && frameOutOfFareDiv.isDisplayed())
			return true;
		return false;
	}
	
	
	public void setUISearchParameters(String searchCarrier, String searchOrigin, String searchDest, String searchCMP,
			String searchFCC, String searchresulBC, String orValue, String fareSource, String fareConstuction) throws InterruptedException {

		Log.info("Setting Frame List Search options");

		// Base Carrier
		if (searchCarrier != null) {
			clickElement(carrier);
			carrier.sendKeys(searchCarrier);
			Thread.sleep(sleep);
		}

		// Origin
		if (searchOrigin != null) {
			clickElement(origin);
			origin.sendKeys(searchOrigin);
			Thread.sleep(sleep);
		}

		// Destination
		if (searchDest != null) {
			clickElement(destination);
			destination.sendKeys(searchDest);
			Thread.sleep(sleep);
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
			FCC.sendKeys(searchFCC);
		// Resulting BC
		if (searchresulBC != null)
			BC.sendKeys(searchresulBC);
		Log.info("Search Criterion Saved");
	}
	
	public int checkDBFaresAirportAirport(String carrier, String origin, String destination, String cmp,
			String FCC, String BC, String orValue, String fareSource, String fareConstuction) throws SQLException, ClassNotFoundException
	{
		
		Map<Integer,String> queryParameters = new HashMap<Integer,String>();
		DBConnectionHandler db = new DBConnectionHandler();
		Helper helper = new Helper();
		
		String query = "select count(*) from NETPRICE.Fare where effective_date < sysdate and discon_date > sysdate and substr(fareclass_co,1,1) in (select BOOKING_CLASS from NETPRICE.compartment_coding where carrier_co = 'LH' and COMPARTMENT = 'Y') ";
		if (nvl(origin))
			query = query + " and origin_co in ?";
		if (nvl(destination))
			query = query + " and destination_co in ?";
		if(nvl(orValue))
			query = query + " and ONEWAY_ROUND_IND in ?";
		if (nvl(carrier))
			query += " and carrier_co=?";
		if (nvl(FCC))
			query += " and FARECLASS_CO = ?";
		if (nvl(BC))
			query += " and BOOKING_CLASS = ?";

		String compDBSearch = null;
		if (cmp != null && cmp.trim().length() > 0) {
			compDBSearch = cmp.replaceAll("\\s", "").equalsIgnoreCase("First") ? "F"
					: cmp.replaceAll("\\s", "").equalsIgnoreCase("Business") ? "C"
							: cmp.replaceAll("\\s", "").equalsIgnoreCase("PremiumEconomy") ? "W" : "Y";
		}

		// Check Origin and Destination. If they are not at airport level then call
		// helper function to do so

		int parameterCounter = 1;
		if (nvl(origin)) {
			queryParameters.put(parameterCounter, helper.checkAndNormalizeToAirportCode(origin));
			parameterCounter++;
		}

		if (nvl(destination)) {
			queryParameters.put(parameterCounter, helper.checkAndNormalizeToAirportCode(destination));
			parameterCounter++;
		}
		
		if(nvl(orValue)) {
			queryParameters.put(parameterCounter, helper.getCommaSeperatedValues(Integer.parseInt(orValue)));
			parameterCounter++;
		}

		if (nvl(carrier)) {
			queryParameters.put(parameterCounter, carrier.trim().length() == 0 ? "%" : carrier);
			parameterCounter++;
		}

		if (nvl(compDBSearch)) {
			queryParameters.put(parameterCounter, cmp.trim().length() == 0 ? "%" : compDBSearch);
			parameterCounter++;
		}

		if (nvl(FCC)) {
			queryParameters.put(parameterCounter, FCC.trim().length() == 0 ? "%" : FCC + "%");
			parameterCounter++;
		}

		if (nvl(BC)) {
			queryParameters.put(parameterCounter, BC.trim().length() == 0 ? "%" : BC);
		}

		Log.info("Total Search Parameter Count: " + (parameterCounter - 1));

		int DBRecordCount = db.getDBRecordCount(query, queryParameters).intValue();
		Log.info("Database Query Response:" + DBRecordCount);
		return DBRecordCount;
		
	}
	
	public void createFrames()
	{
		clickElement(createButton);
	}
	
	public boolean nvl(String searchElement) {
		if (searchElement != null && searchElement.trim().length() > 0)
			return true;
		return false;

	}
	

}
