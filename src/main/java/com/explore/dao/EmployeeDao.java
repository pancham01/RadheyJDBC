package com.explore.dao;

import java.sql.SQLException;

import com.explore.entity.Employee;

public interface EmployeeDao {

	public void saveEmp(Employee e)throws SQLException;

	public void updateEmp(Employee e);

	public void deleteEmp(int id);

	public void getAllEmp();

}
