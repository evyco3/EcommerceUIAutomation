package com.evy.framework.pages.authentication;

import com.evy.framework.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page object representing the authentication navigation page.
 * <p>
 * This page object is used for navigating to different authentication-related pages
 * such as the registration and login pages.
 * </p>
 */
public class NavigateToAuthentication extends BasePage {

    @FindBy(css = ".page-header a[href*='create']")
    private WebElement registerLink;

    @FindBy(css = ".page-header a[href*='login']")
    private WebElement loginLink;

    /**
     * Navigates to the registration page by clicking the registration link.
     * <p>
     * This method performs the following actions:
     * <ul>
     * <li>Clicks on the registration link.</li>
     * <li>Waits for the page title to change to "Create New Customer Account".</li>
     * <li>Returns an instance of {@link RegisterPage} representing the registration page.</li>
     * </ul>
     * </p>
     *
     * @return an instance of {@link RegisterPage}
     */
    public RegisterPage navigateToRegisterPage() {
        clickWaitForTitleAndNavigate(this.registerLink, "register link", "Create New Customer Account", "RegisterPage");
        return new RegisterPage();
    }

    /**
     * Navigates to the login page by clicking the login link.
     * <p>
     * This method performs the following actions:
     * <ul>
     * <li>Clicks on the login link.</li>
     * <li>Waits for the page title to change to "Customer Login".</li>
     * <li>Returns an instance of {@link LoginPage} representing the login page.</li>
     * </ul>
     * </p>
     *
     * @return an instance of {@link LoginPage}
     */
    public LoginPage navigateToLoginPage() {
        clickWaitForTitleAndNavigate(this.loginLink, "login link", "Customer Login", "LoginPage");
        return new LoginPage();
    }
}
