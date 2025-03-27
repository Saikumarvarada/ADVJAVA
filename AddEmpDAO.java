package pack1;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddEmpDAO 
{
	int rowCount=0;
int insert_empdata(EmployeeBean bean)
{
	try
	{
	Connection con=DBConnect .getCon();
	PreparedStatement pstmt=con.prepareStatement("insert into employee values(?,?,?,?,?)");
	pstmt.setString(1,bean.getEmp_Id());
	pstmt.setString(2,bean.getEmp_Fname());
	pstmt.setString(3,bean.getEmp_Lname());
	pstmt.setInt(4,bean.getEmp_Sal());
	pstmt.setString(5,bean.getEmp_Addr());
	
	rowCount=pstmt.executeUpdate();
	}
	catch(Exception e)
	{
		e.printStackTrace();

	}
	return rowCount;
}
}
