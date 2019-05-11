package model.functionalities;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

import model.records.AppointmentRecord;
import model.records.Record;

public class Appointment extends Functionality {

	@Override
	public int add(Record record) {
		AppointmentRecord r = (AppointmentRecord) record;
		System.out.println("entered");

		try {
			PreparedStatement stmt = con.prepareStatement(
					"INSERT INTO APPOINTMENT (PATIENTID,DOCTORID,HOUR,APPOINTMENTDATE) Values(?,?,?,?);");

			stmt.setInt(1, r.getPatientId());
			stmt.setInt(2, r.getDoctorId());
			System.out.println("doctor's ID" + String.valueOf(r.getDoctorId()));
			stmt.setString(3, r.getHour());
			stmt.setDate(4, (Date) r.getAppointmentDate());
			return stmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		}

	}

	@Override
	public int edit(String key, String value, Record record) {
		AppointmentRecord r = (AppointmentRecord) record;
		try {
			PreparedStatement stmt = con.prepareStatement("Update APPOINTMENT SET  PATIENTID = ? , DOCTORID = ?, "
					+ "HOUR = ? , APPOINTMENTDATE = ? " + " WHERE " + key + " = ? ;");

			stmt.setInt(1, r.getPatientId());
			stmt.setInt(2, r.getDoctorId());
			stmt.setString(3, r.getHour());
			stmt.setDate(4, (Date) r.getAppointmentDate());
			stmt.setString(5, value);
			return stmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.toString());
			return -1;
		}
	}

	@Override
	public int delete(String key, String value) {
		try {
			PreparedStatement stmt = con.prepareStatement("DELETE FROM APPOINTMENT " + " WHERE " + key + " = ? ;");
			stmt.setString(1, value);
			return stmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.toString());
			return -1;
		}
	}

	@Override
	public ResultSet search(String key, String value) {

		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM APPOINTMENT " + " WHERE " + key + " = ? ;");
			stmt.setString(1, value);
			return (ResultSet) stmt.executeQuery();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	@Override
	public ResultSet getAll() {

		try {
			Statement stat = (Statement) con.createStatement();
			String query = "";
			query += "SELECT * from APPOINTMENT;";
			ResultSet result = (ResultSet) stat.executeQuery(query);
			return result;
		} catch (Exception e) {
			return null;
		}
	}

}
