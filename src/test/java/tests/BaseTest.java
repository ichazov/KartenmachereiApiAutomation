package tests;

import com.codeborne.selenide.*;
import constants.*;
import fragments.*;
import io.restassured.http.*;
import org.testng.annotations.*;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.open;

public class BaseTest {

    CookieConsentDialog cookieConsentDialog = new CookieConsentDialog();

    HeaderComponent headerComponent = new HeaderComponent();

    static Cookie wallArtsGroupCookie = new Cookie.Builder("cb-wall-arts-group-v2", "A").build();

    @BeforeMethod
    void setUp() {
        Configuration.browser = "Chrome";
        open();
        clearBrowserCookies();
    }

    @AfterMethod
    void tearDown() {
        closeWebDriver();
    }

    void executeBasicSteps() {
        open(Urls.HOME);
        cookieConsentDialog.clickAcceptNecessaryButton();
        headerComponent.clickAccountIcon();
    }
}
