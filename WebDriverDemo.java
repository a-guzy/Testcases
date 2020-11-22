import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import junit.framework.Assert;

public class WebDriverDemo {

	public static void main(String[] args) throws Exception {
		
		WebDriver driver = new FirefoxDriver(); //open Firefox
		//WebDriver driver = new ChromeDriver(); //open Chrome
		//WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), 
		//		DesiredCapabilities.firefox()); //test on Selenium Server using Firefox
		
		driver.get("https://test.salesforce.com"); //navigate to URL
		
		//Login:
		WebElement userField = driver.findElement(By.id("username")); 
		userField.sendKeys("a.guzy-f3pb@force.com");
		WebElement passField = driver.findElement(By.id("password")); 
		passField.sendKeys("fakePass3");
		passField.submit(); //hit the enter key
		
		
		
		//Test Case no. 1
		
		//Navigate to Accounts
		WebElement accountsTab = driver.findElements(By.className("slds-truncate")).get(3);
		accountsTab.click();
		
		//Create new account
		String createAccountName = "Account2";
		WebElement newAccount = driver.findElement(By.xpath("/html/body/div[4]/div[1]/section/div[1]/div[2]/div[1]/div/div/div/div/div/div/div/div[1]/div[1]/div[2]/ul/li[1]/a/div"));
		newAccount.click();
		
		WebElement accountName = driver.findElement(By.id("66:2524;a"));
		accountName.sendKeys(createAccountName);
		WebElement saveChanges = driver.findElement(By.linkText("Save"));
		saveChanges.click();
		
		//Verification
		accountsTab.click();
		WebElement myAccountName = driver.findElement(By.xpath("/html/body/div[4]/div[1]/section/div[1]/div[2]/div[1]/div/div/div/div/div/div/div/div[2]/div/div[1]/div[2]/div[2]/div[1]/div/div/table/tbody/tr[1]/th/span"));
		Assert.assertEquals(createAccountName, myAccountName.getText());
		
		//Test Case no. 2
		/* if it should run separately, then we should 
		 *  - clean up after Test Case no. 1
		 *  - maybe logout (depending on the test suite)
		 *  - log in again
		 *  - go to Accounts
		 * 
		 * 
		 * if it runs after Test Case no. 1:
		 */
		myAccountName.click();
		
		WebElement editButton = driver.findElements(By.name("Edit")).get(1);
		editButton.click();
		
		String phone = "987654321";
		
		WebElement phoneNo = driver.findElement(By.id("8962:0"));
		phoneNo.sendKeys(phone);
		
		WebElement save = driver.findElement(By.linkText("Save"));
		save.click();
		
		//Verify:
		WebElement checkPhoneNo = driver.findElement(By.xpath("/html/body/div[4]/div[1]/section/div[1]/div[2]/div[2]/div[1]/div/div/div/div[2]/div/one-record-home-flexipage2/forcegenerated-adgrollup_component___forcegenerated__flexipage_recordpage___account_record_page___account___view/forcegenerated-flexipage_account_record_page_account__view_js/record_flexipage-record-page-decorator/div[1]/slot/flexipage-record-home-template-desktop2/div/div[2]/div[1]/slot/slot/flexipage-component2/slot/flexipage-tabset2/div/lightning-tabset/div/slot/slot/slot/flexipage-tab2[1]/slot/flexipage-component2/slot/records-lwc-detail-panel/records-base-record-form/div/div/div/div/records-record-layout-event-broker/slot/records-lwc-record-layout/forcegenerated-detailpanel_account___012000000000000aaa___full___view___recordlayout2/force-record-layout-block/slot/force-record-layout-section[1]/div/div/div/slot/force-record-layout-row[1]/slot/force-record-layout-item[2]/div/div/div[2]/span/slot[1]/slot/lightning-formatted-phone/a"));
		Assert.assertEquals(phone, checkPhoneNo.getText());
		
	}
	

}
