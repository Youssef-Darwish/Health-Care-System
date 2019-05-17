package model.functionalities;

import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

import model.records.Record;

public class GetReports extends Functionality {
	public ResultSet getNumberOfNewPatients () {
		try {
			Statement stat = (Statement) con.createStatement();
			String query = "";
			query += "select count(ID) as TotalPatients from Patient "
					+ "where REGISTERATIONDATE > DATE(NOW() - INTERVAL 3 MONTH)";
			ResultSet result = (ResultSet) stat.executeQuery(query);
			
			return result;
					
		} catch (Exception e) {
			return null;
		}
	}
	public ResultSet getNumberOfNewStaff () {
		try {
			Statement stat = (Statement) con.createStatement();
			String query = "";
			query += "select count(ID) as c from hospital_db.staff where "
					+ "HIRINGDATE > DATE(NOW() - INTERVAL 3 MONTH) group by role;";
			ResultSet result = (ResultSet) stat.executeQuery(query);
			
			return result;
					
		} catch (Exception e) {
			return null;
		}
	}
	public ResultSet getAppointmentsChart() {
		try {
			Statement stat = (Statement) con.createStatement();
			String query = "";
			query += "select doctorid, count(patientID) from appointment"
					+ " where APPOINTMENTDATE > DATE(NOW() - INTERVAL 3 MONTH)"
					+ " group by doctorid;";
			System.out.println(query);
			ResultSet result = (ResultSet) stat.executeQuery(query);
			return result;
			
		} catch (Exception e) {
			return null;
		}
	}
	public ResultSet getNumberOfNewAppointments () {
		try {
			Statement stat = (Statement) con.createStatement();
			String query = "";
			query += "select count(ID) as TotalPatients, count(distinct(DOCTORID)) as doctor"
					+ ", count(distinct(PatientID)) as patient from `hospital_db`.`Appointment` "
					+ "where APPOINTMENTDATE > DATE(NOW() - INTERVAL 3 MONTH)";
			ResultSet result = (ResultSet) stat.executeQuery(query);
			return result;
			
		} catch (Exception e) {
			return null;
		}
	}
	public ResultSet getTotalPricesOfEachMedicine () {
		try {
			Statement stat = (Statement) con.createStatement();
			String query = "";
			System.out.println("here");
			query += "select medication , price*count(medication) from patientCase "
					+ "join hospital_db.medication "
					+ "on"
					+ " patientCase.medication = hospital_db.medication.name "
					+ "where patientCase.CaseDate > DATE(NOW() - INTERVAL 3 MONTH)"
					+ "group by medication";
			System.out.println(query);
			ResultSet result = (ResultSet) stat.executeQuery(query);
			return result;
			
		} catch (Exception e) {
			return null;
		}
	}
	

	@Override
	public int add(Record record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int edit(String key, String value, Record record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String key, String value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ResultSet getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet search(String key, String value) {
		// TODO Auto-generated method stub
		return null;
	}

}
