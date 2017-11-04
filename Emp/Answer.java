package com.example.anjaleegamage.employeeassistancesystem;

/**
 * Created by AnjaleeGamage on 5/3/2017.
 */

import java.io.Serializable;

public class Answer implements Serializable {
    private static final long serialVersionUID = -56543888L;
    private String text;
    private String uuid;

    public Answer(){

    }

    public Answer(String text, String uuid){
        this.text = text;
        this.uuid = uuid;
    }
    public String getText(){
        return text;
    }

    public String getUuid(){
        return uuid;
    }

    public void setText(String text){
        this.text = text;
    }

    public void setUuid(String uuid){
        this.uuid = uuid;
    }

}
