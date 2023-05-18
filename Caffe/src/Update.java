import java.sql.*;

public class Update extends Inventory
{
    public void addquantity(String a,int b) throws SQLException
    {
        Connection con=DriverManager.getConnection(db_path,"root",pass);
        String sql = "update inventory set Quantity=Quantity+? where Item_Name=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, b);
        pstmt.setString(2, a);
        System.out.println("Item quantity has been changed");
        pstmt.executeUpdate();
        con.close();
    }
    public void subquantity(String a,int b) throws SQLException
    {
        Connection con=DriverManager.getConnection(db_path,"root","root");
        String sql = "update inventory set Quantity=Quantity-? where Item_Name=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, b);
        pstmt.setString(2, a);
        System.out.println("Item quantity has been changed");
        pstmt.executeUpdate();
        con.close();
    }


}