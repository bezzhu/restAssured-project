import io.restassured.http.ContentType;

import io.restassured.response.Response;
import junit.framework.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;

public class XMLTests {
    Response response;
    public XMLTests(){
        response = given()
                .accept(ContentType.XML)
                .when()
                .get("http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso/ListOfContinentsByName");

    }
    @Test
    public void validateCountOfAllSNameNode(){
        int count = response.then().extract().path("ArrayOftContinent.tContinent.sName.size()");
        Assert.assertEquals(count, 6);
    }
    @Test
    public void validateListOfAllSNameNodesValue(){

    }
    @Test
    public void validatesNameNodeResultWithValueOfsCodeEqualsToAN(){
        String sName = response.then().extract().path("**.find{it.sCode =='AN'}.sName");
        Assert.assertEquals(sName, "AN");
    }
    @Test
    public void validateTheLastTContinentNodesSNameValue(){
        String lastsName = response.then().extract().path("ArrayOftContinent.tContinent[-1].sName");
        Assert.assertEquals(lastsName,"The Americas");
    }

}
