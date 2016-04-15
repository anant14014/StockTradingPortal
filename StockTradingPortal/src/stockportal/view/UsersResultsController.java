package stockportal.view;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import stockportal.MainApp;
import stockportal.model.User;

public class UsersResultsController {
	
	@FXML
	private Button backButton;
	
	@FXML
	private TableView<User> userTable;
	@FXML
	private TableColumn<User, Integer> idColumn;
	@FXML
	private TableColumn<User, String> nameColumn;
	@FXML
	private TableColumn<User, String> emailColumn;
	@FXML
	private TableColumn<User, String> mobileColumn;
	@FXML
	private TableColumn<User, Hyperlink> linkColumn;
	
	private MainApp mainApp;
	
	@FXML
    void initialize() {
		idColumn.setCellValueFactory(cellData -> cellData.getValue().customerIdProperty().asObject());
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
		mobileColumn.setCellValueFactory(cellData -> cellData.getValue().mobileProperty());
    }
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	public void showResults(ObservableList<User> results){
		userTable.setItems(results);
	}
	
	public void handleBack() {
		mainApp.showUsersFilter();
	}
	
}