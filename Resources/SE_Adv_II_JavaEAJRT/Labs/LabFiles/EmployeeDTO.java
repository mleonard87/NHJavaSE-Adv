/*
 * Created on May 31, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.mycom.model;

/**
 * @author bibhas
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class EmployeeDTO {
	/**
	 * @return Returns the departmentNo.
	 */
	public String getDepartmentNo() {
		return departmentNo;
	}
	/**
	 * @param departmentNo The departmentNo to set.
	 */
	public void setDepartmentNo(String departmentNo) {
		this.departmentNo = departmentNo;
	}
	/**
	 * @return Returns the employeeNumber.
	 */
	public String getEmployeeNumber() {
		return employeeNumber;
	}
	/**
	 * @param employeeNumber The employeeNumber to set.
	 */
	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}
	/**
	 * @return Returns the firstName.
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName The firstName to set.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return Returns the lastName.
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName The lastName to set.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return Returns the salary.
	 */
	public double getSalary() {
		return salary;
	}
	/**
	 * @param salary The salary to set.
	 */
	public void setSalary(double salary) {
		this.salary = salary;
	}
	String employeeNumber;
	String firstName;
	String lastName;
	double salary;
	String departmentNo;
}
