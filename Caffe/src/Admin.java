import java.sql.*;
import java.util.*;

public class Admin
{
    public static void admin()
    {
        int i=0;
        do
        {
            Scanner sc=new Scanner(System.in);
            System.out.println("1.Inventory\n2.Update inventory");
            System.out.println("Enter your choice:");
            int x=sc.nextInt();
            switch(x)
            {
                case 1:
                {
                    System.out.println("-----------------------Inventory-----------------------");
                    Inventory obj=new Inventory();
                    obj.invent();
                    break;
                }
                case 2:
                {
                    System.out.println("------------------------Update Inventory  -------------------------");
                    System.out.println("Choose your Option:");
                    System.out.println("1.Add new Item\n2.Update quantity\n3.Delete item");
                    int y=sc.nextInt();
                    switch(y)
                    {
                        case 1:
                        {
                            System.out.println("Enter the Item name:");
                            String a=sc.next();
                            System.out.println("Enter the Quantity of the Item:");
                            int b=sc.nextInt();
                            System.out.println("Enter the price:");
                            float c=sc.nextFloat();
                            try
                            {
                                Add obj=new Add();
                                obj.insertrecord(a, b, c);
                                
                            }
                            catch(SQLException e)
                            {
                                System.out.println(e);
                            }
                            break;                            
                        }
                        case 2:
                        {
                            Update obj=new Update();
                            System.out.println("Enter the Item name:");
                            String a=sc.next();
                            System.out.println("Which action do you want to perform:");
                            System.out.println("1.Add quantity\n2.Subtract quantity");
                            int b=sc.nextInt();
                            System.out.println("Enter the quantity");
                            int c=sc.nextInt();
                            try
                            {
                                if(b==1)
                                {
                                    obj.addquantity(a,c);
                                }
                                else
                                {
                                    obj.subquantity(a,c);
                                }
                            }
                            catch(SQLException e)
                            {
                                System.out.println(e);
                            }
                            break;      
                        }
                        case 3:
                        {
                            System.out.println("Enter the Item name which you want to delete from the inventory:");
                            String a=sc.next();
                            Delete obj=new Delete();
                            try
                            {
                                obj.deleterecord(a);
                            }
                            catch(SQLException e)
                            {
                                System.out.println(e);
                            }
                            break;      
                        }
                    }
                    
                }
            }
            System.out.println("Do you want to-\n1.Continue\n2.Quit");
            int terminate=sc.nextInt();
            if(terminate==1)
            {
                continue;
            }
            else
            {
                i++;
            }
            sc.close();
        }while(i==0);
        
    }

}