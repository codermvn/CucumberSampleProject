package com.lh.test.pages;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.lh.test.OBR.Helper;
import com.lh.test.framework.annotations.IdentifyBy;
import com.lh.test.framework.application.BasePage;
import com.lh.test.framework.application.helpers.LocatorTypes;
import com.lh.test.framework.configuration.Configuration;
import com.lh.test.framework.handlers.DBConnectionHandler;
import com.lh.test.model.FrameListFields;
import com.lh.test.model.FramePOJO;
import au.com.bytecode.opencsv.CSVReader;

public class FrameEditPage extends BasePage {
	@IdentifyBy(locator = LocatorTypes.CSS, identifier = "div.MuiToolbar-root.MuiToolbar-regular.jss106.MuiToolbar-gutters > span.jss107 > strong > span", attribute = "text", value = "Frame edit")
	Logger Log = Logger.getLogger(FrameEditPage.class.getName());
	FramePOJO framePojo = new FramePOJO();

	@FindBy(css = "input[id='downshift-21-input']") 
	private WebElement poc;		

	@FindBy(css = "span[qa-id='frames-bulk-edit-header-cell-fd_sub_category'] select")
	private WebElement subCategory;     
	
    @FindBy(css = "span[qa-id='frames-bulk-edit-header-cell-fd_sub_category'] select option[value ='A']")
    private WebElement subCategoryOptionA;
	
	@FindBy(css = "span[qa-id='frames-bulk-edit-header-cell-fd_filing_method'] select")
	private WebElement filingMethodSelector;

	@FindBy(css = "#downshift-20-input") //downshift-20-input
	private WebElement cxr;

	@FindBy(css = "span[qa-id='frames-bulk-edit-header-cell-bd_source'] select")
	private WebElement source;
	
	@FindBy(css = "input[id='downshift-22-input']") //downshift-22-input
	private WebElement origin;

	@FindBy(css = "input[id='downshift-23-input']") //downshift-23-input
	private WebElement destination;

	@FindBy(css = "span[qa-id='frames-bulk-edit-header-cell-bd_compartment'] select")
	private WebElement compartment;		

	@FindBy(css = "span[qa-id='frames-bulk-edit-header-cell-bd_wildcard_fcc'] input")
	private WebElement wildCardFcc;

	@FindBy(css = "span[qa-id='frames-bulk-edit-header-cell-bd_currency_code'] input")
	private WebElement baseCurrency;

	@FindBy(css = "span[qa-id='frames-bulk-edit-header-cell-bd_oneway_roundtrip_indicator'] select")
	private WebElement OorR;

	@FindBy(css = "span[qa-id='frames-bulk-edit-header-cell-bd_routing_code'] input")
	private WebElement baseRoutingCode;

	@FindBy(css = "span[qa-id='frames-bulk-edit-header-cell-bd_tariff'] input")
	private WebElement tariff;

	@FindBy(css = "span[qa-id='frames-bulk-edit-header-cell-bd_maximum_relative_corporate_discount'] input")
	private WebElement maxPercentageDiscount;

	@FindBy(css = "span[qa-id='frames-bulk-edit-header-cell-bd_maximum_absolute_corporate_discount'] input")
	private WebElement maxAbsDiscount;

	@FindBy(css = "span[qa-id='frames-bulk-edit-header-cell-bd_maximum_absolute_corporate_discount_currency_code'] input")
	private WebElement maxAbsDiscountCur;

	@FindBy(css = "span[qa-id='frames-bulk-edit-header-cell-rfd_fare_amount'] input")
	private WebElement resultingAmt;

	@FindBy(css = "input[id='downshift-24-input']")
	private WebElement resultingCXR;

	@FindBy(css = "span[qa-id='frames-bulk-edit-header-cell-rfd_booking_class'] input")
	private WebElement resultingBCC;

	@FindBy(css = "span[qa-id='frames-bulk-edit-header-cell-rfd_fcc'] input")
	private WebElement resultingFCC;
	
	@FindBy(css = "span[qa-id='frames-bulk-edit-header-cell-rfd_rule_code'] input")
	private WebElement ruleCode;	
	
	@FindBy(css = "span[qa-id='frames-bulk-edit-header-cell-rfd_corporate_fcc_indicator'] select")
	private WebElement corporateFCCIndicator;	
	
	@FindBy(css = "span[qa-id='frames-bulk-edit-header-cell-rfd_midweek_indicator'] select")
	private WebElement midWeekIndicator;	
	
	@FindBy(css = "span[qa-id='frames-bulk-edit-header-cell-rfd_seasonal_indicator'] select")
	private WebElement seasonalIndicator;	
	
	@FindBy(css = "span[qa-id='frames-bulk-edit-header-cell-rfd_routing_code'] input")
	private WebElement routingCode;	
		
	@FindBy(css = "span[qa-id='frames-bulk-edit-header-cell-tv_travel_from'] input")
	private WebElement travelFromDate;
	
	@FindBy(css = "span[qa-id='frames-bulk-edit-header-cell-tv_travel_to'] input")
	private WebElement travelToDate;
	
	@FindBy(css = "span[qa-id='frames-bulk-edit-header-cell-fv_frame_valid_from'] input")
	private WebElement frameFromDate;
	
	@FindBy(css = "span[qa-id='frames-bulk-edit-header-cell-fv_frame_valid_to'] input")
	private WebElement frameToDate;	
	
//	@FindBy(css = "div[qa-id ='frames-rows-container']")
//	private WebElement errorSection;

	@FindBy(css = "div[qa-id ='frames-rows-container']") //>div
	private WebElement scroll;
	
	@FindBy(css = "button[qa-id='frames-save-bulk-edit-btn']")
	private WebElement saveButton;
	
	@FindBy(css = "button[qa-id='frames-bulk-edit-dialog-ok-btn']")
	private WebElement okButton;	 
	
	@FindBy(css = "span[qa-id ='frames-bulk-edit-header-cell-rfd_rule_code'] input")
    private WebElement rule;

	@FindBy(css = "button[qa-id='frames-close-bulk-edit-btn']")
    private WebElement Editframe;

	@FindBy(css = "span[qa-id='frames-rfd_rule_code-header-tooltip']")
	private WebElement ruleHeader;	
	
	@FindBy(css = "div[qa-id = 'frames-bulk-edit-dialog-summary']")
	private WebElement frameEditDialogSummary;
	
	

	Map<String,FrameListFields> linkedHMNonEditableFields = null;


	public FrameEditPage() {
		PageFactory.initElements(driver, this);
	}


