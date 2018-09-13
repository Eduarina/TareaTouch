package com.example.rafao.dedopintar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener{

    SeekBar barR;
    SeekBar barG;
    SeekBar barB;
    int red,green,blue;
    LinearLayout texto;
    Button btn01;
    public static final String TAG = "MyTouchEventHandler";

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        barR = (SeekBar)findViewById(R.id.seek1);
        barG = (SeekBar)findViewById(R.id.seek2);
        barB = (SeekBar)findViewById(R.id.seek3);

        texto = (LinearLayout) findViewById(R.id.linear);

        barR.setOnSeekBarChangeListener(this);
        barG.setOnSeekBarChangeListener(this);
        barB.setOnSeekBarChangeListener(this);

        btn01 = (Button) findViewById(R.id.btn1);
        btn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn1:
                        MainActivity.color = Color.rgb(red,green,blue);
                        Log.i(TAG,""+MainActivity.color);
                        finish();
                        break;
                }
            }
        });

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        red = barR.getProgress();
        green = barG.getProgress();
        blue = barB.getProgress();

        int id = seekBar.getId();

        switch (id){
            case com.example.rafao.dedopintar.R.id.seek1:
                red = progress;
                break;
            case com.example.rafao.dedopintar.R.id.seek2:
                green = progress;
                break;
            case com.example.rafao.dedopintar.R.id.seek3:
                blue = progress;
                break;
        }

        texto.setBackgroundColor(Color.rgb(red,green,blue));
        MainActivity.color = Color.rgb(red,green,blue);

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
