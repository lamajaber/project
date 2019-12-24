package com.example.notes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ConfirmMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm_message_activity);


    }

    public void goEmail(View view) {

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("*/*");
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }

    }
}
