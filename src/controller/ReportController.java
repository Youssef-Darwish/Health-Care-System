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
import javafx.scene.chart.Axis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.users.Manager;

public class ReportController implements Initializable {
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
	@FXML
	private Label patients;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		XYChart.Series series = new XYChart.Series<>();
		ResultSet rs = ((Manager) LoginController.loggedIn).getTotalPricesOfEachMedicine();
		System.out.println(rs==null);
		try {
			while (rs.next()) {
				series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getDouble(2)));
			}
			barChart.getData().addAll(series);
			
			
		} catch (SQLException e) {
			
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
