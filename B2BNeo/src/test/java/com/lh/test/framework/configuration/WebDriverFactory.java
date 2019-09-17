package com.lh.test.framework.configuration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.inject.Provider;
import java.util.function.BiConsumer;

public class WebDriverFactory implements DependencyFactory<WebDriver>, Provider<WebDriver> {

    WebDriver driver;

    public WebDriver get() {
        if (driver != null){
            return driver;
        }
        driver = createNewDriver();

        return driver;
    }

    private WebDriver createNewDriver() {
        String webdriverProperty = System
                .getProperty("selenium.webdriver", "chrome");
        String webDriverServer = System
                .getProperty("selenium.webdriver.remote.server", null);

        if (webDriverServer != null) {
            System.setProperty("webdriver.remote.server",
                    webDriverServer);
        }

        if (webdriverProperty.equalsIgnoreCase("remote")) {
            final DesiredCapabilities capabilities = new DesiredCapabilities();
            System.getProperties().forEach(new BiConsumer<Object, Object>() {
                public void accept(Object o, Object o2) {
                    if (o.toString().matches("selenium.webdriver.remote..*")) {
                        String capabilityName = o.toString()
                                .replace("selenium.webdriver.remote.", "");
                        capabilities
                                .setCapability(capabilityName, o2.toString());
                    }
                }
            });
            return new RemoteWebDriver(capabilities);
        }

        if (webdriverProperty.equalsIgnoreCase("chrome"))
            return new ChromeDriver();

        if (webdriverProperty.equalsIgnoreCase("ie") ||
                webdriverProperty.equalsIgnoreCase("iexplore") ||
                webdriverProperty.equalsIgnoreCase("internet explorer"))
            return new InternetExplorerDriver();

        FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.setEnableNativeEvents(false);
        return new FirefoxDriver(firefoxProfile);
    }
}
