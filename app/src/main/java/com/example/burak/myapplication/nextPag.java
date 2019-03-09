package com.example.burak.myapplication;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class nextPag extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private Button mButton1,mButton2,mButton3,mButton4,mButton5;
    private CheckBox cb1,cb2,cb3,cb4,cb5,cb6,cb7,cb8;
    private int selectedh[]=new int[4];
    private int selectedm[]=new int[4];
    private Boolean sms_trigger[]=new Boolean[4];
    private Boolean mail_trigger[]=new Boolean[4];
    List<EditText> allEds = new ArrayList<EditText>();
    private EditText sms[]=new EditText[4];
    private EditText mail[]=new EditText[4];
    private EditText taskName[]=new EditText[4];
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_pag);
        databaseHelper = new DatabaseHelper(this);
        Calendar c = Calendar.getInstance();
        for(int j=0;j<4;j++){
            selectedh[j]=c.get(Calendar.HOUR_OF_DAY)+1;
            selectedm[j]=c.get(Calendar.MINUTE);
        }
        for(int j=0;j<4;j++){
            sms_trigger[j]=false;
            mail_trigger[j]=false;
        }
        mButton1=(Button)findViewById(R.id.button1);
        mButton2=(Button)findViewById(R.id.button2);
        mButton3=(Button)findViewById(R.id.button3);
        mButton4=(Button)findViewById(R.id.button4);
        mButton5=(Button)findViewById(R.id.button5);
        cb1=(CheckBox)findViewById(R.id.sms1);
        cb2=(CheckBox)findViewById(R.id.mail1);
        cb3=(CheckBox)findViewById(R.id.sms2);
        cb4=(CheckBox)findViewById(R.id.mail2);
        cb5=(CheckBox)findViewById(R.id.sms3);
        cb6=(CheckBox)findViewById(R.id.mail3);
        cb7=(CheckBox)findViewById(R.id.sms4);
        cb8=(CheckBox)findViewById(R.id.mail4);
        taskName[0]=(EditText)findViewById(R.id.editText);
        taskName[1]=(EditText)findViewById(R.id.editText5);
        taskName[2]=(EditText)findViewById(R.id.editText9);
        taskName[3]=(EditText)findViewById(R.id.editText13);
        sms[0]=findViewById(R.id.editText3);
        mail[0]=findViewById(R.id.editText4) ;
        sms[1]=findViewById(R.id.editText7);
        mail[1]=findViewById(R.id.editText8);
        sms[2]=findViewById(R.id.editText11);
        mail[2]=findViewById(R.id.editText12);
        sms[3]=findViewById(R.id.editText15);
        mail[3]=findViewById(R.id.editText16);
        mButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                showDialog(999);
            }
        });
        mButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                showDialog(1000);
            }
        });
        mButton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                showDialog(1001);
            }
        });
        mButton4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                showDialog(1002);
            }
        });
        mButton5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Flow newFlow =new Flow();
                Task b=new Task();
                long tempId=0;
                Bundle extras = getIntent().getExtras();
                String flowName=extras.getString("flowName");
                String desc=extras.getString("desc");
                if(flowName.equals("")){
                    flowName="noName";
                }
                if(desc.equals("")){
                desc="noName";
                }
                newFlow.setFlow_name(flowName);
                newFlow.setDescription(desc);
                long a=databaseHelper.addNewFlow(newFlow);

                for(int i=3;i>=0;i--){
                    b.setStatus("current");
                    b.setFlow_task((int)a);
                    b.setNext_task((int)tempId);
                    if(taskName[i].getText().toString().equals("")){
                        b.setTask_name("noName");
                    }else {
                        b.setTask_name(taskName[i].getText().toString());
                    }
                    b.setMail_trigger(mail_trigger[i]);
                    b.setDate(String.valueOf(selectedh[i])+String.valueOf(selectedm[i]));
                    startAlarm(selectedh[i],selectedm[i]);
                    if(sms_trigger[i].equals(true))
                    {
                        b.setSms_trigger(sms_trigger[i]);
                        b.setSms(sms[i].getText().toString());
                    }
                    else{
                        b.setSms("empty");
                        b.setSms_trigger(sms_trigger[i]);
                    }
                    if(mail_trigger[i].equals(true)){
                        b.setMail_trigger(mail_trigger[i]);
                        b.setMail(mail[i].getText().toString());
                    }
                    else{
                        b.setMail("empty");
                        b.setMail_trigger(mail_trigger[i]);
                    }

                    tempId=databaseHelper.addNewTask(b);
                }
                finish();


            }
        });
        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    sms[0].setVisibility(View.VISIBLE);
                    sms_trigger[0]=true;
                }
                else{
                    sms[0].setVisibility(View.INVISIBLE);
                    sms_trigger[0]=false;
                }
            }
        });
        cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mail[0].setVisibility(View.VISIBLE);
                    mail_trigger[0]=true;
                }
                else {
                    mail[0].setVisibility(View.INVISIBLE);
                    mail_trigger[0]=false;
                }
            }
        });
        cb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    sms[1].setVisibility(View.VISIBLE);
                    sms_trigger[1]=true;
                }
                else {
                    sms[1].setVisibility(View.INVISIBLE);
                    sms_trigger[1]=false;
                }
            }
        });
        cb4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mail[1].setVisibility(View.VISIBLE);
                    mail_trigger[1]=true;
                }
                else {
                    mail[1].setVisibility(View.INVISIBLE);
                    mail_trigger[1]=false;
                }
            }
        });



        cb5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    sms[2].setVisibility(View.VISIBLE);
                    sms_trigger[2]=true;
                }
                else {
                    sms[2].setVisibility(View.INVISIBLE);
                    sms_trigger[2]=false;
                }
            }
        });
        cb6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mail[2].setVisibility(View.VISIBLE);
                    mail_trigger[2]=true;
                }
                else {
                    mail[2].setVisibility(View.INVISIBLE);
                    mail_trigger[2]=false;
                }
            }
        });

        cb7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    sms[3].setVisibility(View.VISIBLE);
                    sms_trigger[3]=true;
                }
                else {
                    sms[3].setVisibility(View.INVISIBLE);
                    sms_trigger[3]=false;
                }
            }
        });
        cb8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mail[3].setVisibility(View.VISIBLE);
                    mail_trigger[3]=true;
                }
                else {
                    mail[3].setVisibility(View.INVISIBLE);
                    mail_trigger[3]=false;
                }
            }
        });

    }

    protected Dialog onCreateDialog(int id) {
        if (id == 999) {
            TimePickerDialog timePicker = new TimePickerDialog(nextPag.this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                    mButton1.setText(String.valueOf(selectedHour)+":"+String.valueOf(selectedMinute));
                    selectedh[0]=selectedHour;
                    selectedm[0]=selectedMinute;
                }
            }, this.selectedh[0], selectedm[0], true);
            timePicker.setTitle("Select Time");
            timePicker.setButton(DatePickerDialog.BUTTON_POSITIVE, "Set", timePicker);
            timePicker.setButton(DatePickerDialog.BUTTON_NEGATIVE, "Cancel", timePicker);

            timePicker.show();
        }
        if(id==1000){
            TimePickerDialog timePicker = new TimePickerDialog(nextPag.this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                    mButton2.setText(String.valueOf(selectedHour)+":"+String.valueOf(selectedMinute));
                    selectedh[1]=selectedHour;
                    selectedm[1]=selectedMinute;
                }
            }, this.selectedh[1], this.selectedm[1], true);//true 24 saatli sistem için
            timePicker.setTitle("Select Time");
            timePicker.setButton(DatePickerDialog.BUTTON_POSITIVE, "Set", timePicker);
            timePicker.setButton(DatePickerDialog.BUTTON_NEGATIVE, "Cancel", timePicker);

            timePicker.show();
        }
        if(id==1001){
            TimePickerDialog timePicker = new TimePickerDialog(nextPag.this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                    mButton3.setText(String.valueOf(selectedHour)+":"+String.valueOf(selectedMinute));
                    selectedh[2]=selectedHour;
                    selectedm[2]=selectedMinute;
                }
            }, this.selectedh[2], this.selectedm[2], true);//true 24 saatli sistem için
            timePicker.setTitle("Select Time");
            timePicker.setButton(DatePickerDialog.BUTTON_POSITIVE, "Set", timePicker);
            timePicker.setButton(DatePickerDialog.BUTTON_NEGATIVE, "Cancel", timePicker);

            timePicker.show();
        }
        if(id==1002){
            TimePickerDialog timePicker = new TimePickerDialog(nextPag.this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                    mButton4.setText(String.valueOf(selectedHour)+":"+String.valueOf(selectedMinute));
                    selectedh[3]=selectedHour;
                    selectedm[3]=selectedMinute;
                }
            }, this.selectedh[3], this.selectedm[3], true);//true 24 saatli sistem için
            timePicker.setTitle("Select Time");
            timePicker.setButton(DatePickerDialog.BUTTON_POSITIVE, "Set", timePicker);
            timePicker.setButton(DatePickerDialog.BUTTON_NEGATIVE, "Cancel", timePicker);
            timePicker.show();
        }

        return null;
    }

    private void startAlarm(int hour,int minute) {
        AlarmManager manager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent myIntent;
        PendingIntent pendingIntent;
        Calendar timeCal = Calendar.getInstance();
        timeCal.set(Calendar.HOUR_OF_DAY, hour);
        timeCal.set(Calendar.MINUTE, minute);
        timeCal.set(Calendar.SECOND,0);


            myIntent=new Intent(nextPag.this,alarmNotificationReceiver.class);
            pendingIntent=PendingIntent.getBroadcast(this,0,myIntent,0);

            manager.set(AlarmManager.RTC_WAKEUP,timeCal.getTimeInMillis()+3000,pendingIntent);


    }
}
