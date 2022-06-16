package tubes;

import com.google.zxing.WriterException;

import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;

public class main extends javax.swing.JFrame {
    static String loginUser;
    static int id;
    static String cara , nomorQR;

    final static Page.login loginPage = new Page.login();
    final static Page.Register registerPage = new Page.Register();
    final static Page.tampilanUndangan undanganPage = new Page.tampilanUndangan();
    final static Page.akunUser akunPage = new Page.akunUser();
    final static Page.historyPemesanan historyPage = new Page.historyPemesanan();
    final static Page.pesananGolden history1 = new Page.pesananGolden();
    final static Page.pesananBlackWhite history2 = new Page.pesananBlackWhite();
    final static Page.pesananRoseLove history3 = new Page.pesananRoseLove();
    final static Page.kompensasi notifKompensasi = new Page.kompensasi();
    final static Page.notifRegister notifRegister = new Page.notifRegister();
    final static Page.notifSelamat notifSelamat = new Page.notifSelamat();
    final static Page.detailUndangan1 detailUndangan1 = new Page.detailUndangan1();
    final static Page.detailUndangan2 detailUndangan2 = new Page.detailUndangan2();
    final static Page.detailUndangan3 detailUndangan3 = new Page.detailUndangan3();
    final static Page.detailWedding dataDiri = new Page.detailWedding();
    final static Page.orderPage orderPage = new Page.orderPage();
    final static Page.confirmPesanan confirmPage = new Page.confirmPesanan();
    final static Page.notifDiterima notidTerima = new Page.notifDiterima();
    final static Page.uiAdmin adminPage = new Page.uiAdmin();
    final static Page.kelolaAkun kelolaAkun = new Page.kelolaAkun();
    final static Page.kelolaPemesanan kelolaPemesanan = new Page.kelolaPemesanan();
    final static Page.kelolaItem kelolaItem = new Page.kelolaItem();
    final static Page.kelolaEvent kelolaEvent = new Page.kelolaEvent();
    final static Page.notifBerhasil notifBerhasil = new Page.notifBerhasil();

    final static login login = new login();
    final static account account = new account();
    final static user user = new user();
    final static item item = new item();
    final static event event = new event();
    final static pesanan pesanan = new pesanan();
    final static admin admin = new admin();

    //LoginPage
    public static void main(String[] args) {
        loginPage.setVisible(true);
    }

    public static void tombolLogin() {
        String user = loginPage.username.getText();
        String pass = String.valueOf(loginPage.password.getPassword());
        boolean[] loginAccess = login.loginAccess(user, pass);
        if (loginAccess == null) {
            loginPage.username.setText("");
            loginPage.password.setText("");
            loginPage.notif.setText("Anda sedang tidak terhubung kepada server");
            loginPage.notif.setForeground(Color.red);
        }

        if (loginAccess[0]) {
            if (loginAccess[1]) {
                adminPage.setVisible(true);
                loginPage.setVisible(false);
                loginUser = null;
            } else {
                loginUser = user;
                loginPage.username.setText("");
                loginPage.password.setText("");
                loginPage.setVisible(false);
                tampilanUndangan();
            }
        } else {
            loginPage.username.setText("");
            loginPage.password.setText("");
            loginPage.notif.setText("Username/Password yang anda masukan salah");
            loginPage.notif.setForeground(Color.red);
        }
    }

    //Register Page
    public static void tombolRegister() {
        loginPage.setVisible(false);
        registerPage.setVisible(true);
    }

    public static void confirmRegister() {
        String[] dataRegister = new String[6];
        dataRegister[0] = registerPage.username.getText();
        dataRegister[1] = registerPage.password.getText();
        dataRegister[2] = registerPage.nama.getText();
        dataRegister[3] = registerPage.email.getText();
        dataRegister[4] = registerPage.noHp.getText();
        dataRegister[5] = registerPage.alamat.getText();
        Object[] confirmRegister = login.register(dataRegister);
        if ((Boolean) confirmRegister[0]) {
            account.bonus(dataRegister[0]);
            notifSelamat.setVisible(true);
        }
        notifRegister((Boolean) confirmRegister[0], (String) confirmRegister[1]);
    }

