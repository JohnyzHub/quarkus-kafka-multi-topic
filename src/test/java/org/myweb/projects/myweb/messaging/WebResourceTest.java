package org.myweb.projects.myweb.messaging;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class WebResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/products")
          .then()
             .statusCode(200)
             .body(is("Hello from RESTEasy Reactive"));
    }

}