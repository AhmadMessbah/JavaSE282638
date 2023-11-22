package mft.controller;

import java.util.regex.Pattern;

public class Validator {
    public static boolean checkName(String name, int length) {
        return Pattern.matches("[\\w\\s\\.]{2," + length + "}", name);
    }

    public static boolean checkEnglishText(String text, int length) {
        return Pattern.matches("[\\w\\.\\s\\-]{2," + length + "}", text);
    }
}
