package com.example.notes.SignIn_Up;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.notes.Adapter_Listener.SliderAdapterNote;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.example.notes.R;

public class NotebookTutorialActivity extends AppCompatActivity {


    private SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notebook_tutorial);


        preferences = getSharedPreferences("userInfo", MODE_PRIVATE);

        if (preferences.getBoolean("is_logged_in", false)) {
            Intent intent = new Intent(getApplicationContext(), SplashActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }

        sliderView();


        findViewById(R.id.btn_nextSplash).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = preferences.edit();

                editor.apply();
                Intent intent = new Intent(getApplicationContext(), SplashActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });


    }

    private void sliderView(){
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

                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("is_logged_in", true);
                editor.apply();

                Intent intent = new Intent(getApplicationContext(),SplashActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                NotebookTutorialActivity.this.finish();

            }
        }, 13000);

    }

}
