package com.evy.framework.data;

import org.testng.annotations.DataProvider;

public final class ProductCategoriesDropdown {

    private ProductCategoriesDropdown() {
    }

    @DataProvider(name = "productCategoriesDropdownData")
    public static Object[][] getData() {
        return new Object[][]{
                {"Women", "", "", "/women.html"},
                {"Men", "", "", "/men.html"},
                {"Gear", "", "", "/gear.html"},
                {"Training", "", "", "/training.html"},
                {"Sale", "", "", "/sale.html"},
                {"Women", "Tops", "", "/women/tops-women.html"},
                {"Women", "Bottoms", "", "/women/bottoms-women.html"},
                {"Men", "Tops", "", "/men/tops-men.html"},
                {"Men", "Bottoms", "", "/men/bottoms-men.html"},
                {"Gear", "Bags", "", "/gear/bags.html"},
                {"Gear", "Fitness Equipment", "", "/gear/fitness-equipment.html"},
                {"Gear", "Watches", "", "/gear/watches.html"},
                {"Training", "Video Download", "", "/training/training-video.html"},
                {"Women", "Tops", "Jackets", "/women/tops-women/jackets-women.html"},
                {"Women", "Tops", "Hoodies & Sweatshirts", "women/tops-women/hoodies-and-sweatshirts-women.html"},
                {"Women", "Tops", "Tees", "/women/tops-women/tees-women.html"},
                {"Women", "Tops", "Bras & Tanks", "/women/tops-women/tanks-women.html"},
                {"Women", "Bottoms", "Pants", "/women/bottoms-women/pants-women.html"},
                {"Women", "Bottoms", "Shorts", "/women/bottoms-women/shorts-women.html"},
                {"Men", "Tops", "Jackets", "/men/tops-men/jackets-men.html"},
                {"Men", "Tops", "Hoodies & Sweatshirts", "/men/tops-men/hoodies-and-sweatshirts-men.html"},
                {"Men", "Tops", "Tees", "/men/tops-men/tees-men.html"},
                {"Men", "Tops", "Tanks", "/men/tops-men/tanks-men.html"},
                {"Men", "Bottoms", "Pants", "/men/bottoms-men/pants-men.html"},
                {"Men", "Bottoms", "Shorts", "/men/bottoms-men/shorts-men.html"},

        };
    }
}
