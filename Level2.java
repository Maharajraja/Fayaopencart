package com.test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
public class Level2 {


	    public static void main(String[] args) {
	        
	        System.setProperty("webdriver.gecko.driver","D:\\Softwares\\Java & Selenium\\geckodriver-v0.33.0-win64\\geckodriver.exe");
	        WebDriver driver = new FirefoxDriver();


	        try {
	         
	            register(driver, "raja", "test", "test@mailinator.com", "password");
	            register(driver, "raja1", "test1", "test1@mailinator.com", "password1");
	            register(driver, "raja2", "test2", "test2@mailinator.com", "password2");
	            register(driver, "raja3", "test3", "test3@mailinator.com", "password3");        
	            login(driver, "test@mailinator.com", "password");
	            selectProduct(driver);
	            addToCart(driver);
	            checkout(driver);

	        } finally {
	            // Close the browser window
	            driver.quit();
	        }
	    }

	    private static void register(WebDriver driver, String firstname, String lastname, String email, String password) {
	        
	        driver.get("https://demo.opencart.com/index.php?route=account/register");

	        WebElement firstName = driver.findElement(By.name("firstname"));
	        firstName.sendKeys(getCharSeqFromString(firstname));

	        WebElement lastName = driver.findElement(By.name("lastname"));
	        lastName.sendKeys(getCharSeqFromString(lastname));

	        WebElement emailid = driver.findElement(By.name("email"));
	        emailid.sendKeys(getCharSeqFromString(email));

	        WebElement newpassword = driver.findElement(By.name("password"));
	        newpassword.sendKeys(getCharSeqFromString(password));
            
	        //scroll dwon
            scrollDown(driver,500);     

            //Wait for 2 seconds
	        try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	        WebElement privacyPolicyCheckbox = driver.findElement(By.name("agree"));           
            privacyPolicyCheckbox.click();
	        
	        WebElement continueButton = driver.findElement(By.cssSelector("button.btn.btn-primary"));
	        continueButton.click();
	    }
	    
	    private static void login(WebDriver driver, String email, String password) {
	       
	        driver.get("https://demo.opencart.com/index.php?route=account/login");

	        WebElement emailid = driver.findElement(By.name("email"));
	        emailid.sendKeys(getCharSeqFromString(email));     

	        WebElement newpassword = driver.findElement(By.name("password"));
	        newpassword.sendKeys(getCharSeqFromString(password));  
	        
	        WebElement continueButton = driver.findElement(By.cssSelector("button.btn.btn-primary"));
	        continueButton.click();
	    }

	    private static void selectProduct(WebDriver driver) {
	         driver.get("https://demo.opencart.com/index.php?route=product/product&product_id=43");
	    }

	    private static void addToCart(WebDriver driver) {
	        WebElement addToCartButton = driver.findElement(By.id("button-cart"));
	        addToCartButton.click();
	        try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	    }

	    private static void checkout(WebDriver driver) {
	        driver.get("https://demo.opencart.com/index.php?route=checkout/cart");
	        WebElement checkoutButton = driver.findElement(By.cssSelector("a[href*='/checkout']"));
	        checkoutButton.click();
	    }
	    
	    public static CharSequence[] getCharSeqFromString(String input) {
	    	CharSequence[] charSeq = new CharSequence[input.length()];
	    	for (int i = 0; i < input.length(); i++) {
	    		charSeq[i] = String.valueOf(input.toCharArray()[i]);
	    	}
	    	return charSeq;
	    }
	    private static void scrollDown(WebDriver driver, int pixels) {
	        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
	        String script = "window.scrollBy(0, " + pixels + ");";
	        jsExecutor.executeScript(script, new Object[1]);
	    }
	}
