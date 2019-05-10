package controller;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.users.Manager;

public class NewAppointmentsController implements Initializable {
	@FXML
	private Label patientsCount;
	@FXML
	private Label doctorsCount;
	@FXML
	private Label appointmentsNumber;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ResultSet rs1 = ((Manager) LoginController.loggedIn).getNumberOfNewAppointments();
		try {
			while (!rs1.isLast()) {
			rs1.next();
			System.out.println(rs1.getInt(1));
			patientsCount.setText(String.valueOf(rs1.getInt(3)));
			doctorsCount.setText(String.valueOf(rs1.getInt(2)));
			appointmentsNumber.setText(String.valueOf(rs1.getInt(1)));
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
