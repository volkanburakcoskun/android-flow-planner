package com.example.burak.myapplication;


import java.util.Calendar;
import java.util.Date;

public class Task {

    public int id=0;
    public  String task_name="empty";
    public  String status="current";
    public  int next_task=0;
    public Boolean sms_trigger=false;
    public Boolean mail_trigger=false;
    public int flow_task=0;
    public String sms="empty";
    public String mail="empty";

    public String date;
    public Task(){
        Calendar c = Calendar.getInstance();
    this.date=String.valueOf(c.get(Calendar.HOUR_OF_DAY))+String.valueOf(c.get(Calendar.MINUTE));}

    public Task(String task_name, String status, int next_task, String task_type, int flow_task, Boolean sms_trigger, Boolean mail_trigger,Boolean send_mail,Boolean send_sms) {
        super();
        this.task_name = task_name;
        this.status=status;
        this.next_task=next_task;
        this.sms_trigger=sms_trigger;
        this.mail_trigger=mail_trigger;
        this.flow_task=flow_task;
    }

    //getters & setters


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Boolean getSms_trigger() {
        return sms_trigger;
    }

    public void setSms_trigger(Boolean sms_trigger) {
        this.sms_trigger = sms_trigger;
    }

    public Boolean getMail_trigger() {
        return mail_trigger;
    }

    public void setMail_trigger(Boolean mail_trigger) {
        this.mail_trigger = mail_trigger;
    }






    public int getId() {
        return id;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setId(int id) {

        this.id = id;
    }

    public  void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status){this.status = status;
    }

    public int getNext_task() {
        return next_task;
    }

    public void setNext_task(int next_task) {
        this.next_task = next_task;
    }



    public int getFlow_task() {
        return flow_task;
    }

    public void setFlow_task(int flow_task) {
        this.flow_task = flow_task;
    }


}