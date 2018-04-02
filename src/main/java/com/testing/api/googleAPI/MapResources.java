package com.testing.api.googleAPI;

public class MapResources {

    public static String getPostPlacePath(){

        String resource = "/maps/api/place/add/json";
        return resource;
    }

    public static String getNearbySearchPath(){

        String resource = "/maps/api/place/nearbysearch/json";
        return resource;
    }

    public static String getNearbySearchWrongPath(){

        String resource = "/maps/api/place/nearbysearch/jso";
        return resource;
    }

    public static String getStaticMapPath(){
        String resource = "/maps/api/staticmap";
        return resource;
    }

    public static String getDeletePlacePath(){
        String resource = "/maps/api/place/delete/json";
        return resource;
    }

}
