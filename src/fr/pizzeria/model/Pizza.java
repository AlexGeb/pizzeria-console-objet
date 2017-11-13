package fr.pizzeria.model;

/**
 * @author ETY0002
 * Class Pizza
 */
public class Pizza {
	
	public static int numOfPizzas = 0; // variable pour garder en mémoire le nombre de pizza
	public int id;
	public String code;
	public String name;
	public double price;

	/**
	 * @param code String
	 * @param name String
	 * @param price double
	 */
	public Pizza(String code, String name, double price) {
		this.id = numOfPizzas;
		numOfPizzas++;
		this.code = code;
		this.name = name;
		this.price = price;
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
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	/*
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return code + " -> " + name + "(" + price + ")";
	}

}