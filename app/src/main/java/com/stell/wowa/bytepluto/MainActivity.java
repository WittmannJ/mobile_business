package com.stell.wowa.bytepluto;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

import com.stell.wowa.bytepluto.model.Post;
import com.stell.wowa.bytepluto.test.PostTestData;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    List<Post> mPostList = PostTestData.createTestData();

    ListView mListView;

    private static String TAG = "MainActivity";
    private static String currentUser = null;

    @Override
    protected void onStart() {

        super.onStart();

        if (currentUser == null ) {
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
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        currentUser = intent.getStringExtra("message");
        Toast.makeText(this, currentUser, Toast.LENGTH_LONG).show();

        ArrayAdapter<Post> mAdapter = new ArrayAdapter<Post>(
                this,
                android.R.layout.simple_list_item_2,
                android.R.id.text1,
                mPostList
        ) {
            @NonNull
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
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

            case R.id.mainMenuHelp:
                Log.d(TAG, "Help was pressed");
                return true;

            case R.id.mainMenuTest:
                Log.d(TAG, "Test was pressed");
                return true;

            case R.id.mainMenuManageAccount:
                Log.d(TAG, "ManageAccount was pressed.");

                intent = new Intent(getApplication(),
                        ManageAccountActivity.class);
                startActivity(intent);


                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
