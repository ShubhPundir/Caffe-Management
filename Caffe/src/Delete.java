import java.sql.*;

public class Delete extends Inventory
{
    public void deleterecord(String a) throws SQLException
    {
        Connection con=DriverManager.getConnection(db_path,"root",pass);
        String sql = "delete from inventory where Item_Name=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, a);
        System.out.println("Item has been deleted from the record");
        pstmt.executeUpdate();
        con.close();
    }

}