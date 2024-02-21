package files;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;

import static io.restassured.RestAssured.*;

public class JiraTest {

	public static void main(String[] args) {
		
		
		RestAssured.baseURI ="http://localhost:8080";
		//Login scenario
		SessionFilter session = new SessionFilter();
		
		String response= given().header("Content-type", "application/json").body("{\r\n"
				+ "\"username\": \"arunji.pandey\",\r\n"
				+ "\"password\": \"Arun2784$\"\r\n"
				+ "\r\n"
				+ "}").log().all().filter(session).when().post("/rest/auth/1/session").then().log().all().extract().response().asString();
		
		given().pathParam("key", "10101").log().all().header("Content-type", "application/json").body("{\r\n"
				+ "    \"body\": \"This is my first comment2.\",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Administrators\"\r\n"
				+ "    }\r\n"
				+ "}").filter(session).when().post("rest/api/2/issue/{key}/comment").then().log().all().
		assertThat().statusCode(201);

	}

}
