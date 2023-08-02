package webpage.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	
	WebDriver driver;
	
	@FindBy(id = "inputEmail")
	WebElement email;
	
	@FindBy(id="inputPassword")
	WebElement password;
	
	@FindBy(xpath = "//button[@type='submit']")
	WebElement signInButton;
	
	@FindBy(xpath = "//div[@id='test-2-div']//li")
	List<WebElement> listItems;
	
	@FindBy(xpath="//div[@id='test-3-div']//button[@id='dropdownMenuButton']")
	WebElement dropdownButton;
	
	@FindBy(xpath="//*[@id='test-4-div']/button[contains(@class,'btn-primary')]")
	WebElement primaryButton;
	
	@FindBy(xpath="//*[@id='test-4-div']/button[contains(@class,'btn-secondary')]")
	WebElement secondaryButton;
	
	@FindBy(xpath="//div[@id='test-5-div']//button[@id='test5-button']")
	WebElement dynamicButton;
	
	@FindBy(xpath="//div[@id='test-5-div']//div[@id='test5-alert']")
	WebElement successAlert;
		
	public HomePage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * Description	: This method verifies whether Email edit box is displayed on Home screen and returns 'true' if displayed else 'false'
	 * Arguments	: NA
	 * Returns		: boolean
	 */
	public boolean isEmailDisplayed() {
		return email.isDisplayed();
	}
	
	/**
	 * Description	: This method verifies whether password edit box is displayed on Home screen and returns 'true' if displayed else 'false'
	 * Arguments	: NA
	 * Returns		: boolean
	 */
	public boolean isPasswordDisplayed() {
		return password.isDisplayed();
	}
	
	/**
	 * Description	: This method verifies whether sign in button is displayed on Home screen and returns 'true' if displayed else 'false'
	 * Arguments	: NA
	 * Returns		: boolean
	 */
	public boolean isSignInBtnDisplayed() {
		return signInButton.isDisplayed();
	}
	
	/**
	 * Description	: This method sets a text to email input field on Home screen
	 * Arguments	: String
	 * Returns		: NA
	 */
	public void setEmail(String emailId) {
		email.sendKeys(emailId);
	}
	
	/**
	 * Description	: This method sets a text to password input field on Home screen
	 * Arguments	: String
	 * Returns		: NA
	 */
	public void setPassword(String pwd) {
		password.sendKeys(pwd);
	}
	
	/**
	 * Description	: This method retrieves the count of list items displayed in the list group of Home screen
	 * Arguments	: NA
	 * Returns		: int
	 */
	public int getListItemCount() {
		return listItems.size();
	}
	
	/**
	 * Description	: This method retrieves the badge value of a list item whose index is passed as an argument to it
	 * Arguments	: int
	 * Returns		: String
	 */
	public String getItemBadgeByIndex(int index) {
		WebElement itemBadge = driver.findElement(By.xpath("//li[contains(@class,'list-group-item')]["+index+"]/span[contains(@class, 'badge')]"));
		return itemBadge.getText();
	}
	
	/**
	 * Description	: This method retrieves the text value of a list item whose index is passed as an argument to it
	 * Arguments	: int
	 * Returns		: String
	 */
	public String getItemvalueByIndex(int index) {
		WebElement itemValue = driver.findElement(By.xpath("//li[contains(@class,'list-group-item')]["+index+"]"));
		return itemValue.getText();
	}
	
	/**
	 * Description	: This method retrieves the text value of option selected in the drop down
	 * Arguments	: NA
	 * Returns		: String
	 */
	public String getDropdownSelectedValue() {
		return dropdownButton.getText();
	}
	
	/**
	 * Description	: This method helps to select an option from the drop down displayed in Home screen
	 * Arguments	: String
	 * Returns		: NA
	 */
	public void selectADropdownValue(String value) {
		dropdownButton.click();
		WebElement itemValue = driver.findElement(By.xpath("//a[@class='dropdown-item' and text()='"+value+"']"));
		itemValue.click();
	}
	
	/**
	 * Description	: This method verifies whether first button under test 4 div is enabled and returns 'true' if enabled else 'false'
	 * Arguments	: NA
	 * Returns		: boolean
	 */
	public boolean isPrimaryButtonEnabled() {
		return primaryButton.isEnabled();
	}
	
	/**
	 * Description	: This method verifies whether second button under test 4 div is enabled and returns 'true' if enabled else 'false'
	 * Arguments	: NA
	 * Returns		: boolean
	 */
	public boolean isSecondaryButtonEnabled() {
		return secondaryButton.isEnabled();
	}
	
	/**
	 * Description	: This method helps to click button displayed in test 5 div of Home screen, the click is performed only when the button is displayed on the screen with a maximum wait time as 30 seconds 
	 * Arguments	: NA
	 * Returns		: NA
	 */
	public void clickDynamicButton() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(dynamicButton));
		dynamicButton.click();
	}
	
	/**
	 * Description	: This method returns the text displayed in the alert message 
	 * Arguments	: NA
	 * Returns		: String
	 */
	public String getSuccessAlertMessage() {
		return successAlert.getText().trim();
	}
	
	/**
	 * Description	: This method verifies whether the button in div 5 is enabled and returns 'true' if enabled else 'false'  
	 * Arguments	: NA
	 * Returns		: boolean
	 */
	public boolean isDynamicButtonEnabled() {
		return dynamicButton.isEnabled();
	}
	
	/**
	 * Description	: This method returns the value of any cell on the grid by accepting the index of row and column 
	 * Arguments	: int, int
	 * Returns		: String
	 */
	public String getDynamicCellValue(int rowNum,int colNum) {
		rowNum++;
		colNum++;
		WebElement cell = driver.findElement(By.xpath("//table//tr["+rowNum+"]/td["+colNum+"]"));
		return cell.getText();
	}
}
