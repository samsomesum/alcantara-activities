package com.example.droidcafe;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.provider.ContactsContract;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.droidcafe.databinding.ActivityOrderBinding;

public class OrderActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {

    private AppBarConfiguration appBarConfiguration;
    private ActivityOrderBinding binding;

    //The last image click
    Intent intent;
    String choice;
    TextView textView;

    //-----------
    RadioButton sameday;
    RadioButton nextday;
    RadioButton pickup;

    //----------
    RadioGroup radioGroup;

    //challenge
    EditText phoneNumber;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_order);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAnchorView(R.id.fab)
                        .setAction("Action", null).show();
            }
        });
        //------------------ additional code
        intent = getIntent();
        choice = intent.getStringExtra("CHOICE");

        textView = findViewById(R.id.order_textview);
        textView.setText(choice);
        //-------------------------------------
        sameday = findViewById(R.id.sameday);
        nextday = findViewById(R.id.nextday);
        pickup = findViewById(R.id.pickup);

        radioGroup = findViewById(R.id.radioGroup);
        defaultValRadio();

        //--------------
        phoneNumber = findViewById(R.id.phone_text);

        //Create the spinner
        Spinner spinner= findViewById(R.id.label_spinner);
        if (spinner != null){
            spinner.setOnItemSelectedListener(this);
        }
        //Create ArrayAdapter using the string array and default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.labels_array, android.R.layout.simple_spinner_item);
        //Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        //Apply the adapter to the spinner
        if (spinner != null){
            spinner.setAdapter(adapter);
        }
        //End of onCreate code
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_order);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    //default value of radioGroup(challenge po ito mam)
    public void defaultValRadio(){
        radioGroup.check(R.id.nextday);
    }

    public void onRadioButtonClicked(View view) {
         boolean checked = ((RadioButton) view).isChecked();
         if (view.getId() == sameday.getId()){
            if (checked){
                displayToast(getString(R.string.same_day_messenger_service));
            }
         } else if (view.getId() == nextday.getId()){
             if (checked){
                 displayToast(getString(R.string.next_day_ground_delivery));
             }
         } else if (view.getId() == pickup.getId()){
             if (checked){
                 displayToast(getString(R.string.pick_up));
             }
         }
    }
    public void displayToast(String message){
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String spinnerLabel = adapterView.getItemAtPosition(i).toString();
        displayToast(spinnerLabel);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    //Challenge method

}