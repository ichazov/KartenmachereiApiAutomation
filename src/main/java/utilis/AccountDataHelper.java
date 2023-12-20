package utilis;

import org.apache.commons.lang3.*;

public class AccountDataHelper {

    private AccountDataHelper() {
    }

    public static String generateEmailAddress(int length) {
        return RandomStringUtils.randomAlphanumeric(length)
                .concat("@mailsac.com")
                .toLowerCase();
    }

    public static String generatePassword(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }
}
