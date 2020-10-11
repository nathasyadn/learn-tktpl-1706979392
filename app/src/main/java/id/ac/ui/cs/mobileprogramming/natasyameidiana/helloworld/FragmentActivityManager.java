package id.ac.ui.cs.mobileprogramming.natasyameidiana.helloworld;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import id.ac.ui.cs.mobileprogramming.natasyameidiana.helloworld.entity.UserData;
import id.ac.ui.cs.mobileprogramming.natasyameidiana.helloworld.userView.UserDetailFragment;
import id.ac.ui.cs.mobileprogramming.natasyameidiana.helloworld.userView.UserListFragment;

public class FragmentActivityManager extends AppCompatActivity implements UserListFragment.OnUserSelected {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main_activity);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.root_layout, UserListFragment.newInstance(), "userList")
                    .commit();
        }
    }

    @Override
    public void onUserSelected(UserData user) {
        UserDetailFragment detailsFragment = UserDetailFragment.newInstance(user);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.root_layout, detailsFragment, "userDetails")
                .addToBackStack(null)
                .commit();
    }
}