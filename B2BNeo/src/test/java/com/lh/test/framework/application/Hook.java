package com.lh.test.framework.application;

import org.openqa.selenium.OutputType;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.AfterStep;
import io.cucumber.java.BeforeStep;

public class Hook {
	  protected WebDriver driver = SetupTest.getDriver();

	@AfterStep
	public void screenShotAfterStep(Scenario scenario) {
		addScreenshot(scenario);
	}

	private void addScreenshot(Scenario scenario) {
		try {
			WebDriver augmentedDriver = new Augmenter().augment(driver);
			byte[] screenshot = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.BYTES);
			scenario.embed(screenshot, "image/png");
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}
	
}
