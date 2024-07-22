package com.evy.framework.pages.product;

import com.evy.framework.pages.BasePage;
import com.evy.framework.utils.LoggerUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Represents the Product Listing Page of the application.
 * <p>
 * Provides methods to select a product from the list based on the product name.
 * </p>
 */
public class ProductListingPage extends BasePage {

    /**
     * Selects a product by its name from the product listing page.
     * <p>
     * Navigates to the Product Page of the selected product.
     * </p>
     *
     * @param productName the name of the product to select
     * @return an instance of the {@link ProductPage} class
     */
    public ProductPage selectProductByName(String productName) {
        try {
            String productNameStringValue = String.format("//a[@class='product-item-link'][normalize-space()='%s']", productName);
            WebElement productNameElement = driver.findElement(By.xpath(productNameStringValue));
            click(productNameElement, productName);
            waitForElementToBeVisible(driver.findElement(By.xpath("//span[@class='base'][normalize-space()='" + productName + "']")), "ProductPageHeader");
            return new ProductPage();

        } catch (Exception e) {
            LoggerUtils.error(getClass(), "Error during product: " + productName + " from product pool result", e);
            throw new RuntimeException("Failed to select " + productName, e);
        }
    }
}
