package com.lh.test.framework.configuration;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class ConfigurationProvider implements Provider<Configuration> {

    @Inject
    private  Configuration configuration;

    public Configuration get() {
            if (configuration != null){
                return configuration;
            }
        return Configuration.getConfiguration();
    }
}
