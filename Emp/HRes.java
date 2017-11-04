package com.example.anjaleegamage.employeeassistancesystem;

/**
 * Created by Umesh on 5/8/2017.
 */
public class HRes {

    String resID;
    String resHall;
    String resDate;
    String resTimeFrom;
    String resTimeTo;
    String resDept;
    String resRemarks;


    public HRes(String resID, String resDate, String resHall, String resDept, String resTimeFrom, String resTimeTo, String resRemaks) {

        this.resID=resID;
        this.resDate=resDate;
        this.resHall=resHall;
        this.resDept=resDept;
        this.resTimeFrom=resTimeFrom;
        this.resTimeTo=resTimeTo;
        this.resRemarks=resRemaks;


    }
    public String getResID() {
        return resID;
    }
    public String getResHall() {
        return resHall;
    }
    public String getResDate() {
        return resDate;
    }
    public String getResDept() {
        return resDept;
    }public String getResTimeFrom() {
        return resTimeFrom;
    }
    public String getResTimeTo() {
        return resTimeTo;
    }
    public String getResRemarks() {
        return resRemarks;
    }


}
