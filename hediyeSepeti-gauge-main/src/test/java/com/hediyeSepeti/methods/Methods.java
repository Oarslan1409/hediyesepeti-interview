package com.hediyeSepeti.methods;

import com.hediyeSepeti.driver.DriverExec;
import helper.ElementHelper;
import helper.StoreHelper;
import model.ElementInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Methods {

    private final Logger logger = LoggerFactory.getLogger(Methods.class);
    public static WebDriverWait wait;
    private static WebDriver driver;


    public Methods() {
        this.driver = DriverExec.driver;
        wait = new WebDriverWait(this.driver, 30);
    }

    public ElementInfo getElementInfo(String key) {
        return StoreHelper.INSTANCE.findElementInfoByKey(key);
    }

    public boolean containsKeyInElementInfoMap(String key) {
        return StoreHelper.INSTANCE.containsKey(key);
    }

    public By getBy(String key) {
        logger.info("Element " + key + " değerinde tutuluyor");
        return ElementHelper.getElementInfoToBy(getElementInfo(key));
    }

    public WebElement findElement(By by) {
        logger.info("Element " + by.toString() + " by değerine sahip");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public void clickElement(By by) {
        findElement(by).click();
    }

    public String getInnerText(String key) {
        return findElement(getBy(key)).getAttribute("innerText");
    }

    public Float replaceString(String key, char oldChar, char newChar) {
        key = key.replace(oldChar, newChar);
        return Float.valueOf(key);
    }

    public void confirmPopup() {
        driver.switchTo().alert().accept();
    }

    public String getValue(By key){
        return driver.findElement(key).getAttribute("value");
    }
    public void scrollDown(int pixel){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,"+pixel+")");
    }

    public Select getSelect(By by) {
        return new Select(findElement(by));
    }

    public void selectByVisibleText(By by, String text) {
        getSelect(by).selectByVisibleText(text);
    }

    public void selectByValue(By by, String value) {
        getSelect(by).selectByValue(value);
    }

    public void selectByIndex(By by, int index) {
        getSelect(by).selectByIndex(index);
    }

    private List<WebElement> findElements(By by) {
        return driver.findElements(by);
    }

    public void findElementGetText(By by, String text, By optionalBy) {

        List<WebElement> allRows = findElements(by);

        for (WebElement element : allRows) {
            if (element.getText().contains(text)) {
                element.findElement(optionalBy).click();
            }
        }
    }

}