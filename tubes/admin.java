package tubes;

public class admin {
    account account = new account();
    user user = new user();
    item item = new item();
    pesanan pesanan = new pesanan();
    event event = new event();

    //Edit data
    //Edit data account dan user
    public String editDataAccountdanUser(String type, String username, String data) {
        boolean typeCheckAccount = type.equals("password") || type.equals("nama") || type.equals("Role") ||
                type.equals("email") || type.equals("nomor") || type.equals("alamat");
        boolean typeCheckUser = type.equals("namaPria") || type.equals("namaWanita") || type.equals("namaAyahPria") ||
                type.equals("namaIbuPria") || type.equals("namaAyahWanita") || type.equals("namaIbuWanita")
                || type.equals("tanggalAcara") || type.equals("alamatAcara");

        if (type.equals("Role") && !(data.equals("admin") || data.equals("user"))) {
            return "Pilih Role sebagai 'admin' atau 'user'";
        } else if (typeCheckAccount && account.checkUsernameAccount(username) && account.checkDataAccount(username, data)) {
            account.editDataAccount(type, username, data);
            return "Edit " + type + " " + username + " Berhasil";
        } else if (typeCheckUser && account.checkUsernameAccount(username) && user.checkDataUser(username, data)) {
            user.editDataUser(type, username, data);
            return "Edit " + type + " " + username + " Berhasil";
        } else if (type.equals("username") && account.checkUsernameAccount(username)) {
            account.editDataAccount(type, username, data);
            user.editDataUser(type, username, data);
            return "Edit " + type + " " + username + " Berhasil";
        } else if (type.equals("id")){
            return "Silahkan hubungi SuperAdmin untuk mengedit ID";
        } else if (!(typeCheckAccount || typeCheckUser)) {
            return "Tidak dapat melakukan edit karena type data yang anda masukan salah";
        } else if (!account.checkUsernameAccount(username)) {
            return "Tidak dapat melakukan edit karena username " + username + " tidak terdaftar";
        } else if (!user.checkDataUser(username, data) || !account.checkDataAccount(username, data)) {
            return "Tidak dapat melakukan edit karena " + type + " yang anda masukan sama";
        }
        return "Terjadi ERROR";
    }

    //Edit data item
    public String editDataItem(String type, int id, String data) {
        boolean typeCheck = type.equals("nama") || type.equals("gambar") || type.equals("harga") || type.equals("stock")
                || type.equals("detail");

        if (typeCheck && item.checkIdItem(id) && item.checkDataItem(id, data)) {
            item.editDataItem(type, id, data);
            return "Edit " + type + " item id " + id + " Berhasil";
        } else if (type.equals("id")) {
            return "Silahkan hubungi SuperAdmin untuk mengedit ID";
        } else if (!typeCheck) {
            return "Tidak dapat melakukan edit karena type data yang anda masukan salah";
        } else if (!item.checkIdItem(id)) {
            return "Tidak dapat melakukan edit karena id item " + id + " tidak terdaftar";
        } else if (!item.checkDataItem(id, data)) {
            return "Tidak dapat melakukan edit karena " + type + " yang anda masukan sama";
        }
        return "Terjadi ERROR";
    }

    //Kelola Pesanan
    public String confirmPesanan(String status, String nomor) {
//        if (!pesanan.timerPesanan(nomor)) {
            pesanan.confirmPesanan(status, nomor);
            return "Status Pesanan berhasil di update";
//        }
//        return "Tidak Bisa mengupdate status pesanan, Mohon menghubungi SuperAdmin";
    }

    //Edit data Event
    public String editDataEvent(String type,int idEvent,String data) {
        event.editEvent(type, idEvent, data);
        return "Event Berhasil di edit";
    }
}
