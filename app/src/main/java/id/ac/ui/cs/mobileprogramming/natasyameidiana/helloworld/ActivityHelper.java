package id.ac.ui.cs.mobileprogramming.natasyameidiana.helloworld;

public class ActivityHelper {
    public static String giveResult(String name, String phone) {
        if (isNullOrEmpty(name) || isNullOrEmpty(phone)) return "Name or Phone must be filled, found: name:" + name + " phone:" + phone;
        if (!validPhoneNumber(phone)) return "Phone number is not valid";
        else return "Success";
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str == "null" || str.isEmpty();
    }

    public static boolean validPhoneNumber(String phone) {
        return (phone.length() == 11 || phone.length() == 12);
    }
}
