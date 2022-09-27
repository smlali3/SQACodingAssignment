import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import java.util.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.path.json.JsonPath;

public class API_Automation {

	JsonPath jsonPath;
	Response response;
	
	@Test(priority = 1)
	public void validates_that_year_is_the_same_in_all_elements_of_the_data_array() {
		
		response = sendGetRequest();
		
		jsonPath = response.jsonPath();
		
		ArrayList<String> years = jsonPath.getJsonObject("data.Year");
		for(int i=0; i<years.size(); i++) {
			Assert.assertEquals(years.get(i), years.get(0), "Year is not the same in all elements of the data array.");
		}
	}
	
	@Test(priority = 2)
	public void validates_that_each_state_name_is_unique_in_data_array() {
		
		ArrayList<String> states = jsonPath.getJsonObject("data.'Slug State'");
		Set<String> hashSet = new HashSet<String>();
		for(int i=0; i<states.size(); i++) {
			hashSet.add(states.get(i));
		}
		Assert.assertEquals(hashSet.size(), states.size(), "State name is not unique in data array.");
	
	}
	
	public Response sendGetRequest() {
		response = given()
                .queryParam("drilldowns", "State")
                .queryParam("measures", "Population")
                .queryParam("year", "latest")
                .when()
                .get("https://datausa.io/api/data");
		return response;
	}
	
}
