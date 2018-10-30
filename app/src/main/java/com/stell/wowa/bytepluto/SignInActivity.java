package com.stell.wowa.bytepluto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "XX SignInActivity";

    //Declare UI variables
    EditText mEditTextEmail;
    EditText mEditTextPassword;

    Button mButtonSignIn;
    Button mButtonForgotPassword;
    Button mButtonCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        //Initialise UI variables
        mEditTextEmail = (EditText)findViewById(R.id.signInEditEmail);
        mEditTextPassword = (EditText)findViewById(R.id.signInEditPassword);

        mButtonSignIn = (Button)findViewById(R.id.signInBtn);
        mButtonForgotPassword = (Button)findViewById(R.id.signInForgotPswdBtn);
        mButtonCreateAccount = (Button)findViewById(R.id.signInCreateAccountBtn);

        ((Button)findViewById(R.id.signInBtn)).setOnClickListener(this);
        ((Button)findViewById(R.id.signInForgotPswdBtn)).setOnClickListener(this);
        ((Button)findViewById(R.id.signInCreateAccountBtn)).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        switch (i) {
            case R.id.signInBtn:
                doSignIn();
                return;

            case R.id.signInForgotPswdBtn:
                doResetPassword();
                return;

            case R.id.signInCreateAccountBtn:
                doCreateAccount();
                return;
            default:
                return;
        }
    }

    private void doCreateAccount() {
        //TODO: springe zu CreateAccount activity

        Log.d(TAG, "Create Account Button clicked!");
    }

    private void doResetPassword() {
        //TODO: sende password reset Email

        Log.d(TAG, "Reset Password Button clicked!");
    }

    private void doSignIn() {
        //TODO: Check if ok => (1) => MainActivity; else => (0) => SignInActivity mit FehlerToast
        Log.d(TAG, "Sign in Button clicked!");
    }
}
