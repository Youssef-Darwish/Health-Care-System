package controller;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.Axis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
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
	
	private Manager m = new Manager();
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		XYChart.Series series = new XYChart.Series<>();
		ResultSet rs = m.getTotalPricesOfEachMedicine();
		//ResultSet rs = ((Manager) LoginController.loggedIn).getTotalPricesOfEachMedicine();
		//series.setName("amira");
		//series.getData().add(new XYChart.Data<>("1",2));
		try {
			while (!rs.isLast()) {
				rs.next();
				series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getDouble(2)));
			}
			barChart.getData().addAll(series);
			ResultSet rs1 = m.getNumberOfNewAppointments();
			while (!rs1.isLast()) {
			rs1.next();
			System.out.println(rs1.getInt(1));
			patientsCount.setText(String.valueOf(rs1.getInt(3)));
			doctorsCount.setText(String.valueOf(rs1.getInt(2)));
			appointmentsNumber.setText(String.valueOf(rs1.getInt(1)));
			}
			ResultSet rs2 = m.getNumberOfNewPatients();
			while (!rs2.isLast()) {
				rs2.next();
				//System.out.println(rs1.getInt(1));
				patients.setText(String.valueOf(rs2.getInt(1)));
				
				}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	
	}

}
