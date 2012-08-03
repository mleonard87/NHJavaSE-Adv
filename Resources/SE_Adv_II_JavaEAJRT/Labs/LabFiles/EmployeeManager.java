/*
 * Created on May 31, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.mycom.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * @author bibhas
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class EmployeeManager {
	private Connection open() throws Exception {
		Context ctx = new InitialContext();
		
		DataSource ds = (DataSource) ctx.lookup("jdbc/EmployeeDB");
		
		return ds.getConnection();
	}
	/**
	 * Return a list of all employees. The ArrayList
	 * is fileld with EmployeeDTO objects.
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<EmployeeDTO> getAllEmployees() throws Exception {
		Connection con = null;
		ArrayList<EmployeeDTO> list = new ArrayList<EmployeeDTO>();
		
		try {
			con = open();
			PreparedStatement ps = 
				con.prepareStatement(
				"select * from employee");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				EmployeeDTO e = loadEmployee(rs);
				
				list.add(e);
			}
			
			rs.close();
			ps.close();
		} finally {
			if (con != null) {
				con.close();
			}
		}
		return list;
	}
	
	/**
	 * Given an employee number, returns an EmployeeDTO object.
	 * 
	 * @param empNo - The employee number.
	 * @return The EmployeeDTO object or null if employee can not be found.
	 * @throws Exception
	 */
	public EmployeeDTO findEmployee(String empNo) throws Exception {
		Connection con = null;
		EmployeeDTO e = null;
		
		try {
			con = open();
			PreparedStatement ps = 
				con.prepareStatement(
				"select * from employee where EMPNO=?");
			ps.setString(1, empNo);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				e = loadEmployee(rs);
			}
			
			rs.close();
			ps.close();
		} finally {
			if (con != null) {
				con.close();
			}
		}
		
		return e;
	}
	/**
	 * Updates an employee record.
	 * 
	 * @param e
	 * @throws Exception
	 */
	public void updateEmployee(EmployeeDTO e) throws Exception {
		Connection con = null;
		
		try {
			con = open();
			PreparedStatement ps = 
				con.prepareStatement(
				"update employee set FIRST_NAME=?, LAST_NAME=?, WORKDEPT=?, SALARY=? where EMPNO=?");
			ps.setString(1, e.getFirstName());
			ps.setString(2, e.getLastName());
			ps.setString(3, e.getDepartmentNo());
			ps.setDouble(4, e.getSalary());
			ps.setString(5, e.getEmployeeNumber());
			
			ps.executeUpdate();
			ps.close();
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}
	
	private EmployeeDTO loadEmployee(ResultSet rs) throws Exception {
		EmployeeDTO e = new EmployeeDTO();
		
		e.setFirstName(rs.getString("FIRST_NAME"));
		e.setLastName(rs.getString("LAST_NAME"));
		e.setEmployeeNumber(rs.getString("EMPNO"));
		e.setDepartmentNo(rs.getString("WORKDEPT"));
		e.setSalary(rs.getDouble("SALARY"));
		
		return e;
	}
	/**
	 * Returns an ArrayList of all departments.
	 * 
	 * @return The ArrayList contains DepartmentDTO objects.
	 * @throws Exception
	 */
	public ArrayList<DepartmentDTO> getAllDepartments() throws Exception {
		Connection con = null;
		ArrayList<DepartmentDTO> list = new ArrayList<DepartmentDTO>();
		
		try {
			con = open();
			PreparedStatement ps = 
				con.prepareStatement(
				"select * from department");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				DepartmentDTO d = new DepartmentDTO();
				
				d.setDepartmentNo(rs.getString("DEPTNO"));
				d.setName(rs.getString("DEPTNAME"));
				d.setLocation(rs.getString("LOCATION"));
				
				list.add(d);
			}
			
			rs.close();
			ps.close();
		} finally {
			if (con != null) {
				con.close();
			}
		}
		return list;
	}
	/**
	 * Adds a new employee.
	 * @param e The Employee object containing information about the new employee.
	 * @throws Exception
	 */
	public void addEmployee(EmployeeDTO e) throws Exception {
		Connection con = null;
		
		try {
			con = open();
			PreparedStatement ps = 
				con.prepareStatement(
				"insert into employee (FIRST_NAME, LAST_NAME, WORKDEPT, SALARY, EMPNO) values (?, ?, ?, ?, ?)");
			ps.setString(1, e.getFirstName());
			ps.setString(2, e.getLastName());
			ps.setString(3, e.getDepartmentNo());
			ps.setDouble(4, e.getSalary());
			ps.setString(5, e.getEmployeeNumber());
			
			ps.executeUpdate();
			ps.close();
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}
}
