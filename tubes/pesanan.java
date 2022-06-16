package tubes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import javax.imageio.ImageIO;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.sql.ResultSet;
import java.util.Random;
import java.text.SimpleDateFormat;
import java.util.Date;

public class pesanan {
    database database = new database();
    account account = new account();
    item item = new item();
    event event = new event();
    String sql;
    ResultSet rs;
    String[] dataPesanan;
    int id;

    //Pesanan database
    //Mengambil jumlah pesanan dalam database
    public int getJumlahPesanan(){
        int count = 0;
        sql = "SELECT COUNT(*) FROM pesanan;";
        try{
            rs = database.queryDatabase(sql);
            rs.next();
            count = rs.getInt(1);
        }catch(Exception ex){
            System.out.println(ex);
        }
        return count;
    }

    public int getJumlahPesananUser(String username){
        int count = 0;
        sql = "SELECT COUNT(*) FROM pesanan WHERE username = " + "'" + username + "';";
        try{
            rs = database.queryDatabase(sql);
            rs.next();
            count = rs.getInt(1);
        }catch(Exception ex){
            System.out.println(ex);
        }
        return count;
    }

    //Mengambil data pesanan
    public String[][] getPesanan(){
        int jumlahKolom = getJumlahPesanan();
        String[][] dataPesanan = new String[jumlahKolom][11];
        int i = 0;
        sql = "SELECT * FROM `pesanan`";
        try{
            rs = database.queryDatabase(sql);
            while (rs.next()) {
                dataPesanan[i][0] = rs.getString("id");
                dataPesanan[i][1] = rs.getString("Nomor");
                dataPesanan[i][2] = rs.getString("username");
                dataPesanan[i][3] = rs.getString("Tanggal");
                dataPesanan[i][4] = rs.getString("idItem");
                dataPesanan[i][5] = rs.getString("Jumlah");
                dataPesanan[i][6] = rs.getString("Harga");
                dataPesanan[i][7] = rs.getString("Pembayaran");
                dataPesanan[i][8] = rs.getString("status");
                dataPesanan[i][9] = rs.getString("lastConfirm");
                dataPesanan[i][10] = rs.getString("code");
                i++;
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        return dataPesanan;
    }

    //Mengambil nomor pesanan
    public String[] getNomorPesananUser(String username) {
        int jumlahKolom = getJumlahPesananUser(username);
        String[] nomor = new String[jumlahKolom];
        int i = 0;
        sql = "SELECT * FROM `pesanan` WHERE username = " + "'" + username + "'";
        try{
            rs = database.queryDatabase(sql);
            while (rs.next()) {
                nomor[i] = rs.getString("Nomor");
                i++;
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        return nomor;
    }


    //Mengambil data item spesifik
    public String[] getDataPesanan(int id){
        String[] dataPesanan = new String[11];
        sql = "SELECT * FROM `pesanan` WHERE id = " + "'" + id + "'";
        try{
            rs = database.queryDatabase(sql);
            while (rs.next()) {
                dataPesanan[0] = String.valueOf(id);
                dataPesanan[1] = rs.getString("Nomor");
                dataPesanan[2] = rs.getString("username");
                dataPesanan[3] = rs.getString("Tanggal");
                dataPesanan[4] = rs.getString("idItem");
                dataPesanan[5] = rs.getString("Jumlah");
                dataPesanan[6] = rs.getString("Harga");
                dataPesanan[7] = rs.getString("Pembayaran");
                dataPesanan[8] = rs.getString("status");
                dataPesanan[9] = rs.getString("lastConfirm");
                dataPesanan[10] = rs.getString("code");
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        return dataPesanan;
    }

    public String[] getDataPesanan(String nomor){
        String[] dataPesanan = new String[11];
        sql = "SELECT * FROM `pesanan` WHERE Nomor = " + "'" + nomor + "'";
        try{
            rs = database.queryDatabase(sql);
            while (rs.next()) {
                dataPesanan[0] = rs.getString("id");
                dataPesanan[1] = nomor;
                dataPesanan[2] = rs.getString("username");
                dataPesanan[3] = rs.getString("Tanggal");
                dataPesanan[4] = rs.getString("idItem");
                dataPesanan[5] = rs.getString("Jumlah");
                dataPesanan[6] = rs.getString("Harga");
                dataPesanan[7] = rs.getString("Pembayaran");
                dataPesanan[8] = rs.getString("status");
                dataPesanan[9] = rs.getString("lastConfirm");
                dataPesanan[10] = rs.getString("code");
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        return dataPesanan;
    }

    //Mengambil daftar pesanan user
    public String[][] getDaftarPesanan(String[] nomor, String username){
        int jumlahDaftar = getJumlahPesananUser(username);
        String[][] daftarPesanan = new String[jumlahDaftar][11];
        int j = 0;
        for (String i : nomor) {
            daftarPesanan[j] = getDataPesanan(i);
            j++;
        }
        return daftarPesanan;
    }

    //Input Pesanan baru
    public String inputDataPesanan(String username, int idItem, String cara, int jumlah, int harga){
        String nomor = getNomorPesanan(username, idItem, cara, jumlah);
        sql = "INSERT INTO `pesanan` (`Nomor`, `username`,`idItem`, `Jumlah`, `Harga`, `Pembayaran`)" +
                "VALUES (" +
                "'" + nomor            + "',"  +
                "'" + username         + "',"  +
                ""  + idItem           + ","   +
                ""  + jumlah           + ","   +
                ""  + harga            + ","   +
                "'" + cara             + "')";
        database.updateDatabase(sql);
        return nomor;
    }

    //Logic Pesanan
    //Confirm Pesanan
    public void confirmPesanan(String data, String nomor){
        sql = "UPDATE `pesanan` SET `status` = '"+ data +"' WHERE `pesanan`.`Nomor` = '"+ nomor +"'; ";
        database.updateDatabase(sql);
    }

    public void terimaKompensasi(String nomor){
        sql = "UPDATE `pesanan` SET `code` = 1 WHERE `pesanan`.`Nomor` = '"+ nomor +"'; ";
        database.updateDatabase(sql);
    }

    //Update Timer Pesanan
    public void updateTime(int id){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        sql = "UPDATE `pesanan` SET `lastConfirm` = '" + formatter.format(date) + "' WHERE `pesanan`.`id` = " + id + ";";
        database.updateDatabase(sql);
    }

    //Timer Pesanan
    public boolean timerPesanan(String nomor) {
        dataPesanan = getDataPesanan(nomor);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        int dateNow = Integer.parseInt(formatter.format(date));
        int datePesanan = Integer.parseInt((dataPesanan[9].replace("-","")));
        return switch (dataPesanan[8]) {
            case "Menunggu Konfirmasi"     -> datePesanan + 3 < dateNow;
            case "Pesanan sedang diproses" -> datePesanan + 7 < dateNow;
            case "Pesanan Sedang diKirim"  -> datePesanan + 4 < dateNow;
            case "Pesanan Selesai"         -> false;
            case "Pesanan di Tolak"        -> false;
            default                        -> false;
        };
    }

    public Object[] notifkompensasi(String username, String nomor, boolean timer) {
        if (timer) {
            confirmPesanan("Pesanan gagal diproses", nomor);
            if (getDataPesanan(nomor)[10].equals("0")) {
                account.isiSaldo(username, 50000);
                terimaKompensasi(nomor);
                return new Object[]{true, "Mohon Maaf atas ketidaknyamanannya", "Kami memberikan Kompensasi RiRAHPAY sebesar Rp 50.000,00"};
            }
        }
        return new Boolean[]{false};
    }

    //Nomor Pesanan
    private String getRandomNumber() {
        Random r = new Random();
        return String.valueOf(r.nextInt(10));
    }

    private String getNomorIdAccount(String username) {
        id = account.getIdAccount(username);
        if (id < 10){
            return "0" + id;
        }
        return String.valueOf(id);
    }

    private String getNomorIdItem(String nama){
        id = item.getIdItem(nama);
        if (id < 10){
            return "0" + id;
        }
        return String.valueOf(id);
    }

    private String getNomorIdItem(int idItem){
        if (idItem < 10){
            return "0" + idItem;
        }
        return String.valueOf(idItem);
    }

    public String getIdJenisPembayaran(String cara){
        return switch (cara) {
            case "COD" -> "01";
            case "Gopay" -> "02";
            case "RiRAHPay" -> "44";
            default -> "00";
        };
    }

    private String getDateNow() {
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyy");
        Date date = new Date();
        return formatter.format(date);
    }

    private String getNomorJumlahBarang(int jumlah){
        if (jumlah < 10){
            return "00" + jumlah;
        } else if (jumlah < 100) {
            return "0" + jumlah;
        }
        return String.valueOf(jumlah);
    }

    private String getNomorEvent(int id){
        if (event.eventStatus(id)) {
            int nomor = Integer.parseInt(event.getCurrentEvent()[0]);
            if (nomor < 10) {
                return "00" + nomor;
            } else if (nomor < 100) {
                return "0" + nomor;
            }
            return String.valueOf(nomor);
        }
        return "000";
    }

    public String getNomorPesanan(String username, int idItem, String cara, int jumlah){
        return getRandomNumber() + getRandomNumber() + getNomorIdAccount(username) + getNomorIdItem(idItem) + "-"
                + getIdJenisPembayaran(cara) + getDateNow() + "-" + getNomorJumlahBarang(jumlah) + getNomorEvent(idItem) + getRandomNumber();
    }

    //QR code generator
    public void inputDataQR(String nomor) throws WriterException, IOException {
        String[] dataPesanan = getDataPesanan(nomor);
        String qrCodeText =
                "Pesanan Nomor     = " + nomor                                                     +
                "\nTanggal           = " + dataPesanan[3]                                          +
                "\nPembeli           = " + dataPesanan[2]                                          +
                "\nMetode Pembayaran = " + dataPesanan[7]                                          +
                "\nNama barang       = " + item.getDataItem(Integer.parseInt(dataPesanan[4]))[1]   +
                "\nJumlah barang     = " + dataPesanan[5]                                          +
                "\nHarga             = " + dataPesanan[6]                                          +
                "\nAlamat            = " + account.getDataAccount(dataPesanan[2])[7];

        String filePath = nomor +".jpeg";
        int size = 400;
        String fileType = "png";
        File qrFile = new File(filePath);
        createQRImage(qrFile, qrCodeText, size, fileType);
    }


    private void createQRImage(File qrFile, String qrCodeText, int size, String fileType)
            throws WriterException, IOException {
        Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<>();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix byteMatrix = qrCodeWriter.encode(qrCodeText, BarcodeFormat.QR_CODE, size, size, hintMap);
        int matrixWidth = byteMatrix.getWidth();
        BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
        image.createGraphics();

        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, matrixWidth, matrixWidth);
        graphics.setColor(Color.BLACK);
        for (int i = 0; i < matrixWidth; i++) {
            for (int j = 0; j < matrixWidth; j++) {
                if (byteMatrix.get(i, j)) {
                    graphics.fillRect(i, j, 1, 1);
                }
            }
        }
        ImageIO.write(image, fileType, qrFile);
    }
}
