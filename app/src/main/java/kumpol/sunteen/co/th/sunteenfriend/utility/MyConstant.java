package kumpol.sunteen.co.th.sunteenfriend.utility;

public class MyConstant {
    private String hostFTP = "ftp.androidthai.in.th";
    private String userFTP = "sun@androidthai.in.th";
    private String passwordFTP = "Abc12345";
    private int portFTP = 21;
    private  String UrlImage = "http://androidthai.in.th/sun/jochi/";
//    private String UrlAddUser = "http://androidthai.in.th/sun/addDataJochi.php";
    private String UrlAddUser = "http://androidthai.in.th/sun/addDataMaster.php";
    private String urlGetAllUser = "http://androidthai.in.th/sun/getAllUser.php";

    public String getUrlGetAllUser() {
        return urlGetAllUser;
    }

    public String getUrlAddUser() {
        return UrlAddUser;
    }

    public String getUrlImage() {
        return UrlImage;
    }

    public String getHostFTP() {
        return hostFTP;
    }

    public String getUserFTP() {
        return userFTP;
    }

    public String getPasswordFTP() {
        return passwordFTP;
    }

    public int getPortFTP() {
        return portFTP;
    }
}
