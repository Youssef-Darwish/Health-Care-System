package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.mysql.jdbc.ResultSet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.records.Record;
import model.users.Admin;
import model.users.Doctor;
import model.users.Manager;
import model.users.Receptionist;
import model.users.User;

public class LoginController implements Initializable {
	// private static Database db = new Database();
	// private Connection con = db.getCon();
	
	// change it in login
	public static User loggedIn;

	@FXML // fx:id="emailText"
	private TextField email;

	@FXML // fx:id="passwordText"
	private PasswordField password;

	@FXML
	public int login(ActionEvent event) throws IOException {
		Parent pageParent = null; 
//		try {
//			Statement stat = con.createStatement();
//			String query = "";
//			query += "select Role from STAFF where Name = '" + userName + "' and pass = MD5('" + password + "');";
//
//			ResultSet result = (ResultSet) stat.executeQuery(query);
//			if (!result.next()) { // empty set
//				return -1;
//			}

			//int Role = result.getInt("Role");
			int Role = 1;

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

//		} catch (SQLException e) {
//
//			return -2;
//		}
		
		Scene adminPageScene = new Scene(pageParent);
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		stage.setScene(adminPageScene);
		stage.show();

		return 1;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
