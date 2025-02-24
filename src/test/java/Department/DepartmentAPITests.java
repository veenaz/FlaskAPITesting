package Department;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class DepartmentAPITests {

    @BeforeClass
    public void setUp() {
        // Set the base URI for REST Assured
        RestAssured.baseURI = "http://flask-api.ub";
    }

    @Test
    public void testGetDepartments() {
        // Send a GET request to /departments and capture the response
        Response response = given()
        						.header("Accept", "application/json")
                                .when()
                                .get("/departments")
                                .then()
                                .extract().response();

        System.out.println("Response Headers: " + response.getHeaders());
        response.prettyPrint();
        
        // Verify that the status code is 200 OK
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200, "Expected status code 200, but got " + statusCode);

        // Optional: Parse the response JSON as a List and verify it's not empty
        List<?> departments = response.jsonPath().getList("$");
        
        System.out.println("Status code:"+ response.statusCode());
        
        System.out.println("Response:"+ response.asString());
        
        Assert.assertTrue(departments != null && !departments.isEmpty(), "Expected non-empty departments list");
    }
}

