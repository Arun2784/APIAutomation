package RestAPIAutomation.RestAPIAutomation;

import io.restassured.RestAssured;
import io.restassured.internal.http.Status;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.ReusableMethods;
import files.payload;

public class Basics {

	public static void main(String[] args) {

		// Validate if Add place API is working as expected
		// given- all input details
		// when-Submit the API
		// Then-validate the response

		// Add place --->update place with new Address--> get Place to validate if new
		// Address is present in response

		RestAssured.baseURI = "https://rahulshettyacademy.com/";

		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(payload.AddPlace()).when().post("maps/api/place/add/json").then().assertThat().statusCode(200)
				.body("scope", equalTo("APP")).header("server", "Apache/2.4.52 (Ubuntu)").extract().response()
				.asString();

		System.out.println(response);
		JsonPath js = new JsonPath(response);
		String place_id = js.getString("place_id");

		System.out.println(place_id);

		// update place with new Address

		String new_address = "Summer walk Africa123";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body("{\r\n" + "\"place_id\":\"" + place_id + "\",\r\n" + "\"address\":\"" + new_address + "\",\r\n"
						+ "\"key\":\"qaclick123\"\r\n" + "}")
				.when().put("maps/api/place/update/json").then().assertThat().log().all().statusCode(200)
				.body("msg", equalTo("Address successfully updated"));

		// GetPlace

		String getPlaceResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", place_id)
				.when().get("maps/api/place/get/json").then().assertThat().log().all().statusCode(200).extract()
				.response().asString();
		
		JsonPath js1= ReusableMethods.rawTojson(getPlaceResponse);
		String actual_address = js1.getString("address");
		Assert.assertEquals(actual_address, new_address);

	}
}
