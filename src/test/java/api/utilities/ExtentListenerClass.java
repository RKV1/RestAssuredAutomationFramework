package api.utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentListenerClass implements ITestListener {
	ExtentSparkReporter htmlReporter;
	ExtentReports reports;
	ExtentTest	test;
	
	public void configureReport()
	{
		//ReadConfig readConfig = new ReadConfig();
		String timestamp = new SimpleDateFormat("yyyy.mm.dd.hh.mm.ss").format(new Date());
		String reportName = "MyStoreTestReport-" + timestamp + ".html";
		htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir")+ "//Reports//"  + reportName);
		reports = new ExtentReports();
		reports.attachReporter(htmlReporter);
		
		//add system information/environment information to reports
		reports.setSystemInfo("Machine",  "LocalMachine");
		reports.setSystemInfo("OS",  "Windows 11");
		reports.setSystemInfo("user name",  "Ravi");
		
		//Configuration to change look and feel of the report
		htmlReporter.config().setDocumentTitle("Extent Listener Report Demo");
		htmlReporter.config().setReportName("This is my First Report");
		htmlReporter.config().setTheme(Theme.DARK);
		
	}
	
	//OnStart method is called when any Test starts.
	public void onStart(ITestContext Result)
	{
		configureReport();
		System.out.println("On Start method invoked...");
	}
	
	//OnFinish method is called after all tests are executed
	public void onFinish(ITestContext Result)
	{
		System.out.println("On Finished method invoked....");
		reports.flush(); //It is mandatory to call flush method to ensure informtion is written to the started reporter.
	}
	
	//when test case gets failed this method is called
	public void onTestFalure(ITestResult Result)
	{
		System.out.println("Name of test method failed:" + Result.getName());
		test = reports.createTest(Result.getName()); //create entry in html report
		test.generateLog(Status.FAIL, MarkupHelper.createLabel("Name of the failed test case is: "+ Result.getName(), ExtentColor.RED));
		
		String screenShotPath = System.getProperty("user.dir") + "ScreenShots" + Result.getName() + ".png";
		
		File screenShotFile = new File(screenShotPath);
		
		if(screenShotFile.exists())
		{
			test.fail("Captured Screenshot is below:" + test.addScreenCaptureFromPath(screenShotPath));
		}
		
		// test.addScrenCaptureFromPath(null)
	}
	
	//When Test case get skipped, this method is called.
	public void onTestSkkipped(ITestResult Result)
	{
		System.out.println("Name of test method skipped:" + Result.getName());
		
		test = reports.createTest(Result.getName());
		test.log(Status.SKIP, MarkupHelper.createLabel("Name of the skip test case is: " + Result.getName(), ExtentColor.YELLOW));
	}
	
	//When Test case get started, this method is called
	public void onTestStart(ITestResult Result)
	{
		System.out.println("Name of test method started: " + Result.getName());
	}
	
	//When Test case get passed, this method is called
	public void onTestSuccess(ITestResult Result)
	{
		System.out.println("Name of test method successfully executed: " + Result.getName());
		
		test = reports.createTest(Result.getName());
		test.log(Status.PASS,MarkupHelper.createLabel("Name of the passed test casse is: " + Result.getName(), ExtentColor.GREEN));
	}
	
	public void onTestFailedButWithinSuccessPercentage(ITestResult Result)
	{
		
	}
}
