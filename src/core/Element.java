package core;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import exception.NotFoundElementException;

public class Element {
	
	/** types of element searches
	 */
	public enum SEARCH_TYPE{
		BY_ID,
		BY_CLASS,
		BY_CSS_SELECTOR,
		BY_NAME,
		BY_LINK_TEXT,
		BY_PARTIAL_LINK_TEXT,
		BY_TAG_NAME,
		BY_XPATH
	};
	
	private WebElement element;
	
	/** Create a new element from the web driver element
	 * 
	 * @param nElement WebDriver element
	 */
	private Element(WebElement nElement) {
		this.element = nElement;
	}
	
	/** Create a new element from selection criteria
	 * 
	 * @param driver Driver needed to connect to the page
	 * @param name String of search
	 * @param type Selection criteria
	 */
	public Element(WebDriver driver,String name, SEARCH_TYPE type) throws NotFoundElementException{
		
		this.element = this.createElement(driver, name, type, 0);
		
		if(this.element==null) {
			throw new NotFoundElementException("Not Found Element on Page",name,type); 
		}
		
	}
	
	/** Create a new element from selection criteria among several elements
	 * 
	 * @param driver Driver needed to connect to the page
	 * @param name String of search
	 * @param type Selection criteria
	 * @param value Index of the item to be chosen
	 */
	public Element(WebDriver driver,String name, SEARCH_TYPE type,int index) throws NotFoundElementException{
		WebElement e = this.createElement(driver, name, type, index);
		
		if(e==null) {
			throw new NotFoundElementException("Not Found Element on Page",name,type); 
		}
	}
	
	/** auxiliary function to create a new element from selection criteria among several elements
	 * 
	 * @param driver Driver needed to connect to the page
	 * @param name String of search
	 * @param type Selection criteria
	 * @param value Index of the item to be chosen
	 */
	private WebElement auxCreateElement(List<WebElement> lwe, int index){
		if(index<0 || index>lwe.size()-1) {
			return null;
		}else {
			return lwe.get(index);
		}
	}
	
	/** Auxiliary function to create a new element from selection criteria among several elements
	 * 
	 * @param driver Driver needed to connect to the page
	 * @param name String of search
	 * @param type Selection criteria
	 * @param value Index of the item to be chosen
	 */
	private WebElement createElement(WebDriver driver,String name, SEARCH_TYPE type,int index){
		
		List<WebElement> lwe = null;
		
		switch(type) {
			case BY_ID :
				lwe = driver.findElements(By.id(name));
			break;
			case BY_CLASS :	
				lwe = driver.findElements(By.className(name));	
			break;
			case BY_CSS_SELECTOR :
				lwe = driver.findElements(By.cssSelector(name));
			break;
			case BY_NAME :
				lwe = driver.findElements(By.name(name));
			break;
			case BY_LINK_TEXT :
				lwe = driver.findElements(By.linkText(name));
			break;
			case BY_PARTIAL_LINK_TEXT :
				lwe = driver.findElements(By.partialLinkText(name));
			break;
			case BY_TAG_NAME :
				lwe = driver.findElements(By.tagName(name));
			break;
			case BY_XPATH :
				lwe = driver.findElements(By.xpath(name));
			break;
		}
		
		return auxCreateElement(lwe, index); 
	}
	
	/** Gets the Text of element
	 * 
	 * @return A string representing the text of element
	 */
	public String getText() {
		return this.element.getText();
	}
	
	/** Action of click of element
	 */
	public void click() {
		this.element.click();
	}
	
	/** Action of submit of element
	 */
	public void submit() {
		this.element.submit();
	}
	
	/** Get attribute of element
	 * 
	 * @param name Name of attribute of element
	 * 
	 * @return Value of attribute of element
	 */
	public String getAttribute(String name) {
		return this.element.getAttribute(name);
	}
	
	/** Get parent of element
	 *
	 * @return If exist, it return the parent of element, else return null
	 */
	public Element getParent() {
		
		List<WebElement> parent = this.element.findElements(By.xpath(".."));
		
		if(parent.isEmpty()) {
			return null;
		}else {
			return new Element(parent.get(0));
		}
	}
	
