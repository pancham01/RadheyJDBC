package com.explore.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.explore.entity.Employee;

public class EmployeeDaoImpl implements EmployeeDao {

	private static final String INSERT_QUERY = "INSERT INTO EMPLOYEE VALUES (%d,'%s','%s',%d)";
	private static final String INSERT_QUERY_BY_PS = "INSERT INTO EMPLOYEE VALUES (?,?,?,?)";
	private static final String UPDATE_QUERY = "UPDATE EMPLOYEE SET NAME = '%s', GENDER = '%s', SALARY = %d WHERE ID = %d";
	private static final String DELETE_QUERY = "DELETE FROM EMPLOYEE  WHERE ID = %d";
	private static final String SELECT_QUERY = "SELECT * FROM EMPLOYEE";
	private static final String SELECT_EMP_BY_NAME_QUERY = "SELECT * FROM EMPLOYEE WHERE NAME = '%s'";
//	private static final String SELECT_EMP_BY_NAME_QUERY = "SELECT * FROM EMPLOYEE WHERE NAME = 'abc' or '1 = 1'";

	private static Connection conn = null;

	static {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testDb", "root", "root");
//			Driver d = new Driver();
//			Properties prop = new Properties();
//			prop.put("user", "root");
//			prop.put("password", "root");
//			conn = d.connect("jdbc:mysql://localhost:3306/testDb",prop);
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
		
		try (Statement statement = conn.createStatement()) {

			statement.executeUpdate(String.format(DELETE_QUERY, id));

		} catch (SQLException exception) {
			exception.printStackTrace();
		}

		System.out.println("EmployeeDaoImpl.delete()");

	}

	@Override
	public List<Employee> getAllEmp() {
		List<Employee> list = new ArrayList<Employee>();

		try (Statement statement = conn.createStatement()) {

			ResultSet rs = statement.executeQuery(SELECT_QUERY);

			while (rs.next()) {
				Employee employee = new Employee();

				employee.setId(rs.getInt(1));
				employee.setName(rs.getString(2));
				employee.setGender(rs.getString(3));
				employee.setSalary(rs.getInt(4));
				list.add(employee);
			}

		} catch (SQLException exception) {
			exception.printStackTrace();
		}

		return list;
	}

	@Override
	public void printAllEmp() {

		try (Statement statement = conn.createStatement()) {

			ResultSet rs = statement.executeQuery(SELECT_QUERY);

			while (rs.next()) {
				System.out.println("ID = " + rs.getInt(1) + "\t NAME = " + rs.getString(2) + "\t Gender = "
						+ rs.getString(3) + "\t Salary = " + rs.getInt(4));
			}

		} catch (SQLException exception) {
			exception.printStackTrace();
		}

	}

	@Override
	public void getEmpByName(String name) {

		try (Statement statement = conn.createStatement()) {

			ResultSet rs = statement.executeQuery(String.format(SELECT_EMP_BY_NAME_QUERY, name));

			while (rs.next()) {
				System.out.println("ID = " + rs.getInt(1) + "\t NAME = " + rs.getString(2) + "\t Gender = "
						+ rs.getString(3) + "\t Salary = " + rs.getInt(4));
			}

		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}

	@Override
	public void saveEmpByPs(Employee e) {

		try (PreparedStatement ps = conn.prepareStatement(INSERT_QUERY_BY_PS)) {

			ps.setInt(1, e.getId());
			ps.setString(2, e.getName());
			ps.setString(3, e.getGender());
			ps.setInt(4, e.getSalary());
			ps.executeUpdate();

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		System.out.println("EmployeeDaoImpl.saveEmpByPs()");
	}

	@Override
	public void insertBatch() throws SQLException {

		conn.setAutoCommit(false);
		PreparedStatement ps = conn.prepareStatement(INSERT_QUERY_BY_PS);

		for (int i = 1; i <= 100; i++) {
			ps.setInt(1, 16 + i);
			ps.setString(2, "Guest " + i);
			ps.setString(3, "M");
			ps.setInt(4, 50_000);
			ps.addBatch();

			if (i % 100 == 0) {
				ps.executeBatch();
				conn.commit();
			}

		}
	}

}
