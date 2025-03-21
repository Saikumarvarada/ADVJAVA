package pack2;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
@WebServlet("/empServlet")
public class SecondServlet extends GenericServlet
{

	@Override
public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException 
{
	PrintWriter pw=res.getWriter();
	res.setContentType("text/html");
	String empName=req.getParameter("ename");
	String empId=req.getParameter("eid");
	int empSal =Integer.parseInt(req.getParameter("esal"));
	int empExp =Integer.parseInt(req.getParameter("eexp"));
	if(empExp<5)
	{
		empSal=(int) (empSal+(empSal*0.1));
	}
	pw.print("<center>");
	pw.println("***Data****");
	
	pw.println("<h2>EmpName:"+empName);
	pw.println("<br> Emp id:"+empId);
	pw.println("<br> EmpSal:"+empSal);
	pw.println("<br> EmpExp:"+empExp+"</h2>");
  }
}
