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

public class Account extends SQLObject{
	private StringProperty name;
	private IntegerProperty value;
	
	public Account(String name, int value) {
		super();
		this.name = new SimpleStringProperty(name);
		this.value = new SimpleIntegerProperty(value);
	}
	
	public Account() {
		this("", -1);
	}
	
	public static ObservableList<Account> findAll() throws SQLException {
		ObservableList<Account> accounts = FXCollections.observableArrayList();
		String selectQuery = "SELECT * FROM account";
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://" + serverName + ":" + portNumber + "/" + dbName, userName, password);
			PreparedStatement pStatement = connection.prepareStatement(selectQuery);
		    ResultSet resultSet = pStatement.executeQuery();
		    while (resultSet.next()) {
		    	accounts.add(createAccount(resultSet));
		    }
		} catch (SQLException sqlex) {
	      throw sqlex;
	   }
	   return accounts;
	}
	
	public static ObservableList<Account> findByName(String name) throws SQLException {
		ObservableList<Account> accounts = FXCollections.observableArrayList();
		String selectQuery = "SELECT * FROM account WHERE name LIKE ?";
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://" + serverName + ":" + portNumber + "/" + dbName, userName, password);
			PreparedStatement pStatement = connection.prepareStatement(selectQuery);
			pStatement.setString(1, name + '%');
		    ResultSet resultSet = pStatement.executeQuery();
		    while (resultSet.next()) {
		    	accounts.add(createAccount(resultSet));
		    }
		} catch (SQLException sqlex) {
	      throw sqlex;
	   }
	   return accounts;
	}
	
	public static ObservableList<Account> findByValue(int from, int to) throws SQLException {
		ObservableList<Account> accounts = FXCollections.observableArrayList();
		String selectQuery = "SELECT * FROM account WHERE value >= ? AND value <= ?";
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://" + serverName + ":" + portNumber + "/" + dbName, userName, password);
			PreparedStatement pStatement = connection.prepareStatement(selectQuery);
			pStatement.setInt(1, from);
			pStatement.setInt(2, to);
		    ResultSet resultSet = pStatement.executeQuery();
		    while (resultSet.next()) {
		    	accounts.add(createAccount(resultSet));
		    }
		} catch (SQLException sqlex) {
	      throw sqlex;
	   }
	   return accounts;
	}
	
	public static ObservableList<Account> findByNameAndValue(String name, int from, int to) throws SQLException {
		ObservableList<Account> accounts = FXCollections.observableArrayList();
		String selectQuery = "SELECT * FROM account WHERE name LIKE ? AND value >= ? AND value <= ?";
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://" + serverName + ":" + portNumber + "/" + dbName, userName, password);
			PreparedStatement pStatement = connection.prepareStatement(selectQuery);
			pStatement.setString(1, name + '%');
			pStatement.setInt(2, from);
			pStatement.setInt(3, to);
		    ResultSet resultSet = pStatement.executeQuery();
		    while (resultSet.next()) {
		    	accounts.add(createAccount(resultSet));
		    }
		} catch (SQLException sqlex) {
	      throw sqlex;
	   }
	   return accounts;
	}

	private static Account createAccount(ResultSet resultSet) throws SQLException {
		Account account = new Account();
		account.setName(resultSet.getString("name"));
		account.setValue(resultSet.getInt("value"));
		return account;
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

}
