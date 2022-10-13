package liveProject;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import jdk.jfr.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class liveProject_Github{
    RequestSpecification requestSpec;
    ResponseSpecification responseSpec;
    String sshKey = "token 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";
    int id;
    String resourcePath = "/user/keys";

    @BeforeClass
    public void setUp(){
        requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://api.github.com")
                .addHeader("Content-Type","application/json")
                .addHeader("Authorization",sshKey) 
                .build();

        responseSpec = new ResponseSpecBuilder()
                .expectContentType("application/json")
                .expectStatusCode(204)
                .expectBody("message",equalTo(id))
                .build();


    }

    @Test(priority = 1)
    public void postRequest(){

       /* Map<String,String> reqBody = new HashMap<>();
        reqBody.put("title","TestAPIKey");
        reqBody.put("key", "ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQCCqN0BTnjOsoJE4P032DujQTFKv9QkQdh9dUR6zXgpUvo24iY9r9mBQq4sLhpHjjVYvUQxN9Ue7Yj7g2iJdSBVCiE+nKdg2sCv/d4XrFQ9+cd/HxE3cpSVVt81ObsGooZ/DhH3K3KgB3SZ0H7JRtO3ZYY340dCIfeJgCpWCzzmxN0vAxJgZFmXx1UFbdy6NVhAze2AKHwHq1N+i6LHIAMDRnoaSgHEZni/cjr3mXV6CljxIAL2621uU8obQWCgOgiY8BwPwIqTR5A9jrSJKpVtpGV0ZeYo5U3FX3o622l6pkFaQbY7KafqVBFHxXv2z/FFGt0jNiUWKQcdQjIBIE1p");
        */
        String reqBody = "{\"title\": \"TestAPIKey\",\"key\":\"ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQCCqN0BTnjOsoJE4P032DujQTFKv9QkQdh9dUR6zXgpUvo24iY9r9mBQq4sLhpHjjVYvUQxN9Ue7Yj7g2iJdSBVCiE+nKdg2sCv/d4XrFQ9+cd/HxE3cpSVVt81ObsGooZ/DhH3K3KgB3SZ0H7JRtO3ZYY340dCIfeJgCpWCzzmxN0vAxJgZFmXx1UFbdy6NVhAze2AKHwHq1N+i6LHIAMDRnoaSgHEZni/cjr3mXV6CljxIAL2621uU8obQWCgOgiY8BwPwIqTR5A9jrSJKpVtpGV0ZeYo5U3FX3o622l6pkFaQbY7KafqVBFHxXv2z/FFGt0jNiUWKQcdQjIBIE1p"}";

        Response response =given().spec(requestSpec)
                .body(reqBody)
                .when().post(resourcePath);

        System.out.println(response.getBody().asPrettyString());
        //Extract and save it in the variable
        id=response.then().extract().path("id");
        System.out.println("ID Value in Post Request is: "+id);
        //Assertion
        response.then().statusCode(201);

    }

    @Test(priority = 2)
    public void getRequest(){
    Response response =given().spec(requestSpec)
            .pathParam("keyId",id)
            .when().get(resourcePath+"/{keyId}");
        System.out.println("ID Value in Get Request is: "+id);
        System.out.println("Get Request Response: " +response.getBody().asString());

        response.then().body("id",equalTo(id));
    }

    @Test(priority = 3)
    public void deleteRequest(){
    Response response = given().spec(requestSpec)
            .pathParam("keyId",id)
            .when().delete(resourcePath+"/{keyId}");
    System.out.println("Delete Response:" +response.getBody().asString());
    System.out.println("ID Value in Delete Request is: "+id);
    response.then().statusCode(204);

    }
}