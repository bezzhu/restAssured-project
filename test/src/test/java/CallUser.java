import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CallUser {
    @Test
    public void callUser(){
        UsersParam user = new UsersParam();
        user.name = "morpheus";
        user.job = "leader";

        given()
                .baseUri("https://reqres.in")
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post("/api/users")
                .then()
                .log().all()
                .assertThat()
                .statusCode(201);
    }
}
