package tests;

import entities.*;
import io.restassured.response.*;
import io.restassured.specification.*;
import org.testng.annotations.*;
import providers.*;
import utilis.*;

import java.util.*;
import java.util.stream.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class CartTests extends BaseTest {

    static Account defaultAccount;

    static String cartItemId;

    static EnvelopeProduct product = new EnvelopeProduct(
            "EF04",
            "EF04",
            "EC080",
            "10"
    );

    RequestSpecification requestSpec = RequestSpecProvider.provideRequestSpec("/ajaxwishlist/ajax");

    @BeforeClass
    public void beforeClass() {
        defaultAccount = new Account().getDefaultAccount();
    }

    String getSessionValue() {
        return given().spec(requestSpec)
                .cookies(CookieHelper.getCookiesMap())
                .cookie(wallArtsGroupCookie)
                .formParams(UserFormParamsProvider.provideLoginFormParams(
                        defaultAccount.getEmail(),
                        defaultAccount.getPassword()
                ))
                .when()
                .post("/loginPost")
                .then()
                .body("code", equalTo("ok"))
                .extract().response().getCookie("frontend");
    }

    @Test
    public void addProductToCartTest() {
        executeBasicSteps(getSessionValue());

        Map<String, String> cookies = CookieHelper.getCookiesMap();

        ValidatableResponse response = given().spec(requestSpec)
                .cookies(cookies)
                .cookie(wallArtsGroupCookie)
                .formParams(ProductFormParamsProvider.provideProductFormParams(product))
                .when()
                .post("/addToCart")
                .then();

        response.body("code", equalTo("ok"));
        response.body("count", equalTo(1));

        cartItemId = given().spec(requestSpec)
                .basePath("/checkout/cart/")
                .cookies(cookies)
                .cookie(wallArtsGroupCookie)
                .when()
                .get()
                .then().extract().body().htmlPath().prettify()
                .lines()
                .filter(l -> l.contains("data-basket-item-id"))
                .collect(Collectors.joining())
                .replaceAll("\\D", "")
                .substring(1, 10);
    }

    @Test(dependsOnMethods = {"addProductToCartTest"})
    public void removeItemFromCartTest() {
        executeBasicSteps(getSessionValue());

        given().spec(requestSpec)
                .basePath("/customcheckout/ajax")
                .cookies(CookieHelper.getCookiesMap())
                .cookie(wallArtsGroupCookie)
                .formParams("id", cartItemId)
                .when()
                .post("/remove")
                .then()
                .body("code", equalTo("ok"))
                .body("cartCount", equalTo(0));
    }
}
