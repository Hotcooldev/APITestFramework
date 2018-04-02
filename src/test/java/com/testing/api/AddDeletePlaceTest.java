package com.testing.api;

import com.testing.api.googleAPI.Payload;

import com.testing.api.googleAPI.MapResources;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;



public class AddDeletePlaceTest extends BaseTest {

    @Test

    public void AddAndDeleteplace(){

        log.info("Host information " + prop.getProperty("HOST"));
        log.info("Preparing to add new place.");
        Response response = given().log().all().
                queryParam("key",prop.getProperty("KEY")).
                body(Payload.getPostData()).
                when().post(MapResources.getPostPlacePath()).
                then().assertThat().statusCode(200).contentType(ContentType.JSON).log().all().
                body("status",equalTo("OK")).
                extract().response();
        log.info("New place has been added.");

        String responseString = response.asString();
        log.info("This is response: " + responseString);


        log.info("Preparig to take placeId from the response.");
        JsonPath jsPath = new JsonPath(responseString);
        String placeId = jsPath.get("place_id");
        log.info("This is: placeId: " + placeId);


        log.info("Preparing to delete added place - we wil place placeId in the delete request.");
        RestAssured.given().
                queryParam("key",prop.getProperty("KEY")).
                body(Payload.getDeleteData(placeId)).when().post(MapResources.getDeletePlacePath()).
                then().assertThat().statusCode(200).contentType(ContentType.JSON).
                body("status",equalTo("OK"));
        log.info("Place has been deleted successfully.");


        log.info("Deleting one more time. This time place should not be found.");
        RestAssured.given().
                queryParam("key","AIzaSyBLpEDY8okhTPoj61Rai79C1SNI5h3YC0Y").
                body(Payload.getDeleteData(placeId)).when().post("/maps/api/place/delete/json").
                then().assertThat().statusCode(200).contentType(ContentType.JSON).
                body("status",equalTo("NOT_FOUND"));
        log.info("Place not found.");
    }
}
