package SAILEELATESTCLASS;

import java.time.Duration;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Utility.clickonblankspace;
import base.SAILEELABaseclass;
import io.appium.java_client.AppiumBy;

public class Createnewexpenses extends SAILEELABaseclass {

	// Locator
	By menubar = By.xpath(
			"//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.widget.Button");
	By expenseandpurchase = AppiumBy.accessibilityId("Expenses & Purchase");
	By Expensesmenu = AppiumBy.accessibilityId("Expenses");
	By Addexpenses = AppiumBy.accessibilityId("Add Expense");
	By selectExpenseName = AppiumBy.accessibilityId("Select Expense Name\nSelect Expense Name");
	By AddNewExpense = AppiumBy.accessibilityId("Add New Expense");
	By expensesnamefield = AppiumBy
			.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)");
	By Expensesamountfield = AppiumBy
			.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(1)");
	By submitbutton = AppiumBy.accessibilityId("Submit");
	By searchfield = By.xpath("//android.widget.EditText");
	By editbutton = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(2)");
	By newamount = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(1)");
	By deletebutton = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(3)");
	By deleteconfirmation = AppiumBy.accessibilityId("Delete");

	

	@DataProvider(name = "expensesDetails")
	public Object[][] itemDetails() {

		String expensenamefield = "Disel";
		String expenseamount = "1000";
		String updatedexpenseamount = "2000";

		return new Object[][] { { expensenamefield, expenseamount, updatedexpenseamount } };
	}

	@Test(dataProvider = "expensesDetails", priority = 1)
	public void createexpenses(String expensenamefield, String expenseamount, String updatedexpenseamount)
			throws Exception {

		test = extent.createTest("Create the Supplier with name: " + expensenamefield);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		driver.findElement(menubar).click();
		driver.findElement(expenseandpurchase).click();
		driver.findElement(Expensesmenu).click();
		driver.findElement(Addexpenses).click();
		Thread.sleep(2000);
		driver.findElement(selectExpenseName).click();
		driver.findElement(AddNewExpense).click();
		driver.findElement(expensesnamefield).click();
		driver.findElement(expensesnamefield).sendKeys(expensenamefield);
		driver.findElement(Expensesamountfield).click();
		driver.findElement(Expensesamountfield).sendKeys(expenseamount);
		driver.findElement(submitbutton).click();

		test.pass("Expenses  created successfully");
	}

	@Test(dataProvider = "expensesDetails", priority = 2)
	public void editexpenses(String expensenamefield, String expenseamount, String updatedexpenseamount)
			throws Exception {

		test = extent.createTest("edit the expense  " + expensenamefield);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		driver.findElement(searchfield).click();
		driver.findElement(searchfield).sendKeys(expensenamefield);
		driver.findElement(editbutton).click();
		driver.findElement(newamount).click();
		driver.findElement(newamount).clear();
		driver.findElement(newamount).sendKeys(updatedexpenseamount);
		driver.findElement(submitbutton).click();

		test.pass("Expense updated  successfully");
		
		Thread.sleep(2000);
		driver.navigate().back();
		clickonblankspace.clickAtPosition(driver, 740, 1110);

	}

//	@Test(dataProvider = "expensesDetails", priority = 3)
//	public void deleteexpenses(String expensenamefield, String expenseamount, String updatedexpenseamount)
//			throws Exception {
//
//		test = extent.createTest("Delete the expense  " + expensenamefield);
//
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
//
//		driver.findElement(searchfield).click();
//		driver.findElement(searchfield).clear();
//		driver.findElement(searchfield).sendKeys(expensenamefield);
//		driver.findElement(deletebutton).click();
//		driver.findElement(deleteconfirmation).click();
//
//		test.pass("Expense Deleted  successfully");
//
//	}

}
