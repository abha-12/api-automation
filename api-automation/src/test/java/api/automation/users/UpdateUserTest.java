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

public class UpdateUserTest {

	@Test
	public void putAUserTest() {
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
		RestAssured.basePath = "users/6";

		RequestSpecification httpRequest = RestAssured.given();
		Header contentType = new Header("Content-Type", "application/json");

		String payload = "{\r\n" + "    \"id\": 6,\r\n" + "    \"name\": \"Mrs. Updated Name\",\r\n"
				+ "    \"username\": \"Updated Username\",\r\n" + "    \"email\": \"updated email\",\r\n"
				+ "    \"address\": {\r\n" + "        \"street\": \"Updated street\",\r\n"
				+ "        \"suite\": \"Apt. 950\",\r\n" + "        \"city\": \"Updated city\",\r\n"
				+ "        \"zipcode\": \"23505-1337\",\r\n" + "        \"geo\": {\r\n"
				+ "            \"lat\": \"-71.4197\",\r\n" + "            \"lng\": \"71.7478\"\r\n" + "        }\r\n"
				+ "    },\r\n" + "    \"phone\": \"1-477-935-8478 x6430\",\r\n" + "    \"website\": \"ola.org\",\r\n"
				+ "    \"company\": {\r\n" + "        \"name\": \"updated name\",\r\n"
				+ "        \"catchPhrase\": \"catching catchphrase\",\r\n"
				+ "        \"bs\": \"e-enable innovative applications\"\r\n" + "    }\r\n" + "}";

		httpRequest.header(contentType);
		httpRequest.body(payload);

		// Sending the update request
		Response rs = httpRequest.request(Method.PUT);

		// Checking the status code of response
		int statusCode = rs.statusCode();
		Assert.assertTrue(statusCode == 200, "Status code not 200, Put request failed.");

		// Printing the response
		System.out.println("The response to put request is : " + rs.asPrettyString());

		User user = rs.getBody().as(User.class);

		// Validations of the payload variables
		String nameFromResponse = user.getName();
		Assert.assertTrue(nameFromResponse.equals("Mrs. Updated Name"), "Name validation failed.");

		String usernameFromResponse = user.getUsername();
		Assert.assertTrue(usernameFromResponse.equals("Updated Username"), "Username validation failed.");

		String emailFromResponse = user.getEmail();
		Assert.assertTrue(emailFromResponse.equals("updated email"), "Email validation failed.");

		Map expectedAddress = new HashMap();
		Map geo = new HashMap();

		geo.put("lat", "-71.4197");
		geo.put("lng", "71.7478");

		expectedAddress.put("street", "Updated street");
		expectedAddress.put("suite", "Apt. 950");
		expectedAddress.put("city", "Updated city");
		expectedAddress.put("zipcode", "23505-1337");
		expectedAddress.put("geo", geo);

		HashMap addressFromResponse = user.getAddress();

		Assert.assertTrue(addressFromResponse.equals(expectedAddress), "Address validation failed.");

		String phoneFromResponse = user.getPhone();
		Assert.assertTrue(phoneFromResponse.equals("1-477-935-8478 x6430"), "Phone validation failed.");

		String websiteFromResponse = user.getWebsite();
		Assert.assertTrue(websiteFromResponse.equals("ola.org"), "Website validation failed.");

		Map expectedCompany = new HashMap();
		expectedCompany.put("name", "updated name");
		expectedCompany.put("catchPhrase", "catching catchphrase");
		expectedCompany.put("bs", "e-enable innovative applications");

		HashMap companyFromResponse = user.getCompany();
		Assert.assertTrue(companyFromResponse.equals(expectedCompany), "Company validation failed.");

	}
}
