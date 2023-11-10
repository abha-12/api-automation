package api.automation.users;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RetrieveUserTest {
	@Test
	public void getAUser() {
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
		RestAssured.basePath = "users/7";

		RequestSpecification httpRequest = RestAssured.given();
		Response rs = httpRequest.request(Method.GET);

		int statusCode = rs.statusCode();
		Assert.assertTrue(statusCode == 200, "Status code is not 200, Read request not successful.");

		System.out.println("Response for read request is: " + rs.asPrettyString());

		User user = rs.getBody().as(User.class);

		// ****Retrieving and validating user id****

		int userIdFromResponse = user.getId();
		Assert.assertTrue(userIdFromResponse == 7, "UserID validation failed.");

		// ****Retrieving and validating address****

		HashMap addressFromResponse = user.getAddress();

		Map expectedAddress = new HashMap();

		// ****Expected values for the geo coordinates****
		Map geo = new HashMap();
		geo.put("lat", "24.8918");
		geo.put("lng", "21.8984");

		expectedAddress.put("street", "Rex Trail");
		expectedAddress.put("suite", "Suite 280");
		expectedAddress.put("city", "Howemouth");
		expectedAddress.put("zipcode", "58804-1099");
		expectedAddress.put("geo", geo);

		Assert.assertTrue(addressFromResponse.equals(expectedAddress), "Address validation failed.");

		String nameFromResponse = user.getName();
		Assert.assertTrue(nameFromResponse.equals("Kurtis Weissnat"), "Name validation failed.");

		String usernameFromResponse = user.getUsername();
		Assert.assertTrue(usernameFromResponse.equals("Elwyn.Skiles"), "Username validation failed.");

		String emailFromResponse = user.getEmail();
		Assert.assertTrue(emailFromResponse.equals("Telly.Hoeger@billy.biz"), "Email validation failed.");

		String phoneFromResponse = user.getPhone();
		Assert.assertTrue(phoneFromResponse.equals("210.067.6132"), "Phone validation failed.");

		String websiteFromResponse = user.getWebsite();
		Assert.assertTrue(websiteFromResponse.equals("elvis.io"), "Website validation failed.");

		HashMap companyFromResponse = user.getCompany();
		Map expectedCompany = new HashMap();
		expectedCompany.put("name", "Johns Group");
		expectedCompany.put("catchPhrase", "Configurable multimedia task-force");
		expectedCompany.put("bs", "generate enterprise e-tailers");

		Assert.assertTrue(companyFromResponse.equals(expectedCompany), "Company validation failed.");
	}

}
