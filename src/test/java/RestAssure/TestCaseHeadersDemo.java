package RestAssure;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import io.restassured.http.Header;
import io.restassured.http.Headers;


public class TestCaseHeadersDemo {

    @Test
    void testHeaders(){
        given()
        .when()
                .get("https://www.google.com/")
        .then()
                .header("Content-Type","text/html; charset=ISO-8859-1")
                .and()
                .header("Content-Encoding","gzip")
                .and()
                .header("Server","gws");
    }
    @Test
    void getHeader(){
        Response rs = given().when().get("https://www.google.com/");
        String server = rs.getHeader("Server");
        System.out.println(server);

        Headers allHeaders = rs.getHeaders();
        for (Header hd : allHeaders) {
            System.out.println(hd.getName()+" "+hd.getValue());
        }
    }
}
