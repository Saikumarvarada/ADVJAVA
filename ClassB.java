package package1;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
public class ClassB 
{
	String driver = "oracle.jdbc.OracleDriver";
	String DBurl = "jdbc:oracle:thin:@192.168.1.9:1521:XE";
	String DBuname = "system";
	String DBpwd = "tiger";
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
	Connection con=connect();
	{
		try
		{
	CallableStatement cstm=con.prepareCall("{call InsertEmpData(?,?,?,?,?,?,?)}");
	
	System.out.println("Enter name");
	String name=sc.nextLine();
	
	System.out.println("Enter epwd");
	int pwd=Integer.parseInt(sc.nextLine());
	
	System.out.println("Enter fname");
	String fname=sc.nextLine();
	
	System.out.println("Enter lname");
	String lname=sc.nextLine();
	
	System.out.println("Enter address");
	String addr=sc.nextLine();
	
	System.out.println("Enter mail_id");
	String mid=sc.nextLine();
	
	System.out.println("Enter phno");
    Long phno=sc.nextLong();
	cstm.setString(1,name);
	cstm.setInt(2,pwd);
	cstm.setString(3,fname);
	cstm.setString(4,lname);
	cstm.setString(5,addr);
	cstm.setString(6,mid);
	cstm.setLong(7,phno);
	if(cstm.execute())
		System.out.println("data is not inserted");
	else
		System.out.println("Registration successfully");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		}
}
public static void main(String[] args) 
{
ClassB obj=new ClassB();
obj.meth1();
}
}
