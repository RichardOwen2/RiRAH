package tubes;

import org.jetbrains.annotations.NotNull;
import java.sql.ResultSet;

public class user {
    database database = new database();
    account account = new account();
    pesanan pesanan = new pesanan();
    String sql;
    ResultSet rs;
    String[][] dataUser;
    String[] dataLoginUser;

    //User database
    //Mengambil jumlah dataUser
    public int getJumlahDataDiriUser(){
        int count = 0;
        sql = "SELECT COUNT(*) FROM user;";
        try{
            rs = database.queryDatabase(sql);
            rs.next();
            count = rs.getInt(1);
        }catch(Exception ex){
            System.out.println(ex);
        }
        return count;
    }

    //Mengambil dataUser
    public String[][] getUser(){
        int jumlahKolom = getJumlahDataDiriUser();
        String[][] dataUser = new String[jumlahKolom][10];
        sql = "SELECT * FROM user";
        int i = 0;
        try{
            rs = database.queryDatabase(sql);
            while (rs.next()) {
                dataUser[i][0] = rs.getString("id");
                dataUser[i][1] = rs.getString("username");
                dataUser[i][2] = rs.getString("namaPria");
                dataUser[i][3] = rs.getString("namaWanita");
                dataUser[i][4] = rs.getString("namaAyahPria");
                dataUser[i][5] = rs.getString("namaIbuPria");
                dataUser[i][6] = rs.getString("namaAyahWanita");
                dataUser[i][7] = rs.getString("namaIbuWanita");
                dataUser[i][8] = rs.getString("tanggalAcara");
                dataUser[i][9] = rs.getString("alamatAcara");
                i++;
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        return dataUser;
    }
    //Mengambil dataUser spesifik
    public String[] getDataUser(String username){
        String[] dataItem = new String[7];
        sql = "SELECT * FROM `user` WHERE id = " + "'" + account.getIdAccount(username) + "'";
        try{
            database.queryDatabase(sql);
            while (rs.next()) {
                dataItem[0] = rs.getString("id");
                dataItem[1] = rs.getString("nama");
                dataItem[2] = rs.getString("gambar");
                dataItem[3] = rs.getString("harga");
                dataItem[4] = rs.getString("stock");
                dataItem[5] = rs.getString("detail");
                dataItem[6] = rs.getString("Item Created");
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        return dataItem;
    }

    //input data user
    public void inputDataUser(String username, String @NotNull [] dataUser){
        sql = "INSERT INTO `user` (`id`, `username`, `namaPria`, `namaWanita`, `namaAyahPria`, `namaIbuPria`, " +
                "`namaAyahWanita`, `namaIbuWanita`, `tanggalAcara`, `alamatAcara`) VALUES (" +
                "'" + account.getIdAccount(username) + "',"  +
                "'" + username                       + "',"  +
                "'" + dataUser[0]                    + "',"  +
                "'" + dataUser[1]                    + "',"  +
                "'" + dataUser[2]                    + "',"  +
                "'" + dataUser[3]                    + "',"  +
                "'" + dataUser[4]                    + "',"  +
                "'" + dataUser[5]                    + "',"  +
                "'" + dataUser[6]                    + "',"  +
                "'" + dataUser[7]                    + "')";
        database.updateDatabase(sql);
    }

    //
    public Object[] confirmInputDataDiri(String @NotNull [] dataUser) {
        for (String i : dataUser) {
            if (i.equals("")) {
                return new Object[]{false, "Mohon isi semua kolom"};
            }
        }
        return new Object[]{true, "Input Berhasil"};
    }


    //Check username
    public boolean checkUsername(String username){
        dataUser = getUser();
        try {
            for (String[] i : dataUser) {
                System.out.println(i[1]);
                if (i[1].equals(username)){
                    return true;
                }
            }
        } catch (NullPointerException e) {
            return false;
        }
        return false;
    }

    //Check data user
    public boolean checkDataUser(String username, String dataUser){
        dataLoginUser = getDataUser(username);
        if (!account.checkUsernameAccount(username)) {
            return false;
        }else{
            for (String i : dataLoginUser) {
                if (i.equals(dataUser)) {
                    return false;
                }
            }
        }
        return true;
    }

    //Edit data user
    public void editDataUser(String type, String username, String data){
        sql = "UPDATE `user` SET `"+ type +"` = '"+ data +"' WHERE `user`.`id` = "+ account.getIdAccount(username) +"; ";
        database.updateDatabase(sql);
    }

}
