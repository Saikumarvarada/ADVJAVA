package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Jdbc_App_6 
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
		System.out.println("Implementing Scrolling ResultSet====>READ_INLY\n");
		Connection con = connect();
		try 
		{
			// Statement
			// stm=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			Statement stm = con.createStatement(1004, 1007);
			ResultSet rs = stm.executeQuery("select*from emp");
			rs.afterLast();
			while (rs.previous())
			{
				System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " "
						+ rs.getString(4) + " " + rs.getString(5));
			}
			rs.last();
			System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4)
					+ " " + rs.getString(5) + "\n");
			rs.first();
			System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4)
					+ " " + rs.getString(5) + "\n");
			rs.beforeFirst();
			while (rs.next()) 
			{
				System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " "
						+ rs.getString(4) + " " + rs.getString(5));
			}
			rs.absolute(3);
			System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4)
					+ " " + rs.getString(5) + "\n");
			rs.absolute(-3);
			System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4)
					+ " " + rs.getString(5) + "\n");
			rs.relative(-3);
			System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4)
					+ " " + rs.getString(5) + "\n");
			rs.relative(1);
			System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4)
					+ " " + rs.getString(5) + "\n");

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	void meth2()
	{
		System.out.println("Implementing Scrollable ResultSet===>Update\n");
		Connection con = connect();
		{
			try
			{
				Statement stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				ResultSet rs = stm.executeQuery("select eid,efname,esal from emp");
				while (rs.next())
				{
					String id = rs.getString(1);
					if (id.equals("103")) 
					{
						rs.updateInt("esal", 90000);
						rs.updateRow();
					}
				}
				Statement stm2 = con.createStatement();
				ResultSet rs2 = stm2.executeQuery("select*from emp");
				while (rs2.next()) 
				{
					System.out.println(rs2.getString(1) + " " + rs2.getString(2) + " " + rs2.getString(3) + " "
							+ rs2.getString(4) + " " + rs2.getString(5));
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
	}

	void meth3()
	{
		Connection con = connect();
		try 
		{
			PreparedStatement pstm = con.prepareStatement("select eid,efname,esal from emp", ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = pstm.executeQuery();
			System.out.println("enter your eid");
			String Eid = sc.next();
			while (rs.next())
			{
				String id = rs.getString(1);
				if (id.equals(Eid)) 
				{
					System.out.println("enter your modify esal");
					int Esal = sc.nextInt();
					rs.updateInt("Esal", Esal);
					rs.updateRow();

					ResultSet rs1 = pstm.executeQuery("select*from emp");
					while (rs1.next())
					{
						System.out.println(rs1.getString(1) + " " + rs1.getString(2) + " " + rs1.getString(3) + " "
								+ rs1.getString(4) + " " + rs1.getString(5) + "\n");
					}
					break;
				}
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args) 
	{
		Jdbc_App_6 obj = new Jdbc_App_6();
		// obj.meth1();
		// obj.meth2();
		obj.meth3();
	}
}
