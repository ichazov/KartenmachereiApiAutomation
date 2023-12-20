package utilis;

import org.openqa.selenium.*;

import java.util.*;
import java.util.stream.*;

import static com.codeborne.selenide.Selenide.refresh;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class CookieHelper {

    private CookieHelper() {
    }

    public static void addCookie(Cookie cookie) {
        getWebDriver().manage().addCookie(cookie);
        refresh();
    }

    public static Map<String, String> getCookiesMap() {
        return getWebDriver().manage().getCookies()
                .stream()
                .collect(Collectors.toMap(Cookie::getName, Cookie::getValue));
    }

    public static Cookie provideSessionCookie(String session) {
        return new Cookie(
                "frontend",
                session,
                "www.kartenmacherei.de",
                "/",
                DateHelper.getDays(14)
        );
    }
}
