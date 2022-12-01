package br.com.valetech.smartmusicchoice;

import br.com.valetech.smartmusicchoice.setup.DynamoSetup;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Objects;

import static io.restassured.RestAssured.given;

@QuarkusTest
@QuarkusTestResource(DynamoSetup.class)
class GetIntegrationTest {

    @SneakyThrows
    @Test
    void testInstrumentGetEndpoint() {
        var response = loadJson();

        given()
                .when()
                .get("/instrument")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .asString()
                .equals("["+response+"]");
    }

    protected static JsonObject loadJson() {
        try {
            return Buffer.buffer(
                            Objects.requireNonNull(
                                            GetIntegrationTest.class
                                                    .getClassLoader()
                                                    .getResourceAsStream("files/instrument.json"))
                                    .readAllBytes())
                    .toJsonObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
