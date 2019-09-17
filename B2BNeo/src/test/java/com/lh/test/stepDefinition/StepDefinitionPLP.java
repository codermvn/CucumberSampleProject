package com.lh.test.stepDefinition;

import java.awt.AWTException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.Keys;

import com.lh.test.framework.application.helpers.BaseOperations;
import com.lh.test.framework.handlers.DBConnectionHandler;
import com.lh.test.model.Market;
import com.lh.test.model.OBR.OBRRequest;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionPLP extends BaseSteps {

	Logger Log = Logger.getLogger(StepDefinitionPLP.class.getName());

	static String tigerID = null;
	static List<Integer> OBR_IDLst = null;
	static Map<Integer, Market> OBR_IDAndMarketMap = new HashMap<Integer, Market>();
	static int dbRecords, screenRecords;
	static LocalDateTime timeOfExport = null;
	static int rowCount;

	@Given("^OBR created through service with given (.*) and I am on Login Page$")
	public void createOBRUsingService(String markets) throws Exception {
		List<Market> marketLst = helper.createMarketLstFromInput(markets, null);
		tigerID = obrService.generateTigerID();
		Log.info("Tiger ID:" + tigerID + "For Market" + marketLst.toString());
		OBRRequest obrRequest = obrService.createOBRRequest(marketLst, tigerID, null);
		OBR_IDLst = obrService.doRequestToPLP(obrRequest);
		Assert.assertTrue("Market split from application is not correct-->" + "InputSize(Automation):"
				+ marketLst.size() + " WSResponse:" + OBR_IDLst.size(), OBR_IDLst.size() == marketLst.size());
		Log.info("OBRs Created:" + OBR_IDLst);
		OBR_IDAndMarketMap = helper.getOBR_IDAndMarketMap(OBR_IDLst, marketLst);
	}

	@Given("^OBRs created through service with given (.*) and Filing (.*)$")
	public void createOBRForMktsWithFilingMethod(String markets, String filingMethod) throws Exception {
		List<Market> marketLst = helper.createMarketLstFromInput(markets, null);
		tigerID = obrService.generateTigerID();
		Log.info("Tiger ID:" + tigerID + "For Market" + marketLst.toString());
		OBRRequest obrRequest = obrService.createOBRRequest(marketLst, tigerID, filingMethod);
		OBR_IDLst = obrService.doRequestToPLP(obrRequest);
		Log.info("OBRs Created: " + OBR_IDLst);
		OBR_IDAndMarketMap = helper.getOBR_IDAndMarketMap(OBR_IDLst, marketLst);
	}

	@And("^I login with (.*) and (.*)$")
	public void loadPLPHomePage(String username, String password) {
		Log.info("Logging into application with Username:" + username + " and Password:" + password);
		login.enterPLPCredentialsAndLogin(username, password);
		homePage.waitForPage();
	}

	@Given("^Nothing$")
	public void nothing() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		Log.info("No Specific Pre-requsite");
	}

	@When("^I click delete button$")
	public void clickFrameListDelete() {
		if (frameListPage.clickDeleteButton())
			deletePage.clickOK();
		Log.info("Frame Count after delete is:" + screenRecords);
	}

	@Then("^I shall have no frames$")
	public void checkDeleteFrame() {
		Assert.assertEquals(0, frameListPage.getSearchedFrameCount());
	}

	@When("^I click on OBRList button$")
	public void clickOBRListButton() throws InterruptedException {
		homePage.clickOBRListButton();
		Thread.sleep(sleep);

	}

	@When("^I click on FrameList Button$")
	public void clickFrameListButton() {
		Log.info("Clicking Frame List Button");
		homePage.clickFrameListButton();
	}

	@When("^I select (.*) in status dropdown$")
	public void selectStatus(String status) {
		oBRListpage.selectStatus(status);
	}

	@When("^I update Booking Class to \"([^\"]*)\" and discount to \"([^\"]*)\" for (.*) and (.*) the request$")
	public void actionOBR(String rbd, String discount, String filingMethod, String action) throws InterruptedException {
		filingMethod = filingMethod.replaceAll("\\s", "").toUpperCase();
		Log.info("Method- Update OBR and Approve/Reject as per Use Case");
		if (discount != null && discount.trim().length() == 0)
			discount = "0";
		oBRDetailsPage.updateOBRDetails(new Integer(discount), rbd, filingMethod);
		Thread.sleep(sleep);
		switch (action.toUpperCase()) {
		case "REJECT":
			oBRDetailsPage.clickRejectButton();
			Thread.sleep(sleep);
			break;
		case "APPROVE":
			oBRDetailsPage.clickApproveButton();
			Thread.sleep(sleep);
			break;
		default:
			Log.fatal("Not a valid input");
			Assert.fail("OBR Action is not a valid input, pls check the action input in feature file");
		}
	}

	@When("^I enter TigerID$")
	public void enterTigerID() {
		oBRListpage.enterTigerID(tigerID);
	}

	@When("^I click on OBRList search button$")
	public void searchButton() throws InterruptedException {
		oBRListpage.clickSearchButton();
	}

	@When("^I search Framelist with (.*) and (.*) and (.*) and (.*) and (.*) and (.*) and (.*) and (.*) and (.*) and (.*)$")
	public void searchFrames(String frameType, String filingMeth, String SubCat, String POC, String baseCarrier,
			String Origin, String destination, String cmp, String fccContains, String resultBC)
			throws InterruptedException, ClassNotFoundException, SQLException {
		frameListPage.setUISearchParameters(frameType, filingMeth, SubCat, POC, baseCarrier, Origin, destination, cmp,
				fccContains, resultBC);
		frameListPage.searchFrames();
		screenRecords = frameListPage.getSearchedFrameCount();
		dbRecords = frameListPage.checkDBFrameAirportAirport(frameType, filingMeth, SubCat, POC, baseCarrier, Origin,
				destination, cmp, fccContains, resultBC);
	}

	@When("^I search Framelist with Origin= (.*) and Destination= (.*) and FilingMethod= (.*)$")
	public void searchFrames(String origin, String destination, String filingMethod)
			throws InterruptedException, ClassNotFoundException, SQLException {
		frameListPage.setUISearchParameters("PANDA", filingMethod, null, null, null, origin, destination, null, null,
				null);
		frameListPage.searchFrames();
		screenRecords = frameListPage.getSearchedFrameCount();
		dbRecords = frameListPage.checkDBFramesAirportAirport(origin, destination, filingMethod);

	}

	@When("^I search for all OBRs with CustName (.*) and (.*) and (.*)$")
	public void openOBR(String customerName, String date, String status) {
		oBRListpage.clickOnAllOpenRequests(customerName, date, status);
	}

	@When("^I upload file (.*)$")
	public void importFrame(String filePath) throws InterruptedException, AWTException {
		frameListPage.importFrame(filePath);
		Thread.sleep(sleep);

	}

	@Then("^I should reach the import page$")
	public void clickOkImportPage() throws InterruptedException {
		confirmPage.clickOK();
		Thread.sleep(sleep);
	}

	@Then("^I should see list of (.*) OBRs$")
	public void verifyListOfOBRsDisplayed() throws InterruptedException {
		oBRListpage.verifyOBRListpageDisplayed();
	}

	@Then("^Standard OBRs should be displayed$")
	public void verifyCreatedOBRGetsDisplayed() {
		oBRListpage.verifyOBRDisplayed(OBR_IDAndMarketMap);
	}

	@Then("^OBR Details Screen should open$")
	public void verifyOBRDisplayedOnOBRDetailsPage() throws InterruptedException {

		for (Map.Entry<Integer, Market> OBR_IDAndMarketEntry : OBR_IDAndMarketMap.entrySet()) {
			boolean marketFoundOnScreen = false;
			oBRListpage.openOBRByOBRID(OBR_IDAndMarketEntry.getKey());
			marketFoundOnScreen = oBRDetailsPage.verifyMarketOrgnAndDstn(OBR_IDAndMarketEntry.getValue());
			OBR_IDAndMarketEntry.getValue().setFoundOnPLPScreen(marketFoundOnScreen);
			Thread.sleep(sleep);
		}
	}

	@Then("^OBR Details Screen should open and we reject it$")
	public void verifyOBRDisplayedOnOBRDetailsPageAndReject() throws InterruptedException {
		for (Map.Entry<Integer, Market> OBR_IDAndMarketEntry : OBR_IDAndMarketMap.entrySet()) {
			boolean marketFoundOnScreen = false;
			oBRListpage.openOBRByOBRID(OBR_IDAndMarketEntry.getKey());
			marketFoundOnScreen = oBRDetailsPage.verifyMarketOrgnAndDstn(OBR_IDAndMarketEntry.getValue());
			OBR_IDAndMarketMap.get(OBR_IDAndMarketEntry.getKey()).setFoundOnPLPScreen(marketFoundOnScreen);
			Thread.sleep(sleep);
			oBRDetailsPage.clickRejectButton();
			Thread.sleep(sleep);
			oBRDetailsPage.clickBackButton();
		}

		for (Map.Entry<Integer, Market> OBR_IDAndMarketEntry : OBR_IDAndMarketMap.entrySet()) {
			Assert.assertTrue(
					"Number of OBR split not equal to OBRs visible to logged in user:Missing OnD:"
							+ ((Market) OBR_IDAndMarketEntry.getValue()).getMarketOrgn() + "-"
							+ ((Market) OBR_IDAndMarketEntry.getValue()).getMarketDstn(),
					((Market) OBR_IDAndMarketEntry.getValue()).isFoundOnPLPScreen());
		}
	}

	@Then("^Get All OBRs for Processing$")
	public void allOBRsDisplayed() {
		oBRListpage.getOBRScrollable();
	}

	@Then("^Reject All OBRs$")
	public void rejectOpenOBRs() throws Throwable {
		int totalObrs = 0;
		;
		for (Integer obrId : oBRListpage.getListOpenOBRs()) { // Iterate Over all OBRs
			Log.info("OBR ID Present on Screen-->" + obrId);
			totalObrs++;
		}
		for (Integer obrId : oBRListpage.getListOpenOBRs()) { // Iterate Over all OBRs
			Log.info("OBR ID Currently rejected-->" + obrId);
			oBRListpage.openOBRfromURL(obrId.toString()); // Open OBR
			oBRDetailsPage.clickRejectButton();
			oBRDetailsPage.clickBackButton();
			Thread.sleep(sleep);
		}
		Assert.assertNotEquals(0, totalObrs);
	}

	@Then("^OBR should be processed for (.*) Discount (.*) and Booking Class (.*)$")
	public void isOBRProcessed(String filingMethod, String discount, String rbd) {
		filingMethod = filingMethod.replaceAll("\\s", "").toUpperCase();
		ArrayList<String> errors = (ArrayList<String>) oBRDetailsPage.isObrActioned(filingMethod, discount, rbd);
		if (errors.size() > 0)
			Assert.fail(errors.toString());
	}

	@Then("^I should see Framelist search page$")
	public void isFrameListPageOpen() {
		if (!frameListPage.isFrameListPageDispplayed())
			;
		// Assert.fail("FrameList page is not loaded in stipulated time");
	}

	@Then("^I should see ProfitLine/Price-OBRList page")
	public void verifyOBRListPage() throws InterruptedException {
		oBRListpage.verifyOBRListpageDisplayed();
	}

	@Then("^File (.*) should have same records in DB with Origin (.*) and Destination (.*) and filingMethod = (.*)$")
	public void matchRecordsWithDB(String path, String origin, String destination, String filingMethod)
			throws IOException, ClassNotFoundException, SQLException {
		dbRecords = frameListPage.checkDBFramesAirportAirport(origin, destination, filingMethod);
		Assert.assertEquals((new BaseOperations().readNumberOfRowInExcel(path)) - 1, dbRecords);
	}

	@Then("^I should see same frames on screen as in DB with Origin (.*) and Destination (.*) and filingMethod = (.*)$")
	public void matchScreenRecordsFromDB(String origin, String destination, String filingMethod)
			throws ClassNotFoundException, SQLException {
		dbRecords = frameListPage.checkDBFramesAirportAirport(origin, destination, filingMethod);
		Assert.assertEquals(frameListPage.getSearchedFrameCount(), dbRecords);
	}

	@When("^I click search Frames again$")
	public void clickSearchFrames() throws InterruptedException {
		frameListPage.searchFrames();
	}

	@When("^I click on export button$")
	public void clickExport() throws InterruptedException {
		frameListPage.exportFrame();
		Thread.sleep(sleep);
		exportDailogPage.clickOK();
		timeOfExport = LocalDateTime.now().withSecond(0).withNano(0);
	}

	@Then("^Exported file should have same records as on screen$")
	public void verifyExportedFile() throws InterruptedException, IOException, URISyntaxException, AWTException {
		Thread.sleep(60000);
		frameListPage.openOutlookPortal();
		outlookPortal.login();
		int fileRecords = outlookPortal.exportFileAndGetRecords(timeOfExport);
		Assert.assertEquals(fileRecords - 1, screenRecords);

	}

	@Then("^Check database query for edit$")
	public void checkEdit() throws SQLException {
		DBConnectionHandler dbcon = new DBConnectionHandler();
		dbcon.main(null);
	}

	@Then("^DB and Screen counts should match$")
	public void DBandScreenCheck() {
		Assert.assertEquals(screenRecords, dbRecords);
	}

	@When("^I click Edit icon and reach Edit page$")
	public void EditFrame() throws InterruptedException {
		rowCount = frameListPage.getSearchedFrameCount();
		frameListPage.EditFrame();
		if (!frameListPage.isEditFramePageDisplayed())
			;
	}

	@Then("^validate the framelist generic noneditable fields along with filingMethod (.*)$")
	public void verifyNonEditableField(String filingMethod) {
		Log.info("validate method");
		frameEditPage.verifyNonEditableFields("", rowCount);
		frameEditPage.verifyNonEditableFields(filingMethod, rowCount);
		frameEditPage.scrollPage(12, Keys.LEFT);
	}

	@Then("^validate the framelist editable fields with filingMethod (.*)$")
	public void verifyEditableField(String filingMethod) throws IOException, InterruptedException {
		Boolean testresult = frameEditPage.validateEditableFields(filingMethod, rowCount);
		Assert.assertTrue(testresult);
	}

	@When("^I click on Frame out of Fares button$")
	public void clickFramesOutOfFaresButton() {
		frameListPage.clickFrameOutOfFareBtn();

	}

	@Then("^I should see Frame out of fares page$")
	public void isFramesOutOfFarePageOpen() {
		framesOutOfFaresPage.isFrameOutOfFarePageDisplayed();
	}

	@When("^I create the frames with (.*) and (.*) and (.*) and (.*) and (.*) and (.*) and (.*) and (.*) and (.*)$")
	public void createFrames(String Carrier, String Origin, String destination, String cmp, String fcc, String BC,
			String orValue, String fareSource, String fareConstruction)
			throws InterruptedException, ClassNotFoundException, SQLException {
		framesOutOfFaresPage.setUISearchParameters(Carrier, Origin, destination, cmp, fcc, BC, orValue, fareSource,
				fareConstruction);
		framesOutOfFaresPage.createFrames();
		dbRecords = framesOutOfFaresPage.checkDBFaresAirportAirport(Carrier, Origin, destination, cmp, fcc, BC, orValue,
				fareSource, fareConstruction);
	}

	@Then("^I should see FrameEdit page$")
	public void isFrameEditPageDisplayed() {
		frameEditPage.isEditFramePageDisplayed();
	}

	@When("^I supply mandatory values$")
	public void setMandatoryParameters() throws InterruptedException {
		rowCount = frameEditPage.setMandatoryValuesToCreateFrames();
		Assert.assertEquals(dbRecords, rowCount);
	}

	@When("^I click save button$")
	public void clickSaveAllChanges() throws InterruptedException {
		frameEditPage.clickSaveAllChanges();
	}

	@Then("^Verify Frame creation for (.*) and (.*) and (.*) and (.*)$")
	public void validateNewlyCreatedFrames(String Carrier, String Origin, String Destination, String userId) throws InterruptedException, ClassNotFoundException, SQLException
	{
		List<Integer> frameSummaryLst = frameEditPage.getFrameBuilkEditSummary();
		int createdDBCount = frameEditPage.checkUpdatedFramesAirportAirport(Carrier, Origin, Destination, userId,false);
		int updatedDBCount = frameEditPage.checkUpdatedFramesAirportAirport(Carrier, Origin, Destination, userId,true);
		Assert.assertEquals(createdDBCount, frameSummaryLst.get(1).intValue()+frameSummaryLst.get(2).intValue());
		Assert.assertEquals(updatedDBCount, frameSummaryLst.get(2).intValue());

	}
	
	@Then("^Click ok button")
	public void clickOkButton() throws InterruptedException {
		frameEditPage.clickOkButton();
	}
}
