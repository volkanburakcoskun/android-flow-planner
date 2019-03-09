package com.example.burak.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class showFlows extends AppCompatActivity {
    private LinearLayout mLayout;
    private DatabaseHelper databaseHelper;
    private ArrayList<Flow> FlowList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_flows);
        mLayout = (LinearLayout) findViewById(R.id.linearLayout);

        databaseHelper = new DatabaseHelper(this);
        FlowList= databaseHelper.getAllFlow();
        for (int i = 0; i <FlowList.size(); i++){

            Button b=createButton(FlowList.get(i).getFlow_name(),String.valueOf(FlowList.get(i).getId()));

            b.setOnClickListener(
                    new View.OnClickListener() {

                        @Override
                        public void onClick(View view) {
                            // TODO Auto-generated method stub
                            Button b=(Button) view;
                            Intent intent = new Intent(showFlows.this, ShowPlans.class);
                            intent.putExtra("flowId", String.valueOf(b.getId()));
                            startActivity(intent);
                        }
                    });
            mLayout.addView(b);
        }
    }



    private Button createButton(String text,String id) {
        final LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        final Button flow = new Button(this);
        flow.setId(Integer.parseInt(id));
        Log.d("sflws", String.valueOf(id));
        flow.setLayoutParams(lparams);
        flow.setText(text);
        return flow;
    }
}
