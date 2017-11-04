package com.example.anjaleegamage.employeeassistancesystem;

/**
 * Created by AnjaleeGamage on 4/24/2017.
 */

public class VRes {

    String vResID;
  //  String empID_VRes;
    String date_VRes;
    String time_VRes;
    String place;
    String purpose;
    String no_of_participants;

    //String empID_VRes,


    public VRes() {

    }

    public  VRes(String vResID, String place, String purpose, String no_of_participants,String date_VRes,String time_VRes){
        this.vResID = vResID;
       // this.empID_VRes = empID_VRes;
        this.date_VRes = date_VRes;
        this.time_VRes = time_VRes;
        this.place = place;
        this.purpose = purpose;
        this.no_of_participants = no_of_participants;
    }

    public String getvResID() {
        return vResID;
    }

    public String getPlace() {
        return place;
    }

    public String getPurpose() {
        return purpose;
    }

    public String getNo_of_participants() {
        return no_of_participants;
    }

    public String getDate_VRes() {
        return date_VRes;
    }

    public String getTime_VRes() {
        return time_VRes;
    }
}