	/** Return of all distincts childrens of element
	 *
	 * @return List of all elements that are the children of the element
	 */
	public List<Element> getAllChildrens() {
		
		List<Element> childrens = new ArrayList<Element>();
		
		for(WebElement e : this.element.findElements(By.xpath(".//*"))) {
			childrens.add(new Element(e));
		}
		
		return childrens;
	}
	
	/** List of all specific childrens elements
	 * 
	 * @param name Name of string of search
	 * @param type Search criteria of childrens elements
	 * 
	 * @return List of all elements that are the children of the element attending the retrinctions 
	 */
	public List<Element> getEspecificChildrens(String name,SEARCH_TYPE type) {
		
		List<Element> childrens = new ArrayList<Element>();
		
		switch(type) {
			case BY_ID :
				for(WebElement e : this.element.findElements(By.id(name))) {
					childrens.add(new Element(e));
				}
			break;
			case BY_CLASS :	
				for(WebElement e : this.element.findElements(By.className(name))) {
					childrens.add(new Element(e));
				}
			break;
			case BY_CSS_SELECTOR :
				for(WebElement e : this.element.findElements(By.cssSelector(name))) {
					childrens.add(new Element(e));
				}
			break;
			case BY_NAME :
				for(WebElement e : this.element.findElements(By.name(name))) {
					childrens.add(new Element(e));
				}
			break;
			case BY_LINK_TEXT :
				for(WebElement e : this.element.findElements(By.linkText(name))) {
					childrens.add(new Element(e));
				}
			break;
			case BY_PARTIAL_LINK_TEXT :
				for(WebElement e : this.element.findElements(By.partialLinkText(name))) {
					childrens.add(new Element(e));
				}
			break;
			case BY_TAG_NAME :
				for(WebElement e : this.element.findElements(By.tagName(name))) {
					childrens.add(new Element(e));
				}
			break;
			case BY_XPATH :
				for(WebElement e : this.element.findElements(By.xpath(name))) {
					childrens.add(new Element(e));
				}
			break;
		}
		
		return childrens;
	}
	
	/** Send string to element
	 * 
	 * @param value Value to be sent to element
	 */
	public void sendKeys(String value) {
		this.element.sendKeys(value);
	}
	
	/** Get value of element
	 * 
	 * @return A string with the value of the element
	 */
	public String getValue() {
		return this.element.getAttribute("value");
	}
	
	/** Get tagName of element
	 * 
	 * @return A string with the tagName of the element
	 */
	public String getTagName() {
		return this.element.getTagName();
	}
	
	/** Get width of element
	 * 
	 * @return A integer with the width of the element
	*/
	public int getWidth() {
		return this.element.getSize().getWidth();
	}
	
	/** Get height of element
	 * 
	 * @return A integer with the height of the element
	*/
	public int getHeight() {
		return this.element.getSize().getHeight();
	}
	
	/** Verifies that the element has been selected
	 * 
	 * @return True if selected and false otherwise
	*/
	public boolean isSelect() {
		return this.element.isSelected();
	}
	
	/** Verifies that the element has been enabled
	 * 
	 * @return True if enabled and false otherwise
	*/
	public boolean isEnabled() {
		return this.element.isEnabled();
	}
	
	/** Verifies that the element has been visible
	 * 
	 * @return True if visible and false otherwise
	*/
	public boolean isVisible() {
		return this.element.isDisplayed();
	}
	
	/** Informs the position of the x coordinate of the element
	 * 
	 * @return A integer with the position of the x coordinate
	*/
	public int getX() {
		return this.element.getLocation().getX();
	}
	
	/** Informs the position of the y coordinate of the element
	 * 
	 * @return A integer with the position of the y coordinate
	*/
	public int getY() {
		return this.element.getLocation().getY();
	}
	
	/** Moves the element to a new coordinate x and y
	 * 
	 * @param nX New x coordinate
	 * @param nY New y coordinate 
	*/
	public void move(int nX, int nY) {
		this.element.getLocation().move(nX, nY);
	}

}
