package tubes;

//public class test {
//    public static void main(String[] args) {
//        System.out.println(getReturn());
//    }
//    public static boolean getReturn(){
//        int a = 21;
//        if (a == 2){
//            return true;
//        }
//        return false;
//    }
//}




//        String[][] data = new String[20][20];
//        database login = new database();
//        data = login.getUser();
//        System.out.println(data[1][0]);


import com.google.zxing.WriterException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class test {
    public static void main(String[] args) throws IOException, WriterException {
        item item = new item();
        pesanan pesanan = new pesanan();
        String[] dataPesanan = pesanan.getDataPesanan("080101-44160622-1230053");
        String date = dataPesanan[9];
//        date = date.replace("-","");
//        System.out.println(date);
////        for (String i : dataPesanan) {
////            System.out.println(i);
////        }
        pesanan.inputDataQR("060101-44160622-1230050");
//        pesanan.inputDataQR("080101-44160622-1230053");
//        pesanan.inputDataQR("080201-01160622-0600051");
//        pesanan.inputDataQR("190103-02160622-9550004");
//        pesanan.inputDataQR("180302-44160622-0230005");
//        pesanan.inputDataQR("450301-44160622-0500051");
    }
}
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
//        Date date = new Date();
//        String date1 = "2022-06-12";
//        int datee1 = Integer.parseInt(date1.replace("-",""));
//        System.out.println(datee1);
////        date1 = formatter.format(date1);
////        int datee2 = Integer.parseInt(date1);
//        String date2 = formatter.format(date);
//        int datee2 = Integer.parseInt(date2) + 3;
//        System.out.println(datee2);
////        int datee2 =

//        if (datee1 > datee2)
//    }
//}

//    public void getConenction(String sql){
//        try{
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/data","root","");
//            Statement stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery(sql);
//        }catch(Exception ex){
//            System.out.println(ex);
//        }
//    }
//}

//    public static void main(String[] args) {
//        test a = new test();
//        a.testing();
//        if (a.testing()){
//            System.out.println("Login berhasil");
//        }else{
//            System.out.println("Login gagal");
//        }
//    }
//    public boolean testing(){
//        String[][] dataUser = new String[20][20];
//        dataUser[0][0] = "user1";
//        dataUser[0][1] = "password1";
//        dataUser[1][0] = "user11";
//        dataUser[1][1] = "password11";
//
//        String[] loginUser = new String[20];
//        loginUser[0] = "user1";
//        loginUser[1] = "password1";
//
//        int b = 2;
//        for (String[] i : dataUser) {
//            if (b == 2) {
//                return true;
//            }
//        }
//        return false;
//    }
//}

//    public String getDataUser(String username){
//        try{
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/data","root","");
//            Statement stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery("SELECT * FROM account WHERE " + username);
//            System.out.println(rs.getString(1));
//            while(rs.next()) {
//                data[2][2] = "2";
//            }
//            while (rs.next()) {
//                String data = rs.getString(1);
//                System.out.println("Fetching data by column index for row " + rs.getRow() + " : " + data);
//                data = rs.getString("nomor");
//                System.out.println("Fetching data by column name for row " + rs.getRow() + " : " + data);
//            }
//            con.close();
//        }catch(Exception e){
//            System.out.println(e);
//        }
//    }
//}

//package tubes;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//public class test {
//
//    public static void main(String[] args) {
//
//        Connection connection = null;
//        try {
//
//            // Load the MySQL JDBC driver
//
//            String driverName = "com.mysql.cj.jdbc.Driver";
//
//            Class.forName(driverName);
//
//
//            // Create a connection to the database
//
//            String serverName = "localhost:3306";
//
//            String schema = "data";
//
//            String url = "jdbc:mysql://" + serverName +  "/" + schema;
//
//            String username = "root";
//
//            String password = "";
//
//            connection = DriverManager.getConnection(url, username, password);
//
//
//
//            System.out.println("Successfully Connected to the database!");
//
//
//        } catch (ClassNotFoundException e) {
//
//            System.out.println("Could not find the database driver " + e.getMessage());
//        } catch (SQLException e) {
//
//            System.out.println("Could not connect to the database " + e.getMessage());
//        }
//
//        try {
//
//
//// Get a result set containing all data from test_table
//
//            Statement statement = connection.createStatement();
//
//            ResultSet results = statement.executeQuery("SELECT * FROM account");
//
//
//// For each row of the result set ...
//
//            while (results.next()) {
//
//
//                // Get the data from the current row using the column index - column data are in the VARCHAR format
//
//                String data = results.getString(1);
//
//                System.out.println("Fetching data by column index for row " + results.getRow() + " : " + data);
//
//
//                // Get the data from the current row using the column name - column data are in the VARCHAR format
//
//                data = results.getString("username");
//
//                System.out.println("Fetching data by column name for row " + results.getRow() + " : " + data);
//
//
//            }
//
//
//            /*
//
//             * Please note :
//
//             * ResultSet API provides appropriate methods for retrieving data
//
//             * based on each column data type e.g.
//
//             *
//
//             * boolean bool = rs.getBoolean("test_col");
//
//             * byte b = rs.getByte("test_col");
//
//             * short s = rs.getShort("test_col");
//
//             * int i = rs.getInt("test_col");
//
//             * long l = rs.getLong("test_col");
//
//             * float f = rs.getFloat("test_col");
//
//             * double d = rs.getDouble("test_col");
//
//             * BigDecimal bd = rs.getBigDecimal("test_col");
//
//             * String str = rs.getString("test_col");
//
//             * Date date = rs.getDate("test_col");
//
//             * Time t = rs.getTime("test_col");
//
//             * Timestamp ts = rs.getTimestamp("test_col");
//
//             * InputStream ais = rs.getAsciiStream("test_col");
//
//             * InputStream bis = rs.getBinaryStream("test_col");
//
//             * Blob blob = rs.getBlob("test_col");
//
//             */
//
//        } catch (SQLException e) {
//
//            System.out.println("Could not retrieve data from the database " + e.getMessage());
//        }
//
//    }
//}