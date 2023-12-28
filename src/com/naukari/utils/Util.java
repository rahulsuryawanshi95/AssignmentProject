package com.naukari.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;


public class Util {
	public static Properties prop = null;

	public static WebDriver getWebdriver(String browserName) throws MalformedURLException {
		WebDriver webDriver = null;
		if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					"../InterviewAssignment/src/com/naukari/data/chromedriver.exe");
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setBrowserName("firefox");
			webDriver = new FirefoxDriver();
		}
		if (browserName.equalsIgnoreCase("internet explorer")) {
			System.setProperty("webdriver.ie.driver", "../InterviewAssignment/src/com/naukari/data/chromedriver.exe");
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriverService.IE_DRIVER_EXTRACT_PATH_PROPERTY, "..");
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
			capabilities.setCapability("requireWindowFocus", true);
			capabilities.setBrowserName("internet explorer");
			webDriver = new InternetExplorerDriver();
		}
		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"../InterviewAssignment/src/com/naukari/data/chromedriver.exe");
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setJavascriptEnabled(true);
			capabilities.setBrowserName("chrome");
			capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
			LoggingPreferences logs = new LoggingPreferences();
			logs.enable(LogType.BROWSER, Level.ALL);
			logs.enable(LogType.BROWSER, Level.ALL);
			logs.enable(LogType.CLIENT, Level.ALL);
			logs.enable(LogType.DRIVER, Level.ALL);
			logs.enable(LogType.SERVER, Level.ALL);
			logs.enable(LogType.PERFORMANCE, Level.ALL);
			capabilities.setCapability(CapabilityType.LOGGING_PREFS, logs);
			webDriver = new ChromeDriver();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-features=VizDisplayCompositor");
		}
		return webDriver;
	}

	public static Object[][] readExcelFile(int sheetIndex, String fileName) throws IOException {
		// Path to read Excel file
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\com\\naukari\\data\\" + fileName + ".xls");

		Workbook workbook = new HSSFWorkbook(fis);
		Sheet sheet = workbook.getSheetAt(sheetIndex);

//		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		// Read data from 0th Sheet
//		XSSFSheet sheet = workbook.getSheetAt(sheetIndex);

		// get Total no of row count present in sheet
		int rowCount = sheet.getPhysicalNumberOfRows();
		Row row = sheet.getRow(1);

//		XSSFRow row = sheet.getRow(1);

		// get Total no of column
		int colCount = row.getLastCellNum();

		// Initialize Object array to store data
		Object data[][] = new Object[rowCount - 1][colCount];
		for (int i = 0; i < rowCount - 1; i++) {
			// Read data from first row(as 0th row contain column Header)
			row = sheet.getRow(i + 1);
			for (int j = 0; j < colCount; j++) {
//				XSSFCell cell = row.getCell(j);

				Cell cell = row.getCell(j);

				DataFormatter formatter = new DataFormatter();
				// Convert data in the form of String
				data[i][j] = formatter.formatCellValue(cell);

			}
		}
		return data;
	}

}
