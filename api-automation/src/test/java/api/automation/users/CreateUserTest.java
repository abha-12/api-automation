package api.automation.users;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreateUserTest {
	@Test
	public void postAUserTest() {
		// defining the resource url
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
		RestAssured.basePath = "users";

		RequestSpecification httpRequest = RestAssured.given();
		Header contentType = new Header("Content-Type", "application/json");

		String payload = "{\r\n" + "    \"name\": \"Santa Clause\",\r\n" + "    \"username\": \"Little Elves\",\r\n"
				+ "    \"email\": \"clause-family@northpole.org\",\r\n" + "    \"address\": {\r\n"
				+ "        \"street\": \"Sky Trail\",\r\n" + "        \"suite\": \"Suite 234\",\r\n"
				+ "        \"city\": \"North Pole\",\r\n" + "        \"zipcode\": \"98765-1000\",\r\n"
				+ "        \"geo\": {\r\n" + "            \"lat\": \"23.6587\",\r\n"
				+ "            \"lng\": \"22.9087\"\r\n" + "        }\r\n" + "    },\r\n"
				+ "    \"phone\": \"123.456.6785\",\r\n" + "    \"website\": \"santa.toy\",\r\n"
				+ "    \"company\": {\r\n" + "        \"name\": \"Toy Factory\",\r\n"
				+ "        \"catchPhrase\": \"You better not pout\",\r\n" + "        \"bs\": \"In the good list\"\r\n"
				+ "    }\r\n" + "}";

		httpRequest.header(contentType);
		httpRequest.body(payload);

		// Sending the create request
		Response rs = httpRequest.request(Method.POST);

		// Checking the status code of response
		int statusCode = rs.statusCode();
		Assert.assertTrue(statusCode == 201, "Status code not 201, Post request failed.");

		// Printing the response
		System.out.println("The response to post request is : " + rs.asPrettyString());

		User user = rs.getBody().as(User.class);

		// Validations of the payload variables
		String nameFromResponse = user.getName();
		Assert.assertTrue(nameFromResponse.equals("Santa Clause"), "Name validation failed.");

		String usernameFromResponse = user.getUsername();
		Assert.assertTrue(usernameFromResponse.equals("Little Elves"), "Username validation failed.");

		String emailFromResponse = user.getEmail();
		Assert.assertTrue(emailFromResponse.equals("clause-family@northpole.org"), "Email validation failed.");

		Map expectedAddress = new HashMap();
		Map geo = new HashMap();

		geo.put("lat", "23.6587");
		geo.put("lng", "22.9087");

		expectedAddress.put("street", "Sky Trail");
		expectedAddress.put("suite", "Suite 234");
		expectedAddress.put("city", "North Pole");
		expectedAddress.put("zipcode", "98765-1000");
		expectedAddress.put("geo", geo);

		HashMap addressFromResponse = user.getAddress();

		Assert.assertTrue(addressFromResponse.equals(expectedAddress), "Address validation failed.");

		String phoneFromResponse = user.getPhone();
		Assert.assertTrue(phoneFromResponse.equals("123.456.6785"), "Phone validation failed.");

		String websiteFromResponse = user.getWebsite();
		Assert.assertTrue(websiteFromResponse.equals("santa.toy"), "Website validation failed.");

		Map expectedCompany = new HashMap();
		expectedCompany.put("name", "Toy Factory");
		expectedCompany.put("catchPhrase", "You better not pout");
		expectedCompany.put("bs", "In the good list");

		HashMap companyFromResponse = user.getCompany();
		Assert.assertTrue(companyFromResponse.equals(expectedCompany), "Company validation failed.");

	}

}
