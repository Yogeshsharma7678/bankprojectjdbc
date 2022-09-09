package com.jdbcslot1;

import java.sql.*;

public class bankDao {
	
	Connection con= null;
	
	 
  public void Connect() throws Exception
    {
    
	  Class.forName("com.mysql.cj.jdbc.Driver");
    
      con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","123456");
    }
  
  
  //register the account
  
  public int registerCust(bankSystem b1)throws Exception{
	      
	      String holderName = b1.custName;
	      
	      String query ="select * from bankDetail where custName = '" +holderName+"'";
	      
	      Statement st = con.createStatement();
	      
	      ResultSet rs = st.executeQuery(query);
	      
	      if (rs.next())
	      {
	    	  
	    	  return -1;
	    	 
		  }
	      else 
	      {
		   //entering customer details to add customer into bank
	    	String query1="insert into bankDetail(custName,custPassword,custPhone,custAccBalance) values(?,?,?,?)";

            PreparedStatement pst=con.prepareStatement(query1);
            
		    pst.setString(1,b1.custName);
		    
		    pst.setString(2, b1.custPassword);
		    
		    pst.setString(3, b1.custPhone);
		    
		    pst.setInt(4, b1.custAccBalance);
		    
		    int count=pst.executeUpdate();
		
		return count;
			
	}
	  
  }
  
  
  //login of customer using custName
  
  public int login(String holderName , String Pwd) throws Exception 
  {
	  
	  String query="select * from bankDetail where custName='"+holderName+"'";
		
	  Statement st=con.createStatement();
		
	  ResultSet rs=st.executeQuery(query);
		
	  if(rs.next()) 
		{
		    //fetching password from database
			String custPwd=rs.getString(2);
			
			//match database password with enterd password
			if(custPwd.equals(Pwd)) 
			{
			
				int custId=rs.getInt(5);
			//if login success we can return custId
			
				return custId;
			}
			
			else 
			{
			return 0;
			}
		}
			else {
				
			return -1;
			
			}
   
  }
  
	public int withdraw(int custId,int amount)throws Exception {
		//account details based on custId
		String query="select * from bankDetail where custId="+custId;	
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query);
		rs.next();
		//fetching account balance
		int custAccBalance=rs.getInt(4);
		//if account balance is greater than withdraw amount we can allow to withdraw
		if(custAccBalance>amount) {
			custAccBalance-=amount;
			String query2="update bankDetail set custAccBalance="+custAccBalance+" where custId="+custId;
			//updating the custAccBalance after withdraw
			Statement st2=con.createStatement();
			int res=st2.executeUpdate(query2);
			return custAccBalance;
				}	
		else {
			return -1;
			}
		}
	
	
	public int deposit(int custId, int amount)throws Exception{
		//depositing the amount
		String query="select * from bankDetail where custId="+custId;
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query);
		
		if(rs.next())
		{
		int custAccBalance=rs.getInt(4);
		//adding amount to account balance
		custAccBalance+=amount;
		//update account balance
		String query2="update bankDetail set custAccBalance="+custAccBalance+" where custId="+custId;
		PreparedStatement pst=con.prepareStatement(query2);
		pst.executeUpdate();
		return custAccBalance;
		}
		else {
			return -1;
		}
	}
	
	public int checkBalance(int custId)throws Exception{
		//check the account balance
		String query="select * from banking where custId="+custId;
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query);
		rs.next();
		int custAccBalance=rs.getInt(4);
		return custAccBalance;
		
	}
	
	public int pinChange(int custId, String oldpin,String newPin)throws Exception{
		//getting details of the account holder
		String query="select * from bankDetail where custId="+custId;
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query);
		rs.next();
		String Pwd=rs.getString(3);
		//matching present password to update with new password
		if(Pwd.equals(oldpin)) {
			String query2="update bankDetail set custPassword="+newPin+" where custId="+custId;
			PreparedStatement pst=con.prepareStatement(query2);
			int count=pst.executeUpdate();
			return count;
		}
		else {
			return -1;
		}
		
	}
}
