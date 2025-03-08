package com.pack1;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class Jdbc_App_9
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
System.out.println("Implementing Callable Statement ====>InOut parameters");
		
		Connection con=connect();
		try
		{
		CallableStatement cstmt=con.prepareCall("{call ReteriveEmpData(?,?,?,?,?)}");
		System.out.println("Enter empid");
		String eid=sc.nextLine();
		cstmt.setString(1,eid);
		cstmt.registerOutParameter(2,Types.VARCHAR);
		cstmt.registerOutParameter(3,Types.VARCHAR);
		cstmt.registerOutParameter(4,Types.INTEGER);
		cstmt.registerOutParameter(5,Types.FLOAT);
		
		cstmt.execute();
		System.out.println("Emp id:"+eid);
		System.out.println("Emp name:"+cstmt.getString(2));
		System.out.println("Emp desg:"+cstmt.getString(3));
		System.out.println("Emp bsal:"+cstmt.getInt(4));
		System.out.println("Emp tsal:"+cstmt.getFloat(5));
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public static void main(String[] args) 
	{
	Jdbc_App_9 obj=new Jdbc_App_9();
	obj.meth1();
	}
}
