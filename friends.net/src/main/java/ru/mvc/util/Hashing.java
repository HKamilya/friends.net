package ru.mvc.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hashing {
    public String hasing(String password) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException();
        }
        byte[] bytes = md5.digest(password.getBytes());
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(b);
        }
        return builder.toString();
    }
}
