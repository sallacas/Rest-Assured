package RestAssure;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TestCaseParsingJSONResponseData {

    @Test(priority = 1)
    void testJsonResponseBodyData(){
        given()
                .contentType(ContentType.JSON)
        .when()
                .get("http://localhost:3000/store")
        .then()
                .statusCode(200)
                .header("Content-Type","application/json; charset=utf-8")
                .body("book[3].title",equalTo("The lord of the Rings"));
    }

    @Test
    void testJsonResponseObject(){
        Response rs = given().contentType(ContentType.JSON).when().get("http://localhost:3000/store");
        Assert.assertEquals(rs.statusCode(),200);
        Assert.assertEquals(rs.header("Content-Type"),"application/json; charset=utf-8");
        Assert.assertEquals(rs.jsonPath().get("book[3].title"),"The lord of the Rings");
    }
    @Test
    void testCase(){
        Response rs = given().contentType(ContentType.JSON).when().get("http://localhost:3000/store");
        JSONObject jobj = new JSONObject(rs.asString());
        boolean status = false;
        for (int i = 0; i < jobj.getJSONArray("book").length(); i++){
            String title = jobj.getJSONArray("book").getJSONObject(i).getString("title");
            if (title.equals("The lord of the Rings")){
                status = true;
                break;
            }
        }
        Assert.assertTrue(status);
    }
}
