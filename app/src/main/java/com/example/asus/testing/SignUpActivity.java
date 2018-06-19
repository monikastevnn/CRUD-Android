package com.example.asus.testing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asus.testing.Utils.sharedPrefManager;

public class SignUpActivity extends AppCompatActivity {
    private EditText mPasswordView, mEmailView;
    Button mEmailSignUpButton;
    sharedPrefManager mSharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mSharedPrefManager=new sharedPrefManager(this);

        // Set up the login form.
        mEmailView = findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        mEmailSignUpButton = (Button) findViewById(R.id.email_sign_up_button);


        mEmailSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=mEmailView.getText().toString();
                String password=mPasswordView.getText().toString();
                mSharedPrefManager.setUsername(sharedPrefManager.Username,username);
                mSharedPrefManager.setPassword(sharedPrefManager.Password,password);
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                Toast.makeText(getApplicationContext(), username+password, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
