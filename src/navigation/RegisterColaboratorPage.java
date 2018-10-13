package navigation;

import org.openqa.selenium.WebDriver;

import core.Element;
import core.Page;
import core.Element.SEARCH_TYPE;
import exception.NotFoundElementException;

/** Represents an Register Page
 * @author SOS
 * @version 1.0
 * @since 1.0
 */
public class RegisterColaboratorPage extends Page{
	/** Represents a register page
	 * @param nDriver Driver needed to connect to the page  
	 */
	public RegisterColaboratorPage(WebDriver nDriver) {
		super(nDriver);
		
		try {
			this.elements.put("NOME", new Element(this.driver,"inputNome",SEARCH_TYPE.BY_ID));
			this.elements.put("CPF", new Element(this.driver,"inputCPF",SEARCH_TYPE.BY_ID));
			this.elements.put("TIPO_COLABORADOR", new Element(this.driver,"selectTipo",SEARCH_TYPE.BY_ID));
			this.elements.put("E_MAIL", new Element(this.driver,"inputEmail",SEARCH_TYPE.BY_ID));
			this.elements.put("TELEFONE", new Element(this.driver,"inputTelefone",SEARCH_TYPE.BY_ID));
			this.elements.put("LOGIN", new Element(this.driver,"inputLogin",SEARCH_TYPE.BY_ID));
			this.elements.put("CANCELAR", new Element(this.driver,"btn-secondary",SEARCH_TYPE.BY_CLASS));
			this.elements.put("CADASTRAR", new Element(this.driver,"btn-success",SEARCH_TYPE.BY_CLASS));
			
		}catch(NotFoundElementException e) {
			System.err.println("Not Found element " + e.getName() + " using the type search " + e.getType().name());
			e.printStackTrace();
			System.exit(-1);
		}
		
	}
	
	//TODO gets
	
	public Page registerNewColaborator(String nome, String cpf, String tipoColaborador, String email, String telefone, String login) {
		
		this.elements.get("NOME").sendKeys(nome);
		this.elements.get("CPF").sendKeys(cpf);
		this.elements.get("TIPO_COLABORADOR").sendKeys(tipoColaborador);
		this.elements.get("E_MAIL").sendKeys(email);
		this.elements.get("TELEFONE").sendKeys(telefone);
		this.elements.get("LOGIN").sendKeys(login);
		
		this.elements.get("CADASTRAR").submit();
		
		if(this.getURL().contains("colaborador/form")){
			return this;
		}else {
			return new ListarColaboratorPage(this.driver);
		}
		
	}
	
	
}