	public String verifyNonEditableFields(String filingMethod, int rowCount) {
		String result = null;

		List<Boolean> lstTestStepResult = new ArrayList<>();
		Map<String,String> fieldAndValueMap = getTestDataForNonEditableFields(filingMethod);
		if(filingMethod.isEmpty()) {
			result = verifyGenericNonEditableFields();
		}
		else {		
		List<FramePOJO> framePojoLst = new ArrayList<FramePOJO>();
		Map<String, String> mapnonEditableFields = getnefields(filingMethod);
		
		if(filingMethod.equalsIgnoreCase("CAT25")) {
		for(Map.Entry<String, String> entry : fieldAndValueMap.entrySet())
		{
			FrameListFields frameAttribute = linkedHMNonEditableFields.get(entry.getKey());
			//frameAttribute.setFieldName(entry.getKey());
			scrollPage(frameAttribute.getNoOfScroll(), Keys.RIGHT);
			WebElement webElement = driver.findElement(By.cssSelector(frameAttribute.getWebElement()));
			webElement.sendKeys(entry.getValue());
			maxPercentageDiscount.click();
		}
		
		framePojoLst = getAllRowsFromFrameEditScreen(rowCount);		
		Boolean teststeppresult = true;
		int index = 1;
		for(FramePOJO framePOJOrows : framePojoLst ) {
		if(!(((framePOJOrows.getCurrency_co().equals(fieldAndValueMap.get("BaseCurrency")) && linkedHMNonEditableFields.get("BaseCurrency").isEditable()))|| (!(framePOJOrows.getCurrency_co().equals(fieldAndValueMap.get("BaseCurrency")) && !linkedHMNonEditableFields.get("BaseCurrency").isEditable()))))
		{
			teststeppresult= false;
			Log.info(result = " Row: "+index+ " -- attribute: BaseCurrency should be not be editable - check in UI by selecting "+filingMethod);
		}
		else {
			teststeppresult= false;
			Log.info(result = " Row: "+index+ " -- attribute: BaseCurrency is noneditable as expected for "+filingMethod);
		}
		
		if(!(((framePOJOrows.getMax_absolute_corp_discount().equals(fieldAndValueMap.get("MaxAbsDiscount")) && linkedHMNonEditableFields.get("MaxAbsDiscount").isEditable()))|| (!(framePOJOrows.getCurrency_co().equals(fieldAndValueMap.get("MaxAbsDiscount")) && !linkedHMNonEditableFields.get("MaxAbsDiscount").isEditable()))))
		{
			teststeppresult= false;
			Log.info(result = " Row: "+index+ " -- attribute: MaxAbsDiscount should be not be editable - check in UI by selecting "+filingMethod);
		}
		else {
			teststeppresult= false;
			Log.info(result = " Row: "+index+ " -- attribute: MaxAbsDiscount is noneditable as expected for "+filingMethod);
		}
		if(!(((framePOJOrows.getMax_abs_corp_disc_currency_co().equals(fieldAndValueMap.get("MaxAbsDiscountCur")) && linkedHMNonEditableFields.get("MaxAbsDiscountCur").isEditable()))|| (!(framePOJOrows.getCurrency_co().equals(fieldAndValueMap.get("MaxAbsDiscountCur")) && !linkedHMNonEditableFields.get("MaxAbsDiscountCur").isEditable()))))
		{
			teststeppresult= false;
			Log.info(result = " Row: "+index+ " -- attribute: MaxAbsDiscountCur should be not be editable - check in UI by selecting "+filingMethod);
		}
		else {
			teststeppresult= false;
			Log.info(result = " Row: "+index+ " -- attribute: MaxAbsDiscountCur is noneditable as expected for "+filingMethod);
		}
		if(!(((framePOJOrows.getResult_amount().equals(fieldAndValueMap.get("ResultingAmt")) && linkedHMNonEditableFields.get("ResultingAmt").isEditable()))|| (!(framePOJOrows.getCurrency_co().equals(fieldAndValueMap.get("ResultingAmt")) && !linkedHMNonEditableFields.get("ResultingAmt").isEditable()))))
		{
			teststeppresult= false;
			Log.info(result = " Row: "+index+ " -- attribute: ResultingAmt should be not be editable - check in UI by selecting "+filingMethod);
		}
		else {
			teststeppresult= false;
			Log.info(result = " Row: "+index+ " -- attribute: ResultingAmt->is noneditable as expected for "+filingMethod);
		}
		index++;
		}		
		}
		
		else if(filingMethod.equalsIgnoreCase("CAT25 abs")) {
		
		for(Map.Entry<String, String> entry : fieldAndValueMap.entrySet())
		{
			FrameListFields frameAttribute = linkedHMNonEditableFields.get(entry.getKey());
			scrollPage(frameAttribute.getNoOfScroll(), Keys.RIGHT);
			WebElement webElement = driver.findElement(By.cssSelector(frameAttribute.getWebElement()));
			webElement.sendKeys(entry.getValue());
			maxPercentageDiscount.click();
		}
		
		framePojoLst = getAllRowsFromFrameEditScreen(rowCount);		
		Boolean teststeppresult = true;
		int index = 1;
		
		for(FramePOJO framePOJOrows : framePojoLst ) {
				
			if(!(((framePOJOrows.getCurrency_co().equals(fieldAndValueMap.get("BaseCurrency")) && linkedHMNonEditableFields.get("BaseCurrency").isEditable()))|| (!(framePOJOrows.getCurrency_co().equals(fieldAndValueMap.get("BaseCurrency")) && !linkedHMNonEditableFields.get("BaseCurrency").isEditable()))))
			{
				teststeppresult= false;
				Log.info(result = " Row: "+index+ " -- attribute: BaseCurrency should be not be editable - check in UI by selecting "+filingMethod);
			}
			else {
				teststeppresult= false;
				Log.info(result = " Row: "+index+ " -- attribute: BaseCurrency is noneditable as expected for "+filingMethod);
			}
			if(!(((framePOJOrows.getMax_relative_corp_discount().equals(fieldAndValueMap.get("MaxPercentageDiscount")) && linkedHMNonEditableFields.get("MaxPercentageDiscount").isEditable()))|| (!(framePOJOrows.getCurrency_co().equals(fieldAndValueMap.get("MaxPercentageDiscount")) && !linkedHMNonEditableFields.get("MaxPercentageDiscount").isEditable()))))
			{
				teststeppresult= false;
				Log.info(result = " Row: "+index+ " -- attribute: MaxPercentageDiscount should be not be editable - check in UI by selecting "+filingMethod);
			}
			else {
				teststeppresult= false;
				Log.info(result = " Row: "+index+ " -- attribute: MaxPercentageDiscount is noneditable as expected for "+filingMethod);
			}
			if(!(((framePOJOrows.getResult_amount().equals(fieldAndValueMap.get("ResultingAmt")) && linkedHMNonEditableFields.get("ResultingAmt").isEditable()))|| (!(framePOJOrows.getCurrency_co().equals(fieldAndValueMap.get("ResultingAmt")) && !linkedHMNonEditableFields.get("ResultingAmt").isEditable()))))
			{
				teststeppresult= false;
				Log.info(result = " Row: "+index+ " -- attribute: ResultingAmt should be not be editable - check in UI by selecting "+filingMethod);
			}
			else {
				teststeppresult= false;
				Log.info(result = " Row: "+index+ " -- attribute: ResultingAmt is noneditable as expected for "+filingMethod);
			}
			index++;
			}
		}
		
		
		else if(filingMethod.equalsIgnoreCase("CAT35 fix")) {
			
		for(Map.Entry<String, String> entry : fieldAndValueMap.entrySet())
		{
			FrameListFields frameAttribute = linkedHMNonEditableFields.get(entry.getKey());
			scrollPage(frameAttribute.getNoOfScroll(), Keys.RIGHT);
			WebElement webElement = driver.findElement(By.cssSelector(frameAttribute.getWebElement()));
			webElement.sendKeys(entry.getValue());
			maxPercentageDiscount.click();
		}
		
		framePojoLst = getAllRowsFromFrameEditScreen(rowCount);	
		Boolean teststeppresult = true;
		int index = 1;
		
		
		for(FramePOJO framePOJOrows : framePojoLst ) {		
				if(!(((framePOJOrows.getSource().equals(fieldAndValueMap.get("Source")) && linkedHMNonEditableFields.get("Source").isEditable()))|| (!(framePOJOrows.getCurrency_co().equals(fieldAndValueMap.get("BaseCurrency")) && !linkedHMNonEditableFields.get("BaseCurrency").isEditable()))))
				{
					teststeppresult= false;
					Log.info(result = " Row: "+index+ " -- attribute: Source should be not be editable - check in UI by selecting "+filingMethod);
				}
				else {
					teststeppresult= true;
					Log.info(result = " Row: "+index+ " -- attribute: Source is noneditable as expected for "+filingMethod);
				}	
				if(!(((framePOJOrows.getMax_relative_corp_discount().equals(fieldAndValueMap.get("MaxPercentageDiscount")) && linkedHMNonEditableFields.get("MaxPercentageDiscount").isEditable()))|| (!(framePOJOrows.getCurrency_co().equals(fieldAndValueMap.get("MaxPercentageDiscount")) && !linkedHMNonEditableFields.get("MaxPercentageDiscount").isEditable()))))
				{
					teststeppresult= false;
					Log.info(result = " Row: "+index+ " -- attribute: MaxPercentageDiscount should be not be editable - check in UI by selecting "+filingMethod);
				}
				else {
					teststeppresult= true;
					Log.info(result = " Row: "+index+ " -- attribute: MaxPercentageDiscount->is noneditable as expected for "+filingMethod);
				}
				if(!(((framePOJOrows.getMax_absolute_corp_discount().equals(fieldAndValueMap.get("MaxAbsDiscount")) && linkedHMNonEditableFields.get("MaxAbsDiscount").isEditable()))|| (!(framePOJOrows.getCurrency_co().equals(fieldAndValueMap.get("MaxAbsDiscount")) && !linkedHMNonEditableFields.get("MaxAbsDiscount").isEditable()))))
				{
					teststeppresult= false;
					Log.info(result = " Row: "+index+ " -- attribute: MaxAbsDiscount should be not be editable - check in UI by selecting "+filingMethod);
				}
				else {
					teststeppresult= true;
					Log.info(result = " Row: "+index+ " -- attribute: MaxAbsDiscount is noneditable as expected for "+filingMethod);
				}
				if(!(((framePOJOrows.getMax_abs_corp_disc_currency_co().equals(fieldAndValueMap.get("MaxAbsDiscountCur")) && linkedHMNonEditableFields.get("MaxAbsDiscountCur").isEditable()))|| (!(framePOJOrows.getCurrency_co().equals(fieldAndValueMap.get("MaxAbsDiscountCur")) && !linkedHMNonEditableFields.get("MaxAbsDiscountCur").isEditable()))))
				{
					teststeppresult= false;
					Log.info(result = " Row: "+index+ " -- attribute: MaxAbsDiscountCur should be not be editable - check in UI by selecting "+filingMethod);
				}
				else {
					teststeppresult= true;
					Log.info(result = " Row: "+index+ " -- attribute: MaxAbsDiscountCur is noneditable as expected for "+filingMethod);
				}
				index++;
		}
			
		}
		
		else if(filingMethod.equalsIgnoreCase("CAT35 float")) {
			
		for(Map.Entry<String, String> entry : fieldAndValueMap.entrySet())
		{
			FrameListFields frameAttribute = linkedHMNonEditableFields.get(entry.getKey());
			scrollPage(frameAttribute.getNoOfScroll(), Keys.RIGHT);
			WebElement webElement = driver.findElement(By.cssSelector(frameAttribute.getWebElement()));
			webElement.sendKeys(entry.getValue());
			maxPercentageDiscount.click();
		}
		
		framePojoLst = getAllRowsFromFrameEditScreen(rowCount);
		Boolean teststeppresult = true;
		int index = 1;		
		
		for(FramePOJO framePOJOrows : framePojoLst ) {
		
			if(!(((framePOJOrows.getSource().equals(fieldAndValueMap.get("Source")) && linkedHMNonEditableFields.get("Source").isEditable()))|| (!(framePOJOrows.getCurrency_co().equals(fieldAndValueMap.get("BaseCurrency")) && !linkedHMNonEditableFields.get("BaseCurrency").isEditable()))))
			{
				teststeppresult= false;
				Log.info(result = " Row: "+index+ " -- attribute: Source should be not be editable - check in UI by selecting "+filingMethod);
			}
			else {
				teststeppresult= true;
				Log.info(result = " Row: "+index+ " -- attribute: Source is noneditable as expected for "+filingMethod);
			}
			if(!(((framePOJOrows.getMax_absolute_corp_discount().equals(fieldAndValueMap.get("MaxAbsDiscount")) && linkedHMNonEditableFields.get("MaxAbsDiscount").isEditable()))|| (!(framePOJOrows.getCurrency_co().equals(fieldAndValueMap.get("MaxAbsDiscount")) && !linkedHMNonEditableFields.get("MaxAbsDiscount").isEditable()))))
			{
				teststeppresult= false;
				Log.info(result = " Row: "+index+ " -- attribute: MaxAbsDiscount should be not be editable - check in UI by selecting "+filingMethod);
			}
			else {
				teststeppresult= true;
				Log.info(result = " Row: "+index+ " -- attribute: MaxAbsDiscount is noneditable as expected for "+filingMethod);
			}
			if(!(((framePOJOrows.getMax_abs_corp_disc_currency_co().equals(fieldAndValueMap.get("MaxAbsDiscountCur")) && linkedHMNonEditableFields.get("MaxAbsDiscountCur").isEditable()))|| (!(framePOJOrows.getCurrency_co().equals(fieldAndValueMap.get("MaxAbsDiscountCur")) && !linkedHMNonEditableFields.get("MaxAbsDiscountCur").isEditable()))))
			{
				teststeppresult= false;
				Log.info(result = " Row: "+index+ " -- attribute: MaxAbsDiscountCur should be not be editable - check in UI by selecting "+filingMethod);
			}
			else {
				teststeppresult= true;
				Log.info(result = " Row: "+index+ " -- attribute: MaxAbsDiscountCur is noneditable as expected for "+filingMethod);
			}
			if(!(((framePOJOrows.getResult_amount().equals(fieldAndValueMap.get("ResultingAmt")) && linkedHMNonEditableFields.get("ResultingAmt").isEditable()))|| (!(framePOJOrows.getCurrency_co().equals(fieldAndValueMap.get("ResultingAmt")) && !linkedHMNonEditableFields.get("ResultingAmt").isEditable()))))
			{
				teststeppresult= false;
				Log.info(result = " Row: "+index+ " -- attribute: ResultingAmt should be not be editable - check in UI by selecting "+filingMethod);
			}
			else {
				teststeppresult= false;
				Log.info(result = " Row: "+index+ " -- attribute: ResultingAmt->is noneditable as expected for "+filingMethod);
			}
			index++;
		}
		
		}
		}
		return result;
	}
	
