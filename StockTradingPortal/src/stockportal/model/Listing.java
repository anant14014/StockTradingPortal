package stockportal.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Listing extends SQLObject {
	private StringProperty name;
	
	public Listing(String name) {
		super();
		this.name = new SimpleStringProperty(name);
	}
	
	public Listing() {
		this("");
	}
	
	public static ObservableList<Listing> findAll() throws SQLException { 
		ObservableList<Listing> listings = FXCollections.observableArrayList();
		String selectQuery = "SELECT * FROM listing";
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://" + serverName + ":" + portNumber + "/" + dbName, userName, password);
			PreparedStatement pStatement = connection.prepareStatement(selectQuery);
			ResultSet resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				listings.add(createListing(resultSet));
			}
		} catch (SQLException sqlex) {
			throw sqlex;
		}
		return listings;
	}
	
	public static ObservableList<Listing> findByName(String name) throws SQLException {
		ObservableList<Listing> listings = FXCollections.observableArrayList();
		String selectQuery = "SELECT * FROM listing WHERE name LIKE ?";
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://" + serverName + ":" + portNumber + "/" + dbName, userName, password);
			PreparedStatement pStatement = connection.prepareStatement(selectQuery);
			pStatement.setString(1, "%" + name + "%");
			ResultSet resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				listings.add(createListing(resultSet));
			}
		} catch (SQLException sqlex) {
			throw sqlex;
		}
		return listings;
	}
	
	private static Listing createListing(ResultSet resultSet) throws SQLException {
		Listing listing = new Listing();
		listing.setName(resultSet.getString("name"));
		return listing;
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
}
