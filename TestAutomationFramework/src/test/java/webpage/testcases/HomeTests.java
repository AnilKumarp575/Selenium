package webpage.testcases;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import webpage.pageObjects.HomePage;
import webpage.utils.BaseUtil;

public class HomeTests extends BaseUtil {
	
	@Parameters({"email", "password"})
	@Test
	public void HomePage_Test1(String email, String password){
		HomePage home = new HomePage(driver);	
		
		//********* Assertions *********
		Assert.assertEquals(home.isEmailDisplayed(), true, "Email is not displayed on the Home screen");
		extentTest.pass("Email is displayed on Home Screen");
		Assert.assertEquals(home.isPasswordDisplayed(), true, "Password is not displayed on the Home screen");
		extentTest.pass("Password is displayed on Home Screen");
		Assert.assertEquals(home.isSignInBtnDisplayed(), true, "Sign in button is not displayed on the Home screen");
		extentTest.pass("Sign In button is displayed on Home Screen");
		
		//********* Navigation Flow **********
		home.setEmail(email);
		extentTest.pass("Email value set successfully as '"+ email+"'");
		home.setPassword(password);
		extentTest.pass("Password value set successfully");
	}
	
	
	@Test
	public void HomePage_Test2() {	
		HomePage home = new HomePage(driver);
		
		//********* Navigation Flow ***********
		String expectedListItemName =  "List Item 2";		
		String itemValue = home.getItemvalueByIndex(2);
		String badge = home.getItemBadgeByIndex(2);
		String actualListItemValue = itemValue.substring(0, expectedListItemName.length());
		
		//********* Assertions ***********
		Assert.assertEquals(home.getListItemCount(), 3, "List Items count does not match");
		extentTest.pass("There are "+ home.getListItemCount() + " items displayed in the list group");
		Assert.assertEquals(actualListItemValue, expectedListItemName, "List Item Badge is not displayed correctly");
		extentTest.pass("Second list item's value is displayed as: "+ actualListItemValue);
		Assert.assertEquals(badge, "6", "List Item Badge is not displayed as "+badge+" instead of 6");
		extentTest.pass("Second list item's badge value is displayed as: "+ badge);
	}
	
	@Parameters("selectOption")
	@Test
	public void HomePage_Test3(String selectOption) {		
		
		HomePage home = new HomePage(driver);
		
		//********* Navigation Flow ***********
		String defaultValueSelected = home.getDropdownSelectedValue().trim();
		
		Assert.assertEquals(defaultValueSelected, "Option 1", "The value selected is '" + defaultValueSelected +"' instead of Option 1");
		extentTest.pass("Default option selected in dropdown is: " + defaultValueSelected);
		
		//select option 3 from drop down
		home.selectADropdownValue(selectOption);
		String newValueSelected = home.getDropdownSelectedValue().trim();
		
		//********* Assertions ***********
		Assert.assertEquals(newValueSelected, selectOption, "The value selected is '" + newValueSelected +"' instead of "+selectOption);
		extentTest.pass("New value selected in dropdown as: " + newValueSelected);		
		
	}
	
	
	@Test
	public void HomePage_Test4() {		
		
		HomePage home = new HomePage(driver);
		
		//********* Assertions ***********
		Assert.assertTrue(home.isPrimaryButtonEnabled(), "Primary button is not enabled");
		extentTest.pass("Primary button is enabled");
		Assert.assertFalse(home.isSecondaryButtonEnabled(), "Secondary button is not disabled");
		extentTest.pass("Secondary button is disabled");
	}
	
	@Test
	public void HomePage_Test5() {		
		
		HomePage home = new HomePage(driver);
		
		//********* Navigation Flow ***********
		home.clickDynamicButton();
		extentTest.pass("Dynamic button is clicked successfully");
		String successMessage = home.getSuccessAlertMessage();
		String expectedMessage = "You clicked a button!";
		
		//********* Assertions ***********
		Assert.assertEquals(successMessage, expectedMessage, "Success Message is not displayed");
		extentTest.pass("Alert Message: '"+ successMessage +"' is displayed successfully");
		Assert.assertFalse(home.isDynamicButtonEnabled(), "Dynamic button is not disabled");
		extentTest.pass("Dynamic button is disabled after click");
	}
	
	@Parameters({"rowIndex", "colIndex", "expectedValue"})
	@Test
	public void HomePage_Test6(int rowIndex, int colIndex, String expectedValue) {		
		
		HomePage home = new HomePage(driver);
		
		//********* Navigation Flow ***********
		String cellValue = home.getDynamicCellValue(rowIndex, colIndex);
		
		//********* Assertions ***********
		Assert.assertEquals(cellValue, expectedValue, "Failed: Expected value-"+ expectedValue + "Actual value-" +cellValue);
		extentTest.pass("Retrieved value: '"+cellValue+"' for row index: "+rowIndex+ " and column index: "+colIndex +" from the grid");
		
	}
	

}