    public static void backRegister() {
        loginPage.setVisible(true);
        registerPage.setVisible(false);
    }

    //notif Register
    public static void notifRegister(boolean confirm, String notif) {
        notifRegister.setVisible(true);
        if (confirm) {
            registerPage.username.setText("");
            registerPage.password.setText("");
            registerPage.nama.setText("");
            registerPage.email.setText("");
            registerPage.noHp.setText("");
            registerPage.alamat.setText("");
            notifRegister.notifRegis.setText(notif);
            loginPage.setVisible(true);
            registerPage.setVisible(false);
        } else {
            registerPage.username.setText("");
            registerPage.password.setText("");
            registerPage.nama.setText("");
            registerPage.email.setText("");
            registerPage.noHp.setText("");
            registerPage.alamat.setText("");
            notifRegister.setVisible(true);
            notifRegister.notifRegis.setText(notif);
        }
    }

    public static void notifRegisterClose() {
        notifRegister.setVisible(false);
    }

    public static void notifSelamatClose() {
        notifSelamat.setVisible(false);
    }

    //Login User
    //TampilanUndangan Page
    public static void tampilanUndangan() {
        undanganPage.setVisible(true);
    }

    public static void account() {
        akunPage.setVisible(true);
        String[] dataAccount = account.getDataAccount(loginUser);
        akunPage.userName.setText(dataAccount[1]);
        akunPage.rirahPay.setText(dataAccount[9]);
        akunPage.namaUser.setText(dataAccount[4]);
        akunPage.emailUser.setText(dataAccount[5]);
    }

    public static void backAccount() {
        akunPage.setVisible(false);
    }

    public static void logout() {
        loginUser = null;
        akunPage.setVisible(false);
        undanganPage.setVisible(false);
        loginPage.setVisible(true);
    }

    public static void history() {
        historyPage.setVisible(true);
        int jumlahPesanan = pesanan.getJumlahPesananUser(loginUser);
        historyPage.pesanan1.setText("Tidak ada pemesanan");
        historyPage.pesanan2.setText("Tidak ada pemesanan");
        historyPage.pesanan3.setText("Tidak ada pemesanan");
        historyPage.pesanan4.setText("Tidak ada pemesanan");
        if (jumlahPesanan > 0) {
            String[] nomor = pesanan.getNomorPesananUser(loginUser);
            switch (jumlahPesanan) {
                case 4 : historyPage.pesanan4.setText("Pesanan " + nomor[3]);
                case 3 : historyPage.pesanan3.setText("Pesanan " + nomor[2]);
                case 2 : historyPage.pesanan2.setText("Pesanan " + nomor[1]);
                case 1 : historyPage.pesanan1.setText("Pesanan " + nomor[0]);
            }
        }
    }

    public static void backHistory() {
        historyPage.setVisible(false);
    }

