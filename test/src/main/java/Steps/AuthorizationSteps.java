package Steps;

import Models.Requests.User;
import Models.Requests.UserRecord;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class AuthorizationSteps {
    User user = new User();
    UserRecord userRecord;
    public AuthorizationSteps CheckThatUserBecameAuthorized(){
        userRecord = new UserRecord(user.username , user.password);
            given()
                    .filter(new AllureRestAssured())
                    .baseUri("https://bookstore.toolsqa.com")
                    .contentType(ContentType.JSON)
                    .body(userRecord)
                    .when()
                    .post("/Account/v1/Authorized")
                    .getBody()
                    .equals(true);
        return this;
    }
}
