package fr.pizzeria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzaDaoJdbcImpl extends DaoJdbc implements IPizzaDao {

	public PizzaDaoJdbcImpl() {
		super();
		addPizzas();
	}

	@Override
	public List<Pizza> findAllPizzas() {
		Connection conn = getConnection();
		try {
			PreparedStatement prepStat = conn.prepareStatement("SELECT * FROM pizza");
			ResultSet result = prepStat.executeQuery();
			return getListFromResultSet(result);
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
			return new ArrayList<Pizza>();
		}
	}

	private void addPizzas() {
		List<Pizza> pizzas = new ArrayList<Pizza>();
		Connection conn = getConnection();
		try {
			Statement stat = conn.createStatement();
			ResultSet result = stat.executeQuery("SELECT count(*) FROM pizza");
			Integer count = 0;
			while (result.next()) {
				count = result.getInt(1);
			}
			result.close();
			stat.close();
			LOGGER.debug("nombre de pizza : " + count);
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
			manageSqlException(e);
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
		Connection conn = null;
		PreparedStatement prepInsert = null;
		try {
			conn = getConnection();
			prepInsert = conn.prepareStatement("INSERT INTO pizza (code,name,price,category) VALUES (?,?,?,?)");
			prepInsert.setString(1, pizza.getCode());
			prepInsert.setString(2, pizza.getName());
			prepInsert.setDouble(3, pizza.getPrice());
			prepInsert.setString(4, pizza.getCategory().toString());
			Integer numInsert = prepInsert.executeUpdate();
			return numInsert.equals(1);
		} catch (SQLException e) {
			manageSqlException(e);
			return false;
		} finally {
			closeSqlResources(conn, prepInsert, null);
		}
	}

	@Override
	public boolean updatePizza(String codePizza, Pizza pizza) {
		Connection conn = null;
		PreparedStatement prepInsert = null;
		try {
			conn = getConnection();
			prepInsert = conn.prepareStatement("UPDATE pizza SET code=? ,name=?,price=?,category=? WHERE code=?");
			prepInsert.setString(1, pizza.getCode());
			prepInsert.setString(2, pizza.getName());
			prepInsert.setDouble(3, pizza.getPrice());
			prepInsert.setString(4, pizza.getCategory().toString());
			prepInsert.setString(5, codePizza.toUpperCase());
			Integer numupdated = prepInsert.executeUpdate();
			return numupdated.equals(1);
		} catch (SQLException e) {
			manageSqlException(e);
			return false;
		} finally {
			closeSqlResources(conn, prepInsert, null);
		}
	}

	@Override
	public boolean deletePizza(String codePizza) {
		Connection conn = null;
		PreparedStatement prepInsert = null;
		try {
			conn = getConnection();
			prepInsert = conn.prepareStatement("DELETE FROM pizza WHERE code=?");
			prepInsert.setString(1, codePizza);
			Integer numDeleted = prepInsert.executeUpdate();
			return numDeleted.equals(1);
		} catch (SQLException e) {
			manageSqlException(e);
			return false;
		} finally {
			closeSqlResources(conn, prepInsert, null);
		}
	}

}
