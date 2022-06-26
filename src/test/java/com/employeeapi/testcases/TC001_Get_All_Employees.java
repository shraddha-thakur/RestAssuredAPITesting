package com.employeeapi.testcases;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;
import com.api.reports.ExtentManager;
import com.api.reports.ExtentReport;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;

/**
 * 
 * @author Shraddha
 * 
 */

public class TC001_Get_All_Employees extends TestBase{

	@BeforeClass
	void getAllEmployees() throws InterruptedException {
		System.out.println("******************   Started TC001_Get_All_Employees ***************************");
		//		ExtentReport.createTestName("getAllEmployees");
		//		ExtentManager.getExtentTest().info("******************   Started TC001_Get_All_Employees ***************************");

		// specify base uri
		//		RestAssured.baseURI ="http://dummy.restapiexample.com/api/v1";
		RestAssured.baseURI ="https://reqres.in/api";

		// Request object
		httpRequest = RestAssured.given();

		// Response object
		response = httpRequest.request(Method.GET, "/users");

		Thread.sleep(3000);
	}

	@Test(groups = {"GET"})
	void checkResponseBody() {
		ExtentReport.createTestName("TC001-checkResponseBody", "Shraddha");
		ExtentManager.getExtentTest().info("******************checking Response Body***************************");
		System.out.println("\n******************checking Response Body***************************");

		//print response in console
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is : " + responseBody);
		ExtentManager.getExtentTest().info("Response Body is : " + responseBody);

		Assert.assertTrue(responseBody!=null);

	}

	@Test(groups = {"GET"})
	void checkStatusCode() {

		ExtentReport.createTestName("TC001-checkStatusCode", "Shraddha");
		ExtentManager.getExtentTest().info("******************checking Status Code***************************");

		System.out.println("******************checking Status Code***************************");

		//print response in console
		int statusCode = response.getStatusCode();
		System.out.println("Status Code is : " + statusCode);
		ExtentManager.getExtentTest().info("Status Code is : " + statusCode);

		Assert.assertEquals(statusCode, 200);
	}


	@Test(groups = {"GET"})
	void checkResponseTime() {
		ExtentReport.createTestName("TC001-checkResponseTime", "Shraddha");
		ExtentManager.getExtentTest().info("******************checking Response Time  ***************************");

		System.out.println("******************checking Response Time***************************");

		//print response in console
		long responseTime = response.getTime();
		System.out.println("Response Time is : " + responseTime);
		ExtentManager.getExtentTest().info("Response Time is : " + responseTime);

		if(responseTime>2000) {
			System.out.println("Response Time is greater than 2000");
			ExtentManager.getExtentTest().warning("Response Time is greater than 2000");}

		Assert.assertTrue(responseTime<2000);

	}


	@Test(groups = {"GET"})
	void checkStatusLine() {
		ExtentReport.createTestName("TC001-checkStatusLine", "Shraddha");
		ExtentManager.getExtentTest().info("******************checking Status Line***************************");

		System.out.println("******************checking Status Line ***************************");

		//print response in console
		String statusLine = response.getStatusLine();
		System.out.println("Status Line is : " + statusLine);
		ExtentManager.getExtentTest().info("Status Line is : " + statusLine);

		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}

	@Test(groups = {"GET"})
	void checkContentType() {
		ExtentReport.createTestName("TC001-checkContentType", "Shraddha");
		ExtentManager.getExtentTest().info("******************checking Content Type ***************************");

		System.out.println("******************checking Content Type ***************************");

		//print response in console
		String ContentType  = response.getContentType();
		System.out.println("ContentType : " + ContentType);
		ExtentManager.getExtentTest().info("ContentType : " + ContentType);

		Assert.assertEquals(ContentType, "application/json; charset=utf-8");

	}

	@Test(groups = {"GET"})
	void checkServerType() {
		ExtentReport.createTestName("TC001-checkServerType", "Shraddha");
		ExtentManager.getExtentTest().info("******************checking Server Type***************************");

		System.out.println("******************checking Server Type ***************************");

		String serverType = response.header("Server");
		System.out.println("Server Type : " + serverType);
		ExtentManager.getExtentTest().info("Server Type : " + serverType);

		Assert.assertEquals(serverType, "cloudflare");
	}

	@Test(groups = {"GET"})
	void checkContentEncoding() {
		ExtentReport.createTestName("TC001-checkContentEncoding", "Shraddha");
		ExtentManager.getExtentTest().info("******************checking ContentEncoding***************************");

		System.out.println("******************checking ContentEncoding ***************************");

		String ContentEncoding = response.header("Content-Encoding");
		System.out.println("Content Encoding : " + ContentEncoding);
		ExtentManager.getExtentTest().info("Content Encoding : " + ContentEncoding);

		Assert.assertEquals(ContentEncoding, "gzip");
	}

	@Test(groups = {"GET"})
	void checkContentLength() {
		ExtentReport.createTestName("TC001-checkContentLength", "Shraddha");
		ExtentManager.getExtentTest().info("******************checking Content Length ***************************");

		System.out.println("******************checking Content Length ***************************");

		String contentLength = response.header("Content-Length");
		System.out.println("contentLength : " + contentLength);
		ExtentManager.getExtentTest().info("contentLength : " + contentLength);

		if(Integer.parseInt(contentLength) < 100) {
			System.out.println("Content Length is less than 100");
			ExtentManager.getExtentTest().warning("Content Length is less than 100");}

		Assert.assertTrue(Integer.parseInt(contentLength) < 100);
	}




	@Test(groups = {"GET"})
	void checkAllHeaders() {
		ExtentReport.createTestName("TC001-checkAllHeaders", "Shraddha");
		ExtentManager.getExtentTest().info("******************checking All Headers***************************");

		System.out.println("******************checking All Headers***************************");

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
		System.out.println("***********************   Finished TC001_Get_All_Employees   ***************************");
	}

}
