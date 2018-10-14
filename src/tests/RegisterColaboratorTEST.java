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
import navigation.ListarColaboratorPage;
import navigation.LoginPage;
import navigation.RegisterColaboratorPage;


class RegisterColaboratorTEST {
	
	private LoginPage page;
	private String driverPath = "libs/chromeDriver-2.42/chromedriver";
	private StringBuffer verificationErrors = new StringBuffer();
	
	@Parameters
	public static Stream<Arguments> data(){
		return Stream.of(
			// Testar cadastro de colaborador ainda não cadastrado com todos os dados básicos.
			Arguments.of("38639444021",	"Neto",	"as@gmail.com", "teste", "8431334454", "netodoforro1",ListarColaboratorPage.class,null),
			Arguments.of("74250286894", "Neto", "fs@gmail.com", "teste", "8431334454", "netodoforro2",ListarColaboratorPage.class,null),
			
			// Testar cadastro de colaborador com campos obrigatórios vazios (Nome, CPF, e-mail, login, senha)
			Arguments.of("", "Ramon", "bs@gmail.com", "teste", "84999873313", "ramon123",RegisterColaboratorPage.class,null),
			Arguments.of("32108584897", "", "cs@gmail.com", "teste", "8434568796", "misterio_345",RegisterColaboratorPage.class,null),
			Arguments.of("48506146127", "Yuri", "", "teste", "84999968796", "yuretreloso",RegisterColaboratorPage.class,null),
			Arguments.of("1226918140", "Raíssa", "es@gmail.com", "teste", "84988868796", "",RegisterColaboratorPage.class,null),
			
			// Testar cadastro de colaborador com campos obrigatórios inválidos.
			Arguments.of("11111111111", "Luana", "ds@gmail.com", "teste", "8433333333", "Luadecristal",RegisterColaboratorPage.class,null),
			Arguments.of("86461891200", "Daniel", "hsgmail.com", "teste", "84991234567", "123Sol",RegisterColaboratorPage.class,null),
			Arguments.of("54437989287", "Raikar", "is@gmail.com", "teste", "", "ra ra ra@#$%", RegisterColaboratorPage.class,null),
			
			// Testar cadastro de colaborador com campos únicos já cadastrados (CPF, e-mail, login).
			Arguments.of("74250286894", "Laila", "js@gmail.com", "teste", "84932334567", "Lailinhah",RegisterColaboratorPage.class,null),
			Arguments.of("81621711692", "Evandro", "as@gmail.com", "teste", "99995555", "Evandro12",RegisterColaboratorPage.class,null),
			Arguments.of("59354550720", "Natasha", "ks@gmail.com", "teste", "", "netodoforro2",RegisterColaboratorPage.class,null)
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
		
		page.close();
	    String verificationErrorString = verificationErrors.toString();
	    
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	}
}
