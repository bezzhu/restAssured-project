import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class SuccessResponse {
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
        register.setPassword("pistol");
        return register;
    }
    @Test
    public void successfulRegister() {
        Response response = sendRequest(getJsonData());
        ResponseBody body = response.getBody();

        if(response.getStatusCode() == 200) {
            RegistrationSuccessResponse responseBody = body.as(RegistrationSuccessResponse.class);
            assertEquals(responseBody.getId() , "4");
            assertEquals(responseBody.getToken() , "QpwL5tke4Pnpja7X4");
        }
    }
}
