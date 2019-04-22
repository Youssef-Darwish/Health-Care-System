package model.records;

import java.util.*;

public class PatientRecord implements Record {

	private String name;
	private String gender;
	private String telephone;
	private int id;
	private Date registrationDate;

	public PatientRecord(int id, String name, String gender, String telephone, Date regDate) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.telephone = telephone;
		this.registrationDate = regDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
}
