package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import navigation.IndexPage;
import navigation.LoginPage;

class LoginPageTEST {

	private WebDriver driver;
	private String driverPath = "libs/chromeDriver-2.42/chromedriver";
	private StringBuffer verificationErrors = new StringBuffer();

	@BeforeEach
	public void setUp() throws Exception {
		System.out.println("Iniciando o browser Chrome");
		System.setProperty("webdriver.chrome.driver", driverPath);
		this.driver = new ChromeDriver();
	    this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void testLogin() throws Exception {
	    
		driver.get("https://homologacaosigsaude.imd.ufrn.br/sigsaude/login");
		
		LoginPage loginPage = new LoginPage(driver);
	    
	    assertEquals(loginPage.executeAuthentication("admin", "admin").getClass(),IndexPage.class);
	}
	
	@AfterEach
	public void tearDown() throws Exception {
		driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	}
}
