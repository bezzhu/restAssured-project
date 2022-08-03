import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Hamcrest {

    @Test
    public void deleteBook(){
        given()
                .auth()
                .preemptive()
                .basic("admin" , "password123")
                .when()
                .delete("https://restful-booker.herokuapp.com/booking/115")
                .then()
                .assertThat()
                .statusCode(201);

    }
    @Test
    public void useHamcrestAssertions(){
        given()
                .get("http://ergast.com/api/f1/2017/circuits.json")
                .then()
                .body("MRData.CircuitTable.Circuits.circuitId" , hasItems("marina_bay"),
                        "MRData.CircuitTable.Circuits[-1].Location.long" , anyOf(greaterThan("1"), equalTo(10)),
                        "MRData.CircuitTable.Circuits[1 , -1].Location.country" , hasItems("USA" , "UAE"));
    }

}
