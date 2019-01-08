package com.stell.wowa.bytepluto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.util.HashMap;
import java.util.Map;

public class PostActivity extends AppCompatActivity implements View.OnClickListener {

    EditText mPostTitle;
    EditText mPostBody;
    FirebaseAuth mAuth;
    FirebaseUser mCurrentUser;
    FirebaseDatabase mDatabase;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        mPostTitle = (EditText) findViewById( R.id.postTitle);
        mPostBody  = (EditText) findViewById( R.id.postText);

        ((Button) findViewById( R.id.postButtonPost)).setOnClickListener( this );
        String body = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ";
        mPostBody.setText(body);

        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();



        //  TODO: Remove; test setting

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        switch (i) {
            case R.id.postButtonPost:
                doPost();
                return;
            default:
                return;
        }
    }

    private void doPost() {

        // TODO: Add checkings before posting
        // Nur zur Sicherheit.. sollte in der Regel nicht auftauchen
        if (mCurrentUser == null){
            return;
        }

        Map<String, Object> postMap = new HashMap<>();
        postMap.put("uid", mCurrentUser.getUid());
        postMap.put("author", mCurrentUser.getEmail());
        postMap.put("title", mPostTitle.getText().toString());
        postMap.put("body", mPostBody.getText().toString());
        postMap.put("timestamp", ServerValue.TIMESTAMP);

        mDatabase = FirebaseDatabase.getInstance();
        myRef = mDatabase.getReference("Posts/");
        myRef.push().setValue(postMap);

        Toast.makeText(getApplicationContext(), "Post geschrieben", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplication(),
                MainActivity.class);
        startActivity(intent);
    }
}
