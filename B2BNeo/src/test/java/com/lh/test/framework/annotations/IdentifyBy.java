package com.lh.test.framework.annotations;

import com.lh.test.framework.application.helpers.LocatorTypes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface IdentifyBy {

    LocatorTypes locator(); // type of locator
    String identifier();  // locator value
    String attribute() default ""; // attribute for element to be identified; can be same as locator; mostly different when css is the locator
    String value() default "---"; // value of the attribute;

}
