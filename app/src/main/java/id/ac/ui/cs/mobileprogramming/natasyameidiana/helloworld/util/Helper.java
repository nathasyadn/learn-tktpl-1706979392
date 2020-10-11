package id.ac.ui.cs.mobileprogramming.natasyameidiana.helloworld.util;

public class Helper {
    public static boolean isNotNullAndNotEmpty(String str) {
        return str != null && !str.equals("null") && !str.isEmpty();
    }
}