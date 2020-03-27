package muvilog;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Test
public class muviLogin 
{
	public String baseUrl = "https://www.muvi.com/";
    String driverPath = "D:\\chromedriver_win32\\chromedriver_win32\\chromedriver.exe";
    public WebDriver driver; 
    public String expected = null;
    public String actual = null;
        @BeforeTest
      public void launchBrowser() 
        {
          System.out.println("launching Chrome browser"); 
          System.setProperty("webdriver.chrome.driver", driverPath);
          driver= new ChromeDriver();
          driver.get(baseUrl);
      }
        
        @Parameters({"email","password"})
        @Test(priority = 0)
        public void login(String email, String password) throws InterruptedException 
        {
        	driver.manage().window().maximize();
        	driver.findElement(By.id("load_login")).click() ;
        	Thread.sleep(5000);
            driver.findElement(By.xpath("//*[@id=\"LoginForm_email\"]")).sendKeys(email);
            driver.findElement(By.xpath("//*[@id=\"LoginForm_password\"]")).sendKeys(password);
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"btn-login\"]")).click();
            Thread.sleep(500);
            System.out.println("Login Completed");
            Reporter.log("Login Completed");
        }
        @Parameters({"content"})
        @Test(priority = 1)
        public void Content_Library(String content) throws InterruptedException
        {
        	Thread.sleep(5000);
           driver.findElement(By.xpath("//*[@id=\"owl-Walkover\"]/div/div/div[2]/div/div[3]/div/div/a")).click();
           driver.findElement(By.xpath("//*[@id=\"body_alert\"]/div[5]/div[3]/div[3]/div/a[1]/button")).click();
           driver.findElement(By.id("mname")).sendKeys(content);
           Select drop =new Select(driver.findElement(By.id("content_category_value")));
           drop.selectByVisibleText("Movie");
           Thread.sleep(500);
           driver.findElement(By.id("save-btn")).click();
           Thread.sleep(500);
           System.out.println("Content Submitted Sucessfylly");
           Reporter.log("Content Submitted Sucessfylly");
        }

        @Parameters({"contentt"})
       @Test(priority = 2)
       public void Zontent_Verify( String contentt) throws InterruptedException
        {
    	   Thread.sleep(5000);
    	   System.out.println("3rd test");
          driver.findElement(By.xpath("//*[@id=\"body_alert\"]/div[5]/div[3]/div[1]/div/div[1]/ol/li[3]/a")).click();
        String tp=	driver.findElement(By.className("caption")).getText();
       if(contentt.equals(tp))
        {
        	System.out.println("Library Sucessfully Verified");
       }
        	
       }
        @AfterTest
        public void terminateBrowser(){
            driver.close();
        }
}
