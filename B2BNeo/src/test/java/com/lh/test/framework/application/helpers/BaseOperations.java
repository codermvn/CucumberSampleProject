package com.lh.test.framework.application.helpers;

import com.lh.test.framework.application.SetupTest;
import com.lh.test.framework.exceptions.EndTestException;
import com.lh.test.framework.exceptions.OptionNotFound;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.not;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElements;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementSelectionStateToBe;

/**
 * Extended operations on webelements are defined here
 */
public class BaseOperations {

    protected WebDriver driver = SetupTest.getDriver();

    /**
     * Select from dropdown by value.
     *
     * @param element WebElement
     * @param value   String
     */
    public void selectFromDropdownByValue(WebElement element, String value){
        waitUntilElementEnabled(element);
        new Select(element).selectByValue(value);
    }

    /**
     * checks if the element displays input text
     *
     * @param element WebElement
     * @param text    String
     * @return boolean
     */
    public boolean isElementContainingText(WebElement element, String text){
        waitUntilElementVisible(element);
        return element.getText().toLowerCase().contains(text.toLowerCase());
    }

    /**
     * Select from dropdown by index of the displayed option
     *
     * @param element WebElement
     * @param index   int
     */
    public void selectFromDropdownByIndex(WebElement element, int index){
        waitUntilElementEnabled(element);
        new Select(element).selectByIndex(index);
    }

    /**
     * Select from dropdown by visible text.
     *
     * @param element WebElement
     * @param text    String
     */
    public void selectFromDropdownByText(WebElement element, String text){
        waitUntilElementEnabled(element);
        new Select(element).selectByVisibleText(text);
    }

    /**
     * Get available options from dropdown
     *
     * @param element WebElement
     * @return List<String>
     */
    public List<String> getAvailableDropdownOptions(WebElement element){
        waitUntilElementVisible(element);
        List<String> options = new ArrayList<>();
        List<WebElement> selectOptions = new Select(element).getOptions();
        int size;
        int count = 0;
        for(int i=0;i<100;i++) {
            selectOptions = new Select(element).getOptions();
            size = selectOptions.size();
            if (size == new Select(element).getOptions().size()) {
                count++;
            }
            else{
                count = 0;
            }
            if (count == 20) {
                break;
            }
        }

        for(WebElement optionElement: selectOptions){
           options.add(optionElement.getText());
        }
        return options;
    }

    /**
     * Get available options from dropdown
     *
     * @param element WebElement
     * @return List<String>
     */
    public List<String> getSelectedDropdownOptions(WebElement element){
        waitUntilElementVisible(element);
        List<String> options = new ArrayList<>();
        for(WebElement optionElement: new Select(element).getAllSelectedOptions()){
            options.add(optionElement.getText());
        }
        return options;
    }

    /**
     * Enter in text box.
     *
     * @param element WebElement
     * @param text    String
     */
    public void enterInTextBox(WebElement element, String text){
        waitUntilElementClickable(element);
        new Actions(driver).sendKeys(element,text).perform();
    }

    /**
     * Focus and Click element.
     *
     * @param element WebElement
     */
    public void clickElement(WebElement element){
        waitUntilElementClickable(element);
        element.click();
//        Actions actions= new Actions(driver);
//        actions.moveToElement(element).click().build().perform();
    }

    /**
     * Focus and Click element if present
     *
     * @param element WebElement
     */
    public boolean checkAndClickElementIfPresent(WebElement element){
        if(isElementPresent(element)) {
            waitUntilElementClickable(element);
            Actions actions = new Actions(driver);
            actions.moveToElement(element).click().build().perform();
            return true;
        }
        return false;
    }

    public void clickPageDown(){
        new Actions(driver).sendKeys(Keys.PAGE_DOWN).perform();
    }

    public void clickTab(){
        new Actions(driver).sendKeys(Keys.TAB).perform();
    }

    public void clickPageUp(){
        new Actions(driver).sendKeys(Keys.PAGE_UP).perform();
    }

    /**
     * Wait for element to come into the dom tree
     *
     * @param element WebElement
     */
    public void waitForElement(WebElement element){
        waitForElement(element,180);
    }

