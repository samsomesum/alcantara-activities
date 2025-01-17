package com.example.droidcafe;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.droidcafe.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    private String lastClickImage = "";

    //I add Mam, because i declare the variable of each menu
    MenuItem order;
    MenuItem status;
    MenuItem favorites;
    MenuItem contact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, OrderActivity.class);
                intent.putExtra("CHOICE", lastClickImage);
                startActivity(intent);


                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAnchorView(R.id.fab)
                        .setAction("Action", null).show();
                 */
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();  // Get the ID of the clicked item

        // Check which menu item was selected
        if (itemId == R.id.action_order) {
            displayToast(getString(R.string.action_order_message));
            Intent intent = new Intent(MainActivity.this, OrderActivity.class);
            intent.putExtra("CHOICE", lastClickImage);
            startActivity(intent);
            return true;
        } else if (itemId == R.id.action_status) {
            displayToast(getString(R.string.action_status_message));
            return true;
        } else if (itemId == R.id.action_favorites) {
            displayToast(getString(R.string.action_favorites_message));
            return true;
        } else if (itemId == R.id.action_contact) {
            displayToast(getString(R.string.action_contact_message));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
    public void showDonutOrder(View view){
        displayToast(getString(R.string.donut_order_message));
        lastClickImage = getString(R.string.donut_order_message); // Fix here
    }
    public void showIceCreamOrder(View view){
        displayToast(getString(R.string.ice_cream_order_message));
        lastClickImage = getString(R.string.ice_cream_order_message); // Fix here
    }
    public void showFroyoOrder(View view){
        displayToast(getString(R.string.froyo_order_message));
        lastClickImage = getString(R.string.froyo_order_message); // Fix here
    }


    public void displayToast(String message){
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }

}