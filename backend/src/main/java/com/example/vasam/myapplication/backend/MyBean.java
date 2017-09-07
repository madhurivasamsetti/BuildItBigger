package com.example.vasam.myapplication.backend;

/**
 * The object model for the data we are sending through endpoints
 */
public class MyBean {

    private String myData;

    public void setData(String data) {
        myData = data;
    }

    public String getData() {
        return myData;
    }


}