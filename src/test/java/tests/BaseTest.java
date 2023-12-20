package tests;

import com.codeborne.selenide.*;
import constants.*;
import fragments.*;
import io.restassured.http.*;
import io.restassured.specification.*;
import org.testng.annotations.*;
import providers.*;
import utilis.*;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.open;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

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

    String getSessionValue(RequestSpecification requestSpec, String email, String password) {
        return given().spec(requestSpec)
                .cookies(CookieHelper.getCookiesMap())
                .cookie(wallArtsGroupCookie)
                .formParams(UserFormParamsProvider.provideLoginFormParams(
                        email,
                        password
                ))
                .when()
                .post("/loginPost")
                .then()
                .body("code", equalTo("ok"))
                .extract().response().getCookie("frontend");
    }

    void executeBasicSteps(String sessionValue) {
        open(Urls.HOME);
        cookieConsentDialog.clickAcceptNecessaryButton();
        headerComponent.clickAccountIcon();
        CookieHelper.addCookie(CookieHelper.provideSessionCookie(sessionValue));
        refresh();
    }
}
