import java.sql.*;

public class Inventory
{
    String db_path="jdbc:mysql://localhost:3306/admin";
    String pass="12345";
    void invent()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection(db_path,"root",pass);
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("Select * from inventory");
            System.out.println("Item Name\tQuantity\tPrice");
            while(rs.next())
            {
                String Item_Name=rs.getString("Item_Name");
                int Quantity=rs.getInt("Quantity");
                float Price=rs.getFloat("Price");
                System.out.println(Item_Name+"\t"+Quantity+"\t"+Price);
            }
            con.close();
        }
        catch(Exception e)
        {
            System.out.println("Ln27 Exception");
            System.out.println(e);
        }
    }
        
}

