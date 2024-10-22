package com.example.gesturesproyect;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.textclassifier.TextLanguage;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GestureDetectorCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private final String DEBUG_TAG = "Gestures";
    private GestureDetectorCompat MiGD;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        MiGD = new GestureDetectorCompat(this, new GestureDetector.SimpleOnGestureListener() {
            private void cambiarTexto(String texto){
                EditText cajaTexto = findViewById(R.id.editTextText);
                cajaTexto.setText(texto);
            }

            public boolean onDown(@NonNull MotionEvent event) {
                Log.d(DEBUG_TAG,"onDown: " + event.toString());
                this.cambiarTexto("Down");
                return true;
            }

            @Override
            public boolean onFling(MotionEvent event1, MotionEvent event2,
                                   float velocityX, float velocityY) {
                Log.d(DEBUG_TAG, "onFling: " + event1.toString() + event2.toString());
                this.cambiarTexto("Fling");
                return true;
            }

            @Override
            public void onLongPress(MotionEvent event) {
                Log.d(DEBUG_TAG, "onLongPress: " + event.toString());
                this.cambiarTexto("LongPress");
            }

            @Override
            public boolean onScroll(MotionEvent event1, MotionEvent event2, float distanceX,
                                    float distanceY) {
                Log.d(DEBUG_TAG, "onScroll: " + event1.toString() + event2.toString());
                this.cambiarTexto("Scroll");
                return true;
            }

            @Override
            public void onShowPress(MotionEvent event) {
                Log.d(DEBUG_TAG, "onShowPress: " + event.toString());
                this.cambiarTexto("ShowPress");
            }

            @Override
            public boolean onSingleTapUp(MotionEvent event) {
                Log.d(DEBUG_TAG, "onSingleTapUp: " + event.toString());
                this.cambiarTexto("SingleTapUp");
                return true;
            }

            @Override
            public boolean onDoubleTap(MotionEvent event) {
                Log.d(DEBUG_TAG, "onDoubleTap: " + event.toString());
                this.cambiarTexto("DoubleTap");
                return true;
            }

            @Override
            public boolean onDoubleTapEvent(MotionEvent event) {
                Log.d(DEBUG_TAG, "onDoubleTapEvent: " + event.toString());
                this.cambiarTexto("DoubleTapEvent");
                return true;
            }


            @Override
            public boolean onSingleTapConfirmed(MotionEvent event) {
                Log.d(DEBUG_TAG, "onSingleTapConfirmed: " + event.toString());
                this.cambiarTexto("SingleTapConfirmed");
                return true;
            }
        });
        ImageView rectangulo = findViewById(R.id.rectangulo);
        
        rectangulo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return MiGD.onTouchEvent(event);
            }
        });
    }
}