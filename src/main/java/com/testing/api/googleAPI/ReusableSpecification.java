package com.testing.api.googleAPI;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import java.util.Properties;
import static com.testing.api.utils.Constants.*;



public class ReusableSpecification {

    public static RequestSpecBuilder requestSpecBuilder;
    public static RequestSpecification requestSpecification;


    public static RequestSpecification getRequestSearchPlaceValid(){

        Properties prop = new Properties();

        requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.addQueryParam("location", LAT + "," + LONGI );
        requestSpecBuilder.addQueryParam("radius",RADIUS);
        requestSpecBuilder.addQueryParam("key",KEY);

        requestSpecification = requestSpecBuilder.build();
        return requestSpecification;
    }

    public static RequestSpecification getRequestSearchPlaceMissingParameter(){

        requestSpecBuilder = new RequestSpecBuilder();

        requestSpecBuilder.addQueryParam("location",LAT + "," + LONGI);
        requestSpecBuilder.addQueryParam("key",KEY);

        requestSpecification = requestSpecBuilder.build();
        return requestSpecification;
    }
/*
    public static RequestSpecification getRequestSearchPlace(String latitude, String longitude, String radius){
        requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.addQueryParam("location", latitude + "," + longitude );
        requestSpecBuilder.addQueryParam("radius",radius);
        requestSpecBuilder.addQueryParam("key",KEY);

        requestSpecification = requestSpecBuilder.build();
        return requestSpecification;
    }
    */



}
