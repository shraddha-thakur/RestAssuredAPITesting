package com.api.reports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Objects;


import com.api.constants.FrameworkConstants;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ViewName;


public final class ExtentReport {


	//	com.jsp.reports.ExtentReport.extent" is null
	private static ExtentReports extent;

	public static void initReports() throws Exception{

		if(Objects.isNull(extent)) {

			extent = new ExtentReports();

			ExtentSparkReporter spark = new ExtentSparkReporter(FrameworkConstants.getExtentReportFilePath())
					.viewConfigurer().viewOrder().as(new ViewName[] {ViewName.TEST, ViewName.DASHBOARD, ViewName.AUTHOR, ViewName.CATEGORY }).apply();

			//			ExtentSparkReporter failedspark = new ExtentSparkReporter(FrameworkConstants.getExtentReportFilePath())
			//					//					.viewConfigurer().viewOrder().as(new ViewName[] {ViewName.DASHBOARD, ViewName.TEST})
			//					.filter().statusFilter().as(new Status[] {Status.FAIL})
			//					.apply();
			//
			//			failedspark.config().setDocumentTitle("Failed Test cases Only");

			File CONF = new File(FrameworkConstants.getExtentconfigpath());
			try {
				spark.loadXMLConfig(CONF);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//
			//			spark.config().setTheme(Theme.STANDARD);
			//			spark.config().setDocumentTitle("Automation Test Report");
			//			spark.config().setReportName("Java Selenium Programs for Automation");

			extent.setSystemInfo("HostName", "My Host");
			extent.setSystemInfo("Project Name", "API Testing with Rest Assured");
			extent.setSystemInfo("Tester", "Shraddha Thakur");
			extent.setSystemInfo("OS", "win 7");
			extent.setSystemInfo("Browser", "Chrome");

			extent.attachReporter(spark); //, failedspark
			//			spark.loadXMLConfig(new File (FrameworkConstants.getExtentconfigpath()));

			//

			//		spark.config().setTheme(Theme.STANDARD);
			//		spark.config().setDocumentTitle("Automation Framework Test Report");
			//		spark.config().setReportName("Extent Report for Test Cases");
			//
			//		extent.attachReporter(spark);
			//
		}
	}
	public static void flushReports() throws Exception {
		if(Objects.nonNull(extent)) {
			extent.flush();
			ExtentManager.unload();
		}
		try {
			Desktop.getDesktop().browse(new File(FrameworkConstants.getExtentReportFilePath()).toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void createTestName(String testcasename, String author) {

		ExtentManager.setExtentTest(extent.createTest(testcasename).assignAuthor(author));

	}
	public static void createTestName(String testcasename) {

		ExtentManager.setExtentTest(extent.createTest(testcasename));
	}

	public static void createTestName(String testcasename, String author, String category) {

		ExtentManager.setExtentTest(extent.createTest(testcasename).assignAuthor(author).assignCategory(category));

	}

	/*
	 * public static String getBase64() {
	 * 
	 * // return
	 * ((TakesScreenshot)DriverManager.getDriver()).getScreenshotAs(OutputType.
	 * BASE64); }
	 */

}
