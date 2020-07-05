package com.gregorius.finalexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

//M-OOP FINAL EXAM
//Gregorius Albert / 2301854486
//LA03

//This Application is tested on Pixel 3 API 28 & Nexus 5x API 29 Emulator and Asus Zenfone 5Z Physical Device

public class MainActivity extends AppCompatActivity {

    private String type, color, size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner typeSpinner = (Spinner) findViewById(R.id.typeDropdown);
        Spinner colorSpinner= (Spinner) findViewById(R.id.colorDropdown);
        Spinner sizeSpinner = (Spinner) findViewById(R.id.sizeDropdown);

        //Spinner for Product Type
        final ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.typeArray)) {

            // Disable default item
            @Override
            public boolean isEnabled(int position){
                return position != 0;
            }

            // Gray out default item
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                if(position == 0) {
                    TextView tv = (TextView) view;
                    tv.setTextColor(Color.GRAY);
                }
                return view;
            }
        };
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(typeAdapter);

        //Spinner for Product Color
        final ArrayAdapter<String> colorAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.colorArray)){

            // Disable default item
            @Override
            public boolean isEnabled(int position) {
                return position != 0;
            }

            // Gray out default item
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                if(position == 0) {
                    TextView tv = (TextView) view;
                    tv.setTextColor(Color.GRAY);
                }
                return view;
            }

        };
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colorSpinner.setAdapter(colorAdapter);

        //Spinner for Product Size
        final ArrayAdapter<String> sizeAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.sizeArray)){

            // Disable default item
            @Override
            public boolean isEnabled(int position){
                return position != 0;
            }

            // Gray out default item
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                if(position == 0) {
                    TextView tv = (TextView) view;
                    tv.setTextColor(Color.GRAY);
                }
                return view;
            }
        };
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeSpinner.setAdapter(sizeAdapter);

        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Gray out spinner text on default item
                if(position==0){
                    TextView tv = (TextView) view;
                    tv.setTextColor(Color.GRAY);
                    type = null;
                }
                else{
                    type = parent.getItemAtPosition(position).toString();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        colorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Gray out spinner text on default item
                if(position==0){
                    TextView tv = (TextView) view;
                    tv.setTextColor(Color.GRAY);
                    color = null;
                }
                else{
                    color = parent.getItemAtPosition(position).toString();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Gray out spinner text on default item
                if(position==0){
                    TextView tv = (TextView) view;
                    tv.setTextColor(Color.GRAY);
                    size = null;
                }
                else{
                    size = parent.getItemAtPosition(position).toString();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    // Method to preview products on "VIEW" button click
    public void viewChosen(View view) {
        //Validate if user has selected all item
        if(type==null || color==null || size==null){
//            To clear SharedPreferences data, uncomment below, select none and click view
//            PreferenceManager.getDefaultSharedPreferences(getBaseContext()).edit().clear().apply();
            Toast.makeText(getApplicationContext(), "Choose all options before proceeding!", Toast.LENGTH_LONG).show();
        }
        else{
            Intent intent = new Intent(this, ViewActivity.class);
            intent.putExtra("type", type);
            intent.putExtra("color", color);
            intent.putExtra("size", size);
            startActivity(intent);
        }
    }

    // Method to load last saved customization on "LOAD CUSTOMIZATION" button click
    public void loadCustomization(View view) {
        String savedType, savedColor, savedSize;

        //Get data from SharedPreferences
        SharedPreferences sPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        savedType = sPrefs.getString("KEY_TYPE", null);
        savedColor = sPrefs.getString("KEY_COLOR", null);
        savedSize = sPrefs.getString("KEY_SIZE", null);

        //Validate if the retrieved data is on default value
        if(savedType == null || savedColor == null || savedSize == null){
            Toast.makeText(getApplicationContext(), "No saved customizations yet!", Toast.LENGTH_LONG).show();
        }
        else{
            Intent intent = new Intent(this, ViewActivity.class);
            intent.putExtra("type", savedType);
            intent.putExtra("color", savedColor);
            intent.putExtra("size", savedSize);
            startActivity(intent);
        }
    }
}