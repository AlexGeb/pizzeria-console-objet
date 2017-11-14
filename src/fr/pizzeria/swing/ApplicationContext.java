package fr.pizzeria.swing;

import java.util.HashMap;
import java.util.Map;

public class ApplicationContext {

	private Map<String, Object> attributes = new HashMap<>();
	
	public void setAttribute(String key, Object attribute){
		attributes.put(key, attribute);
	}
	
	public Object getAttribute(String key){
		return attributes.get(key);
	}
	
	public Object removeAttribute(String key){
		return attributes.remove(key);
	}
}
