package ru.kpfu.itis.gimaletdinova.util;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class PasswordUtil {
    public static String encrypt(String password) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            return DatatypeConverter.printHexBinary(digest).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static Map<String, Boolean> verify(String password) {
        Map<String, Boolean> map = new HashMap<>();
        if (password.length() < 8) {
            map.put("password_length_error", true);
        }
        if (password.matches("[a-zA-Z]*")) {
            map.put("password_dig_error", true);
        }
        if (password.matches("\\d*")) {
            map.put("password_letters_error", true);
        }
        return map;
    }

    public static Boolean verifyEquals(String password, String password2) {
        return password.equals(password2);
    }

}
