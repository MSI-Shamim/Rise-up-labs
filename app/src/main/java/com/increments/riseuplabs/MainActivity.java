package com.increments.riseuplabs;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.increments.riseuplabs.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private NavController mNavController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.increments.riseuplabs.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mNavController = Navigation.findNavController(this, R.id.fragment2);
        binding.bottomNavView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            default:
            case R.id.menu_home:
                switchFragment(R.id.homeFragment);
                break;
            case R.id.menu_search:
                switchFragment(R.id.searchFragment);
                break;
            case R.id.menu_profile:
                switchFragment(R.id.profileFragment);
                break;
        }
        return true;
    }

    private void switchFragment(int id) {
        mNavController.navigate(id);
    }
}