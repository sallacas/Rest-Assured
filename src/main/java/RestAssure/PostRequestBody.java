package RestAssure;

import static io.restassured.RestAssured.*;

import RestAssure.data.StudentModel;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import java.io.FileReader;
import java.util.HashMap;

import java.io.FileNotFoundException;

public class PostRequestBody {

    //@Test(priority = 1) - Test using HashMap for data
    void testPostUsingHashMap(){
        HashMap data = new HashMap();
        data.put("name","Thiago");
        data.put("location","spain");
        data.put("phone","31123145");

        String[] courses = {"C++","Java"};
        data.put("courses",courses);

        given()
                .contentType("application/json")
                .body(data)
        .when()
                .post("http://localhost:3000/students")
        .then()
                .statusCode(201)
                .body("name", equalTo(data.get("name")))
                .body("location", equalTo(data.get("location")))
                .body("phone", equalTo(data.get("phone")))
                .body("courses[0]", equalTo(courses[0]))
                .body("courses[1]", equalTo(courses[1]))
                .log().all();
    }

    //@Test(priority = 1) - Test using org.json library for data
    void testPostUsingJSONLibrary(){
        JSONObject data = new JSONObject();

        data.put("name","Thiago");
        data.put("location","spain");
        data.put("phone","31123145");

        String[] courses = {"C++","Java"};
        data.put("courses",courses);

        given()
                .contentType("application/json")
                .body(data.toString())
                .when()
                .post("http://localhost:3000/students")
                .then()
                .statusCode(201)
                .body("name", equalTo(data.get("name")))
                .body("location", equalTo(data.get("location")))
                .body("phone", equalTo(data.get("phone")))
                .body("courses[0]", equalTo(courses[0]))
                .body("courses[1]", equalTo(courses[1]))
                .log().all();
    }
    //@Test(priority = 1) - Test using POJO or Model class
    void testPostUsingPOJO(){
        StudentModel data = new StudentModel();

        data.setName("Thiago");
        data.setLocation("spain");
        data.setPhone("31123145");

        String[] courses = {"C++","Java"};
        data.setCourses(courses);

        given()
                .contentType("application/json")
                .body(data)
                .when()
                .post("http://localhost:3000/students")
                .then()
                .statusCode(201)
                .body("name", equalTo(data.getName()))
                .body("location", equalTo(data.getLocation()))
                .body("phone", equalTo(data.getPhone()))
                .body("courses[0]", equalTo(data.getCourses()[0]))
                .body("courses[1]", equalTo(data.getCourses()[1]))
                .log().all();
    }
    @Test(priority = 1)
    void testPostUsingFile(){
        try {
            JSONObject data = new JSONObject(new JSONTokener(new FileReader("src/main/resources/student.json")));

            given()
                    .contentType("application/json")
                    .body(data.toString())
                    .when()
                    .post("http://localhost:3000/students")
                    .then()
                    .statusCode(201)
                    .body("name", equalTo(data.get("name")))
                    .body("location", equalTo(data.get("location")))
                    .body("phone", equalTo(data.get("phone")))
                    .body("courses[0]", equalTo("C++"))
                    .body("courses[1]", equalTo("Java"))
                    .log().all();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @Test(priority = 2)
    void testDelete(){
        given()
        .when()
                .delete("http://localhost:3000/students/5")
        .then()
                .statusCode(200);
    }
}
