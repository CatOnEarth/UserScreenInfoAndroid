package com.snail.userscreen;


import static com.snail.userscreen.ui.account.AccountFragment.APP_PREFERENCES;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.snail.userscreen.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements DrawerLayout.DrawerListener {

    private AppBarConfiguration mAppBarConfiguration;
    public TextView textViewUsernameNavView;
    public TextView textViewUserEmailNavView;
    private SharedPreferences mSettings;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        com.snail.userscreen.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        drawer.addDrawerListener(this);
        NavigationView navigationView = binding.navView;

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_account, R.id.nav_info, R.id.nav_image)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        View headerView = navigationView.getHeaderView(0);
        textViewUsernameNavView  = headerView.findViewById(R.id.textViewUsername);
        textViewUserEmailNavView = headerView.findViewById(R.id.textViewUserEmail);

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        textViewUsernameNavView.setText(mSettings.getString("username", ""));
        textViewUserEmailNavView.setText(mSettings.getString("useremail", ""));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            startSettingActivity();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void startSettingActivity() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
    }

    @Override
    public void onDrawerOpened(@NonNull View drawerView) {
        textViewUsernameNavView.setText(mSettings.getString("username", ""));
        textViewUserEmailNavView.setText(mSettings.getString("useremail", ""));
    }

    @Override
    public void onDrawerClosed(@NonNull View drawerView) {

    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }
}