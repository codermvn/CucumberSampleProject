package com.lh.test.framework.application.helpers;

import org.openqa.selenium.By;

/**
 * The enum Locator type used in identify by annotation.
 */
public enum LocatorTypes {

    ID("id") ,
    CSS("cssSelector"),
    NAME("name"),
    XPATH("xpath"),
    CLASSNAME("classname"),
    LINKTEXT("linktext");

    private String locatorType;

    LocatorTypes(String locatorType){
        this.locatorType = locatorType;
    }

    /**
     * Get locator type string.
     *
     * @return String
     */
    public String getLocatorType(){
        return this.locatorType;
    }

    /**
     * Get locator by value
     *
     * @param locatorTypes LocatorTypes
     * @param value        String
     * @return By
     */
    public static By getLocator(LocatorTypes locatorTypes, String value){
        if(locatorTypes.equals(LocatorTypes.ID)){
            return By.id(value);
        }
        if(locatorTypes.equals(LocatorTypes.CSS)){
            return By.cssSelector(value);
        }
        if(locatorTypes.equals(LocatorTypes.NAME)){
            return By.name(value);
        }
        if(locatorTypes.equals(LocatorTypes.XPATH)){
            return By.xpath(value);
        }
        if(locatorTypes.equals(LocatorTypes.CLASSNAME)){
            return By.className(value);
        }
        if(locatorTypes.equals(LocatorTypes.LINKTEXT)){
            return By.linkText(value);
        }
        return null;
    }
}
