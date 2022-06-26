package com.api.reports;

import java.util.Objects;

import com.aventstack.extentreports.ExtentTest;

public class ExtentManager {

	private ExtentManager() {}

	private static ThreadLocal<ExtentTest> extTest = new ThreadLocal<ExtentTest>();

	public static ExtentTest getExtentTest() {
		return extTest.get();
	}

	public static void setExtentTest(ExtentTest reportref) {
		if(Objects.nonNull(reportref)) {
			extTest.set(reportref);
		}
	}

	public static void unload() {
		extTest.remove();
	}

}


/*
 * What is Extent Report
ExtentReports is an open-source reporting library useful for test automation.
It can also be easily integrated with major testing frameworks like JUnit, NUnit, TestNG, etc.
These reports are HTML rich documents that depict results as pie charts.

Extent Reports offer several advantages when compared to the built-in reports
that are generated through JUnit and TestNG such as test stepwise report generation ,
pie chart representation, adding screenshots etc. ****Also, we can assign Authors, Categories(like Smoke, sanity, rwgression, UI at testcase level)

What are the main classes in Extent Report
 ExtentHtmlReporter - To create/generate report and for look and feel of the reports.
 ExtentReports - Add your test cases in extent report.
 ExtentTest - update the status of the test cases like pass/fail/skip etc.
 */


