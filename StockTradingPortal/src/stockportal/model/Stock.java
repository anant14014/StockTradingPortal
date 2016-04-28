package stockportal.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Stock extends SQLObject{
	private StringProperty name;
	private IntegerProperty value;
	private IntegerProperty quantity;
	
	public Stock(String name, int value, int quantity) {
		super();
		this.name = new SimpleStringProperty(name);
		this.value = new SimpleIntegerProperty(value);
		this.quantity = new SimpleIntegerProperty(quantity);
	}
	
	public Stock() {
		this("", -1, -1);
	}
	
	public static ObservableList<Stock> findAll() throws SQLException {
		ObservableList<Stock> stocks = FXCollections.observableArrayList();
		String selectQuery = "SELECT * FROM stock";
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://" + serverName + ":" + portNumber + "/" + dbName, userName, password);
			PreparedStatement pStatement = connection.prepareStatement(selectQuery);
		    ResultSet resultSet = pStatement.executeQuery();
		    while (resultSet.next()) {
		    	stocks.add(createStock(resultSet));
		    }
		} catch (SQLException sqlex) {
	      throw sqlex;
	   }
	   return stocks;
	}
	
	public static ObservableList<Stock> findByName(String name) throws SQLException {
		ObservableList<Stock> stocks = FXCollections.observableArrayList();
		String selectQuery = "SELECT * FROM stock WHERE name LIKE ?";
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://" + serverName + ":" + portNumber + "/" + dbName, userName, password);
			PreparedStatement pStatement = connection.prepareStatement(selectQuery);
			pStatement.setString(1, "%" + name + "%");
		    ResultSet resultSet = pStatement.executeQuery();
		    while (resultSet.next()) {
		    	stocks.add(createStock(resultSet));
		    }
		} catch (SQLException sqlex) {
	      throw sqlex;
	   }
	   return stocks;
	}
	
	public static ObservableList<Stock> findByValue(int from, int to) throws SQLException {
		ObservableList<Stock> stocks = FXCollections.observableArrayList();
		String selectQuery = "SELECT * FROM stock WHERE value >= ? AND value <= ?";
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://" + serverName + ":" + portNumber + "/" + dbName, userName, password);
			PreparedStatement pStatement = connection.prepareStatement(selectQuery);
			pStatement.setInt(1, from);
			pStatement.setInt(2, to);
		    ResultSet resultSet = pStatement.executeQuery();
		    while (resultSet.next()) {
		    	stocks.add(createStock(resultSet));
		    }
		} catch (SQLException sqlex) {
	      throw sqlex;
	   }
	   return stocks;
	}
	
	public static ObservableList<Stock> findByNameAndValue(String name, int from, int to) throws SQLException {
		ObservableList<Stock> stocks = FXCollections.observableArrayList();
		String selectQuery = "SELECT * FROM stock WHERE name LIKE ? AND value >= ? AND value <= ?";
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://" + serverName + ":" + portNumber + "/" + dbName, userName, password);
			PreparedStatement pStatement = connection.prepareStatement(selectQuery);
			pStatement.setString(1, name + '%');
			pStatement.setInt(2, from);
			pStatement.setInt(3, to);
		    ResultSet resultSet = pStatement.executeQuery();
		    while (resultSet.next()) {
		    	stocks.add(createStock(resultSet));
		    }
		} catch (SQLException sqlex) {
	      throw sqlex;
	   }
	   return stocks;
	}
	
	public static ObservableList<Stock> findByAccount(int accountNumber) throws SQLException {
		ObservableList<Stock> stocks = FXCollections.observableArrayList();
		String selectQuery = "SELECT name, value, quantity FROM has_stock, stock WHERE has_stock.stockName = stock.name AND accountNumber = ?";
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://" + serverName + ":" + portNumber + "/" + dbName, userName, password);
			PreparedStatement pStatement = connection.prepareStatement(selectQuery);
			pStatement.setInt(1, accountNumber);
		    ResultSet resultSet = pStatement.executeQuery();
		    while (resultSet.next()) {
		    	stocks.add(createStockWithQuantity(resultSet));
		    }
		} catch (SQLException sqlex) {
	      throw sqlex;
	   }
	   return stocks;
	}

	private static Stock createStock(ResultSet resultSet) throws SQLException {
		Stock stock = new Stock();
		stock.setName(resultSet.getString("name"));
		stock.setValue(resultSet.getInt("value"));
		return stock;
	}
	
	private static Stock createStockWithQuantity(ResultSet resultSet) throws SQLException {
		Stock stock = new Stock();
		stock.setName(resultSet.getString("name"));
		stock.setValue(resultSet.getInt("value"));
		stock.setQuantity(resultSet.getInt("quantity"));
		return stock;
	}
	
	public final StringProperty nameProperty() {
		return this.name;
	}
	
	public final String getName() {
		return this.nameProperty().get();
	}
	
	public final void setName(final String name) {
		this.nameProperty().set(name);
	}
	
	public final IntegerProperty valueProperty() {
		return this.value;
	}
	
	public final int getValue() {
		return this.valueProperty().get();
	}
	
	public final void setValue(final int value) {
		this.valueProperty().set(value);
	}

	public final IntegerProperty quantityProperty() {
		return this.quantity;
	}
	

	public final int getQuantity() {
		return this.quantityProperty().get();
	}
	

	public final void setQuantity(final int quantity) {
		this.quantityProperty().set(quantity);
	}
	

}
