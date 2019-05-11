package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.users.Manager;

public class NewComersController implements Initializable{
	@FXML
	private Label patients;
	@FXML
	private Label doctors;
	@FXML
	private Label receptionists;
	@FXML
	private Label managers;
	@FXML
	private Label admins;
	private Label [] labels = new Label[4];
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ResultSet rs = ((Manager) LoginController.loggedIn).getNumberOfNewPatients();
		ResultSet rs1 = ((Manager) LoginController.loggedIn).getNumberOfNewStaff();
		try {
			while (!rs.isLast()) {
				rs.next();
				patients.setText(String.valueOf(rs.getInt(1)));
				
				}
			
			labels[0] = admins;
			labels[1] = doctors;
			labels[2] = managers;
			labels[3] = receptionists;

			int i = 0;
			while (!rs1.isLast()) {
				rs1.next();
				labels[i].setText(String.valueOf(rs1.getInt(1)));
				i++;
				
				}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@FXML
	public void logout (ActionEvent event) throws IOException {
		Parent pageParent = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
		Scene adminPageScene = new Scene(pageParent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(adminPageScene);

		stage.show();
	}
	@FXML
	public void goBack (ActionEvent event) throws IOException {
		Parent pageParent = FXMLLoader.load(getClass().getResource("/view/ReportButtons.fxml"));
		Scene adminPageScene = new Scene(pageParent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(adminPageScene);

		stage.show();
	}
}
