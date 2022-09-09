package com.jdbcslot1;
import java.sql.*;

public class jdbcDemo {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
        
		//Loading and ragistering 
	    Class.forName("com.mysql.cj.jdbc.Driver");
	    
	    String url = "jdbc:mysql://localhost:3306/jdbc";
	    String username = "root";
	    String password = "123456";
       
	    String query ="select * from student where sid = 101";
	    
	    //get the connection
	   Connection con = DriverManager.getConnection(url, username, password);
	   
	   //statement creation
	   Statement st = con.createStatement();
	   
	   //execution of query
	   ResultSet rs = st.executeQuery(query);
	   
	   //go to next element
	   rs.next();
	   
	   //processing of result
	   String studentname = rs.getString("sname")+ " and study in " +rs.getString("sdomain");
	   System.out.println("Student Name :"+studentname);
	   
	   //closing of connection object
	   con.close();
	}

}
