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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.users.Manager;

public class NewAppointmentsController implements Initializable {
	@FXML
	private CategoryAxis x = new CategoryAxis();
	@FXML
	private NumberAxis y = new NumberAxis();
	@FXML
	private BarChart <?,?> barChart = new BarChart<>(x,y);
	@FXML
	private Label patientsCount;
	@FXML
	private Label doctorsCount;
	@FXML
	private Label appointmentsNumber;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		XYChart.Series series = new XYChart.Series<>();
		ResultSet rs = ((Manager) LoginController.loggedIn).getAppointmentsChart();
		
		try {
			while (rs.next()) {
				
				//series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getDouble(2)));
			}
			barChart.getData().addAll(series);
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
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
