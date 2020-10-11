package id.ac.ui.cs.mobileprogramming.natasyameidiana.helloworld.userView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import id.ac.ui.cs.mobileprogramming.natasyameidiana.helloworld.entity.UserData;
import id.ac.ui.cs.mobileprogramming.natasyameidiana.helloworld.databinding.FragmentUserDetailsBinding;

public class UserDetailFragment extends Fragment {

    public static UserDetailFragment newInstance(UserData user) {
        Bundle args = new Bundle();
        args.putSerializable("USERMODEL", user);
        UserDetailFragment fragment = new UserDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public UserDetailFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentUserDetailsBinding fragmentUserDetailsBinding =
                FragmentUserDetailsBinding.inflate(inflater, container, false);

        UserData model = (UserData) getArguments().getSerializable("USERMODEL");
        fragmentUserDetailsBinding.setUserData(model);
        return fragmentUserDetailsBinding.getRoot();
    }
}
