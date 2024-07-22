package com.evy.framework.pages.checkout;

import com.evy.framework.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page object representing the checkout page.
 * Provides methods to interact with and retrieve information from the checkout page.
 */
public class ShipmentPage extends BasePage {

    @FindBy(css = "input[name='firstname']")
    private WebElement firstName;

    @FindBy(css = "input[name='lastname']")
    private WebElement lastName;

    @FindBy(css = "input[name='street[0]']")
    private WebElement address;

    @FindBy(css = "input[name='city']")
    private WebElement city;

    @FindBy(css = "input[name='postcode']")
    private WebElement postcode;

    @FindBy(css = "select[name='country_id']")
    private WebElement country;

    @FindBy(css = "input[name='telephone']")
    private WebElement telephone;

    @FindBy(css = "input[value='flatrate_flatrate']")
    private WebElement flatRateShipmentMethod;

    @FindBy(css = "button[data-role='opc-continue']")
    private WebElement nextButton;

    /**
     * Sets the first name in the checkout form.
     *
     * @param firstName The first name to be entered.
     * @return the current instance of CheckoutPage for method chaining.
     */
    public ShipmentPage setFirstName(String firstName){
        sendKeys(this.firstName, firstName, "firstName");
        return this;
    }

    /**
     * Sets the last name in the checkout form.
     *
     * @param lastName The last name to be entered.
     * @return the current instance of CheckoutPage for method chaining.
     */
    public ShipmentPage setLastName(String lastName){
        sendKeys(this.lastName, lastName, "lastName");
        return this;
    }

    /**
     * Sets the address in the checkout form.
     *
     * @param address The address to be entered.
     * @return the current instance of CheckoutPage for method chaining.
     */
    public ShipmentPage setAddress(String address){
        sendKeys(this.address, address, "address");
        return this;
    }

    /**
     * Sets the city in the checkout form.
     *
     * @param city The city to be entered.
     * @return the current instance of CheckoutPage for method chaining.
     */
    public ShipmentPage setCity(String city){
        sendKeys(this.city, city, "city");
        return this;
    }

    /**
     * Sets the postcode in the checkout form.
     *
     * @param postcode The postcode to be entered.
     * @return the current instance of CheckoutPage for method chaining.
     */
    public ShipmentPage setPostcode(String postcode){
        sendKeys(this.postcode, postcode, "postCode");
        return this;
    }

    /**
     * Sets the country in the checkout form by selecting from the dropdown.
     *
     * @param country The country to be selected.
     * @return the current instance of CheckoutPage for method chaining.
     */
    public ShipmentPage setCountry(String country){
        selectByVisibleText(this.country, country, "country");
        return this;
    }

    /**
     * Sets the phone number in the checkout form.
     *
     * @param phone The phone number to be entered.
     * @return the current instance of CheckoutPage for method chaining.
     */
    public ShipmentPage setPhone(String phone){
        sendKeys(this.telephone, phone, "telephone");
        return this;
    }

    /**
     * Clicks the flat rate shipment method option if it is visible and enabled.
     *
     * @return the current instance of CheckoutPage for method chaining.
     */
    public ShipmentPage clickFlatRateShipmentMethod(){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(this.flatRateShipmentMethod));
        if(this.flatRateShipmentMethod.isEnabled()) {
            click(this.flatRateShipmentMethod, "flat rate shipment method");
        }
        return this;
    }

    /**
     * Clicks the 'Next' button to proceed to the payment page.
     *
     * @return an instance of PaymentPage for further interactions with the payment page.
     */
    public PaymentPage clickNextPageBtn(){
        click(this.nextButton, "nextButton");
        waitForElementToBeVisible(driver.findElement(By.xpath("//div[normalize-space()='Payment Method']")),"aa");
        return new PaymentPage();
    }
}