package id.ac.ui.cs.mobileprogramming.natasyameidiana.helloworld.userView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import id.ac.ui.cs.mobileprogramming.natasyameidiana.helloworld.entity.UserData;
import id.ac.ui.cs.mobileprogramming.natasyameidiana.helloworld.R;
import id.ac.ui.cs.mobileprogramming.natasyameidiana.helloworld.databinding.RecyclerUserModelBinding;

import java.util.ArrayList;
import java.util.List;

public class UserListFragment extends Fragment {
    private List<String> names = new ArrayList<>();
    private List<String> emails = new ArrayList<>();
    private List<String> descriptions = new ArrayList<>();
    private OnUserSelected listener;

    public interface OnUserSelected {
        void onUserSelected(UserData userData);
    }

    public UserListFragment() {
    }

    public static UserListFragment newInstance() {
        return new UserListFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (OnUserSelected) context;

        UserViewModel viewModel = ViewModelProviders.of(getActivity()).get(UserViewModel.class);
        viewModel.setUserDataList(context);
        viewModel.gerUserDataList().observe(this, new Observer<List<UserData>>() {
            @Override
            public void onChanged(List<UserData> users) {
                for (UserData userData : users) {
                    names.add(userData.getName());
                    emails.add(userData.getEmail());
                    descriptions.add(userData.getDescription());
                }
            }
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        UserViewModel viewModel = ViewModelProviders.of(getActivity()).get(UserViewModel.class);
        View view = inflater.inflate(R.layout.list_user, container, false);
        Context activity = getActivity();
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(activity, 2));
        recyclerView.setAdapter(new UserListAdapter(activity, viewModel));

        return view;
    }

    public class UserListAdapter extends RecyclerView.Adapter<ViewHolder> {
        private LayoutInflater layoutInflater;
        private UserViewModel viewModelUser;

        public UserListAdapter(Context context, UserViewModel viewModelUser) {
            this.layoutInflater = LayoutInflater.from(context);
            this.viewModelUser = viewModelUser;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            RecyclerUserModelBinding recyclerUserModelBinding =
                    RecyclerUserModelBinding.inflate(layoutInflater, parent, false);
            return new ViewHolder(recyclerUserModelBinding.getRoot(), recyclerUserModelBinding);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
            final UserData user = new UserData(names.get(position), descriptions.get(position), descriptions.get(position));
            holder.setData(user);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    viewModelUser.getUserDetail(names.get(position)).observe(getActivity(), new Observer<UserData>() {
                                @Override
                                public void onChanged(UserData userdata) {
                                    listener.onUserSelected(userdata);
                                }
                            }
                    );
                }
            });
        }

        @Override
        public int getItemCount() {
            return names.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private RecyclerUserModelBinding recyclerItemUserListBinding;

        public ViewHolder(View itemView, RecyclerUserModelBinding recyclerUserModelBinding) {
            super(itemView);
            this.recyclerItemUserListBinding = recyclerUserModelBinding;
        }

        public void setData(UserData userData) {
            recyclerItemUserListBinding.setUser(userData);
        }
    }
}
