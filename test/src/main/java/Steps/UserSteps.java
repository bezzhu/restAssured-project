package Steps;

import Models.Requests.User;
import Models.Requests.UserRecord;
import Models.Responses.User406;
import Models.Responses.UserResponse;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

public class UserSteps {
    User user = new User();
    UserRecord userRecord;
    Response response;
    public String userID;
    public UserSteps addUser(){
        userRecord = new UserRecord(user.username, user.password);
          response = given()
                .filter(new AllureRestAssured())
                .baseUri("https://bookstore.toolsqa.com")
                .contentType(ContentType.JSON)
                .body(userRecord)
                .when()
                .post("/Account/v1/User");

          if(response.getStatusCode() == 406){
                response.getBody().as(User406.class);
          }
        return this;
    }
    public UserSteps CheckThatBooksListEqualsToNull() {
        if (response.statusCode() == 201) {
            Assert.assertTrue( response.getBody().as(UserResponse.class).books().isEmpty());
        }
        return this;
    }
}