	private String verifyGenericNonEditableFields() {
		String result = null;

		Map<String, String> mapNonEditableFields = new HashMap<String, String>();
		 String posType = "span[qa-id='frames-bulk-edit-header-cell-bd_pos_type']>div input";
			String originType = "span[qa-id='frames-bulk-edit-header-cell-bd_origin_type'] input";
			String oc = "span[qa-id='frames-bulk-edit-header-cell-bd_origin_country'] input";
			String destnType = "span[qa-id='frames-bulk-edit-header-cell-bd_destination_type'] input";
			String dc = "span[qa-id='frames-bulk-edit-header-cell-bd_destination_country'] input";
			String fcc = "span[qa-id='frames-bulk-edit-header-cell-bd_fare_class_code'] input";
			String fareAmt = "span[qa-id= 'frames-bulk-edit-header-cell-bd_fare_amount'] input"; 

			mapNonEditableFields.put("posType", posType);
			mapNonEditableFields.put("originType", originType);
			mapNonEditableFields.put("oc", oc);
			mapNonEditableFields.put("destnType", destnType);
			mapNonEditableFields.put("dc", dc);
			mapNonEditableFields.put("fcc", fcc);
			mapNonEditableFields.put("fareAmt", fareAmt);
			for(Map.Entry<String,String> entryNE : mapNonEditableFields.entrySet()) {
				 List<WebElement> webElement = driver.findElements(By.cssSelector(entryNE.getValue()));
				 if(webElement.size() == 0) 
						Log.info(result = "This " + entryNE.getKey() + "->is noneditable as expected");
					else 
						Log.info(result = "This " + entryNE.getKey() + "->is noneditable as expected");	
			}
		return result;
	}

