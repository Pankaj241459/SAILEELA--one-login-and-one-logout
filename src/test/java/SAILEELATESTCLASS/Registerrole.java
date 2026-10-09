package SAILEELATESTCLASS;

import java.time.Duration;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Utility.clickonblankspace;
import base.SAILEELABaseclass;
import io.appium.java_client.AppiumBy;

public class Registerrole extends SAILEELABaseclass {

	By menubar = By.xpath(
			"//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.widget.Button");
	By UserManagement = AppiumBy.accessibilityId("User Management");
	By rolesModule = AppiumBy.accessibilityId("Roles Module\nManage roles & permissions");
	By addrolebutton1 = AppiumBy.accessibilityId("Add Role");
	By enterRolename = By.xpath("//android.widget.EditText");
	By selectall = AppiumBy.accessibilityId("Select All");
	By addportion = AppiumBy.accessibilityId("Add Portion ");
	By deleteportion = AppiumBy.accessibilityId("Delete Portion");
	By editportion = AppiumBy.accessibilityId("Edit Portion");
	By viewportion = AppiumBy.accessibilityId("View Portion");
	By addrolebutton = By.xpath("//android.widget.Button[@content-desc=\"Add Role\"]");
	By searchfield = By.xpath("//android.widget.EditText");
	By viewbutton = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(2)");
	By editbutton = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(3)");
	By updaterole = AppiumBy.accessibilityId("Update Role");
	By deletebutton = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(4)");
	By Deleteconfirmationbutton = AppiumBy.accessibilityId("Delete");

	

	@DataProvider(name = "Roledetails")
	public Object[][] itemDetails() {
		String rolename = "Store Manager";

		return new Object[][] { { rolename } };
	}

	@Test(dataProvider = "Roledetails", priority = 1)
	public void registernewrole(String rolename) throws Exception {
		test = extent.createTest("Register New Role" + rolename);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		driver.findElement(menubar).click();
		driver.findElement(UserManagement).click();
		driver.findElement(rolesModule).click();
		driver.findElement(addrolebutton1).click();
		driver.findElement(enterRolename).click();
		driver.findElement(enterRolename).sendKeys(rolename);
		driver.hideKeyboard();
		driver.findElement(selectall).click();
		Thread.sleep(3000);
		driver.findElement(selectall).click();

		driver.findElement(addportion).click();

		driver.findElement(addrolebutton).click();

		Thread.sleep(1000);
		test.pass("Role registeres successfully");

	}

	@Test(dataProvider = "Roledetails", priority = 2)
	public void viewnewrole(String rolename) throws Exception {
		test = extent.createTest("View New Role" + rolename);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.findElement(searchfield).click();
		driver.findElement(searchfield).sendKeys(rolename);

		driver.findElement(viewbutton).click();
		Thread.sleep(3000);
		driver.navigate().back();

		test.pass("Role details viewed successfully.");

	}

	@Test(dataProvider = "Roledetails", priority = 3)
	public void Editnewrole(String rolename) throws Exception {
		test = extent.createTest("Edit New Role" + rolename);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.findElement(searchfield).click();
		driver.findElement(searchfield).clear();
		driver.findElement(searchfield).sendKeys(rolename);
		driver.findElement(editbutton).click();

		driver.findElement(deleteportion).click();
		driver.findElement(editportion).click();

		driver.findElement(viewportion).click();

		driver.findElement(updaterole).click();

		test.pass("Role edited successfully");
		Thread.sleep(2000);
		driver.navigate().back();
		Thread.sleep(2000);
		driver.navigate().back();
		
		clickonblankspace.clickAtPosition(driver, 740, 1110);
		
		
	}

//	@Test(dataProvider = "Roledetails", priority = 4)
//	public void Deletenewrole(String rolename) throws Exception {
//		test = extent.createTest("Delete New Role" + rolename);
//
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
//		driver.findElement(searchfield).click();
//		driver.findElement(searchfield).clear();
//		driver.findElement(searchfield).sendKeys(rolename);
//		driver.findElement(deletebutton).click();
//		driver.findElement(Deleteconfirmationbutton).click();
//
//		test.pass("Role deleted successfully");
//
//	}
}
