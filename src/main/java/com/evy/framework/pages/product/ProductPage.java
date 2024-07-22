package com.evy.framework.pages.product;

import com.evy.framework.pages.BasePage;
import com.evy.framework.pages.checkout.ShipmentPage;
import com.evy.framework.utils.ActionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Represents the Product Page of the application.
 * <p>
 * Provides methods to interact with the product's details, such as setting product options, adding to cart,
 * and navigating to the checkout page.
 * </p>
 */
public class ProductPage extends BasePage {

    @FindBy(css = "span[data-ui-id='page-title-wrapper']")
    private WebElement pageHeader;

    @FindBy(css = "#qty")
    private WebElement quantity;

    @FindBy(css = "#product-addtocart-button")
    private WebElement addProductToCartBtn;

    @FindBy(css = "div[data-ui-id='message-success']>div")
    private WebElement addToCartSuccessMsg;

    @FindBy(css = "#qty-error")
    private WebElement quantityProductError;

    @FindBy(css = ".action.showcart")
    private WebElement cartBtn;

    @FindBy(css = "#top-cart-btn-checkout")
    private WebElement navigateToCheckoutBtn;

    /**
     * Gets the header text of the Product Page.
     *
     * @return the header text of the Product Page
     */
    public String getPageHeader() {
        return getText(this.pageHeader, "ProductPage Header");
    }

    /**
     * Sets the product size.
     * <p>
     * Selects the size option for the product.
     * </p>
     *
     * @param productSize the size to select
     * @return the current {@link ProductPage} instance
     */
    public ProductPage setProductSize(String productSize) {
        String productSizeStringValue = String.format("div[id*='option-label-size'][option-tooltip-value='%s']", productSize);
        WebElement element = driver.findElement(By.cssSelector(productSizeStringValue));
        click(element, productSize);
        return this;
    }

    /**
     * Sets the product color.
     * <p>
     * Selects the color option for the product.
     * </p>
     *
     * @param productColor the color to select
     * @return the current {@link ProductPage} instance
     */
    public ProductPage setProductColor(String productColor) {
        String productColorStringValue = String.format("div[id*='option-label-color'][aria-label='%s']", productColor);
        WebElement element = driver.findElement(By.cssSelector(productColorStringValue));
        click(element, productColor);
        return this;
    }

    /**
     * Sets the quantity of the product.
     * <p>
     * Enters the quantity for the product.
     * </p>
     *
     * @param productQuantity the quantity to set
     * @return the current {@link ProductPage} instance
     */
    public ProductPage setProductQuantity(String productQuantity) {
        sendKeys(this.quantity, productQuantity, "productQuantity");
        return this;
    }

    /**
     * Clicks the "Add to Cart" button for the product.
     * <p>
     * Adds the product to the cart.
     * </p>
     *
     * @return the current {@link ProductPage} instance
     */
    public ProductPage clickProductAddToCartBtn() {
        click(this.addProductToCartBtn, "add product to cart button");
        return this;
    }

    /**
     * Gets the response message after attempting to add the product to the cart.
     * <p>
     * Displays a success message if the operation is successful, or an error message if the quantity is invalid.
     * </p>
     *
     * @param operation the type of operation ("valid data" or "invalid data")
     * @return the response message
     */
    public String getAddProductCartResponseMsg(String operation) {
        if (operation.equalsIgnoreCase("valid data")) {
            ActionUtils.execBooleanFunction(getClass(), () ->
                    new WebDriverWait(driver, Duration.ofSeconds(10))
                            .until(ExpectedConditions.textToBePresentInElement(this.addToCartSuccessMsg, "You added")), "Success Message Display", "Success Message Is not Display");
            return getText(this.addToCartSuccessMsg, "add to cart success message");
        } else if (operation.equalsIgnoreCase("invalid data")) {
            return getText(this.quantityProductError, "Quantity Product Error message");
        } else {
            throw new IllegalArgumentException(operation + " is an illegal operation");
        }
    }

    /**
     * Navigates to the checkout page.
     * <p>
     * Clicks on the cart button and then the checkout button, waiting for the success message to be displayed.
     * </p>
     *
     * @return an instance of the {@link ShipmentPage} class
     */
    public ShipmentPage navigateToCheckout() {
        ActionUtils.execBooleanFunction(getClass(), () ->
                new WebDriverWait(driver, Duration.ofSeconds(10))
                        .until(ExpectedConditions.textToBePresentInElement(this.addToCartSuccessMsg, "You added")), "Success Message Display", "Success Message Is not Display");
        click(this.cartBtn, "cart button");
        waitForElementToBeVisible(driver.findElement(By.cssSelector(".subtotal")), "total");
        clickWaitForTitleAndNavigate(this.navigateToCheckoutBtn, "navigate to checkout button", "Checkout", "CheckoutPage");

        return new ShipmentPage();
    }
}
