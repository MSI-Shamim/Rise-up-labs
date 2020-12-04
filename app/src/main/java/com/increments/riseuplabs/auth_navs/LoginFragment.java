package com.increments.riseuplabs.auth_navs;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.increments.riseuplabs.MainActivity;
import com.increments.riseuplabs.R;
import com.increments.riseuplabs.databinding.FragmentLoginBinding;
import com.increments.riseuplabs.models.User;
import com.increments.riseuplabs.viewmodels.SignedUserViewModel;
import com.increments.riseuplabs.viewmodels.UserViewModel;

import java.util.List;

public class LoginFragment extends Fragment implements View.OnClickListener {
    private FragmentLoginBinding mBinding;

    public LoginFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentLoginBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    private UserViewModel mViewModel;
    private SignedUserViewModel mSignedUserViewModel;
    private NavController mNavController;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //user view model
        mViewModel = new UserViewModel(requireActivity().getApplication());
        mSignedUserViewModel = new ViewModelProvider(requireActivity()).get(SignedUserViewModel.class);

        //navigation controller
        mNavController = Navigation.findNavController(view);

        //login & create account btn
        mBinding.loginBtn.setOnClickListener(this);
        mBinding.createAccountBtn.setOnClickListener(this);


        //privacy policy text
        String privacyPolicy = "By clicking login, you're agree to <b>terms & conditions</b> and <b>Privacy Policy</b> of Rise UP Labs.";
        mBinding.privacyPolicyTxt.setText(Html.fromHtml(privacyPolicy));


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.login_btn) {
            loginState();
        } else if (view.getId() == R.id.create_account_btn) {
            createAccountState();
        }
    }

    //manage account creation
    private void createAccountState() {
        mNavController.navigate(R.id.action_loginFragment_to_registrationFragment);
    }

    //manage login
    private void loginState() {
        String username = mBinding.usernameEt.getText().toString();
        if (TextUtils.isEmpty(username)) {
            mBinding.usernameEt.setError("Required | Min:5");
            mBinding.usernameEt.requestFocus();
            return;
        }

        String password = mBinding.passwordEt.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mBinding.passwordTil.setError("Required | Min:8");
            mBinding.passwordEt.requestFocus();
            return;
        }

        mViewModel.getUserLiveData().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                if (!users.isEmpty()){
                    for (User user : users){
                        if (user.getUsername().equals(username) && user.getPassword().equals(password)){
                            mSignedUserViewModel.setUser(user);
                            startDashboard();
                        }
                    }
                }
            }
        });
    }

    private void startDashboard() {
        Toast.makeText(requireContext(), "LoggedIn", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(requireActivity(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}