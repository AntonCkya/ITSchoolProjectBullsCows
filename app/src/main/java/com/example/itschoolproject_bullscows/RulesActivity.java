package com.example.itschoolproject_bullscows;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RulesActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);
        //Я не знаю что тут нужно объяснять, тупо кнопка назад в MainActivity, и при нажатии назад то же самое
        //Думаю тут мог быть код вообще излишен, но конпку лучше добавить
    }
    @Override
    public void onClick(View v) {
        Intent i;
        i = new Intent( this, MainActivity.class );
        startActivity( i );
    }
    public void onBackPressed() {
        Intent i;
        i = new Intent( this, MainActivity.class );
        startActivity( i );
    }
}