    public static void viewHistory(int id){
        int jumlahPesanan = pesanan.getJumlahPesananUser(loginUser);
        if (id <= jumlahPesanan) {
            String[] nomor = pesanan.getNomorPesananUser(loginUser);
            String[][] daftarPesanan = pesanan.getDaftarPesanan(nomor, loginUser);
            for (String i : nomor) {
                timer(i);
            }
            if (id == 1 && jumlahPesanan >= 1) {
                nomorQR = daftarPesanan[0][1];
                String[] pesanan = daftarPesanan[0];
                int item = Integer.parseInt(pesanan[4]);
                if (item == 1) {
                    history1.setVisible(true);
                    history1.jumlah.setText(pesanan[5] + " pcs");
                    history1.cara.setText(pesanan[6]);
                    history1.harga.setText("Rp " + pesanan[7]);
                    history1.status.setText(pesanan[8]);
                } else if (item == 2) {
                    history2.setVisible(true);
                    history2.jumlah.setText(pesanan[5] + " pcs");
                    history2.cara.setText("Rp " + pesanan[7]);
                    history2.harga.setText(pesanan[6]);
                    history2.status.setText(pesanan[8]);
                } else if (item == 3) {
                    history3.setVisible(true);
                    history3.jumlah.setText(pesanan[5] + " pcs");
                    history3.cara.setText(pesanan[7]);
                    history3.harga.setText("Rp " + pesanan[6]);
                    history3.status.setText(pesanan[8]);
                }
            } else if (id == 2 && jumlahPesanan >= 2) {
                nomorQR = daftarPesanan[1][1];
                String[] pesanan = daftarPesanan[1];
                int item = Integer.parseInt(pesanan[4]);
                if (item == 1) {
                    history1.setVisible(true);
                    history1.jumlah.setText(pesanan[5] + " pcs");
                    history1.cara.setText(pesanan[6]);
                    history1.harga.setText("Rp " + pesanan[7]);
                    history1.status.setText(pesanan[8]);
                } else if (item == 2) {
                    history2.setVisible(true);
                    history2.jumlah.setText(pesanan[5] + " pcs");
                    history2.cara.setText("Rp " + pesanan[7]);
                    history2.harga.setText(pesanan[6]);
                    history2.status.setText(pesanan[8]);
                } else if (item == 3) {
                    history3.setVisible(true);
                    history3.jumlah.setText(pesanan[5] + " pcs");
                    history3.cara.setText(pesanan[7]);
                    history3.harga.setText("Rp " + pesanan[6]);
                    history3.status.setText(pesanan[8]);
                }
            } else if (id == 3 && jumlahPesanan >= 3) {
                nomorQR = daftarPesanan[2][1];
                String[] pesanan = daftarPesanan[2];
                int item = Integer.parseInt(pesanan[4]);
                if (item == 1) {
                    history1.setVisible(true);
                    history1.jumlah.setText(pesanan[5] + " pcs");
                    history1.cara.setText(pesanan[6]);
                    history1.harga.setText("Rp " + pesanan[7]);
                    history1.status.setText(pesanan[8]);
                } else if (item == 2) {
                    history2.setVisible(true);
                    history2.jumlah.setText(pesanan[5] + " pcs");
                    history2.cara.setText("Rp " + pesanan[7]);
                    history2.harga.setText(pesanan[6]);
                    history2.status.setText(pesanan[8]);
                } else if (item == 3) {
                    history3.setVisible(true);
                    history3.jumlah.setText(pesanan[5] + " pcs");
                    history3.cara.setText(pesanan[7]);
                    history3.harga.setText("Rp " + pesanan[6]);
                    history3.status.setText(pesanan[8]);
                }
            } else if (id == 4 && jumlahPesanan >= 4) {
                nomorQR = daftarPesanan[3][1];
                String[] pesanan = daftarPesanan[3];
                int item = Integer.parseInt(pesanan[4]);
                if (item == 1) {
                    history1.setVisible(true);
                    history1.jumlah.setText(pesanan[5] + " pcs");
                    history1.cara.setText(pesanan[6]);
                    history1.harga.setText("Rp " + pesanan[7]);
                    history1.status.setText(pesanan[8]);
                } else if (item == 2) {
                    history2.setVisible(true);
                    history2.jumlah.setText(pesanan[5] + " pcs");
                    history2.cara.setText(pesanan[6]);
                    history2.harga.setText("Rp " + pesanan[7]);
                    history2.status.setText(pesanan[8]);
                } else if (item == 3) {
                    history3.setVisible(true);
                    history3.jumlah.setText(pesanan[5] + " pcs");
                    history3.cara.setText(pesanan[6]);
                    history3.harga.setText("Rp " + pesanan[7]);
                    history3.status.setText(pesanan[8]);
                }
            }
        }
    }

    public static void tampilkanQR(){
        Page.tampilkanQR QRPage = new Page.tampilkanQR(nomorQR);
        QRPage.setVisible(true);
    }

    public static void timer(String nomor){
        Object[] timer = pesanan.notifkompensasi(loginUser, nomor, pesanan.timerPesanan(nomor));
        if ((Boolean) timer[0]) {
            notifKompensasi.setVisible(true);
        }

    }

    public static void closeKompensasi(){
        notifKompensasi.setVisible(false);
    }

    public static void backViewHistory(int id) {
        switch (id) {
            case 1 :
                history1.setVisible(false);
                break;
            case 2 :
                history2.setVisible(false);
                break;
            case 3 :
                history3.setVisible(false);
                break;
        }
    }

