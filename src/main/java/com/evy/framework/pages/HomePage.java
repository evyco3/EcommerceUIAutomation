package com.evy.framework.pages;

import com.evy.framework.pages.authentication.NavigateToAuthentication;
import com.evy.framework.pages.product.NavigateToProductDropdown;

/**
 * Represents the Home Page of the application.
 * <p>
 * Provides methods to navigate to different sections of the application from the home page.
 * </p>
 */
public class HomePage extends BasePage {

    /**
     * Gets an instance of the HomePage.
     *
     * @return a new instance of HomePage
     */
    public static HomePage getInstance() {
        return new HomePage();
    }

    /**
     * Navigates to the Authentication section.
     *
     * @return an instance of NavigateToAuthentication
     */
    public NavigateToAuthentication navigateToAuthentication() {
        return new NavigateToAuthentication();
    }

    /**
     * Navigates to the Product Dropdown section.
     *
     * @return an instance of NavigateToProductDropdown
     */
    public NavigateToProductDropdown navigateToProductDropdown() {
        return new NavigateToProductDropdown();
    }
}
