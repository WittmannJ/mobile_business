package com.stell.wowa.bytepluto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener{

    EditText mEditTextEmail;
    EditText mEditTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mEditTextEmail = (EditText) findViewById(R.id.signInEmail);
        mEditTextPassword = (EditText) findViewById(R.id.signInPassword);

        ((Button) findViewById(R.id.signInButtonSignIn)).setOnClickListener(this);
        ((Button) findViewById(R.id.signInButtonResetPassword)).setOnClickListener(this);
        ((Button) findViewById(R.id.signInButtonCreateAccount)).setOnClickListener(this);


        
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        switch (i) {
            case R.id.signInButtonSignIn:
                doSignIn();
                return;

            case R.id.signInButtonResetPassword:
                doResetPassword();
                return;

            case R.id.signInButtonCreateAccount:
                doCreateAccount();
                return;
            default:
                return;
        }
    }

    private void doCreateAccount() {
        Intent intent = new Intent(getApplication(),
                CreateAccountActivity.class);
        startActivity(intent);
    }

    private void doResetPassword() {
    }

    private void doSignIn() {
    }
}
