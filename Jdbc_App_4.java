package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Jdbc_App_4
{
String driver="oracle.jdbc.OracleDriver";
String DBurl="jdbc:oracle:thin:@localhost:1521:orcl";
String DBuname="system";
String DBpwd="TIGER";
String sqlQuery2="select*from ";
String slQuery3="delete from library where bookId='112'";
Scanner sc=new Scanner(System.in);
Connection connect()
{
	Connection con=null;
	try
	{
		Class.forName(driver);
		con=DriverManager.getConnection(DBurl,DBuname,DBpwd);
		System.out.println("connection created");
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return con;
}
void meth1()
{

	System.out.println("Reteriving the data from the database\n");
	Connection con=connect();	
	try
	{
		Class.forName(driver);
        Statement stm=con.createStatement();
        System.out.println("Enter your table");
        ResultSet rs=stm.executeQuery(sqlQuery2.concat(sc.nextLine()));
		while(rs.next())
		{
System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5));
		}
	  }
	catch(Exception e)
	{
	e.printStackTrace();
	}
}
void meth2()
{

	System.out.println("\nadding the data into the database\n");
	Connection con=connect();	
	try
	{
		Class.forName(driver);
        Statement stm=con.createStatement();
        System.out.println("Enter your bookId");
        int bookId=Integer.parseInt(sc.nextLine());
        
        System.out.println("Enter your bookName");
        String bookName=sc.nextLine();

        System.out.println("Enter your author");
        String author=sc.nextLine();

        System.out.println("Enter your Genere");
        String Genere=sc.nextLine();

        System.out.println("Enter your bookCost");
        String bookCost=sc.nextLine();
        int rowCount=stm.executeUpdate("insert into library values("+bookId+",'"+bookName+"','"+author+"','"+Genere+"',"+bookCost+")");
		if(rowCount==1)
		{
			System.out.println("data added");
			System.out.println("do you want to view the table(yes/no)");
			String choice=sc.nextLine().toLowerCase();
			if("yes".equals(choice))
				meth1();
			else
			{
				System.out.println("thank you see you soon");
				System.exit(0);
			}
		}
		else
		{
			System.out.println("data is not inerted");
		}
	  }
	catch(Exception e)
	{
	e.printStackTrace();
	}
}
void meth3()
{
	System.out.println("\ndeleting the data from the database\n");
	Connection con=connect();	
	try
	{
		Class.forName(driver);
        Statement stm=con.createStatement();
        System.out.println("Enter your bookId");
        int bookId=Integer.parseInt(sc.nextLine());
        int rowCount=stm.executeUpdate("delete from library where bookId='"+bookId+"'");
		if(rowCount==1)
		{
			System.out.println("data deleted");
			System.out.println("do you want to view the table(yes/no)");
			String choice=sc.nextLine().toLowerCase();
			if("yes".equals(choice))
				meth1();
			else
			{
				System.out.println("thank you see you soon");
				System.exit(0);
			}
		}
		else
		{
			System.out.println("data is not inerted");
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
}
public static void main(String[] args) 
{
Jdbc_App_4 obj=new Jdbc_App_4();
obj.meth1();
obj.meth2();
obj.meth3();
}
}

