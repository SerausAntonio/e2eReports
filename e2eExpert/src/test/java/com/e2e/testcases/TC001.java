package com.e2e.testcases;

import java.io.File;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.e2e.base.Testbase;

public class TC001 extends Testbase {

	
  @Test
  public void f() throws Exception {
	  System.out.println(this.getClass().getName());
	  driver.findElement(By.cssSelector(OR.getProperty("email"))).sendKeys("email");;
      driver.findElement(By.cssSelector(OR.getProperty("password"))).sendKeys("pass");
    //  driver.findElement(By.cssSelector(OR.getProperty("aanmelden"))).click();
      log.debug("Login succesfully executed");
      Thread.sleep(2000);
  }
   
}
