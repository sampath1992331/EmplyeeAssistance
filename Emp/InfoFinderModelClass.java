package com.example.anjaleegamage.employeeassistancesystem;

/**
 * Created by AnjaleeGamage on 4/20/2017.
 */

public class InfoFinderModelClass {
    String tpno,fname,lname,email;

    public InfoFinderModelClass(String fname, String tpno, String lname, String email){

        this.fname=fname;
        this.tpno=tpno;
        this.lname=lname;
        this.email=email;
    }

    public InfoFinderModelClass() {
    }

    public String getTpno() {
        return tpno;
    }

    public void setTpno(String tpno) {
        this.tpno = tpno;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
