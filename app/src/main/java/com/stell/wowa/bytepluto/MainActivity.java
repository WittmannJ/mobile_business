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
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_main);
        Toast.makeText(getApplicationContext(), R.string.toastString, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onReStart");
    }

    /* Der Inflater lädt die Daten aus dem Menu (XML) und bläßt es in die View (Activity)
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater(); //braucht man sich nicht merken, nur was das logisch macht
        inflater.inflate(R.menu.main_menu, menu); // braucht man sich nicht merken, nur was das logisch macht
        return true;
    }

    // Hier hinterlegt man die Logik für die jeweiligen MenuItems
    // Die Referenz für das MenuItem kommt vom MenuInflater, dieser setzt die Referenz der MenuItems für unsere Methode
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent intent;
        switch (item.getItemId()){
            case R.id.mainMenuPost:
                Log.d(TAG, "Post was pressed");
                Toast.makeText(getApplicationContext(), "Post", Toast.LENGTH_LONG).show();
                return true;

            case R.id.mainMenuHelp:
                Log.d(TAG, "Help was pressed");
                Toast.makeText(getApplicationContext(), "Help", Toast.LENGTH_LONG).show();
                return true;

            case R.id.mainMenuTest:
                Log.d(TAG, "Test was pressed");
                Toast.makeText(getApplicationContext(), "Test", Toast.LENGTH_LONG).show();
                return true;

            case R.id.idWriteTime:
                Log.d(TAG, "WriteTime was pressed");
                Toast.makeText(getApplicationContext(), "WriteTime", Toast.LENGTH_LONG).show();
                return true;

            case R.id.mainMenuManageAccount:
                Log.d(TAG, "ManageAccount was pressed.");
                Toast.makeText(getApplicationContext(), "Manage Account", Toast.LENGTH_LONG).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
