package com.example.anjaleegamage.employeeassistancesystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Home extends AppCompatActivity implements View.OnClickListener{
    private FirebaseAuth firebaseAuth;
    private TextView userid;
    private Button Logout;
    private Button hide;
    public Button button_VehicleRequest;
    public Button button_employee_finder;
    public Button button_qa_forum;
    public Button button_hall_allocation;

    public void vRes(){
        button_VehicleRequest = (Button)findViewById(R.id.btn_vehicle_request);

        button_VehicleRequest.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent vRes = new Intent(Home.this,VehicleRequests.class);
                startActivity(vRes);
            }
        }
        );
    }

    public void empFinder(){
        button_employee_finder = (Button)findViewById(R.id.btn_employee_finder);

        button_employee_finder.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent empFinder = new Intent(Home.this,InfoFinder.class);
                startActivity(empFinder);
            }
        }
        );
    }

    public void qaForum(){
        button_qa_forum = (Button)findViewById(R.id.btn_qa_forum);

        button_qa_forum.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent qaForum = new Intent(Home.this, QAmain.class);
                startActivity(qaForum);
            }
        }
        );
    }

    public void hallAllocation(){
        button_hall_allocation = (Button)findViewById(R.id.btn_hall_allocation);

        button_hall_allocation.setOnClickListener(new View.OnClickListener(){
                                               @Override
                                               public void onClick(View view) {
         Intent hallAllocation = new Intent(Home.this, Hll.class);
         startActivity(hallAllocation);
         }
         }
        );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        vRes();
        empFinder();
        qaForum();
        hallAllocation();


        userid=(TextView) findViewById(R.id.id);
        Logout=(Button) findViewById(R.id.logout);
        Logout.setOnClickListener(this);
        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(this,Login.class));
        }
        FirebaseUser user=firebaseAuth.getCurrentUser();
        userid=(TextView) findViewById(R.id.id);
        userid.setText("Welcome"+user.getEmail());
        if(user.getEmail()=="Admin"){
           // hide=(Button) findViewById(R.id.button4);
           // hide.setEnabled(false);


        }
    }

    @Override
    public void onClick(View view) {
        if(view==Logout){

            startActivity(new Intent(this,Login.class));
        }

    }


}

