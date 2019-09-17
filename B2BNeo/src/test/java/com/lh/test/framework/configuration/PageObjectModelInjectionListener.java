package com.lh.test.framework.configuration;

import com.google.inject.Injector;
import com.google.inject.Provider;
import com.google.inject.spi.InjectionListener;
import org.openqa.selenium.support.PageFactory;

import java.util.logging.Logger;

/**
 * Listener file for POM
 * Used for Guice injection methods
 */
public class PageObjectModelInjectionListener implements InjectionListener {

    Logger logger = Logger.getLogger(PageObjectModelInjectionListener.class.getName());

    private Provider<Injector> injector;

    public PageObjectModelInjectionListener(Provider<Injector> injector) {
        this.injector = injector;
    }

    /**
     * Used to perform after operations for guice injection
     * Will initialize the elements of the class object being injected
     * @param page Object
     */
    public void afterInjection(Object page) {
        WebDriverFactory webDriverFactory = injector.get().getInstance(WebDriverFactory.class);
        logger.warning("Inside PageObjectModelInjectionListener");
        PageFactory.initElements(webDriverFactory.get(), page);
    }
}
