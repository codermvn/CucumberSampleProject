package com.lh.test.framework.handlers;

import com.lh.test.framework.application.SetupTest;
import cucumber.api.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import java.util.logging.Logger;

public class ScreenshotHandler {
    static Logger LOG = Logger.getLogger(ScreenshotHandler.class.getName());

    public static void takeScreenshotIfFail(Scenario s,WebDriver webDriver) {
        if (s.isFailed()) {
            captureScreenshot(s,webDriver);
        }
    }

    public static void captureScreenshot(Scenario s) {
        captureScreenshot(s,SetupTest.getDriver());
    }

    public static void captureScreenshot(Scenario s,WebDriver webDriver) {

        if (webDriver instanceof TakesScreenshot) {
            try {
                byte[] screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
                s.embed(screenshot, "image/png");
                s.write("URL at capture: " + webDriver.getCurrentUrl());
            } catch (WebDriverException wde) {
                s.write("Embed Failed " + wde.getMessage());
            } catch (ClassCastException cce) {
                cce.printStackTrace();
                // TODO: 11/2/2017 write logger 
            }
        } else {
            // TODO: 11/2/2017 Remove java logger and add log4j logger
            LOG.warning("This web driver implementation cannot create screenshots");
        }
    }
}
