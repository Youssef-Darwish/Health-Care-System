package model.records;

import java.util.Date;

public class AppointmentRecord implements Record {

	private int patientId;
	private int doctorId;
	private Date appointmentDate;
	private String hour;

	public AppointmentRecord(int patientId, int doctorId, String hour, Date date) {
		this.patientId = patientId;
		this.doctorId = doctorId;
		this.hour = hour;
		this.appointmentDate = date;
	}

	public AppointmentRecord(int appId,int patientId, int doctorId, String hour, Date date) {
		this.patientId = patientId;
		this.doctorId = doctorId;
		this.hour = hour;
		this.appointmentDate = date;
		this.appointmentId = appId;
	}

	public int getPatientId() {
		return patientId;
	}
	

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public int getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}

	private int appointmentId;

}
