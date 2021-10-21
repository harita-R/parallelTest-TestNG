package testng2;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;

public class TestMethodParallelExecution {
	public String username = "YOUR_USERNAME";
	public String accesskey = "YOUR_ACCESSKEY";
	public RemoteWebDriver driver = null;
	public String gridURL = "@hub.lambdatest.com/wd/hub";
	boolean status = false;


	@BeforeMethod
	@Parameters(value={"browser","version","platform"})
	public void setUp(String browser, String version, String platform) throws Exception {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("browserName", browser);
		capabilities.setCapability("version", version);
		capabilities.setCapability("platform", platform); // If this cap isn't specified, it will just get the any available one
		capabilities.setCapability("build", "TestMethodParallelExecution");
		capabilities.setCapability("name", "TestMethodParallelExecution");
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

	@Test
	public void forgotPassword() {

		// To navigate to URL
		driver.get("https://opensource-demo.orangehrmlive.com/");
		// To locate and click on Forgot password link
		driver.findElement(By.linkText("Forgot your password?")).click();	
		// To locate input field and enter username
		driver.findElement(By.id("securityAuthentication_userName")).sendKeys("testuser");
		// To locate and click on reset button
		driver.findElement(By.id("btnSearchValues")).click();		       
	}

	@AfterMethod
	public void tearDown() throws Exception {
		if (driver != null) {
			System.out.println("Tearing down..");
			driver.quit();
		}
	}

}
