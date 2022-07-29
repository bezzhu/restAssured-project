import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DDTTest {

    @Test(dataProvider = "data")
    public void test1(String row,String country, String circuitId) {
        String circuitId1 = given().
                get("http://ergast.com/api/f1/2017/circuits.json").
        then().
                body("MRData.CircuitTable.Circuits["+ row + "].Location.country",equalTo(country)).
                extract().
                path("MRData.CircuitTable.Circuits["+ row +"].circuitId");
        given().
                pathParam("circuitId", circuitId1).
                when().
                get("http://ergast.com/api/f1/circuits/{circuitId}.json").
                then().
                assertThat().
                body("MRData.CircuitTable.circuitId",equalTo(circuitId));
    }
    @DataProvider(name="data")
    public Object[][] getDataFromDataProvider(){
        return new Object[][]
                {
                        { "1", "USA", "americas" },
                        { "5", "Hungary", "hungaroring" },
                };

    }

}
