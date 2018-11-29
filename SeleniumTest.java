package org.com;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumTest {


		public static String driverPath = "Y:/chromedriver_win32/";
		 public static WebDriver driver;
		 
			public static void main(String []args) {
				System.out.println("launching chrome browser");
				System.setProperty("webdriver.chrome.driver", driverPath+"chromedriver.exe");
				driver = new ChromeDriver();
//				driver.navigate().to("http://google.com");
				driver.navigate().to("file://hqintl1.com/View/Folder_Redirect/9148356/Desktop/Ravi.html");
				waitForPageLoaded(driver);
				Actions actions = new Actions(driver);
				WebElement  headerText =driver.findElement(By.xpath("/html/body/h1[1]"));
				System.out.println("headerText-->"+headerText.getText());
				System.out.println("headerText-->"+headerText.getLocation());
//				headerText.clear();
				actions.moveToElement(headerText,8,20);
				//actions.click();
				
				actions.sendKeys("SOME DATA");
				actions.build().perform();
				System.out.println("After change-->"+driver.findElement(By.xpath("/html/body/h1[1]")).getText());
				
				WebElement button = driver.findElement(By.xpath("/html/body/div/button[4]"));
				System.out.println("Buton Text-->"+button.getText());
				button.click();
			}
			
			public static void waitForPageLoaded(WebDriver driver) {
	               ExpectedCondition<Boolean> expectation = new
	                       ExpectedCondition<Boolean>() {
	                           public Boolean apply(WebDriver driver) {
	                               return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
	                           }
	                       };
	               try {
	                   Thread.sleep(1000);
	                   WebDriverWait wait = new WebDriverWait(driver, 30);
	                   wait.until(expectation);
	               } catch (Throwable error) {
	                   System.out.println("Timeout waiting for Page Load Request to complete.");
	               }
	           }

	}
