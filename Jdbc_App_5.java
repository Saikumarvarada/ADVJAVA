package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Jdbc_App_5 
{
	String driver="oracle.jdbc.OracleDriver";
	String DBurl="jdbc:oracle:thin:@localhost:1521:orcl";
	String DBuname="system";
	String DBpwd="TIGER";
	Scanner sc=new Scanner(System.in);
	Connection connect()
	{
		Connection con=null;
		try
		{
			Class.forName(driver);
			con=DriverManager.getConnection(DBurl,DBuname,DBpwd);
			System.out.println("connection created\n");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
	void patientOperations()
	{
		System.out.println("-----Welcome to patient database--------");
System.out.println("1) Add Data\n2) View Data\n3) Reterive patient Data\n4) Update patient Data\n5) Delete patient Data\n6)Exit");
	System.out.println("enter your choice");
	int choice=Integer.parseInt(sc.nextLine());
	Connection con=connect();
      try
		{
			
			PreparedStatement pstm1=con.prepareStatement("insert into patient values(?,?,?,?)");
		    PreparedStatement pstm2=con.prepareStatement("select*from patient");
		    PreparedStatement pstm3=con.prepareStatement("select*from patient where pid=?");
		    PreparedStatement pstm4=con.prepareStatement("update patient set pname=? where pid=?");
		    PreparedStatement pstm5=con.prepareStatement("delete from patient where pid=?");
		    
		    
			while(true)
		 {
			 switch(choice)
			 {
			 case 1:
				 System.out.println("Inseting patient data");
				 System.out.println("Enter patient Id");
				 String pat_id=sc.nextLine();
				 System.out.println("Enter patient Name");
				 String pat_name=sc.nextLine();
				 System.out.println("Enter patient Age");
				 int pat_age=Integer.parseInt(sc.nextLine());
				 System.out.println("Enter patient contact");
				 long pat_contact=Long.parseLong(sc.nextLine());
				  
				 pstm1.setString(1,pat_id);
				 pstm1.setString(2,pat_name);
				 pstm1.setInt(3,pat_age);
				 pstm1.setLong(4,pat_contact);
				 
				 int rowCount=pstm1.executeUpdate();
				 if(rowCount==1)
					 System.out.println("Data Inserted");
				 else
					 System.out.println("Data not Inserted");
				    System.exit(0);
				 break;
			 case 2:
				 System.out.println("View patient data");
               //System.out.println("enter your table");
               //String t_name=sc.nextLine();//because the values are not assigning for parameters
               // pstm2.setString(1, t_name);
				 
				 ResultSet rs=pstm2.executeQuery();
				 while(rs.next())
				 {
	System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));
				 }
				 System.out.println("Data Reterived");
				 System.exit(0);
				 break;
			 case 3:
				 System.out.println("View specific patient data");
				 System.out.println("Enter patient id");
				 String pat_id2=sc.nextLine();
				 pstm3.setString(1, pat_id2);
				 ResultSet rs2=pstm3.executeQuery();
				 if(rs2.next())
						System.out.println(rs2.getString(1)+" "+rs2.getString(2)+" "+rs2.getInt(3)+" "+rs2.getLong(4));
				 System.exit(0);
				 break;
			 case 4:
				 System.out.println("Updating patient Name");
				 System.out.println("Enter patient id");
				 String pat_id3=sc.nextLine();
				 System.out.println("Enter patient Modified Name");
				 String pat_name3=sc.nextLine();
				 
				 pstm4.setString(1,pat_name3);
				 pstm4.setString(2,pat_id3);
				 int rowCount1=0;
			 rowCount1=pstm4.executeUpdate();
				 if(rowCount1>0)
					 System.out.println("data is updated");
				 else
					 System.out.println("data is not updated");
				 System.exit(0);
				 break;
			 case 5:
				 System.out.println("Deleting the patient record");
				 System.out.println("Enter patient id");
				 String pat_id4=sc.nextLine();
				 pstm5.setString(1,pat_id4);
				 int rowCount2=pstm5.executeUpdate();
				 if(rowCount2==1)
					 System.out.println(pat_id4+"Record deleted");
				 else
					 System.out.println("There is NO change in the database");
				 System.exit(0);
				  break;
			 case 6:
				 System.out.println("Thank you for visiting see you soon:)");
				 System.exit(0);
				 break;
				 default:
					 System.out.println("Invalid data");
					 patientOperations();
			 }
		 }
	}
	catch(Exception e)
	{
			e.printStackTrace();
	}
	}
	public static void main(String[] args) 
	{
		new Jdbc_App_5().patientOperations();
		
	}
}
