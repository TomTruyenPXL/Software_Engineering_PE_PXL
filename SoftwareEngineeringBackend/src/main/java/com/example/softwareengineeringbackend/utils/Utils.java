package com.example.softwareengineeringbackend.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class Utils {
    public static String hashPassword(String password) {
        return DigestUtils.sha512Hex(password);
    }
}
