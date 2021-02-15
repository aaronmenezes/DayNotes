package com.kyser.daynotes.services.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Note{

    private int id;
    private int key;
    private int priority;
    private String name;
    private String body;
    private String date;

    public void setId(int id) {
        this.id = id;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public int getKey() {
        return key;
    }

    public int getPriority() {
        return priority;
    }

    public String getName() {
        return name;
    }

    public String getBody() {
        return body;
    }

    public String getDate() {
        return date;
    }

}
