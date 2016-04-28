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

public class Account extends SQLObject{
	private IntegerProperty accountNumber;
	private StringProperty type;
	private IntegerProperty balance;
	private IntegerProperty customerId;
	private ObjectProperty<Hyperlink> button;
	public MainApp mainApp;

	public Account(int accountNumber, String type, int balance, int customerId) {
		super();
		this.accountNumber = new SimpleIntegerProperty(accountNumber);
		this.type = new SimpleStringProperty(type);
		this.balance = new SimpleIntegerProperty(balance);
		this.customerId = new SimpleIntegerProperty(customerId);
		button = new SimpleObjectProperty<Hyperlink>(new Hyperlink());
		button.get().setText("Account");
		button.get().setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent e) {
		    	try {
					mainApp.showSingleAccount(Account.this);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    }
		});
	}
	
	public Account() {
		this(-1, "", -1, -1);
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
	
	public static Account findOneByAccountNumber(int accountNumber) throws SQLException {
		String selectQuery = "SELECT * FROM account WHERE accountNumber = ?";
		Account account;
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://" + serverName + ":" + portNumber + "/" + dbName, userName, password);
			PreparedStatement pStatement = connection.prepareStatement(selectQuery);
			pStatement.setInt(1, accountNumber);
		    ResultSet resultSet = pStatement.executeQuery();
		    account = createAccount(resultSet); 
		} catch (SQLException sqlex) {
	      throw sqlex;
	   }
	   return account;
	}
	
	public static ObservableList<Account> findByAccountNumber(int accountNumber) throws SQLException {
		ObservableList<Account> accounts = FXCollections.observableArrayList();
		String selectQuery = "SELECT * FROM account WHERE accountNumber = ?";
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://" + serverName + ":" + portNumber + "/" + dbName, userName, password);
			PreparedStatement pStatement = connection.prepareStatement(selectQuery);
			pStatement.setInt(1, accountNumber);
		    ResultSet resultSet = pStatement.executeQuery();
		    while (resultSet.next()) {
		    	accounts.add(createAccount(resultSet));
		    }
		} catch (SQLException sqlex) {
	      throw sqlex;
	   }
	   return accounts;
	}
	
	public static ObservableList<Account> findByType(String type) throws SQLException {
		ObservableList<Account> accounts = FXCollections.observableArrayList();
		String selectQuery = "SELECT * FROM account WHERE type = ?";
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://" + serverName + ":" + portNumber + "/" + dbName, userName, password);
			PreparedStatement pStatement = connection.prepareStatement(selectQuery);
			pStatement.setString(1, type);
		    ResultSet resultSet = pStatement.executeQuery();
		    while (resultSet.next()) {
		    	accounts.add(createAccount(resultSet));
		    }
		} catch (SQLException sqlex) {
	      throw sqlex;
	   }
	   return accounts;
	}
	
	public static ObservableList<Account> findByCustomerId(int customerId) throws SQLException {
		ObservableList<Account> accounts = FXCollections.observableArrayList();
		String selectQuery = "SELECT * FROM account WHERE customerId = ?";
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://" + serverName + ":" + portNumber + "/" + dbName, userName, password);
			PreparedStatement pStatement = connection.prepareStatement(selectQuery);
			pStatement.setInt(1, customerId);
		    ResultSet resultSet = pStatement.executeQuery();
		    while (resultSet.next()) {
		    	accounts.add(createAccount(resultSet));
		    }
		} catch (SQLException sqlex) {
	      throw sqlex;
	   }
	   return accounts;
	}
	
	public static ObservableList<Account> findByBalance(int balanceFrom, int balanceTo) throws SQLException {
		ObservableList<Account> accounts = FXCollections.observableArrayList();
		String selectQuery = "SELECT * FROM account WHERE balance >= ? AND balance <= ?";
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://" + serverName + ":" + portNumber + "/" + dbName, userName, password);
			PreparedStatement pStatement = connection.prepareStatement(selectQuery);
			pStatement.setInt(1, balanceFrom);
			pStatement.setInt(2, balanceTo);
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
		account.setAccountNumber(resultSet.getInt("accountNumber"));
		account.setType(resultSet.getString("type"));
		account.setBalance(resultSet.getInt("balance"));
		account.setCustomerId(resultSet.getInt("customerId"));
		return account;
	}

	public final IntegerProperty accountNumberProperty() {
		return this.accountNumber;
	}
	

	public final int getAccountNumber() {
		return this.accountNumberProperty().get();
	}
	

	public final void setAccountNumber(final int accountNumber) {
		this.accountNumberProperty().set(accountNumber);
	}
	

	public final StringProperty typeProperty() {
		return this.type;
	}
	

	public final String getType() {
		return this.typeProperty().get();
	}
	

	public final void setType(final String type) {
		this.typeProperty().set(type);
	}
	

	public final IntegerProperty balanceProperty() {
		return this.balance;
	}
	

	public final int getBalance() {
		return this.balanceProperty().get();
	}
	

	public final void setBalance(final int balance) {
		this.balanceProperty().set(balance);
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
