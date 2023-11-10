package api.automation.users;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DeleteUserTest {
	@Test
	public void deleteAUserTest() {
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
		RestAssured.basePath = "users/5";

		RequestSpecification httpRequest = RestAssured.given();
		Response rs = httpRequest.request(Method.DELETE);
		
		int statusCode=rs.statusCode();
		Assert.assertTrue(statusCode==200, "Status code is not 200, Delete request failed.");
		
		System.out.println("Response for delete request is : "+ rs.asPrettyString());
	}

}
