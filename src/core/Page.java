package core;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;

/** Represents an Page
 * @author SOS
 * @version 1.0
 * @since 1.0
*/
public class Page {
	
	/** Driver Required of connection
	*/
	protected WebDriver driver;
	
	/** Represents a map of elements of page
	*/
	protected Map<String,Element> elements;
	
	/** Represents a page
	 * @param nDriver Driver needed to connect to the page  
	*/
	public Page(WebDriver nDriver) {
		this.driver = nDriver;		
		this.elements = new HashMap<String,Element>();
	}
	
}

