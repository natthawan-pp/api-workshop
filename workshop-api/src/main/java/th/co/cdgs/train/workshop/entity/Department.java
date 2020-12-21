package th.co.cdgs.train.workshop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Department")
public class Department implements java.io.Serializable {

	private String departmentCode;
	private String departmentName;

	public Department() {
	}

	public Department(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public Department(String departmentCode, String departmentName) {
		this.departmentCode = departmentCode;
		this.departmentName = departmentName;
	}

	@Id
	@Column(name = "department_code", unique = true, nullable = false, length = 3)
	public String getDepartmentCode() {
		return this.departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	@Column(name = "department_name", length = 50)
	public String getDepartmentName() {
		return this.departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
}
