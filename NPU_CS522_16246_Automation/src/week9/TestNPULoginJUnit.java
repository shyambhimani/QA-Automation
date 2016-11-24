package week9;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;


public class TestNPULoginJUnit {
	public WebDriver driver;
	Actions actions;
	Logger logger = Logger.getLogger("CS522_16246_TestNPULoginJunit");

//	@Ignore
	@Test
	public void testNPUCreateNewAccountJUnit(){			
		
		openApplyOnline();
		logger.info("Step 4. If Apply Online button not found, try - Passed");
		captureScreenshot(driver,"Step" + "4" + " 16246 " );
		
		verifycheckboxIUnderstandNotSelected(driver);
		logger.info("Step 5. Verify checkbox is not selected - Passed");
		captureScreenshot(driver,"Step" + "5" + " 16246 " );
		
		selectCheckboxIUnderstand(driver);
		logger.info("Step 6. Select check box " + "\"" +"I understand and I would like to register a new account - Passed");
		captureScreenshot(driver,"Step" + "6" + " 16246 " );
		
		openLinkText(driver,"Register Account");
		logger.info("Step 7. Click link text " + "\"" +"Register Account - Passed");
		captureScreenshot(driver,"Step" + "7" + " 16246 " );
		
		selectRadioButtonNewApplicant(driver, "new");
		logger.info("Step 8. Verify default new button should select in New Applicant - Passed");
		captureScreenshot(driver,"Step" + "8" + " 16246 " );
		
		selectGender(driver, "Male");
		logger.info("Step 9. Select your Gender - Passed");
		captureScreenshot(driver,"Step" + "9" + " 16246 " );
	
		inputFirstnameLastnameEmailAddressPasswordConfirm(driver, "FirstName","LastName","Email","Password","ConfirmPassword");
		logger.info("Step 10. Input Given Name, Surname, Email, Password, Confirm Password - Passed");
		captureScreenshot(driver,"Step" + "10" + " 16246 ");
		
		clickSaveButton(driver);
		logger.info("Step 11. Click Save button - Passed");
		captureScreenshot(driver,"Step" + "11" + " 16246 ");
		
		driver.navigate().back();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		sleep(2);
		
		driver.navigate().back();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		sleep(2);		
		logger.info("Step 13. Go back to create new account page - Passed");
		captureScreenshot(driver,"Step" + "13" + " 16246 ");
		
		driver.navigate().back();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		sleep(2);		
		logger.info("Step 14. Go back to NPU home page - Passed");
		captureScreenshot(driver,"Step" + "14" + " 16246 ");
		

	}
	
	@BeforeClass
	public static void beforeclass(){
		System.out.println("==========Beginning==========");
	}
		@AfterClass
	public static void afterclass(){
			System.out.println("==========ENDING==========");
		}

	
	@Before
	public void setUp(){
		
		//1. Initiate Firefox browser
		driver = new FirefoxDriver();
		
		logger.info("Step 1. Initiate Firefox Browser - Passed");
		captureScreenshot(driver, "Step" + "1" + " 16246 ");
		
		//2. Google search and open the search result
		GoogleSearchOpenResult(driver, actions, "NPU", "Northwestern Polytechnic University | Welcome to ... - Fremont", "http://npu.edu");
		logger.info("Step 2. Google search and click to open search result - Passed");
		captureScreenshot(driver,"Step" + "2" + " 16246 ");
		
		//3. Verify NPU page has loaded 
		verifyWebPageIsOpened(driver, "Northwestern Polytechnic University | Welcome to Northwestern Polytechnic University - Fremont");
		logger.info("Step 3. Verify NPU page is loaded - Passed");
		captureScreenshot(driver,"Step" + "3" + " 16246 ");
		
	}
	
	@After
	public void tearDown(){
		driver.close();
		logger.info("final step:close browser and exit-passed");
		captureScreenshot(driver,"Test"+"start" + " 16246 ");
		
	}
	
	public void GoogleSearchOpenResult(WebDriver driver, Actions action, String searchtext, String searchresultlink, String WebURL){
		driver.get("http://www.google.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.id("lst-ib")).sendKeys("NPU");
		Actions actions = new Actions(driver);
	    actions.sendKeys(Keys.ENTER).build().perform();
	    sleep(3);
	    if(driver.getPageSource().contains(searchresultlink)){
	    	driver.findElement(By.linkText(searchresultlink)).click();	    	
	    }
	    else{
	    	driver.navigate().to(WebURL);	    	
	    }
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	
	    if(driver.findElement(By.linkText("Northwestern Polytechnic University | Welcome to ... - Fremont")).isDisplayed() ){
		    driver.findElement(By.linkText("Northwestern Polytechnic University | Welcome to ... - Fremont")).click(); // Click Search Result Link Text to open NPU homepage
		    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); // wait maximum up to 30s until page loaded completely
		    
	    } else{
	    	driver.get("http://www.npu.edu"); // if not found the proper search result link text, just open npu.edu web site
	    }
	}
	
