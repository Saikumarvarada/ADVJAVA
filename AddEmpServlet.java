package pack1;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/es")
public class AddEmpServlet extends HttpServlet
{
	@Override
protected void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
{
	EmployeeBean bean=new EmployeeBean();
	
	bean.setEmp_Id(req.getParameter("eId"));
	bean.setEmp_Fname(req.getParameter("eFname"));
	bean.setEmp_Lname(req.getParameter("eLname"));
	bean.setEmp_Sal(Integer.parseInt(req.getParameter("eSal")));
	bean.setEmp_Addr(req.getParameter("eAddr"));
	
	AddEmpDAO AddEmpDAOobj=new AddEmpDAO();
	int rowCount=AddEmpDAOobj.insert_empdata(bean);
	if(rowCount>0)
	{
		req.setAttribute("msg","Data inserted into the database successfully!!!");
		RequestDispatcher rd=req.getRequestDispatcher("AddEmp.jsp");
		rd.forward(req, res);
	}
	else
	{
	throw new ServletException("Check the code correctly");	
	}
}
}
