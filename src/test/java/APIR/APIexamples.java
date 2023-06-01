package APIR;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class APIexamples {
    // wht we use@Test: to write down the test case
    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9" +
            ".eyJpYXQiOjE2ODUwNTg1MDEsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY4NTEwMTcwMSwidXNlcklkIjoiNTIxMyJ9" +
            ".ntEmA2Xw-_Q6Wpp82_npLyYw985s_RXOVbAnOzfdj9w";

    @Test
    public void CreateAnEmployee() {

        RequestSpecification preparerequest = given().
                header("Content-Type", "application/json").
                header("Authorization", token).
                body(" {\n" +
                        "  \n" +
                        "  \"emp_firstname\": \"Hajar\",\n" +
                        "  \"emp_lastname\": \"Ehsani\",\n" +
                        "  \"emp_middle_name\": \"ms\",\n" +
                        "  \"emp_gender\": \"F\",\n" +
                        "  \"emp_birthday\": \"2012-05-20\",\n" +
                        "  \"emp_status\": \"confirmed\",\n" +
                        "  \"emp_job_title\": \"Engineer\"");

         Response response  = preparerequest.when().post("/createEmployee.php");// will send post request to send KEY
        // prepare requset ^
        response .prettyPrint();

       //verification
        response.then().assertThat().statusCode(201);
        //verify the body or the data
        // in order to verify thet the value of the key "Message" is "Employee_created"
        String expectedvalue= "Employee_created";
       // actualvalue=coming from the response
            //    expectedvalue="Employee_created"

    //get the actual value out of the response----> this is the only task is though

      String actualValue= response.jsonPath() .getString("Message");
        System.out.println(actualValue);
        Assert.assertEquals(actualValue,expectedvalue);

       //verify that " emp-job-title" is Cloud Architect
        //actual
        String expected="Cloud Architect";

        String actual= response.jsonPath().getString(" Employee.emp_job_tittle");
        Assert.assertEquals(expected,actual);

        //       response.then().assertThat().body("Employee.emp_job_title",equalTo("Cloud Architect"));




    }

}
