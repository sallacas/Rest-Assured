package RestAssure;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import java.util.Map;

public class TestCaseCookieDemo {

    @Test(priority = 1)
    void testCookies(){
        given()

        .when()
                .get("https://www.google.com/")
        .then()
                .cookie("AEC",notNullValue())
                .log().all();
    }
    @Test(priority = 2)
    void getCookiesInfo(){
        Response res = given().when().get("https://www.google.com/");
        String cookieValue = res.getCookie("AEC");
        System.out.println("Cookie value is "+cookieValue);
        Map<String, String> allCookieValues = res.getCookies();
        for (String cookie : allCookieValues.keySet()) {
            System.out.println(res.getCookie(cookie));
        }

    }

}
