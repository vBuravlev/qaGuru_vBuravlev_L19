import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;
import static listeners.CustomAllureListener.withCustomTemplates;

public class TestBase {

    @BeforeAll
    static void beforeAll() {
        RestAssured.baseURI = "http://demowebshop.tricentis.com";
        RestAssured.filters(withCustomTemplates());
    }
}
