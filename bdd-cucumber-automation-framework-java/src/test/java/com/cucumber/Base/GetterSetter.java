package com.cucumber.Base;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class GetterSetter {

    @Setter
    @Getter
    public static String productName;

    @Setter
    @Getter
    public static String priceForProd1;

    @Setter
    @Getter
    public static String priceForProd2;

    @Setter
    @Getter
    public static List<String> prodAddCartText;

    @Setter
    @Getter
    public static boolean prodName;

    @Setter
    @Getter
    public static boolean correctPrice;

    @Setter
    @Getter
    public static boolean correctQty;

}
