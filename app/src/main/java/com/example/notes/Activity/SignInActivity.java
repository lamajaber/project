package com.example.notes.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.notes.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class SignInActivity extends AppCompatActivity {


    private String emaill, pass;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;

    private TextInputLayout Eemail;
    private TextInputLayout Epass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_activity);

        mAuth = FirebaseAuth.getInstance();
        Eemail = findViewById(R.id.editI_email);
        Epass = findViewById(R.id.editI_pass);

        Drawable img = getResources().getDrawable(R.drawable.ic_done1);
        Eemail.setPasswordVisibilityToggleDrawable(img);




        findViewById(R.id.btn_signIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emaill = Eemail.getEditText().getText().toString().trim();
                pass = Epass.getEditText().getText().toString().trim();

                if (checkEmailPassword(emaill, pass)) {

                    progressDialog = new ProgressDialog(SignInActivity.this, R.style.AppTheme_Dark_Dialog);
                    progressDialog.setIndeterminate(true);
                    progressDialog.setMessage("Authenticating...");
                    progressDialog.show();

                    signIn(emaill,pass);

                }
            }
        });
    }



    public void forgotPass(View view) {
        Intent intent = new Intent(getApplicationContext(), ForgotPassActivity.class);
        startActivity(intent);
    }

    public void SignUpActivity(View view) {
        Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
        startActivity(intent);
    }

    public void closeSignIn(View view) {
        Intent intent = new Intent(getApplicationContext(), SplashActivity.class);
        startActivity(intent);
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

    private void signIn(String email, String paa) {
        mAuth.signInWithEmailAndPassword(email,paa)
                .addOnCompleteListener(SignInActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(SignInActivity.this, "Done", Toast.LENGTH_SHORT).show();

                            new android.os.Handler().postDelayed(
                                    new Runnable() {
                                        public void run() {
                                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                            startActivity(intent);
                                            progressDialog.dismiss();
                                        }
                                    }, 1000);

                        }
                        if (!task.isSuccessful()) {
                            Toast.makeText(SignInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

}
