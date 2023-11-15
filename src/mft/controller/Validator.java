package mft.controller;

import java.util.regex.Pattern;

public class Validator {
    public static boolean checkName(String name, int length) {
        return Pattern.matches("[\\w]{2," + length + "}", name);
    }
}
