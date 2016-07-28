package butlers.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CustomWait {

    private WebDriver driver;
    private int globalWaitTime;

    public CustomWait(WebDriver driver) {
        this.driver = driver;
        this.globalWaitTime = 10;
    }

    public WebElement waitForElementPresent(final By elementLocator) {
        return waitForElementPresent(elementLocator, globalWaitTime);
    }

    public WebElement waitForElementPresent(final By elementLocator, final int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
    }

    public WebElement waitForElementPresent(final WebElement element) {
        return waitForElementPresent(element, globalWaitTime);
    }

    public WebElement waitForElementPresent(final WebElement element, final int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitForElementBeingClickable(final By elementLocator, final int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        return wait.until(ExpectedConditions.elementToBeClickable(elementLocator));
    }

    public WebElement waitForElementBeingClickable(final WebElement element) {
        return waitForElementBeingClickable(element, globalWaitTime);
    }

    public WebElement waitForElementBeingClickable(final WebElement element, final int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForElementDisappear(final By locator) {
        waitForElementDisappear(locator, globalWaitTime);
    }

    public void waitForElementDisappear(final By locator, final int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public boolean isElementPresent(final By locator) {
        return isElementPresent(locator, globalWaitTime);
    }

    public boolean isElementPresent(final By locator, final int timeoutInSeconds) {
        try {
            waitForElementPresent(locator, timeoutInSeconds);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isElementPresent(final WebElement element) {
        return isElementPresent(element, globalWaitTime);
    }

    public boolean isElementPresent(final WebElement element, final int timeoutInSeconds) {
        try {
            waitForElementPresent(element, timeoutInSeconds);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isElementDisappeared(final By locator) {
        return isElementDisappeared(locator, globalWaitTime);
    }

    public boolean isElementDisappeared(final By locator, final int timeoutInSeconds) {
        try {
            waitForElementDisappear(locator, timeoutInSeconds);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }


}
