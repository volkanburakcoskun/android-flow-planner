package com.example.burak.myapplication;

import android.content.Intent;
import android.graphics.Color;
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

public class ShowPlans extends AppCompatActivity {

    private EditText etname;
    private DatabaseHelper databaseHelper;
    private TextView tvnames;
    private ArrayList<Task> TaskList;
    private LinearLayout mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_plans);

        databaseHelper = new DatabaseHelper(this);
        mLayout = (LinearLayout) findViewById(R.id.linearLayout);
        Bundle extras = getIntent().getExtras();
        String id=extras.getString("flowId");

       TaskList = databaseHelper.getoneFlow(Integer.parseInt(id));
        for (int i = TaskList.size()-1; i >=0; i--){

                Button b=createButton(TaskList.get(i).getTask_name(),String.valueOf(TaskList.get(i).getId()));
                if(TaskList.get(i).getStatus().equals("finished"))
                {
                    b.setBackgroundColor(Color.parseColor("#ff0000"));
                    b.setText(b.getText()+" (Finished)");
                }
                else{
                    b.setBackgroundColor(Color.parseColor("#A4C639"));
                    b.setText(b.getText());
                }
                final Task a =TaskList.get(i);
                b.setOnClickListener(
                        new View.OnClickListener() {

                            @Override
                            public void onClick(View view) {
                                Button b=(Button) view;
                                if(a.getStatus().equals("current")){
                                    b.setBackgroundColor(Color.parseColor("#ff0000"));
                                    b.setText(a.getTask_name()+" (Finished)");
                                    databaseHelper.changeToFinished(b.getId());
                                    a.setStatus("finished");
                                }
                                else{
                                    b.setBackgroundColor(Color.parseColor("#A4C639"));
                                    b.setText(a.getTask_name());

                                    databaseHelper.changeToCurrent(b.getId());

                                    a.setStatus("current");
                                }
                            }
                        });
                mLayout.addView(b);
            }



    }
    private Button createButton(String text,String id) {
        final LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        final Button task = new Button(this);
        task.setId(Integer.parseInt(id));
        task.setBackgroundColor(Color.parseColor("#A4C639"));
        task.setLayoutParams(lparams);
        task.setText(text);
        return task;
    }
}