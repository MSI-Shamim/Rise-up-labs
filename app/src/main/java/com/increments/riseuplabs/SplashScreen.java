package com.increments.riseuplabs;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashScreen extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View splashScreen = new EasySplashScreen(this)
                .withFullScreen()
                .withTargetActivity(AuthenticationActivity.class)
                .withFooterText(getString(R.string.copy_right))
                .withLogo(R.drawable.riseuplabs)
                .withAfterLogoText(getString(R.string.tag_line))
                .withSplashTimeOut(1000)
                .create();
        setContentView(splashScreen);
    }
}