    public static void beli1() {
        undanganPage.setVisible(false);
        detailUndangan1.setVisible(true);
        String[] dataItem = item.getDataItem(1);
        if (event.eventStatus(1)) {
            String[] dataEvent = event.eventBarang(1);
            detailUndangan1.label.setText("Diskon Hingga ");
            detailUndangan1.persenDiskon.setText(dataEvent[2] + "%");
            detailUndangan1.upTo.setText("Up to ");
            detailUndangan1.diskonHarga.setText("Rp " + dataEvent[3]);
            detailUndangan1.minPembelian.setText("Minimal pembelian " + dataEvent[5] + "pcs");
        }
        detailUndangan1.harga.setText("Rp " + dataItem[2] + " /pcs");
        detailUndangan1.stock.setText(dataItem[3]);
        id = 1;
    }

    public static void beli2() {
        undanganPage.setVisible(false);
        detailUndangan2.setVisible(true);
        String[] dataItem = item.getDataItem(2);
        if (event.eventStatus(2)) {
            String[] dataEvent = event.eventBarang(2);
            detailUndangan2.label.setText("Diskon Hingga ");
            detailUndangan2.persenDiskon.setText(dataEvent[2] + "%");
            detailUndangan2.upTo.setText("Up to ");
            detailUndangan2.diskonHarga.setText("Rp " + dataEvent[3]);
            detailUndangan2.minPembelian.setText("Minimal pembelian " + dataEvent[5] + "pcs");
        }
        detailUndangan2.harga.setText("Rp " + dataItem[2] + " /pcs");
        detailUndangan2.stock.setText(dataItem[3]);
        id = 2;
    }

    public static void beli3() {
        undanganPage.setVisible(false);
        detailUndangan3.setVisible(true);
        String[] dataItem = item.getDataItem(3);
        if (event.eventStatus(3)) {
            String[] dataEvent = event.eventBarang(3);
            detailUndangan3.label.setText("Diskon Hingga ");
            detailUndangan3.persenDiskon.setText(dataEvent[2] + "%");
            detailUndangan3.upTo.setText("Up to ");
            detailUndangan3.diskonHarga.setText("Rp " + dataEvent[3]);
            detailUndangan3.minPembelian.setText("Minimal pembelian " + dataEvent[5] + "pcs");
        }
        detailUndangan3.harga.setText("Rp " + dataItem[2] + " /pcs");
        detailUndangan3.stock.setText(dataItem[3]);
        id = 3;
    }

    public static void backBeli(int id) {
        if (id == 1) {
            detailUndangan1.setVisible(false);
        } else if (id == 2) {
            detailUndangan2.setVisible(false);
        } else if (id == 3) {
            detailUndangan3.setVisible(false);
        }
        undanganPage.setVisible(true);
    }

    //isi Data Diri
    public static void dataDiri() {
        if (user.checkUsername(loginUser)) {
            order();
            detailUndangan1.setVisible(false);
            detailUndangan2.setVisible(false);
            detailUndangan3.setVisible(false);
        } else {
            dataDiri.setVisible(true);
            detailUndangan1.setVisible(false);
            detailUndangan2.setVisible(false);
            detailUndangan3.setVisible(false);
        }
    }

    public static void tombolInput() {
        String[] dataUser = new String[8];
        dataUser[0] = dataDiri.NamaPria.getText();
        dataUser[1] = dataDiri.NamaWanita.getText();
        dataUser[2] = dataDiri.namaAyahPria.getText();
        dataUser[3] = dataDiri.namaAyahWanita.getText();
        dataUser[4] = dataDiri.namaIbuPria.getText();
        dataUser[5] = dataDiri.namaIbuWanita.getText();
        dataUser[6] = dataDiri.HariTanggal.getText() + " " + dataDiri.waktuAcara.getText();
        dataUser[7] = dataDiri.alamat.getText();
        Object[] confirm = user.confirmInputDataDiri(dataUser);
        if ((Boolean) confirm[0]) {
            user.inputDataUser(loginUser, dataUser);
            notifRegister.setVisible(true);
            notifRegister.notifRegis.setText((String) confirm[1]);
            dataDiri.setVisible(false);
            order();
        } else {
            notifRegister.setVisible(true);
            notifRegister.notifRegis.setText((String) confirm[1]);
        }
    }

