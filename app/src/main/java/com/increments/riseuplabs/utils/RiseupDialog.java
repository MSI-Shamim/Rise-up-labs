package com.increments.riseuplabs.utils;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.increments.riseuplabs.R;

public class RiseupDialog extends AlertDialog.Builder {
    private AlertDialog.Builder mBuilder;
    private AlertDialog mDialog;

    public RiseupDialog(@NonNull Context context) {
        super(context);
        mBuilder = new AlertDialog.Builder(context);
    }

    public void showLoginDialog() {
        mBuilder.setView(R.layout.item_riseup_loading);
        mDialog = mBuilder.create();
        mDialog.show();
    }

}
