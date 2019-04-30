package model.functionalities;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.jdbc.ResultSet;

import model.records.PatientCaseRecord;
import model.records.Record;

public class PatientCase extends Functionality {
	private int validate(Record record) {
		PatientCaseRecord r = (PatientCaseRecord) record;
		System.out.println("entered");

		try {
			PreparedStatement stmt1 = con.prepareStatement("SELECT * FROM MEDICATION " + "WHERE NAME = ? ;");
			stmt1.setString(1, r.getMedication());
			// System.out.println(">>"+stmt1);
			ResultSet rs = (ResultSet) stmt1.executeQuery();
			// no medication
			if (rs.next() == false)
				return -1;
			// high amount
			if (r.getAmount() < rs.getInt("MINI") || r.getAmount() > rs.getInt("MAXM"))
				return -2;

			PreparedStatement stmt2 = con.prepareStatement("SELECT MEDCOMP FROM MEDCOMPONENTS "
					+ "JOIN PATIENTALERGIES ON " + "MEDCOMPONENTS.MEDCOMP = PATIENTALERGIES.PATIENTALERGIE "
					+ "WHERE NAME = ? and PATIENTID = " + r.getPatientId() + " ;");
			stmt2.setString(1, r.getMedication());
			ResultSet rs2 = (ResultSet) stmt2.executeQuery();
			// this medication doesn't suit patient
			if (rs2.next() == true)
				return -3;

			return 1;
		} catch (SQLException e) {
			System.out.println(e);
			return -1;
		}
	}

	@Override
	public int add(Record record) {
		PatientCaseRecord r = (PatientCaseRecord) record;
		System.out.println("entered");

		try {
//			int x = validate(record);
//			if (x != 1)
//				return x;

			PreparedStatement stmt = con.prepareStatement(
					"INSERT INTO PATIENTCASE (PATIENTID, DISEASE, MEDICATION) Values(?,?,?) ;");

			stmt.setInt(1, r.getPatientId());
			stmt.setString(2, r.getDisease());
			stmt.setString(3, r.getMedication());
			

			return stmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e);
			return -1;
		}
	}

	@Override
	public int edit(String key, String value, Record record) {
		PatientCaseRecord r = (PatientCaseRecord) record;
		try {
//			int x = validate(record);
//			if (x != 1)
//				return x;
			PreparedStatement stmt = con.prepareStatement(
					"Update PATIENTCASE SET  DISEASE = ?, " + "MEDICATION = ? WHERE " + key + " = ? ;");

			stmt.setString(1, r.getDisease());
			stmt.setString(2, r.getMedication());
			stmt.setString(3, value);
			return stmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.toString());
			return -1;
		}
	}

	@Override
	public int delete(String key, String value) {
		try {
			PreparedStatement stmt = con.prepareStatement("DELETE FROM PATIENTCASE " + " WHERE " + key + " = ? ;");
			stmt.setString(1, value);
			return stmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.toString());
			return -1;
		}
	}

	@Override
	public ResultSet getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet search(String key, String value) {
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM PATIENTCASE " + " WHERE " + key + " = ? ;");
			stmt.setString(1, value);
			return (ResultSet) stmt.executeQuery();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public ResultSet getDoctorsPatient(int doctorId) {

		try {
			PreparedStatement stmt = con.prepareStatement("SELECT DISTINCT Patient.ID,Patient.NAME FROM APPOINTMENT "
					+ "JOIN Patient ON " + "APPOINTMENT.patientID = Patient.ID " + "WHERE DoctorId = ?" + " ;");

			stmt.setInt(1, doctorId);
			return (ResultSet) stmt.executeQuery();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

}
