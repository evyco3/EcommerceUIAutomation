package com.evy.tests;

import com.evy.framework.data.ProductCategoriesDropdown;
import com.evy.framework.pages.HomePage;
import com.evy.framework.utils.AssertionUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

/**
 * Test class for product category selection from dropdown.
 * <p>
 * This class contains test methods for verifying the selection of product categories from a dropdown menu.
 * It uses data from the ProductDropdownData data provider to test the dropdown functionality.
 * </p>
 */
@Feature("Product Dropdown")

public class SelectProductCategoriesTest extends BaseTest {

    /**
     * Tests product category selection from dropdown with various input data.
     * <p>
     * This method uses data from the {@link ProductCategoriesDropdown} data provider to test the dropdown functionality.
     * It verifies if selecting a category navigates to the expected URL.
     * <p>
     * Test Steps:
     *  1. Navigate to the Home Page.
     *  2. Access the product dropdown menu.
     *  3. Select the specified categories (main, sub, sub-sub).
     *  4. Retrieve the current URL after selection.
     *  5. Verify if the current URL matches the expected URL.
     * </p>
     */
    @Test(dataProviderClass = ProductCategoriesDropdown.class, dataProvider = "productCategoriesDropdownData")
    @Story("Product Category Selection")
    @Description("Tests various product category selections from the dropdown menu to verify if the navigation works correctly.")
    public void testUserSelectCategoriesFromDropdown(String mainCategory, String subCategory, String subSubCategory, String expectedUrl) {
        // Perform category selection and get the resulting URL
        String actualUrl = selectCategoriesFromDropdownAndGetUrl(mainCategory, subCategory, subSubCategory);

        // Verify if the actual URL matches the expected URL
        AssertionUtils.assertContains(actualUrl, expectedUrl, "Expected each category from dropdown to navigate to the correct URL");
    }

    /**
     * Selects categories from the product dropdown and retrieves the resulting URL.
     * <p>
     * This method navigates to the dropdown menu, selects the specified categories, and returns the current URL.
     * </p>
     *
     * @param mainCategory  the main category to select
     * @param subCategory   the sub-category to select
     * @param subSubCategory the sub-sub-category to select
     * @return the current URL after category selection
     */
    private String selectCategoriesFromDropdownAndGetUrl(String mainCategory, String subCategory, String subSubCategory) {
        return HomePage.getInstance()
                .navigateToProductDropdown()
                .selectCategories(mainCategory, subCategory, subSubCategory)
                .getCurrentUrl();
    }
}
