package com.assignment.dao;

import java.util.ArrayList;

import com.assignment.entity.Employee;

public interface EmployeeDao {
	public void addEmployee(Employee employee) throws DaoException;
	public void deleteEmployee(Integer id) throws DaoException;
	public void updateEmployee(Employee employee) throws DaoException;
	public Employee getEmployeeById(Integer id) throws DaoException;
	public ArrayList<Employee> getAll() throws DaoException;
	public ArrayList<Employee> getEmployeeByCity(String city) throws DaoException;
}
