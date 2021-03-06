package tubes;

import org.jetbrains.annotations.NotNull;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class event extends database{
    String[][] dataEvent;

    //database Abstract
    public ResultSet queryDatabase(String sql){
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

    //Event database
    //Mengambil jumlah event dalam database
    public int getJumlahEvent(){
        int count = 0;
        sql = "SELECT COUNT(*) FROM event";
        try{
            rs = queryDatabase(sql);
            rs.next();
            count = rs.getInt(1);
        }catch(Exception ex){
            System.out.println(ex);
        }
        return count;
    }

    //Mengambil data event
    public String[][] getEvent(){
        int jumlahKolom = getJumlahEvent();
        String[][] dataEvent = new String[jumlahKolom][6];
        int i = 0;
        sql = "SELECT * FROM `event`";
        try{
            rs = queryDatabase(sql);
            while (rs.next()) {
                dataEvent[i][0] = rs.getString("id");
                dataEvent[i][1] = rs.getString("date");
                dataEvent[i][2] = rs.getString("diskonpersen");
                dataEvent[i][3] = rs.getString("diskonharga");
                dataEvent[i][4] = rs.getString("idbarang");
                dataEvent[i][5] = rs.getString("jumlah");
                i++;
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        return dataEvent;
    }

    //Mengambil data event spesifik
    public String[] eventBarang(int id){
        String[] dataEventSatuan = new String[6];
        sql = "SELECT * FROM `event` WHERE `idbarang` = " + id;
        try{
            rs = queryDatabase(sql);
            while (rs.next()) {
                dataEventSatuan[0] = rs.getString("id");
                dataEventSatuan[1] = rs.getString("date");
                dataEventSatuan[2] = rs.getString("diskonpersen");
                dataEventSatuan[3] = rs.getString("diskonharga");
                dataEventSatuan[4] = rs.getString("idbarang");
                dataEventSatuan[5] = rs.getString("jumlah");
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        return dataEventSatuan;
    }

    //Input data Event
    public void inputEvent(String @NotNull [] dataEvent){
        sql = "INSERT INTO account (`date`, `diskonpersen`, `diskonharga`, `idbarang`" +
                "VALUES (" +
                "'" + dataEvent[0]   + "',"  +
                "'" + dataEvent[1]   + "',"  +
                "'" + dataEvent[2]   + "',"  +
                "'" + dataEvent[3]   + "')";
        updateDatabase(sql);
    }

    public void inputEvent(int idItem, String @NotNull [] dataEvent){
        sql = "INSERT INTO account (`date`, `diskonpersen`, `diskonharga`, `idbarang`" +
                "VALUES (" +
                "'" + dataEvent[0]    + "',"  +
                "'" + dataEvent[1]    + "',"  +
                "'" + dataEvent[2]    + "',"  +
                "'" + idItem          + "')";
        updateDatabase(sql);
    }

    //Edit data event
    public void editEvent(String type,int idEvent,String data){
        sql = "UPDATE `event` SET `"+ type +"` = '"+ data +"' WHERE `event`.`id` = "+ idEvent +"; ";
        updateDatabase(sql);
    }

    //Logic Event
    //Mengetahui event yang aktif
    public String[] getCurrentEvent(){
        Date date = new Date();
        dataEvent = getEvent();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateNow = formatter.format(date);
        for (String[] i : dataEvent) {
            if (i[1].equals(dateNow)) {
                return i;
            }
        }
        return null;
    }

    //Menggunakan event
    public boolean eventStatus(int idItem){
        String[] CurrentEvent = getCurrentEvent();
        if (CurrentEvent != null) {
            return CurrentEvent[4].equals(String.valueOf(idItem));
        }
        return false;
    }
}
