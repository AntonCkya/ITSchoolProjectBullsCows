package com.example.itschoolproject_bullscows;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.os.Bundle;

public class TuningActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private TextView TextView; //Выводит значение SeekBar
    private int TuneValue = 4 ; //значение ползунка
    private Switch FZ; //свич на первый_нуль

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuning);

        TextView = findViewById( R.id.Tune );
        //SeekBar; "ползунок"
        android.widget.SeekBar seekBar = findViewById(R.id.SeekBar);
        seekBar.setOnSeekBarChangeListener(this); //отслеживание изменений SeekBar
        TextView.setText("4 "); //дать начальное значение текстовому полю
        FZ = findViewById( R.id.FZSwitch ); //свич
    }
    //при изменении SeekBar:
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        TextView.setText((seekBar.getProgress() + 3) + " "); //вставить текст
        TuneValue = seekBar.getProgress() + 3; //изменение значения ползунка
        //в остальных аналогично
    }
    //при начале изменения SeekBar:
    public void onStartTrackingTouch(SeekBar seekBar) {
        TextView.setText((seekBar.getProgress() + 3) + " ");
        TuneValue = seekBar.getProgress() + 3;
    }
    //при окончании изменения SeekBar:
    public void onStopTrackingTouch(SeekBar seekBar) {
        TextView.setText((seekBar.getProgress() + 3) + " ");
        TuneValue = seekBar.getProgress() + 3;
    }

    @Override
    public void onClick(View v) {
        Intent i;
        Randomize Random = new Randomize( TuneValue, FZ.isChecked() ); //Создаём экземпляр класса Randomize
        String Rn = Random.Generate(); //Генерим число
        i = new Intent(this, GameActivity.class);
        i.putExtra("RightNum", Rn );
        startActivity( i );
        /*
        При нажатии переход в другую активность с переносом значения ползунка
         */
    }
    @Override
    public void onBackPressed() //Написано, чтобы нельзя было вернуться к проигранной попытке заново
    {
        Intent i;
        i = new Intent( this, MainActivity.class );
        startActivity( i );
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        //я не знаю зачем это здесь, но мне 2 недели назад было виднее
    }
}
