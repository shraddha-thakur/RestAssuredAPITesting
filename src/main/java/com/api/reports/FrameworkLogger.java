package com.api.reports;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Consumer;

import com.api.enums.ConfigProperties;
import com.api.enums.LogType;
import com.api.utility.ConfigPropertiesUtils;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class FrameworkLogger {
	public FrameworkLogger() {	}
	//
	private static Consumer<String> PASS = message -> ExtentManager.getExtentTest().pass(message);
	private static Consumer<String> FAIL = message -> ExtentManager.getExtentTest().fail(MarkupHelper.createLabel(message, ExtentColor.RED));
	private static Consumer<String> SKIP = message -> ExtentManager.getExtentTest().skip(MarkupHelper.createLabel(message, ExtentColor.GREY));
	private static Consumer<String> INFO = message -> ExtentManager.getExtentTest().info(message);
	private static Consumer<String> FAILINFO = message -> ExtentManager.getExtentTest().info(MarkupHelper.createCodeBlock(message, CodeLanguage.XML));

	private static Consumer<String> CONSOLE = message -> System.out.println("[INFO]  " + message);

	private static Consumer<String> EXTENTANDCONSOLE = PASS.andThen(CONSOLE);

	//	private static Consumer<String> TAKESCREENSHOT = message -> ExtentManager.getExtentTest()
	//			.info("Click here to see Screenshot", MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenShotUtils.getBase64Img()).build());

	// SCREENSHOT AS .PNG FILE
	//.info("Click here to see Screenshot", MediaEntityBuilder.createScreenCaptureFromPath(ScreenShotUtils.getPngImg()).build());


	private static Map<LogType, Consumer<String>> MAP = new EnumMap<>(LogType.class);

	private static Map<LogType, Consumer<String>> SCREENSHOTMAP = new EnumMap<>(LogType.class);

	static {
		MAP.put(LogType.PASS, PASS);
		//		MAP.put(LogType.FAIL, FAIL.andThen(TAKESCREENSHOT).andThen(CONSOLE));
		MAP.put(LogType.FAIL, FAIL.andThen(CONSOLE));

		MAP.put(LogType.SKIP, SKIP.andThen(CONSOLE));
		MAP.put(LogType.INFO, INFO);
		MAP.put(LogType.CONSOLE, CONSOLE);
		MAP.put(LogType.EXTENTANDCONSOLE, EXTENTANDCONSOLE);
		MAP.put(LogType.FAILINFO, FAILINFO);

		/*
		 * SCREENSHOTMAP.put(LogType.PASS, PASS.andThen(TAKESCREENSHOT));
		 * SCREENSHOTMAP.put(LogType.FAIL, FAIL.andThen(TAKESCREENSHOT));
		 * SCREENSHOTMAP.put(LogType.SKIP, SKIP.andThen(TAKESCREENSHOT));
		 * SCREENSHOTMAP.put(LogType.INFO, INFO); SCREENSHOTMAP.put(LogType.CONSOLE,
		 * CONSOLE); SCREENSHOTMAP.put(LogType.EXTENTANDCONSOLE,
		 * EXTENTANDCONSOLE.andThen(TAKESCREENSHOT));
		 */
	}

	public static void log(LogType status, String message) throws Exception {

		if(ConfigPropertiesUtils.get(ConfigProperties.PASSEDSTEPSSCREENSHOT).equalsIgnoreCase("yes")) {
			SCREENSHOTMAP.getOrDefault(status, EXTENTANDCONSOLE).accept(message);
		}else {
			MAP.getOrDefault(status, EXTENTANDCONSOLE).accept(message);
		}
	}
}
