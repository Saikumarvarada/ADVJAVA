package package1;

import java.util.Scanner;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class ClassA
{
String diver="oracle.jdbc.OracleDriver";
String DBurl="jdbc:oracle:thin:@localhost:1521:xe";
String DBuname="system";
String DBpwd="TIGER";
Scanner sc=new Scanner(System.in);
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
		System.out.println("connection created\n");
		
		jrs.setCommand("select *from emp");
		jrs.execute();
	
		jrs.last();
		System.out.println(jrs.getString(1)+" "+jrs.getString(2)+" "+jrs.getString(3)+" "+jrs.getString(6)+"\n");
		jrs.beforeFirst();
		while(jrs.next())
		{
		System.out.println(jrs.getString(1)+" "+jrs.getString(2)+" "+jrs.getString(3)+" "+jrs.getString(6));
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
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
		System.out.println("connection created");
		crs.setCommand("select*from emp");
		 //crs.close();
		crs.execute();	
	 while(crs.next())
	   {
System.out.println(crs.getString(1)+" "+crs.getString(2)+" "+crs.getString(3)+" "+crs.getString(6)+"\n");
	   }
	 crs.beforeFirst();
	 System.out.println("enter your empno");
	 String empno=sc.next();
	 System.out.println("enter your modify sal");
	   int Sal=sc.nextInt();
	 while(crs.next())
	 {
		 String id=crs.getString(1);
		 
   if(empno.equals(id))
   { 
	   crs.updateInt("Sal",Sal);
	   crs.updateRow();
   }
	 }
	 crs.acceptChanges();
	 
		crs.beforeFirst();
		while(crs.next())
		{
System.out.println(crs.getString(1)+" "+crs.getString(2)+" "+crs.getString(3)+" "+crs.getString(6)+"\n");
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
}
public static void main(String[] args) 
{
ClassA obj=new ClassA();
//obj.meth1();
obj.meth2();
}
}
