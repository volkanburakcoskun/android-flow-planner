package com.example.burak.myapplication;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class flowPlanner extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_planner);

}
public void startApp(View view){
    Intent intent = new Intent(this, MenuPage.class);
    startActivity(intent);
    finish();
}}