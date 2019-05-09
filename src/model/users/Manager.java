package model.users;

import com.mysql.jdbc.ResultSet;

import model.functionalities.GetReports;

public class Manager implements User {
	private GetReports gr = new GetReports();
	public ResultSet getNumberOfNewPatients () {
		return gr.getNumberOfNewPatients();
	}
	public ResultSet getNumberOfNewAppointments () {
		return gr.getNumberOfNewAppointments();
	}
	public ResultSet getTotalPricesOfEachMedicine () {
		return gr.getTotalPricesOfEachMedicine();
	}
}
