package com.evy.framework.pages;

import com.evy.framework.drivers.Driver;
import com.evy.framework.utils.ActionUtils;
import com.evy.framework.utils.LoggerUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

/**
 * Base class for all page objects in the framework.
 * <p>
 * Provides common methods and utilities for interacting with web elements on a page,
 * including actions like clicking, sending keys, retrieving text, and waiting for elements.
 * </p>
 */
public class BasePage {

    protected final WebDriver driver;

    /**
     * Initializes the WebDriver instance and initializes PageFactory elements.
     */
    public BasePage(){
        this.driver = Driver.getInstance().getDriver();
        PageFactory.initElements(this.driver, this);
    }

    /**
     * Sends keys to a specified WebElement.
     * <p>
     * Waits for the element to be visible, clears any existing text, and sends the new value.
     * </p>
     *
     * @param element     the WebElement to send keys to
     * @param value       the value to send
     * @param elementName the name of the element for logging purposes
     */
    protected void sendKeys(WebElement element, String value, String elementName) {
        ActionUtils.execVoidFunction(getClass(), () -> {
            WebElement webElement = waitForElementToBeVisible(element, elementName);
            webElement.clear();
            webElement.sendKeys(value);
        }, "Sent keys to " + elementName + ":" + value, "Failed to send keys to " + elementName);
    }

    /**
     * Clicks on a specified WebElement using JavaScript.
     * <p>
     * Waits for the element to be visible and performs the click action via JavaScript.
     * </p>
     *
     * @param element     the WebElement to click on
     * @param elementName the name of the element for logging purposes
     */
    protected void click(WebElement element, String elementName) {
        ActionUtils.execVoidFunction(getClass(), () -> {
            WebElement webElement = waitForElementToBeVisible(element, elementName);
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("arguments[0].click();", webElement);
        }, "Clicked on " + elementName, "Failed to click on " + elementName);
    }

    /**
     * Retrieves the text from a specified WebElement.
     * <p>
     * Waits for the element to be visible and returns its text content.
     * </p>
     *
     * @param element     the WebElement to retrieve text from
     * @param elementName the name of the element for logging purposes
     * @return the text content of the WebElement
     */
    protected String getText(WebElement element, String elementName) {
        return ActionUtils.execStringFunction(getClass(), () -> {
            WebElement webElement = waitForElementToBeVisible(element, elementName);
            return webElement.getText().trim();
        }, "Successfully retrieved text from " + elementName + ":" + element.getText(), "Failed to retrieve text from " + elementName);
    }

    /**
     * Checks if a specified WebElement is displayed.
     * <p>
     * Returns true if the element is visible, false otherwise.
     * </p>
     *
     * @param element     the WebElement to check
     * @param elementName the name of the element for logging purposes
     * @return true if the element is displayed, false otherwise
     */
    protected boolean isDisplayed(WebElement element, String elementName) {
        return ActionUtils.execBooleanFunction(getClass(), element::isDisplayed, elementName + " is displayed", elementName + " is not displayed");
    }

    /**
     * Moves the mouse to a specified WebElement.
     * <p>
     * Uses Actions to perform a mouse hover over the element.
     * </p>
     *
     * @param element     the WebElement to move to
     * @param elementName the name of the element for logging purposes
     */
    protected void moveToElement(WebElement element, String elementName) {
        ActionUtils.execVoidFunction(getClass(), () -> {
            Actions actions = new Actions(driver);
            actions.moveToElement(element).perform();
        }, "Moved to " + elementName, "Failed to move to " + elementName);
    }

    /**
     * Clicks on a WebElement and waits for the page title to match the expected title.
     * <p>
     * Navigates to a new page and verifies the page title.
     * </p>
     *
     * @param element     the WebElement to click on
     * @param elementName the name of the element for logging purposes
     * @param pageTitle   the expected page title after navigation
     * @param nextClassPage the name of the next page class for logging purposes
     */
    protected void clickWaitForTitleAndNavigate(WebElement element, String elementName, String pageTitle, String nextClassPage) {
        ActionUtils.execVoidFunction(getClass(), () -> {
            click(element, elementName);
            waitForPageTitle(pageTitle);
        }, "Navigated to " + nextClassPage, "Failed to navigate to " + nextClassPage);
    }

    /**
     * Clicks on a WebElement and waits for another WebElement to be visible before navigating.
     * <p>
     * Used to ensure that the next page is fully loaded before proceeding.
     * </p>
     *
     * @param element        the WebElement to click on
     * @param elementName    the name of the element for logging purposes
     * @param elementToWait  the WebElement to wait for
     * @param elementToWaitName the name of the WebElement to wait for
     * @param nextClassPage  the name of the next page class for logging purposes
     */
    protected void clickWaitForElementAndNavigate(WebElement element, String elementName, WebElement elementToWait, String elementToWaitName, String nextClassPage) {
        ActionUtils.execVoidFunction(getClass(), () -> {
            click(element, elementName);
            waitForElementToBeVisible(elementToWait, elementToWaitName);
        }, "Navigated to " + nextClassPage, "Failed to navigate to " + nextClassPage);
    }

    /**
     * Selects an option from a dropdown menu by visible text.
     * <p>
     * Waits for the dropdown to be visible and selects the specified option.
     * </p>
     *
     * @param element     the WebElement representing the dropdown
     * @param value       the visible text of the option to select
     * @param elementName the name of the element for logging purposes
     */
    protected void selectByVisibleText(WebElement element, String value, String elementName) {
        ActionUtils.execVoidFunction(getClass(), () -> {
            WebElement webElement = waitForElementToBeVisible(element, elementName);
            Select select = new Select(webElement);
            select.selectByVisibleText(value);
        }, "Selected " + value + " from " + elementName + " dropdown", "Failed to select " + value + " from " + elementName + " dropdown");
    }

    /**
     * Retrieves the current URL of the browser.
     *
     * @return the current URL
     */
    public String getCurrentUrl() {
        return ActionUtils.execStringFunction(getClass(), driver::getCurrentUrl,
                "Retrieved current URL: " + driver.getCurrentUrl(), "Failed to retrieve current URL");
    }

    /**
     * Waits for the page title to match the expected title.
     * <p>
     * Uses WebDriverWait to wait until the page title matches the provided title.
     * </p>
     *
     * @param pageTitle the expected page title
     */
    private void waitForPageTitle(String pageTitle) {
        ActionUtils.execVoidFunction(getClass(), () -> {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.titleIs(pageTitle));
        }, "Page title is correct: " + pageTitle, "Failed to wait for page title to be equal to " + pageTitle);
    }

    /**
     * Waits for a WebElement to be visible.
     * <p>
     * Uses FluentWait to handle common exceptions and wait for the element's visibility.
     * </p>
     *
     * @param element     the WebElement to wait for
     * @param elementName the name of the element for logging purposes
     * @return the visible WebElement
     */
    protected WebElement waitForElementToBeVisible(WebElement element, String elementName) {
        try {
            Wait<WebDriver> wait = new FluentWait<>(driver)
                    .ignoring(StaleElementReferenceException.class)
                    .ignoring(ElementNotInteractableException.class)
                    .ignoring(NoSuchElementException.class)
                    .withTimeout(Duration.ofSeconds(8))
                    .pollingEvery(Duration.ofSeconds(1));

            return wait.until(ExpectedConditions.visibilityOf(element));

        } catch (Exception e) {
            LoggerUtils.error(getClass(), elementName + " is not visible", e);
            throw new RuntimeException("Error: Cannot locate " + elementName);
        }
    }
}
