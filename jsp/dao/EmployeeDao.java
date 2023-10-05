package com.jsp.dao;
import java.util.*; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jsp.configuration.*;
import com.jsp.dto.*;
import exception.EmployeeException;
public class EmployeeDao
{
	Scanner sc = new Scanner(System.in);
	Configuration config = new Configuration();
	public void create(EmployeeDto dto) throws Exception
	{	
		
			PreparedStatement prep = config.configure().prepareStatement("insert into employee values (?, ?, ?, ?)");
			prep.setInt(1, dto.getId());
			prep.setString(2, dto.getName());	
			prep.setString(3, dto.getDesignation());
			prep.setDouble(4, dto.getSalary());
			boolean b = prep.execute();
			System.out.println(b); 
			System.out.println(dto.getName() + " Successfully added");
			prep.close();
	}
	
	public void readById(EmployeeDto dto) throws Exception 
	{
		PreparedStatement prep = config.configure().prepareStatement("select * from employee where id=?");
		prep.setInt(1, dto.getId());
		ResultSet res = prep.executeQuery();
		if(res.next())
		{
			System.out.println("ID: "+res.getInt(1));
			System.out.println("Name: "+ res.getString(2));
			System.out.println("Designation: "+ res.getString(3));
			System.out.println("Salary: "+res.getInt(4));
		}
			else {
			throw new EmployeeException("BSDK Employee ye nahi h be");
		}
		System.out.println("----------------------------------");
		res.close();
		prep.close();
	}
	public void readAll() throws Exception
	{
		PreparedStatement prep = config.configure().prepareStatement("select * from employee");
		ResultSet res = prep.executeQuery();
		while(res.next())
		{
			System.out.println("ID: "+res.getInt(1));
			System.out.println("Name: "+ res.getString(2));
			System.out.println("Designation: "+ res.getString(3));
			System.out.println("Salary: "+res.getInt(4));
			System.out.println("----------------------------------");
		}
		res.close();
		prep.close();
	}
	
	public void update(EmployeeDto dto, int O) throws ClassNotFoundException, SQLException, EmployeeException
	{
				if(O==1)
				{
					PreparedStatement prep = config.configure().prepareStatement("update employee set name=? where id=?");
					prep.setString(1, dto.getName());
					prep.setInt(2, dto.getId());
					int i = prep.executeUpdate();
					System.out.println("Value = "+i);
					prep.close();
				}else {
					PreparedStatement prep = config.configure().prepareStatement("update employee set desig=? where id=?");
					prep.setString(1, dto.getDesignation());
					prep.setInt(2, dto.getId());
					int i = prep.executeUpdate();
					System.out.println("Value = "+i);
					System.out.println("Designation for "+dto.getId()+" is Upadated");
					prep.close();
				}
	}
	
	public void delete(EmployeeDto dto) throws ClassNotFoundException, SQLException, EmployeeException 
	{
			PreparedStatement p = config.configure().prepareStatement("delete from employee where id = ?");
			p.setInt(1, dto.getId());
			int result = p.executeUpdate();
			if(result == 0)
				throw new EmployeeException("EmployeeNotFound");
			else
				System.out.println(dto.getId()+" deleted Successfully");
			p.close();
	}
}

