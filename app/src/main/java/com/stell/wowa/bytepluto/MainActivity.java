package com.stell.wowa.bytepluto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "XX MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "my first message!");
        setContentView(R.layout.activity_main);
        Toast.makeText(getApplicationContext(), R.string.dashboard_Text, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent intent;
        switch (item.getItemId()){
            case R.id.mainMenuPost:
                Log.d(TAG, "Post was pressed");
                return true;

            case R.id.mainMenuHelp:
                Log.d(TAG, "Help was pressed");
                return true;

            case R.id.mainMenuTest:
                Log.d(TAG, "Test was pressed");
                return true;

            case R.id.idWriteTime:
                Log.d(TAG, "WriteTime was pressed");
                return true;

            case R.id.mainMenuManageAccount:
                Log.d(TAG, "ManageAccount was pressed.");
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
