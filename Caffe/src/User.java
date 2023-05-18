import java.sql.*;
import java.util.*;

public class User extends Inventory
{
   
    // here lied idChecker

    // here lied ordCheck()

    // here lied getPrice()

    public static void user()
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Hi!! Welcome to Cafe-0 of NCU");
        System.out.println("Enter your 10 digit mobile number below");
        String mob=sc.next();
        
        RecordCheck rc=new RecordCheck();
        Bill bill=new Bill();

        int disc=rc.idChecker(mob);
        rc.displayMenu();

        System.out.println("Enter the Item Name of your order");
        System.out.println("Enter QUIT if your order is done");
        String ordString=sc.next();
        int qty=0;
        
        if(ordString.compareTo("QUIT")!=0)
        {
            System.out.println("How many of it would you like");
            qty=sc.nextInt();
        }

        while(ordString.compareTo("QUIT")!=0) // will run as long as User will not enter QUIT
        {
            try
            {
                if(rc.ordCheck(ordString,qty)==true)
                {
                    float price=rc.getPrice(ordString, qty);
                    bill.insertBill(ordString, qty,price);
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }

            System.out.println();
            System.out.println("Your order so far...");
            bill.show();
            System.out.println();

            System.out.println("Is that all...?");
            System.out.println("Enter the Item Name that you wish to order");
            System.out.println("Enter QUIT if your order is done");

            ordString=sc.next();
            if(ordString.compareTo("QUIT")!=0)
            {
                System.out.println("How many of it would you like");
                qty=sc.nextInt();
            }
            System.out.println();
        }

        System.out.println();
        System.out.println("Your order so far");
        
        bill.show();
        System.out.println("Would you like to delete any orders? Enter QUIT if not");
        ordString=sc.next();
        while(ordString.compareTo("QUIT")!=0) // will run as long as User will not enter QUIT
        {
            try
            {
                bill.deleteBill(ordString);
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            System.out.println();
            System.out.println("Is that all...?");

            System.out.println();
            System.out.println("Your order so far...");
            bill.show();
            System.out.println();

            System.out.println("Enter the Item Name that you wish to remove");
            System.out.println("Enter QUIT if your are done");
            
            ordString=sc.next();

            System.out.println();
        }

        System.out.println();
        System.out.println("Your order");
        double total = bill.show();
        System.out.println();
        System.out.println("TOTAL Before Discount: "+total);
        double payup=total -(double)(disc*total)/100.0;
        System.out.println("TOTAL After Discount: "+total);
        System.out.println();

        System.out.println("-------- PRINTING YOUR BILL---------");
        bill.printingText( mob, total, payup);
        System.out.println("Thank you for shopping with Cafe-0");

        sc.close();

    }
}
