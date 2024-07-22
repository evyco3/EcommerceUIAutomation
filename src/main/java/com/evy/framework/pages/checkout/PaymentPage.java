package com.evy.framework.pages.checkout;

import com.evy.framework.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Represents the Payment Page of the application.
 * <p>
 * Provides methods to interact with the payment options and place an order.
 * </p>
 */
public class PaymentPage extends BasePage {

    @FindBy(css = "button[title='Place Order']")
    private WebElement placeOrderBtn;

    /**
     * Clicks the 'Place Order' button and navigates to the Success Order Page.
     * <p>
     * Waits for the title of the next page and returns an instance of the SuccessOrderPage.
     * </p>
     *
     * @return an instance of the {@link SuccessOrderPage} class
     */
    public SuccessOrderPage clickPlaceOrder() {
        clickWaitForTitleAndNavigate(this.placeOrderBtn, "place order button", "Success Page", "SuccessOrderPage");
        return new SuccessOrderPage();
    }
}
