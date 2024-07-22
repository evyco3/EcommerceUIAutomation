package com.evy.tests;

import com.evy.framework.data.ProductNamesData;
import com.evy.framework.pages.HomePage;
import com.evy.framework.utils.AssertionUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

/**
 * Test class for selecting products by name from categories.
 * <p>
 * This class contains test methods for verifying the selection of a product by its name after navigating through categories.
 * It uses data from the ProductNamesData data provider to test the selection functionality.
 * </p>
 */
@Feature("Product Selection")

public class SelectProductByNameTest extends BaseTest {

    /**
     * Tests product selection by name with various input data.
     * <p>
     * This method uses data from the {@link ProductNamesData} data provider to test the selection of a product by its name.
     * It verifies if selecting a product navigates to the expected product page and displays the correct header text.
     * <p>
     * Test Steps:
     *  1. Navigate to the Home Page.
     *  2. Access the product dropdown menu.
     *  3. Select the specified categories (main, sub, sub-sub).
     *  4. Select the product by its name.
     *  5. Retrieve the product page header text.
     *  6. Verify if the product page header text matches the expected header text.
     * </p>
     */
    @Test(dataProviderClass = ProductNamesData.class, dataProvider = "productNamesData")
    @Story("Product Selection by Name")
    @Description("Tests various product selections by name to verify if the navigation works correctly and the product page displays the expected header text.")
    public void testUserSelectProductByName(String mainCategory, String subCategory, String subSubCategory, String productName, String expectedProductPageHeader) {
        // Perform product selection and get the resulting product page header text
        String actualProductPageHeader = selectProductByNameAndGetProductPageHeader(mainCategory, subCategory, subSubCategory, productName);

        // Verify if the actual product page header text matches the expected header text
        AssertionUtils.assertEquality(actualProductPageHeader, expectedProductPageHeader, "Verify select product by name navigates to the correct Product Page HeaderText");
    }

    /**
     * Selects a product by name after navigating through categories and retrieves the product page header text.
     * <p>
     * This method navigates through the categories, selects the specified product by its name, and returns the product page header text.
     * </p>
     *
     * @param mainCategory         the main category to select
     * @param subCategory          the sub-category to select
     * @param subSubCategory       the sub-sub-category to select
     * @param productName          the name of the product to select
     * @return the header text of the product page after selection
     */
    private String selectProductByNameAndGetProductPageHeader(String mainCategory, String subCategory, String subSubCategory, String productName) {
        return HomePage.getInstance()
                .navigateToProductDropdown()
                .selectCategories(mainCategory, subCategory, subSubCategory)
                .selectProductByName(productName)
                .getPageHeader();
    }
}
