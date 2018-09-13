package com.example.rafao.dedopintar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MyTouchEventHandler";
    static int color = -1;
    TextView tex;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar barra = findViewById(R.id.barra);
        barra.setTitle("Pintar");
        setSupportActionBar(barra);
        final ImageView imagen = findViewById(R.id.imagen1);

        imagen.setOnTouchListener((view, motionEvent) -> {

            switch(motionEvent.getAction ()) {
                case MotionEvent.ACTION_DOWN:
                    return true;

                case MotionEvent.ACTION_UP:
                    return true;

                case MotionEvent.ACTION_MOVE:
                    Bitmap bmap = ((BitmapDrawable)imagen.getDrawable()).getBitmap().copy(Bitmap.Config.ARGB_8888,true);
                    Canvas cnv = new Canvas(bmap);
                    Paint colorea = new Paint();
                    colorea.setColor(color);
                    Log.i(TAG,"X: "+imagen.getX()+" Y:"+(motionEvent.getY()-imagen.getY()));
                    imagen.setImageBitmap(bmap);
                    float diferencia = (view.getHeight()-bmap.getHeight())/2;
                    if(motionEvent.getY() >= diferencia && motionEvent.getX() <= (view.getHeight()-diferencia)){
                        cnv.drawCircle(motionEvent.getX(), (motionEvent.getY()-imagen.getY()),5,colorea);
                    }
                    break;
            }

            return super.onTouchEvent(motionEvent);
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btn:
                Intent iniciar = new Intent(this, Main2Activity.class);
                startActivity(iniciar);
                break;
            }
            return super.onOptionsItemSelected(item);
        }



    }
