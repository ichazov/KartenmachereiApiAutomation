package utilis;

import org.apache.commons.lang3.*;

public class UserDataHelper {

    private UserDataHelper() {
    }

    public static String getName(int length) {
        return RandomStringUtils.randomAlphabetic(length).toUpperCase();
    }
}
