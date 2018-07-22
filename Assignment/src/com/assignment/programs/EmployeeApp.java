package com.assignment.programs;

import java.util.ArrayList;
import java.util.Scanner;

import com.assignment.dao.DaoException;
import com.assignment.dao.EmployeeDao;
import com.assignment.dao.impl.EmployeeDaoJdbcImpl;
import com.assignment.entity.Employee;
import com.assignment.util.KeyboardUtil;

public class EmployeeApp {
	EmployeeDao dao = new EmployeeDaoJdbcImpl();
	
	private static int option;
	@SuppressWarnings("resource")
	public static void main(String[] args) throws DaoException {
		while(true) {
		EmployeeApp app = new EmployeeApp();
		System.out.println("Choose an option: ");
		System.out.println("1. Add an employee");
		System.out.println("2. Delete an employee");
		System.out.println("3. Edit an employee");
		System.out.println("4. View all employees");
		System.out.println("5. Get employe by id");
		System.out.println("6. Get employee by city");
		System.out.println("0. Exit the app");
		
		Scanner scan = new Scanner(System.in);
		option = scan.nextInt();
		switch(option) {
		case 1 : app.addEmployee();
		break;
		case 2 : app.deleteEmployee();
		break;
		case 3 : app.editEmployee();
		break;
		case 4 : app.viewAllEmployees();
		break;
		case 5 : app.getById();
		break;
		case 6 : app.getByCity();
		break;
		case 0 : return;
		}
		}
	}
	
	
	@SuppressWarnings("resource")
	private void getByCity() throws DaoException {
		System.out.println("Enter the city name:");
		Scanner scan = new Scanner(System.in);
		ArrayList<Employee> list = dao.getEmployeeByCity(scan.nextLine());
		for(Employee e : list) {
			System.out.println("id \t: "+e.getEmpId());
			System.out.println("Name \t: "+e.getEmpName());
			System.out.println("Email \t: "+e.getEmail());
			System.out.println("City \t: "+e.getCity());
			System.out.println("Phone \t: "+e.getPhone());
			System.out.println("----------------------------");
						
		}
	}
	
	
	@SuppressWarnings("resource")
	private void getById() throws DaoException {
		System.out.println("Enter the id of the employee for details :");
		Scanner scan = new Scanner(System.in);
		Employee e= dao.getEmployeeById(scan.nextInt());
		System.out.println("id \t: "+e.getEmpId());
		System.out.println("Name \t: "+e.getEmpName());
		System.out.println("Email \t: "+e.getEmail());
		System.out.println("City \t: "+e.getCity());
		System.out.println("Phone \t: "+e.getPhone());
		System.out.println("----------------------------");
		
	}
	private void viewAllEmployees() throws DaoException {
		ArrayList<Employee> list = dao.getAll();
		for(Employee e : list) {
			System.out.println("id \t: "+e.getEmpId());
			System.out.println("Name \t: "+e.getEmpName());
			System.out.println("Email \t: "+e.getEmail());
			System.out.println("City \t: "+e.getCity());
			System.out.println("Phone \t: "+e.getPhone());
			System.out.println("----------------------------");
						
		}
		
	}
	
	
	@SuppressWarnings("resource")
	private void editEmployee() throws DaoException {
		int id;
		Employee e = new Employee();
		System.out.println("Enter the id of the employee to be edited :");
		Scanner scan = new Scanner(System.in);
		id = scan.nextInt();
		Employee e1 = dao.getEmployeeById(id);
		e.setEmpId(id);
		e.setEmpName(KeyboardUtil.getUpdateString("Name", e1.getEmpName()));
		e.setEmail(KeyboardUtil.getUpdateString("Email", e1.getEmail()));
		e.setCity(KeyboardUtil.getUpdateString("City", e1.getCity()));
		e.setPhone(KeyboardUtil.getUpdateString("Phone", e1.getPhone()));
		dao.updateEmployee(e);
		System.out.println("Successfully updated \n");
				
	}
	
	
	@SuppressWarnings("resource")
	private void deleteEmployee() throws DaoException {
		int id;
		System.out.println("Enter the id of the employee to be deleted :");
		Scanner scan = new Scanner(System.in);
		id = scan.nextInt();
		dao.deleteEmployee(id);
		System.out.println("Deleted succesfully \n");
			
	}
	
	
	private void addEmployee() throws DaoException {
		Employee e = new Employee();
		System.out.println("Enter the details of the employee:");
		e.setEmpName(KeyboardUtil.getString("Name"));
		e.setEmail(KeyboardUtil.getString("Email"));
		e.setCity(KeyboardUtil.getString("City"));
		e.setPhone(KeyboardUtil.getString("Phone"));
		dao.addEmployee(e);
		System.out.println("Successfully added! \n");
	}
}
