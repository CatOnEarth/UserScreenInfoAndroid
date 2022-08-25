package com.snail.userscreen;


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

/**
 * Main class of application
 */
public class MainActivity extends AppCompatActivity implements DrawerLayout.DrawerListener {

    /** The Openable layout indicating that the Navigation button should be displayed as a drawer symbol when it is not being shown as an Up button */
    private AppBarConfiguration mAppBarConfiguration;

    /** TextView which display username in NavigationView */
    private TextView textViewUsernameNavView;
    /** TextView which display user's email in NavigationView */
    private TextView textViewUserEmailNavView;

    /** Object points to a file containing key-value pairs and provides simple methods to read and write them */
    private SharedPreferences mSettings;
    /** Name file to save preferences */
    public static final String APP_PREFERENCES            = "user_info";
    /** Key to save username in preferences */
    public static final String APP_PREFERENCES_USERNAME   = "user_name";
    /** Key to save user's email in preferences */
    public static final String APP_PREFERENCES_USER_EMAIL = "user_email";

    /** onCreate method of MainActivity
     *
     * @param savedInstanceState a reference to a Bundle object that is passed into the onCreate method of every Android Activity
     */
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

    }

    /** Create menu
     *
     * @param menu xml file, which contains list of menu items
     * @return true if menu display
     * @return false if menu don't display
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**Make any action if menu's item was clicked
     *
     * @param item item selected in menu
     * @return true if action complete
     * @return false if action don't complete
     */
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            startSettingActivity();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /** Start setting activity */
    private void startSettingActivity() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    /**This method is called whenever the user chooses to navigate Up within your application's activity hierarchy from the action bar.
     *
     * @return true if Up navigation completed successfully and this Activity was finished
     * @return false otherwise
     */
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    /**Called when a drawer's position changes.
     *
     * @param drawerView The child view that was moved
     * @param slideOffset The new offset of this drawer within its range, from 0-1
     */
    @Override
    public void onDrawerSlide(@NonNull View drawerView, float slideOffset) { }

    /**Called when a drawer has settled in a completely open state. The drawer is interactive at this point.
     *
     * @param drawerView Drawer view that is now open
     */
    @Override
    public void onDrawerOpened(@NonNull View drawerView) {
        textViewUsernameNavView.setText(mSettings.getString(APP_PREFERENCES_USERNAME,
                                                            getResources().getString(R.string.nav_header_title)));
        textViewUserEmailNavView.setText(mSettings.getString(APP_PREFERENCES_USER_EMAIL,
                                                            getResources().getString(R.string.nav_header_subtitle)));
    }

    /**Called when a drawer's position changes.
     *
     * @param drawerView Drawer view that is now closed
     */
    @Override
    public void onDrawerClosed(@NonNull View drawerView) {  }

    /**Called when the drawer motion state changes.
     *
     * @param newState The new drawer motion state
     */
    @Override
    public void onDrawerStateChanged(int newState) {  }
}