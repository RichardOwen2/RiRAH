package tubes;

import org.jetbrains.annotations.NotNull;
import java.sql.ResultSet;

public class account {
    database database = new database();
    String sql;
    ResultSet rs;
    String[][] dataAccount;
    String[] dataUser;

    //Account database
    //Mengambil jumlah acccount dalam database
    public int getJumlahAccount(){
        int count = 0;
        sql = "SELECT COUNT(*) FROM account;";
        try{
            rs = database.queryDatabase(sql);
            rs.next();
            count = rs.getInt(1);
        }catch(Exception ex){
            System.out.println(ex);
        }
        return count;
    }

    //Mengambil Id Account
    public int getIdAccount(String username){
        int idUser = 0;
        sql = "SELECT * FROM `account` WHERE `username` = " + '"' + username + '"' + ";";
        try{
            rs = database.queryDatabase(sql);
            while (rs.next()) {
                idUser = rs.getInt("id");
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        return idUser;
    }

    //Mengambil data account
    public String[][] getAccount(){
        int jumlahKolom = getJumlahAccount();
        String[][] dataAccount = new String[jumlahKolom][10];
        sql = "SELECT * FROM account";
        int i = 0;
        try{
            rs = database.queryDatabase(sql);
            while (rs.next()) {
                dataAccount[i][0] = rs.getString("id");
                dataAccount[i][1] = rs.getString("username");
                dataAccount[i][2] = rs.getString("password");
                dataAccount[i][3] = rs.getString("Role");
                dataAccount[i][4] = rs.getString("nama");
                dataAccount[i][5] = rs.getString("email");
                dataAccount[i][6] = rs.getString("nomor");
                dataAccount[i][7] = rs.getString("alamat");
                dataAccount[i][8] = rs.getString("Account Created");
                dataAccount[i][9] = rs.getString("RiRAHPay");
                i++;
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        return dataAccount;
    }

    //Mengambil data account spesifik dengan username
    public String[] getDataAccount(String username){
        String[] dataUser = new String[10];
        sql = "SELECT * FROM `account` WHERE id = " + "'" + getIdAccount(username) + "'";
        try{
            rs = database.queryDatabase(sql);
            while (rs.next()) {
                dataUser[0] = rs.getString("id");
                dataUser[1] = username;
                dataUser[2] = rs.getString("password");
                dataUser[3] = rs.getString("Role");
                dataUser[4] = rs.getString("nama");
                dataUser[5] = rs.getString("email");
                dataUser[6] = rs.getString("nomor");
                dataUser[7] = rs.getString("alamat");
                dataUser[8] = rs.getString("Account Created");
                dataUser[9] = rs.getString("RiRAHPay");
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        return dataUser;
    }

    //Insert Account Baru
    public void inputDataAccount(String @NotNull [] dataAccount){
        sql = "INSERT INTO account (`username`, `password`, `nama`, `email`"+
                ", `nomor`, `alamat`) " +
                "VALUES (" +
                "'" + dataAccount[0] + "',"  +
                "'" + dataAccount[1] + "',"  +
                "'" + dataAccount[2] + "',"  +
                "'" + dataAccount[3] + "',"  +
                "'" + dataAccount[4] + "',"  +
                "'" + dataAccount[5] + "')";
        database.updateDatabase(sql);
    }

    //Check Account
    public boolean checkUsernameAccount(String username) {
        dataAccount = getAccount();
        for (String[] i : dataAccount) {
            if (i[1].equals(username)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkDataAccount(String username, String data) {
        dataUser = getDataAccount(username);
        if (!checkUsernameAccount(username)) {
            return false;
        }else{
            for (String i : dataUser) {
                if (i.equals(data)) {
                    return false;
                }
            }
        }
        return true;
    }

    //Edit data account
    public void editDataAccount(String type, String username, String data){
        sql = "UPDATE `account` SET `"+ type +"` = '"+ data +"' WHERE `account`.`id` = "+ getIdAccount(username) +"; ";
        database.updateDatabase(sql);
    }

    public void editSaldoAccount(String username, int saldo){
        sql = "UPDATE `account` SET `RiRAHPay` = "+ saldo +" WHERE `account`.`id` = "+ getIdAccount(username) +"; ";
        database.updateDatabase(sql);
    }

    //RiRAHPay
    //Menggunakan Saldo Account
    public void bonus(String username){
        isiSaldo(username, 50000);
    }

    public Object[] pakaiSaldo(String username, String password, int harga){
        dataUser = getDataAccount(username);
        if (dataUser[1].equals(username) && dataUser[2].equals(password)) {
            int saldo = Integer.parseInt(dataUser[9]);
            if (saldo >= harga) {
                saldo = saldo - harga;
                editSaldoAccount(username, saldo);
                return new Object[]{true, "Pembayaran Berhasil"};
            } else {
                return new Object[]{false, "Saldo Tidak Mencukupi"};
            }
        } else {
            return new Object[]{false, "Password yang anda masukan salah"};
        }
    }

    public String isiSaldo(String username, int jumlah){
        int saldo = Integer.parseInt(getDataAccount(username)[9]) + jumlah;
        editSaldoAccount(username, saldo);
        return "Berhasil menambahkan saldo sebesar " + jumlah + " ke account " + username;
    }
}