    public static void backInput() {
        dataDiri.setVisible(false);
        if (id == 1) {
            detailUndangan1.setVisible(true);
        } else if (id == 2) {
            detailUndangan2.setVisible(true);
        } else if (id == 3) {
            detailUndangan3.setVisible(true);
        }
    }

    //Order
    public static void order() {
        orderPage.setVisible(true);
        dataDiri.setVisible(false);
        if (event.eventStatus(id)) {
            String[] dataEvent = event.eventBarang(id);
            orderPage.jumlahDiskon.setText("Diskon Hingga " + dataEvent[2] + "%");
            orderPage.UpTo.setText("Up to Rp " + dataEvent[3]);
            orderPage.hargaDiskon.setText("Minimal pembelian " + dataEvent[5] + "pcs");
        } else {
            orderPage.UpTo.setText("Tidak ada Diskon untuk item ini");
        }
        orderPage.stock.setText(item.getDataItem(id)[3]);
    }

    public static void methodBayar(int id) {
        if (id == 1) {
            cara = "COD";
            orderPage.metodePembayaran.setText("Cash On Delivery");
        } else if (id == 2) {
            cara = "Gopay";
            orderPage.metodePembayaran.setText("GoPay");
        } else if (id == 3) {
            cara = "RiRAHPay";
            orderPage.metodePembayaran.setText("RiRAHPay");
        }
    }

    public static void setHarga(int jumlah) {
        Integer[] harga = item.getHarga(id, jumlah);
        orderPage.totalPembayaran.setText(String.valueOf(harga[3]));
        orderPage.hemat.setText(String.valueOf(harga[2]));
    }

    public static void batalBayar() {
        orderPage.setVisible(false);
        undanganPage.setVisible(true);
        orderPage.totalPembayaran.setText("");
        orderPage.hemat.setText("");
    }

    //Konfirmasi Pembayaran
    public static void confirmBayar() {
        int jumlah = Integer.parseInt(orderPage.jumlahPesanan.getText());
        if (jumlah <= Integer.parseInt(item.getDataItem(id)[3])) {
            if (!(cara == null)) {
                confirmPage.setVisible(true);
            } else {
                notifRegister.setVisible(true);
                notifRegister.notifRegis.setText("Pilih Metode Pembayaran");
            }
        } else {
            notifRegister.setVisible(true);
            notifRegister.notifRegis.setText("Stock Tidak Cukup");
            orderPage.totalPembayaran.setText("");
            orderPage.hemat.setText("");
            orderPage.jumlahPesanan.setText("");
        }
    }

    public static void backConfirm(){
        confirmPage.setVisible(false);
    }

    public static void tombolConfirm() throws IOException, WriterException {
        boolean[] access = login.loginAccess(loginUser, String.valueOf(confirmPage.inputPassword.getPassword()));
        if (access[0]) {
            confirmPage.setVisible(false);
            String password = String.valueOf(confirmPage.inputPassword.getPassword());
            int jumlah = Integer.parseInt(orderPage.jumlahPesanan.getText());
            Integer[] harga = item.getHarga(id, jumlah);
            pesan(password ,cara ,harga[3] ,jumlah);
        } else {
            notifRegister.setVisible(true);
            notifRegister.notifRegis.setText("Password Salah");
        }
    }

    public static void pesan(String password, String cara, int harga, int jumlah) throws IOException, WriterException {
        if (cara.equals("RiRAHPay")) {
            Object[] RiRAHPay = account.pakaiSaldo(loginUser, password, harga);
            if ((Boolean) RiRAHPay[0]) {
                item.takeStock(id, jumlah);
                String nomor = pesanan.inputDataPesanan(loginUser, id, cara, jumlah, harga);
                notidTerima.setVisible(true);
                pesanan.inputDataQR(nomor);
            } else {
                notifRegister.notifRegis.setText((String) RiRAHPay[1]);
                orderPage.totalPembayaran.setText("");
                orderPage.hemat.setText("");
                orderPage.jumlahPesanan.setText("");
            }
        } else {
            item.takeStock(id, jumlah);
            String nomor = pesanan.inputDataPesanan(loginUser, id, cara, jumlah, harga);
            pesanan.inputDataQR(nomor);
            notidTerima.setVisible(true);
        }
    }

