package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;

import navigation.IndexPage;
import navigation.LoginPage;

class LoginPageTEST {

	private LoginPage page;
	private String driverPath = "libs/chromeDriver-2.42/chromedriver";
	private StringBuffer verificationErrors = new StringBuffer();

	@BeforeEach
	public void setUp() throws Exception {
		System.out.println("Iniciando o browser Chrome");
		System.setProperty("webdriver.chrome.driver", driverPath);
		page = new LoginPage(new ChromeDriver(), "https://homologacaosigsaude.imd.ufrn.br/sigsaude/login");
	    page.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void testLogin() throws Exception {
	    
	    assertEquals(page.executeAuthentication("admin", "admin").getClass(),IndexPage.class);
	}
	
	@AfterEach
	public void tearDown() throws Exception {
		page.close();
	    String verificationErrorString = verificationErrors.toString();
	    
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	}
}
