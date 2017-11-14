package fr.pizzeria.model;

/**
 * @author ETY0002
 * Class Pizza
 */
public class Pizza {
	
	private static int numOfPizzas = 0; // variable pour garder en mémoire le nombre de pizza
	private String code;
	private String name;
	private double price;

	/** Instantiate a new Pizza
	 * @param code unique String object to identify the pizza
	 * @param name String => nom de la pizza
	 * @param price Double => prix de la pizza
	 */
	public Pizza(String code, String name, double price) {
		numOfPizzas++;
		this.code  = code.toUpperCase(); // toUpperCase() pour normalizer les codes
		this.name = name;
		this.price = price;
	}
	
	/** delete() has to be called each time you want to delete a pizza
	 * it is used to decrement the total number of pizzas in the pizzeria
	 * @return null
	 */
	public static Pizza delete() {
		numOfPizzas--;
		return null;
	}

	/* equals
	 * @see java.lang.Object#equals(java.lang.Object)
	 * deux pizzas sont égales si elles ont le même code
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pizza other = (Pizza) obj;
		if (getCode() == null) {
			if (other.getCode() != null)
				return false;
		} else if (!getCode().equals(other.getCode()))
			return false;
		return true;
	}
	

	/*
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getCode() + " -> " + name + "(" + price + ")";
	}

	/**
	 * @return an int, which is the total number of pizzas in the pizzeria
	 */
	public static int getNumOfPizzas() {
		return numOfPizzas;
	}

	/**
	 * @return the code of the pizza (unique identifier)
	 */
	public String getCode() {
		return code;
	}
	
}