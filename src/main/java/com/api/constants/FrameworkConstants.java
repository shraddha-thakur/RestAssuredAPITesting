package com.api.constants;

import com.api.commonUtils.CurrentDate;
import com.api.enums.ConfigProperties;
import com.api.utility.ConfigPropertiesUtils;

public class FrameworkConstants {

	private FrameworkConstants() {}

	// Add driver executable file path if not using webdriverManager

	private static final String CONFIGFILEPATH =  System.getProperty("user.dir") + "/src/test/resources/config/config.properties";
	private static final String EXTENTREPORTFOLDERPATH = System.getProperty("user.dir") + "/extent-test-report/";
	private static String extentReportFilePath = "";

	private static final int EXPLICITWAIT = 0;

	private static final String EXTENTCONFIGPATH = System.getProperty("user.dir") + "/src/test/resources/config/extentConfig.xml";


	private static final String chromebrowser = "chrome";
	public static String getChromebrowser() {
		return chromebrowser;
	}


	public static String getFirefoxbrowser() {
		return firefoxbrowser;
	}

	private static final String firefoxbrowser = "firefox";


	public static int getExplicitWait() {
		return EXPLICITWAIT;
	}


	public static String getConfigfilepath() {
		return CONFIGFILEPATH;
	}


	public static String getExtentReportFilePath() throws Exception {
		if(extentReportFilePath.isEmpty()) {
			extentReportFilePath = createFilePath();
		}
		return extentReportFilePath;
	}

	public static String createFilePath() throws Exception{


		if(ConfigPropertiesUtils.get(ConfigProperties.OVERRIDEREPORTS).equalsIgnoreCase("no")) {
			return EXTENTREPORTFOLDERPATH + "index_" + CurrentDate.getCurrentDate() +
					//dateformat.format(cal.getTime())
					".html";
		}else {
			return EXTENTREPORTFOLDERPATH + "index" + ".html";
		}
	}


	public static String getExtentconfigpath() {
		return EXTENTCONFIGPATH;
	}


}
