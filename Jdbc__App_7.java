package com.pack1;

import java.util.Scanner;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class Jdbc__App_7
{
	String driver = "oracle.jdbc.OracleDriver";
	String DBurl = "jdbc:oracle:thin:@localhost:1521:orcl";
	String DBuname = "system";
	String DBpwd = "TIGER";
	Scanner sc = new Scanner(System.in);
	void meth1()
	{
		System.out.println("Implementing JDBCRowSet\n");
		try
		{
			RowSetFactory rsf=RowSetProvider.newFactory();
			JdbcRowSet jrs=rsf.createJdbcRowSet();
			
			jrs.setUrl(DBurl);
			jrs.setUsername(DBuname);
			jrs.setPassword(DBpwd);
			
			System.out.println("Connection Created\n");
			
			jrs.setCommand("select *from emp");
			jrs.execute();
			
			//jrs.close();//It generates SQLRecoverableException because JdbcRowSet works only when Connected to the DB
			
			jrs.last();
System.out.println(jrs.getString(1)+" "+ jrs.getString(2)+" "+jrs.getString(3)+" "+jrs.getString(4));
 jrs.beforeFirst();
 while(jrs.next())
 {
		System.out.println(jrs.getString(1) + " " + jrs.getString(2) + " " + jrs.getString(3) + " " + jrs.getString(4));
 }
		}
		catch(Exception e)
		{
			
		}
	}
	void meth2()
	{
		System.out.println("Implementing CachedRowSet\n");
		try
		{
			RowSetFactory rsf=RowSetProvider.newFactory();
			CachedRowSet crs=rsf.createCachedRowSet();
			
			crs.setUrl(DBurl);
			crs.setUsername(DBuname);
			crs.setPassword(DBpwd);
			
			System.out.println("Connection Created\n");
			crs.setCommand("select*from emp");
			//crs.close();
			crs.execute();
			/*while(crs.next())
			{
				System.out.println(crs.getString(1));
			}*/
			System.out.println("enter your eid");
			String eid=sc.next();
			System.out.println("enter your modified esal");
			int esal=sc.nextInt();
			while(crs.next())
			{
				String id=crs.getString(1);
				if(eid.equals(id))
				{
					crs.updateInt("esal",esal);
					crs.updateRow();
				}
			}
			//crs.acceptChanges();
			crs.beforeFirst();
		while (crs.next())
		{
			System.out.println(crs.getString(1) + " " + crs.getString(2) + " " + crs.getString(3) + " "
					+ crs.getString(4) + " " + crs.getString(5) + "\n");
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void main(String[] args) 
	{
	Jdbc__App_7 obj=new Jdbc__App_7();
	//obj.meth1();
	obj.meth2();
	}
}
