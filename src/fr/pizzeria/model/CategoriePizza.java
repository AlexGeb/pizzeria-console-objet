package fr.pizzeria.model;

public enum CategoriePizza {
	VIANDE("Viande"), POISSON("Poisson"), SANS_VIANDE("Sans Viande");
	
	private String value;
	
	private CategoriePizza(String cat){
		this.value = cat;
	}

	public String getValue() {
		return value;
	}
	
	public static boolean contains(String test) {

	    for (CategoriePizza c : CategoriePizza.values()) {
	        if (c.toString().equals(test)) {
	            return true;
	        }
	    }

	    return false;
	}
}
