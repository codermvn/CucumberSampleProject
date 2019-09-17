package com.lh.test.framework.handlers;

import cucumber.api.Scenario;

import java.util.List;

public class TagsHandler {

    public static List<String> getAllTags(Scenario scenario){
        return (List<String>) scenario.getSourceTagNames();
    }
}
