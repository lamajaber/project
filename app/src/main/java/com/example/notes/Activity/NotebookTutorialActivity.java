package com.example.notes.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.notes.R;
import com.example.notes.Adapter.SliderAdapterNote;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

public class NotebookTutorialActivity extends AppCompatActivity {


    SharedPreferences.Editor editor;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notebook_tutorial);
        prefs = getSharedPreferences("sss", MODE_PRIVATE);

        if (prefs.getBoolean("name", false)) {
            startActivity(new Intent(getApplicationContext(), SplashActivity.class));
            NotebookTutorialActivity.this.finish();
        }


        SliderView sliderView = findViewById(R.id.imageSlider);
        SliderAdapterNote adapter = new SliderAdapterNote(getApplicationContext());
        sliderView.setSliderAdapter(adapter);
        sliderView.setIndicatorAnimation(IndicatorAnimations.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.argb(200, 244, 135, 33));
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(8);
        sliderView.startAutoCycle();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                editor = getSharedPreferences("sss", MODE_PRIVATE).edit();
                editor.putBoolean("key", true);
                editor.apply();

                startActivity(new Intent(getApplicationContext(), SplashActivity.class));
                NotebookTutorialActivity.this.finish();


            }
        }, 13000);


        findViewById(R.id.btn_nextSplash).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SplashActivity.class);
                startActivity(intent);
            }
        });


    }

}
