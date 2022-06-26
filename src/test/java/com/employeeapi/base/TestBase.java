package com.employeeapi.base;



import org.apache.logging.log4j.*;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
//import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.api.reports.ExtentReport;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.api.reports.ExtentManager;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {

	public static RequestSpecification httpRequest;
	public static Response response;
	public String empID = "2"; // Hard Coded - Input for Get Details of Single Employee & Update Employee

	//	public Logger log;

	//	public Logger logger; // = LogManager.getLogger(TestBase.class.getName());

	@BeforeSuite
	protected void beforeSuite() throws Exception {
		ExtentReport.initReports();
	}

	@AfterSuite
	protected void afterSuite() throws Exception {
		ExtentReport.flushReports();
	}

	@AfterMethod
	protected void tearDown(ITestResult result) {

		if(result.getStatus()==ITestResult.FAILURE) {
			ExtentManager.getExtentTest().log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
			ExtentManager.getExtentTest().log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));

			//			ExtentManager.getExtentTest().fail("Click to see screenshot ", MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.getBase64()).build());
			//			ExtentManager.getExtentTest().fail(MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
			//			ExtentManager.getExtentTest().fail(MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
			//			//			ExtentManager.getExtentTest().fail(MarkupHelper.createLabel(result.getThrowable().getStackTrace() + " - Test Case Failed", ExtentColor.RED));

		}
		else if (result.getStatus()==ITestResult.SKIP) {
			//			ExtentManager.getExtentTest().skip("Skipped Test Case is : "+ result.getName());
			ExtentManager.getExtentTest().log(Status.SKIP, "Skipped Test Case is : "+ result.getName());
		}
		else if (result.getStatus()==ITestResult.SUCCESS) {
			ExtentManager.getExtentTest().log(Status.PASS, MarkupHelper.createLabel(result.getName() + " - Test Case PASSED", ExtentColor.GREEN));

			//			ExtentManager.getExtentTest().pass(MarkupHelper.createLabel(result.getName() + " - Test Case PASSED", ExtentColor.GREEN));
		}

	}



}
