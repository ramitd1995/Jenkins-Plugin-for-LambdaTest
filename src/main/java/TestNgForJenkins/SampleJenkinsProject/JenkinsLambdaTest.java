package TestNgForJenkins.SampleJenkinsProject;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class JenkinsLambdaTest {
	
	public String username = System.getenv("LT_USERNAME");
	public String accesskey = System.getenv("LT_ACCESS_KEY");
	public RemoteWebDriver driver = null;
	public String gridURL = System.getenv("LT_GRID_URL");
	boolean status = false;

	

	@BeforeTest
	public void setUp() throws Exception {
		
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		
		capabilities.setCapability("browserName", System.getenv("LT_BROWSER_NAME"));
		capabilities.setCapability("version", System.getenv("LT_BROWSER_VERSION"));
		capabilities.setCapability("platform", System.getenv("LT_PLATFORM")); // If this cap isn't specified, it will just get
		
		capabilities.setCapability("build", System.getenv("LT_BUILD_NAME"));
		capabilities.setCapability("name", System.getenv("LT_BUILD_NUMBER"));
		
		capabilities.setCapability("network", true); // To enable network logs
		capabilities.setCapability("visual", true);
		capabilities.setCapability("video", true); // To enable video recording`
		capabilities.setCapability("console", true); // To capture console logs
		capabilities.setCapability("resolution", System.getenv("LT_RESOLUTION"));

		capabilities.setCapability("tunnel",true);
		capabilities.setCapability("tunnelName", "Ramit-007");
		Thread.sleep(10000);



		try {
			driver = new RemoteWebDriver(new URL(gridURL), capabilities);

		} catch (MalformedURLException e) {
			System.out.println("Invalid grid URL");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void testSimple() throws Exception {
		try {
			
			driver.get("http://localhost.lambdatest.com/attendance/attendance_view.php");
			driver.manage().window().maximize();
			Thread.sleep(5000);
	        
			
			System.out.println("Opening URL");
			
			/*
			driver.get("https://apple.com/");
			driver.manage().window().maximize();
			Thread.sleep(5000);

			System.out.println("iPad");
			driver.findElement(By.xpath("//*[@id=\'ac-globalnav\']/div/ul[2]/li[3]")).click();
			Thread.sleep(2000);

			System.out.println("iPad Air");
			driver.findElement(
					By.cssSelector("#chapternav > div > ul > li.chapternav-item.chapternav-item-ipad-air > a")).click();
			Thread.sleep(2000);

			System.out.println("Why iPad");
			driver.findElement(By.linkText("Why iPad")).click();
			Thread.sleep(2000);
			*/
			System.out.println("Test Execution Finished");
					
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@AfterTest
	public void tearDown() throws Exception {
		if (driver != null) {
			((JavascriptExecutor) driver).executeScript("lambda-status=" + status);
			driver.quit();
		}
	}

	
	

}
