import java.sql.*;

public class Add extends Inventory
{
    public void insertrecord(String a,int b,float c) throws SQLException
    {
        Connection con=DriverManager.getConnection(db_path,"root",pass);
        String sql = "INSERT INTO inventory values(?,?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, a);
        pstmt.setInt(2, b);
        pstmt.setFloat(3,c);
        System.out.println("Item "+a+" of qty "+b+" and price "+c+" has been successfully added");
        pstmt.executeUpdate();
        con.close();
    }
}