    /**
     * Wait for element with custom timeout
     *
     * @param element WebElement
     * @param timeout int in millseconds
     */
    public void waitForElement(WebElement element, int timeout){
        new WebDriverWait(driver, timeout).until(ExpectedConditionExtension.elementWaitCondition(element));
    }
    
    public void waitForElements(List<WebElement> elementLst)
    {
    	new WebDriverWait(driver, 180).until(ExpectedConditions.visibilityOfAllElements(elementLst));
    }

    public WebElement getParentElement(WebElement element){
        waitForElement(element);
        return (WebElement) ((JavascriptExecutor) driver).executeScript("return arguments[0].parentNode;", element);
    }

    /**
     * checks if the element is present in dom tree
     *
     * @param element WebElement
     * @return boolean
     */
    public boolean isElementPresent(WebElement element) {
        try {
            element.getTagName();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }

        return true;
    }

    /**
     * Gets inner text of the dom element
     *
     * @param element WebElement
     * @return String
     */
    public String getHiddenText(WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        assert jsExecutor != null;

        return (String) jsExecutor.executeScript("return arguments[0].innerText;", element);
    }

    /**
     * Gets value attribute
     *
     * @param element WebElement
     * @return String
     */
    public String getValue(WebElement element) {
        waitUntilElementVisible(element);
        return element.getAttribute("value");
    }

    /**
     * Set the value on select box or textbox
     *
     * @param webElement WebElement
     * @param text       String
     */
    public void set(WebElement webElement, String text) {
        waitUntilElementVisible(webElement);
        String tagName = webElement.getTagName();
        if (tagName.equalsIgnoreCase("input")) {
            webElement.clear();
            enterInTextBox(webElement,text);
            return;
        }

        if (tagName.equalsIgnoreCase("select")) {
            selectFromDropdownByValue(webElement,text);
            return;
        }

        throw new EndTestException("Cannot set elements value: " + tagName);
    }
    
    /**
     * Checks if the WebElement is of Input Type.
     * @param webElement
     * @return boolean
     */
    public boolean checkIfElementIsInput(WebElement webElement)
    {
    	waitUntilElementVisible(webElement);
    	 String tagName = webElement.getTagName();
         if (tagName.equalsIgnoreCase("input")) {
        	 return true;
         }
         return false;
    }

    /**
     * Set according to input format
     *
     * @param format String
     * @param args   the args
     */
    public void set(String format, Object... args) {
        set(String.format(format, args));
    }

    /**
     * Double click.
     *
     * @param webElement WebElement
     */
    public void doubleClick(WebElement webElement) {
        new Actions(driver).doubleClick(webElement).perform();
    }

    /**
     * Drop onto the target area
     *
     * @param webElement WebElement
     * @param target     WebElement
     */
    public void dropOnto(WebElement webElement, WebElement target) {
        new Actions(driver).dragAndDrop(webElement, target).perform();
    }

    public void moveToElement(WebElement webElement) {
        new Actions(driver).moveToElement(webElement).perform();
    }

    public void waitUntilElementNotPresent(WebElement webElement, int timeout)  {
        new WebDriverWait(driver, timeout).until(not(ExpectedConditionExtension.elementWaitCondition(webElement)));
    }

    public void waitUntilElementNotPresent(WebElement webElement)  {
        waitUntilElementNotPresent(webElement,180);
    }

    public void waitUntilElementNotVisible(WebElement webElement,int timeout) {
        new WebDriverWait(driver, timeout).until(not(visibilityOf(webElement)));
    }

    public void waitUntilElementNotVisible(WebElement webElement) {
        waitUntilElementNotVisible(webElement,180);
    }

    public void waitUntilElementVisible(WebElement webElement,int timeoutInSec) {
        waitForElement(webElement,timeoutInSec);
        new WebDriverWait(driver, timeoutInSec).until(visibilityOf(webElement));
    }

    public void waitUntilElementVisible(WebElement webElement) {
        waitUntilElementVisible(webElement,180);
    }

    /**
     * Wait until all elements visible within the defined timeout in seconds
     *
     * @param webElement List<WebElement>
     * @param timeout    int - in seconds
     */
    public void waitUntilAllElementsVisible(List<WebElement> webElement,int timeout) {
        new WebDriverWait(driver, timeout).until(visibilityOfAllElements(webElement));
    }

