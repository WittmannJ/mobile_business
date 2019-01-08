package com.stell.wowa.bytepluto;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ManageAccountActivity extends AppCompatActivity implements View.OnClickListener {

    TextView mEmail, mAccountState, mTechnicalId;
    EditText mPassword;
    Button manageAccountButtonSendActivationMail;
    private static FirebaseAuth mAuth;
    private static FirebaseUser currentUser;
    private static String TAG = "ManageAccountActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_account);

        mEmail = (TextView) findViewById(R.id.manageAccountEmail);
        mAccountState = (TextView) findViewById(R.id.manageAccountVerificationState);
        mTechnicalId = (TextView) findViewById(R.id.manageAccountTechnicalId);
        mPassword = (EditText) findViewById(R.id.manageAccountPassword);


        ((Button) findViewById(R.id.manageAccountButtonSignOut)).setOnClickListener(this);
        manageAccountButtonSendActivationMail = (Button) findViewById(R.id.manageAccountButtonSendActivationMail);
        manageAccountButtonSendActivationMail.setOnClickListener(this);
        ((Button) findViewById(R.id.manageAccountButtonDeleteAccount)).setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();


        mEmail.setText("Mail : " + currentUser.getEmail());
        if (currentUser.isEmailVerified()) {
            mAccountState.setText("Account verified: true");
            manageAccountButtonSendActivationMail.setVisibility(View.GONE);
        } else {
            mAccountState.setText("Account verified: false");
        }


        mTechnicalId.setText("ID : " + currentUser.getUid());
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
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            Toast.makeText(getApplicationContext(), "No user authenticated.",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.getCurrentUser().sendEmailVerification();
        Toast.makeText(getApplicationContext(), "Authorization Email sent!", Toast.LENGTH_LONG).show();
    }

    private void doSignOut() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            Toast.makeText(getApplicationContext(), "No user signed in.",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        FirebaseAuth.getInstance().signOut();


        Toast.makeText(ManageAccountActivity.this, "You are signed out.",
                Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getApplication(),
                SignInActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void doDeleteAccount() {
        String temp_password = mPassword.getText().toString();
        if (temp_password == null || temp_password.equals("")){
            Toast.makeText(ManageAccountActivity.this, "Please enter a password!", Toast.LENGTH_LONG).show();
        }else{
            FirebaseUser user = mAuth.getCurrentUser();

            AuthCredential credential = EmailAuthProvider
                    .getCredential(user.getEmail(), mPassword.getText().toString());


            user.reauthenticate(credential)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Log.d(TAG, "User re-authenticated.");
                        }
                    });

            if (user == null) {
                // ....


                return;
            }

            user.delete()
                    .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(ManageAccountActivity.this, "Deletion of user successful!", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplication(),
                                        MainActivity.class);
                                startActivity(intent);

                            } else {
                                Toast.makeText(ManageAccountActivity.this, "Deletion of user failed!", Toast.LENGTH_LONG).show();

                            }
                        }
                    });
        }


    }
}
