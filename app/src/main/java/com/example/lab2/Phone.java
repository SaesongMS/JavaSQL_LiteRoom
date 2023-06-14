package com.example.lab2;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "phone_table")
public class Phone {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int mID;

    @NonNull
    @ColumnInfo(name = "producer")
    private String mProducer;

    @NonNull
    @ColumnInfo(name = "model")
    private String mModel;

    @NonNull
    @ColumnInfo(name = "androidVersion")
    private String mAndroidVersion;

    @NonNull
    @ColumnInfo(name = "website")
    private String mWebsite;

    public Phone(@NonNull String producer, @NonNull String model, @NonNull String androidVersion, @NonNull String website) {
        this.mProducer = producer;
        this.mModel = model;
        this.mAndroidVersion = androidVersion;
        this.mWebsite = website;
    }

    public Phone(int id, @NonNull String producer, @NonNull String model, @NonNull String androidVersion, @NonNull String website) {
        this.mID = id;
        this.mProducer = producer;
        this.mModel = model;
        this.mAndroidVersion = androidVersion;
        this.mWebsite = website;
    }

    public int getID(){return this.mID;}
    public String getProducer(){return this.mProducer;}
    public String getModel(){return this.mModel;}
    public String getAndroidVersion(){return this.mAndroidVersion;}
    public String getWebsite(){return this.mWebsite;}

    public void setID(int id){this.mID = id;}
    public void setProducer(String producer){this.mProducer = producer;}
    public void setModel(String model){this.mModel = model;}
    public void setAndroidVersion(String androidVersion){this.mAndroidVersion = androidVersion;}
    public void setWebsite(String website){this.mWebsite = website;}


}