	public Map<String, String> getnefields(String filingMethod) {

		Map<String, String> mapNonEditableFields = new HashMap<String, String>();
		if(!filingMethod.isEmpty()) {
			String nonEditSource = "span[qa-id='frames-bulk-edit-header-cell-bd_source'] select option[value = 'ATPCO-C-PRIV']";
			String nonEditbaseCurrency = "span[qa-id='frames-bulk-edit-header-cell-bd_currency_code'] input"; 
			String nonEditwildCardFcc = "span[qa-id='frames-bulk-edit-header-cell-bd_wildcard_fcc'] input"; 
			String nonEditmaxPercentageDiscount = "span[qa-id='frames-bulk-edit-header-cell-bd_maximum_relative_corporate_discount'] input";
			String nonEditmaxAbsDiscount = "span[qa-id='frames-bulk-edit-header-cell-bd_maximum_absolute_corporate_discount'] input";
			String nonEditmaxAbsDiscountCur = "span[qa-id='frames-bulk-edit-header-cell-bd_maximum_absolute_corporate_discount_currency_code'] input";
			String nonEditresultingAmt = "span[qa-id='frames-bulk-edit-header-cell-rfd_fare_amount'] input";

			if(filingMethod.equalsIgnoreCase("CAT25")) { 
				linkedHMNonEditableFields = new LinkedHashMap<String,FrameListFields>();
				linkedHMNonEditableFields.put("BaseCurrency",new FrameListFields(nonEditbaseCurrency, 3,false));
				linkedHMNonEditableFields.put("MaxAbsDiscount", new FrameListFields(nonEditmaxAbsDiscount, 3,false));
				linkedHMNonEditableFields.put("MaxAbsDiscountCur",new FrameListFields( nonEditmaxAbsDiscountCur, 3,false));
				linkedHMNonEditableFields.put("ResultingAmt",new FrameListFields( nonEditresultingAmt, 3,false));

			}
			else if(filingMethod.equalsIgnoreCase("CAT25 abs")) { 
				linkedHMNonEditableFields = new LinkedHashMap<String,FrameListFields>();
				linkedHMNonEditableFields.put("BaseCurrency",new FrameListFields(nonEditbaseCurrency, 3,false));
				linkedHMNonEditableFields.put("MaxPercentageDiscount", new FrameListFields(nonEditmaxPercentageDiscount, 3,false));
				linkedHMNonEditableFields.put("ResultingAmt",new FrameListFields( nonEditresultingAmt, 3,false));
			}
			else if(filingMethod.equalsIgnoreCase("Cat35 fix")) { 
				linkedHMNonEditableFields = new LinkedHashMap<String,FrameListFields>();
				linkedHMNonEditableFields.put("Source",new FrameListFields(nonEditSource, 3,false));
				linkedHMNonEditableFields.put("WildCardFcc", new FrameListFields(nonEditwildCardFcc, 3,false));
				linkedHMNonEditableFields.put("MaxPercentageDiscount",new FrameListFields( nonEditmaxPercentageDiscount, 3,false));
				linkedHMNonEditableFields.put("MaxAbsDiscount", new FrameListFields(nonEditmaxAbsDiscount, 3,false));
				linkedHMNonEditableFields.put("MaxAbsDiscountCur",new FrameListFields( nonEditmaxAbsDiscountCur, 3,false));
			}
			else if(filingMethod.equalsIgnoreCase("CAT35 float")) { 
				linkedHMNonEditableFields = new LinkedHashMap<String,FrameListFields>();
				linkedHMNonEditableFields.put("BaseCurrency",new FrameListFields(nonEditbaseCurrency, 3,false));
				linkedHMNonEditableFields.put("MaxAbsDiscount", new FrameListFields(nonEditmaxAbsDiscount, 3,false));
				linkedHMNonEditableFields.put("MaxAbsDiscountCur",new FrameListFields( nonEditmaxAbsDiscountCur, 3,false));
				linkedHMNonEditableFields.put("ResultingAmt",new FrameListFields( nonEditresultingAmt, 3,false));
			}
		}

		return mapNonEditableFields;

	}

