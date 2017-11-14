package fr.pizzeria.swing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Input {

	private Map<String, String> map;
	private String[] keys;
	
	public Input(String... codes){
		map = new HashMap<>();
		for (String code: codes){
			map.put(code, "");
		}
		keys = new String[map.size()];
		map.keySet().toArray(keys);
	}
	
	public String getValue(String code){
		return map.get(code);
	}
	
	public String getValue(int index){
		if (keys==null){
			throw new IndexOutOfBoundsException("Inputs is empty");
		}
		else if (index>keys.length-1){
			throw new IndexOutOfBoundsException("Maximum index is "+(keys.length-1));
		}
		return map.get(keys[index]);
	}
	
	public int size(){
		return map.size();
	}
	
	public List<String> getCodes(){
		return map.keySet().stream().collect(Collectors.toList());
	}

	public void putValue(String code, String text) {
		map.put(code, text);
		
	}
}
