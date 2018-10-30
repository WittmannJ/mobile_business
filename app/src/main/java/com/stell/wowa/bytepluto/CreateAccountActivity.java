package com.stell.wowa.bytepluto;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CreateAccountActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEditTextEMail;
    private EditText mEditTextPassword1;
    private EditText mEditTextPassword2;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        mAuth = FirebaseAuth.getInstance();

        mEditTextEMail = ((EditText) findViewById(R.id.createAccountEmail));
        mEditTextPassword1 = ((EditText) findViewById(R.id.createAccountPassword1));


        ((Button) findViewById(R.id.createAccountButtonCreateAccount)).setOnClickListener(this);

        /*
        // TODO: Remove presets later
        mEditTextEMail.setText("xxx@gmail.com");
        mEditTextPassword1.setText("123456");
        mEditTextPassword2.setText("123456");
        */
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        switch (i) {
            case R.id.createAccountButtonCreateAccount:
                doCreateAccount();
                return;
            default:
                return;
        }
    }

    private void doCreateAccount() {
        String mail = mEditTextEMail.getText().toString();
        String password = mEditTextPassword1.getText().toString();

        mAuth.createUserWithEmailAndPassword(mail, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Create User successful!", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Create User failed!", Toast.LENGTH_LONG).show();
                        }

                    }
                });


    }
}

