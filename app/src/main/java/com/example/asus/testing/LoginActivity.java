package com.example.asus.testing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asus.testing.Utils.sharedPrefManager;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    // UI references.
    private EditText mPasswordView, mEmailView;
    Button mEmailSignInButton, mEmailSignUpButton;

    sharedPrefManager mSharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mSharedPrefManager=new sharedPrefManager(this);

        // Set up the login form.
        mEmailView = findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignUpButton = (Button) findViewById(R.id.email_sign_up_button);

        //cek login status
        if (mSharedPrefManager.getCekLogin()){
            //kalo udah login langsung ke activity
            startActivity(new Intent(this,ListActivity.class));
        }

        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = mEmailView.getText().toString();
                String password = mPasswordView.getText().toString();
                //cek login validity
                //salah semua
                if (!username.equals(mSharedPrefManager.getUsername()) && !password.equals(mSharedPrefManager.getPassword())) {
                    Toast.makeText(getApplicationContext(), "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                }else if(!username.equals(mSharedPrefManager.getUsername()) || !password.equals(mSharedPrefManager.getPassword())){
                    //salah satu salah
                    Toast.makeText(getApplicationContext(), "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                }else{
                   startActivity(new Intent(getApplicationContext(),ListActivity.class));
               }
            }
        });

        mEmailSignUpButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SignUpActivity.class));
            }
        });
    }

}

