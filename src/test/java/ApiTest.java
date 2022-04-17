import io.restassured.http.Cookies;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class ApiTest extends TestBase{

    //Save Cookies
    Cookies cookieAuth = given()
            .contentType("application/x-www-form-urlencoded; charset=UTF-8")
            .body("__RequestVerificationToken=fy91s48kOV6JI2uhRaadj6YbHluA0aOSceSPEOn6GA6Jyn53S4Q12j7tg" +
                    "_doMunuzqkc_pP33s4d_y16QCeYg4zZB_A3P08UkO9F39PexAE1" +
                    "&Gender=M&FirstName=TestFirstName" +
                    "&LastName=TestLastName" +
                    "&Email=test1%40banx.ru" +
                    "&Password=test123" +
                    "&ConfirmPassword=test123" +
                    "&register-button=Register")
            .when()
            .post("/register")
            .then()
            .log().status()
            .log().body()
            .statusCode(302)
            .extract()
            .response()
            .getDetailedCookies();


    @BeforeAll
    static void setUp() {

    }


    @Test
    @DisplayName("Add to wishlist")
    void addToWishListAuth() {
        int quantityAddWihList = 5;

        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .cookie(cookieAuth.toString())
                .body("giftcard_2.RecipientName=TestName" +
                        "&giftcard_2.RecipientEmail=test2%40banx.ru" +
                        "&giftcard_2.SenderName=TestFirstName TestLastName" +
                        "&giftcard_2.SenderEmail=test1%40banx.ru" +
                        "&giftcard_2.Message=test5" +
                        "&addtocart_2.EnteredQuantity=" + quantityAddWihList)
                .when()
                .post("/addproducttocart/details/2/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your <a href=\"/wishlist\">wishlist</a>"))
                .body("updatetopwishlistsectionhtml", is("(" + quantityAddWihList + ")"));

    }

    @Test
    @DisplayName("Add to cart")
    void addToCartAuth() {
        int quantityAddToCart = 10;

        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .cookie(cookieAuth.toString())
                .body("product_attribute_72_5_18=53" +
                        "&product_attribute_72_6_19=54" +
                        "&product_attribute_72_3_20=57" +
                        "&addtocart_72.EnteredQuantity=" + quantityAddToCart)
                .when()
                .post("/addproducttocart/details/72/1")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"))
                .body("updatetopcartsectionhtml", is("(" + quantityAddToCart + ")"));

    }

    }




