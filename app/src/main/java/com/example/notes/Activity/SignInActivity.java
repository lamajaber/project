package com.example.notes.Activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.notes.R;


public class SignInActivity extends AppCompatActivity {


    private EditText Eemail, Epass;
    String emaill, pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_activity);


        Eemail = findViewById(R.id.editI_email);
        Epass = findViewById(R.id.editI_pass);


        Drawable img = getResources().getDrawable(R.drawable.ic_done1);
        Eemail.setCompoundDrawablesWithIntrinsicBounds(null, null, img, null);
        Epass.setCompoundDrawablesWithIntrinsicBounds(null, null, img, null);


        findViewById(R.id.btn_signIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkEmailPassword(Eemail, Epass)) {

                    Drawable img = getResources().getDrawable(R.drawable.ic_done2);
                    Eemail.setCompoundDrawablesWithIntrinsicBounds(null, null, img, null);
                    Epass.setCompoundDrawablesWithIntrinsicBounds(null, null, img, null);

                    Toast.makeText(SignInActivity.this, "Done", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);

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

    private Boolean checkEmailPassword(EditText txtemail, EditText txtpass) {
        emaill = txtemail.getText().toString().trim();
        pass = txtpass.getText().toString().trim();

        if (!(emaill.contains("gmail.com")) || !(emaill.contains("gmail.com")) || !(emaill.contains("gmail.com"))) {
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
