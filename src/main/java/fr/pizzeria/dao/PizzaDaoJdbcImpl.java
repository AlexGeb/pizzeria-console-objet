package fr.pizzeria.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzaDaoJdbcImpl extends DaoJdbc implements IPizzaDao {
	private Connection conn;

	public PizzaDaoJdbcImpl() throws ClassNotFoundException, SQLException {
		_init();
		addPizzas();
	}

	private void _init() {

		try {
			Class.forName("org.postgresql.Driver");
			String host = "blsxb6cjfpu78rs-postgresql.services.clever-cloud.com:5432/blsxb6cjfpu78rs";
			String password = "q1xKatkfZuz6LdazL1zT";
			String user = "upq079x4usuy8be9s8yj";
			String URLConnection = "jdbc:postgresql://" + host;
			conn = DriverManager.getConnection(URLConnection, user, password);
		} catch (SQLException | ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public List<Pizza> findAllPizzas() {
		try {
			PreparedStatement prepStat = conn.prepareStatement("SELECT * FROM pizza");
			ResultSet result = prepStat.executeQuery();
			return getListFromResultSet(result);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return new ArrayList<Pizza>();
		}
	}

	private void addPizzas() {
		List<Pizza> pizzas = new ArrayList<Pizza>();
		try {
			Statement stat = conn.createStatement();
			ResultSet result = stat.executeQuery("SELECT count(*) FROM pizza");
			stat.close();
			Integer count = 0;
			while (result.next()) {
				count = result.getInt(1);
			}
			result.close();
			System.out.println("nombre de pizza : " + count);
			if (count < 8) {
				pizzas.add(new Pizza("PEP", "Pépéroni", 12.5, CategoriePizza.VIANDE));
				pizzas.add(new Pizza("MAR", "Margherita", 14, CategoriePizza.VIANDE));
				pizzas.add(new Pizza("REIN", "La Reine", 11.5, CategoriePizza.VIANDE));
				pizzas.add(new Pizza("FRO", "La 4 fromages", 12, CategoriePizza.VIANDE));
				pizzas.add(new Pizza("CAN", "La cannibale", 12.5, CategoriePizza.VIANDE));
				pizzas.add(new Pizza("SAV", "La savoyarde", 13, CategoriePizza.VIANDE));
				pizzas.add(new Pizza("ORI", "L'orientale", 13.5, CategoriePizza.VIANDE));
				pizzas.add(new Pizza("IND", "L'indienne", 14, CategoriePizza.VIANDE));
				conn.createStatement().executeUpdate("TRUNCATE pizza");
				PreparedStatement prepInsert = conn
						.prepareStatement("INSERT INTO pizza (code,name,price,category) VALUES (?,?,?,?)");
				for (Pizza p : pizzas) {
					prepInsert.setString(1, p.getCode());
					prepInsert.setString(2, p.getName());
					prepInsert.setDouble(3, p.getPrice());
					prepInsert.setString(4, p.getCategory().toString());
					prepInsert.addBatch();
				}
				prepInsert.executeBatch();
				prepInsert.close();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private List<Pizza> getListFromResultSet(ResultSet res) throws SQLException {
		List<Pizza> pizzas = new ArrayList<Pizza>();
		while (res.next()) {
			CategoriePizza cat = CategoriePizza.valueOf(res.getString("category"));
			Pizza pizz = new Pizza(res.getString("code"), res.getString("name"), res.getDouble("price"), cat);
			pizzas.add(pizz);
		}
		return pizzas;
	}

	@Override
	public boolean saveNewPizza(Pizza pizza) {
		try {
			PreparedStatement prepInsert = conn
					.prepareStatement("INSERT INTO pizza (code,name,price,category) VALUES (?,?,?,?)");
			prepInsert.setString(1, pizza.getCode());
			prepInsert.setString(2, pizza.getName());
			prepInsert.setDouble(3, pizza.getPrice());
			prepInsert.setString(4, pizza.getCategory().toString());
			Integer numInsert = prepInsert.executeUpdate();
			return numInsert.equals(1);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	@Override
	public boolean updatePizza(String codePizza, Pizza pizza) {
		try {
			PreparedStatement prepInsert = conn.prepareStatement("UPDATE pizza SET code=? ,name=?,price=?,category=? WHERE code=?");
			prepInsert.setString(1, pizza.getCode());
			prepInsert.setString(2, pizza.getName());
			prepInsert.setDouble(3, pizza.getPrice());
			prepInsert.setString(4, pizza.getCategory().toString());
			prepInsert.setString(5, codePizza.toUpperCase());
			Integer numupdated = prepInsert.executeUpdate();
			return numupdated.equals(1);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	@Override
	public boolean deletePizza(String codePizza) {
		try {
			PreparedStatement prepInsert = conn.prepareStatement("DELETE FROM pizza WHERE code=?");
			prepInsert.setString(1, codePizza);
			Integer numDeleted = prepInsert.executeUpdate();
			return numDeleted.equals(1);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

}
