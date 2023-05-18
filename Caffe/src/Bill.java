import java.sql.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Bill extends Inventory
{
    Bill()
    {
        try(Connection con = DriverManager.getConnection(db_path, "root", pass);
            Statement stmt = con.createStatement()) {
            String sql = "TRUNCATE TABLE bill";
            stmt.executeUpdate(sql);
            System.out.println("Bill truncated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void insertBill(String item,int qty,float price) throws SQLException
    {
        
            Connection con=DriverManager.getConnection(db_path,"root",pass);
            String sql = "INSERT INTO bill VALUES(?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, item);
            pstmt.setInt(2, qty);
            pstmt.setFloat(3,price);
            System.out.println("Item "+item+" of qty "+qty+ " and price "+price+" has been successfully added to your bill");
            pstmt.executeUpdate();
            // System.out.println("PSTMT Executed");
            con.close();
    }
    public void deleteBill(String a) throws SQLException
    {
        Connection con=DriverManager.getConnection(db_path,"root",pass);
        String sql = "delete from bill where Item_Name=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, a);
        System.out.println("Item has been deleted from the bill");
        pstmt.executeUpdate();
        con.close();
    }

    public double show()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection(db_path,"root",pass);
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("Select * from bill");
            System.out.println("Item Name\tQuantity\tPrice");
            double total=0;
            while(rs.next())
            {
                String Item_Name=rs.getString("Item_Name");
                int Quantity=rs.getInt("Quantity");
                float Price=rs.getFloat("Price");
                total+=Quantity*Price;
                System.out.println(Item_Name+"\t"+Quantity+"\t"+Price);
            }
            con.close();
            System.out.println("TOTAL :\t"+total);
            System.out.println("----------------------------");
            return total;
        }
        catch(Exception e)
        {
            System.out.println("Ln58 Exception");
            System.out.println(e);
            return -9999;
        }
    }


    public void printingText(String mob, double total, double payup) {
        try (Connection con = DriverManager.getConnection(db_path, "root", pass);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM bill")) {

            try (PrintWriter writer = new PrintWriter(new FileWriter(mob+"_Bill.txt"))) {
                writer.println("Customer Mobile: " + mob);
                writer.println("TOTAL Before Discount: " + total);
                writer.println("TOTAL After Discount: " + payup);
                writer.println("--------------------------");

                while (rs.next()) {
                    String item = rs.getString("Item_Name");
                    int qty = rs.getInt("Quantity");
                    float price = rs.getFloat("Price");

                    writer.println("Item: " + item);
                    writer.println("Quantity: " + qty);
                    writer.println("Price: " + price);
                    writer.println("--------------------------");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
