package com.evy.framework.data;

import org.testng.annotations.DataProvider;

public final class LoginData {

    private LoginData(){}


    @DataProvider(name="loginData")
    public static Object[][]getData(){
        return new Object[][]{
                {"userx@email.com","Password123","valid login data"}, //valid data
                {"userx@email.com","password","invalid login data"}, //valid email +invalid password
                {"wrongEmail@email.com","Password123","invalid login data"}, // invalid email + valid password
                {"wrongEmail@gmail.com","wrongPassword","invalid login data"}, // invalid email +invalid password
                {"","Password123","invalid login empty data"}, //empty email
                {"userx@email.com","","invalid login empty data"}, //empty password
                {"","","invalid login empty data"}, //empty email+password


        };
    }
}