	public List<FramePOJO> getAllRowsFromFrameEditScreen(int rowCount) {

		List<FramePOJO> framePojoLst = new ArrayList<FramePOJO>();

		for (int index = 0; index < rowCount; index++) {
			WebElement weScroll = driver.findElement(By.cssSelector("div[qa-id ='frames-rows-container']"));
			if(!weScroll.getAttribute("style").contains("overflow: auto hidden;") && index>10) {
				Log.info("scroll");				
				scrollPage(1, Keys.ARROW_DOWN);
			}
			framePojo.setPos(driver.findElement(By.cssSelector("div[qa-id='frames-fd_point_of_sale-cell-"+index+"']>span")).getText());
			framePojo.setSub_category(driver.findElement(By.cssSelector("div[qa-id='frames-fd_sub_category-cell-"+index+"']>span")).getText());
			framePojo.setFiling_method(driver.findElement(By.cssSelector("div[qa-id='frames-fd_filing_method-cell-"+index+"']>span")).getText());
			framePojo.setCxr(driver.findElement(By.cssSelector("div[qa-id='frames-bd_carrier_code-cell-"+index+"']>span")).getText());			
			framePojo.setSource(driver.findElement(By.cssSelector("div[qa-id='frames-bd_source-cell-"+index+"']>span")).getText());
			framePojo.setOrigin_co(driver.findElement(By.cssSelector("div[qa-id='frames-bd_origin_code-cell-"+index+"']>span")).getText());
			framePojo.setDestination_co(driver.findElement(By.cssSelector("div[qa-id='frames-bd_destination_code-cell-"+index+"']>span")).getText());
			framePojo.setCompartment(driver.findElement(By.cssSelector("div[qa-id='frames-bd_compartment-cell-"+index+"']>span")).getText());
			framePojo.setFcc_wildcard(driver.findElement(By.cssSelector("div[qa-id='frames-bd_wildcard_fcc-cell-"+index+"']>span")).getText());
			framePojo.setCurrency_co(driver.findElement(By.cssSelector("div[qa-id='frames-bd_currency_code-cell-"+index+"']>span")).getText());
			framePojo.setOneway_round_ind(driver.findElement(By.cssSelector("div[qa-id='frames-bd_oneway_roundtrip_indicator-cell-"+index+"']>span")).getText());
			framePojo.setRouting_co(driver.findElement(By.cssSelector("div[qa-id='frames-bd_routing_code-cell-"+index+"']>span")).getText());
			framePojo.setTariff_co(driver.findElement(By.cssSelector("div[qa-id='frames-bd_tariff-cell-"+index+"']>span")).getText());
			framePojo.setMax_relative_corp_discount(driver.findElement(By.cssSelector("div[qa-id='frames-bd_maximum_relative_corporate_discount-cell-"+index+"']>span")).getText());
			framePojo.setMax_absolute_corp_discount(driver.findElement(By.cssSelector("div[qa-id='frames-bd_maximum_absolute_corporate_discount-cell-"+index+"']>span")).getText());
			framePojo.setMax_abs_corp_disc_currency_co(driver.findElement(By.cssSelector("div[qa-id='frames-bd_maximum_absolute_corporate_discount_currency_code-cell-"+index+"']>span")).getText());
			framePojo.setResult_amount(driver.findElement(By.cssSelector("div[qa-id='frames-rfd_fare_amount-cell-"+index+"']>span")).getText());
			framePojo.setResult_carrier_co(driver.findElement(By.cssSelector("div[qa-id='frames-rfd_carrier_code-cell-"+index+"']>span")).getText());
			framePojo.setResult_bcc(driver.findElement(By.cssSelector("div[qa-id='frames-rfd_booking_class-cell-"+index+"']>span")).getText());
			framePojo.setResult_fcc(driver.findElement(By.cssSelector("div[qa-id='frames-rfd_fcc-cell-"+index+"']>span")).getText());
			framePojo.setRule_co(driver.findElement(By.cssSelector("div[qa-id='frames-rfd_rule_code-cell-"+index+"']>span")).getText());
			framePojo.setCorporate_fcc_indicator(driver.findElement(By.cssSelector("div[qa-id='frames-rfd_corporate_fcc_indicator-cell-"+index+"']>span")).getText());
			framePojo.setMidweek_weekend_indicator(driver.findElement(By.cssSelector("div[qa-id='frames-rfd_midweek_indicator-cell-"+index+"']>span")).getText());
			framePojo.setSeasonal_indicator(driver.findElement(By.cssSelector("div[qa-id='frames-rfd_seasonal_indicator-cell-"+index+"']>span")).getText());
			framePojo.setRouting_co(driver.findElement(By.cssSelector("div[qa-id='frames-rfd_routing_code-cell-"+index+"']>span")).getText());
			framePojo.setFirst_travel_date(driver.findElement(By.cssSelector("div[qa-id='frames-tv_travel_from-cell-"+index+"']>span")).getText());
			framePojo.setLast_travel_date(driver.findElement(By.cssSelector("div[qa-id='frames-tv_travel_to-cell-"+index+"']>span")).getText());
			framePojo.setValid_from(driver.findElement(By.cssSelector("div[qa-id='frames-fv_frame_valid_from-cell-"+index+"']>span")).getText());
			framePojo.setValid_to(driver.findElement(By.cssSelector("div[qa-id='frames-fv_frame_valid_to-cell-"+index+"']>span")).getText());
			framePojoLst.add(framePojo);
		}
		return framePojoLst;			
	}
	public Boolean validateEditableFields(String filingMethod, int rowCount) throws IOException, InterruptedException {	
		List<FramePOJO> lstEditfields = getTestDataForEditableFields(filingMethod);
		//Actions actions = new Actions(driver);
		Boolean teststeppresult = true;
		for(FramePOJO editFramePOJO : lstEditfields) {
			//framePojo = lstEditField;
			poc.sendKeys(editFramePOJO.getPos());
			poc.sendKeys(Keys.ENTER);
			Thread.sleep(1000);
			waitForElement(subCategory);
			new Select (subCategory).selectByVisibleText(editFramePOJO.getSub_category());
			//Thread.sleep(1000);
			cxr.sendKeys(editFramePOJO.getCxr());
			cxr.sendKeys(Keys.ENTER);
			origin.sendKeys(editFramePOJO.getOrigin_co());
			origin.sendKeys(Keys.ENTER);
			destination.sendKeys(editFramePOJO.getDestination_co());
			destination.sendKeys(Keys.ENTER);
			//actions.contextClick(compartment).perform();
			Thread.sleep(1000);
			waitForElement(compartment);
			new Select (compartment).selectByVisibleText(editFramePOJO.getCompartment());
	
			if(!filingMethod.equalsIgnoreCase("CAT35 fix")) {
				Thread.sleep(1000);
				waitForElement(source);
				new Select (source).selectByVisibleText(editFramePOJO.getSource());
				wildCardFcc.sendKeys(editFramePOJO.getFcc_wildcard());
				wildCardFcc.sendKeys(Keys.ENTER);
			}
			else {
				baseCurrency.sendKeys(editFramePOJO.getCurrency_co());
			}
			Thread.sleep(1000);
			waitForElement(OorR);
			new Select (OorR).selectByVisibleText(editFramePOJO.getOneway_round_ind());
			baseRoutingCode.sendKeys(editFramePOJO.getBase_routing_co());
			tariff.sendKeys(editFramePOJO.getTariff_co());
			
			scrollPage(6, Keys.RIGHT);
	
			if(filingMethod.equalsIgnoreCase("CAT25") || filingMethod.equalsIgnoreCase("CAT35 float")) {
			maxPercentageDiscount.sendKeys(editFramePOJO.getMax_relative_corp_discount());
			scrollPage(4, Keys.RIGHT);
			}
	
			if(filingMethod.equalsIgnoreCase("CAT25 abs")) {
				maxAbsDiscount.sendKeys(editFramePOJO.getMax_absolute_corp_discount());
				scrollPage(3, Keys.RIGHT);
				maxAbsDiscountCur.sendKeys(editFramePOJO.getMax_abs_corp_disc_currency_co());
				scrollPage(3, Keys.RIGHT);				
				resultingFCC.sendKeys(editFramePOJO.getResult_fcc());
			}
			scrollPage(9, Keys.RIGHT);
			resultingCXR.sendKeys(editFramePOJO.getResult_carrier_co());
			resultingCXR.sendKeys(Keys.ENTER);
	
			if(filingMethod.equalsIgnoreCase("CAT35 fix") || filingMethod.equalsIgnoreCase("CAT35 float")) {
			resultingAmt.sendKeys(editFramePOJO.getResult_amount());
			scrollPage(3, Keys.RIGHT);
			}
			
			resultingBCC.sendKeys(editFramePOJO.getResult_bcc());
			scrollPage(5, Keys.RIGHT);
			
			if(!filingMethod.equalsIgnoreCase("CAT25 abs")) {
			 scrollPage(10, Keys.RIGHT);
			 ruleCode.sendKeys(editFramePOJO.getRule_co());
			 routingCode.sendKeys(editFramePOJO.getRouting_co());
			}

			scrollPage(20, Keys.RIGHT);
		    travelFromDate.sendKeys(editFramePOJO.getFirst_travel_date());
		    travelToDate.sendKeys(editFramePOJO.getLast_travel_date());
		    frameFromDate.sendKeys(editFramePOJO.getValid_from());
			frameToDate.sendKeys(editFramePOJO.getValid_to());
			frameToDate.sendKeys(Keys.TAB);
			
			List<FramePOJO> framePojoLst = new ArrayList<FramePOJO>();
//			if(rowCount>13) {
//				scrollPage(5, Keys.ARROW_DOWN);
//			}
			framePojoLst = getAllRowsFromFrameEditScreen(rowCount);	
			int index = 0;
			for(FramePOJO checkeditFrame : framePojoLst) {						
					if(!editFramePOJO.getPos().isEmpty() && !editFramePOJO.getPos().equals(checkeditFrame.getPos())) {
						teststeppresult = false;
						Log.info(" Row: "+index+ " -- attribute: POS value: "+editFramePOJO.getPos()+" is not applied in grid for fillingMethod: "+filingMethod);
					}				
					
					if(!editFramePOJO.getSub_category().isEmpty() && !editFramePOJO.getSub_category().equals(checkeditFrame.getSub_category())) {
						teststeppresult = false;
						Log.info(" Row: "+index+ " -- attribute: Sub category value: "+editFramePOJO.getSub_category()+" is not applied in grid for fillingMethod: "+filingMethod);
					}
					
					if(!editFramePOJO.getOrigin_co().isEmpty() && !editFramePOJO.getOrigin_co().equals(checkeditFrame.getOrigin_co())) {
						teststeppresult = false;
						Log.info(" Row: "+index+ " -- attribute: Origin value: "+editFramePOJO.getOrigin_co()+" is not applied in grid for fillingMethod:: "+filingMethod);
					}
					
					if(!editFramePOJO.getDestination_co().isEmpty() && !editFramePOJO.getDestination_co().equals(checkeditFrame.getDestination_co())) {
						teststeppresult = false;
						Log.info(" Row: "+index+ " -- attribute: Destination value: "+editFramePOJO.getDestination_co()+" is not applied in grid for fillingMethod:: "+filingMethod);
					}
					
					if(!filingMethod.equalsIgnoreCase("CAT35 fix")) {		
						if(!editFramePOJO.getSource().isEmpty() && !editFramePOJO.getSource().equals(checkeditFrame.getSource())) {
							teststeppresult = false;
							Log.info(" Row: "+index+ " -- attribute: Source value: "+editFramePOJO.getSource()+" is not applied in grid for fillingMethod: "+filingMethod);
						}	
						if(!editFramePOJO.getFcc_wildcard().isEmpty() && !editFramePOJO.getFcc_wildcard().equals(checkeditFrame.getFcc_wildcard())) {
							teststeppresult = false;
							Log.info(" Row: "+index+ " -- attribute: wilcardFcc value: "+editFramePOJO.getFcc_wildcard()+" is not applied in grid for fillingMethod: "+filingMethod);
						}	
					}
					else {
						if(!editFramePOJO.getCurrency_co().isEmpty() && !editFramePOJO.getCurrency_co().equals(checkeditFrame.getCurrency_co())) {
							teststeppresult = false;
							Log.info(" Row: "+index+ " -- attribute: currency code value: "+editFramePOJO.getCurrency_co()+" is not applied in  for fillingMethod:: "+filingMethod);
						}
					}
					
					if(!editFramePOJO.getOneway_round_ind().isEmpty() && !editFramePOJO.getOneway_round_ind().equals(checkeditFrame.getOneway_round_ind())) {
						teststeppresult = false;
						Log.info(" Row: "+index+ " -- attribute: Oneway round trip request(OorR) value: "+editFramePOJO.getOneway_round_ind()+" is not applied in grid for fillingMethod: "+filingMethod);
					}
					
					if(!editFramePOJO.getRouting_co().isEmpty() && !editFramePOJO.getRouting_co().equals(checkeditFrame.getRouting_co())) {
						teststeppresult = false;
						Log.info(" Row: "+index+ " -- attribute: Routing code value: "+editFramePOJO.getRouting_co()+" is not applied in grid for fillingMethod: "+filingMethod);
					}
					
					if(!editFramePOJO.getTariff_co().isEmpty() && !editFramePOJO.getTariff_co().equals(checkeditFrame.getTariff_co())) {
						teststeppresult = false;
						Log.info(" Row: "+index+ " -- attribute: Tariff value: "+editFramePOJO.getTariff_co()+" is not applied in grid for fillingMethod: "+filingMethod);
					}

					if(filingMethod.equalsIgnoreCase("CAT25") || filingMethod.equalsIgnoreCase("CAT35 float")) {
						if(!editFramePOJO.getMax_relative_corp_discount().isEmpty() && !editFramePOJO.getMax_relative_corp_discount().equals(checkeditFrame.getMax_relative_corp_discount())) {
							teststeppresult = false;
							Log.info(" Row: "+index+ " -- attribute: Max percentage discount value: "+editFramePOJO.getMax_relative_corp_discount()+" is not applied in grid for fillingMethod: "+filingMethod);
						}
					}

					if(filingMethod.equalsIgnoreCase("CAT25 abs")) {
						if(!editFramePOJO.getMax_absolute_corp_discount().isEmpty() && !editFramePOJO.getMax_absolute_corp_discount().equals(checkeditFrame.getMax_absolute_corp_discount())) {
							teststeppresult = false;
							Log.info(" Row: "+index+ " -- attribute: Max absolute value: "+editFramePOJO.getMax_absolute_corp_discount()+" is not applied in grid for fillingMethod: "+filingMethod);
						}
						
						if(!editFramePOJO.getMax_abs_corp_disc_currency_co().isEmpty() && !editFramePOJO.getMax_abs_corp_disc_currency_co().equals(checkeditFrame.getMax_abs_corp_disc_currency_co())) {
							teststeppresult = false;
							Log.info(" Row: "+index+ " -- attribute: Max absolute currency value: "+editFramePOJO.getMax_abs_corp_disc_currency_co()+" is not applied in grid for fillingMethod: "+filingMethod);
						}						
						
						if(!editFramePOJO.getResult_fcc().isEmpty() && !editFramePOJO.getResult_fcc().equals(checkeditFrame.getResult_fcc())) {
							teststeppresult = false;
							Log.info(" Row: "+index+ " -- attribute: Resulting FCC value: "+editFramePOJO.getResult_fcc()+" is not applied in grid for fillingMethod: "+filingMethod);
						}
					}
					
					if(!editFramePOJO.getResult_carrier_co().isEmpty() && !editFramePOJO.getResult_carrier_co().equals(checkeditFrame.getResult_carrier_co())) {
						teststeppresult = false;
						Log.info(" Row: "+index+ " -- attribute: Resulting CXR value: "+editFramePOJO.getResult_carrier_co()+" is not applied in grid for fillingMethod: "+filingMethod);
					}
					
					if(filingMethod.equalsIgnoreCase("CAT35 fix") || filingMethod.equalsIgnoreCase("CAT35 float")) {
						if(!editFramePOJO.getResult_amount().isEmpty() && !editFramePOJO.getResult_amount().equals(checkeditFrame.getResult_amount())) {
							teststeppresult = false;
							Log.info(" Row: "+index+ " -- attribute: Resulting Amount value: "+editFramePOJO.getResult_amount()+" is not applied in grid for fillingMethod: "+filingMethod);
						}
					}
					
					if(!filingMethod.equalsIgnoreCase("CAT25 abs")) {
						if(!editFramePOJO.getRule_co().isEmpty() && !editFramePOJO.getRule_co().equals(checkeditFrame.getRule_co())) {
							teststeppresult = false;
							Log.info(" Row: "+index+ " -- attribute: Rule code value: "+editFramePOJO.getRule_co()+" is not applied in grid for fillingMethod: "+filingMethod);
						}
						
						if(!editFramePOJO.getRouting_co().isEmpty() && !editFramePOJO.getRouting_co().equals(checkeditFrame.getRouting_co())) {
							teststeppresult = false;
							Log.info(" Row: "+index+ " -- attribute: Routing code value: "+editFramePOJO.getRouting_co()+" is not applied in grid for fillingMethod: "+filingMethod);
						}
					}
					if(!editFramePOJO.getFirst_travel_date().isEmpty() && !editFramePOJO.getFirst_travel_date().equals(checkeditFrame.getFirst_travel_date())) {
						teststeppresult = false;
						Log.info(" Row: "+index+ " -- attribute: First Travel date value: "+editFramePOJO.getFirst_travel_date()+ " is not applied in grid for fillingMethod: "+filingMethod);
					}
					
					if(!editFramePOJO.getLast_travel_date().isEmpty() && !editFramePOJO.getLast_travel_date().equals(checkeditFrame.getLast_travel_date())) {
						teststeppresult = false;
						Log.info(" Row: "+index+ " -- attribute: Last Travel date value: " +editFramePOJO.getLast_travel_date()+ " is not applied in grid for fillingMethod: "+filingMethod);
					}

					if(!editFramePOJO.getValid_from().isEmpty() && !editFramePOJO.getValid_from().equals(checkeditFrame.getValid_from())) {
						teststeppresult = false;
						Log.info(" Row: "+index+ " -- attribute: Validity From date value: "+editFramePOJO.getValid_from() +" is not applied in grid for fillingMethod: "+filingMethod);
					}
					
					if(!editFramePOJO.getValid_to().isEmpty() && !editFramePOJO.getValid_to().equals(checkeditFrame.getValid_to())) {
						teststeppresult = false;
						Log.info(" Row: "+index+ " -- attribute: Validity To date value: "+editFramePOJO.getValid_to()+ " is not applied in grid for fillingMethod: "+filingMethod);
					}
					
					index++;	
				}
			}
			saveButton.click();
			waitForElement(okButton);
			okButton.click();	
			int rowCnt = getLstofFrames();
			List<Integer> frameSummaryLst = getFrameBuilkEditSummary();
			Assert.assertEquals(rowCount, frameSummaryLst.get(0).intValue());	
			Log.info("Successfully edited the frames");
			return teststeppresult;
		}
		
	


