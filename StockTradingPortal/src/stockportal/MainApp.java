package stockportal;

import java.io.IOException;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import stockportal.model.Account;
import stockportal.model.Listing;
import stockportal.model.Stock;
import stockportal.model.User;
import stockportal.view.AccountsFilterController;
import stockportal.view.AccountsResultsController;
import stockportal.view.ListingsFilterController;
import stockportal.view.ListingsResultsController;
import stockportal.view.QueryHomeController;
import stockportal.view.SingleAccountController;
import stockportal.view.StocksFilterController;
import stockportal.view.StocksResultsController;
import stockportal.view.UsersFilterController;
import stockportal.view.UsersResultsController;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Stock Trading Portal");

        initRootLayout();
        showQueryHome();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showHome() {
        try {
            // Load home.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/Home.fxml"));
            AnchorPane home = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(home);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showQueryHome() {
        try {
            // Load home.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/QueryHome.fxml"));
            AnchorPane queryHome = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(queryHome);
            
            QueryHomeController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showStocksFilter() {
        try {
            // Load home.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/StocksFilter.fxml"));
            AnchorPane stocksFilter = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(stocksFilter);
            
            StocksFilterController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showUsersFilter() {
        try {
            // Load home.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/UsersFilter.fxml"));
            AnchorPane usersFilter = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(usersFilter);
            
            UsersFilterController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showAccountsFilter() {
        try {
            // Load home.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/AccountsFilter.fxml"));
            AnchorPane accountsFilter = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(accountsFilter);
            
            AccountsFilterController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showListingsFilter() {
    	try {
    		// Load home.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ListingsFilter.fxml"));
            AnchorPane listingsFilter = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(listingsFilter);
            
            ListingsFilterController controller = loader.getController();
            controller.setMainApp(this);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    public void showStocksResults(ObservableList<Stock> stocks) {
        try {
            // Load home.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/StocksResults.fxml"));
            AnchorPane results = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(results);
            
            StocksResultsController controller = loader.getController();
            controller.setMainApp(this);
            controller.showResults(stocks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showUsersResults(ObservableList<User> users) {
        try {
            // Load home.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/UsersResults.fxml"));
            AnchorPane results = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(results);
            
            UsersResultsController controller = loader.getController();
            controller.setMainApp(this);
            controller.showResults(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showAccountsResults(ObservableList<Account> accounts) {
        try {
            // Load home.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/AccountsResults.fxml"));
            AnchorPane results = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(results);
            
            AccountsResultsController controller = loader.getController();
            controller.setMainApp(this);
            controller.showResults(accounts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showListingsResults(ObservableList<Listing> listings) {
        try {
            // Load home.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ListingsResults.fxml"));
            AnchorPane results = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(results);
            
            ListingsResultsController controller = loader.getController();
            controller.setMainApp(this);
            controller.showResults(listings);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showSingleAccount(Account account) throws SQLException {
        try {
            // Load home.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/SingleAccount.fxml"));
            AnchorPane results = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(results);
            
            SingleAccountController controller = loader.getController();
            controller.setMainApp(this);
            controller.setAccount(account);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}