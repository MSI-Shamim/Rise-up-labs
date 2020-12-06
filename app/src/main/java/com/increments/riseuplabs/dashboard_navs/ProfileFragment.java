package com.increments.riseuplabs.dashboard_navs;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.increments.riseuplabs.AuthenticationActivity;
import com.increments.riseuplabs.R;
import com.increments.riseuplabs.databinding.FragmentProfileBinding;
import com.increments.riseuplabs.models.User;

import static com.increments.riseuplabs.utils.Constants.NOT_AVAILABLE;
import static com.increments.riseuplabs.utils.Constants.SHARED_PREF;
import static com.increments.riseuplabs.utils.Constants.S_DATE;
import static com.increments.riseuplabs.utils.Constants.S_NAME;
import static com.increments.riseuplabs.utils.Constants.S_PASSWORD;
import static com.increments.riseuplabs.utils.Constants.S_PHONE;
import static com.increments.riseuplabs.utils.Constants.S_USERNAME;

public class ProfileFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "ProfileFragment";

    public ProfileFragment() {
        // Required empty public constructor
    }

    private FragmentProfileBinding mBinding;
    private SharedPreferences mSharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentProfileBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSharedPreferences = requireActivity().getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        User user = getUserData();

        mBinding.name.setText(Html.fromHtml(requireActivity().getString(R.string.str_name, user.getName())));
        mBinding.phone.setText(Html.fromHtml(requireActivity().getString(R.string.str_phone, user.getPhone())));
        mBinding.username.setText(Html.fromHtml(requireActivity().getString(R.string.str_username, user.getUsername())));
        mBinding.date.setText(Html.fromHtml(requireActivity().getString(R.string.str_date, user.getDate())));

        mBinding.changeProfilePhoto.setOnClickListener(this);
        mBinding.logout.setOnClickListener(this);
        mBinding.showUsers.setOnClickListener(this);
        mBinding.settings.setOnClickListener(this);

    }

    private User getUserData() {
        return new User(
                mSharedPreferences.getString(S_NAME, null),
                mSharedPreferences.getString(S_USERNAME, null),
                mSharedPreferences.getString(S_PASSWORD, null),
                mSharedPreferences.getString(S_PHONE, null),
                mSharedPreferences.getString(S_DATE, null)
        );
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.change_profile_photo:
            case R.id.settings:
                showSnackbar();
                break;
            case R.id.show_users:
                break;
            case R.id.logout:
                new AlertDialog.Builder(requireContext())
                        .setMessage(R.string.alert_logout)
                        .setNegativeButton(R.string.cancel, null)
                        .setPositiveButton(R.string.logout, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                logoutState();
                            }
                        }).create().show();
                break;
        }

    }

    //random snack bar
    private void showSnackbar() {
        Snackbar.make(requireView(), NOT_AVAILABLE, Snackbar.LENGTH_LONG).show();
    }

    //logout state
    private void logoutState() {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.clear();
        editor.apply();
        Intent intent = new Intent(requireActivity(), AuthenticationActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


}