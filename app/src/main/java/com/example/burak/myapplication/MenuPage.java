package com.example.burak.myapplication;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MenuPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_page);
        Intent intent = getIntent();
    }
    public void stopApp(View view){
        finish();
        System.exit(0);
    }
    public void newActivity(View view){
        Intent intent = new Intent(this, newActivity.class);
        startActivity(intent);
    }
    public void editActivity(View view){
        Intent intent = new Intent(this, showFlows.class);
        startActivity(intent);
    }
    public void settings(View view){
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }
}