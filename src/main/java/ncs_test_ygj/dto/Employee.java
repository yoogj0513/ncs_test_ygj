package ncs_test_ygj.dto;

import java.util.Date;

public class Employee {
	private String empNo;
	private String empName;
	private Title title;
	private int salary;
	private int gender;
	private Department dno;
	private Date hireDate;

	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public Employee(String empNo) {
		this.empNo = empNo;
	}

	public Employee(String empNo, String empName, Title title, int salary, int gender, Department dno, Date hireDate) {
		this.empNo = empNo;
		this.empName = empName;
		this.title = title;
		this.salary = salary;
		this.gender = gender;
		this.dno = dno;
		this.hireDate = hireDate;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public Department getDno() {
		return dno;
	}

	public void setDno(Department dno) {
		this.dno = dno;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((empNo == null) ? 0 : empNo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (empNo == null) {
			if (other.empNo != null)
				return false;
		} else if (!empNo.equals(other.empNo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("%s, %s, %s, %s, %s, %s, %s]", empNo, empName, title, salary, gender, dno, hireDate);
	}

}
