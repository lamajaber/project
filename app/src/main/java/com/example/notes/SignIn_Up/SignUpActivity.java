package com.example.notes.SignIn_Up;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.notes.NoteBook.HomePageActivity;
import com.example.notes.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    private TextInputLayout Eemail ,Epass;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    private String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getSupportActionBar().hide();

        mAuth = FirebaseAuth.getInstance();

        Eemail = findViewById(R.id.editU_email);
        Epass = findViewById(R.id.editU_pass);




        findViewById(R.id.btn_signUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = Eemail.getEditText().getText().toString().trim();
                password = Epass.getEditText().getText().toString().trim();

                if (checkEmailPassword(email,password)) {


                    Eemail.setPasswordVisibilityToggleDrawable(R.drawable.ic_done2);
                    Epass.setPasswordVisibilityToggleDrawable(R.drawable.ic_done2);


                    progressDialog = new ProgressDialog(SignUpActivity.this,
                            R.style.AppTheme_Dark_Dialog);
                    progressDialog.setIndeterminate(true);
                    progressDialog.setMessage("Creating Account...");
                    progressDialog.show();

                    doSignUp(email, password);


                }





            }
        });


    }


    private void doSignUp(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull final Task<AuthResult> task) {
                        if (task.isSuccessful()) {


                            Intent intent = new Intent(SignUpActivity.this, HomePageActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);

                        } else {
                            Toast.makeText(SignUpActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                     }

                    }


                });
    }


    private Boolean checkEmailPassword(String email, String pss) {

        if (!(email.contains("gmail.com")) ) {
            if (!(email.contains("gmail.com"))) {
                if (!(email.contains("gmail.com"))) {
                    Eemail.setError("Error email not voiled");
                    return false;
                }
            }

        }else if (!(pss.length() > 6)) {
            Epass.setError("Error should more 6");
            return false;

        } else {
            return true;
        }
        return false;
    }


    public void closeSignUp(View view) {
        this.finish();
    }

    public void SignInActivity(View view) {
        Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
        startActivity(intent);
    }
}