    public static void diTerima(){
        notidTerima.setVisible(false);
        orderPage.setVisible(false);
        orderPage.totalPembayaran.setText("");
        orderPage.hemat.setText("");
        orderPage.jumlahPesanan.setText("");
        cara = null;
        undanganPage.setVisible(true);
    }

    //login Admin
    public static void kelolaAkundanUser(){
        adminPage.setVisible(false);
        kelolaAkun.setVisible(true);
        String[][] data = account.getAccount();
        DefaultTableModel tableModel = (DefaultTableModel) kelolaAkun.tabelAkun.getModel();
        for (String[] i : data) {
            tableModel.addRow(new Object[]{i[0],i[1],i[2],i[3],i[4],i[5],i[6],i[7],i[8],i[9]});
        }
    }

    public static void kelolaEvent(){
        adminPage.setVisible(false);
        kelolaEvent.setVisible(true);
        String[][] data = event.getEvent();
        DefaultTableModel tableModel = (DefaultTableModel) kelolaEvent.tabelEvent.getModel();
        for (String[] i : data) {
            tableModel.addRow(new Object[]{i[0],i[1],i[2],i[3],i[4],i[5],i[6],i[7],i[8],i[9]});
        }
    }

    public static void kelolaItem(){
        adminPage.setVisible(false);
        kelolaItem.setVisible(true);
        String[][] data = item.getItem();
        DefaultTableModel tableModel = (DefaultTableModel) kelolaItem.tabelItem.getModel();
        for (String[] i : data) {
            tableModel.addRow(new Object[]{i[0],i[1],i[2],i[3],i[4],i[5]});
        }
    }

    public static void kelolaPemesanan(){
        adminPage.setVisible(false);
        kelolaPemesanan.setVisible(true);
        String[][] data = pesanan.getPesanan();
        DefaultTableModel tableModel = (DefaultTableModel) kelolaPemesanan.jTable1.getModel();
        for (String[] i : data) {
            tableModel.addRow(new Object[]{i[0],i[1],i[2],i[3],i[4],i[5],i[6],i[7],i[8],i[9],i[10]});
        }
    }

    public static void editDataAkun(){
        String type = (String) kelolaAkun.pilihDetailEdit.getSelectedItem();
        String username = kelolaAkun.pilihAkunEdit.getText();
        String data = kelolaAkun.dataBaru.getText();
        String notif = admin.editDataAccountdanUser(type, username, data);
        kelolaAkun.pilihAkunEdit.setText("");
        kelolaAkun.dataBaru.setText("");
        notifBerhasil.setVisible(true);
        notifBerhasil.notif.setText(notif);
    }

    public static void editDataItem(){
        String type = (String) kelolaItem.piilihItem.getSelectedItem();
        int id = Integer.parseInt(kelolaItem.pilihId.getText());
        String data = kelolaItem.dataBaru.getText();
        String notif = admin.editDataItem(type, id, data);
        notifBerhasil.setVisible(true);
        notifBerhasil.notif.setText(notif);
    }

    public static void confirmPesanan(){
        String status = (String) kelolaPemesanan.jComboBox1.getSelectedItem();
        String nomor = kelolaPemesanan.nomorPemesanan.getText();
        String notif = admin.confirmPesanan(status, nomor);
        notifBerhasil.setVisible(true);
        notifBerhasil.notif.setText(notif);
    }

    public static void backEdit(int id){
        adminPage.setVisible(true);
        switch (id) {
            case 1 :
                kelolaAkun.setVisible(false);
                break;
            case 2 :
                kelolaEvent.setVisible(false);
            case 3 :
                kelolaItem.setVisible(false);
                break;
            case 4 :
                kelolaPemesanan.setVisible(false);
                break;
        }
    }

    public static void backNotif(){
        notifBerhasil.setVisible(false);
    }
}
