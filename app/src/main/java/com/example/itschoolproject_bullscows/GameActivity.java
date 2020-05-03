package com.example.itschoolproject_bullscows;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MotionEventCompat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ConcurrentHashMap;

import static android.widget.Toast.*;

public class GameActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView AttText, BullsText, CowsText, History; //Текстовые поля( попытки, быки, коровы )
    private EditText EditText; //Вставить текст
    private int att = 0; //Попытки
    private String Rn, Pn, HistoryText; //Правильный ответ и пользовательский ответ
    private boolean IsRight;
    private int[] BnC;
    public SharedPreferences SP;
    //комментарии не соответствуют действительности, так как недавно переделал этот класс
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            att = savedInstanceState.getInt("Att" );
            BnC = savedInstanceState.getIntArray("BullsCows" );
            HistoryText = savedInstanceState.getString("History" );
        }else{
            att = 0;
            BnC = new int[2];
            BnC[0] = 0 ; BnC[1] = 0;
            HistoryText = "";
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Bundle arguments = getIntent().getExtras();
        SP = getSharedPreferences("statistics", Context.MODE_PRIVATE);
        Rn = arguments.getString("RightNum"); //Получаем из интента число
        AttText = findViewById(R.id.AttemptionsText);
        BullsText = findViewById(R.id.BullsText);
        CowsText = findViewById(R.id.CowsText);
        History = findViewById(R.id.History);
        EditText = findViewById(R.id.PlayerNumEditText);
        BullsText.setText("Bulls: " + BnC[0]);
        CowsText.setText("Cows: " + BnC[1]);
        AttText.setText("Attemptions: " + att);
        History.setText(HistoryText);
    }
    @Override
    public void onClick(View v) {
        SharedPreferences.Editor editor = SP.edit();
        IsRight = true;
        Pn = EditText.getText().toString(); //Получаем пользовательское число
        BullsCowsDetect BullsCowsDetect = new BullsCowsDetect(Pn, Rn); //Создаём экземпляр класса BullsCowsDetect
        att++; //Плюс попытка
        BnC = BullsCowsDetect.GetBullsCows(); //Получаем быков и коров из двух строк
        if (BnC[0] == 228 && BnC[1] == 228) { //Проверка на выход за границы строки
            BnC[0] = 0; //Обнуляем быков и коров
            BnC[1] = 0;
            att--; //За попытку не считаем
            IsRight = false;
            Toast.makeText(getApplicationContext(), "Lenght: " + Rn.length() + " !", LENGTH_SHORT).show(); //Выводим длину искомого числа
        }
        BullsText.setText("Bulls: " + BnC[0]);
        CowsText.setText("Cows: " + BnC[1]);
        AttText.setText("Attemptions: " + att);
        if( IsRight ) {
            HistoryText = History.getText().toString() + "\n" + att + ": " + Pn + " Bulls: " + BnC[0] + " Cows: " + BnC[1];
            History.setText(HistoryText);
        }
        Intent i;
        i = new Intent(this, TuningActivity.class);
        if (BnC[0] == Rn.length() ) {
            /*
            Если число быков равно длине числа, то происходит победа
            Вывод тоста
             */
            editor.putBoolean("result", true);
            editor.putInt("highscore", att);
            Toast.makeText(getApplicationContext(), "You Win!\n" + Rn, LENGTH_SHORT).show();
            startActivity(i);
        } else if (att == 11) {
            /*
            Если попыток больше 10, то происходит поражение
            Вывод тоста
             */
            editor.putBoolean("result", false);
            editor.putInt("highscore", att);
            Toast.makeText(getApplicationContext(), "Game Over!\nСorrect Аnswer:" + Rn, LENGTH_SHORT).show();
            startActivity(i);
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putIntArray( "BullsCows" , BnC );
        outState.putInt( "Att" , att );
        outState.putString( "History" , HistoryText );
        super.onSaveInstanceState(outState);
    }
    @Override
    public void onBackPressed() //Мало ли
    {
        Intent i;
        i = new Intent(this, TuningActivity.class);
        startActivity(i);
    }
}
