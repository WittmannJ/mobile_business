package com.stell.wowa.bytepluto;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.stell.wowa.bytepluto.model.Post;
import com.stell.wowa.bytepluto.test.PostTestData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TEST_USERNAME = "Hans Huber";
    private static final String TEST_MAIL = "hans.huber@gmail.com";
    private static final String TEST_PASSWORD = "123456";
    private static final String TEST_NEW_DISPLAY_NAME = "Sepp Maier";

    List<Post> mPostList = PostTestData.createTestData();
    ListView mListView;

    private static String TAG = "MainActivity";
    private static FirebaseUser currentUser;

    private FirebaseAuth mAuth;

    private ChildEventListener mChildEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            Log.d(TAG, "onChildAdded: " + dataSnapshot.getKey());
        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            Log.d(TAG, "onChildChanged: " + dataSnapshot.getKey());
        }

        @Override
        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
            Log.d(TAG, "onChildRemoved: " + dataSnapshot.getKey());
        }

        @Override
        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            Log.d(TAG, "onChildMoved: " + dataSnapshot.getKey());
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            Log.d(TAG, "called onCancelled - Listener died ");
        }
    };

    FirebaseDatabase mDatabase;
    DatabaseReference myRef;

    @Override
    protected void onStart() {

        super.onStart();
        Log.d("lifecycle", "onStart invoked");

        currentUser = mAuth.getCurrentUser();

        if (currentUser == null) {
            Log.d(TAG, "User not authenticated! ");
            Intent intent = new Intent(getApplication(),
                    SignInActivity.class);
            startActivity(intent);

        } else {
            Log.d(TAG, "User authenticated.");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("lifecycle", "onCreate invoked");
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();


        ArrayAdapter<Post> mAdapter = new ArrayAdapter<Post>(
                this,
                android.R.layout.simple_list_item_2,
                android.R.id.text1,
                mPostList
        ) {
            @NonNull
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView text1, text2;
                text1 = (TextView) view.findViewById(android.R.id.text1);
                text2 = (TextView) view.findViewById(android.R.id.text2);


                // Latest posts first...

                Post post = getItem(getCount() - position - 1);

                text1.setText(post.author);
                text2.setText(post.title + "\n" + post.body);
                return view;
            }
        };


        mListView = (ListView) findViewById(R.id.listViewMessages);
        mListView.setAdapter(mAdapter);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        FirebaseDatabase.getInstance().getReference("Posts/").addChildEventListener(mChildEventListener);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.mainMenuPost:
                Log.d(TAG, "Post was pressed");

                intent = new Intent(getApplication(), PostActivity.class);
                startActivity(intent);
                return true;

            case R.id.mainMenuManageAccount:
                Log.d(TAG, "ManageAccount was pressed.");

                intent = new Intent(getApplication(),
                        ManageAccountActivity.class);
                startActivity(intent);
                return true;

            case R.id.sendResetPasswordMail:
                Log.d(TAG, "sendResetPaswordMail was pressed.");
                sendResetPasswordMail();
                return true;


            case R.id.test_post:
                Log.d(TAG, "send test post");
                sendTestPost();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void sendTestPost(){
        FirebaseUser mCurrentUser = mAuth.getCurrentUser();
        Map<String, Object> postMap = new HashMap<>();
        postMap.put("uid", mAuth.getCurrentUser().getUid());
        postMap.put("author", mCurrentUser.getEmail());
        postMap.put("title", "test-title");
        postMap.put("body", "test-body");
        postMap.put("timestamp", ServerValue.TIMESTAMP);

        mDatabase = FirebaseDatabase.getInstance();
        myRef = mDatabase.getReference("Posts/");
        myRef.push().setValue(postMap);
    }

    private void signOutTestuser() {

        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            Toast.makeText(getApplicationContext(), "No user signed in.",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        FirebaseAuth.getInstance().signOut();


        Toast.makeText(MainActivity.this, "You are signed out.",
                Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getApplication(),
                SignInActivity.class);
        startActivity(intent);

    }

    private void setDisplayName(String testNewDisplayName) {


    }

    private void signInTestuser(String testMail, String testPassword) {
        mAuth.signInWithEmailAndPassword(testMail, testPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(getApplicationContext(), "User signed in.",
                                    Toast.LENGTH_SHORT).show();

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

    private void testAuthStatus() {
        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null && user.getEmail() != null) {
            Toast.makeText(MainActivity.this, user.getEmail(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MainActivity.this, "No User logged in", Toast.LENGTH_LONG).show();

        }


    }

    private void deleteTestUser() {


        FirebaseUser user = mAuth.getCurrentUser();

        AuthCredential credential = EmailAuthProvider
                .getCredential(TEST_MAIL, TEST_PASSWORD);


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
                            // Sign in success, update UI with the signed-in user's information
                            // ...

                        } else {
                            // If sign in fails, display a message to the user.
                            // ...

                        }
                    }
                });
    }

    private void sendResetPasswordMail() {

        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            if (user.getEmail() != null) {
                mAuth.sendPasswordResetEmail(user.getEmail())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "We sent you a link to your e-mail account.",
                                            Toast.LENGTH_LONG).show();
                                    Log.d(TAG, "Email sent.");
                                } else {
                                    Toast.makeText(getApplicationContext(), "Could not send mail. Correct e-mail?.",
                                            Toast.LENGTH_LONG).show();

                                }

                            }
                        });
            }
        }


    }

    private void sendMailVerification() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            Toast.makeText(getApplicationContext(), "No user authentiated.",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.getCurrentUser().sendEmailVerification();
    }

    private void signInWithGoogle() {

    }

    private void createTestUser(String testMail, String testPassword) {
        mAuth.createUserWithEmailAndPassword(testMail, testPassword)
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


    @Override
    protected void onResume() {
        super.onResume();
        Log.d("lifecycle", "onResume invoked");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("lifecycle", "onPause invoked");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("lifecycle", "onStop invoked");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("lifecycle", "onRestart invoked");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("lifecycle", "onDestroy invoked");
    }


}


/*

   ---> keine Ahnung an welcher Stelle im Code das hier am meisten Sinn macht

    AuthCredential credential = EmailAuthProvider.getCredential(email, password);

        mUser.reauthenticate(credential)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
@Override
public void onComplete(@NonNull Task<Void> task) {
        if (task.isSuccessful()) {
        Log.d(TAG, "User re-authenticated.");
        deleteUserAccount();
        } else
        Log.d(TAG, "FAIL! User not re-authenticated.");
        }
        });*/
