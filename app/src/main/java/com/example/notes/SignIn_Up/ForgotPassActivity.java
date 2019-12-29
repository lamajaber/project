package com.example.notes.SignIn_Up;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.example.notes.R;

public class ForgotPassActivity extends AppCompatActivity {

    EditText txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        txtEmail = findViewById(R.id.EditF_email);
getSupportActionBar().setDisplayHomeAsUpEnabled(true);
getSupportActionBar().setDisplayShowHomeEnabled(true);
getSupportActionBar().setTitle("Reset Password");


    }

    public void recoverPass(View view) {
        String email = txtEmail.getText().toString().trim();

        resetUserPassword(email);
    }


    public void goEmail(View view) {
        Intent intent = new Intent(getApplicationContext(), ConfirmMessageActivity.class);
        startActivity(intent);
    }


    public void resetUserPassword(String email) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        final ProgressDialog progressDialog = new ProgressDialog(ForgotPassActivity.this);
        progressDialog.setMessage("verifying..");
        progressDialog.show();

        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            Log.d("ddddd",task.toString());

                            Toast.makeText(getApplicationContext(), "Reset password instructions has sent to your email",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            progressDialog.dismiss();
                            Log.d("ddddd","error");

                            Toast.makeText(getApplicationContext(),
                                    "Email don't exist", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                Log.d("ddddd",e.toString());
            }
        });
    }
}
