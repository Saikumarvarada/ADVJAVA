package pack1;

import java.io.Serializable;

public class EmployeeBean implements Serializable
{
private String emp_Id;
private String emp_Fname;
private String emp_Lname;
private int emp_Sal;
private String emp_Addr;


public String getEmp_Id() 
{
	return emp_Id;
}
public void setEmp_Id(String emp_Id)
{
	this.emp_Id = emp_Id;
}
public String getEmp_Fname() 
{
	return emp_Fname;
}
public void setEmp_Fname(String emp_Fname)
{
	this.emp_Fname = emp_Fname;
}
public String getEmp_Lname()
{
	return emp_Lname;
}
public void setEmp_Lname(String emp_Lname) 
{
	this.emp_Lname = emp_Lname;
}
public int getEmp_Sal() 
{
	return emp_Sal;
}
public void setEmp_Sal(int emp_Sal)
{
	this.emp_Sal = emp_Sal;
}
public String getEmp_Addr() 
{
	return emp_Addr;
}
public void setEmp_Addr(String emp_Addr)
{
	this.emp_Addr = emp_Addr;
}
public EmployeeBean()
{
	
}

}
