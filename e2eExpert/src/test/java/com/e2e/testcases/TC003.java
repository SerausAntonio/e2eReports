package com.e2e.testcases;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.e2e.base.Testbase;

public class TC003 extends Testbase {

  @Test (dataProvider="getData")
  public void f(String email, String password) {
	  
	  System.out.println(this.getClass().getName());
      driver.findElement(By.cssSelector(OR.getProperty("email"))).sendKeys(email); 
      driver.findElement(By.cssSelector(OR.getProperty("password"))).sendKeys(password);
      //  driver.findElement(By.cssSelector(OR.getProperty("aanmelden"))).click();
  
  }
  
  @DataProvider
  public Object[][] getData(){
	  String sheetName = "TC003";
	  int rows = excel.getRowCount(sheetName);
	  int cols = excel.getColumnCount(sheetName);
	  
	  Object[][] data = new Object[rows-1][cols];
	  
	  for (int rowNum=2; rowNum<= rows; rowNum++){
		  for (int colNum=0; colNum < cols++; colNum++){
			  data[rowNum -2][colNum] = excel.getCellData(sheetName, colNum, rowNum);
		  }
	  }
	  return data;
  }
  
}
