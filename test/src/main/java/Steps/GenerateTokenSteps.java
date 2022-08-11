package Steps;

import Models.Requests.User;
import Models.Requests.UserRecord;
import Models.Responses.GenerateTokenResponse;
import io.restassured.http.ContentType;
import junit.framework.Assert;

import static io.restassured.RestAssured.given;

public class GenerateTokenSteps {
    User user = new User();
    UserRecord userRecord = new UserRecord(user.username, user.password);
    GenerateTokenResponse response;
    String token;
    public GenerateTokenSteps generateToken(){
         response = given()
                .baseUri("https://bookstore.toolsqa.com")
                .contentType(ContentType.JSON)
                .body(userRecord)
                .when()
                .post("/Account/v1/GenerateToken")
                .as(GenerateTokenResponse.class);
         token = response.token();
        return this;
    }
    public GenerateTokenSteps ValidateThatStatusIsEqualsToSuccess(){
        Assert.assertEquals("Success", response.status());
        return this;
    }
    public GenerateTokenSteps ValidateThatResultIsEqualsToUserAuthorizedSuccessfully(){
        Assert.assertEquals("User authorized successfully.", response.result());
        return this;
    }
}
