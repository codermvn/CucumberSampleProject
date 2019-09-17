package com.lh.test.framework.handlers;

import com.lh.test.framework.exceptions.EndTestException;

import java.util.List;
import java.util.regex.Pattern;

public class TestNameHandler {

    private static final Pattern TEST_CASE_NAME_TAG = Pattern.compile("^@Name-[A-Z](\\w*)$");

    private static String testCaseName;

    public static String identifyTestCaseName(List<String> tags){
        for(String tag:tags){
            if(TEST_CASE_NAME_TAG.matcher(tag.trim()).matches()){
                testCaseName = tag.replace("@Name-","");
                return testCaseName;
            }
        }
        throw  new EndTestException("Test case name not Found. Tag should begin with Name- followed by a capital " +
                "alphabet. Only alphanumeric and underscore allowed. Example tag: @Name-Test_001");
    }

    public static String getTestCaseName() {
        return testCaseName;
    }

    public static void setTestCaseName(String testCaseName) {
        TestNameHandler.testCaseName = testCaseName;
    }
}
