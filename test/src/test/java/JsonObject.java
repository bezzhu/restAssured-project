import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import static io.restassured.RestAssured.given;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class JsonObject {

    @Test
    public void updateTestUsingJson(){
        JSONObject json = new JSONObject();
        json.put("firstname", "user");
        json.put("lastname", "user");

        // given:
        RequestSpecification request = given()
                .header("Content-Type", "application/json")
                .body(json);
        // when:
        Response response =
                given()
                        .spec(request)
                        .put("https://reqres.in/api/users/2");
        response.then().log().ifStatusCodeIsEqualTo(200);;
    }

    @Test
    public void extractValueUsingPath() {

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);

        JsonPath jsonPath = given()
                .get("https://chercher.tech/sample/api/product/read")
                .then().extract()
                .jsonPath();

        assertEquals(jsonPath.get("records[-1].name").toString(), "");

        String time = jsonPath.get("records.created").toString();
        String tim = time.substring(1, time.length() -1);
        String[] str = tim.split(", ");
        for (String s : str) {
            assertTrue(s.compareTo(formattedDate) == 2);
        }

    }
}
