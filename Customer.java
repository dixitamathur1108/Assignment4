package com.database.files;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Customer {
	Connection con;
	Scanner sc=new Scanner(System.in);
	PreparedStatement ps;
	ResultSet res;
	
	public Customer()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/d11","dixita","45924@Mysql98291");
			System.out.println("Database Connected....");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void Insert()
	{
		System.out.println();
		System.out.println("Enter customer id:");
		int id=sc.nextInt();
		System.out.println("Enter customer first name:");
		String fname=sc.next();
		System.out.println("Enter customer last name:");
		String lname=sc.next();
		System.out.println("Enter customer address:");
		String address=sc.next();
		System.out.println("Enter customer email:");
		String email=sc.next();
		
		try {
			ps=con.prepareStatement("insert into customer values(?,?,?,?,?)");
			ps.setInt(1, id);
			ps.setString(2, fname);
			ps.setString(3, lname);
			ps.setString(4, address);
			ps.setString(5, email);
			int r=ps.executeUpdate();
			if(r>0)
			{
				System.out.println("Customer added");
			}
			else
				System.out.println("There has been a problem!! Try again");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println();
		
	}
	
	public void Delete()
	{
		try {
			System.out.println();
			System.out.println("Enter the customer id");
			int id=sc.nextInt();
			ps=con.prepareStatement("delete from customer where customer_id=?");
			ps.setInt(1, id);
			int r=ps.executeUpdate();
			if(r>0)
				System.out.println("Customer Deleted");
			else
				System.out.println("There has been a problem!!Try again");
				
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		System.out.println();
		
	}
	
	public void Update()
	{
		try {
			System.out.println();
			System.out.println("Enter customer id:");
			int id=sc.nextInt();
			System.out.println("Enter customer first name:");
			String fname=sc.next();
			System.out.println("Enter customer last name:");
			String lname=sc.next();
			System.out.println("Enter customer address:");
			String address=sc.next();
			System.out.println("Enter customer email:");
			String email=sc.next();
			ps=con.prepareStatement("update customer set customer_fname=?,customer_lname=?,address=?,email=? where customer_id=?");
			ps.setString(1, fname);
			ps.setString(2, lname);
			ps.setString(3, address);
			ps.setString(4, email);
			ps.setInt(5, id);
			int r=ps.executeUpdate();
			if(r>0)
			{
				System.out.println("Customer updated");
			}
			else
				System.out.println("There has been a problem!! Try again");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		System.out.println();
	}
	
	public void Print()
	{
		System.out.println();
		try 
		{
			// Creates query object for database connected through con object
			ps=con.prepareStatement("Select * from customer");
			//Executes SQL Select Query and returns data as ResultSet Object
			res=ps.executeQuery();
			while(res.next())
			{
				System.out.println(res.getInt(1));
				System.out.println(res.getString(2));
				System.out.println(res.getString(3));
				System.out.println(res.getString(4));
				System.out.println(res.getString(5));
			}
		} 
		catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Customer c=new Customer();
		String ch="y";
		String option=null;
		while(ch.equals("y"))
		{
			System.out.println("Select the operation you want to perform:(insert/delete/update/print/exit");
			option=c.sc.next();
			if(option.equals("insert"))
				c.Insert();
			else if(option.equals("delete"))
				c.Delete();
			else if(option.equals("update"))
				c.Update();
			else if(option.equals("print"))
				c.Print();
			else if(option.equals("exit"))
				{System.out.println("Thank You");
				break;}
			System.out.println("Do you want to continue(y/n)");
			ch=c.sc.next();
		}
		

	}

}
