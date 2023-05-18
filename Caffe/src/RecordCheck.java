import java.sql.*;

public class RecordCheck extends Inventory
{
    public int idChecker(String recordId)
    {
        try
        {
            Connection con = DriverManager.getConnection(db_path, "root", pass);
            String sql = "SELECT COUNT(*) FROM users WHERE id = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, recordId);
            ResultSet rs = pstmt.executeQuery();
            
            // Check if a record exists
            if (rs.next() && rs.getInt(1) > 0)
            {
                System.out.println("Record with ID " + recordId + " exists.");
                System.out.println("CONGRATS! premium users get a discount of 10%");
                return 10; // 10% discount    
            }

            
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        System.out.println("Record with ID " + recordId + " does not exist.");
        return 0;  // 0 % discount
        
    }

    public boolean ordCheck(String item,int qty)
    {
        try
        {
            Connection con = DriverManager.getConnection(db_path, "root", pass);
            String sql = "SELECT COUNT(*) FROM inventory WHERE Item_Name = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, item);
            ResultSet rs = pstmt.executeQuery();
            
            // Check if a record exists
            if (rs.next() && rs.getInt(1) > 0)
            {
                System.out.println("Record with item " + item + " exists.");
                String query = "select Quantity from inventory where Item_Name=?";
                PreparedStatement p = con.prepareStatement(query);
                p.setString(1, item);
                ResultSet r = p.executeQuery();
                if(r.next() && r.getInt(1)>qty)
                {
                    System.out.println("Order Initiated of "+item+" of Quantity "+qty);
                    return true;
                }
            }
            else
            {
                System.out.println("Record with ID " + item + " does not exist.");
            }
            
            // Close the resources
            rs.close();
            pstmt.close();
            con.close();
            return false;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public float getPrice(String item, int qty) throws SQLException
    {
        Connection con = DriverManager.getConnection(db_path, "root", pass);
        String query = "SELECT Price FROM inventory WHERE Item_Name = ?";
        PreparedStatement p = con.prepareStatement(query);
        p.setString(1, item);
        ResultSet r = p.executeQuery();
        if (r.next()) { // Move the cursor to the first row
            float price = r.getFloat("Price");
            System.out.println("The Price of " + item + " is " + price);
            return price;
        }
        // Handle the case when no records are found
        System.out.println("No record found for item " + item);
        return 0; // Return a default value or handle it accordingly in your code
    }
    public void displayMenu()
    {
        System.out.println("--------------MENU--------------");
        int i=1;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection(db_path,"root",pass);
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("Select * from inventory");
            System.out.println("SN\tItem Name\tPrice");
            while(rs.next())
            {
                String Item_Name=rs.getString("Item_Name");
                float Price=rs.getFloat("Price");
                System.out.println(i+"\t"+Item_Name+"\t"+Price);
                i++;
            }
            con.close();
        }
        catch(Exception e)
        {

            System.out.println(e);
        }
    }
    
}
