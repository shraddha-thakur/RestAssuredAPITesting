package com.employeeapi.testcases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.api.commonUtils.RestUtils;
import com.api.reports.ExtentManager;
import com.api.reports.ExtentReport;
import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;

public class TC004_Put_Employee_Record extends TestBase{

	String username = RestUtils.userName();
	String userjob = RestUtils.userJob();
	String useremail = RestUtils.randomemail();


	@BeforeClass
	void updateEmployee() throws InterruptedException {
		System.out.println("******************   Started TC004_Put_Employee_Record ***************************");
		// specify base uri
		//		RestAssured.baseURI ="http://dummy.restapiexample.com/api/v1";
		RestAssured.baseURI ="https://reqres.in/api";

		// Request object
		httpRequest = RestAssured.given();


		JSONObject requestParams = new JSONObject();
		requestParams.put("email", useremail);
		requestParams.put("first_name", username);

		// Add a header stating the Request body is a JSON
		httpRequest.header("Content-Type", "application/json");

		// Add the JSON to the body of the request
		httpRequest.body(requestParams.toJSONString());


		// Response object
		//		response = httpRequest.request(Method.POST, "");
		response = httpRequest.request(Method.PUT, "/users/" + empID);

		Thread.sleep(5000);
	}

	@Test(groups = {"PUT"})
	void checkResponseBody() {
		ExtentReport.createTestName("TC004-checkResponseBody", "Thakur");
		ExtentManager.getExtentTest().info("******************checking Response Body***************************");

		System.out.println("\n******************checking Response Body***************************");
		//print response in console
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is : " + responseBody);
		ExtentManager.getExtentTest().info("Response Body is : " + responseBody);

		Assert.assertEquals(responseBody.contains(username),true);
		Assert.assertEquals(responseBody.contains(useremail),true);

	}

	@Test(groups = {"PUT"})
	void checkStatusCode() {
		ExtentReport.createTestName("TC004-checkStatusCode", "Thakur");
		ExtentManager.getExtentTest().info("******************checking Status Code***************************");

		System.out.println("\n******************checking Status Code***************************");

		//print response in console
		int statusCode = response.getStatusCode();
		System.out.println("Status Code is : " + statusCode);
		ExtentManager.getExtentTest().info("Status Code is : " + statusCode);

		Assert.assertEquals(statusCode, 200);
	}


	@Test(groups = {"PUT"})
	void checkResponseTime() {
		ExtentReport.createTestName("TC004-checkResponseTime", "Thakur");
		ExtentManager.getExtentTest().info("******************checking Response Time  ***************************");

		System.out.println("\n******************checking Response Time***************************");

		//print response in console
		long responseTime = response.getTime();
		System.out.println("Response Time is : " + responseTime);
		ExtentManager.getExtentTest().info("Response Time is : " + responseTime);

		if(responseTime>2000)
			System.out.println("Response Time is greater than 2000");
		ExtentManager.getExtentTest().warning("Response Time is greater than 2000");

		Assert.assertTrue(responseTime>2000);

	}


	@Test(groups = {"PUT"})
	void checkStatusLine() {
		ExtentReport.createTestName("TC004-checkStatusLine", "Thakur");
		ExtentManager.getExtentTest().info("******************checking Status Line***************************");

		System.out.println("\n******************checking Status Line ***************************");

		//print response in console
		String statusLine = response.getStatusLine();
		System.out.println("Status Line is : " + statusLine);
		ExtentManager.getExtentTest().info("Status Line is : " + statusLine);

		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}

	@Test(groups = {"PUT"})
	void checkContentType() {
		ExtentReport.createTestName("TC004-checkContentType", "Thakur");
		ExtentManager.getExtentTest().info("******************checking Content Type ***************************");

		System.out.println("\n******************checking Content Type ***************************");

		//print response in console
		String ContentType  = response.getContentType();
		System.out.println("ContentType : " + ContentType);
		ExtentManager.getExtentTest().info("ContentType : " + ContentType);

		Assert.assertEquals(ContentType, "application/json; charset=utf-8");

	}

	@Test(groups = {"PUT"})
	void checkServerType() {
		ExtentReport.createTestName("TC004-checkServerType", "Thakur");
		ExtentManager.getExtentTest().info("******************checking Server Type***************************");

		System.out.println("\n******************checking Server Type ***************************");

		String serverType = response.header("Server");
		System.out.println("Server Type : " + serverType);
		ExtentManager.getExtentTest().info("Server Type : " + serverType);

		Assert.assertEquals(serverType, "cloudflare");
	}

	@Test(groups = {"PUT"})
	void checkContentEncoding() {
		ExtentReport.createTestName("TC004-checkContentEncoding", "Thakur");
		ExtentManager.getExtentTest().info("******************checking ContentEncoding***************************");

		System.out.println("\n******************checking ContentEncoding ***************************");

		String ContentEncoding = response.header("Content-Encoding");
		System.out.println("Content Encoding : " + ContentEncoding);
		ExtentManager.getExtentTest().info("Content Encoding : " + ContentEncoding);

		Assert.assertEquals(ContentEncoding, "gzip");
	}

	@Test(groups = {"PUT"})
	void checkContentLength() {
		ExtentReport.createTestName("TC004-checkContentLength", "Thakur");
		ExtentManager.getExtentTest().info("******************checking Content Length ***************************");

		System.out.println("\n******************checking Content Length ***************************");

		String contentLength = response.header("Content-Length");
		System.out.println("Content Length : " + contentLength);
		ExtentManager.getExtentTest().info("Content Length : " + contentLength);

		if(Integer.parseInt(contentLength) < 100)
			System.out.println("Content Length is less than 100");
		ExtentManager.getExtentTest().warning("Content Length is less than 100");

		Assert.assertTrue(Integer.parseInt(contentLength) < 100);
	}




	@Test(groups = {"PUT"})
	void checkAllHeaders() {
		ExtentReport.createTestName("TC004-checkAllHeaders" , "Thakur");
		ExtentManager.getExtentTest().info("******************checking All Headers***************************");

		System.out.println("\n\n******************checking All Headers***************************");

		Headers allheaders = response.headers(); // capture all headers from response

		for(Header header : allheaders) {
			System.out.println(header.getName() + " : " + header.getValue());
			ExtentManager.getExtentTest().info(header.getName() + " : " + header.getValue());

		}

		String ContentType  = response.getContentType();
		System.out.println("ContentType : " + ContentType);
		ExtentManager.getExtentTest().info("ContentType : " + ContentType);

		String serverType = response.header("Server");
		System.out.println("serverType : " + serverType);
		ExtentManager.getExtentTest().info("serverType : " + serverType);

		String ContentEncoding = response.header("Content-Encoding");
		System.out.println("ContentEncoding : " + ContentEncoding);
		ExtentManager.getExtentTest().info("ContentEncoding : " + ContentEncoding);

		//		int contentLength = response.getBody().toString().length();
		String contentLength = response.header("Content-Length");
		System.out.println("contentLength : " + contentLength);
		ExtentManager.getExtentTest().info("contentLength : " + contentLength);

		String cookie = response.getCookies().toString();
		System.out.println("Cookies : " + cookie);
		ExtentManager.getExtentTest().info("Cookies : " + cookie);

	}

	@AfterClass
	void tearDown() {
		System.out.println("\n***********************   Finished TC004_Put_Employee_Record    ***************************");
	}



}
