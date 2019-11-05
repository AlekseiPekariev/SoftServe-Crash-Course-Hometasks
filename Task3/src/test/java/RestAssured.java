import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

public class RestAssured {
    @Test
    public void testWithParams(){
        given().
                param("q","potter").
                when().
                get("https://www.googleapis.com/books/v1/volumes").
                then().
                assertThat().statusCode(200).body("items", hasSize(10));
    }
    @Test
    public void testStatusCodeAndBody() {
        Response resp = given().when().get("https://petstore.swagger.io/v2/pet/159357");
        System.out.println( "Resp code = " + resp.getStatusCode());
        Assert.assertEquals(200 ,resp.getStatusCode());
        Assert.assertTrue(resp.asString().contains("Barsik"));
    }

    }

