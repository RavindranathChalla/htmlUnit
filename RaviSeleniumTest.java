package com.sel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class RaviSeleniumTest {

	public static String driverPath = "R:/softwares/chromedriver_win32/";
	 public static WebDriver driver;
	 
		public static void main(String []args) {
			System.out.println("launching chrome browser");
			System.setProperty("webdriver.chrome.driver", driverPath+"chromedriver.exe");
			driver = new ChromeDriver();
//			driver.navigate().to("http://google.com");
			driver.navigate().to("file:///C:/Users/Ravi/Desktop/Ravi.html");
			WebElement textHeader = driver.findElement(By.xpath("/html/body/h1[1]"));
			System.out.println("Header Name-->"+textHeader.getText());
			Actions actions = new Actions(driver);
			actions.moveToElement(textHeader);
			actions.click();
			actions.sendKeys("RAVI TEST");
			actions.build().perform();
			System.out.println("Updated Header Value-->"+driver.findElement(By.xpath("/html/body/h1[1]")).getText());
		
			WebElement button = driver.findElement(By.xpath("/html/body/div/div[2]/button[3]"));
			System.out.println("Button Text-->"+button.getText());
			
		
		}

}
