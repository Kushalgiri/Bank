package bank;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class GoToBank {
	Scanner sc =new Scanner(System.in);
	void CreateAccount() throws ClassNotFoundException, SQLException
	{
		System.out.println("Enter your first name: ");
		String f_name = sc.nextLine();
		System.out.println("Enter your last name:");
		String l_name = sc.nextLine();
		System.out.println("Enter your address:");
		String add = sc.nextLine();
		System.out.println("Enter you email:");
		String email= sc.nextLine();
		System.out.println("Enter your phone number:");
		String phone = sc.nextLine();
		System.out.println("Enter your four pin for atm:");
		int pin= sc.nextInt();
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","");
		Statement sta = cn.createStatement();
		String sql = "insert into customer value('"+f_name+"','"+l_name+"','"+add+"','"+phone+"',"+pin+",0,0,'"+email+"')";
		sta.executeUpdate(sql);
		ResultSet rs= sta.executeQuery("select * from customer where Atm_pin="+pin+"");
		rs.next();
		System.out.println("\n\nFirst Name: "+f_name+"\nLast Name:"+l_name+"\nAddress: "+add+"\nEmail: "+email+"\nPhone No.: "+phone+"\nAccount no.: "+rs.getString("Account_no")+ "\nATM pin: "+pin);
		}
	
	void Deposite() throws ClassNotFoundException, SQLException
	{
		System.out.println("Enter your acount number: ");
		int act=sc.nextInt();
		System.out.println("Enter the amount to deposite:");
		long amount= sc.nextLong();
		System.out.println("Deposited amount: "+amount);
		Class.forName("com.mysql.jdbc.Driver");
		Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","");
		Statement sta = cn.createStatement();
		ResultSet rs= sta.executeQuery("Select * from customer");
		//ResultSet st= sta.executeQuery("Select * from statement");
		rs.next();
		long deposit= amount+rs.getInt("Amount");
		//long sdeposit = amount +st.getInt("deposite");
		sta.executeUpdate("update customer set Amount="+deposit+" where Account_no="+act+"");
		 

             Connection con2=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","");

 			Statement st2=con2.createStatement();

              String query2= "insert into deposite (deposite,Account_no)"

               + "values("+amount+","+act+")";

                 st2.executeUpdate(query2);
		
		System.out.println("Amount Deposited Sucessfully !!\n");
	}
	
	void Withdraw() throws ClassNotFoundException, SQLException
	{
		System.out.println("Enter account number:");
		int acc = sc.nextInt();
		System.out.println("Enter four digit pin: ");
		int pin =sc.nextInt();
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","");
		Statement sta = cn.createStatement();
		ResultSet rs= sta.executeQuery("Select * from customer where Account_no="+acc+"");
		rs.next();
		int dpin=rs.getInt("Atm_Pin") ;
		int dcc=rs.getInt("Account_no") ;
		if(pin == dpin && acc == dcc)
		{
			System.out.println("Enter amount to withdraw: ");
			int withdraw= sc.nextInt();
			if(withdraw <= rs.getInt("Amount"))
			{
			int draw = rs.getInt("Amount") - withdraw;						//to extract from database
			sta.executeUpdate("update customer set Amount="+draw+" where Account_no="+acc+"");
			
			Connection con2=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","");

  			Statement st2=con2.createStatement();

               String query2= "insert into withdraw (withdraw,Account_no)"

                + "values("+withdraw+","+acc+")";

                  st2.executeUpdate(query2);
                  
			System.out.println("\n\t\t*---Thank you for using our service---* ");
			}
			else
			{
				System.out.println("\n\tNot Enough balance!!\n\tTry Again.");
			}
		}
		else 
			{
			System.out.println("\nYour Pin or Account not matched\n Re-Enter your information.\n");
			Withdraw();
			}
			
		
	}
	

}
