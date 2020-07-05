package com.gregorius.finalexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.prefs.PreferenceChangeEvent;

//M-OOP FINAL EXAM
//Gregorius Albert / 2301854486
//LA03

public class ViewActivity extends AppCompatActivity {

    private String type, color, size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        TextView tvType = findViewById(R.id.typeView);
        TextView tvColor = findViewById(R.id.colorView);
        TextView tvSize = findViewById(R.id.sizeView);
        ImageView imvProduct = findViewById(R.id.productImage);

        // Get parsed data from main activity
        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        color = intent.getStringExtra("color");
        size = intent.getStringExtra("size");


        if(type.equals("Shirt") && color.equals("Red")) imvProduct.setImageResource(R.drawable.red_shirt);
        else if(type.equals("Shirt") && color.equals("Green")) imvProduct.setImageResource(R.drawable.green_shirt);
        else if(type.equals("Shirt") && color.equals("Blue")) imvProduct.setImageResource(R.drawable.blue_shirt);

        if(type.equals("Pants") && color.equals("Red")) imvProduct.setImageResource(R.drawable.red_pants);
        else if(type.equals("Pants") && color.equals("Green")) imvProduct.setImageResource(R.drawable.green_pants);
        else if(type.equals("Pants") && color.equals("Blue")) imvProduct.setImageResource(R.drawable.blue_pants);

        if(type.equals("Hat") && color.equals("Red")) imvProduct.setImageResource(R.drawable.red_hat);
        else if(type.equals("Hat") && color.equals("Green")) imvProduct.setImageResource(R.drawable.green_hat);
        else if(type.equals("Hat") && color.equals("Blue")) imvProduct.setImageResource(R.drawable.blue_hat);

        String sType = "Type: " + type;
        String sColor = "Color: " + color;
        String sSize = "Size: " + size;

        tvType.setText(sType);
        tvColor.setText(sColor);
        tvSize.setText(sSize);
    }

    // Method to save current selected options to Shared Preferences
    public void saveCustomization(View view) {

        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(this).edit();
        editor.putString("KEY_TYPE", type);
        editor.putString("KEY_COLOR", color);
        editor.putString("KEY_SIZE", size);
        editor.apply();

        Toast.makeText(getApplicationContext(), "Customization saved!", Toast.LENGTH_LONG).show();
    }
}