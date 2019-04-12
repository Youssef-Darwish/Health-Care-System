package model.records;

public class StaffRecord implements Record {

	private int id;
	private String name;
	private String role;
	private String telephone;
	private double salary;

	private String password;

	public StaffRecord(String name, String role, String telephone, double salary, String pass) {
		this.name = name;
		this.role = role;
		this.telephone = telephone;
		this.salary = salary;
		this.password = pass;

	}
	
	public StaffRecord(int id, String name, String role, String telephone, double salary) {
		this.id = id;
		this.name = name;
		this.role = role;
		this.telephone = telephone;
		this.salary = salary;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
