package HourCheckPackage;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
public class webdriverTest {
	@Test
	public void testJenkins() throws InterruptedException, IOException
	{
		//some checnge4 and 5 now
		//System.setProperty("webdriver.ie.driver", "C:\\IEDriverServer\\IEDriverServer.exe");
		
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			
		 //WebDriver dr=new InternetExplorerDriver(); 
		 WebDriver dr=new ChromeDriver();
		// WebDriver dr=new FirefoxDriver();
			dr.get("http://intranet.pn.avalara.net/");	
			dr.manage().window().maximize();
			
			
			Thread.sleep(6000);
			String window1=dr.getWindowHandle();
			dr.findElement(By.linkText("Ascent Portal")).click();
			
			Thread.sleep(3000);
			
			Set <String> windowsName= dr.getWindowHandles();
			
			System.out.println(windowsName);
			String temp=null;
			Iterator iter = windowsName.iterator();
			while (iter.hasNext()) {
				 temp=iter.next().toString();
			    System.out.println(temp);
			    
			    if(temp.equals(window1)==false)
			    {
			    	dr.switchTo().window(temp);
			    }
			}
			Thread.sleep(3000);
			System.out.println(dr.getTitle());
			
			dr.findElement(By.id("Image1")).click();
			Thread.sleep(3000);
			windowsName= dr.getWindowHandles();
			System.out.println(windowsName);
			iter = windowsName.iterator();
			while (iter.hasNext()) {
				String temp1=iter.next().toString();
			    
			    	dr.switchTo().window(temp1);
			   
			}
			
			System.out.println(dr.getWindowHandle());
			
			dr.findElement(By.id("txtUserName")).clear();
			dr.findElement(By.id("txtUserName")).sendKeys("nitesh.jain");
			
			dr.findElement(By.id("txtPassword")).clear();
			dr.findElement(By.id("txtPassword")).sendKeys("*****");
			
			dr.findElement(By.id("btnLogin")).click();
			Thread.sleep(10000);
			
			System.out.println(dr.getCurrentUrl());
			System.out.println(dr.getWindowHandles());
			System.out.println(dr.getTitle());
			
			dr.switchTo().frame("ASPxSplitter1_0_CC");
			//WebDriverWait wait = new WebDriverWait(dr,30);
			
			//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='lblLastLogin']")));
		//	System.out.println(dr.findElement(By.xpath("//*[@id='lblLastLogin']")).getText());
			//dr.switchTo().frame("form1");
			dr.findElement(By.xpath("//*[@id='RadPanelMenuBar']/ul/li[3]/a/span/span[1]")).click();
			Thread.sleep(3000);
			dr.findElement(By.xpath("//*[@id='RadPanelMenuBar_i3_i0_RTV']/ul/li[2]/div/a")).click();
			Thread.sleep(3000);
			
			dr.switchTo().defaultContent();
			dr.switchTo().frame("ASPxSplitter1_1i1_CC");
			  File scrFile = ((TakesScreenshot)dr).getScreenshotAs(OutputType.FILE);
	           //The below method will save the screen shot in d drive with name "screenshot.png"
	              FileUtils.copyFile(scrFile, new File("D:\\capture\\screenshot.png"));
	              
	              
	           // Grab the table
	              WebElement table = dr.findElement(By.className("tdBorderAll"));

	              // Now get all the TR elements from the table
	              String cellVal="";
	              List<WebElement> allRows = table.findElements(By.tagName("tr"));
	              // And iterate over them, getting the cells
	              for (WebElement row : allRows) {
	                  List<WebElement> cells = row.findElements(By.tagName("td"));
	                  for (WebElement cell : cells) {
	                	  if(cell.getText().contains("January"))
	                	  {
	                    //  System.out.print("content >>   " + cell.getText());
	                      //System.out.println("trap1 trap1 trap1");
	                      cellVal=cell.getText();
	                      break;
	                	  }
	                	  
	                  }
	                  if(cellVal.length()>15)
	                  {
	                	  break;
	                  }
	                  //System.out.println();
	                  //System.out.println("trap***");
	                 
	              }
	              
	            //  System.out.println("may val:::"+cellVal);
	              
	              String cellVal2=cellVal.substring(cellVal.indexOf("Sat")+3);
	              //System.out.println("new val2::"+cellVal2);
	              //System.out.println(cellVal2.length());
	              
	             String cellVal3= cellVal2.trim().replaceAll("\n", "");
	             // replaceAll("(\s|\n)", "");
	             
	             System.out.println("val3:::\n"+cellVal3);
	             
	             String cellVal4= cellVal3.substring(cellVal3.lastIndexOf("Net Hrs")-15, cellVal3.lastIndexOf("Net Hrs")-2) +"\t"+cellVal3.substring(cellVal3.lastIndexOf("Net Hrs"), cellVal3.lastIndexOf("Net Hrs")+15);
	             System.out.println("final hours for  today:"+cellVal4);
	             
	             dr.quit();
	}

}
