package providers;

import entities.*;

import java.util.*;

public class UserFormParamsProvider {

    private UserFormParamsProvider() {
    }

    public static Map<String, String> provideLoginFormParams(User user) {
        return Map.of(
                "login[username]", user.getAccount().getEmail(),
                "login[password]", user.getAccount().getPassword()
        );
    }

    public static Map<String, String> provideLoginFormParams(String username, String password) {
        return Map.of(
                "login[username]", username,
                "login[password]", password
        );
    }

    public static Map<String, String> provideRegistrationFormParams(User user) {
        return Map.of(
                "firstname", user.getFirstName(),
                "lastname", user.getLastName(),
                "email", user.getAccount().getEmail(),
                "password", user.getAccount().getPassword(),
                "confirmation", user.getAccount().getPassword()
        );
    }
}
