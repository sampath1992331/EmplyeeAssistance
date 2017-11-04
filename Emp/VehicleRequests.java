package com.example.anjaleegamage.employeeassistancesystem;

/**
 * Created by AnjaleeGamage on 5/8/2017.
 */

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class VehicleRequests extends AppCompatActivity {

    EditText editPlace;
    EditText editPurpose;
    EditText editParticipants;
    TextView textview1;
    TextView textview2;
    Button buttonRequest;

    DatabaseReference databaseEmployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_requests);

        databaseEmployee = FirebaseDatabase.getInstance().getReference("vReqest");

        editPlace = (EditText) findViewById(R.id.editPlace);
        editPurpose = (EditText) findViewById(R.id.editPurpose);
        editParticipants = (EditText) findViewById(R.id.editParticipants);
        textview1 = (TextView) findViewById(R.id.textview1);
        textview2 = (TextView) findViewById(R.id.textview2);
        buttonRequest = (Button) findViewById(R.id.buttonRequest);

        buttonRequest.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                addRequest();

            }
        });
    }



    private void addRequest(){
        String place = editPlace.getText().toString().trim();
        String purpose = editPurpose.getText().toString().trim();
        String no_of_participants = editParticipants.getText().toString().trim();
        String date_VRes = textview1.getText().toString().trim();
        String time_VRes = textview2.getText().toString().trim();

        if( (!TextUtils.isEmpty(purpose)) && (!TextUtils.isEmpty(place)) && (!TextUtils.isEmpty(no_of_participants)) && (!date_VRes.equals("Date")) && (!time_VRes.equals("Time")) ){

            //adding values to database
            String vResID = databaseEmployee.push().getKey();

            //*******Temporary id to store*******/
            // String empID_VRes = vResID;

            //empID_VRes,
            VRes vRes = new VRes(vResID, place, purpose, no_of_participants, date_VRes, time_VRes);

            databaseEmployee.child(vResID).setValue(vRes);

            //sending msg to HR Manager

            String phoneNo = "+94719362741";
            String sms = "You are having a message regarding vehicle request" +
                    "\nName: " +
                    "\nID: " +
                    "\nPlace: "+place+
                    "\nNo. of Participants: " +no_of_participants+
                    "\nPurpose: " +purpose+
                    "\nDate: " +date_VRes+
                    "\nTime: " +time_VRes;

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

        }else{
            Toast.makeText(this, "Please fill all the entries", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void showTimePickerDialog(View v1) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

}

