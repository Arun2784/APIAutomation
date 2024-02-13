package files;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class dynamicJson {
	
	
	@Test(priority = 1,dataProvider  = "BooksData")
	public void addBook(String isbn, String aisle) {
		
		RestAssured.baseURI ="http://216.10.245.166";
		String response= given().header("Content-Type", "application/json").body(payload.Addbook(isbn,aisle)).
		when(). 
		post("Library/Addbook.php").  
		then().log().all().statusCode(200).extract().response().asString();
	 JsonPath js=ReusableMethods.rawTojson(response);
	String  id =js.get("ID");
	 System.out.println(id);	
		
	 // Deletebook
	 
	 String deleteResponse=given().header("Content-Type", "application/json").
				body(payload.deletebook(id)).
				when().
				post("Library/DeleteBook.php").then().statusCode(200)
			.extract().response().asString();
		JsonPath js1=ReusableMethods.rawTojson(deleteResponse);
		 String msga  =js1.get("msg");
		 System.out.println(msga);
//		
//		
	}
	
	
	@DataProvider(name="BooksData")
	public Object[][] getData() {
		//array = collection of elements
		
		return new Object[][] { {"211osjft","211123"},{"211asdf","211234"},{"121qwer","211345"} };
		
		
		
		
	}
	
	
}
