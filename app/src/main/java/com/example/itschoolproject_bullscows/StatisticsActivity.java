package com.example.itschoolproject_bullscows;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class StatisticsActivity extends AppCompatActivity implements View.OnClickListener {
    int ng, nw, com;
    double cof;
    SharedPreferences SP;
    /*
    ng-количество игр
    nw-количество побед
    com-комбо побед ( подряд )
    cof-коэффициент побед ( победы делим на все игры )
     */
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
        /*
        Получаем из SP ng, nw, com ( по дефолту нуль, на случай отсутствия первой игры )
         */
        ng = SP.getInt("ng", 0 );
        nw = SP.getInt("nw", 0 );
        com = SP.getInt("com" , 0);
        if( SP.getBoolean("isPlayed", false)) //Если игра произошла, то количество игр +1
            ng++;
        if( SP.getBoolean("result", false) &&  SP.getBoolean("isPlayed", false) ) {
            //Если победа и игра была, то победы +1 и комбо +1
            com++;
            nw++;
        }else //Иначе обнуляем комбо
            com = 0;
        if( ng != 0 ) //Чтобы не вызывать ошибку деления на нуль ( какой try / catch ?? )
            cof =  (double) nw / (double) ng ;
        else
            cof = 1;
        cof = Math.round(cof * 100.0 ) / 100.0; //Моя гордость: округление до 2 знаков после запятой с помощью Math.round
        editor.putInt("ng", ng);//закидываем в SP
        editor.putInt("nw", nw);
        editor.putInt("com", com);
        editor.putBoolean("result", false);
        editor.putBoolean("isPlayed", false);
        NG.setText("Количество игр: " + ng );
        NW.setText("Количество побед: " + nw );
        Cof.setText("Коэффициент побед: \f" + cof);
        Com.setText("Побед подряд: " + com );
        editor.apply(); //Я наверное в округе 2 дней не мог понять почему не сохранялись значения, а я просто забыл это прописать
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
