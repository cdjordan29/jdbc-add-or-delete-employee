import java.sql.*;
import java.io.*;

public class Employee
{
	public  String EMPID, Lname, Fname, Sex, Dept, Phone, DBstatus;
	public  int Salary;
	private Connection conn;
	private Statement stmt;
	private int connected;
	private String user, pass;
	private String query; 

	public Employee(String ID)
	{
		EMPID = ID;
		Lname = "";
		Fname = "";
		Sex = "";
		Dept = "";
		Phone = "";
		Salary = 0;
		DBstatus = "";

		user = "cjordan29";
		pass = "password";

		try
		{
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			conn = DriverManager.getConnection("jdbc:oracle:thin:@Picard2:1521:itec2",user,pass);
			stmt = conn.createStatement();
			conn.setAutoCommit(false);
			query = "Select empid, lname, fname, sex, dept, phone, salary "
					+ "from employees "
					+ "where empid = '" + this.EMPID + "'";
			System.out.println(query);

			ResultSet rset = stmt.executeQuery(query);
			while(rset.next())
			{
				EMPID = rset.getString("empid");
				Lname = rset.getString("lname");
				Fname = rset.getString("fname");
				Sex = rset.getString("sex");
				Dept = rset.getString("dept");
				Phone = rset.getString("phone");
				Salary = rset.getInt("salary");
				DBstatus = "Found";
			}
			conn.close();
		}
		catch(SQLException e)
		{
			DBstatus = "Error";
			System.out.println(e);
		}
	}

	public String Save()
	{
		//add code here to save the fields back to the database
		//return a string that says "SUCCESS" or "FAILURE" depending on if your save succeeds
		String output = "";
		
		try
		{
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			conn = DriverManager.getConnection("jdbc:oracle:thin:@Picard2:1521:itec2",user,pass);
			stmt = conn.createStatement();
			conn.setAutoCommit(false);
			query = "UPDATE Employees " +
					 "SET lname = '" + this.Lname + "', fname = '" + this.Fname + "'" +
					 " WHERE empid = '" + this.EMPID + "'";
			System.out.println(query);
			
			stmt.executeUpdate(query);
			conn.commit();	
			
			output = "SUCCESS";
		}
		catch(SQLException e)
		{
			DBstatus = "Error";
			System.out.println(e);
			
			output = "FAILURE";
		}
		 	
		System.out.println(output);
		return output;
	}

}

