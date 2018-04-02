package com.testing.api;

import io.restassured.RestAssured;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {

    protected Properties prop = new Properties();
    protected static Logger log = LogManager.getLogger(BaseTest.class.getName());

    @BeforeTest
    public void getData() throws IOException {


        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\environment.properties");
        try {
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        RestAssured.baseURI = prop.getProperty("HOST");

    }
}