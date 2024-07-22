package com.evy.tests;

import com.evy.framework.data.LoginData;
import com.evy.framework.pages.HomePage;
import com.evy.framework.pages.authentication.LoginPage;
import com.evy.framework.utils.AssertionUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Test class for user login scenarios.
 * <p>
 * This class contains test methods for verifying different user login scenarios.
 * It uses data from the LoginData data provider to test the login functionality.
 * </p>
 */
@Feature("User Login")

public class LoginTest extends BaseTest {

    /**
     * Tests user login scenarios with various input data.
     * <p>
     * This method uses data from the {@link LoginData} data provider to test the login functionality.
     * It verifies if the login operation displays the correct message based on the provided details.
     * <p>
     * Test Steps:
     *  1. Land on the Home Page.
     *  2. Navigate to the Authentication section.
     *  3. Go to the Login Page.
     *  4. Attempt to log in with the provided details.
     *  5. Verify if the login result status matches the expected outcome.
     * </p>
     */
    @Test(dataProviderClass = LoginData.class, dataProvider = "loginData")
    @Story("User Login")
    @Description("Tests various user login scenarios to verify if the login process works correctly.")
    @Parameters({"email", "password", "operation"})
    public void testUserLoginScenarios(String email, String password, String operation) {
        // Perform login and get the response message display status
        boolean loginResultStatus = loginAndGetResponseMsgDisplayStatus(email, password, operation);

        // Verify if the login result status matches the expected outcome
        AssertionUtils.verifyTrue(loginResultStatus, "Verify if proper message display for every login operation");
    }

    /**
     * Logs in a user and retrieves the login result status.
     * <p>
     * This method navigates to the login page, performs the login, and returns the result status.
     * </p>
     *
     * @param email      the email of the user
     * @param password   the password for the user
     * @param operation  the login operation to perform
     * @return true if the login was successful and displayed the expected message; false otherwise
     */
    private boolean loginAndGetResponseMsgDisplayStatus(String email, String password, String operation) {
        // Navigate to the login page and perform the login
        return HomePage.getInstance()
                .navigateToAuthentication()
                .navigateToLoginPage()
                .login(email, password, false, LoginPage.class)
                .isLoginSuccess(operation);
    }
}
