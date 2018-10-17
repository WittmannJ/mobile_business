package com.stell.wowa.bytepluto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity{



    private static String TAG = "MainActivity";
    private static boolean currentUser = false;

    @Override
    protected void onStart() {

        super.onStart();

        if (!currentUser ) {
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
                return true;

            case R.id.mainMenuHelp:
                Log.d(TAG, "Help was pressed");
                return true;

            case R.id.mainMenuTest:
                Log.d(TAG, "Test was pressed");
                return true;

            case R.id.mainMenuManageAccount:
                Log.d(TAG, "ManageAccount was pressed.");
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