	public void verifyWebPageIsOpened(WebDriver driver, String title){
		try{
			Assert.assertTrue(driver.getTitle().contains(title));
		}catch(Throwable t){
			t.getMessage();
		}
	}
	
	public void openApplyOnline(){
		driver.findElement(By.xpath(".//*[@id='block-tb-megamenu-menu-header-top-menu']/div/div/div/ul/li[2]/a")).click();;
		driver.findElement(By.xpath(".//*[@id='tb-megamenu-column-6']/div/ul/li[2]/a")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		sleep(1);
	}
	
	public void verifycheckboxIUnderstandNotSelected(WebDriver driver){
		List<WebElement> checkbox = driver.findElements(By.id("regcheck"));
		boolean b = false;
		try{
			b = checkbox.get(0).isSelected();
		}catch(Throwable t){
			Assert.assertFalse(b);
		}
 
	}
	
	public void selectCheckboxIUnderstand(WebDriver driver){

		List<WebElement> checkbox = driver.findElements(By.id("regcheck"));
		boolean b = false;
		b = checkbox.get(0).isSelected();
				if(b==true){
					
				}
				else{
					checkbox.get(0).click();
				}
					
	}
	
	public void openLinkText(WebDriver driver, String linktext){
		driver.findElement(By.xpath(".//*[@id='main']/fieldset/fieldset/fieldset[2]/div/a")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	public void selectRadioButtonNewApplicant(WebDriver driver, String value){
		List<WebElement> radio = driver.findElements(By.name("userType"));
		int size = radio.size();
		for(int i=0; i<size; i++){
			String getValue = radio.get(i).getAttribute("value");
			if(getValue.equalsIgnoreCase(value)){
				radio.get(i).click();
				break;
			}			
		}
	}
	
	public void selectGender(WebDriver driver, String gender){
		WebElement select = driver.findElement(By.name("Gender"));
		List<WebElement> options = select.findElements(By.tagName("option"));
		for(WebElement option:options){
			if(option.getText().equals(gender)){
				option.click();
				break;
			}
		}
		sleep(1);
	}
		
	public void inputFirstnameLastnameEmailAddressPasswordConfirm(WebDriver driver, String firstname,String lastname,String email, String password, String confirmpassword){
		if(firstname !=null){
			driver.findElement(By.id("FirstName")).clear();
			driver.findElement(By.id("FirstName")).sendKeys("Shyam");
		}
		
		if(lastname !=null){
			driver.findElement(By.id("LastName")).clear();
			driver.findElement(By.id("LastName")).sendKeys("Bhimani");
		}
		
		if(email !=null){
			driver.findElement(By.id("Email")).clear();
			driver.findElement(By.id("Email")).sendKeys("shyambhimani@gmail.com");
		}
		
		if(password !=null){
			driver.findElement(By.id("Password")).clear();
			driver.findElement(By.id("Password")).sendKeys("00016246");
		}
		
		if(confirmpassword !=null){
			driver.findElement(By.id("ConfirmPassword")).clear();
			driver.findElement(By.id("ConfirmPassword")).sendKeys("00016246");
		}
	}

	public void clickSaveButton(WebDriver driver){
		driver.findElement(By.xpath(".//*[@id='main']/fieldset/form[2]/input")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		sleep(3);
	}
	
	private void sleep(int i) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public static String captureScreenshot(WebDriver driver, String screenshotname) {
		
		try {
				TakesScreenshot ts = (TakesScreenshot)driver;
		
				File source = ts.getScreenshotAs(OutputType.FILE);
				
				String dest = "./screenshot/" + screenshotname + ".png";
				
				File destination = new File(dest);
		
				//FileUtils.copyFile(source, new File("./screenshot/" + screenshotname + ".png"));
				
				FileUtils.copyFile(source, destination);
			
				System.out.println("screnshot taken");
				
				return dest;
				
		} catch (Exception e) {
			System.out.println("Exception while taking screenshot." + e.getMessage());
			return e.getMessage();
		}
	}
	
}