	public void scrollPage(int noOfTimes,Keys key) {
		Log.info("Clicking Scroll Key - "+key+" Button");
		waitForElement(scroll);
		while(noOfTimes>0)
		{
			scroll.sendKeys(key);
			noOfTimes--;
		}
	}

	public Map<String,String> getTestDataForNonEditableFields(String filingMethod)
	{
		Map<String,String> testDataMap = new LinkedHashMap<String,String>();
			if(filingMethod.equals("CAT25") || filingMethod.equals("CAT25 abs") || filingMethod.equals("CAT35 float")) {	
				testDataMap.put("BaseCurrency", "ZZZ");
				testDataMap.put("ResultingAmt", "22");
			}
			if(filingMethod.equals("CAT35 fix")) {
				testDataMap.put("Source", "TT");
				testDataMap.put("WildCardFcc", "AAAAAA");
			}			
			if(filingMethod.equals("CAT25 abs") || filingMethod.equals("CAT35 fix")) {
				testDataMap.put("MaxPercentageDiscount", "11");
			}
			if(filingMethod.equals("CAT25") || filingMethod.equals("CAT35 fix") || filingMethod.equals("CAT35 float")) {
				testDataMap.put("MaxAbsDiscount", "11");
				testDataMap.put("MaxAbsDiscountCur", "11");
			}
			
		return testDataMap;
	}
	
