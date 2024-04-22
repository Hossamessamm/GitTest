import com.github.javafaker.Faker;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class Tc_api {
    Faker faker = new Faker();
    String name = faker.name().firstName();
    String job = faker.job().position();
    @Test
    public void TC_01(){
        // -----------Request body----------
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("name", name);
        requestBody.put("job", job);
        //------------request -----------------
        Response response = given()
                .baseUri("https://reqres.in")
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .filter(new AllureRestAssured())
                .post("/api/users")
                .then()
                .log().all()
                .statusCode(404) // The correct status code for POST method should be 201
                .extract().response();
        String job = response.jsonPath().getString("job");
        System.out.println(job);
    }


    @Test
    public void TC_Delete(){
        Response response = given()
                .baseUri("https://reqres.in")
                .contentType(ContentType.JSON)
                .filter(new AllureRestAssured())
                .delete("/api/users/2")
                .then()
                .log().all()
                .extract().response();
        if (response.getStatusCode() == 204) {
            System.out.println("done");
        } else {
            System.out.println("fail");
        }
    }

    @Test
    public void put(){
        Map<String,String> body = new HashMap<>();
        body.put("name", "morpheus");
        body.put("job","zion resident");

        Response response = given()
                .baseUri("https://reqres.in")
                .body(body)
                .filter(new AllureRestAssured())
                .put("/api/users/2")
                .then()
                .log().all()
                .extract().response();
    }
    @Test
    public void TcGet(){
        Response response = given()
                .baseUri("https://reqres.in")
                .contentType(ContentType.JSON)
                .filter(new AllureRestAssured()) // this line connect between restassured report and the result
                .get("/api/unknown/2")
                .then()
                .log().all()
                .extract().response();
            String message = response.jsonPath().getString("support.text");
        System.out.println("the text is : " + message);
    }

}

