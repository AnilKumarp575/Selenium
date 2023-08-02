package webpage.utils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseUtil {
	
	protected static WebDriver driver;
	ExtentReports extentReports = new ExtentReports();
	protected ExtentTest extentTest;
	static String currentDirectory = System.getProperty("user.dir");
	
	/**
	 * Description	: This method is used to initialize the extent reports and is triggered before automation suite is executed
	 * Arguments	: NA
	 * Returns		: NA
	 */
	@BeforeSuite
	public void initialize() {		
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(currentDirectory+"\\target\\reports\\sparkReport.html");
		extentReports.attachReporter(sparkReporter);
	}	
	
	/**
	 * Description	: This method is used to launch the given application web page in chrome browser and invoked automatically before every test method execution
	 * Arguments	: Method, IContext
	 * Returns		: NA
	 */
	@BeforeMethod
	public void launchBrowser(Method method, ITestContext context) {
		extentTest = extentReports.createTest(method.getName());
		WebDriverManager.chromedriver().setup();				
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(currentDirectory+"\\src\\test\\resources\\app\\QE-index.html");
		extentTest.pass("Navigated to home screen successfully");
	}
	
	/**
	 * Description	: This method is used to finally generate the extent reports after test suite execution is complete
	 * Arguments	: NA
	 * Returns		: NA
	 */
	@AfterSuite
	public void tearDown() {
		extentReports.flush();
		
	}
	
	/**
	 * Description	: This method is used to write the status (Pass or Fail) of each test to the extent report and is responsible for closing the browser window after finishing the execution of each test
	 * Arguments	: Method, ITestResult
	 * Returns		: NA
	 */
	@AfterMethod
	public void closeBrowser(Method m, ITestResult result) {		
		if(result.getStatus()== ITestResult.FAILURE) {
			extentTest.fail(m.getName()+ " is failed "+ result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(BaseUtil.takeScreenShot(m)).build());
			
		}else if(result.getStatus()== ITestResult.SUCCESS) {
			extentTest.pass(m.getName()+ " is passed");
		}
		driver.quit();
	}
	
	/**
	 * Description	: This method is used to capture screenshots when a test method failure is occurred, save them in 'target\reports\screenshots' directory and returns the path of the saved screenshot
	 * Arguments	: Method
	 * Returns		: String
	 */
	public static String takeScreenShot(Method m) {
		String destPath ="";
		try {
			TakesScreenshot screenshot = (TakesScreenshot)driver;
			File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
			destPath = currentDirectory+"\\target\\reports\\screenshots\\"+m.getName()+".jpg";
			File destFile = new File(destPath);
			FileUtils.copyFile(srcFile, destFile);
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return destPath;
	}	
	
}
