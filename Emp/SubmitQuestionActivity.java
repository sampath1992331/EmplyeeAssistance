package com.example.anjaleegamage.employeeassistancesystem;

/**
 * Created by AnjaleeGamage on 5/3/2017.
 */


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SubmitQuestionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_question);

        Button submitBtn = (Button) findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText titleET= (EditText) findViewById(R.id.titleET);
                EditText contentET = (EditText) findViewById(R.id.contentET);
                String title = titleET.getText().toString();
                String content = contentET.getText().toString();

                Question question = new Question(title, content,
                        FirebaseAuth.getInstance().getCurrentUser().getUid());

                FirebaseDatabase.getInstance().getReference().child("questions").push()
                        .setValue(question, new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(DatabaseError databaseError, DatabaseReference reference) {
                                if (databaseError != null) {
                                    Toast.makeText(SubmitQuestionActivity.this,"Unable to submit question",
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    startActivity(new Intent(SubmitQuestionActivity.this, QAmain.class));
                                    Toast.makeText(SubmitQuestionActivity.this,"Question submitted",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}
