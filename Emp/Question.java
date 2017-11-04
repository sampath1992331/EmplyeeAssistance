package com.example.anjaleegamage.employeeassistancesystem;

/**
 * Created by AnjaleeGamage on 5/3/2017.
 */

import java.io.Serializable;

public class Question implements Serializable {
    private static final long serialVersionUID = -56543230L;
    private String title;
    private String content;
    private String uuid;

    public Question(){

    }

    public Question(String title, String content, String uuid){
        this.title = title;
        this.content = content;
        this.uuid = uuid;
    }
    public String getTitle(){
        return title;
    }

    public String getContent(){
        return content;
    }

    public String getUuid(){
        return uuid;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setContent(String content){
        this.content = content;
    }

    public void setUuid(String uuid){
        this.uuid = uuid;
    }
}