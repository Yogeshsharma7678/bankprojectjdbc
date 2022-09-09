package com.jdbcslot1;
import java .util.*;

public class bankMain {

	public static void main(String[] args)throws Exception{
	
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		bankDao dao = new bankDao();
		
		bankSystem b1 = new bankSystem();
		
		
		System.out.println("/t/t-----Welcome to bank System------/t/t");
		
		System.out.println("/t/t-----Select Operation : \\n1 for Register Account \\n2 for Login to Account");

		int op = sc.nextInt();
		
	    switch(op){
	    case 1 -> 
	            {  System.out.println("---enter customer detail----");
	             
	              System.out.println("---enter customer name----");
	              
	              String custName = sc.next();
	              
	              
	              System.out.println("---create customer password----");
	              
	              String custPassword =sc.next();
	            	  
	              System.out.println("---enter customer phone no:----");  
	              
	              String custPhone =sc.next();
	              
	              System.out.println("---enter account balance----");
	              
	              int custAccBalance =sc.nextInt();
	              
	              b1.custAccBalance=custAccBalance;
	              
	              b1.custName = custName;
	              
	              b1.custPassword= custPassword;
	              
	              b1.custPhone = custPhone;
	              
	              dao.Connect();
	              
	              int res = dao.registerCust(b1);
	              
	              if (res>0)
	              {
	            	  System.out.println("Account Created");
	  			  }
	  			  else
	  			  {
	  		 	 	System.out.println("user already existed in system");
	  			  }  
	              
	            
	           }
	    case 2 ->{System.out.println("----enter your details to login-----");
	    	
	    	     System.out.println("----enter your username-----");
	    	     
	    	     String custName = sc.next();
	    	     
	    	     System.out.println("----enter your password-----");
	    	     
	    	     String custPassword = sc.next();
	    	     
	    	     dao.Connect();
	    	     
	    	     int res = dao.login(custName , custPassword);
	    	     
	    	     if(res==0)
	    	     {
	    	    	 System.out.println("Wrong username or password");
	    	    	 
	    	     }
	    	     else if(res==-1) {
	    	    	 System.out.println("account not found So,Please Register yourself");
	    	     }
	    	     else
	    	     {
	    	    	 System.out.println("Login success");
	    	        int op2=0;
					while(op2<5) {
						System.out.println("Select Operation : \n 1 for Withdraw ,\n 2 for deposit ,\n 3 for check balance ,\n 4 for pin change ,\n 5 for exit");
						op2=sc.nextInt();
					
						switch(op2) {
						
						case 1->{
							//withdraw amount from customer account
							System.out.println("Enter amount to withdraw");
							int amt=sc.nextInt();
						    int res2=dao.withdraw(res, amt);
							if(res2<=0) {
								System.out.println("Something went wrong");
							}
							else {
								System.out.println("Withdraw done, updated balance is : "+res2);
								
							}
						}
						case 2->{
							//deposit money into account
							System.out.println("Enter amount to deposit");
							int amount=sc.nextInt();
							int res2=dao.deposit(res, amount);
							if(res2==-1) {
								System.out.println("Something went wrong");
							}
							else {
								System.out.println("Deposit done, updated balance is: "+res2);
							}
						}
						
						case 3->{
							//balance checking of customer account
							System.out.println("your balance is +"+dao.checkBalance(res));
						}
						case 4->{
							//pin change
							System.out.println("Enter your Password");
							String pwd=sc.next();
							System.out.println("Enter new password");
							String newpin=sc.next();
							int count=dao.pinChange(res, pwd,newpin);

						if(count<=0)
						{
							System.out.println("Password not matched");
						}
						else {
							System.out.println("password updated");
						}
						
						
						
						}
						
						
						
						
						
						
						
						}
						
		
					}
					
				
				
				
				}
			}
			
			
			}
			
			
			
			
			
					sc.close();
		}

	}