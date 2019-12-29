package com.example.notes.SignIn_Up;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.notes.R;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spla);
        getSupportActionBar().hide();

    }


    public void signIn(View view) {
        //this.finish();
        startActivity(new Intent(this, SignInActivity.class));
    }

    public void singUp(View view) {
        //this.finish();
        startActivity(new Intent(this, SignUpActivity.class));
    }
}
