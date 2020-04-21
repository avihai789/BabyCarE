package com.example.babycare;

public class DateOfAlert {
    String dateAndTime;

    public DateOfAlert(){

    }

    public DateOfAlert(String time){
        this.dateAndTime=time;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }
}
