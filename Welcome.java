package bank;
import java.sql.SQLException;
import java.util.*;

public class Welcome {
	public static void main(String[] args) throws Exception {
		int choice;
		do {
		System.out.println("\n\t\t\t*--Java Bank Limited--*\n");
		System.out.println("Choose an option:");
		System.out.println("1.Goto Bank\n2.Goto atm\n3.Exit\n");
		Scanner sn=new Scanner(System.in);
		choice= sn.nextInt();
		GoToBank b=new GoToBank();
		GotoATM a= new GotoATM();
		//Statement s= new Statement();
		// AccessAtm m= new  AccessAtm();
		switch(choice)
		{
		case 1:
		 //System.out.println("Inside Bank");
			System.out.println("Select from Menu:\n1.Create an account\n2.Cash Deposite\n3.Cash Withdrawl\n4.Exit\n");
			int choice1=sn.nextInt();
			switch(choice1)
			{
				
				case 1:
					//System.out.println("Inside cretae account!");
					b.CreateAccount();
					break;
				case 2:
					//System.out.println("inside deposite sectiton");
					b.Deposite();
					break;
				case 3:
					//System.out.println("inside cash withdrawl");
					b.Withdraw();
					break;
				case 4:
					break;
				default:
					System.out.println("Invalid input");
					break;
					
				
			}
			break;
		case 2:
			//System.out.println("Inside ATM");
			System.out.println("Chose an option:\n1.Balance enquiry\n2.Cash Withdrawal\n3.Statement\n4.Pin change\n5.Exit\n ");
			int choice2=sn.nextInt();
			switch(choice2)
			{
				case 1:
					//System.out.println("inside Balance inquery");
					a.BalanceInquiry();
					break;
				case 2:
					//System.out.println("Inside cash withdrawl");
					a.Withdraw();
					break;
				case 3:
					a.Getstatement();
					//System.out.println("Inside statement");
					break;
				case 4:
					//System.out.println("Inside pin change");
					a.PinChange();
					break;
				case 5:
					break;
				default:
					System.out.println("Invalid input");
					break;
			}
			
			break;
			
		case 3:
			break;
		default:
			System.out.println("Invalid Input!!");
			break;
		}
	}while(choice!=3);
	}
}


