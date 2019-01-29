package com.e2e.base;



import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.e2e.utilities.ExcelReader;


public class Testbase {
  public static WebDriver driver;
  public static FileInputStream fn;
  public static Properties config;
  public static Properties OR;
  public static Logger log = Logger.getLogger("devpinoyLogger");
  public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir")+ "\\src\\test\\resources\\excel\\TestData.xlsx");
  
  
  @BeforeSuite
  public void beforeMethod() throws IOException {
	  if (driver == null){
		fn = new FileInputStream(System.getProperty("user.dir")+ "\\src\\test\\resources\\properties\\config.properties");
	  	config = new Properties();
	  	config.load(fn);
	  	fn = new FileInputStream(System.getProperty("user.dir")+ "\\src\\test\\resources\\properties\\OR.properties");
	  	OR = new Properties();
	  	OR.load(fn);
	  	
	    log.debug("Config file loaded!!!");
	  	if (config.getProperty("browser").equals("chrome")){
	  		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");
	  		driver = new ChromeDriver();
	  		log.debug("Chrome Launched!!!");
	  	}else if (config.getProperty("browser").equals("IE")){
	  		System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\IEDriverServer.exe");
	  		driver = new InternetExplorerDriver();
	  	}else if (config.get("browser").equals("firefox")){
	  		System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\geckodriver.exe");
	  		driver = new FirefoxDriver();		
	  	}
	  }
	  driver.get(config.getProperty("testsiteurl"));
	  driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("wait")),TimeUnit.SECONDS);
	  log.debug("Navigated to: " + config.getProperty("testsiteurl"));
  }

  @AfterSuite
  public void afterMethod() {
	  if (driver!=null){
	     driver.quit();
	  }
	  log.debug("Testexecution completed!!!");
  }

  
  public Boolean isElementPresent(By by){
	  try{
		  driver.findElement(by);
		  return true;
	  }catch(NoSuchElementException e){
		  return false;
	  }
  }
}
