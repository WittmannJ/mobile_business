package com.stell.wowa.bytepluto;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ManageAccountActivity extends AppCompatActivity implements View.OnClickListener{

    TextView mEmail, mAccountState, mTechnicalId;
    EditText mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_account);

        mEmail = (TextView) findViewById(R.id.manageAccountEmail);
        mAccountState = (TextView) findViewById(R.id.manageAccountVerificationState);
        mTechnicalId = (TextView) findViewById(R.id.manageAccountTechnicalId);
        mPassword = (EditText) findViewById(R.id.manageAccountPassword);

        // Just to display some velues
        mEmail.setText("Mail : " + "user@demo.de");
        mAccountState.setText("Account verified: NO");
        mTechnicalId.setText("ID : " + "uis-1231231");

        ((Button) findViewById( R.id.manageAccountButtonSignOut)).setOnClickListener( this );
        ((Button) findViewById( R.id.manageAccountButtonSendActivationMail)).setOnClickListener( this );
        ((Button) findViewById( R.id.manageAccountButtonDeleteAccount)).setOnClickListener( this );
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        switch (i) {
            case R.id.manageAccountButtonDeleteAccount:
                doDeleteAccount();
                return;
            case R.id.manageAccountButtonSignOut:
                doSignOut();
                return;
            case R.id.manageAccountButtonSendActivationMail:
                doSendActivationMail();
                return;
            default:
                return;
        }
    }

    private void doSendActivationMail() {
    }

    private void doSignOut() {

    }

    private void doDeleteAccount() {
    }
}
