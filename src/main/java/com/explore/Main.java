package com.explore;

import java.sql.SQLException;
import java.util.List;

import com.explore.dao.EmployeeDao;
import com.explore.dao.EmployeeDaoImpl;
import com.explore.entity.Employee;

public class Main {
	
	

	public static void main(String[] args) throws SQLException {
		
		EmployeeDao edao = new EmployeeDaoImpl();

		Employee ankit = new Employee(16 , "Akash", "Male", 77777);
		
//		edao.saveEmp(ankit);
		
//		edao.updateEmp(ankit);
		
		
//		edao.printAllEmp();
		
//		List<Employee> allEmp = edao.getAllEmp();
//		
//		for(Employee e : allEmp)
//		{
//			System.out.println(e);
//		}
		
		
//		edao.getEmpByName("abc' or '1 = 1");
		
//		edao.saveEmpByPs(ankit);
		
		edao.deleteEmp(116);
		
		
		edao.insertBatch();
		
		edao.printAllEmp();

		
	}

}
