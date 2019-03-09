package com.example.burak.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class newActivity extends AppCompatActivity {
    private EditText flowName,desc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        flowName = (EditText) findViewById(R.id.flowName);
        desc= (EditText) findViewById(R.id.description);




}
    public void nextPag(View view){

        Intent intent = new Intent(this, nextPag.class);
        intent.putExtra("flowName", flowName.getText().toString());
        intent.putExtra("desc", desc.getText().toString());
        startActivity(intent);


    }}
