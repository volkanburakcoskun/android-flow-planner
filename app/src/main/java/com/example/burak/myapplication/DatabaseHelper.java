package com.example.burak.myapplication;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "flows_database";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_FLOWS = "flows";
    private static final String FLOW_ID = "id";
    private static final String KEY_FLOWNAME = "flow_name";
    private static final String KEY_DESCRIPTION="description";
    private static final String TABLE_TASKS="tasks";
    private static final String KEY_TASK_NAME="task_name";
    private static final String KEY_STATUS="status";
    private static final String NEXT_TASK="next_task";
    private static final String TASK_ID="id";
    private static final String SMS_TRIGGER="sms_trigger";
    private static final String MAIL_TRIGGER="mail_trigger";
    private static final String FLOWTASK="flow_task";
    private static final String SMS="sms_address";
    private static final String MAIL="mail_address";
    private static final String DATE="date_job";



    private static final String CREATE_TABLE_FLOWS = "CREATE TABLE IF NOT EXISTS "
            + TABLE_FLOWS + "(" + FLOW_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_FLOWNAME + " TEXT,"+ KEY_DESCRIPTION + " TEXT" +" );";

    private static final String CREATE_TABLE_TASK="CREATE TABLE IF NOT EXISTS "+ TABLE_TASKS + "(" + TASK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
          +SMS_TRIGGER+ " BOOLEAN,"+ MAIL_TRIGGER+ " BOOLEAN," + SMS+ " TEXT,"+MAIL+" TEXT,"+ KEY_TASK_NAME+ " TEXT," + KEY_STATUS + " TEXT,"+FLOWTASK+" INTEGER,"+DATE+" TEXT,"+NEXT_TASK+ " INTEGER" +" );";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_FLOWS);
        db.execSQL(CREATE_TABLE_TASK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_FLOWS + "'");
        onCreate(db);
    }

    public long addNewFlow(Flow newFlow) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Creating content values
        ContentValues values = new ContentValues();
        values.put(KEY_FLOWNAME, newFlow.getFlow_name().toString());
        values.put(KEY_DESCRIPTION, newFlow.getDescription().toString());
        // insert row in students table
        long insert = db.insert(TABLE_FLOWS, null, values);

        return insert;
    }
    public long addNewTask(Task newTask) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Creating content values
        ContentValues values = new ContentValues();
        values.put(KEY_TASK_NAME, newTask.getTask_name().toString());
        values.put(KEY_STATUS, newTask.getStatus().toString());
        values.put(NEXT_TASK, newTask.getNext_task());
        values.put(SMS_TRIGGER, newTask.getSms_trigger());
        values.put(MAIL_TRIGGER, newTask.getMail_trigger());
        values.put(MAIL,newTask.getMail());
        values.put(SMS,newTask.getSms());
        values.put(FLOWTASK, newTask.getFlow_task());

        Log.d("array", newTask.getSms());
        values.put(DATE,newTask.getSms());
        // insert row in students table
        long insert = db.insert(TABLE_TASKS,null,  values);

        return insert;
    }
    public ArrayList<String> getAllFlows() {
        ArrayList<String> flowsArrayList = new ArrayList<String>();
        String flowName="";
        String description="";
        String selectQuery = "SELECT  * FROM " + TABLE_FLOWS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                flowName = c.getString(c.getColumnIndex(KEY_FLOWNAME));
                description =c.getString(c.getColumnIndex(KEY_DESCRIPTION));
                // adding to Students list
                flowsArrayList.add(flowName+description);
            } while (c.moveToNext());
        }
        return flowsArrayList;
    }

    public ArrayList<Task> getoneFlow(int id){

        ArrayList<Task> taskArrayList=new ArrayList<>();
        String selectQuery="SELECT * FROM "+ TABLE_TASKS+" WHERE "+FLOWTASK+"="+id;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c =db.rawQuery(selectQuery,null);
        if(c.moveToFirst()){
            do{
                Task a=new Task();
                a.setId(c.getInt(c.getColumnIndex(TASK_ID)));
                a.setTask_name(c.getString(c.getColumnIndex(KEY_TASK_NAME)));
                a.setFlow_task(c.getInt(c.getColumnIndex(FLOWTASK)));
                a.setStatus(c.getString(c.getColumnIndex(KEY_STATUS)));
                taskArrayList.add(a);

                a=null;

            }while(c.moveToNext()) ;
        }
        return taskArrayList;
    }
    public long changeToFinished(int id){
        String updateQuery="UPDATE "+TABLE_TASKS+" SET "+KEY_STATUS+"="+"'finished'"+" WHERE "+TASK_ID+"="+id+";";
        SQLiteDatabase db=this.getReadableDatabase();
        db.execSQL(updateQuery);
        return 4;
    }
    public long changeToCurrent(int id){
        String updateQuery="UPDATE "+TABLE_TASKS+" SET "+KEY_STATUS+"="+"'current'"+" WHERE "+TASK_ID+"="+id+";";
        SQLiteDatabase db=this.getReadableDatabase();
        db.execSQL(updateQuery);
        return 4;
    }
    public long changeToFinishedWithSms(String number){
        String updateQuery="UPDATE "+TABLE_TASKS+" SET "+KEY_STATUS+"="+"'finished'"+" WHERE "+SMS+"="+number+";";
        SQLiteDatabase db=this.getReadableDatabase();
        db.execSQL(updateQuery);
        return 4;
    }
    public ArrayList<Flow> getAllFlow(){


        ArrayList<Flow> flowArrayList=new ArrayList<>();
        String selectQuery="SELECT * FROM "+ TABLE_FLOWS;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c =db.rawQuery(selectQuery,null);
        if(c.moveToFirst()){
            do{
                Flow a=new Flow();
                a.setFlow_name(c.getString(c.getColumnIndex(KEY_FLOWNAME)));

                a.setDescription(c.getString(c.getColumnIndex(KEY_DESCRIPTION)));
                a.setId(c.getInt(c.getColumnIndex(FLOW_ID)));
                flowArrayList.add(a);
                a=null;

            }while(c.moveToNext()) ;
        }
        return flowArrayList;
    }
    public void toast(String number){
        String updateQuery="UPDATE "+TABLE_TASKS+" SET "+KEY_STATUS+"="+"'finished'"+" WHERE "+SMS+"="+number+";";
        SQLiteDatabase db=this.getReadableDatabase();
        db.execSQL(updateQuery);

        Log.d("array", "a");
    }

}