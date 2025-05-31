package com.explore;

import java.sql.SQLException;

import com.explore.dao.EmployeeDao;
import com.explore.dao.EmployeeDaoImpl;
import com.explore.entity.Employee;

public class Main {

	public static void main(String[] args) throws SQLException {
		
		EmployeeDao edao = new EmployeeDaoImpl();

		Employee ankit = new Employee(12, "Ankit", "N/A", 77777);
		
//		edao.saveEmp(shiv);
		
		edao.updateEmp(ankit);

		
	}

}
