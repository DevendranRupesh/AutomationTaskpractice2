package org.Automation;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.Testdata.TestData;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Automation_Task2 extends TestData {
	
	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(TestData.url1);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		JavascriptExecutor js= (JavascriptExecutor) driver;
		TakesScreenshot ts= (TakesScreenshot)driver;
		
		//Test Case 1: Home Page
		//	Get url and check that url contains index.html
	    String url1 = driver.getCurrentUrl();
		System.out.println("current url:"+url1);
		System.out.println("contains index.html:"+driver.getTitle()); 
		
		//	Verify that “Sign In” and “Skip Sign In” buttons are clickable
	    boolean enabled = driver.findElement(By.xpath("//button[@id='btn1']")).isEnabled();
	    System.out.println( "Signin verified:"+enabled);
        boolean enabled2 = driver.findElement(By.xpath("//button[@id='btn2']")).isEnabled();
	    System.out.println("Skipsignin verified:"+enabled2);
	    
	    //Enter email id
	  	driver.findElement(By.xpath("//input[@id='email']")).sendKeys(TestData.Mail1);
	  		
	    //Click on the next button
	  	driver.findElement(By.xpath("//img[@src='enter.png']")).click();
	    String currentUrl = url1;
	  	System.out.println("currenturl:"+currentUrl);
	  	System.out.println(currentUrl.contains("Index.html"));
         if(currentUrl=="index.html") {
		System.out.println("Get url :"+currentUrl);
		}else {
		System.out.println("url is not matching");
		}
        File source = ts.getScreenshotAs(OutputType.FILE);
 		File Destination=new File("D:\\Java Selenium practice\\Selenium Intergration\\Automation_Task_Practice2\\snapshot\\Homepage.png");
 		FileUtils.copyFile(source, Destination);
 		
 		//Test Case 2: Register Page
	    //	Enter Firstname, Lastname, Address, Email Address, Phone number.
         driver.findElement(By.xpath("//input[@type='text'][1]")).sendKeys(TestData.FName1);
         driver.findElement(By.xpath("//input[@ng-model='LastName']")).sendKeys(TestData.LName1);
         driver.findElement(By.xpath("//textarea[@rows='3']")).sendKeys(TestData.Add);
	     driver.findElement(By.xpath("//input[@type='email']")).sendKeys(TestData.Mail2);
         driver.findElement(By.xpath("//input[@type='tel']")).sendKeys(TestData.mobno);
	    
	    //	Verify that female radio button is selected, if not select that (logic needs to be applied here)
         WebElement radiobtn = driver.findElement(By.xpath("//input[@value='FeMale']"));
         if(!radiobtn.isSelected()) {
 			radiobtn.click();
 		  System.out.println("radiobtn:"+radiobtn.isEnabled());
 			}
         
         //Get all hobbies and click any two of the hobbies
         driver.findElement(By.id("checkbox1")).click();
         driver.findElement(By.id("checkbox2")).click();
         
         WebElement GetAll = driver.findElement(By.xpath("//label[text()='Hobbies']"));
         String text = GetAll.getText();
         System.out.println("getall:"+text);
         
         /*List<WebElement> getall = driver.findElements(By.cssSelector("div input[type='checkbox']"));
         for(WebElement hobbies: getall) {
          hobbies.click();
          System.out.println(hobbies.getText());
        	  }*/
         
         //Check Languages multi-dropdown, if yes select any two languages.
         Thread.sleep(3000);
         WebElement multi_select = driver.findElement(By.xpath("//div/multi-select/div[@id='msdd']"));
         if(multi_select.isEnabled()) {
        	  multi_select.click();
        	  }
         WebElement lang1 = driver.findElement(By.linkText(TestData.la12));
         
         js.executeScript("arguments[0].click();", lang1);
         
         WebElement lang2 = driver.findElement(By.linkText(TestData.lan13));
        
         js.executeScript("arguments[0].click();", lang1);
         
         //Select Skills from dropdown
         Thread.sleep(2000);
         WebElement skill = driver.findElement(By.xpath("//div/ select[@id='Skills']"));
         Select se= new Select(skill);
         se.selectByIndex(3);
         se.selectByVisibleText(TestData.Skill1);
         
         //Enter the country in checkbox and select the country
         Thread.sleep(3000);
         WebElement country = driver.findElement(By.id(TestData.country1));
         js.executeScript("arguments[0].click();", country);
         Select selectcountry= new Select(country);
         selectcountry.selectByVisibleText(TestData.county2);
         Thread.sleep(3000);
       
        boolean enabled3 = country.isEnabled();
        System.out.println("country field is enabled:"+enabled3);
        System.out.println("Text present inside countryfield:"+country.getText());
        
       //using javascriptexecutor to scroll page to down
       js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        
       WebElement scountry= driver.findElement(By.xpath("//select[@id='country']"));
       js.executeScript("arguments[0].click();", scountry); 
        Select select2= new Select(scountry);
        select2.selectByVisibleText("Australia");
        
        //	Select DOB from the drop-down
        WebElement year = driver.findElement(By.cssSelector("#yearbox"));
        Select select3= new Select(year);
        select3.selectByIndex(5);
        
        WebElement month = driver.findElement(By.xpath("//select[@placeholder='Month']"));
        Select select4= new Select(month);
        select4.selectByIndex(5);
        
        Thread.sleep(5000);
        
        WebElement day1 = driver.findElement(By.cssSelector("#daybox"));
      Select select5= new Select(day1);
        select5.selectByValue("29");
        
        //	Enter the password
        driver.findElement(By.cssSelector("div input[id='firstpassword']")).sendKeys("rajesh123");
       
       //	Enter the confirm password
        driver.findElement(By.cssSelector("div input[id='secondpassword']")).sendKeys("rajesh123");
         
        //using javascriptexecutor to scroll page to down
          js.executeScript("window.scrollTo(0, 0);");
        
       //	Upload dummy photo
        Thread.sleep(3000);
        WebElement photo = driver.findElement(By.xpath("//input[@id='imagesrc']"));
        photo.sendKeys("C:\\Users\\HP\\OneDrive\\Pictures\\Saved Pictures\\20230927.jpg");
        System.out.println("upload image:"+photo);
        
        //	Click on submit button
        Thread.sleep(3000);
        WebElement submitbtn = driver.findElement(By.cssSelector("div button[id='submitbtn']"));
        js.executeScript("arguments[0].click();", submitbtn);
        
        File source2 = ts.getScreenshotAs(OutputType.FILE);
 		File Destination2=new File("D:\\Java Selenium practice\\Selenium Intergration\\Automation_Task_Practice2\\snapshot\\Registerpage.png");
 		FileUtils.copyFile(source2, Destination2);
 		
        
       //Test Case 3: Switch To page for Alert
       //Hover the switchTo Tab and Select Alert
        
       WebElement switchelement = driver.findElement(By.xpath("//a[text()='SwitchTo']"));
       Actions actions= new Actions(driver);
       actions.moveToElement(switchelement).build().perform();
       
       Thread.sleep(3000);
       
       WebElement alert1 = driver.findElement(By.xpath("//a[text()='Alerts']"));
       js.executeScript("arguments[0].click();", alert1);
       
       String alerturl= driver.getCurrentUrl();
       
       System.out.println("Alert url:"+alerturl);
       if(alerturl=="Alerts.html") {
    	   
    	   System.out.println("current url is matches");
     }else {
    	   System.out.println("current url not matching");
       }
        
       // Click the red button and ok the alert
		
      		WebElement alertok = driver.findElement(By.xpath("//button[@class='btn btn-danger']"));
      		alertok.click();
      		
      		Thread.sleep(5000);
      		
      		Alert alert=  driver.switchTo().alert();
      		alert.accept();
      		
      		String text1 = alertok.getText();
      		System.out.println(text1);
      		
      	//	Click Alert with Ok & cancel and click the button to display alert, cancel the alert
      		
      		WebElement Alert1 = driver.findElement(By.xpath("//a[@href='#CancelTab']"));
      		
      		Alert1.click();
      		
      		Thread.sleep(3000);
      		
      		WebElement Alert2 = driver.findElement(By.xpath("//button[text()='click the button to display a confirm box ']"));
      		
      		Alert2.click();
      		
      		driver.switchTo().alert().accept();
      		
      		//driver.switchTo().alert().dismiss();
      		
      	    //Click Alert with Textbox and click the button to display alert, Enter your name and ok the alert
      		
      		Thread.sleep(3000);
      		
      		WebElement promptalert = driver.findElement(By.xpath("//a[@data-toggle='tab'][text()='Alert with Textbox ']"));
      		
      		promptalert.click();
      		
      		Thread.sleep(3000);
      		
      		WebElement Alert3 = driver.findElement(By.xpath("//button[@class='btn btn-info']"));
      		Alert3.click();
      		
      		Thread.sleep(3000);
      		
      		driver.switchTo().alert();
      		
      		alert.sendKeys("hai i am rajesh");
      		
      		alert.accept();
      		
      		File source3 = ts.getScreenshotAs(OutputType.FILE);
     		File Destination3=new File("D:\\Java Selenium practice\\Selenium Intergration\\Automation_Task_Practice2\\snapshot\\Alertpage.png");
     		FileUtils.copyFile(source3, Destination3);
     		
     		//Test Case 3: Switch To page for Windows
     	    
     		//driver.navigate().back();
     		
     		// Hover the switchTo Tab and Select windows
     		
     	  WebElement switchelem1= driver.findElement(By.xpath("//a[text()='SwitchTo']"));
          Actions ac1= new Actions(driver);
          actions.moveToElement(switchelem1).build().perform();
            
          Thread.sleep(3000);
          
          WebElement window = driver.findElement(By.cssSelector("li a[href='Index.html']"));
          js.executeScript("arguments[0].click();", window);
          
          //Get url and check that url contains Alerts.html
          
          String url2=driver.getCurrentUrl();
          
          System.out.println("Get Url:"+url2);
          
          if(url2=="Alerts.html") {
       	   
       	   System.out.println("current url is matches");
        }else {
       	   System.out.println("current url not matching");
          }
     	
     		
     		
     		
     		
        
         
 		
        	 
        	 
         }
	}


