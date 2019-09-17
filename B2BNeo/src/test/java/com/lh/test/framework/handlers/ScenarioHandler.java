package com.lh.test.framework.handlers;

import cucumber.api.Scenario;

public class ScenarioHandler {

    private static Scenario scenario;


    public static Scenario getCurrentScenario() {
        return scenario;
    }

    public static void setScenario(Scenario scenario) {
        ScenarioHandler.scenario = scenario;
    }
}
