package com.example.anjaleegamage.employeeassistancesystem;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by Umesh on 5/8/2017.
 */

public class fire extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}

