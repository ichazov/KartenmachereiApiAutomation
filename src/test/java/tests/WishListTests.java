package tests;

import constants.*;
import entities.*;
import io.restassured.response.*;
import io.restassured.specification.*;
import org.openqa.selenium.*;
import org.testng.annotations.*;
import providers.*;
import utilis.*;

import static com.codeborne.selenide.Selenide.*;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class WishListTests extends BaseTest {

    static String wishlistItemId;

    static Account defaultAccount;

    static EnvelopeProduct product = new EnvelopeProduct(
            "EF14",
            "EF14",
            "EC800",
            "1");

    RequestSpecification requestSpec = RequestSpecProvider.provideRequestSpec("/ajaxwishlist/ajax");

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

    @BeforeClass
    public void beforeClass() {
        defaultAccount = new Account().getDefaultAccount();
    }

    @Test
    void addProductToWishlistTest() {
        executeBasicSteps();
        CookieHelper.addCookie(CookieHelper.provideSessionCookie(getSessionValue()));

        ValidatableResponse response = given().spec(requestSpec)
                .cookies(CookieHelper.getCookiesMap())
                .cookie(wallArtsGroupCookie)
                .formParams(ProductFormParamsProvider.provideProductFormParams(product))
                .when()
                .post("/add")
                .then();

        response.body("code", equalTo("ok"));
        response.body("count", equalTo(1));
        wishlistItemId = response.extract().body().path("data.wishlistItemId").toString();

        open(Urls.HOME.concat("/wishlist"));

        assertThat($$(By.cssSelector(".products-table .product")))
                .as("Product is not added to wishlist")
                .hasSize(1);
    }

    @Test(dependsOnMethods = {"addProductToWishlistTest"})
    public void removeItemFromWishlistTest() {
        executeBasicSteps();
        CookieHelper.addCookie(CookieHelper.provideSessionCookie(getSessionValue()));

        given().spec(requestSpec)
                .cookies(CookieHelper.getCookiesMap())
                .cookie(wallArtsGroupCookie)
                .formParam("item", wishlistItemId)
                .when()
                .post("/remove")
                .then()
                .body("code", equalTo("ok"))
                .body("wishlistCount", equalTo(""));
    }
}
