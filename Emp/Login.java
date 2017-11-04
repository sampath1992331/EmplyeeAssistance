package com.example.anjaleegamage.employeeassistancesystem;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements View.OnClickListener{
    private Button Login;
    private EditText username;
    private EditText password;
    private TextView attempt;
    int attemptCounter=5;

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() !=null){

            finish();
          startActivity(new Intent(getApplicationContext(),Home.class));
        }
        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        attempt=(TextView)findViewById(R.id.attempts);

        Login=(Button)findViewById(R.id.login);
        progressDialog=new ProgressDialog(this);

       Login.setOnClickListener(this);

    }

    private void UserLogin() {

        String Username=username.getText().toString().trim();
        String Password=password.getText().toString().trim();
        attempt.setText(Integer.toString(attemptCounter));
        if(TextUtils.isEmpty(Username)){

            Toast.makeText(this,"Please Enter Username",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(Password)){

            Toast.makeText(this,"Please Enter Password",Toast.LENGTH_LONG).show();
            return;

        }
        progressDialog.setMessage("Loing User");
       progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(Username,Password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();

                if(task.isSuccessful()){
                    finish();
                    Toast.makeText(com.example.anjaleegamage.employeeassistancesystem.Login.this,"Login Successfully",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),Home.class));
                }
                else{
                    Toast.makeText(com.example.anjaleegamage.employeeassistancesystem.Login.this,"Invalid User Email or Password ",Toast.LENGTH_SHORT).show();
                    attemptCounter--;
                    attempt.setText(Integer.toString(attemptCounter));
                    if(attemptCounter==0){

                        Login.setEnabled(false);
                    }

                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        if(view==Login){

            UserLogin();
        }

    }


}
