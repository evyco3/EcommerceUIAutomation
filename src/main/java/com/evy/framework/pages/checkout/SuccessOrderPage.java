package com.evy.framework.pages.checkout;

import com.evy.framework.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Represents the Success Order Page of the application.
 * <p>
 * Provides methods to retrieve the success order message after placing an order.
 * </p>
 */
public class SuccessOrderPage extends BasePage {

    @FindBy(css = ".page-title>span")
    private WebElement successOrderMsg;

    /**
     * Retrieves the success order message displayed on the page.
     *
     * @return the success order message as a {@link String}
     */
    public String getSuccessOrderMsg() {
        return getText(this.successOrderMsg, "success order message");
    }
}
