package testng;

import java.util.concurrent.TimeUnit;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ParallelTest {

	public String username = "YOUR_USERNAME";
	public String accesskey = "YOUR_ACCESSKEY";
	public RemoteWebDriver driver = null;
	public String gridURL = "@hub.lambdatest.com/wd/hub";
	boolean status = false;


	@BeforeClass
	@org.testng.annotations.Parameters(value={"browser","version","platform"})
	public void setUp(String browser, String version, String platform) throws Exception {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("browserName", browser);
		capabilities.setCapability("version", version);
		capabilities.setCapability("platform", platform); // If this cap isn't specified, it will just get the any available one
		capabilities.setCapability("build", "ParallelTestNG");
		capabilities.setCapability("name", "ParallelTestNG");
		capabilities.setCapability("network", true); // To enable network logs
		capabilities.setCapability("visual", true); // To enable step by step screenshot
		capabilities.setCapability("video", true); // To enable video recording
		capabilities.setCapability("console", true); // To capture console logs
		try {
			driver = new RemoteWebDriver(new URL("https://" + username + ":" + accesskey + gridURL), capabilities);
		} catch (MalformedURLException e) {
			System.out.println("Invalid grid URL");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void login() {

		// To navigate to URL
		driver.get("https://opensource-demo.orangehrmlive.com/");
		// To locate input field for username and enter value
		driver.findElement(By.name("txtUsername")).sendKeys("Admin");	
		// To locate input field for password and enter value
		driver.findElement(By.name("txtPassword")).sendKeys("admin123");
		// To locate and click on login button
		driver.findElement(By.className("button")).click();		       
	}

	@AfterClass
	public void tearDown() throws Exception {
		if (driver != null) {
			System.out.println("Tearing down..");
			driver.quit();
		}
	}

}
