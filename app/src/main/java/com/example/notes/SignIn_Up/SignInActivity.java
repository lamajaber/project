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


        findViewById(R.id.btn_signIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = Eemail.getEditText().getText().toString().trim();
                password = Epass.getEditText().getText().toString().trim();


                if (checkEmailPassword(email, password)) {
                    progressDialog = new ProgressDialog(SignInActivity.this, R.style.AppTheme_Dark_Dialog);
                    progressDialog.setIndeterminate(true);
                    progressDialog.setMessage("Authenticating...");
                    progressDialog.show();

                    signIn(email, password);
                }
            }
        });

    }





    private void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull final Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            FirebaseUser user = mAuth.getCurrentUser();


                            Map<String, Object> data = new HashMap<>();
                            data.put("lastSignIn", new Date().getTime());


                            FirebaseDatabase.getInstance().getReference().child("User").child(user.getUid()).updateChildren(data)
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(SignInActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                            Log.d("error", e.getLocalizedMessage());
                                        }
                                    })
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Intent intent = new Intent(SignInActivity.this, HomePageActivity.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intent);
                                        }
                                    });

                        } else {
                            Toast.makeText(SignInActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
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
