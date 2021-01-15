package id.ac.ui.cs.mobileprogramming.natasyameidiana.helloworld.database;

import android.content.Context;

import id.ac.ui.cs.mobileprogramming.natasyameidiana.helloworld.entity.UserData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseRepository {
    private Map<String, UserData> userDataMap = new HashMap<>();

    public List<UserData> getUserData(Context context) {
        DbHelper helper = new DbHelper(context);
        List<UserData> userData = helper.getData();
        for (UserData user : userData) {
            userDataMap.put(user.getName(), user);
        }
        return userData;
    }

    public long addUser(Context context, String name, String email, String description) {
        DbHelper helper = new DbHelper(context);
        return helper.insertData(name, email, description);
    }

    public UserData getUserDetail(String name) {
        return userDataMap.get(name);
    }
}
