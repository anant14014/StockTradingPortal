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

public class User extends SQLObject{
	private StringProperty name;
	private IntegerProperty value;
	
	public User(String name, int value) {
		super();
		this.name = new SimpleStringProperty(name);
		this.value = new SimpleIntegerProperty(value);
	}
	
	public User() {
		this("", -1);
	}
	
	public static ObservableList<User> findAll() throws SQLException {
		ObservableList<User> users = FXCollections.observableArrayList();
		String selectQuery = "SELECT * FROM user";
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://" + serverName + ":" + portNumber + "/" + dbName, userName, password);
			PreparedStatement pStatement = connection.prepareStatement(selectQuery);
		    ResultSet resultSet = pStatement.executeQuery();
		    while (resultSet.next()) {
		    	users.add(createUser(resultSet));
		    }
		} catch (SQLException sqlex) {
	      throw sqlex;
	   }
	   return users;
	}
	
	public static ObservableList<User> findByName(String name) throws SQLException {
		ObservableList<User> users = FXCollections.observableArrayList();
		String selectQuery = "SELECT * FROM user WHERE name LIKE ?";
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://" + serverName + ":" + portNumber + "/" + dbName, userName, password);
			PreparedStatement pStatement = connection.prepareStatement(selectQuery);
			pStatement.setString(1, name + '%');
		    ResultSet resultSet = pStatement.executeQuery();
		    while (resultSet.next()) {
		    	users.add(createUser(resultSet));
		    }
		} catch (SQLException sqlex) {
	      throw sqlex;
	   }
	   return users;
	}
	
	public static ObservableList<User> findByValue(int from, int to) throws SQLException {
		ObservableList<User> users = FXCollections.observableArrayList();
		String selectQuery = "SELECT * FROM user WHERE value >= ? AND value <= ?";
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://" + serverName + ":" + portNumber + "/" + dbName, userName, password);
			PreparedStatement pStatement = connection.prepareStatement(selectQuery);
			pStatement.setInt(1, from);
			pStatement.setInt(2, to);
		    ResultSet resultSet = pStatement.executeQuery();
		    while (resultSet.next()) {
		    	users.add(createUser(resultSet));
		    }
		} catch (SQLException sqlex) {
	      throw sqlex;
	   }
	   return users;
	}
	
	public static ObservableList<User> findByNameAndValue(String name, int from, int to) throws SQLException {
		ObservableList<User> users = FXCollections.observableArrayList();
		String selectQuery = "SELECT * FROM user WHERE name LIKE ? AND value >= ? AND value <= ?";
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://" + serverName + ":" + portNumber + "/" + dbName, userName, password);
			PreparedStatement pStatement = connection.prepareStatement(selectQuery);
			pStatement.setString(1, name + '%');
			pStatement.setInt(2, from);
			pStatement.setInt(3, to);
		    ResultSet resultSet = pStatement.executeQuery();
		    while (resultSet.next()) {
		    	users.add(createUser(resultSet));
		    }
		} catch (SQLException sqlex) {
	      throw sqlex;
	   }
	   return users;
	}

	private static User createUser(ResultSet resultSet) throws SQLException {
		User user = new User();
		user.setName(resultSet.getString("name"));
		user.setValue(resultSet.getInt("value"));
		return user;
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