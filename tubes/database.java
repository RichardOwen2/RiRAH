package tubes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class database {
    String sql;
    ResultSet rs;
    Connection con;

    //Membuat koneksi dengan database
    public ResultSet queryDatabase(String sql) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "root", "");
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return rs;
    }

    public void updateDatabase(String sql) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "root", "");
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            con.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
