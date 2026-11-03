package com.saas.tourismagency.admin.backoffice.core.password;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class PasswordService {

    @Value("${application.secret.passwordhash}")
    private String secretPasswordHash;

    public String hash(String plainPass, String salt) {
        return hash256(concat(plainPass, salt));
    }

    public boolean matches(String plainPass, String salt, String hash) {
        return hash256(concat(plainPass, salt)).equalsIgnoreCase(hash);
    }

    public String concat(String plainPass, String salt) {
        return plainPass + salt + secretPasswordHash;
    }

    private String hash256(String text) {
        return String.format(
                "%1$064x", new java.math.BigInteger(
                        1, hash256(
                        text.getBytes()
                )
                )
        );
    }

    private byte[] hash256(byte[] text) {
        try {
            MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
            return mDigest.digest(text);
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
