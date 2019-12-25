package com.example.notes.Activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.notes.R;


public class SignUpActivity extends AppCompatActivity {

    private EditText Eemail, Epass;
    private String email, pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_activity);


        Eemail = findViewById(R.id.editU_email);
        Epass = findViewById(R.id.editU_pass);


        Drawable img = getResources().getDrawable(R.drawable.ic_done1);
        Eemail.setCompoundDrawablesWithIntrinsicBounds(null, null, img, null);
        Epass.setCompoundDrawablesWithIntrinsicBounds(null, null, img, null);


        findViewById(R.id.btn_signUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkEmailPassword(Eemail, Epass)) {


                    Drawable img = getResources().getDrawable(R.drawable.ic_done2);
                    Eemail.setCompoundDrawablesWithIntrinsicBounds(null, null, img, null);
                    Epass.setCompoundDrawablesWithIntrinsicBounds(null, null, img, null);


                    Toast.makeText(SignUpActivity.this, "Done", Toast.LENGTH_SHORT).show();


                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);

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

    private Boolean checkEmailPassword(EditText txtemail, EditText txtpass) {
        email = txtemail.getText().toString().trim();
        pass = txtpass.getText().toString().trim();

        if (!(email.contains("gmail.com")) || !(email.contains("gmail.com")) || !(email.contains("gmail.com"))) {
            txtemail.setError("Error email not voiled");
            return false;
        } else if (!(pass.length() > 6)) {
            txtpass.setError("Error should more 6");
            return false;
        } else {
            return true;
        }
    }
}
