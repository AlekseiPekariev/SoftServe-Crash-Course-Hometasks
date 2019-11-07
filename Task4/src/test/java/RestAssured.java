import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

public class RestAssured {
    //Google books API test.
    /*    @Test
        public void testWithParams(){
            given().
                    param("q","potter").
                    when().
                    get("https://www.googleapis.com/books/v1/volumes").
                    then().
                    assertThat().statusCode(200).body("items", hasSize(10));
        }*/

    public static Response resp;
    private static String createPetJson;
    private static String updatePetJson;

    @BeforeTest
    public void fileReader() throws FileNotFoundException {
        createPetJson = new Scanner(new File("MyPetJSON.txt")).nextLine();
        updatePetJson = new Scanner(new File("MyUpdatedPetJSON.txt")).nextLine();
    }

    /*@AfterMethod
    public void streamCloser() throws FileNotFoundException {
        createPetJson = new Scanner(new File("MyPetJSON.txt")).toString();
        updatePetJson = new Scanner(new File("MyUpdatedPetJSON.txt")).toString();}*/

        //POST test
        @Test(priority = 0,
                enabled = true,
                timeOut = 10000)
        public void testCreatePet () {
            resp = given()
                    .contentType(ContentType.JSON)
                    .body(createPetJson)
                    .when().post("https://petstore.swagger.io/v2/pet");
            successfulResponseAssertion("Barsik");
        }

        //GET test
        @Test(priority = 1,
                enabled = true,
                timeOut = 2000,
                dependsOnMethods = "testCreatePet")
        public void testStatusCodeAndBody () {
            resp = given()
                    .when()
                    .get("https://petstore.swagger.io/v2/pet/159357");
            successfulResponseAssertion("Barsik");
        }

        //PUT test
        @Test(priority = 2,
                enabled = true,
                timeOut = 2000,
                dependsOnMethods = "testStatusCodeAndBody")
        public void updatePet () {
            resp = given()
                    .contentType(ContentType.JSON)
                    .body(updatePetJson)
                    .when().put("https://petstore.swagger.io/v2/pet");
            successfulResponseAssertion("Tuzik");
        }

        //GET test after update
        @Test(priority = 3,
                enabled = true,
                timeOut = 10000,
                dependsOnMethods = "updatePet")
        public void testStatusCodeAndBodyAfterUpdate () {
            resp = given()
                    .when()
                    .get("https://petstore.swagger.io/v2/pet/159357");
            successfulResponseAssertion("Tuzik");
        }

        //DELETE test
        @Test(priority = 4,
                enabled = true,
                timeOut = 2000,
                dependsOnMethods = "testStatusCodeAndBodyAfterUpdate")
        public void deletePet () {
            resp = given()
                    .when()
                    .delete("https://petstore.swagger.io/v2/pet/159357");
            System.out.println("Pet was deleted " + "\n" + "Request status code : " + resp.getStatusCode());
            Assert.assertEquals(resp.getStatusCode(), 200);

        }

        //GET test after delete
        @Test(priority = 5,
                enabled = true,
                timeOut = 2000,
                dependsOnMethods = "deletePet")
        public void testStatusCodeAndBodyAfterDelete () {
            resp = given()
                    .when()
                    .get("https://petstore.swagger.io/v2/pet/159357");
            Assert.assertEquals(resp.getStatusCode(), 404);
            Assert.assertTrue(resp.asString().contains("Pet not found"));
            System.out.println("Resp code = " + resp.getStatusCode() + " \n" + "Resp: " + resp.asString());

        }

        public static void successfulResponseAssertion (String petName){
            System.out.println("Resp code = " + resp.getStatusCode() + " " + resp.body().asString());
            Assert.assertEquals(resp.getStatusCode(), 200);
            Assert.assertTrue(resp.asString().contains(petName));
        }

    }

