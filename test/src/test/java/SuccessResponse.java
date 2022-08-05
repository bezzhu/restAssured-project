import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class SuccessResponse {

    @Test
    public void successfulRegister() {
        Register register = new Register();
        register.email = "eve.holt@reqres.in";
        register.password = "pistol";

         RequestSpecification request =  given()
                .baseUri("https://reqres.in")
                .contentType(ContentType.JSON)
                .body(register);

         Response response = request.post("/api/register");

        ResponseBody body = response.getBody();

        if(response.getStatusCode() == 200) {
            RegistrationSuccessResponse responseBody = body.as(RegistrationSuccessResponse.class);
            assertEquals(responseBody.id , "4");
            assertEquals(responseBody.token , "QpwL5tke4Pnpja7X4");
        }
    }
}
