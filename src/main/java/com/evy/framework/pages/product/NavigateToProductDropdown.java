package com.evy.framework.pages.product;

import com.evy.framework.pages.BasePage;
import com.evy.framework.utils.LoggerUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Represents the navigation to the product dropdown categories.
 * <p>
 * Provides methods to select categories and navigate to the product listing page based on main, sub, and sub-sub categories.
 * </p>
 */
public class NavigateToProductDropdown extends BasePage {

    @FindBy(css = "span[data-ui-id='page-title-wrapper']")
    private WebElement productListingPageHeader;

    /**
     * Selects the given categories from the dropdown menu and navigates to the Product Listing Page.
     * <p>
     * Handles the selection of main, sub, and sub-sub categories.
     * </p>
     *
     * @param mainCategory the main category to select
     * @param subCategory the sub-category to select (can be empty if not needed)
     * @param subSubCategory the sub-sub-category to select (can be empty if not needed)
     * @return an instance of the {@link ProductListingPage} class
     */
    public ProductListingPage selectCategories(String mainCategory, String subCategory, String subSubCategory) {
        try {
            String mainCategoryStringValue = String.format("//ul[@id='ui-id-2']/li/a/span[normalize-space()='%s']", mainCategory);
            WebElement mainCategoryElement = driver.findElement(By.xpath(mainCategoryStringValue));

            if (subCategory.isEmpty() && subSubCategory.isEmpty()) {
                click(mainCategoryElement, mainCategory);
            } else if (subSubCategory.isEmpty()) {
                String subCategoryStringValue = String.format("//ul[@id='ui-id-2']/li/a/span[normalize-space()='%s']/ancestor::li/ul/li/a/span[normalize-space()='%s']", mainCategory, subCategory);
                WebElement subCategoryElement = driver.findElement(By.xpath(subCategoryStringValue));
                moveToElement(mainCategoryElement, mainCategory);
                click(subCategoryElement, subCategory);
            } else {
                String subCategoryStringValue = String.format("//ul[@id='ui-id-2']/li/a/span[normalize-space()='%s']/ancestor::li/ul/li/a/span[normalize-space()='%s']", mainCategory, subCategory);
                WebElement subCategoryElement = driver.findElement(By.xpath(subCategoryStringValue));
                String subSubCategoryStringValue = String.format("//ul[@id='ui-id-2']/li/a/span[normalize-space()='%s']/ancestor::li/ul/li/a/span[normalize-space()='%s']/ancestor::li//ul//span[normalize-space()='%s']", mainCategory, subCategory, subSubCategory);
                WebElement subSubElement = driver.findElement(By.xpath(subSubCategoryStringValue));
                moveToElement(mainCategoryElement, mainCategory);
                moveToElement(subCategoryElement, subCategory);
                click(subSubElement, subSubCategory);
            }

            waitForElementToBeVisible(driver.findElement(By.cssSelector("span[data-ui-id='page-title-wrapper']")), "ProductListingPage Header");
            return new ProductListingPage();

        } catch (Exception e) {
            LoggerUtils.error(getClass(), "Error during select categories: " + mainCategory + "," + subCategory + "," + subSubCategory + " from dropdown", e);
            throw new RuntimeException("Failed to select categories: " + mainCategory + "," + subCategory + "," + subSubCategory + " from dropdown", e);
        }
    }
}
