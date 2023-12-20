package providers;

import constants.*;
import io.restassured.builder.*;
import io.restassured.http.*;
import io.restassured.specification.*;

public class RequestSpecProvider {

    public static RequestSpecification provideRequestSpec(String basePath) {
        return new RequestSpecBuilder()
                .setBaseUri(Urls.HOME)
                .setContentType(ContentType.URLENC)
                .setBasePath(basePath)
                .build();
    }
}
