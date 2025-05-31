package com.explore.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.explore.entity.Employee;

public class EmployeeDaoImpl implements EmployeeDao {

	private static final String INSERT_QUERY = "INSERT INTO EMPLOYEE VALUES (%d,'%s','%s',%d)";
	private static final String UPDATE_QUERY = "UPDATE EMPLOYEE SET NAME = '%s', GENDER = '%s', SALARY = %d WHERE ID = %d";

	private static Connection conn = null;

	static {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testDb", "root", "root");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void saveEmp(Employee e) throws SQLException {
		Statement statement = conn.createStatement();

		statement.executeUpdate(String.format(INSERT_QUERY, e.getId(), e.getName(), e.getGender(), e.getSalary()));
		statement.close();
		System.out.println("EmployeeDaoImpl.saveEmp()");
	}

	@Override
	public void updateEmp(Employee e) {

		try (Statement statement = conn.createStatement()) {

			statement.executeUpdate(String.format(UPDATE_QUERY, e.getName(), e.getGender(), e.getSalary(), e.getId()));

		} catch (SQLException exception) {
			exception.printStackTrace();
		}

		System.out.println("EmployeeDaoImpl.updateEmp()");

	}

	@Override
	public void deleteEmp(int id) {

	}

	@Override
	public List<Employee> getAllEmp() {
		List<Employee> list = new ArrayList<Employee>();
		
		
		
		list.add(null);
		
		
		return list;
	}

	@Override
	public void printAllEmp() {
		// TODO Auto-generated method stub

	}

}
