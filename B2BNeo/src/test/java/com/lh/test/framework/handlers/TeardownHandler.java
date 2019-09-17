package com.lh.test.framework.handlers;

import com.lh.test.framework.configuration.Configuration;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.io.TemporaryFilesystem;
import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ConfigurationBuilder;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;



public class TeardownHandler {

    private static final Logger LOG = Logger.getLogger(TeardownHandler.class.getName());

    public static void tearDown(WebDriver driver){
        String debug = "false";
        debug = Configuration.getConfiguration().getDebugProperty();
        if (null != driver) {

            try {
                TemporaryFilesystem.getDefaultTmpFS().deleteTemporaryFiles();
            } catch (WebDriverException e) {
                if ("true".equalsIgnoreCase(debug)) {
                    LOG.severe("Exception thrown in tearDown(). Unable to delete temporary files. " + e.getMessage());
                }
            }
            try {
                if ("true".equalsIgnoreCase(debug)) {
                    Set<Cookie> cookies = driver.manage().getCookies();
                    Iterator<Cookie> cookiesIter = cookies.iterator();
                    LOG.severe("Before driver.manage().deleteAllCookies()");
                    while (cookiesIter.hasNext()) {
                        LOG.info(cookiesIter.next().toString());
                    }
                }
                driver.manage().deleteAllCookies();
                if ("true".equalsIgnoreCase(debug)) {

                    Set<Cookie> cookies = driver.manage().getCookies();
                    Iterator<Cookie> cookiesIter = cookies.iterator();
                    LOG.info("After remoteWebDriver.manage().deleteAllCookies()");
                    while (cookiesIter.hasNext()) {
                        LOG.severe(cookiesIter.next().toString());
                    }
                }

            } catch (WebDriverException e) {
                if ("true".equalsIgnoreCase(debug)) {
                    LOG.severe("Exception thrown in tearDown(). Unable to delete cookies. " + e.getMessage());
                }
            }

            // destroyAllStaticVariables();

            try {
                driver.quit();
            } catch (WebDriverException e) {
                LOG.severe("In tearDown() - Exception thrown when attempting driver.quit() " + e.getMessage());
            }
        }
    }

//    // TODO: 10/19/2017  Check if below is needed. Remove if not
//    private void destroyAllStaticVariables(){
//        Set<Class<?>> classes = new HashSet<Class<?>>();
//        ConfigurationBuilder reflectionsConfiguration = new ConfigurationBuilder()
//                .forPackages("com.lh.test")
//                .setScanners(new FieldAnnotationsScanner(), new MethodAnnotationsScanner(),
//                        new SubTypesScanner(), new TypeAnnotationsScanner());
//
//        Reflections reflections = new Reflections(reflectionsConfiguration);
//        // TODO: 10/19/2017 use loop for reading all classes and then take static variables and assign null
//
//        reflections.
//
//    }
}
