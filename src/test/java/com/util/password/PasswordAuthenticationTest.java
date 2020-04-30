package com.util.password;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordAuthenticationTest {

    PasswordAuthentication passwordAuthentication = new PasswordAuthentication();

    @Test
    void canHashString() {
        assertNotEquals(passwordAuthentication.hash("theNewpassword"), "theNewpassword");
    }

    @Test
    void CanAuthenticatePassword() {
        String token = passwordAuthentication.hash("theNewpassword");
        assertTrue(passwordAuthentication.authenticate("theNewpassword".toCharArray(), token));
    }

    @Test
    void DontAuthenticatePassword() {
        String token = passwordAuthentication.hash("theCorrectpassword");
        assertFalse(passwordAuthentication.authenticate("theNewpassword".toCharArray(), token));
    }
}