package com.stell.wowa.bytepluto;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener{

    EditText mEditTextEmail;
    EditText mEditTextPassword;

    private FirebaseAuth mAuth;

    private static String TAG = "SignIn Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mEditTextEmail = (EditText) findViewById(R.id.signInEmail);
        mEditTextPassword = (EditText) findViewById(R.id.signInPassword);

        mAuth = FirebaseAuth.getInstance();



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
            String mail = mEditTextEmail.getText().toString();
            String password = mEditTextPassword.getText().toString();

            mAuth.signInWithEmailAndPassword(mail, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(getApplicationContext(), "User signed in.",
                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplication(), MainActivity.class);
                                intent.putExtra("message", "hi");
                                startActivityForResult(intent, 1);

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInUserWithEmail:failure", task.getException());
                                Toast.makeText(getApplicationContext(), "User signIn failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                            // ...
                        }
                    });




    }
}
