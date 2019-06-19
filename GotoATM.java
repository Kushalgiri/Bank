package bank;
import java.util.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GotoATM {
	Scanner sc= new Scanner(System.in);
	void BalanceInquiry() throws ClassNotFoundException, SQLException
	{
		System.out.println("Enter your account number: ");
		int acc =sc.nextInt();
		System.out.println("Enter you pin:");
		int pin = sc.nextInt();
		Class.forName("com.mysql.jdbc.Driver");
		Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","");
		Statement sta = cn.createStatement();
		ResultSet rs= sta.executeQuery("Select * from customer where Account_no="+acc+" ");
		rs.next();
		
		if(pin == rs.getInt("Atm_Pin") && acc == rs.getInt("Account_no"))
		{
			int amo= rs.getInt("Amount");
			System.out.println("Your Balance: "+amo);
		}
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
			System.out.println("\nTransaction Sucessfull!!\n\t\t*---Thank you for using our service---*\n ");
			}
			else
			{
				System.out.println("\n\tNot Enough balance!!\n\tTry Again.");
			}
		}
		else 
			{
			System.out.println("\nWrong account or pin\nPlease enter corectly.\n");
			Withdraw();
			}
			
	}
	
	void PinChange() throws ClassNotFoundException, SQLException
	{
		System.out.println("Enter you acount no:");
		int acc= sc.nextInt();
		System.out.println("\nEnter you old pin.:");
		int pin1= sc.nextInt();
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","");
		Statement sta = cn.createStatement();
		ResultSet rs= sta.executeQuery("Select * from customer where Account_no="+acc+"");
		rs.next();
		if(pin1 == rs.getInt("Atm_Pin") && acc == rs.getInt("Account_no"))
		{
		System.out.println("Enter the new pin.:");
		int pin2 = sc.nextInt();
		sta.executeUpdate("update customer set Atm_pin="+pin2+" where Account_no="+acc+"");
		System.out.println("\n\tPin change sucessfull.\n\tThank you for using our service.");
		}
		else {
			System.out.println("\nEnter valid Account no. or pin\ncheck again");
			PinChange();
		}
	
	}

public static void Getstatement() throws Exception 
{
	Scanner sc = new Scanner(System.in);
	
	System.out.println("Enter account number:");
	int acc= sc.nextInt();
    
	
	Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","");
	Statement sta = cn.createStatement();
	
	String qry1="SELECT withdraw,Account_no FROM withdraw where Account_no= "+acc+"";
	String qry="SELECT deposite,Account_no FROM deposite where Account_no= "+acc+"";
	String qry2="SELECT First_Name,Last_Name,Address,Amount FROM customer where Account_no= "+acc+"";
	ResultSet rs2=sta.executeQuery(qry2);
	rs2.next();
	//int p=rs2.getInt("Account_no");
	//if(acc ==p )
	{
	System.out.println("\t\t*--Java Bank Limited--*\n\t\t  Statement\n\n");
	System.out.println("Account Holder: "+rs2.getString("First_Name")+" "+rs2.getString("Last_Name"));
	System.out.println("Address: "+rs2.getString("Address"));
	System.out.println("Available Balance: "+rs2.getInt("Amount"));
	System.out.println("\nTransaction Type       Amount      Account No      ");
	
	ResultSet rs=sta.executeQuery(qry);
	
	
	while( rs.next())

	{

	 int pn=rs.getInt("deposite");
	 int a=rs.getInt("Account_no");
	 System.out.println("Deposite                "+pn+"        "+a+"          ");
	 }
	ResultSet rs1=sta.executeQuery(qry1);
	while( rs1.next())

	{

	 int pnn=rs1.getInt("withdraw");
	 int aa=rs1.getInt("Account_no");
	 System.out.println("Withdraw                "+pnn+"        "+aa+"          ");

	}

	System.out.println("\n\tThank You For using our service.");
	}
	//else
	//{
		//System.out.println("\tInvalid Account Number!!\nContact Branch");
	//}
	}
			
}
	

