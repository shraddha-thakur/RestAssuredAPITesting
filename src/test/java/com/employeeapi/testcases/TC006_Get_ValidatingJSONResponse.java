package com.employeeapi.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.api.reports.ExtentManager;
import com.api.reports.ExtentReport;
import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC006_Get_ValidatingJSONResponse extends TestBase{

	@BeforeClass
	void getSingleEmployee() throws InterruptedException {
		System.out.println("******************   Started Demo4_Get_Request_PrintAllHeaders ***************************");
		// specify base uri
		//		RestAssured.baseURI ="http://dummy.restapiexample.com/api/v1";
		RestAssured.baseURI ="https://fakerestapi.azurewebsites.net/api/v1/Authors/authors/books/25";

		// Request object
		RequestSpecification httpRequest = RestAssured.given();

		// Response object
		Response response = httpRequest.request(Method.GET, "/users/" + empID);

		Thread.sleep(3000);
	}

	@Test(groups = {"GET"})
	void checkResponseBody() {
		ExtentReport.createTestName("Demo4-getAllHeaders", "Thakur");
		ExtentManager.getExtentTest().info("******************geting AllHeaders***************************");

		System.out.println("\n******************geting AllHeaders***************************");
		//print response in console
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is : " + responseBody);
		ExtentManager.getExtentTest().info("Response Body is : " + responseBody);

		Headers allHeaders = response.headers(); // capture all headers return from response


		for (Header header : allHeaders) {
			System.out.println(header.getName() + "       "+header.getValue());
			System.out.println(header.getValue());
		}
		Assert.assertEquals(responseBody.contains(empID),true);
	}

	@Test(groups = {"GET"})
	void checkStatusCode() {
		ExtentReport.createTestName("TC002-checkStatusCode", "Thakur");
		ExtentManager.getExtentTest().info("******************checking Status Code***************************");

		System.out.println("\n******************checking Status Code***************************");

		//print response in console
		int statusCode = response.getStatusCode();
		System.out.println("Status Code is : " + statusCode);
		ExtentManager.getExtentTest().info("Status Code is : " + statusCode);

		Assert.assertEquals(statusCode, 200);
	}


	@Test(groups = {"GET"})
	void checkResponseTime() {
		ExtentReport.createTestName("TC002-checkResponseTime", "Thakur");
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


	@Test(groups = {"GET"})
	void checkStatusLine() {
		ExtentReport.createTestName("TC002-checkStatusLine", "Thakur");
		ExtentManager.getExtentTest().info("******************checking Status Line***************************");

		System.out.println("\n******************checking Status Line ***************************");

		//print response in console
		String statusLine = response.getStatusLine();
		System.out.println("Status Line is : " + statusLine);
		ExtentManager.getExtentTest().info("Status Line is : " + statusLine);

		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}

	@Test(groups = {"GET"})
	void checkContentType() {
		ExtentReport.createTestName("TC002-checkContentType", "Thakur");
		ExtentManager.getExtentTest().info("******************checking Content Type ***************************");

		System.out.println("\n******************checking Content Type ***************************");

		//print response in console
		String ContentType  = response.getContentType();
		System.out.println("ContentType : " + ContentType);
		ExtentManager.getExtentTest().info("ContentType : " + ContentType);

		Assert.assertEquals(ContentType, "application/json; charset=utf-8");

	}

	@Test(groups = {"GET"})
	void checkServerType() {
		ExtentReport.createTestName("TC002-checkServerType", "Thakur");
		ExtentManager.getExtentTest().info("******************checking Server Type***************************");

		System.out.println("\n******************checking Server Type ***************************");

		String serverType = response.header("Server");
		System.out.println("Server Type : " + serverType);
		ExtentManager.getExtentTest().info("Server Type : " + serverType);

		Assert.assertEquals(serverType, "cloudflare");
	}

	@Test(groups = {"GET"})
	void checkContentEncoding() {
		ExtentReport.createTestName("TC002-checkContentEncoding", "Thakur");
		ExtentManager.getExtentTest().info("******************checking ContentEncoding***************************");

		System.out.println("\n******************checking ContentEncoding ***************************");

		String ContentEncoding = response.header("Content-Encoding");
		System.out.println("Content Encoding : " + ContentEncoding);
		ExtentManager.getExtentTest().info("Content Encoding : " + ContentEncoding);

		Assert.assertEquals(ContentEncoding, "gzip");
	}

	//	@Test(groups = {"GET"})
	void checkContentLength() {
		ExtentReport.createTestName("TC002-checkContentLength", "Thakur");
		ExtentManager.getExtentTest().info("******************checking Content Length ***************************");

		System.out.println("\n******************checking Content Length ***************************");

		String contentLength = response.header("Content-Length");
		System.out.println("Content Length : " + contentLength);
		ExtentManager.getExtentTest().info("Content Length : " + contentLength);

		if(Integer.parseInt(contentLength) < 100)
			System.out.println("Content Length is less than 100");
		ExtentManager.getExtentTest().warning("Content Length is less than 100");

		Assert.assertTrue(false, contentLength = null);
	}




	@Test(groups = {"GET"})
	void checkAllHeaders() {
		ExtentReport.createTestName("TC002-checkAllHeaders" , "Thakur");
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
		System.out.println("\n***********************   Finished TC002_Get_Single_Employee_Record    ***************************");
	}

}
