package com.cucumber.Base;

import com.cucumber.StepDefs.SmokeStepDef;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;

public class Hooks extends MainBase {

    public WebDriver driver;
    public static Scenario scenario;

    @Before
    public void setUp(Scenario scenario) {
        Hooks.scenario = scenario;
        String browserIs = readPropertiesFile("browser");
        String url = readPropertiesFile("url");
        System.out.println("Browser Name: "+browserIs);
        driver = browserLaunch(browserIs);
        driver.manage().window().maximize();
        driver.get(url);
    }

    @After
    public void tearDown() {
        driver.close();
        driver.quit();
        SmokeStepDef.softAssert.assertAll();
    }
}
