package com.evy.framework.data;

import org.testng.annotations.DataProvider;

public final class ProductNamesData {

    private ProductNamesData(){}

    @DataProvider(name="productNamesData")
    public static Object[][]getData(){
        return new Object[][]{
                {"Men","Tops","Jackets","Proteus Fitness Jackshirt","Proteus Fitness Jackshirt"},
                {"Men","Tops","Jackets","Montana Wind Jacket","Montana Wind Jacket"},
                {"Men","Tops","Jackets","Jupiter All-Weather Trainer","Jupiter All-Weather Trainer"},
                {"Men","Tops","Jackets","Typhon Performance Fleece-lined Jacket","Typhon Performance Fleece-lined Jacket"},
                {"Men","Tops","Jackets","Mars HeatTech™ Pullover","Mars HeatTech™ Pullover"},
                {"Men","Tops","Jackets","Taurus Elements Shell","Taurus Elements Shell"},
                {"Men","Tops","Jackets","Lando Gym Jacket","Lando Gym Jacket"},
                {"Men","Tops","Jackets","Orion Two-Tone Fitted Jacket","Orion Two-Tone Fitted Jacket"},
                {"Men","Tops","Jackets","Kenobi Trail Jacket","Kenobi Trail Jacket"},
                {"Men","Tops","Jackets","Hyperion Elements Jacket","Hyperion Elements Jacket"},
                {"Men","Tops","Jackets","Beaumont Summit Kit","Beaumont Summit Kit"},

        };
    }
}
