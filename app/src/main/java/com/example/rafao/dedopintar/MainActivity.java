package com.example.rafao.dedopintar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
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
    private float x1, y1, x2, y2, a1, b1, a2, b2;
    private float m1, m2;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar barra = findViewById(R.id.barra);
        barra.setTitle("Pintar");
        setSupportActionBar(barra);

        final ImageView imagen = findViewById(R.id.imagen1);
        tex = (TextView) findViewById(R.id.text1);

        imagen.setOnTouchListener((view, motionEvent) -> {

            switch(motionEvent.getActionMasked ()) {
                case MotionEvent.ACTION_DOWN:
                    if(color == -1) {
                        x1 = motionEvent.getX();
                        y1 = motionEvent.getY();
                        return true;
                    }

                case MotionEvent.ACTION_UP:
                    if(color == -1) {
                        a2 = motionEvent.getX(0);
                        b2 = motionEvent.getY(0);
                        m2 = (b2 - b1) / (a2 - a1);
                        Log.i(TAG,""+m2);
                        double degrees = (m2-m1)/(1+(m1*m2));
                        degrees = (float) Math.atan(degrees);
                        float Rotacion = (float) Math.toDegrees(degrees);
                        imagen.setRotation(Rotacion);
                        Log.i(TAG,"grades: "+degrees);
                        Log.i(TAG,"Rotacion; "+Rotacion);
                    }
                    return true;

                case MotionEvent.ACTION_MOVE:
                    if(color != -1) {
                        Bitmap bmap = ((BitmapDrawable) imagen.getDrawable()).getBitmap().copy(Bitmap.Config.ARGB_8888, true);
                        Canvas cnv = new Canvas(bmap);
                        Paint colorea = new Paint();
                        colorea.setColor(color);
                        tex.setBackgroundColor(color);
                        Log.i(TAG, "X: " + imagen.getX() + " Y:" + (motionEvent.getY() - imagen.getY()));
                        imagen.setImageBitmap(bmap);
                        float diferencia = (view.getHeight() - bmap.getHeight()) / 2;
                        if (motionEvent.getY() >= diferencia && motionEvent.getX() <= (view.getHeight() - diferencia)) {
                            cnv.drawCircle((motionEvent.getX() * 200) / bmap.getWidth(), (motionEvent.getY() - imagen.getY() - 160), 5, colorea);
                        }
                    }
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    if(color == -1) {
                        a1 = motionEvent.getX(motionEvent.getActionIndex());
                        b1 = motionEvent.getY(motionEvent.getActionIndex());
                        Log.i(TAG,"PRIMER LINEA");
                    }
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    if(color == -1) {
                        x2 = motionEvent.getX(motionEvent.getActionIndex());
                        y2 = motionEvent.getY(motionEvent.getActionIndex());
                        m1 = (y2 - y1) / (x2 - x1);
                        Log.i(TAG,""+m1);
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
                tex.setText("");
                Intent iniciar = new Intent(this, Main2Activity.class);
                startActivity(iniciar);
                break;
            case R.id.btnC:
                tex.setText("Pintar Cancelado");
                color = -1;
                break;
            }
            return super.onOptionsItemSelected(item);
        }



    }
