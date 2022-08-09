import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class UnSuccessResponse {
    Register register;
    public Response sendRequest(Register register){
        return  given()
                .filter(new AllureRestAssured())
                .baseUri("https://reqres.in")
                .contentType(ContentType.JSON)
                .body(register)
                .when()
                .post("/api/register");
    }
    public Register getJsonData(){
        register = new Register();
        register.setEmail("eve.holt@reqres.in");
        return register;
    }
    @Test
    public void unSuccessfulRegister() {
        Response response = sendRequest(getJsonData());
        ResponseBody body = response.getBody();

        if(response.getStatusCode() == 400) {
            RegistrationUnSuccessResponse responseBody = body.as(RegistrationUnSuccessResponse.class);
            assertEquals(responseBody.getError() , "Missing password");
        }

    }
}
