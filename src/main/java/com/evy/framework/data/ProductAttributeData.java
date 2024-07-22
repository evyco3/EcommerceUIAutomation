package com.evy.framework.data;

import org.testng.annotations.DataProvider;

public final class ProductAttributeData {

    private ProductAttributeData(){}


    @DataProvider(name = "productQuantityData")
    public static Object[][]getData(){
        return new Object[][]{

                {"Men","Tops","Jackets","Montana Wind Jacket","XS","Black","1","valid data","You added Montana Wind Jacket to your shopping cart."},
                {"Men","Tops","Jackets","Montana Wind Jacket","S","Black","1","valid data","You added Montana Wind Jacket to your shopping cart."},
                {"Men","Tops","Jackets","Montana Wind Jacket","M","Black","1","valid data","You added Montana Wind Jacket to your shopping cart."},
                {"Men","Tops","Jackets","Montana Wind Jacket","L","Black","1","valid data","You added Montana Wind Jacket to your shopping cart."},
                {"Men","Tops","Jackets","Montana Wind Jacket","XL","Black","1","valid data","You added Montana Wind Jacket to your shopping cart."},
                {"Men","Tops","Jackets","Montana Wind Jacket","M","Green","1","valid data","You added Montana Wind Jacket to your shopping cart."},
                {"Men","Tops","Jackets","Montana Wind Jacket","M","Red","1","valid data","You added Montana Wind Jacket to your shopping cart."},
                {"Men","Tops","Jackets","Montana Wind Jacket","M","Red","0","invalid data","Please enter a quantity greater than 0."},
                {"Men","Tops","Jackets","Montana Wind Jacket","M","Red","-1","invalid data","Please enter a quantity greater than 0."},




        };
    }
}
