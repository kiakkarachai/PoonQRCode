package th.co.cpn.poon.utility;

/**
 * Created by kiakkarachai on 08/03/2018.
 */

public class MyConstance {
    private String urlReadAllFood = "http://androidthai.in.th/cent/getAllFood.php";
    private String urlAddUser = "http://androidthai.in.th/cent/addDataPoon.php";
    private String urlReadAllUser = "http://androidthai.in.th/cent/getAllDataPoon.php";

    private String[] columnUserTableStrings = new String[]{"id", "Name", "User", "Password"};


    public String getUrlReadAllFood() {
        return urlReadAllFood;
    }

    public String[] getColumnUserTableStrings() {
        return columnUserTableStrings;
    }

    public String getUrlAddUser() {
        return urlAddUser;
    }

    public String getUrlReadAllUser() {
        return urlReadAllUser;
    }
}
