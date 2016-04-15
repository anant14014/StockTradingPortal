package stockportal;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import stockportal.model.Stock;
import stockportal.view.StocksFilterController;
import stockportal.view.StocksResultsController;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Stock Trading Portal");

        initRootLayout();
        showStocksFilter();
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