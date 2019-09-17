package com.lh.test.framework.application.helpers;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Extended implementation for expected condition methods for use in ExpectedConditions.until function
 */
public class ExpectedConditionExtension {


    /**
     * Element wait condition
     *
     * @param element WebElement
     * @return ExpectedCondition<WebElement>
     */
    public static ExpectedCondition<WebElement> elementWaitCondition(WebElement element){
        return new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver driver) {
                try {
                    element.getTagName();
                    return element;
                } catch (NoSuchElementException |StaleElementReferenceException ex) {
                    return null;
                }
            }
        };
    }

    /**
     * Element enabled condition
     *
     * @param element WebElement
     * @return ExpectedCondition<WebElement>
     */
    public static ExpectedCondition<WebElement> elementEnabledCondition(WebElement element){
        return new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver driver) {
                try {
                    if(element.isEnabled()){
                        return element;
                    }
                    return null;
                } catch (NoSuchElementException|StaleElementReferenceException ex) {
                    return null;
                }
            }
        };
    }

    /**
     * more than one window expected condition.
     *
     * @return ExpectedCondition<WebElement>
     */
    public static ExpectedCondition<Boolean> moreThanOneWindowCondition(){
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                try {
                    return driver.getWindowHandles().size() > 1;
                } catch (WebDriverException ex) {
                    return null;
                }
            }
        };
    }

    public static ExpectedCondition<Boolean> textRegexToBePresentInElementLocated(final By locator, final String regex) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                try {
                    String elementText = driver.findElement(locator).getText();
                    return elementText.matches(regex);
                } catch (StaleElementReferenceException var3) {
                    return null;
                }
            }

            public String toString() {
                return String.format("text ('%s') to be present in element found by %s", regex, locator);
            }
        };
    }
}
