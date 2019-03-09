package com.example.burak.myapplication;


public class Flow {
    public int id;
    public String flow_name;
    public String description;
    public Flow(){}

    public Flow(String flow_name,String description) {
        super();
    this.flow_name=flow_name;
    this.description=description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public  String getFlow_name() {
        return flow_name;
    }

    public void setFlow_name(String flow_name) {
        this.flow_name = flow_name;
    }

    public  String getDescription() {
        return description;
    }

    public  void setDescription(String description) {
        this.description = description;
    }
//getters & setters



}