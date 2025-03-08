package com.pack1;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Jdbc_App_8
{
	String driver = "oracle.jdbc.OracleDriver";
	String DBurl = "jdbc:oracle:thin:@localhost:1521:orcl";
	String DBuname = "system";
	String DBpwd = "TIGER";
	Scanner sc = new Scanner(System.in);

	Connection connect()
	{
		Connection con = null;
		try
		{
			Class.forName(driver);
			con = DriverManager.getConnection(DBurl, DBuname, DBpwd);
			System.out.println("connection created");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
	void meth1()
	{
		System.out.println("Implementing Callable Statement");
		
		Connection con=connect();
		try
		{
		CallableStatement cstm=con.prepareCall("{call InsertEmpData(?,?,?,?,?)}");
		
		System.out.println("Enter empId");
		String e_id=sc.nextLine();
		
		System.out.println("Enter empName");
		String e_name=sc.nextLine();
		
		System.out.println("Enter empDesg");
		String e_desg=sc.nextLine();
		
		System.out.println("Enter empSal");
		int e_bsal=Integer.parseInt(sc.nextLine());
		
		float e_tsal=e_bsal +(0.35f*e_bsal)+(0.15f*e_bsal);
		
		cstm.setString(1, e_id);
		cstm.setString(2, e_name);
		cstm.setString(3, e_desg);
		cstm.setInt(4, e_bsal);
		cstm.setFloat(5, e_tsal);
			
		if(cstm.execute())
		{
			System.out.println("Data not Inserted");
		}
		else
		{
			System.out.println("Data Inserted");
		}
		}
		catch( SQLException e)
		{
			e.printStackTrace();
		}
	}
	public static void main(String[] args) 
	{
		Jdbc_App_8 obj=new Jdbc_App_8();
		obj.meth1();
	}
}
