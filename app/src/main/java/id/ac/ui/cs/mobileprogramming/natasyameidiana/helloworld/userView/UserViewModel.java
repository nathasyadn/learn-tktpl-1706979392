package id.ac.ui.cs.mobileprogramming.natasyameidiana.helloworld.userView;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import id.ac.ui.cs.mobileprogramming.natasyameidiana.helloworld.entity.UserData;
import id.ac.ui.cs.mobileprogramming.natasyameidiana.helloworld.database.DatabaseRepository;


import java.util.List;

public class UserViewModel extends ViewModel {
    private MutableLiveData<List<UserData>> userDataList = new MutableLiveData<>();
    private DatabaseRepository repository = new DatabaseRepository();
    private MutableLiveData<UserData> selectedUser = new MutableLiveData<>();

    public long addUser(Context context, String name, String email, String description) {
        return repository.addUser(context, name, email, description);
    }

    public void setUserDataList(Context context) {
        List<UserData> userList = repository.getUserData(context);
        userDataList.setValue(userList);
    }

    public LiveData<List<UserData>> gerUserDataList() {
        return userDataList;
    }

    public LiveData<UserData> getUserDetail(String name) {
        getUser(name);
        return selectedUser;
    }

    private void getUser(String name) {
        selectedUser.setValue(repository.getUserDetail(name));
    }
}