	public List<FramePOJO> getTestDataForEditableFields(String filingMethod) throws IOException {
		CSVReader reader = new CSVReader(new FileReader(System.getProperty("user.dir")+"\\"+Configuration.getConfiguration().getTestDataPath()), ','); 
		List<String[]> records = reader.readAll();
		List<FramePOJO> lstFramePOJO = new ArrayList<FramePOJO>();
		reader.close();
		boolean header = false;
		
		for(String[] nextRecord : records) {
			if(!header) {
				header= true;
				continue;
			}
			else {
				if(nextRecord!=null && nextRecord[3].equals(filingMethod)) {
					FramePOJO editframe = new FramePOJO();
					//editframe.setValidation(nextRecord[0]);
					editframe.setPos(nextRecord[1]);
					editframe.setSub_category(nextRecord[2]);
					editframe.setFiling_method(filingMethod);
					editframe.setCxr(nextRecord[4]);
					editframe.setSource(nextRecord[5]);
					editframe.setOrigin_co(nextRecord[6]);
					editframe.setDestination_co(nextRecord[7]);
					editframe.setCompartment(nextRecord[8]);
					editframe.setFcc_wildcard(nextRecord[9]);
					editframe.setCurrency_co(nextRecord[10]);
					editframe.setOneway_round_ind(nextRecord[11]);
					editframe.setBase_routing_co(nextRecord[12]);
					editframe.setTariff_co(nextRecord[13]);
					editframe.setMax_relative_corp_discount(nextRecord[14]);
					editframe.setMax_absolute_corp_discount(nextRecord[15]);
					editframe.setMax_abs_corp_disc_currency_co(nextRecord[16]);
					editframe.setResult_amount(nextRecord[17]);
					editframe.setResult_carrier_co(nextRecord[18]);
					editframe.setResult_bcc(nextRecord[19]);
					editframe.setResult_fcc(nextRecord[20]);
					editframe.setRule_co(nextRecord[21]);
					editframe.setRouting_co(nextRecord[25]);
					editframe.setFirst_travel_date(nextRecord[26]);
					editframe.setLast_travel_date(nextRecord[27]);
					editframe.setValid_from(nextRecord[28]);
					editframe.setValid_to(nextRecord[29]);
					lstFramePOJO.add(editframe);
				}
			}
		
		}
		return lstFramePOJO;
		
	}
	
