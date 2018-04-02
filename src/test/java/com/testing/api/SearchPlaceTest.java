package com.testing.api;

import com.testing.api.googleAPI.MapResources;
import com.testing.api.googleAPI.ReusableSpecification;
import io.restassured.RestAssured;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_METHOD_NOT_ALLOWED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.equalTo;

public class SearchPlaceTest extends BaseTest {



    @Test
    public void testGetGooglePlacesValid() {

        log.info("Test testGetGooglePlacesValid will be started");
        given().
                spec(ReusableSpecification.getRequestSearchPlaceValid()).
                when().get(MapResources.getNearbySearchPath()).
                then().assertThat().
                statusCode(200).
                body("status",equalTo("OK"));
        log.info("Test testGetGooglePlacesValid finished.");

    }

    @Test
    public void testGetGooglePlacesMissingParam(){
        log.info("Test testGetGooglePlacesMissing Param will be started");
        given().
                spec(ReusableSpecification.getRequestSearchPlaceMissingParameter()).
                when().get(MapResources.getNearbySearchPath()).
                then().assertThat().
                statusCode(200).and().
                body("status",equalTo("INVALID_REQUEST"));
        log.info("Test testGetGooglePlacesMissing is finished.");
    }

    @Test
    public void testStaticMapMissingParameters(){
        log.info("Test testStaticMapMissingParameters will be started");
        RestAssured.when().
                get(MapResources.getStaticMapPath()).
                then().assertThat().
                statusCode(400);
        log.info("Test testStaticMapMissingParameters is finished.");
    }

    @Test
    public void testGetGooglePlacesNotFound(){
        log.info("Test testGetGooglePlacesNotFound will be started");
        given().
                spec(ReusableSpecification.getRequestSearchPlaceValid()).
                when().get(MapResources.getNearbySearchWrongPath()).
                then().assertThat().
                statusCode(404);
        log.info("Test testGetGooglePlacesNotFound is finished.");
    }
/*
    @DataProvider(name = "invalid_method")
    public Object[][] methods() {
        return new Object[][]{
                {" ", SC_OK,"INVALID_REQUEST"},
                {"-500", SC_OK,"INVALID_REQUEST"},

        };
    }

    @Test(dataProvider = "invalid_method")
    public void testDifferentRadiusValues(String radiusValues, int expectedStatus, String body){
        log.info("Test testGetGooglePlacesNotFound will be started");
        given().
                spec(ReusableSpecification.getRequestSearchPlace(radiusValues)).
                when().get(MapResources.getNearbySearchPath()).
                then().assertThat().
                statusCode(expectedStatus).and().
                body("status",equalTo(body));;


    }
    */
}

