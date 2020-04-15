package com.example.itschoolproject_bullscows;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch ( v.getId() ){
            case R.id.button:
                i = new Intent(this, TuningActivity.class);
                startActivity( i );
                break;
            case R.id.button5:
                i = new Intent(this, RulesActivity.class);
                startActivity( i );
                break;
        }
    }

    @Override
    public void onBackPressed()
    {
        //Выход из приложения
        Intent i = new Intent(Intent.ACTION_MAIN);
        i.addCategory(Intent.CATEGORY_HOME);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
}
