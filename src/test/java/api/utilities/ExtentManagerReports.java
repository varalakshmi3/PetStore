package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.text.DateFormatter;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManagerReports implements ITestListener{
		public ExtentReports extent;
		public ExtentSparkReporter sparkReporter;
		public ExtentTest test;
		String RepName;

		public void onTestSuccess(ITestResult result) {
		test=extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.PASS, "test Passed");
		
		
		}
		
		
		public void onTestFailure(ITestResult result) {
		test=extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.FAIL, "Test Failed");
		test.log(Status.FAIL, result.getThrowable().getMessage());
	}

	public void onTestSkipped(ITestResult result) {
		test=extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, "Test Skipped");
		test.log(Status.SKIP, result.getThrowable().getMessage());
		
	}

	public void onStart(ITestContext context)
	{
		String timestamp=new SimpleDateFormat("yyyy.MM.ss.hh.mm.ss").format(new Date());
		RepName="Report."+timestamp+".html";
		sparkReporter=new ExtentSparkReporter(".//reports//"+RepName);
		sparkReporter.config().setDocumentTitle("Rest Assured Automation Project");
		sparkReporter.config().setReportName("Pet Store Report");
		sparkReporter.config().setTheme(Theme.DARK);
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Os", System.getProperty("os.name"));
		extent.setSystemInfo("userName", System.getProperty("user.name"));
		extent.setSystemInfo("Application", "Pet Store User API's");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user","varalakshmi");
	}

	public void onFinish(ITestContext context) {
		extent.flush();
	}

}
