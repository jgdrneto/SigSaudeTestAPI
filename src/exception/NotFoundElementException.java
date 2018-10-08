package exception;

import core.Element.SEARCH_TYPE;

/** Class to handle exceptional behavior of missing an element on a page 
 * 
 * @author SOS
 *
 */
public class NotFoundElementException extends Exception {

	/** Serial value
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private SEARCH_TYPE type;
	
	/** Create a new NotFoundElementException object
	 * 
	 * @param nName Missing element name
	 * @param nType Search criteria
	 */
	public NotFoundElementException(String nName,SEARCH_TYPE nType) {
		super();
		this.name = nName;
		this.type = nType;
	}
	
	/** Create a new NotFoundElementException object
	 * 
	 * @param arg0 Exception message
	 * @param nName Missing element name
	 * @param nType Search criteria
	 */
	public NotFoundElementException(String arg0,String nName,SEARCH_TYPE nType) {
		super(arg0);
		this.name = nName;
		this.type = nType;
	}
	
	/** Gets the missing element name
	 * @return A string representing the name of element
	 */
	public String getName() {
		return name;
	}
	
	/** Gets the search criteria used
	 * @return A enum representing the search criteria used
	 */
	public SEARCH_TYPE getType() {
		return type;
	}

}
