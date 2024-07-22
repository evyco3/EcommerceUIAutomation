package com.evy.framework.pages.authentication;

import com.evy.framework.pages.BasePage;
import com.evy.framework.utils.LoggerUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Represents the Login Page of the application.
 * <p>
 * Provides methods for user login and validation of login success or failure messages.
 * </p>
 */
public class LoginPage extends BasePage {

    @FindBy(css = "#email")
    private WebElement email;
    @FindBy(css = "#pass")
    private WebElement password;
    @FindBy(css = "#send2")
    private WebElement loginBtn;
    @FindBy(css = ".page-header .logged-in")
    private WebElement successLoginMsg;
    @FindBy(css = "div[data-ui-id='message-error']>div")
    private WebElement failLoginMsg;
    @FindBy(css = "div[generated='true']")
    private WebElement failLoginEmptyDataMsg;

    /**
     * Logs in the user with the provided credentials.
     * <p>
     * Performs login and navigates to the next page if criteria are met.
     * </p>
     *
     * @param email       the email address of the user
     * @param password    the password of the user
     * @param criteria    a flag indicating if navigation is required
     * @param nextPageClass the class of the page to navigate to after login
     * @param <T>         the type of the next page class
     * @return an instance of the next page class
     */
    public <T>T login(String email, String password, boolean criteria, Class<T> nextPageClass) {
        try {
            sendKeys(this.email, email, "email");
            sendKeys(this.password, password, "password");

            if (criteria) {
                clickWaitForTitleAndNavigate(this.loginBtn, "login button", "Home Page", "HomePage");
            } else {
                click(this.loginBtn, "login button");
            }

            return nextPageClass.getDeclaredConstructor().newInstance();

        } catch (Exception e) {
            LoggerUtils.error(getClass(), "Error during login operation", e);
            throw new RuntimeException("Failed to complete login operation", e);
        }
    }

    /**
     * Checks if the login operation was successful based on the provided criteria.
     *
     * @param operation the type of operation to check (valid/invalid login data)
     * @return true if the login was successful, false otherwise
     */
    public boolean isLoginSuccess(String operation) {
        return switch (operation) {
            case "valid login data" -> isDisplayed(this.successLoginMsg, "success login message");
            case "invalid login data" -> isDisplayed(this.failLoginMsg, "fail login message");
            case "invalid login empty data" -> isDisplayed(this.failLoginEmptyDataMsg, "fail login empty data message");
            default -> throw new IllegalStateException("Unexpected value: " + operation);
        };
    }
}
