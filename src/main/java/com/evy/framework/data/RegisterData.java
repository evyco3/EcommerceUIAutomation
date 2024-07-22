package com.evy.framework.data;

import com.github.javafaker.Faker;
import org.testng.annotations.DataProvider;

public final class RegisterData {

    private static final Faker faker=new Faker();

    private RegisterData(){}


    @DataProvider(name="registerData")
    public static Object[][] getData(){
        String pw=getPassword();
        return new Object[][]{
                {getFirstName(),getLastName(),getEmail(),pw,pw,"valid data","Thank you for registering with Main Website Store"},
                {getFirstName(),getLastName(),"evy@user.com",pw,pw,"invalid email in use data","here is already an account with this email address"},
                {getFirstName(),getLastName(),"evy@",pw,pw,"invalid email format data","Please enter a valid email address"},
                {getFirstName(),getLastName(),getEmail(),"Password123","Password","invalid passwords mismatch data","Please enter the same value again."},
                {getFirstName(),getLastName(),getEmail(),"password","password","invalid passwords format data","Minimum of different classes of characters in password is 3"},
                {"",getLastName(),getEmail(),pw,pw,"invalid empty data","This is a required field."}
        };
    }




    private static String getFirstName(){
        return faker.name().firstName();
    }

    private static String getLastName(){
        return faker.name().lastName();
    }

    private static String getEmail(){
        return faker.internet().emailAddress();
    }

    private static String getPassword(){
        return faker.internet().password(10,14,true,true,true);
    }
}
