package com.contractor.calculator;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Utils {
    public static String showNumber(Double num) {
        DecimalFormat df = new DecimalFormat("0.00");
        String number = "";
        df.setRoundingMode(RoundingMode.FLOOR);
        number = df.format(num);
        return number;
    }

    public static Double stringToDecimal(String num) {
        Double number = Double.parseDouble(num);
        DecimalFormat df = new DecimalFormat("0.00");
        df.setMaximumFractionDigits(2);
        return number ;
    }
}