    public void waitUntilAllElementsVisible(List<WebElement> webElement) {
        waitUntilAllElementsVisible(webElement,180);
    }

    /**
     * Wait until element clickable.
     *
     * @param webElement the web element
     * @param timeout    the timeout in seconds
     */
    public void waitUntilElementClickable(WebElement webElement,int timeout) {
        waitUntilElementEnabled(webElement,timeout);
        new WebDriverWait(driver, timeout).until(elementToBeClickable(webElement));
    }

    public void waitUntilElementClickable(WebElement webElement) {
        waitUntilElementClickable(webElement,30);
    }

    /**
     * Wait until element gets into the input selection status
     *
     * @param webElement WebElement
     * @param timeout    int - timeout in seconds
     * @param selected   boolean - selection status
     */
    public void waitUntilElementSelectionStateToBe(WebElement webElement,int timeout, boolean selected) {
        waitUntilElementVisible(webElement,timeout);
        new WebDriverWait(driver, timeout).until(elementSelectionStateToBe(webElement,selected));
    }

    public void waitUntilElementSelectionStateToBe(WebElement webElement,boolean selected) {
        waitUntilElementSelectionStateToBe(webElement,180,selected);
    }

    /**
     * Wait until element enabled in given timeout in seconds
     *
     * @param webElement the web element
     * @param timeout    the timeout
     */
    public void waitUntilElementEnabled(WebElement webElement,int timeout) {
        waitUntilElementVisible(webElement,timeout);
        new WebDriverWait(driver, timeout).until(ExpectedConditionExtension.elementEnabledCondition(webElement));
    }

    public void waitUntilElementEnabled(WebElement webElement) {
        waitUntilElementEnabled(webElement,180);
    }

    /**
     * Wait until element disabled
     *
     * @param webElement the web element
     * @param timeout    the timeout
     */
    public void waitUntilElementDisabled(WebElement webElement,int timeout) {
        new WebDriverWait(driver, timeout).until(not(ExpectedConditionExtension.elementEnabledCondition(webElement)));
    }

    /**
     * Wait until element gets disabled
     *
     * @param webElement WebElement
     */
    public void waitUntilElementDisabled(WebElement webElement) {
        waitUntilElementDisabled(webElement,180);
    }

    public boolean isToggleButtonSelected(WebElement element){
        waitUntilElementClickable(element);
        return element.getAttribute("class").contains("ui-state-active");
    }

    public void selectToggleOptions(List<WebElement> toggleButtons, List<String> optionsToSelect){
        int found = 0;
        for (WebElement element : toggleButtons) {
            if (optionsToSelect.contains(element.getText().toLowerCase())) {
                if (!isToggleButtonSelected(element)) {
                    clickElement(element);
                }
                found++;
            } else {
                if (isToggleButtonSelected(element)) {
                    clickElement(element);
                }
            }
        }
        if (found != optionsToSelect.size()) {
            throw new OptionNotFound("All input compartment not available for selection");
        }
    }
    
	public int readNumberOfRowInExcel(String path) throws IOException {
		File myFile = new File(path);
		FileInputStream fis = new FileInputStream(myFile);
		@SuppressWarnings("resource")
		XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);
		XSSFSheet mySheet = myWorkBook.getSheetAt(0);
		int numberOfRows = 0;

		for (int i = 0; i <= mySheet.getLastRowNum(); i++) {
			Row row = mySheet.getRow(i);
			if (row == null || row.getCell(0) == null) {
				continue;
			} else {
				numberOfRows++;
			}

		}
		return numberOfRows;
	}
	
	public void copyFileFromSource(String sourceFilePath) throws IOException, URISyntaxException {
        
   	 File sourceFile = new File (sourceFilePath);
   	 String dstnFilePath = System.getProperty("user.dir")+"\\target\\ExportedFiles";
   	 File file = new File(dstnFilePath);
   	 if (!file.exists()) {
		file.mkdir();
   	 }
   	 String dstnFileName = dstnFilePath+"\\"+sourceFile.getName();
   	 File dstnFile = new File(dstnFileName);
	 Files.copy(sourceFile.toPath(), dstnFile.toPath());  
	    	     
}

}
