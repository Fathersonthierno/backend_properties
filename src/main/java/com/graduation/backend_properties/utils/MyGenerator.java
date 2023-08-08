package com.graduation.backend_properties.utils;

public class MyGenerator {
    public static String generateAlphaNumericStringFromData(int size, GeneratorType type) {
        String theAlphaNumericS;
        if (type.equals(GeneratorType.ALPHA)) theAlphaNumericS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        else if (type.equals(GeneratorType.NUMERIC)) theAlphaNumericS = "0123456789";
        else if (type.equals(GeneratorType.ALPHANUMERIC)) theAlphaNumericS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        else theAlphaNumericS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";

        StringBuilder stringBuilder = new StringBuilder(size);

        for (int m=0; m<size; m++) {
            int myIndex = (int)(theAlphaNumericS.length() * Math.random());
            stringBuilder.append(theAlphaNumericS.charAt(myIndex));
        }
        // The resulting String
        return stringBuilder.toString();
    }
}
