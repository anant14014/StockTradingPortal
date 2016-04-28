package stockportal.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Hyperlink;
import stockportal.MainApp;

public class User extends SQLObject{
	private IntegerProperty customerId;
	private StringProperty name;
	private StringProperty dob;
	private StringProperty email;
	private StringProperty mobile;
	private ObjectProperty<Hyperlink> button;
	public MainApp mainApp;
	
	public User(int customerId, String name, String dob, String email, String mobile) {
		super();
		this.customerId = new SimpleIntegerProperty(customerId);
		this.name = new SimpleStringProperty(name);
		this.dob = new SimpleStringProperty(dob);
		this.email = new SimpleStringProperty(email);
		this.mobile = new SimpleStringProperty(mobile);
		button = new SimpleObjectProperty<Hyperlink>(new Hyperlink());
		button.get().setText("Accounts");
		button.get().setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent e) {
		    	try {
					ObservableList<Account> accounts = Account.findByCustomerId(getCustomerId());
					mainApp.showAccountsResults(accounts);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    }
		});
	}
	
	public User() {
		this(-1, "", "", "", "");
	}
	
	public static ObservableList<User> findAll() throws SQLException {
		ObservableList<User> users = FXCollections.observableArrayList();
		String selectQuery = "SELECT * FROM customer";
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
	
	public static ObservableList<User> findById(int customerId) throws SQLException {
		ObservableList<User> users = FXCollections.observableArrayList();
		String selectQuery = "SELECT * FROM customer WHERE customerId = ?";
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://" + serverName + ":" + portNumber + "/" + dbName, userName, password);
			PreparedStatement pStatement = connection.prepareStatement(selectQuery);
			pStatement.setInt(1, customerId);
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
		String selectQuery = "SELECT * FROM customer WHERE name LIKE ?";
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://" + serverName + ":" + portNumber + "/" + dbName, userName, password);
			PreparedStatement pStatement = connection.prepareStatement(selectQuery);
			pStatement.setString(1, "%" + name + "%");
		    ResultSet resultSet = pStatement.executeQuery();
		    while (resultSet.next()) {
		    	users.add(createUser(resultSet));
		    }
		} catch (SQLException sqlex) {
	      throw sqlex;
	   }
	   return users;
	}
	
	public static ObservableList<User> findByNameAndID(String name, int id) throws SQLException {
		ObservableList<User> users = FXCollections.observableArrayList();
		String selectQuery = "SELECT * FROM customer WHERE name = ? AND customerID = ?";
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://" + serverName + ":" + portNumber + "/" + dbName, userName, password);
			PreparedStatement pStatement = connection.prepareStatement(selectQuery);
			pStatement.setString(1, name);
			pStatement.setInt(2, id);
		    ResultSet resultSet = pStatement.executeQuery();
		    while (resultSet.next()) {
		    	users.add(createUser(resultSet));
		    }
		} catch (SQLException sqlex) {
	      throw sqlex;
	   }
	   return users;
	}
	
	public static ObservableList<User> findByEmail(String email) throws SQLException {
		ObservableList<User> users = FXCollections.observableArrayList();
		String selectQuery = "SELECT * FROM customer WHERE email LIKE ?";
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://" + serverName + ":" + portNumber + "/" + dbName, userName, password);
			PreparedStatement pStatement = connection.prepareStatement(selectQuery);
			pStatement.setString(1, "%" + email + "%");
		    ResultSet resultSet = pStatement.executeQuery();
		    while (resultSet.next()) {
		    	users.add(createUser(resultSet));
		    }
		} catch (SQLException sqlex) {
	      throw sqlex;
	   }
	   return users;
	}
	
	public static ObservableList<User> findByBalance(int balanceFrom, int balanceTo) throws SQLException {
		ObservableList<User> users = FXCollections.observableArrayList();
		String selectQuery = "SELECT customerId, name, dob, email, mobile, sum(balance) FROM customer NATURAL JOIN account GROUP BY customerId HAVING sum(balance) >= ? AND sum(balance) <= ?";
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://" + serverName + ":" + portNumber + "/" + dbName, userName, password);
			PreparedStatement pStatement = connection.prepareStatement(selectQuery);
			pStatement.setInt(1, balanceFrom);
			pStatement.setInt(2, balanceTo);
		    ResultSet resultSet = pStatement.executeQuery();
		    while (resultSet.next()) {
		    	users.add(createUser(resultSet));
		    }
		} catch (SQLException sqlex) {
	      throw sqlex;
	   }
	   return users;
	}
	
	public static int AddUser(String name, String mobile, String email, String dob, int id, String pass) throws SQLException {
		int numRowsChanged;
		ObservableList<Stock> users = FXCollections.observableArrayList();
		String selectQuery = "INSERT INTO customer VALUES(?, ?, ?, ?, ?, ?)";
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://" + serverName + ":" + portNumber + "/" + dbName, userName, password);
			PreparedStatement pStatement = connection.prepareStatement(selectQuery);
			pStatement.setInt(1, id);
			pStatement.setString(2, pass);
			pStatement.setString(3, name);
			pStatement.setString(4,  dob);
			pStatement.setString(5, email);
			pStatement.setString(6, mobile);
		    numRowsChanged = pStatement.executeUpdate();
		    
		} catch (SQLException sqlex) {
	      throw sqlex;
	   }
	   return numRowsChanged;
	}

	private static User createUser(ResultSet resultSet) throws SQLException {
		User user = new User();
		user.setCustomerId(resultSet.getInt("customerId"));
		user.setName(resultSet.getString("name"));
		user.setDob(resultSet.getString("dob"));
		user.setEmail(resultSet.getString("email"));
		user.setMobile(resultSet.getString("mobile"));
		return user;
	}

	public final IntegerProperty customerIdProperty() {
		return this.customerId;
	}
	

	public final int getCustomerId() {
		return this.customerIdProperty().get();
	}
	

	public final void setCustomerId(final int customerId) {
		this.customerIdProperty().set(customerId);
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
	

	public final StringProperty dobProperty() {
		return this.dob;
	}
	

	public final String getDob() {
		return this.dobProperty().get();
	}
	

	public final void setDob(final String dob) {
		this.dobProperty().set(dob);
	}
	

	public final StringProperty emailProperty() {
		return this.email;
	}
	

	public final String getEmail() {
		return this.emailProperty().get();
	}
	

	public final void setEmail(final String email) {
		this.emailProperty().set(email);
	}
	

	public final StringProperty mobileProperty() {
		return this.mobile;
	}
	

	public final String getMobile() {
		return this.mobileProperty().get();
	}
	

	public final void setMobile(final String mobile) {
		this.mobileProperty().set(mobile);
	}

	public final ObjectProperty<Hyperlink> buttonProperty() {
		return this.button;
	}
	

	public final Hyperlink getButton() {
		return this.buttonProperty().get();
	}
	

	public final void setButton(final Hyperlink button) {
		this.buttonProperty().set(button);
	}
	
	

}