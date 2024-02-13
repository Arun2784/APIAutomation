package RestAPIAutomation.RestAPIAutomation;

import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {

		JsonPath js = new JsonPath(payload.CoursePrice());

		// find the size
		int count = js.getInt("courses.size()");

		System.out.println(count);

		// find the purchase Amount

		int totalamount = js.getInt("dashboard.purchaseAmount");

		System.out.println(totalamount);

		// print the title of first course

		String title_first_course = js.get("courses[0].title");
		String title_first_course2 = js.get("courses[1].title");

		System.out.println(title_first_course);
		System.out.println(title_first_course2);

		// print All course title and their respective title

		for (int i = 0; i < count; i++) {

			String courseTitle = js.get("courses[" + i + "].title");
			int coursePrice = js.get("courses[" + i + "].price");
			System.out.println(courseTitle);
			System.out.println(coursePrice);

		}
			
		// find the copies of RPA course title
		
		System.out.println("Print no of copies sold by RPA");
		for(int i=0; i< count; i++) {
			
			String coursetitles= js.get("courses["+i+"].title");
			if(coursetitles.equalsIgnoreCase("RPA")) {
				//copies sold	
				int copies= js.get("courses["+i+"].copies");
	
				System.out.println(copies);
				break;
				
				
			}
			
			
			
		}
		

	}

}
