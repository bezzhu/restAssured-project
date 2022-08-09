import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CallUser {
    UsersParam user;
    public Response sendRequest(UsersParam user){
        return  given()
                .filter(new AllureRestAssured())
                .baseUri("https://reqres.in")
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post("/api/users");
    }
    public UsersParam getJsonData(){
        user = new UsersParam();
        user.setName("leader");
        user.setJob("morpheus");
        return user;
    }

    @Test
    public void callUser1() {
        Response response = sendRequest(getJsonData());
        Assert.assertEquals(response.getStatusCode(), 201);
        response.then().log().all();
    }
}
