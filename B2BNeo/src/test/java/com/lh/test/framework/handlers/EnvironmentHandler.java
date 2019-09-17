package com.lh.test.framework.handlers;

import java.net.MalformedURLException;
import java.net.URL;

public class EnvironmentHandler {

    public static URL getURL(String env) throws MalformedURLException {
        return new URL(env);

    }

    public static URL getHubUrl(String hub, String port) throws MalformedURLException {
        String hubConnection = hub + ":" + port + "/wd/hub";
        return new URL(hubConnection);

    }
}
