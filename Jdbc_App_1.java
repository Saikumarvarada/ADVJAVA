package package1;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Jdbc_App_1 
{
String driver="oracle.jdbc.OracleDriver";
String DBurl="jdbc:oracle:thin:@192.168.1.31:1521:xe";
String DBuname="system";
String DBpwd="tiger";
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
	 System.out.println("Implementing callable statement");
	 Connection con=connect();
	 try
	 {
		CallableStatement cstm=con.prepareCall("{call InsertEmpData(?,?,?,?,?)}");
		
		System.out.println("Enter empid");
		String eid=sc.nextLine();
		
		System.out.println("Enter empname");
		String ename=sc.nextLine();
		
		System.out.println("Enter empdesg");
		String edesg=sc.nextLine();
		
		System.out.println("Enter empsal");
		int ebsal=Integer.parseInt(sc.nextLine());
		
		float etsal=ebsal+(0.35f*ebsal)+(0.15f*ebsal);
		
		cstm.setString(1,eid);
		cstm.setString(2,ename);
		cstm.setString(3,edesg);
		cstm.setInt(4,ebsal);
		cstm.setFloat(5,etsal);
		
		if(cstm.execute())
		{
			System.out.println("Data is not inserted");
		}
		else
		{
			System.out.println("Data is inserted");
		}
	 }
	 catch(SQLException e)
	 {
		 e.printStackTrace();
	 }
 }
 public static void main(String[] args) 
 {
Jdbc_App_1 obj=new Jdbc_App_1();
obj.meth1();
}
}
