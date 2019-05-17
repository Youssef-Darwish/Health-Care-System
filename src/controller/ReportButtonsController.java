package controller;

import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ReportButtonsController implements Initializable {
	@FXML
	private Button NewComersButton, MedicationsChart, Appointments;
	
	@FXML
	private void goToComers(ActionEvent event) throws IOException{
		
		Parent pageParent = FXMLLoader.load(getClass().getResource("/view/NewComers.fxml"));
		Scene adminPageScene = new Scene(pageParent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(adminPageScene);

		stage.show();
		
	}
	@FXML
	private void goToMedications(ActionEvent event) throws IOException{
		Parent pageParent = FXMLLoader.load(getClass().getResource("/view/MedicineBarChart.fxml"));
		Scene adminPageScene = new Scene(pageParent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(adminPageScene);

		stage.show();
		
	}
	@FXML
	private void goToAppointments (ActionEvent event) throws IOException {
		Parent pageParent = FXMLLoader.load(getClass().getResource("/view/NewAppointments.fxml"));
		Scene adminPageScene = new Scene(pageParent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(adminPageScene);

		stage.show();
		
		
	}
	@FXML
	public void logout (ActionEvent event) throws IOException {
		Parent pageParent = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
		Scene adminPageScene = new Scene(pageParent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(adminPageScene);

		stage.show();
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}
