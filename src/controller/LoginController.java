package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.mysql.jdbc.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.records.Record;
import model.records.StaffRecord;
import model.users.Admin;
import model.users.Doctor;
import model.users.Manager;
import model.users.Receptionist;
import model.users.User;

public class LoginController implements Initializable {
	public static Database db = new Database();
	public static Connection con = db.getCon();
	private HelperPlugin plugin = new HelperPlugin();

	// change it in login
	public static User loggedIn;

	@FXML // fx:id="emailText"
	private TextField emailText;

	@FXML // fx:id="passwordText"
	private PasswordField passwordText;

	@FXML // fx:id="warningLabel"
	private Label warningLabel;

	@FXML
	public int login(ActionEvent event) throws IOException, SQLException {
		Parent pageParent = null;

		if (validateInput()) {

			warningLabel.setVisible(true);
		} else {
			try {
				Statement stat = con.createStatement();
				String query = "";
				query += "select Role from STAFF where Name = '" + emailText.getText() + "' and pass = MD5('"
						+ passwordText.getText() + "');";

				ResultSet result = (ResultSet) stat.executeQuery(query);
				if (!result.next()) { // empty set
					warningLabel.setVisible(true);
				}

				int Role = result.getInt("Role");

				if (Role == 1) {
					pageParent = FXMLLoader.load(getClass().getResource("/view/AdminScene.fxml"));
					loggedIn = new Admin();
				} else if (Role == 2) {
					loggedIn = new Manager();
				} else if (Role == 3) {
					loggedIn = new Doctor();
				} else if (Role == 4) {
					loggedIn = new Receptionist();
				}

			} catch (SQLException e) {

				return -2;
			}

			Scene adminPageScene = new Scene(pageParent);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(adminPageScene);

			stage.show();
		}
		return 1;
	}

	@FXML
	public void quit(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	private boolean validateInput() {
		// check that all fields are not empty for now
		boolean check = emailText.getText().trim().isEmpty() || passwordText.getText().trim().isEmpty();
		return check;

	}

}
