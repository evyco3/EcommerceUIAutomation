package com.evy.tests;

import com.evy.framework.pages.HomePage;
import com.evy.framework.utils.AssertionUtils;
import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Test class for end-to-end user scenarios.
 * <p>
 * This class contains a test method for verifying the complete end-to-end process for a user.
 * It includes steps for user registration, product selection, and checkout.
 * </p>
 */
@Feature("End-to-End User Journey")

public class EndToEndTest extends BaseTest {

    /**
     * Data provider for end-to-end tests.
     * <p>
     * This method supplies different sets of data for testing the end-to-end user journey.
     * </p>
     *
     * @return 2D array of test data
     */
    @DataProvider(name = "endToEndData")
    public Object[][] provideEndToEndData() {
        Faker faker = new Faker();
        return new Object[][] {
                // {firstName, lastName, email, password, confirmPassword, mainCategory, subCategory, subSubCategory, productName, productSize, productColor, productQuantity, address, city, postcode, country, phone, expectedMessage}
                {"Evy", "User", faker.internet().emailAddress(), "Password123", "Password123", "Men", "Tops", "Jackets", "Montana Wind Jacket", "M", "Black", "1",
                        "1234 Elm Street", "tel-aviv", "12345", "Israel", "555-1234", "Thank you for your purchase!"},

                // Add more data sets as needed
        };
    }

    /**
     * Tests the end-to-end scenario for a user.
     * <p>
     * This method executes the complete user journey from registration to placing an order.
     * <p>
     * Test Steps:
     *  1. Navigate to the Authentication section.
     *  2. Register a new user.
     *  3. Select product categories and attributes.
     *  4. Add the product to the cart.
     *  5. Navigate to checkout and place the order.
     *  6. Verify the success message of the order placement.
     * </p>
     *
     * @param firstName         the first name of the user
     * @param lastName          the last name of the user
     * @param email             the email address of the user
     * @param password          the password for registration
     * @param confirmPassword   the confirmation password
     * @param mainCategory      the main product category
     * @param subCategory       the sub-category of the product
     * @param subSubCategory    the sub-sub-category of the product
     * @param productName       the name of the product
     * @param productSize       the size of the product
     * @param productColor      the color of the product
     * @param productQuantity   the quantity of the product
     * @param address           the shipping address
     * @param city              the city of the shipping address
     * @param postcode          the postal code of the shipping address
     * @param country           the country of the shipping address
     * @param phone             the phone number of the shipping address
     * @param expectedMessage   the expected success message
     */
    @Test(dataProvider = "endToEndData")
    @Story("End-to-End User Journey")
    @Description("Tests the complete end-to-end user journey from registration to placing an order with various input data.")
    public void testUserEndToEnd(String firstName, String lastName, String email, String password, String confirmPassword,
                                 String mainCategory, String subCategory, String subSubCategory, String productName,
                                 String productSize, String productColor, String productQuantity,
                                 String address, String city, String postcode, String country, String phone,
                                 String expectedMessage) {

        String actualMessage = executeEndToEnd(firstName, lastName, email, password, confirmPassword,
                mainCategory, subCategory, subSubCategory, productName,
                productSize, productColor, productQuantity, address, city, postcode,
                country, phone);

        AssertionUtils.assertEquality(actualMessage, expectedMessage, "Verify that the order success message is displayed correctly after completing the end-to-end purchase process.");
    }

    /**
     * Executes the end-to-end process and retrieves the success order message.
     * <p>
     * This method performs the steps of user registration, product selection, adding to cart, and placing an order.
     * </p>
     *
     * @return the success message after placing the order
     */
    private String executeEndToEnd(String firstName, String lastName, String email, String password, String confirmPassword,
                                   String mainCategory, String subCategory, String subSubCategory, String productName,
                                   String productSize, String productColor, String productQuantity,
                                   String address, String city, String postcode, String country, String phone) {
        return HomePage.getInstance()
                .navigateToAuthentication()
                .navigateToRegisterPage()
                .register(firstName, lastName, email, password, confirmPassword, true, HomePage.class)
                .navigateToProductDropdown()
                .selectCategories(mainCategory, subCategory, subSubCategory)
                .selectProductByName(productName)
                .setProductSize(productSize).setProductColor(productColor).setProductQuantity(productQuantity).clickProductAddToCartBtn()
                .navigateToCheckout()
                .setFirstName(firstName).setLastName(lastName).setAddress(address).setCity(city).setPostcode(postcode).setCountry(country)
                .setPhone(phone).clickFlatRateShipmentMethod().clickNextPageBtn()
                .clickPlaceOrder()
                .getSuccessOrderMsg();
    }
}