	public int setMandatoryValuesToCreateFrames() throws InterruptedException
	{
		clickElement(subCategory);
		clickElement(subCategoryOptionA);
		scrollPage(30,Keys.RIGHT);
		clickElement(travelFromDate);
		travelFromDate.sendKeys("2020-05-01");
		clickElement(travelToDate);
		travelToDate.sendKeys("2020-05-31");
		clickElement(frameFromDate);
		frameFromDate.sendKeys("2020-04-01");
		clickElement(frameToDate);
		frameToDate.sendKeys("2020-04-30");
		clickElement(maxPercentageDiscount);
		maxPercentageDiscount.sendKeys("25");
		clickElement(rule);
		rule.sendKeys("6021");
		clickElement(ruleHeader);
		scrollPage(30,Keys.LEFT);
		int rowCount = getLstofFrames();
		return rowCount;
	}
	
	public boolean isEditFramePageDisplayed() {
		waitForElement(Editframe);
		if (null != Editframe && Editframe.isDisplayed())
			return true;
		return false;
	}
	
	public void clickSaveAllChanges() throws InterruptedException
	{
		clickElement(saveButton);
	}
	
	public void clickOkButton() throws InterruptedException
	{
		clickElement(okButton);
	}
	
	
	
	public int getLstofFrames() throws InterruptedException
	{
		int rowCount = 1, currentVal = 0,nextVal = 0;
		boolean newRowFound = true;
		while(newRowFound)
		{
			newRowFound = false;
			Thread.sleep(1000);
			List<WebElement> webElementLst = driver.findElements(By.cssSelector("div.ReactVirtualized__Grid__innerScrollContainer>div"));
			
			for(int i =0; i<webElementLst.size();i++)
			{
				nextVal = getPixelsFromTop(webElementLst.get(i));
				if(nextVal > currentVal)
				{
					rowCount++;
					currentVal = nextVal;
					newRowFound = true;
				}
			
			}
			scrollPage(3,Keys.DOWN);	
			
		}
		
		return rowCount;
	}
	
	public int getPixelsFromTop(WebElement webElement)
	{
		String[] str = webElement.getAttribute("style").split(" ");
		return Integer.parseInt(str[str.length -1].split("p")[0]);
	}
	
	public List<Integer> getFrameBuilkEditSummary()
	{
		List<Integer> frameSummaryLst = new ArrayList<Integer>();
		waitForElement(frameEditDialogSummary);
		for(int i=1;i<=5;i++)
		{
			WebElement webElement = driver.findElement(By.cssSelector("div[qa-id = 'frames-bulk-edit-dialog-summary']>div:nth-child("+i+")"));
			String str = webElement.getText();
			frameSummaryLst.add(Integer.parseInt(str.split(":")[1].trim()));
		}
		return frameSummaryLst;
	}
	
	public void getCountOfUpdatedFrames(String query)
	{
		
		
		
		
	}
	
	public int checkUpdatedFramesAirportAirport(String Carrier, String Origin, String Destination, String userId, boolean checkUpdatedFrames)

			throws ClassNotFoundException, SQLException {
		DBConnectionHandler db = new DBConnectionHandler();
		Map<Integer, String> queryParameters = new HashMap<Integer, String>();
		Helper helper = new Helper();
		// Check Origin and Destination. If they are not at airport level then call
		// helper function to do so
		queryParameters.put(1, helper.checkAndNormalizeToAirportCode(Origin));
		queryParameters.put(2, helper.checkAndNormalizeToAirportCode(Destination));
		queryParameters.put(3, Carrier);
		queryParameters.put(4, userId);
		String query = null;
		if(checkUpdatedFrames)
		{
			Log.info("Checking DB for updated frames....");
			query = "select count(*) from b2b_frame where origin_co in ? and destination_co in ? and carrier_co = ? and change_user = ? "
					+ "and discon_date < sysdate and to_char(discon_date,'DD-mm-YYYY hh24:mi:ss') >to_char(sysdate - (1/1440) ,'DD-mm-YYYY hh24:mi:ss') ";
		}
		else {
			Log.info("Checking DB for new frames....");
			query = "select count(*) from b2b_frame where origin_co in ? and destination_co in ? and carrier_co = ? and create_user = ? and effective_date < sysdate and to_char(effective_date,'DD-mm-YYYY hh24:mi:ss') > to_char(sysdate - (2/1440),'DD-mm-YYYY hh24:mi:ss') " + 
				"and discon_date > sysdate";
		}

		int DBRecordCount = db.getDBRecordCount(query, queryParameters).intValue();
		Log.info("Database Query Response:" + DBRecordCount);
		return DBRecordCount;
	}
}