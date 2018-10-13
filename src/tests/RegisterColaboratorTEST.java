package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.chrome.ChromeDriver;

import core.Page;
import navigation.IndexPage;
import navigation.LoginPage;
import navigation.RegisterColaboratorPage;


class RegisterColaboratorTEST {
	
	private LoginPage page;
	private String driverPath = "libs/chromeDriver-2.42/chromedriver";
	private StringBuffer verificationErrors = new StringBuffer();
	
	@Parameters
	public static Stream<Arguments> data(){
		return Stream.of(
			Arguments.of("043.020.690-90","neto","84998980908","teste","ajsgsagjh@gmail.com","neto2",RegisterColaboratorPage.class,null)
		);
	}
	
	@BeforeEach
	public void setUp() throws Exception {
		System.out.println("Iniciando o browser Chrome");
		System.setProperty("webdriver.chrome.driver", driverPath);
		//page = new LoginPage(new ChromeDriver(), "https://testessigsaude.imd.ufrn.br/sigsaude/login");
		page = new LoginPage(new ChromeDriver(), "https://homologacaosigsaude.imd.ufrn.br/sigsaude/login");
	    page.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@ParameterizedTest
	@MethodSource("data")
	public void registerColaborator(String cpf, String nome, String telefone, String tipoColaborador, String email,String login, Class<Page> pageResult ,Class<Exception> exception) throws Exception {
	    
		try{
		
		    Page ipage = page.executeAuthentication("admin", "admin");

		    IndexPage indexpage = (IndexPage)ipage;
		    
		    Page rpage = indexpage.gotoRegisterNewColaborator();
		    
		    RegisterColaboratorPage regColPage = (RegisterColaboratorPage)rpage;
		    
		    Page lcol = regColPage.registerNewColaborator(nome,cpf,tipoColaborador,email, telefone,login);
		    
		    assertEquals(pageResult,lcol.getClass());
		    
		}catch(Exception e) {
			assertEquals(e.getClass(),exception);
		}   
	}
	
	@AfterEach
	public void tearDown() throws Exception {
		
		//page.close();
	    String verificationErrorString = verificationErrors.toString();
	    
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	}
}
