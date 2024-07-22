package com.evy.tests;

import com.evy.framework.data.RegisterData;
import com.evy.framework.pages.HomePage;
import com.evy.framework.pages.authentication.RegisterPage;
import com.evy.framework.utils.AssertionUtils;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Test class for user registration scenarios.
 * <p>
 * This class contains test methods for verifying different user registration scenarios.
 * It uses data from the RegisterData data provider to test the registration functionality.
 * </p>
 */
@Feature("User Registration")

public class RegisterTest extends BaseTest {

    /**
     * Tests user registration scenarios with various input data.
     * <p>
     * This method uses data from the {@link RegisterData} data provider to test the registration functionality.
     * It verifies if the actual registration response message contains the expected response message.
     * <p>
     * Test Steps:
     *  1. Land on the Home Page.
     *  2. Navigate to the Authentication section.
     *  3. Go to the Register Page.
     *  4. Register a new user with the provided details.
     *  5. Verify if the actual registration response message contains the expected message.
     * </p>
     *
     */
    @Test(dataProviderClass = RegisterData.class, dataProvider = "registerData")
    @Parameters({"firstName", "lastName", "email", "password", "confirmation", "operation", "expectedRegisterResponseMsg"})
    @Story("User Registration")
    @Description("Tests various user registration scenarios to verify if the registration process works correctly.")
    public void testUserRegisterScenarios(String firstName, String lastName, String email, String password, String confirmation, String operation, String expectedRegisterResponseMsg) {
        // Register a user and get the response message
        String actualRegisterResponseMsg = registerAndGetResponseMsg(firstName, lastName, email, password, confirmation, operation);

        // Verify if the actual register message contains the expected register message
        AssertionUtils.assertContains(actualRegisterResponseMsg, expectedRegisterResponseMsg,
                "Verify if actual register message contains the expected register message");
    }

    /**
     * Registers a user and retrieves the registration response message.
     * <p>
     * This method navigates to the register page, performs the registration, and returns the response message.
     * </p>
     *
     * @param firstName           the first name of the user
     * @param lastName            the last name of the user
     * @param email               the email of the user
     * @param password            the password for the user
     * @param confirmation        the password confirmation
     * @param operation           the operation performed
     * @return the registration response message
     */
    private String registerAndGetResponseMsg(String firstName, String lastName, String email, String password, String confirmation, String operation) {
        // Navigate to the registration page and perform the registration
        return HomePage.getInstance()
                .navigateToAuthentication()
                .navigateToRegisterPage()
                .register(firstName, lastName, email, password, confirmation, false, RegisterPage.class)
                .getRegisterResponseMsg(operation);
    }
}
