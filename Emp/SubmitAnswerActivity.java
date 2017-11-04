package com.example.anjaleegamage.employeeassistancesystem;

/**
 * Created by AnjaleeGamage on 5/3/2017.
 */


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SubmitAnswerActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_answer);

    }

    public void submitAnswer(View view){
        EditText answerET= (EditText) findViewById(R.id.answerET);
        String text = answerET.getText().toString();

        Answer answer = new Answer(text,
                FirebaseAuth.getInstance().getCurrentUser().getUid());

        String questionKey = getIntent().getStringExtra("question_key");


        FirebaseDatabase.getInstance().getReference().child("answers").child(questionKey).push()
                .setValue(answer, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference reference) {
                        if (databaseError != null) {
                            Toast.makeText(SubmitAnswerActivity.this,"Unable to submit question",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            finish();
                            Toast.makeText(SubmitAnswerActivity.this,"Question submitted",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}