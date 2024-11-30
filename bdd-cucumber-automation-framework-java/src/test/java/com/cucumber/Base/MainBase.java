package com.cucumber.Base;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MainBase {

    public static WebDriver driver;
    public static List<String> productCounter = new ArrayList<>();
    public String readPropertiesFile(String property) {

        FileReader fileReader;
        Properties prop = null;
        try{
            fileReader = new FileReader("src/test/resources/config.properties");
            prop = new Properties();
            prop.load(fileReader);
        }catch(Exception e){
            ExtentCucumberAdapter.getCurrentStep().fail(e.getMessage());
        }

        assert prop != null;
        return prop.getProperty(property);
    }

    public WebDriver browserLaunch(String browser){
        if(browser.equalsIgnoreCase("chrome")){
            driver = new ChromeDriver();
        }else if(browser.equalsIgnoreCase("firefox")){
            driver = new FirefoxDriver();
        }else if(browser.equalsIgnoreCase("edge")){
            driver = new EdgeDriver();
        }
        return driver;
    }

    public String getNumbersFromStingWithDecimalValue(String text){
        char[] chars = text.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(char c: chars){
            if(Character.isDigit(c) || c == '.'){
                sb.append(c);
            }
        }
        return String.valueOf(sb);
    }

    public void waitForElement() {
        try{
            Thread.sleep(2000);
        }catch (InterruptedException e){
            System.out.println("wait issue.");
        }
    }
    public void singleClick(WebDriver driver, By by){
        driver.findElement(by).click();
    }

    public void enterText(WebDriver driver, By by, String value){
        driver.findElement(by).sendKeys(value);
    }

    public List<WebElement> returnNumberOfElements(WebDriver driver, By by){
        return driver.findElements(by);
    }

    public boolean isWebElementEmpty(WebDriver driver, By by){
        return driver.findElements(by).isEmpty();
    }

    public String getTextFromWebElement(WebDriver driver, By by){
        return driver.findElement(by).getText();
    }

    public void jsScrollElementInToView(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true)", element);
    }

    public byte[] getScreenshot(){
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
