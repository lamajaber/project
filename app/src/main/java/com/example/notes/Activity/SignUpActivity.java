package com.example.notes.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.notes.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class SignUpActivity extends AppCompatActivity {

    private String email, pass;

    private TextInputLayout Eemail;
    private TextInputLayout Epass;

    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_activity);

        mAuth = FirebaseAuth.getInstance();


        Eemail = findViewById(R.id.editU_email);
        Epass = findViewById(R.id.editU_pass);



        findViewById(R.id.btn_signUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = Eemail.getEditText().getText().toString().trim();
                pass = Epass.getEditText().getText().toString().trim();

                if (checkEmailPassword(email, pass)) {



                    Eemail.setPasswordVisibilityToggleDrawable(R.drawable.ic_done2);
                    Epass.setPasswordVisibilityToggleDrawable(R.drawable.ic_done2);



                    progressDialog = new ProgressDialog(SignUpActivity.this,
                            R.style.AppTheme_Dark_Dialog);
                    progressDialog.setIndeterminate(true);
                    progressDialog.setMessage("Creating Account...");
                    progressDialog.show();



                    createUser(email, pass);

                }

            }
        });
    }


    public void closeSignUp(View view) {
        Intent intent = new Intent(getApplicationContext(), SplashActivity.class);
        startActivity(intent);
    }

    public void SignInActivity(View view) {
        Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
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

    private void createUser(String email, String paa) {
        mAuth.createUserWithEmailAndPassword(email, paa)
                .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(SignUpActivity.this, "Done", Toast.LENGTH_SHORT).show();

                            new android.os.Handler().postDelayed(
                                    new Runnable() {
                                        public void run() {
                                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                            startActivity(intent);
                                            progressDialog.dismiss();
                                        }
                                    }, 1000);
                        }
                        task.addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                Log.d("ffffffff",e.toString());
                                progressDialog.dismiss();

                            }
                        });
                    }
                });
    }
}
