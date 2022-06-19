package tubes;

import org.jetbrains.annotations.NotNull;
import java.sql.ResultSet;

public class item extends event{
    String[][] dataItem;
    String[] dataSatuanItem;
    String[] dataSatuanEvent;

    //Item database
    //Mengambil jumlah item dalam database
    public int getJumlahItem(){
        int count = 0;
        sql = "SELECT COUNT(*) FROM item;";
        try{
            rs = queryDatabase(sql);
            rs.next();
            count = rs.getInt(1);
        }catch(Exception ex){
            System.out.println(ex);
        }
        return count;
    }

    //Mengambil data item
    public String[][] getItem(){
        int jumlahKolom = getJumlahItem();
        String[][] dataItem = new String[jumlahKolom][6];
        int i = 0;
        sql = "SELECT * FROM `item`";
        try{
            rs = queryDatabase(sql);
            while (rs.next()) {
                dataItem[i][0] = rs.getString("id");
                dataItem[i][1] = rs.getString("nama");
                dataItem[i][2] = rs.getString("harga");
                dataItem[i][3] = rs.getString("stock");
                dataItem[i][4] = rs.getString("detail");
                dataItem[i][5] = rs.getString("Item Created");
                i++;
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        return dataItem;
    }

    //Mengambil data item spesifik
    public String[] getDataItem(int id){
        String[] dataItem = new String[6];
        sql = "SELECT * FROM `item` WHERE id = " + "'" + id + "'";
        try{
            rs = queryDatabase(sql);
            while (rs.next()) {
                dataItem[0] = String.valueOf(id);
                dataItem[1] = rs.getString("nama");
                dataItem[2] = rs.getString("harga");
                dataItem[3] = rs.getString("stock");
                dataItem[4] = rs.getString("detail");
                dataItem[5] = rs.getString("Item Created");
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        return dataItem;
    }

    //Mengambil Id Item
    public int getIdItem(String nama){
        int idItem = 0;
        sql = "SELECT * FROM `item` WHERE Item = " + "'" + nama + "'";
        try{
            rs = queryDatabase(sql);
            while (rs.next()) {
                idItem = rs.getInt("id");
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        return idItem;
    }

    //Input item baru
    public void inputDataItem(String @NotNull [] dataItem){
        sql = "INSERT INTO item (`nama`, `harga`, `stock`"+
                ", `detail`) " +
                "VALUES (" +
                "'" + dataItem[0] + "',"  +
                "'" + dataItem[1] + "',"  +
                "'" + dataItem[2] + "',"  +
                "'" + dataItem[3] + "',";
        updateDatabase(sql);
    }

    //Check Id item
    public boolean checkIdItem(int id){
        dataItem = getItem();
        for (String[] i : dataItem){
            if (i[0].equals(String.valueOf(id))){
                return false;
            }
        }
        return true;
    }

    //Check data item
    public boolean checkDataItem(int id, String data){
        dataSatuanItem = getDataItem(id);
        if (!checkIdItem(id)){
            return false;
        } else {
            for (String i : dataSatuanItem){
                if (i.equals(data)){
                    return false;
                }
            }
        }
        return true;
    }

    //Edit data item
    public void editDataItem(String type, int id, String data){
        sql = "UPDATE `item` SET `"+ type +"` = '"+ data +"' WHERE `item`.`id` = "+ id +"; ";
        updateDatabase(sql);
    }

    //Mengambil Stock
    public void takeStock(int idItem, int jumlah){
        int stock = Integer.parseInt(getDataItem(idItem)[3]) - jumlah;
        sql = "UPDATE `item` SET `stock` = "+ stock +" WHERE `item`.`id` = "+ idItem +"; ";
        updateDatabase(sql);
    }

    //Mengambil harga item
    //Template Return = Harga Satuan , Harga Barang (Jumlah) , Total Diskon , Harga Setelah Diskon
    public Integer[] getHarga(int id, int jumlah){
        dataSatuanItem = getDataItem(id);
        dataSatuanEvent = eventBarang(id);
        int hargaSatuan = Integer.parseInt(dataSatuanItem[2]);
        int hargaBarang = hargaSatuan * jumlah;
        if (eventStatus(id)) {
            int diskonPersen = Integer.parseInt(dataSatuanEvent[2]);
            int diskonHarga = Integer.parseInt(dataSatuanEvent[3]);
            int jumlahBarang = Integer.parseInt(dataSatuanEvent[5]);
            if (jumlah >= jumlahBarang) {
                int Diskon = hargaBarang * diskonPersen /100;
                if (Diskon >= diskonHarga) {
                    int hargaSetelahDiskon = hargaBarang - diskonHarga;
                    return new Integer[]{hargaSatuan, hargaBarang, diskonHarga, hargaSetelahDiskon};
                } else {
                    int hargaSetelahDiskon = hargaBarang - Diskon;
                    return new Integer[]{hargaSatuan, hargaBarang, Diskon, hargaSetelahDiskon};
                }
            }
        }
        return new Integer[]{hargaSatuan, hargaBarang, 0, hargaBarang};
    }
}
