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

public class SignInActivity extends AppCompatActivity {

    private TextInputLayout Eemail ,Epass;
    private String email, password;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        getSupportActionBar().hide();

        mAuth = FirebaseAuth.getInstance();

        Eemail = findViewById(R.id.editI_email);
        Epass = findViewById(R.id.editI_pass);


    }




    private Boolean checkEmailPassword(String email, String pss) {
        if (!(email.contains("gmail.com")) || !(email.contains("gmail.com")) || !(email.contains("gmail.com"))) {
            Eemail.setError("Error email not voiled");
            return false;

        } else if (!(pss.length() > 6)) {
            Epass.setError("Error should more 6");
            return false;

        } else {
            return true;
        }
    }


    public void forgotPass(View view) {
        Intent intent = new Intent(getApplicationContext(),ForgotPassActivity.class);
        startActivity(intent);
    }

    public void SignUpActivity(View view) {
        Intent intent = new Intent(getApplicationContext(),SignUpActivity.class);
        startActivity(intent);
    }

    public void closeSignIn(View view) {
        this.finish();

    }
}
