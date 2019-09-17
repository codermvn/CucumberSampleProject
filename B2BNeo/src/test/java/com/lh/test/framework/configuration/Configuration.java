package com.lh.test.framework.configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {

    // TODO: 10/19/2017 add logger and respective log4j props file 

    private static final String DEBUG = "application.selenium.debug";
    private static final String BROWSER = "application.selenium.browser";
    private static final String ENVIRONMENT = "application.selenium.environment";
    private static final String CHROME_WEBDRIVER = "application.webdriver.chromedriver";
    private static final String FIREFOX_WEBDRIVER = "application.webdriver.geckodriver";
    private static final String IE_WEBDRIVER = "application.webdriver.iedriver";
    private static final String USERNAME = "application.webdriver.proxy.username";
    private static final String PASSWORD = "application.webdriver.proxy.password";
    private static final String PLPUSERNAME = "application.selenium.plpusername";
    private static final String PLPPASSWORD = "application.selenium.plppassword";
    private static final String PLPUSERMAILID = "application.selenium.plpusermailid";
    private static final String PLPUSERMAILPASSWORD = "application.selenium.plpmailpassword";
    private static final String REMOTE_RUN = "application.webdriver.remoteRun";
    private static final String HUB = "application.grid.hub";
    private static final String PORT = "application.grid.port";
    private static final String UI_TESTING = "application.uiTesting";
    private static final String PROCESS_DATE = "application.optimizer.processDate";
    private static final String REST_BASE_URI = "application.rest.service.baseUri";
    private static final String REST_PROXY_SERVER = "application.rest.service.proxyServer";
    private static final String REST_PROXY_PORT= "application.rest.service.proxyPort";
    private static final String REST_PROXY_REQUIRED = "application.rest.service.isProxyRequired";
    private static final String REST_PROXY_SCHEME = "application.rest.service.proxyScheme";
    private static final String REST_COOKIE = "application.rest.service.cookie";
    private static final String DB_URL = "application.plp.database.server.host";
    private static final String DB_USERNAME = "application.plp.database.server.username";
    private static final String DB_PASSWORD = "application.plp.database.server.passsword";
    private static final String DB_SID = "application.plp.database.server.sid";
    private static final String DB_PORT = "application.plp.database.server.port";
    private static final String TestDataPath = "application.testDataPath";

    // TODO: 10/19/2017  add correct file paths here
    // TODO: 10/19/2017 add java docs for all methods and classes
    // TODO: 10/23/2017 add paths relative to user.home - to check
    private static final String[] PATHS = {"../../PropertiesHelper.properties",
            "src/main/java/com/lh/test/properties/DatabaseConnection.properties",
            "src/main/java/com/lh/test/properties/RestServiceConnection.properties",
            "src/main/java/com/lh/test/properties/Optimizer.properties",
            "project.properties",
            "src/main/resources/log4j.properties"
    };

    private static Configuration config;
    private static Properties properties;

    private Configuration(){
        properties = new Properties();
        for(String path:PATHS) {
            try {
                properties.load(new FileInputStream(path));
            }catch(IOException i){
                //write log line here
            }
        }
        properties.putAll(System.getProperties());
    }

    public static Configuration getConfiguration() {
        if (null == config) {
            config = new Configuration();
        }
        return config;
    }

    public String getRestProxyScheme() {
        return properties.getProperty(REST_PROXY_SCHEME);
    }

    public String getRestBaseUri(){
        return properties.getProperty(REST_BASE_URI);
    }

    public String getRestProxyServer(){
        return properties.getProperty(REST_PROXY_SERVER);
    }

    public String getRestCookie(){
        return properties.getProperty(REST_COOKIE);
    }

    public Integer getRestProxyPort(){
        return Integer.parseInt(properties.getProperty(REST_PROXY_PORT));
    }

    public String getRestProxyRequired(){
        return properties.getProperty(REST_PROXY_REQUIRED);
    }

    public String getProcessDate(){
        return properties.getProperty(PROCESS_DATE);
    }

    public String getDebugProperty(){
        return properties.getProperty(DEBUG);
    }

    public String getBrowserProperty(){
        return properties.getProperty(BROWSER);
    }

    public String getUiTestingProperty(){
        return properties.getProperty(UI_TESTING);
    }

    public String getRemoteRunProperty(){
        return properties.getProperty(REMOTE_RUN);
    }

    public String getGridHubProperty(){
        return properties.getProperty(HUB);
    }

    public String getGridPortProperty(){
        return properties.getProperty(PORT);
    }

    public String getEnvironmentProperty(){
        return properties.getProperty(ENVIRONMENT);
    }

    public String getWebdriverForChrome(){
        return properties.getProperty(CHROME_WEBDRIVER);
    }

    public String getWebdriverForFirefox(){
        return properties.getProperty(FIREFOX_WEBDRIVER);
    }

    public String getWebdriverForIE(){
        return properties.getProperty(IE_WEBDRIVER);
    }

    public String getProxyUsername(){
        return properties.getProperty(USERNAME);
    }

    public String getProxyPassword(){
        return properties.getProperty(PASSWORD);
    }

    public String getPLPUsername() {
		return properties.getProperty(PLPUSERNAME);
	}
    
	public String getPLPUserMailID() {
		return properties.getProperty(PLPUSERMAILID);
	}

	public String getPlPUserMailPWD() {
		return properties.getProperty(PLPUSERMAILPASSWORD);
	}

	public static String getDbUrl() {
		return properties.getProperty(DB_URL);
	}

	public static String getDbUsername() {
		return properties.getProperty(DB_USERNAME);
	}

	public static String getDbPassword() {
		return properties.getProperty(DB_PASSWORD);
	}

	public static String getDbSid() {
		return properties.getProperty(DB_SID);
	}

	public static String getDbPort() {
		return properties.getProperty(DB_PORT);
	}

	public String getPLPPassword() {
		return properties.getProperty(PLPPASSWORD);
	}

	public String getTestDataPath() {
		return properties.getProperty(TestDataPath);
	}

    public Properties getProperties(){
        return properties;
    }
}
