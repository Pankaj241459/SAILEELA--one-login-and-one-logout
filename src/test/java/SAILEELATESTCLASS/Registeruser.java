package SAILEELATESTCLASS;

import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Utility.clickonblankspace;
import base.SAILEELABaseclass;
import io.appium.java_client.AppiumBy;

public class Registeruser extends SAILEELABaseclass {

	By menubar = By.xpath(
			"//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.widget.Button");
	By UserManagement = AppiumBy.accessibilityId("User Management");
	By usersModule = AppiumBy.accessibilityId(
		    "Users Module\nManage users and accounts"
		);
	By adduserbutton =AppiumBy.accessibilityId("Add User");
	By username=AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)");
	By Fullname=AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(1)");
	By Mobilenumber=AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(2)");
	By selectrole=AppiumBy.accessibilityId("Select Role");
	By role=AppiumBy.accessibilityId("Store Manager");
	By address=AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(3)");
	By enterpassword=AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(4)");
	By enterconfirmpassword= AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(5)");
	By viewpassword=AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(2)");
	By createusersubmitbutton= AppiumBy.accessibilityId("Submit");
	By searchfield=By.xpath("//android.widget.EditText");
	By editbutton=AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(2)");
	
	By submitbutton=AppiumBy.accessibilityId("Submit");
	By deletebutton=AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(3)");
	By deleteconfirmationbutton=AppiumBy.accessibilityId("Delete");
	
	
	

	@DataProvider(name = "Userdetails")
	public Object[][] itemDetails() {
		String MobileNumber = "9226339090";
		String Username = "avdhut";
		String fullname = "AvdhutPawar";
		String Address = "Kolhapur";
		String password = "avdhut@123";
		String confirmpassword = "avdhut@123";
		String updatedaddress = "Pune";
		return new Object[][] {
				{ MobileNumber, Username, fullname, Address, password, confirmpassword, updatedaddress } };
	}

	@Test(dataProvider = "Userdetails", priority = 1)
	public void registernewuser(String MobileNumber, String Username, String fullname, String Address, String password,
			String confirmpassword, String updatedaddress) throws Exception {
		test = extent.createTest("Register New User");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		driver.findElement(menubar).click();
		driver.findElement(UserManagement).click();
		driver.findElement(usersModule).click();
		driver.findElement(adduserbutton).click();
		driver.findElement(username).click();
		driver.findElement(username).sendKeys(Username);
		driver.findElement(Fullname).click();
		driver.findElement(Fullname).sendKeys(fullname);

		driver.findElement(Mobilenumber).click();
		driver.findElement(Mobilenumber).sendKeys(MobileNumber);

		driver.findElement(selectrole).click();
		driver.findElement(role).click();
		driver.findElement(address).click();
		driver.findElement(address).sendKeys(Address);
		driver.findElement(enterpassword).click();
		driver.findElement(enterpassword).sendKeys(password);
		driver.findElement(enterconfirmpassword).click();
		driver.findElement(enterconfirmpassword).sendKeys(confirmpassword);
		driver.findElement(viewpassword).click();
		driver.hideKeyboard();
		driver.findElement(createusersubmitbutton).click();

		Thread.sleep(1000);
		test.pass("User registeres successfully");

	}

	@Test(dataProvider = "Userdetails", priority = 2, dependsOnMethods = "registernewuser")
	public void updatenewuser(String MobileNumber, String Username, String fullname, String Address, String password,
			String confirmpassword, String updatedaddress) throws Exception {
		test = extent.createTest("Edit New User");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.findElement(searchfield).click();
		driver.findElement(searchfield).clear();
		driver.findElement(searchfield).sendKeys(Username);

		driver.findElement(editbutton).click();
		driver.findElement(address).click();
		driver.findElement(address).clear();
		driver.findElement(address).sendKeys(updatedaddress);
		driver.hideKeyboard();
		driver.findElement(submitbutton).click();

		test.pass("User updated successfully");
		Thread.sleep(2000);
		driver.navigate().back();
		Thread.sleep(2000);
		driver.navigate().back();
		clickonblankspace.clickAtPosition(driver, 740, 1110);

	}

//	@Test(dataProvider = "Userdetails", priority = 3, dependsOnMethods = "registernewuser")
//	public void Deletenewuser(String MobileNumber, String Username, String fullname, String Address, String password,
//			String confirmpassword, String updatedaddress) throws Exception {
//		test = extent.createTest("Delete New User");
//
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
//		driver.findElement(searchfield).click();
//		driver.findElement(searchfield).clear();
//		driver.findElement(searchfield).sendKeys(Username);
//		driver.findElement(deletebutton).click();
//		driver.findElement(deleteconfirmationbutton).click();
//
//		test.pass("User deleted successfully");
//
//	}

}
