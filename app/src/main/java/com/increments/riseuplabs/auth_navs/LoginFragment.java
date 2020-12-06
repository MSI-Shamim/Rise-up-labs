package com.increments.riseuplabs.auth_navs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.increments.riseuplabs.MainActivity;
import com.increments.riseuplabs.R;
import com.increments.riseuplabs.databinding.FragmentLoginBinding;
import com.increments.riseuplabs.models.User;
import com.increments.riseuplabs.utils.RiseupDialog;
import com.increments.riseuplabs.viewmodels.UserViewModel;

import java.util.List;

import static com.increments.riseuplabs.utils.Constants.SHARED_PREF;
import static com.increments.riseuplabs.utils.Constants.S_DATE;
import static com.increments.riseuplabs.utils.Constants.S_NAME;
import static com.increments.riseuplabs.utils.Constants.S_PASSWORD;
import static com.increments.riseuplabs.utils.Constants.S_PHONE;
import static com.increments.riseuplabs.utils.Constants.S_USERNAME;

public class LoginFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "LoginFragment";
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
    private SharedPreferences mSharedPreferences;
    private NavController mNavController;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //user view model
        mViewModel = new UserViewModel(requireActivity().getApplication());
        mSharedPreferences = requireActivity().getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);

        //navigation controller
        mNavController = Navigation.findNavController(view);

        //login & create account btn
        mBinding.loginBtn.setOnClickListener(this);
        mBinding.createAccountBtn.setOnClickListener(this);

        //login with ime action -> go
        mBinding.passwordEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_GO) {
                    loginState();
                    return true;
                }
                return false;
            }
        });


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
        if (TextUtils.isEmpty(username) || (username.length() < 5)) {
            mBinding.usernameEt.setError("Required | Min:5");
            mBinding.usernameEt.requestFocus();
            return;
        }

        String password = mBinding.passwordEt.getText().toString();
        if (TextUtils.isEmpty(password) || (password.length() < 8)) {
            mBinding.passwordTil.setError("Required | Min:8");
            mBinding.passwordEt.requestFocus();
            return;
        }

        RiseupDialog dialog = new RiseupDialog(requireContext());
        dialog.showLoginDialog();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mViewModel.getUserLiveData().observe(requireActivity(), new Observer<List<User>>() {
                    @Override
                    public void onChanged(List<User> users) {
                        if (!users.isEmpty()) {
                            for (User user : users) {
                                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                                    SharedPreferences.Editor editor = mSharedPreferences.edit();
                                    editor.putString(S_NAME, user.getName());
                                    editor.putString(S_USERNAME, user.getUsername());
                                    editor.putString(S_PHONE, user.getPhone());
                                    editor.putString(S_PASSWORD, user.getPassword());
                                    editor.putString(S_DATE, user.getDate());
                                    editor.apply();
                                    startDashboard();
                                }
                            }
                        }
                    }
                });
            }
        }, 1000);
    }

    private void startDashboard() {
        Log.d(TAG, "LoggedIn");
        Intent intent = new Intent(requireActivity(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mSharedPreferences.getString(S_NAME, null) != null &&
                mSharedPreferences.getString(S_PASSWORD, null) != null) {
            startDashboard();
        }
    }
}