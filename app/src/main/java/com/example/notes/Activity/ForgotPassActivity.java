package com.example.notes.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.notes.Activity.ConfirmMessageActivity;
import com.example.notes.R;

public class ForgotPassActivity extends AppCompatActivity {

    EditText txtEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_pass_activity);

         txtEmail = findViewById(R.id.EditF_email);


    }



    public void goEmail(View view) {
        Intent intent = new Intent(getApplicationContext(), ConfirmMessageActivity.class);
        startActivity(intent);
    }
}
