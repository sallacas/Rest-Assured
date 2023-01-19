package RestAssure;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
public class HttpRequests {

    int id;
    @Test(priority = 1)
    void getUsers() {
        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body("page", equalTo(2))
                .log().all();
    }

    /*@Test(priority = 2)
    void createUser() {
        HashMap user = new HashMap();
        user.put("name", "Joselito");
        user.put("job", "Trainer");

        given()
                .contentType("application/json")
                .body(user)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .log().all();
    }*/

    @Test(priority = 2)
    void createUser() {
        HashMap user = new HashMap();
        user.put("name", "Joselito");
        user.put("job", "Trainer");

        id = given()
                .contentType("application/json")
                .body(user)

                .when()
                .post("https://reqres.in/api/users")
                .jsonPath().getInt("id");
    }
    @Test(priority = 3, dependsOnMethods = {"createUser"})
    void updateUser(){
        HashMap user = new HashMap();
        user.put("name", "Pedro");
        user.put("job", "Peligro");
        given()
                .contentType("application/json")
                .body(user)
        .when()
                .put("https://reqres.in/api/users/"+id)
        .then()
                .statusCode(200)
                .log().body().extract().jsonPath().getString("updatedAt");
    }
    @Test(priority = 4)
    void deleteUser(){
        given()
        .when()
                .delete("https://reqres.in/api/users/"+id)
        .then()
                .statusCode(204)
                .log().all();
    }
}
