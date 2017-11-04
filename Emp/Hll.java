package com.example.anjaleegamage.employeeassistancesystem;


import android.app.DialogFragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;

import java.util.Map;

public class Hll extends AppCompatActivity {
    private Spinner Hall;
    private Spinner Dept;
    private TextView Date;
    private TextView TimeFrom;
    private TextView TimeTo;
    private Button Check;
    private Button HallRes;
    private EditText Remaks;
    private Firebase database;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hll);
        database=new Firebase("https://employeeassistencesystem.firebaseio.com/HRes");
        String id=database.push().getKey();

        Hall = (Spinner) findViewById(R.id.Hall_Name);
        ArrayAdapter Halln=ArrayAdapter.createFromResource(this,R.array.Hall,android.R.layout.simple_spinner_item);
        Hall.setAdapter(Halln);

        Dept=(Spinner) findViewById(R.id.Department);
        ArrayAdapter Deptn=ArrayAdapter.createFromResource(this,R.array.Department,android.R.layout.simple_spinner_item);
        Dept.setAdapter(Deptn);


        Date=(TextView) findViewById(R.id.DateShow);
        TimeFrom=(TextView) findViewById(R.id.ShowTimeFrom);
        TimeTo=(TextView) findViewById(R.id.ShowTimeTo);
        Remaks=(EditText) findViewById(R.id.Remarks);
        Check=(Button) findViewById(R.id.Check);
        HallRes=(Button) findViewById(R.id.allocate);

        Check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Hdate=Date.getText().toString();
                final Firebase ref = new Firebase("https://employeeassistencesystem.firebaseio.com/HRes");
                Query query = ref.orderByChild("resDate").equalTo(Hdate);
                query.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        Map<String,Object> value = (Map<String, Object>) dataSnapshot.getValue();
                        String TimeF = String.valueOf(value.get("resTimeFrom"));
                        String TimeT = String.valueOf(value.get("resTimeTo"));
                        String TimeSF=TimeFrom.getText().toString();
                        String TimeST=TimeTo.getText().toString();
                        if((TimeF.equals(TimeSF))&&(TimeT.equals(TimeST))){

                            Toast.makeText(Hll.this,"Always Book",Toast.LENGTH_LONG).show();

                        }

                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });

            }
        });

        HallRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String ResDate=Date.getText().toString();
                final String ResTimeFrom=TimeFrom.getText().toString();
                final String ResTimeTo=TimeTo.getText().toString();
                final String ResHall =Hall.getSelectedItem().toString();
                String ResDept=Dept.getSelectedItem().toString();
                final String ResRemaks=Remaks.getText().toString();




                if((!ResHall.isEmpty())&&(!TextUtils.isEmpty(ResDate))&&(!TextUtils.isEmpty(ResTimeFrom))&&(!TextUtils.isEmpty(ResTimeTo))&&(!ResDept.isEmpty())){

                    String ResID = database.push().getKey();

                    HRes hRes = new HRes(ResID, ResDate, ResHall, ResDept, ResTimeFrom, ResTimeTo,ResRemaks);

                    database.child(ResID).setValue(hRes);
                    Toast.makeText(Hll.this,"Hall Request Successfully ",Toast.LENGTH_SHORT).show();

                    final Firebase ref = new Firebase("https://employeeassistencesystem.firebaseio.com/Data");
                    Query query = ref.orderByChild("department").equalTo(ResDept);
                    query.addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            Map<String,Object> value = (Map<String, Object>) dataSnapshot.getValue();
                            String Tel = String.valueOf(value.get("contactnum"));

                            String phoneNo = Tel;
                            String ResHall1=ResHall.toString();
                            String ResDate1=ResDate.toString();
                            String ResTimeFrom1=ResTimeFrom.toString();
                            String ResTimeTo1=ResTimeTo.toString();
                            String ResRemaks1=ResRemaks.toString();




                            String sms = "You Have a Special Meeting at "+ResHall1+" Meeting Hall "+"On "+ResDate1+" From "+ResTimeFrom1+" To  "+ResTimeTo1+
                                    "\n  "+ResRemaks1;

                            try {
                                SmsManager smsManager = SmsManager.getDefault();
                                smsManager.sendTextMessage(phoneNo, null, sms, null, null);
                                Toast.makeText(getApplicationContext(), "Request Sent!",
                                        Toast.LENGTH_LONG).show();
                            } catch (Exception e) {
                                Toast.makeText(getApplicationContext(),
                                        "Request faild, please try again later!",
                                        Toast.LENGTH_LONG).show();
                                e.printStackTrace();
                            }


                        }

                        @Override
                        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onChildRemoved(DataSnapshot dataSnapshot) {

                        }

                        @Override
                        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onCancelled(FirebaseError firebaseError) {

                        }
                    });


                }



                else{

                    Toast.makeText(Hll.this,"Please Fill All Fields ",Toast.LENGTH_SHORT).show();
                    return;

                }

            }
        });

    }



    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public void showDatePickerDialogH(View v) {
        DialogFragment newFragment = new DatePickerFragmentH();
        newFragment.show(getFragmentManager(), "DatePicker");
    }
    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public void showTimePickerDialogFrom(View v1) {
        DialogFragment newFragment = new TimePickerFragmentFrom();
        newFragment.show(getFragmentManager(), "TimePicker");
    }
    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public void showTimePickerDialogTo(View v1) {
        DialogFragment newFragment = new TimePickerFragmentTo();
        newFragment.show(getFragmentManager(), "TimePicker");
    }

}
