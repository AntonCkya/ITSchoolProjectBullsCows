package com.example.itschoolproject_bullscows;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.ColorFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class StatisticsActivity extends AppCompatActivity implements View.OnClickListener {
    int ng, nw, com;
    double cof;
    SharedPreferences SP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        TextView NG = findViewById(R.id.textViewNumberGame);
        TextView NW = findViewById(R.id.textViewNumberWins);
        TextView Cof = findViewById(R.id.textViewСoefficient);
        TextView Com = findViewById(R.id.textViewCCCombo);
        SP = getSharedPreferences( "statistics", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = SP.edit();
        ng = SP.getInt("ng", 0 );
        nw = SP.getInt("nw", 0 );
        com = SP.getInt("com" , 0);
        if( SP.getBoolean("isPlayed", false))
            ng++;
        if( SP.getBoolean("result", false) &&  SP.getBoolean("isPlayed", false) ) {
            com++;
            nw++;
        }else
            com = 0;
        if( ng != 0 )
            cof =  (double) nw / (double) ng ;
        else
            cof = 1;
        cof = Math.round(cof * 100.0 ) / 100.0;
        editor.putInt("ng", ng);
        editor.putInt("nw", nw);
        editor.putInt("com", com);
        editor.putBoolean("result", false);
        editor.putBoolean("isPlayed", false);
        NG.setText("Количество игр: " + ng );
        NW.setText("Количество побед: " + nw );
        Cof.setText("Коэффициент побед: \f" + cof);
        Com.setText("Побед подряд: " + com );
        editor.apply();
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
    @Override
    public void onBackPressed() {
        Intent i = new Intent( this, MainActivity.class );
        startActivity( i );
    }
}
