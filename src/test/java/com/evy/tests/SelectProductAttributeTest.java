package com.evy.tests;

import com.evy.framework.data.ProductAttributeData;
import com.evy.framework.pages.HomePage;
import com.evy.framework.utils.AssertionUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Test class for selecting product attributes scenarios.
 * <p>
 * This class contains test methods for verifying different scenarios when a user selects product attributes.
 * It uses data from the ProductAttributeData data provider to test the functionality.
 * </p>
 */
@Feature("Product Selection")

public class SelectProductAttributeTest extends BaseTest {

    /**
     * Tests product attribute selection scenarios with various input data.
     * <p>
     * This method uses data from the {@link ProductAttributeData} data provider to test the product attribute selection functionality.
     * It verifies if the operation displays the correct message based on the provided details.
     * <p>
     * Test Steps:
     *  1. Navigate to the Product Dropdown.
     *  2. Select the categories.
     *  3. Select the product by name.
     *  4. Set the product attributes (size, color, quantity).
     *  5. Add the product to the cart.
     *  6. Verify the response message.
     * </p>
     */
    @Test(dataProviderClass = ProductAttributeData.class, dataProvider = "productQuantityData")
    @Story("Product Attribute Selection")
    @Description("Tests various scenarios of selecting product attributes to verify if the process works correctly.")
    @Parameters({"mainCategory", "subCategory", "subSubCategory", "productName", "productSize", "productColor", "productQuantity", "operation", "expectedMessage"})
    public void testUserSelectProductAttribute(String mainCategory, String subCategory, String subSubCategory, String productName,
                                               String productSize, String productColor, String productQuantity, String operation, String expectedMessage) {

        String actualResponseMsg = selectProductAttributeAndGetResponseMsg(mainCategory, subCategory, subSubCategory, productName,
                productSize, productColor, productQuantity, operation);

        AssertionUtils.assertEquality(actualResponseMsg, expectedMessage, "Verify setting product attribute message equals to the expected message");
    }

    /**
     * Selects product attributes and retrieves the response message.
     * <p>
     * This method navigates through the product selection process, sets the attributes, and returns the response message.
     * </p>
     *
     * @param mainCategory    the main category of the product
     * @param subCategory     the sub-category of the product
     * @param subSubCategory  the sub-sub-category of the product
     * @param productName     the name of the product
     * @param productSize     the size of the product
     * @param productColor    the color of the product
     * @param productQuantity the quantity of the product
     * @param operation       the operation to perform
     * @return the response message after setting the product attributes
     */
    private String selectProductAttributeAndGetResponseMsg(String mainCategory, String subCategory, String subSubCategory, String productName,
                                                           String productSize, String productColor, String productQuantity, String operation) {
        return HomePage.getInstance()
                .navigateToProductDropdown()
                .selectCategories(mainCategory, subCategory, subSubCategory)
                .selectProductByName(productName)
                .setProductSize(productSize)
                .setProductColor(productColor)
                .setProductQuantity(productQuantity)
                .clickProductAddToCartBtn()
                .getAddProductCartResponseMsg(operation);
    }
}
