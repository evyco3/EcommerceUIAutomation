package com.evy.framework.pages.authentication;

import com.evy.framework.pages.BasePage;
import com.evy.framework.utils.LoggerUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Represents the Register Page of the application.
 * <p>
 * Provides methods for user registration and validation of registration success or failure messages.
 * </p>
 */
public class RegisterPage extends BasePage {

    @FindBy(css = "#firstname")
    private WebElement firstName;
    @FindBy(css = "#lastname")
    private WebElement lastName;
    @FindBy(css = "#email_address")
    private WebElement email;
    @FindBy(css = "#password")
    private WebElement password;
    @FindBy(css = "#password-confirmation")
    private WebElement confirmation;
    @FindBy(css = "button[title*='Create']")
    private WebElement registerBtn;
    @FindBy(css = "div[data-ui-id='message-success']>div")
    private WebElement successRegisterMsg;
    @FindBy(css = "#email_address-error")
    private WebElement failEmailFormatMsg;
    @FindBy(css = "div[data-ui-id='message-error']>div")
    private WebElement failEmailInUseMsg;
    @FindBy(css = "#password-error")
    private WebElement failPasswordsFormatMsg;
    @FindBy(css = "#password-confirmation-error")
    private WebElement failPasswordsMismatchMsg;
    @FindBy(css = "div[generated=true]")
    private WebElement failEmptyDataMsg;

    /**
     * Registers a new user with the provided details.
     * <p>
     * Performs registration and navigates to the next page if criteria are met.
     * </p>
     *
     * @param firstName     the first name of the user
     * @param lastName      the last name of the user
     * @param email         the email address of the user
     * @param password      the password for registration
     * @param confirmation  the password confirmation
     * @param criteria      a flag indicating if navigation is required
     * @param nextPageClass the class of the page to navigate to after registration
     * @param <T>           the type of the next page class
     * @return an instance of the next page class
     */
    public <T>T register(String firstName, String lastName, String email, String password, String confirmation, boolean criteria, Class<T> nextPageClass) {
        try {
            sendKeys(this.firstName, firstName, "firstName");
            sendKeys(this.lastName, lastName, "lastName");
            sendKeys(this.email, email, "email");
            sendKeys(this.password, password, "password");
            sendKeys(this.confirmation, confirmation, "confirmation");

            if (criteria) {
                clickWaitForTitleAndNavigate(this.registerBtn, "register button", "My Account", "MyAccountPage");
            } else {
                click(this.registerBtn, "register button");
            }
            return nextPageClass.getDeclaredConstructor().newInstance();

        } catch (Exception e) {
            LoggerUtils.error(getClass(), "Error during registration operation", e);
            throw new RuntimeException("Failed to complete registration", e);
        }
    }

    /**
     * Retrieves the registration response message based on the provided operation.
     *
     * @param operation the type of operation to check (valid/invalid data)
     * @return the response message
     */
    public String getRegisterResponseMsg(String operation) {
        return switch (operation) {
            case "valid data" -> getText(this.successRegisterMsg, "success register message");
            case "invalid email format data" -> getText(this.failEmailFormatMsg, "fail email format message");
            case "invalid email in use data" -> getText(this.failEmailInUseMsg, "fail email in use message");
            case "invalid passwords format data" -> getText(this.failPasswordsFormatMsg, "fail passwords format message");
            case "invalid passwords mismatch data" -> getText(this.failPasswordsMismatchMsg, "fail passwords mismatch message");
            case "invalid empty data" -> getText(this.failEmptyDataMsg, "fail empty data message");
            default -> throw new IllegalStateException("Unexpected value: " + operation);
        };
    }
}
