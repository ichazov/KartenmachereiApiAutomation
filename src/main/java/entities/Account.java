package entities;

import lombok.*;
import utilis.*;

import java.io.*;
import java.util.*;

public class Account {

    private static final String EMAIL_CONSTANT = "email";

    private static final String PASSWORD_CONSTANT = "password";

    @Getter
    private String email;

    @Getter
    private String password;

    public Account() {
        setEmail();
        setPassword();
    }

    private Account(String email, String password) {
        this.email = email;
        this.password = password;
    }

    private void setEmail() {
        this.email = AccountDataHelper.generateEmailAddress(6);
    }

    private void setPassword() {
        this.password = AccountDataHelper.generatePassword(8);
    }

    public Account getDefaultAccount() {
        Map<String, String> creds = prepareDefaultCreds();
        return new Account(creds.get(EMAIL_CONSTANT), creds.get(PASSWORD_CONSTANT));
    }

    private Map<String, String> prepareDefaultCreds() {
        Map<String, String> creds = new HashMap<>(2);
        Properties properties = new Properties();
        String credentialsFile = "creds.properties";

        try {
            InputStream inputStream = AccountDataHelper.class.getClassLoader().getResourceAsStream(credentialsFile);
            properties.load(inputStream);
            creds.put(EMAIL_CONSTANT, properties.getProperty(EMAIL_CONSTANT));
            creds.put(PASSWORD_CONSTANT, properties.getProperty(PASSWORD_CONSTANT));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return creds;
    }
}
