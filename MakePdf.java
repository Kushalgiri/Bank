package bank;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class MakePdf {
	public static void main(String[] args) throws SQLException, FileNotFoundException, DocumentException {
		Date d2=new Date();

		Scanner sc =new Scanner(System.in);
		System.out.println("Enter account number:");
		int acc= sc.nextInt();
	    
		
		Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","");
		Statement sta = cn.createStatement();
		
		String qry1="SELECT withdraw,Account_no FROM withdraw where Account_no= "+acc+"";
		String qry="SELECT deposite,Account_no FROM deposite where Account_no= "+acc+"";
		String qry2="SELECT First_Name,Last_Name,Address,Amount FROM customer where Account_no= "+acc+"";
		ResultSet rs2=sta.executeQuery(qry2);
		rs2.next();
		
		//if(acc == rs2.getInt("Account_no"))
		//{
			Document doc =new Document();
			PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\kushg\\statement.pdf"));
			doc.open();
			doc.add(new Paragraph("\t\t*--Java Bank Limited--*\n\t\t  Statement\n\n\t\tDate:"+d2.toString()));
			
			doc.add(new Paragraph("Account Holder: "+rs2.getString("First_Name")+" "+rs2.getString("Last_Name")));
			doc.add(new Paragraph("Address: "+rs2.getString("Address")));
			doc.add(new Paragraph("Available Balance: "+rs2.getInt("Amount")));
			doc.add(new Paragraph("\nTransaction Type       Amount      Account No      "));
			
			ResultSet rs=sta.executeQuery(qry);
			
			
			while( rs.next())

			{

			 int pn=rs.getInt("deposite");
			 int a=rs.getInt("Account_no");
			 doc.add(new Paragraph("Deposite                "+pn+"        "+a+"          "));
			 }
			ResultSet rs1=sta.executeQuery(qry1);
			while( rs1.next())

			{

			 int pnn=rs1.getInt("withdraw");
			 int aa=rs1.getInt("Account_no");
			 doc.add(new Paragraph("Withdraw                "+pnn+"        "+aa+"          "));

			}

			doc.add(new Paragraph("\n\tThank You For using our service."));
			//}
			//else
			//{
				//System.out.println("\tInvalid Account Number!!\nContact Branch");
			//}

			doc.close();  	
			System.out.println("Pdf ready!");
		}
	}

