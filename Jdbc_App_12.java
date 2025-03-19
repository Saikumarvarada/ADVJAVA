package package1;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;


public class Jdbc_App_12 
{

	String driver = "oracle.jdbc.OracleDriver";
	String DBurl = "jdbc:oracle:thin:@localhost:1521:XE";
	String DBuname = "system";
	String DBpwd = "TIGER";
	 
	String sqlQuery1="update trainseatavailability set available_seats=available_seats-1"
	 +"where train_id=? and Journey_date=? and class=? and available_seats>0";
	String sqlquery2="insert into bookingdetails values(?,?,?,?,?)";
	String sqlQuery3="select payment_status from customerPayment where Customer_id=?";
	String sqlQuery4="update bookingdetails set status='Payment Done'where booking_id=?";
	
	void ticketBooking()
	{
		System.out.println("Implementing Trasaction management");
         try
         {
        	 Class.forName(driver);
        	 Connection con=DriverManager.getConnection(DBurl,DBuname,DBpwd);
        	 
        	 System.out.println("connection created");
        	 con.setAutoCommit(false);
        	 if(con.getAutoCommit())
        		 System.out.println("Autocommit is enabled");
        	 else
        		 System.out.println("Autocommit is disabled");
        	 
        	 PreparedStatement pstm1=con.prepareStatement(sqlQuery1);
        	 pstm1.setString(1,"1204");
        	 pstm1.setString(2, "2024-10-10");
        	 pstm1.setString(3,"sleeper");
        	 int rowCount=pstm1.executeUpdate();
        	 if(rowCount==0)
        		 throw new SQLException("There are No seats!!!");
        	 else
        	 {
        		 System.out.println("Seat is locked");
        		 Savepoint sp=con.setSavepoint();
        		 //con.commit();
        		 
        		 PreparedStatement pstmt2=con.prepareStatement(sqlquery2);
        		 pstmt2.setString(1,"B105");
        		 pstmt2.setString(2,"12345");
        		 pstmt2.setString(3,"C123");
        		 pstmt2.setInt(4, 1);
        		 pstmt2.setString(5,"pending payment");
        		 int rowCount2=pstmt2.executeUpdate();
        		 if(rowCount2==0)
        			 throw new SQLException("Booking NOT done");
        		       //con.commit();
        		 System.out.println("Booking reccord created successfully");
        		 System.out.println("Waiting for payment confirmation");
        		 
        		 PreparedStatement pstmt3=con.prepareStatement(sqlQuery3);
        		 pstmt3.setString(1,"c12");
        		 ResultSet rs=pstmt3.executeQuery();
        		 String status="Failed";
        		 if(rs.next())
        		 {
        			 status=rs.getString(1);
        			 if(status.equals("success"))
        			 {
        				 PreparedStatement pstmt4=con.prepareStatement(sqlQuery4);
        				 pstmt4.setString(1,"B105");
        				 int rowCount3=pstmt4.executeUpdate();
        				 if(rowCount3==0)
        					 throw new SQLException("Booking faild due to network issue");
        				 System.out.println("Ticket Confirmed!!!");
        				 con.commit();
        				 System.out.println("Transaction Completed");
        				 System.out.println("SavePoint released");
        			 }
        		 }
        		 else
        		 {
        			 System.out.println("Payment is NOT done");
        			 System.out.println("Transation rolling back to the last savepoint");
        			 con.rollback(sp);
        		 }
        	 }
         }
         catch(SQLException e)
         {
        	 e.printStackTrace();
         }
         catch(Exception e)
         {
        	 e.printStackTrace();
         }
	
	}
	public static void main(String[] args) 
	{
	new Jdbc_App_12().ticketBooking();
	}
}