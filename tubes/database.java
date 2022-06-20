package tubes;

import java.sql.Connection;
import java.sql.ResultSet;

public abstract class database {
    //Menurunkan variable
    String sql;
    ResultSet rs;
    Connection con;

    //Membuat koneksi dengan database
    public abstract ResultSet queryDatabase(String sql);
    public abstract void updateDatabase(String sql);
}
