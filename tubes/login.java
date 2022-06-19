package tubes;

public class login extends account{
    String[][] dataUser;
    String[] loginUser = new String[2];

    //Login
    public boolean[] loginAccess(String username, String password) {
        dataUser = getAccount();
        loginUser[0] = username;
        loginUser[1] = password;
        try {
            System.out.println(dataUser[0]);
        } catch (Exception e) {
            return null;
        }
        for (String[] i : dataUser) {
            if (i[1].equals(loginUser[0]) && i[2].equals(loginUser[1])) {
                if (i[3].equals("admin")){
                    return new boolean[]{true,true};
                }
                else {
                    return new boolean[]{true,false};
                }
            }
        }
        return new boolean[]{false,false};
    }

    //Register
    public Object[] register(String[] dataRegisterUser) {
        try {
            for (String i : dataRegisterUser) {
                if (i.equals("")) {
                    return new Object[]{false, "Mohon isi semua kolom"};
                }
            }
            if (!checkUsernameAccount(dataRegisterUser[0])) {
                inputDataAccount(dataRegisterUser);
                return new Object[]{true, "Register Berhasil"};
            } else {
                return new Object[]{false, "Username sudah ada"};
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        return new Object[]{false, "Terjadi ERROR"};
    }

}
