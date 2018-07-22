package com.assignment.programs;

import com.assignment.dao.DaoException;
import com.assignment.dao.EmployeeDao;
import com.assignment.dao.impl.EmployeeDaoJdbcImpl;
import com.assignment.entity.Employee;

public class Test {
	public static void main(String[] args) throws DaoException {
		EmployeeDao e = new EmployeeDaoJdbcImpl();
		Employee e1 = new Employee();
		e1.setEmpId(3);
		e1.setEmpName("Ramesh");
		e1.setEmail("manish@slk");
		e1.setCity("Kannur");
		e1.setPhone("7789657758");
		//e.addEmployee(e1);
		//e.deleteEmployee(2);
		//e.updateEmployee(e1);
		//System.out.println(e.getEmployeeById(1));
		System.out.println(e.getAll());
	}
}
