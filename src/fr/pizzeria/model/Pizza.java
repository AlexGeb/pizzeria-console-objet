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

	/**
	 * @param code String
	 * @param name String
	 * @param price double
	 */
	public Pizza(String code, String name, double price) {
		numOfPizzas++;
		this.setCode(code);
		this.name = name;
		this.price = price;
	}
	
	public static Pizza delete() {
		numOfPizzas--;
		return null;
	}

	/*
	 * @see java.lang.Object#equals(java.lang.Object)
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

	public static int getNumOfPizzas() {
		return numOfPizzas;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}