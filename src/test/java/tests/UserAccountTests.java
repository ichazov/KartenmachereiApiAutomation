package tests;

import entities.*;
import io.restassured.specification.*;
import org.testng.annotations.*;
import providers.*;
import utilis.*;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class UserAccountTests extends BaseTest {

    static User user;

    RequestSpecification requestSpec = RequestSpecProvider.provideRequestSpec("/ajaxwishlist/ajax");

    @BeforeClass
    static void beforeAll() {
        user = new User();
    }

    @Test
    void invalidCredentialsLogInTest() {
        executeBasicSteps();
        given().spec(requestSpec)
                .cookies(CookieHelper.getCookiesMap())
                .formParams(UserFormParamsProvider.provideLoginFormParams(user))
                .when()
                .post("/loginPost")
                .then()
                .body("message", equalTo("Fehlerhafter Anmeldename oder Passwort"))
                .body("code", equalTo("error"));
    }

    @Test
    void userRegistrationTest() {
        executeBasicSteps();
        given().spec(requestSpec)
                .cookies(CookieHelper.getCookiesMap())
                .cookie(wallArtsGroupCookie)
                .formParams(UserFormParamsProvider.provideRegistrationFormParams(user))
                .when().post("/registerPost")
                .then()
                .body("code", equalTo("ok"));
    }

    @Test(dependsOnMethods = {"userRegistrationTest"})
    void logInTest() {
        executeBasicSteps();
        String session = given().spec(requestSpec)
                .cookies(CookieHelper.getCookiesMap())
                .cookie(wallArtsGroupCookie)
                .formParams(UserFormParamsProvider.provideLoginFormParams(user))
                .when()
                .post("/loginPost")
                .then().extract().response().getCookie("frontend");

        CookieHelper.addCookie(CookieHelper.provideSessionCookie(session));

        assertThat(headerComponent.isUserLoggedIn())
                .as("User is not logged in")
                .isTrue();
    }
}
