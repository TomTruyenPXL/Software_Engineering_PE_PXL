package com.example.softwareengineeringbackend.util;

import com.example.softwareengineeringbackend.utils.Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Locale;

public class UtilsTest {
    @Test
    void hashPasswordShouldReturnHashedPasswordFromPlainText() {
        String password = "password";

        String resultPassword = Utils.hashPassword(password);

        Assertions.assertNotNull(resultPassword);
        Assertions.assertTrue(resultPassword.length() > 0);
    }
}
