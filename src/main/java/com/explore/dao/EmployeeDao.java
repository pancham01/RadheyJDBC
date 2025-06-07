package com.explore.dao;

import java.sql.SQLException;
import java.util.List;

import com.explore.entity.Employee;

public interface EmployeeDao {

	public void saveEmp(Employee e) throws SQLException;

	public void saveEmpByPs(Employee e);

	public void updateEmp(Employee e);

	public void deleteEmp(int id);

	public void printAllEmp();

	public List<Employee> getAllEmp();

	public void getEmpByName(String name);

	public void insertBatch() throws SQLException;

}
