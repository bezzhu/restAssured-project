import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class UnSuccessResponse {

    @Test
    public void unSuccessfulRegister() {
        Register register = new Register();
        register.email = "eve.holt@reqres.in";

        RequestSpecification request = given()
                .baseUri("https://reqres.in")
                .contentType(ContentType.JSON)
                .body(register);

        Response response = request.post("/api/register");

        ResponseBody body = response.getBody();

        if(response.getStatusCode() == 400) {
            RegistrationUnSuccessResponse responseBody = body.as(RegistrationUnSuccessResponse.class);
            assertEquals(responseBody.error , "Missing password");
        }

    }
}
