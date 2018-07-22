package com.assignment.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.assignment.dao.DaoException;
import com.assignment.dao.EmployeeDao;
import com.assignment.entity.Employee;
import com.assignment.util.DbUtil;

public class EmployeeDaoJdbcImpl implements EmployeeDao {

	@Override
	public void addEmployee(Employee employee) throws DaoException {
		String sql = "insert into employee values(null,?,?,?,?)";
		try(Connection conn = DbUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);){
			stmt.setString(1, employee.getEmpName());
			stmt.setString(2, employee.getEmail());
			stmt.setString(3, employee.getCity());
			stmt.setString(4, employee.getPhone());
			
			stmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteEmployee(Integer id) throws DaoException {
		String sql = "delete from employee where empid=?";
		
		try(Connection conn = DbUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);){
			stmt.setInt(1, id);
			
			stmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateEmployee(Employee employee) throws DaoException {
		String sql = "update employee set empname=?, email=?, city=?, phone=? where empid=?";
		try(Connection conn = DbUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);){
			stmt.setString(1, employee.getEmpName());
			stmt.setString(2, employee.getEmail());
			stmt.setString(3, employee.getCity());
			stmt.setString(4, employee.getPhone());
			stmt.setInt(5, employee.getEmpId());
			
			stmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Employee getEmployeeById(Integer id) throws DaoException {
		String sql = "select * from employee where empid=?";
		Employee e = new Employee();
		try(Connection conn = DbUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);){
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				e.setEmpId(rs.getInt(1));
				e.setEmpName(rs.getString(2));
				e.setEmail(rs.getString(3));
				e.setCity(rs.getString(4));
				e.setPhone(rs.getString(5));				
			}
			
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return e;
	}

	@Override
	public ArrayList<Employee> getAll() throws DaoException {
		
		
		String sql = "select * from employee";
		try(Connection conn = DbUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery(sql);){
			ArrayList<Employee> list = new ArrayList<Employee>();
			
			while(rs.next()) {
				Employee e = new Employee();
				e.setEmpId(rs.getInt(1));
				e.setEmpName(rs.getString(2));
				e.setEmail(rs.getString(3));
				e.setCity(rs.getString(4));
				e.setPhone(rs.getString(5));
				list.add(e);
			}
			return list;
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
		
		
	}

	@Override
	public ArrayList<Employee> getEmployeeByCity(String city) throws DaoException {
		String sql = "select * from employee where city=?";
		try(Connection conn = DbUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				){
			stmt.setString(1, city);
			ResultSet rs = stmt.executeQuery();
			ArrayList<Employee> list = new ArrayList<Employee>();
			while(rs.next()) {
				Employee e = new Employee();
				e.setEmpId(rs.getInt(1));
				e.setEmpName(rs.getString(2));
				e.setEmail(rs.getString(3));
				e.setCity(rs.getString(4));
				e.setPhone(rs.getString(5));
				list.add(e);
				
			}
			return list;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
