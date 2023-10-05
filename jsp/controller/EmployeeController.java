package com.jsp.controller;
import com.jsp.dao.*;
import com.jsp.dto.EmployeeDto;

import exception.EmployeeException;

import java.util.*;

public class EmployeeController {
	public static void main(String[] args)throws Exception
	{
		while(true)
		{
			Scanner sc = new Scanner(System.in);
			EmployeeDao dao = new EmployeeDao();
			EmployeeDto dto = new EmployeeDto();
			System.out.println("1.Create/Add an employee");
			System.out.println("2.Read an employee");
			System.out.println("3.Read All employee");
			System.out.println("4.Update info of an Employee");
			System.out.println("5.Delete an employee from DB");
			System.out.println("6.Exit ");
			int opt = sc.nextInt();
			
			switch(opt)
			{ 
			case 1:
				System.out.println("*** Enter Employee details***");
				System.out.print("Enter ID: ");
				dto.setId(sc.nextInt());
				System.out.print("Enter Name: ");
				dto.setName(sc.next()); 
				System.out.print("Enter Designation: ");
				dto.setDesignation(sc.next());
				System.out.print("Enter Salary: ");
				dto.setSalary(sc.nextDouble());
				try {
					dao.create(dto);
				}catch(Exception e) {
					e.printStackTrace();
				}
				break;
			case 2://Read the employee
				System.out.print("Enter ID: ");
				dto.setId(sc.nextInt());
				try {
					dao.readById(dto); 
				}catch(EmployeeException e)
				{
					System.out.println(e.getMsg());
				}
				break;
			case 3:
				dao.readAll();
				break;
			case 4:
				System.out.println("1. To Update Name");
				System.out.println("2. To Update designation");
				int o = sc.nextInt();
				if(o == 1)
				{
					System.out.println("Enter ID to Update: ");
					dto.setId(sc.nextInt());
					System.out.print("Enter new name:");
					dto.setName(sc.next());
					dao.update(dto, o);
				}
				else
				{
					System.out.println("Enter ID to Update: ");
					dto.setId(sc.nextInt());
					System.out.print("Enter new Designation:");
					dto.setDesignation(sc.next());
					dao.update(dto, o);
				}
				
				break;
			case 5:
				System.out.println("Enter ID to Delete: ");
				dto.setId(sc.nextInt());
				dao.delete(dto);
				break;
			case 6:
				return;
			default:
				System.out.println("Invalid Option");
			}
		}
	}
}
