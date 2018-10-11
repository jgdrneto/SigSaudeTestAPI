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
public class IndexPage extends Page{
	
	/** Represents a register page
	 * @param nDriver Driver needed to connect to the page  
	 */
	public IndexPage(WebDriver nDriver) {
		super(nDriver);
		
		try {
			this.elements.put("NOVO_COLABORADOR", new Element(this.driver,"novoColaborador",SEARCH_TYPE.BY_ID));
			this.elements.put("LISTAR_COLABORADOR", new Element(this.driver,"listarColaborador",SEARCH_TYPE.BY_ID));
			this.elements.put("TIPOS_COLABORADOR", new Element(this.driver,"tiposColaborador",SEARCH_TYPE.BY_ID));
			
		}catch(NotFoundElementException e) {
			System.err.println("Not Found element " + e.getName() + " using the type search " + e.getType().name());
			e.printStackTrace();
			System.exit(-1);
		}
		
	}
	
}

